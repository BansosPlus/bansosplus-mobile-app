package com.dicoding.bansosplus.navigation.views.home.detailBansos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.navigation.data.model.FeedbackItem
import com.dicoding.bansosplus.repository.BansosRepository
import com.dicoding.bansosplus.repository.FeedbackRepository
import kotlinx.coroutines.launch

class DetailBansosViewModel(
    private val sessionManager: SessionManager,
    ) : ViewModel() {
    private val bansosRepository: BansosRepository = BansosRepository(sessionManager)
    private val feedbackRepository: FeedbackRepository = FeedbackRepository(sessionManager)
    private val _bansosItem = MutableLiveData<BansosItem?>()
    private val _feedbackList = MutableLiveData(ArrayList<FeedbackItem>())
    val bansosItem: MutableLiveData<BansosItem?>
        get() = _bansosItem
    val feedbackList: LiveData<ArrayList<FeedbackItem>>
        get() = _feedbackList

    fun get(id: String) {
        viewModelScope.launch {
            try {
                val response = bansosRepository.getDetail(id)
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    _bansosItem.value = item
                    Log.i("BANSOS", "Get detail bansos successfully")
                } else {
                    Log.e("BANSOS", "Response failed")
                }
            } catch (e: Exception) {
                Log.e("BANSOS", "Connection failed")
            }
        }
    }

    fun getFeedback(id: Int) {
        viewModelScope.launch {
            try {
                val request = FeedbackRequest()
                request.bansosId = id
                val response = feedbackRepository.getFeedbackByBansos(request)
                if (response.isSuccessful) {
                    val list = response.body()?.data
                    _feedbackList.value = list as ArrayList<FeedbackItem>
                    Log.i("FEEDBACK", "Get list of feedback successfully $list")
                } else {
                    Log.e("FEEDBACK", "Response failed")
                }
            } catch (e: Exception) {
                Log.e("FEEDBACK", "Connection failed")
            }

        }
    }


}
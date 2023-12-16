package com.dicoding.bansosplus.navigation.views.bansos.acceptedBansos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.AcceptedBansosItem
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.navigation.data.model.FeedbackItem
import com.dicoding.bansosplus.repository.BansosRegistrationRepository
import com.dicoding.bansosplus.repository.BansosRepository
import kotlinx.coroutines.launch

class DiterimaBansosViewModel(
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val bansosRegistrationRepository: BansosRegistrationRepository = BansosRegistrationRepository(sessionManager)
    private val _acceptedBansosItem = MutableLiveData<AcceptedBansosItem?>()
    val acceptedBansosItem: MutableLiveData<AcceptedBansosItem?>
        get() = _acceptedBansosItem

    fun get(id: String) {
        viewModelScope.launch {
            try {
                val response = bansosRegistrationRepository.getBansosRegistrationDetail(id)
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    _acceptedBansosItem.value = item
                    Log.i("BANSOS", "Get detail bansos successfully")
                } else {
                    Log.e("BANSOS", "Response failed")
                }
            } catch (e: Exception) {
                Log.e("BANSOS", "Connection failed")
            }
        }
    }
}
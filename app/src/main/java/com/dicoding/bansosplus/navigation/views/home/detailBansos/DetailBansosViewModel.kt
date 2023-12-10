package com.dicoding.bansosplus.navigation.views.home.detailBansos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.repository.BansosRepository
import kotlinx.coroutines.launch

class DetailBansosViewModel(
    private val sessionManager: SessionManager,
    ) : ViewModel() {
    private val bansosRepository: BansosRepository = BansosRepository(sessionManager)
    private val _bansosItem = MutableLiveData<BansosItem?>()
    val bansosItem: MutableLiveData<BansosItem?>
        get() = _bansosItem

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
}
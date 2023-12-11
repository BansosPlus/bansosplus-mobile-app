package com.dicoding.bansosplus.navigation.views.bansos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import com.dicoding.bansosplus.repository.BansosRegistrationRepository
import kotlinx.coroutines.launch

class BansosViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val bansosRegistrationRepository: BansosRegistrationRepository = BansosRegistrationRepository(sessionManager)
    private val _bansosRegistrationList = MutableLiveData(ArrayList<BansosStatusItem>())
    val bansosRegistrationList: LiveData<ArrayList<BansosStatusItem>>
        get() = _bansosRegistrationList

    fun getBansos() {
        viewModelScope.launch {
            try {
                val response = bansosRegistrationRepository.getBansosRegistrationByUser()
                if (response.isSuccessful) {
                    val list = response.body()?.data
                    _bansosRegistrationList.value = list as ArrayList<BansosStatusItem>
                    Log.i("BANSOS", "Get list of bansos registration successfully")
                } else {
                    Log.e("BANSOS", "Response failed")
                }
            } catch (e: Exception) {
                Log.e("BANSOS", "Connection failed")
            }

        }
    }
}
package com.dicoding.bansosplus.navigation.views.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.repository.RetrofitRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val retrofitRepository: RetrofitRepository = RetrofitRepository()
    private val _bansosList = MutableLiveData(ArrayList<BansosItem>())
    val bansosList: LiveData<ArrayList<BansosItem>>
        get() = _bansosList

    fun getBansos() {
        viewModelScope.launch {
            try {
                val response = retrofitRepository.getBansos()
                if (response.isSuccessful) {
                    val list = response.body()?.data
                    _bansosList.value = list as ArrayList<BansosItem>
                    Log.i("BANSOS", "Get branch successful")
                } else {
                    Log.e("BANSOS", "Response failed")
                }
            } catch (e: Exception) {
                Log.e("BANSOS", "Connection failed")
            }

        }
    }
}
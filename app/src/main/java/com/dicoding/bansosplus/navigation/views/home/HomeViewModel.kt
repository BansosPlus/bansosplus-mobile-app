package com.dicoding.bansosplus.navigation.views.home

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.repository.BansosRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val bansosRepository: BansosRepository = BansosRepository(sessionManager)
    private val _bansosList = MutableLiveData(ArrayList<BansosItem>())
    val bansosList: LiveData<ArrayList<BansosItem>>
        get() = _bansosList

    fun getBansos() {
        viewModelScope.launch {
            try {
                val response = bansosRepository.get()
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
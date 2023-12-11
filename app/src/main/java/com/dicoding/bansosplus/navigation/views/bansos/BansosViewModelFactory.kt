package com.dicoding.bansosplus.navigation.views.bansos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.views.home.HomeViewModel

class BansosViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BansosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BansosViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
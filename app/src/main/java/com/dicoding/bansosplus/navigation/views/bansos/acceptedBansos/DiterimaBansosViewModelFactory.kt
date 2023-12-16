package com.dicoding.bansosplus.navigation.views.bansos.acceptedBansos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.SessionManager

class DiterimaBansosViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiterimaBansosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DiterimaBansosViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
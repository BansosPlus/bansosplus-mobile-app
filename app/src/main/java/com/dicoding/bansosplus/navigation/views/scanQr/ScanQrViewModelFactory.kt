package com.dicoding.bansosplus.navigation.views.scanQr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.SessionManager

class ScanQrViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScanQrViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScanQrViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
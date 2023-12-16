package com.dicoding.bansosplus.navigation.views.history.detailHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.views.home.detailBansos.DetailBansosViewModel

class DetailHistoryViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailHistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailHistoryViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
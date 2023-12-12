package com.dicoding.bansosplus.navigation.views.profile

import android.provider.ContactsContract.Profile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.views.home.HomeViewModel
import com.dicoding.bansosplus.pref.UserPreferences
import com.dicoding.bansosplus.repository.UserRepository

class ProfileViewModelFactory(private val sessionManager: SessionManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(sessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
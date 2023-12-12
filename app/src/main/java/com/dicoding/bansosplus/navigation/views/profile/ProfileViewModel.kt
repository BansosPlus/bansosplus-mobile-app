package com.dicoding.bansosplus.navigation.views.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.UserItem
import com.dicoding.bansosplus.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val sessionManager: SessionManager): ViewModel() {

    private val userRepository: UserRepository = UserRepository(sessionManager)

    private val _profileData =  MutableLiveData<UserItem>()
    val profileData: LiveData<UserItem>
        get() = _profileData

    fun getUserData(){
        viewModelScope.launch {
            try {
                val response = userRepository.get()
                if (response.isSuccessful){
                    val user = response.body()?.data
                    _profileData.value = user as UserItem
                    Log.d("USER", "User Fetched")
                }else{
                    Log.d("USER", "Failed to Fetch User")
                }
            }catch (e: Exception){
                Log.e("USER", e.toString())
            }
        }
    }
//    fun getUserData(): LiveData<UserItem> =
//        userRepository.getSession().asLiveData()


}
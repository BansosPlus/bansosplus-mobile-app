package com.dicoding.bansosplus.navigation.views.bansos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import com.dicoding.bansosplus.navigation.data.model.ValidateRegisItem
import com.dicoding.bansosplus.repository.BansosRegistrationRepository
import kotlinx.coroutines.launch

class BansosViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val bansosRegistrationRepository: BansosRegistrationRepository = BansosRegistrationRepository(sessionManager)
    private val _bansosRegistrationList = MutableLiveData(ArrayList<BansosStatusItem>())
    private val _bansosStatusItem = MutableLiveData<ValidateRegisItem?>()
    val bansosRegistrationList: LiveData<ArrayList<BansosStatusItem>>
        get() = _bansosRegistrationList

    val bansosStatusItem: MutableLiveData<ValidateRegisItem?>
        get() = _bansosStatusItem

    fun getBansos() {
        viewModelScope.launch {
            try {
                val response = bansosRegistrationRepository.getBansosRegistrationByUser("ON_PROGRESS,ACCEPTED")
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

    fun validateRegis(regisId: String) {
        viewModelScope.launch {
            try {
                val response = bansosRegistrationRepository.validateRegistration(regisId)
                Log.i("QR Codes", "status ${response.isSuccessful}")
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    _bansosStatusItem.value = item
                    Log.i("BANSOS", "Validate bansos registration successfully")
                } else {
                    Log.e("BANSOS", "Response failed")
                    val item = ValidateRegisItem(null, null, null, null, "FAILED",null, null )
                    _bansosStatusItem.value = item
                }
            }catch (e: Exception){
                Log.e("PAYMENT STATUS", "Connection failed")
            }
        }
    }


}
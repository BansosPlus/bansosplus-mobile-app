package com.dicoding.bansosplus.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.bansosplus.navigation.data.model.UserItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//supaya bisa save session || ntar aja dipakenya

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreferences internal constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val EMAIL = stringPreferencesKey("email")
        private val NAME = stringPreferencesKey("name")
        private val TOKEN = stringPreferencesKey("token")
        private val NIK = stringPreferencesKey("nik")
        private val NOKK = stringPreferencesKey("no_kk")
        private val ROLE = stringPreferencesKey("role")
        private val IMAGEURL = stringPreferencesKey("image_url")
        private val INCOME = stringPreferencesKey("income")
        private val WALLQUALITY = stringPreferencesKey("wall_quality")
        private val NUMBEROFMEALS = stringPreferencesKey("number_of_meals")
        private val FUEL = stringPreferencesKey("fuel")
        private val EDUCATION = stringPreferencesKey("education")
        private val TOTALASSET = stringPreferencesKey("total_asset")
        private val TREATMENT = stringPreferencesKey("treatment")
        private val NUMBEROFDEPENDENTS = stringPreferencesKey("number_of_dependents")

        fun getInstance(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun saveSession(user: UserItem) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = user.email
            preferences[NAME] = user.name
            preferences[TOKEN] = user.token
            preferences[NIK] = user.nik
            preferences[NOKK] = user.noKk
            preferences[ROLE] = user.income
            preferences[IMAGEURL] = user.imageUrl
            preferences[INCOME] = user.income
            preferences[WALLQUALITY] = user.wallQuality
            preferences[NUMBEROFMEALS] = user.numberOfMeals
            preferences[FUEL] = user.fuel
            preferences[EDUCATION] = user.education
            preferences[TOTALASSET] = user.totalAsset
            preferences[TREATMENT] = user.treatment
            preferences[NUMBEROFDEPENDENTS]= user.numberOfDependents
        }
    }

    fun getSession(): Flow<UserItem> {
        return dataStore.data.map { preferences ->
            UserItem(
                preferences[EMAIL] ?: "",
                preferences[NAME] ?: "",
                preferences[TOKEN] ?: "",
                preferences[NIK] ?: "",
                preferences[NOKK] ?: "",
                preferences[ROLE] ?: "",
                preferences[IMAGEURL] ?: "",
                preferences[INCOME] ?: "",
                preferences[WALLQUALITY] ?: "",
                preferences[NUMBEROFMEALS] ?: "",
                preferences[FUEL] ?: "",
                preferences[EDUCATION] ?: "",
                preferences[TOTALASSET] ?: "",
                preferences[TREATMENT] ?: "",
                preferences[NUMBEROFDEPENDENTS] ?: ""
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
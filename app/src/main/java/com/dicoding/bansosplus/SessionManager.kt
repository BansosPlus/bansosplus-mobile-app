package com.dicoding.bansosplus

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val NAME = "user_name"
        const val TOKEN = "user_token"
        const val NIK = "user_nik"
        const val NOKK = "user_no_kk"
        const val ROLE = "user_role"
    }

    fun saveName(name: String) {
        prefs.edit().putString(NAME, name).apply()
    }

    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN, token).apply()
    }
    fun saveRole(role: String) {
        prefs.edit().putString(ROLE, role).apply()
    }

    fun fetchName(): String? {
        return prefs.getString(NAME, null)
    }

    fun fetchToken(): String? {
        return prefs.getString(TOKEN, null)
    }

    fun fetchRole(): String? {
        return prefs.getString(ROLE, null)
    }

    fun fetchNIK(): String? {
        return prefs.getString(NIK, null)
    }

    fun fetchNoKK(): String? {
        return prefs.getString(NOKK, null)
    }
}
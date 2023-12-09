package com.dicoding.bansosplus

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val NAME = ""
        const val TOKEN = ""
    }

    fun saveName(name: String) {
        prefs.edit().putString(NAME, name).apply()
    }

    fun saveToken(token: String) {
        prefs.edit().putString(TOKEN, token).apply()
    }

    fun fetchName(): String? {
        return prefs.getString(NAME, null)
    }

    fun fetchToken(): String? {
        return prefs.getString(TOKEN, null)
    }
}
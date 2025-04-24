package com.example.admin

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    var pref: SharedPreferences
    var edior: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0

    constructor(context: Context) {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        edior = pref.edit()
    }
    companion object {
        val PREF_NAME: String = "SessionDemo"
        val IS_LOGIN: String = "isLogin"
        val KEY_USERNAME: String = "username"
        val KEY_PASSWORD: String = "password"
    }

    fun createLoginSession( username: String ,password: String) {
        edior.putBoolean(IS_LOGIN, true)
        edior.putString(KEY_USERNAME, username)
        edior.putString(KEY_PASSWORD, password)
        edior.commit()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }
}
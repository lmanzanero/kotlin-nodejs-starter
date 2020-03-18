package com.example.luismanzanero.kotlinnodejsstarter.preferences

import android.content.Context
import android.content.SharedPreferences

object AuthPrefs {
    private const val NAME = "SESSION"
    private  const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }
    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun getAccessToken(): String? {
        return preferences.getString("ACCESS_TOKEN", "")
    }

    fun getRefreshToken(): String? {
        return preferences.getString("REFRESH_TOKEN", "")
    }

    fun isSessionValid(): Boolean {
        return preferences.getBoolean("LOGGED_IN", false)
    }

    fun clearTokensAndSession() {
        preferences.edit {
            it.putString("ACCESS_TOKEN", "")
            it.putString("REFRESH_TOKEN","")
            it.putBoolean("LOGGED_IN", false)
        }
    }

    fun setTokensAndSession(accessToken: String?, refreshedToken: String?, session: Boolean){
        preferences.edit {
            it.putString("ACCESS_TOKEN", accessToken)
            it.putString("REFRESH_TOKEN",refreshedToken)
            it.putBoolean("LOGGED_IN", session)
        }
    }
}

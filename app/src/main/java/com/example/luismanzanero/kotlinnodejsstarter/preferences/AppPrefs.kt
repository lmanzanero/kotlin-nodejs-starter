package com.example.luismanzanero.kotlinnodejsstarter.preferences


import android.content.Context
import android.content.SharedPreferences

object AppPrefs {
    private const val NAME = "APP_PREFS"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }
    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
        val editor  = edit()
        operation(editor)
        editor.apply()
    }

    fun setNightModeState(themeName: Boolean){
        preferences.edit {
            it.putBoolean("Theme", themeName)
        }
    }

    fun loadNightModeState(): Boolean?  {
        return preferences.getBoolean("Theme", false )
    }
}

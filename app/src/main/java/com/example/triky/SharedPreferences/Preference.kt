package com.example.triky.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class Preference(context: Context) {

    private val ONBOARDING_JUST_ONCE = ""
    private val key = "Key"
    private val preference = context.getSharedPreferences(ONBOARDING_JUST_ONCE , Context.MODE_PRIVATE)
    private val editor = preference.edit()


    fun saveValueWhenAppFirstTimeOpen(value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getValueWhenAppFirstTimeOpen(): Boolean {
        return preference.getBoolean(key, false)
    }
}
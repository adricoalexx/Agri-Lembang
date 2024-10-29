package com.projects.agrilembang.datastore

import android.content.Context
import com.projects.agrilembang.datastore.PreferencesKey.EMAIL_KEY
import com.projects.agrilembang.datastore.PreferencesKey.NAME_PREF
import com.projects.agrilembang.datastore.PreferencesKey.PASSWORD_KEY

class SharedPreferencesManager(context: Context) {

    private val preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)

    var email: String?
        get() = preferences.getString(EMAIL_KEY, "")
        set(value) {
            preferences.edit().apply {
                putString(EMAIL_KEY, value)
                apply()
            }
        }

    var password: String?
        get() = preferences.getString(PASSWORD_KEY, "")
        set(value) {
            preferences.edit().apply {
                putString(PASSWORD_KEY, value)
                apply()
            }
        }

    fun clear(){
        preferences.edit().apply {
            clear()
            apply()
        }
    }
}
package com.projects.agrilembang.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


object PreferencesKey {

    const val NAME_PREF = "login_preferences"
    const val EMAIL_KEY = "email"
    const val PASSWORD_KEY = "password"

    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

}
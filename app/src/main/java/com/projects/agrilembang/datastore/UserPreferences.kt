package com.projects.agrilembang.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.projects.agrilembang.datastore.PreferencesKey.IS_LOGGED_IN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("StatusLogin")
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    suspend fun saveStatus(isLogin : Boolean) = context.dataStore.edit { preferences ->
        preferences[IS_LOGGED_IN] = isLogin
    }

    suspend fun clearStatus() = context.dataStore.edit { preferences ->
        preferences.remove(IS_LOGGED_IN)
    }

}
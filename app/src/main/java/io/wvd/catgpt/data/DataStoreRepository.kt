package io.wvd.catgpt.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "local_store")

class DataStoreRepository(context: Context) {

    private object PreferencesKey {
        val offlineModeKey = booleanPreferencesKey(name = "offline_mode")
    }

    private val dataStore = context.dataStore

    suspend fun setOfflineModeState(isOfflineMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.offlineModeKey] = isOfflineMode
        }
    }

}
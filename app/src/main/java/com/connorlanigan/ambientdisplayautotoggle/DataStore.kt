package com.connorlanigan.ambientdisplayautotoggle

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
 The functions in this file provide access to the settings
 that are configured in the app's UI.
*/

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
private val ENABLED = booleanPreferencesKey("enabled")
private val SHOW_NOTIFICATIONS = booleanPreferencesKey("show_notifications")

fun getEnabled(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { store -> store[ENABLED] ?: true }
}

suspend fun setEnabled(context: Context, value: Boolean) {
    context.dataStore.edit { store -> store[ENABLED] = value }
}

fun getShowNotifications(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { store -> store[SHOW_NOTIFICATIONS] ?: false }
}

suspend fun setShowNotifications(context: Context, value: Boolean) {
    context.dataStore.edit { store -> store[SHOW_NOTIFICATIONS] = value }
}
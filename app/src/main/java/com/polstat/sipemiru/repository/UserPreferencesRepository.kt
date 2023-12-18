package com.polstat.sipemiru.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object{
        val TOKEN = stringPreferencesKey("user_token")
        val FIRST_NAME = stringPreferencesKey("user_name")
        val LAST_NAME = stringPreferencesKey("user_last_name")
        val EMAIL = stringPreferencesKey("user_email")
        val IS_ADMIN = booleanPreferencesKey("user_is_admin")
        val IS_PEMINJAM = booleanPreferencesKey("user_is_peminjam")
        const val TAG = "UserPreferencesRepo"
    }

    val user: Flow<UserState> = dataStore.data
        .catch {
            if (it is IOException){
                Log.e(TAG, "Error reading preferences:", it)
                emit(emptyPreferences())
            }else{
                throw it
            }
        }
        .map { preferences ->
            UserState(
                preferences[TOKEN] ?: "",
                preferences[FIRST_NAME] ?: "",
                preferences[LAST_NAME]?:"",
                preferences[EMAIL] ?: "",
                preferences[IS_ADMIN] ?: false,
                preferences[IS_PEMINJAM] ?: false
            )
        }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun saveFirstName(firstName: String) {
        dataStore.edit { preferences ->
            preferences[FIRST_NAME] = firstName
        }
    }

    suspend fun saveLastName(lastName: String) {
        dataStore.edit { preferences ->
            preferences[LAST_NAME] = lastName
        }
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    suspend fun saveIsAdmin(isAdmin: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ADMIN] = isAdmin
        }
    }

    suspend fun saveIsPeminjam(isPeminjam: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_PEMINJAM] = isPeminjam
        }
    }
}

data class UserState(
    val token: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val isAdmin: Boolean,
    val isPeminjam: Boolean
)


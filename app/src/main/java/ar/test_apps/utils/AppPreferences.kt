package ar.test_apps.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(context: Context) {
    private var preferences: SharedPreferences = context.getSharedPreferences(
        APP_PRIVATE_SHARED_PREFERENCES_KEY,
        Context.MODE_PRIVATE
    )

    private fun getSharedEditor(): SharedPreferences.Editor {
        return preferences.edit()
    }

    fun getUrlType(): String {
        return preferences.getString(BASE_URL, BASE_URL)!!
    }

    fun setUrlType(url: String) {
        getSharedEditor().putString(BASE_URL, url).apply()
    }

    fun clearBaseUrl() {
        getSharedEditor().remove(BASE_URL).apply()
    }


    companion object {
        private const val BASE_URL = "BASE_URL"
        private const val APP_PRIVATE_SHARED_PREFERENCES_KEY = "AR_DEV"

    }

}

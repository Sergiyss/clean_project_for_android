package ua.dev.webnauts.cleanproject.cache

import android.content.Context

class CacheManagerApi(context : Context){
    private val sharedPreferences = context.getSharedPreferences("api_cache", Context.MODE_PRIVATE)

    fun saveData(key: String, data: String) {
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}
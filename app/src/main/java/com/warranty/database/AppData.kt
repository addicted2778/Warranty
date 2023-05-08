package com.warranty.database

import android.content.Context

object AppData {

    const val globalPref_fcm = "warranty_global_pref_fcm"
    const val globalPref = "warranty_global_pref"
    private const val default_pref = "warranty_pref"

    fun save(context: Context?, key: String?, value: String?) {
        save(default_pref, context, key, value)
    }

    fun save(prefName: String?, context: Context?, key: String?, value: String?) {
        context!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .commit()
    }

    fun save(prefName: String?, context: Context?, key: String?, value: Boolean) {
        context!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, value)
            .commit()
    }

    fun save(prefName: String?, context: Context, key: String?, value: Long) {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .edit()
            .putLong(key, value)
            .commit()
    }

    fun save(prefName: String?, context: Context, key: String?, value: Int) {
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .edit()
            .putInt(key, value)
            .commit()
    }

    fun save(context: Context, key: String?, value: Int) {
        save(default_pref, context, key, value)
    }

    fun save(context: Context?, key: String?, value: Boolean) {
        save(default_pref, context, key, value)
    }

    fun saveToGlobal(context: Context?, key: String?, value: Boolean) {
        save(globalPref, context, key, value)
    }

    fun save(context: Context, key: String?, value: Long) {
        save(default_pref, context, key, value)
    }

    fun getString(prefName: String?, context: Context?, key: String?): String? {
        return context!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getString(key, "")
    }

    fun getString(context: Context?, key: String?): String? {
        return getString(default_pref, context, key)
    }

    fun getInt(prefName: String?, context: Context, key: String?): Int {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getInt(key, 0)
    }

    fun getInt(context: Context, key: String?): Int {
        return getInt(default_pref, context, key)
    }

    fun getBoolean(prefName: String?, context: Context?, key: String?): Boolean {
        return context!!.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    fun getBoolean(context: Context?, key: String?): Boolean {
        return getBoolean(default_pref, context, key)
    }

    fun getBooleanGlobal(context: Context?, key: String?): Boolean {
        return getBoolean(globalPref, context, key)
    }

    private fun getLong(prefName: String?, context: Context, key: String?): Long {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getLong(key, 0L)
    }

    fun getLong(context: Context, key: String?): Long {
        return getLong(default_pref, context, key)
    }

    fun clearAll(context: Context) {
        context.getSharedPreferences(default_pref, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .commit()
    }
}
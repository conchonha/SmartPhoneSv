package edu.abcd.smartphone.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import edu.abcd.smartphone.data_source.remote.response.LoginAccountRespose
import java.io.File

class SharePrefs(application: Context) {
    private var _sharedPref: SharedPreferences? = null
    private var _editor: SharedPreferences.Editor? = null
    val gson by lazy { Gson() }
    val sharedPref
        get() = _sharedPref

    /**
     * init instance of [@see sharedPref] or [@see editor]
     */
    init {
        _sharedPref = application.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)
        _editor = _sharedPref?.edit()
    }

    /**
     * @see _editor null -> throw errors
     * @param key earch value is save from key
     * @param data is value any
     */
    fun <T> put(key: String, data: T) {
        if (_editor == null) {
            throw Throwable("Editor or SharedPref not init")
        }

        when (data) {
            is String -> _editor?.putString(key, data)
            is Boolean -> _editor?.putBoolean(key, data)
            is Float -> _editor?.putFloat(key, data)
            is Double -> _editor?.putFloat(key, data.toFloat())
            is Int -> _editor?.putInt(key, data)
            is Long -> _editor?.putLong(key, data)
            else -> _editor?.putString(key, gson.toJson(data))
        }
        _editor?.apply()
    }

    fun getUser(): LoginAccountRespose? {
        val user = sharedPref?.getString(Const.KEY_USER, "") ?: return null
        return Gson().fromJson(user, LoginAccountRespose::class.java)
    }

    /**
     * @see _editor null -> throw errors
     * remove list key the saved keys
     */
    fun remove(vararg key: String) {
        if (_editor == null) {
            throw Throwable("Editor or SharedPref not init")
        }

        key.forEach {
            _editor?.remove(it)?.commit()
        }
    }

    /**
     * @see _editor null -> throw errors
     * delete all the saved keys
     */
    fun removeAll() {
        if (_editor == null) {
            throw Throwable("Editor or SharedPref not init")
        }

        _editor?.clear()?.commit()
    }

    companion object {
        const val EMPTY = ""
        const val FLOAT_DEFAULT = -1f
        const val INT_DEFAULT = -1
        const val LONG_DEFAULT = -1L
        const val PHONE_PREFIX = "phone_"
    }

    fun calculateSizeRecursively(context: Context): String {
        val value =
            context.cacheDir.walkBottomUp().fold(0L) { acc, file -> acc + file.length() } / 1024
        return value.toString()
    }

    fun deleteCache(context: Context) {
        try {
            val dir: File = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory()) {
            val children: Array<String> = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile()) {
            dir.delete()
        } else {
            false
        }
    }
}
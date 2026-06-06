package com.example.bookshop.core.preferences

import android.content.Context
import androidx.core.content.edit

class AppPreferences(
    context: Context
) {
    private val sharedPreferences = context.applicationContext.getSharedPreferences(
        PREF_NAME,
        Context.MODE_PRIVATE
    )

    fun saveLastOpenedBook(bookId: String, bookTitle: String) {
        sharedPreferences.edit {
            putString(KEY_LAST_BOOK_ID, bookId)
            putString(KEY_LAST_BOOK_TITLE, bookTitle)
        }
    }

    fun getLastOpenedBookTitle(): String {
        return sharedPreferences.getString(KEY_LAST_BOOK_TITLE, "").orEmpty()
    }

    fun setDarkMode(isEnabled: Boolean) {
        sharedPreferences.edit {
            putBoolean(KEY_DARK_MODE, isEnabled)
        }
    }

    fun isDarkModeEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_DARK_MODE, false)
    }

    companion object {
        private const val PREF_NAME = "book_preferences"
        private const val KEY_LAST_BOOK_ID = "last_book_id"
        private const val KEY_LAST_BOOK_TITLE = "last_book_title"
        private const val KEY_DARK_MODE = "dark_mode_enabled"
    }
}
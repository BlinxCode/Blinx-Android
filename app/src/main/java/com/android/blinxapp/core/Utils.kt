package com.android.blinxapp.core

import android.util.Log
import com.android.blinxapp.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())
    }
}
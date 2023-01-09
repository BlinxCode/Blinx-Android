package com.olajide.pinviewscreen.data

sealed class PinActions{
    data class Number(val value: String) : PinActions()
    object BackSpace : PinActions()
}
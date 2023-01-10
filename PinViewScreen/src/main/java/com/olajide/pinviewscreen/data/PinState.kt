package com.olajide.pinviewscreen.data

data class PinState(
    val pin: String = "",
    var pinLimit: Int =0,
    val pinListState: ArrayList<Boolean> = ArrayList()

)

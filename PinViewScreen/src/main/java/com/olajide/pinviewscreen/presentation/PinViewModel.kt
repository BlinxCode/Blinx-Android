package com.olajide.pinviewscreen.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.olajide.pinviewscreen.data.PinActions
import com.olajide.pinviewscreen.data.PinState

class PinViewModel: ViewModel() {
    var state by mutableStateOf(PinState())
    private set

    var dotsCurrentState = ArrayList<Boolean>()
    private set

    fun onAction(action: PinActions){
        when(action){
            is PinActions.Number -> enterPin(action.value)
            is PinActions.BackSpace -> backSpace()
        }
    }

    private fun backSpace() {
        when {
            state.pin.isBlank() or state.pin.isEmpty() -> return
        }

        state = state.copy(pin = state.pin.dropLast(1))
        Log.d("LogLengthOfPin", state.pin.length.toString())

        dotsCurrentState[if(state.pin.isEmpty()) 0 else state.pin.length ] = false
        state = state.copy(pinListState = dotsCurrentState)
    }

    private fun enterPin(value: String) {
        when (state.pin.length) {
            state.pinLimit -> return
        }
        state = state.copy(pin = state.pin +value)
        dotsCurrentState[state.pin.length-1] = true
        state = state.copy(pinListState = dotsCurrentState)

    }

     fun setDefaultDotState( arrayLength: Int):ArrayList<Boolean>{
         Log.d("LogUserInput",arrayLength.toString())

         if (dotsCurrentState.size <1){
             dotsCurrentState.clear()
             for (i in 0 until arrayLength){
                 dotsCurrentState.add(false)
                 Log.d("LogUnput",dotsCurrentState[i].toString())
             }
         }

         return dotsCurrentState
    }
}

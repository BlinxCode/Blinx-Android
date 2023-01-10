package com.olajide.pinviewscreen.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olajide.pinviewscreen.data.PinActions
import com.olajide.pinviewscreen.data.PinState
import kotlinx.coroutines.launch

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
        Log.d("backSpace", state.pin)

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

     fun setDefaultDotState(limit: Int):ArrayList<Boolean>{
         Log.d("setDefaultDotState1",limit.toString())

         viewModelScope.launch {
             if (dotsCurrentState.size ==limit){
                 Log.d("DotsState","Dot state has been set")

             }else{
                 dotsCurrentState.clear()
                 for (i in 0 until limit){
                     dotsCurrentState.add(false)
                 }
             }

         }

         return dotsCurrentState
    }

    fun returnDotPosition(limit: Int):Boolean{
        Log.d("returnDotPosition1",limit.toString())
        return dotsCurrentState[limit]
    }

    fun resetData() {

        when(state.pin.length){
            state.pinLimit -> {

                state = state.copy(pin = state.pin.removeRange(0..3))
                for (i in 0 until state.pinLimit){
                    dotsCurrentState.add(false)
                }
            }
        }
    }

}

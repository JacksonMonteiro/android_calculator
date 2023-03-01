package space.jacksonmonteiro.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    // Operands
    private var operand1: Double? = null
    private var pendingOperation: String = "="

    // Live data
    private val result = MutableLiveData<String>()
    val stringResult: LiveData<String>
        get() = Transformations.map(result) { it.toString() }

    private val newNumber = MutableLiveData<String>()
    val stringNewNumber: LiveData<String>
        get() = newNumber

    private val displayOperation = MutableLiveData<String>()
    val stringOperation: LiveData<String>
        get() = displayOperation

    fun digitPressed(caption: String) {
        if (newNumber.value != null) {
            newNumber.value = newNumber.value + caption
        } else {
            newNumber.value = caption
        }
    }

    fun operandPressed(op: String) {
        try {
            val value = newNumber.value?.toString()?.toDouble()
            if (value != null) {
                performOperation(value, op)
            }
        } catch (e: NumberFormatException) {
            newNumber.value = ""
        }

        pendingOperation = op
        displayOperation.value = pendingOperation
    }

    fun negativeButtonPressed() {
        val value = newNumber.value.toString()
        if (value.isEmpty()) {
            newNumber.value = "-"
        } else {
            try {
                var doubleValue = value.toDouble()
                doubleValue *= -1
                newNumber.value = doubleValue.toString()
            } catch (e: NumberFormatException) {
                newNumber.value = ""
            }
        }
    }

    fun cleanButtonPressed() {
        operand1 = null
        pendingOperation = "="
        newNumber.value = ""
        result.value = ""
    }

    private fun performOperation(value: Double, op: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = op
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN // Handle divison by zero
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }

        result.value = operand1.toString()
        newNumber.value = ""
    }
}

package space.jacksonmonteiro.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var result : EditText
    private lateinit var newNumber : EditText
    private val displayOperation by lazy (LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    // Operands
    private var operand1 : Double? = null
    private var pendingOperation : String = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        // Data input buttons
        val button0 : Button = findViewById(R.id.button0)
        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button4 : Button = findViewById(R.id.button4)
        val button5 : Button = findViewById(R.id.button5)
        val button6 : Button = findViewById(R.id.button6)
        val button7 : Button = findViewById(R.id.button7)
        val button8 : Button = findViewById(R.id.button8)
        val button9 : Button = findViewById(R.id.button9)
        val buttonDecimal : Button = findViewById(R.id.buttonDecimal)

        // Operation Numbers
        val buttonPlus : Button = findViewById(R.id.buttonPlus)
        val buttonMinus : Button = findViewById(R.id.buttonMinus)
        val buttonMultiply : Button = findViewById(R.id.buttonMultiply)
        val buttonDivide : Button = findViewById(R.id.buttonDivide)
        val buttonCalc : Button = findViewById(R.id.buttonCalc)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDecimal.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()

            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                newNumber.text.clear()
            }

            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonPlus.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonCalc.setOnClickListener(opListener)
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

        result.setText(operand1.toString())
        newNumber.text.clear()
    }
}
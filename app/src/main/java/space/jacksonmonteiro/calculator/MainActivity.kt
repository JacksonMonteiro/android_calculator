package space.jacksonmonteiro.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import space.jacksonmonteiro.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val viewModel: CalculatorViewModel by viewModels()
        val viewModel: BigDecimalViewModel by viewModels()

        viewModel.stringResult.observe(this) { stringResult -> binding.result.setText(stringResult) }
        viewModel.stringNewNumber.observe(this) { stringNumber -> binding.newNumber.setText(stringNumber) }
        viewModel.stringOperation.observe(this) { stringOperation -> binding.operation.text =  stringOperation }

        // Data input buttonss
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)

        // Operation Numbers
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonCalc: Button = findViewById(R.id.buttonCalc)
        val buttonNegative: Button = findViewById(R.id.buttonNegative)
        val buttonClear: Button = findViewById(R.id.buttonClear)

        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
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
            viewModel.operandPressed((v as Button).text.toString())
        }

        buttonPlus.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonCalc.setOnClickListener(opListener)

        buttonNegative.setOnClickListener {
            viewModel.negativeButtonPressed()
        }

        buttonClear.setOnClickListener {
            viewModel.cleanButtonPressed()
        }
    }
}
package com.example.tipapp_xml

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var etBill: EditText
    private lateinit var spinnerTip: AutoCompleteTextView
    private lateinit var switchRoundup: MaterialSwitch
    private lateinit var tvTipAmount: TextView

    private val tipOptions = arrayOf(15, 18, 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etBill = findViewById(R.id.etBill)
        spinnerTip = findViewById(R.id.spinnerTip)
        switchRoundup = findViewById(R.id.switchRoundUp)
        tvTipAmount = findViewById(R.id.tvTipAmount)

        val formattedOptions = tipOptions.map {
            getString(R.string.percentage_format, it)
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            formattedOptions
        )
        spinnerTip.setAdapter(adapter)
        spinnerTip.setText(getString(R.string.percentage_format, tipOptions[0]), false)

        setupListeners()
        calculateTip()
    }

    private fun setupListeners() {
        etBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        switchRoundup.setOnCheckedChangeListener { _, _ ->
            calculateTip()
        }
        spinnerTip.setOnItemClickListener { _, _, _, _ ->
            calculateTip()
        }
    }

    private fun calculateTip() {
        val bill = etBill.text.toString().toDoubleOrNull() ?: 0.0
        val selectedText = spinnerTip.text.toString()
        val percent = selectedText.filter { it.isDigit() }.toIntOrNull() ?: 15
        var tip = bill * percent / 100

        if (switchRoundup.isChecked) {
            tip = ceil(tip)
        }

        tvTipAmount.text = getString(R.string.formatTip, tip)
    }
}
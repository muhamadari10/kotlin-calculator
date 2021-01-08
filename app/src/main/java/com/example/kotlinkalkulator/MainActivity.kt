package com.example.kotlinkalkulator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.ArithmeticException
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    /* digunakan untuk cek angka pertama sudah ada atau belum */
    var lastNumeric: Boolean = false
    /* di gunakan untuk cek apakah sudah ada titik atau belum */
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false // Update the flag
            lastDot = false    // Reset the DOT flag
        }
    }

    fun onDecimal(view: View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastDot = true
        }
    }

    fun onClear(view: View){
        tvInput.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onSum(view: View){
        if(lastNumeric){
            var value = tvInput.text.toString()
            var prefix = ""

            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1);
                }

                if (value.contains("-")) {

                    // If the inputValue contains the Subtraction operator
                    // Will split the inputValue using Subtraction operator
                    val splitedValue = value.split("-")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }

                    /*Here as the value one and two will be calculated based on the operator and
                    if the result contains the zero after decimal point will remove it.
                    And display the result to TextView*/
                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if (value.contains("+")) {

                    // If the inputValue contains the Subtraction operator
                    // Will split the inputValue using Subtraction operator
                    val splitedValue = value.split("+")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }

                    /*Here as the value one and two will be calculated based on the operator and
                    if the result contains the zero after decimal point will remove it.
                    And display the result to TextView*/
                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if (value.contains("x")) {

                    // If the inputValue contains the Subtraction operator
                    // Will split the inputValue using Subtraction operator
                    val splitedValue = value.split("x")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }

                    /*Here as the value one and two will be calculated based on the operator and
                    if the result contains the zero after decimal point will remove it.
                    And display the result to TextView*/
                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if (value.contains("/")) {

                    // If the inputValue contains the Subtraction operator
                    // Will split the inputValue using Subtraction operator
                    val splitedValue = value.split("/")

                    var one = splitedValue[0] // Value One
                    val two = splitedValue[1] // Value Two

                    if (!prefix.isEmpty()) { // If the prefix is not empty then we will append it with first value i.e one.
                        one = prefix + one
                    }

                    /*Here as the value one and two will be calculated based on the operator and
                    if the result contains the zero after decimal point will remove it.
                    And display the result to TextView*/
                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }


    }

    private fun isOperatorAdded(value: String): Boolean{
        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/")
            || value.contains("*")
            || value.contains("-")
            || value.contains("+"))
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }

}
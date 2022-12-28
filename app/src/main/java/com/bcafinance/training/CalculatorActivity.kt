package com.bcafinance.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*
import org.mariuszgromada.math.mxparser.*
import org.mariuszgromada.math.mxparser.mXparser

class CalculatorActivity : AppCompatActivity() {
    var lastComma:Boolean = false
    var lastDigit:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        btnKeluar.setOnClickListener({
            finish()
        })
    }

    fun onDigitPress(view: View){
        txtInput.append((view as Button).text)
        lastDigit = true
        lastComma = false
    }

    fun onClear(view: View){
        txtInput.setText("")
    }

    fun onOperator(view: View){
        if(lastDigit && !lastComma){
            txtInput.append((view as Button).text)
            lastDigit = false
            lastComma = false
        }
    }

    fun onComma(view: View){
        if(lastDigit && !lastComma){
            txtInput.append(".")
            lastDigit = false
            lastComma = true
        }
    }

    fun onCount(view: View){
        var e = Expression(txtInput.text.toString())
        txtInput.setText(e.calculate().toString())
    }

//    fun onComma(view: View){
//        if(txtInput.text)
//    }
}
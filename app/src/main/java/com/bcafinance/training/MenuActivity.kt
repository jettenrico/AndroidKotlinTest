package com.bcafinance.training

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_menu.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
class MenuActivity : AppCompatActivity() {
    var username = ""
    var password = ""
    var totalSecond = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        animateText()
        val datas:Bundle? = intent.extras
        username = datas?.getString("username", "").toString()
        password = datas?.getString("password", "").toString()

        txtHello.text = "Selamat Datang $username!"
        btnTanggal.setOnClickListener({
            pickDate()
        })

        btnDial.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: 08189189819")
            }
            startActivity(intent)
        }
    }

    fun animateText(){
        val anim = AlphaAnimation(0.0f, 1f)
        anim.duration = 1000
        anim.startOffset = 100
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        txtHello.startAnimation(anim)
    }

    fun pickDate() {
        val c = Calendar.getInstance()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMMM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)

                val different = Period.between(LocalDate.of(year, monthOfYear, dayOfMonth), LocalDate.now())
                txtUmur.setText("Umur Anda adalah ${different.years} tahun ${different.months - 1} bulan")

                totalSecond = (different.years * 365  * 24 * 60 * 60) + (different.months *  30 * 24 * 60 * 60) + (different.days * 24 * 60 * 60)

                txtHitung.text = totalSecond.toString()
                txtTanggalLahir.text = sdf.format(c.getTime())
            }
        }

        DatePickerDialog(
            this, dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()


    }
}


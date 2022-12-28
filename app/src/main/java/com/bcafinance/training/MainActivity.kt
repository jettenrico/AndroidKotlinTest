package com.bcafinance.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    lateinit var username:EditText
//    lateinit var password:EditText
//    lateinit var btnLogin:Button
//    lateinit var container:LinearLayout
    val defaultPassword = "123456"
    val defaultUsername = "testuser"
    var counter = 0

    fun init(){
//        username = findViewById<EditText>(R.id.txtUsername)
//        password = findViewById<EditText>(R.id.txtPassword)
//        btnLogin = findViewById<Button>(R.id.btnLogin)
//        container = findViewById<LinearLayout>(R.id.containerDummy)

        btnLogin.setOnClickListener(View.OnClickListener {
            checkLogin(it)
//            var button = Button(applicationContext)
//            button.text = "hello " + counter++
//             containerDummy.addView(button)

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun checkLogin(v : View){
        if(txtUsername.text.toString().contentEquals(defaultUsername) &&  (txtPassword.text.toString().contentEquals(defaultPassword))){
            Toast.makeText(applicationContext, "Selamat Datang!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,PortfolioActivity::class.java)
            intent.putExtra("username", txtUsername.text.toString())
            intent.putExtra("password", txtPassword.text.toString())
            startActivity(intent)
        }else if (txtUsername.text.toString().contentEquals(defaultUsername)){
            Toast.makeText(applicationContext, "Password Salah", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(applicationContext, "Username Tidak Ditemukan", Toast.LENGTH_LONG).show()
        }
    }

    fun forgot(v : View){
        Toast.makeText(applicationContext, "Forgot Your Password?", Toast.LENGTH_LONG).show()
    }

    override fun onStart(){
        super.onStart()
        Log.d("Lifecycle Activity","Start Action")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle Activity","Resume Action")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle Activity","Pause Action")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle Activity","Stop Action")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle Activity","Destroy Action")
    }
}
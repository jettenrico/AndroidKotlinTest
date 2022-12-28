package com.bcafinance.training

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_portfolio.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PortfolioActivity : AppCompatActivity() {
    companion object{
        private val REQUEST_CODE_PERMISSION = 999
        private val CAMERA_REQUEST_CAPTURE = 666
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)

        imageDial.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: 08189189819")
            }
            startActivity(intent)
        }

        imageMail.setOnClickListener(){
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_EMAIL,"test@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT,"TEST")
            }
            if (intent.resolveActivity(packageManager)!=null){
                startActivity(intent);
            }
        }

        imageMap.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=wisma+bca+pondok+indah"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        btnCamera.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED &&
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions, REQUEST_CODE_PERMISSION)
                }else{
                    captureCamera()
                }
            }
        }

        btnMenu.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        })
        btnCalc.setOnClickListener({
            val intent = Intent(this,CalculatorActivity::class.java)
            startActivity(intent)
        })
    }

    fun saveImage(bitmap: Bitmap){
        val tanggal = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val extStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val namaFile = extStorage + "/BCAF_"+ tanggal + ".png"
        var file :File? = null

        file = File(namaFile)
        file?.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
        val bmData = bos.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(bmData)
        fos.flush()
    }

    fun captureCamera(){
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeCamera, CAMERA_REQUEST_CAPTURE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    captureCamera()
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CAMERA_REQUEST_CAPTURE && resultCode == RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            btnCamera.setImageBitmap(bitmapImage)
            saveImage(bitmapImage)
        }
    }
}
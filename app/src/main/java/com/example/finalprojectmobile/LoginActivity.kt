package com.example.finalprojectmobile

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            val email= inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if(email.isEmpty()|| password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Silakan masukkan Email dan Password",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(email == "admin1@gmail.com" || password == "admin01") {
                val progressDialog =
                    ProgressDialog(this, R.style.ThemeOverlay_MaterialComponents_Light)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Loading...")
                progressDialog.show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}
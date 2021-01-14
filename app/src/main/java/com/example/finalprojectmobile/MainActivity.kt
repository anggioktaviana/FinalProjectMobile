package com.example.finalprojectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView3.setOnClickListener {
            val pindah = Intent(this, DaftarBukuActivity::class.java)
            startActivity(pindah)
        }
        imageView2.setOnClickListener {
            val peminjaman = Intent(this, DaftarPinjamActivity::class.java)
            startActivity(peminjaman)
        }
        imageView4.setOnClickListener {
            val wishlist = Intent(this, DaftarWishlistActivity::class.java)
            startActivity(wishlist)
        }
    }

    //add menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.optionsmenu, menu)
        return true
    }
    //method option menu selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                val i = Intent(this, AboutActivity::class.java)
                startActivity(i)
                return true
            }

            R.id.profile -> {
                val i = Intent(this, ProfileActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}


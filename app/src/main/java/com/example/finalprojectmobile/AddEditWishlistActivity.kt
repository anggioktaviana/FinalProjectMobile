package com.example.finalprojectmobile
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tambah_wishlist.*

class AddEditWishlistActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.finalprojectmobile.EXTRA_ID"
        const val EXTRA_JUDUL = "com.example.finalprojectmobile.EXTRA_JUDUL"
        const val EXTRA_PENULIS = "com.example.finalprojectmobile.EXTRA_PENULIS"
        const val EXTRA_PENERBIT = "com.example.finalprojectmobile.EXTRA_PENERBIT"
        const val EXTRA_HALAMAN = "com.example.finalprojectmobile.EXTRA_HALAMAN"
        const val EXTRA_HARGA = "com.example.finalprojectmobile.EXTRA_HARGA"
        const val EXTRA_PRIORITAS = "com.example.finalprojectmobile.EXTRA_PRIORITAS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_wishlist)
        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 5
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit"
            edit_text_title.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_penulis.setText(intent.getStringExtra(EXTRA_PENULIS))
            edit_text_penerbit.setText(intent.getStringExtra(EXTRA_PENERBIT))
            edit_text_jmlhalaman.setText(intent.getStringExtra(EXTRA_HALAMAN))
            edit_text_harga.setText(intent.getStringExtra(EXTRA_HARGA))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITAS, 1)
        } else {
            title = "Tambah Wishlist"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_wishlist_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_wishlist -> {
                saveWishlist()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun saveWishlist() {
        if (edit_text_title.text.toString().trim().isBlank() || edit_text_penulis.text.toString().trim().isBlank() || edit_text_penerbit.text.toString().trim().isBlank() || edit_text_jmlhalaman.text.toString().trim().isBlank() || edit_text_harga.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Keterangan Buku Wishlist tidak lengkap!", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra(EXTRA_JUDUL, edit_text_title.text.toString())
            putExtra(EXTRA_PENULIS, edit_text_penulis.text.toString())
            putExtra(EXTRA_PENERBIT, edit_text_penerbit.text.toString())
            putExtra(EXTRA_HALAMAN, edit_text_jmlhalaman.text.toString())
            putExtra(EXTRA_HARGA, edit_text_harga.text.toString())
            putExtra(EXTRA_PRIORITAS, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(
                    EXTRA_ID, intent.getIntExtra(
                        EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
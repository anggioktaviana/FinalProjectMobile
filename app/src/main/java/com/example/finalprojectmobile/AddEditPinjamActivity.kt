package com.example.finalprojectmobile
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tambah_peminjaman.*

class AddEditPinjamActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.example.finalprojectmobile.EXTRA_ID"
        const val EXTRA_KODE = "com.example.finalprojectmobile.EXTRA_KODE"
        const val EXTRA_JUDUL = "com.example.finalprojectmobile.EXTRA_JUDUL"
        const val EXTRA_PEMINJAM = "com.example.finalprojectmobile.EXTRA_PEMINJAM"
        const val EXTRA_KONTAK = "com.example.finalprojectmobile.EXTRA_KONTAK"
        const val EXTRA_TGLPINJAM = "com.example.finalprojectmobile.EXTRA_TGLPINJAM"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_peminjaman)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit"
            edit_text_kode.setText(intent.getStringExtra(EXTRA_KODE))
            edit_text_title.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_peminjam.setText(intent.getStringExtra(EXTRA_PEMINJAM))
            edit_text_kontak_peminjam.setText(intent.getStringExtra(EXTRA_KONTAK))
            editTextDatePinjam.setText(intent.getStringExtra(EXTRA_TGLPINJAM))
        } else {
            title = "Tambah Peminjaman"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_pinjam_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_pinjam -> {
                savePinjam()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun savePinjam() {
        if (edit_text_kode.text.toString().trim().isBlank() || edit_text_title.text.toString().trim().isBlank() || edit_text_peminjam.text.toString().trim().isBlank() || edit_text_kontak_peminjam.text.toString().trim().isBlank() || editTextDatePinjam.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Keterangan Buku tidak lengkap!", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra(EXTRA_KODE, edit_text_kode.text.toString())
            putExtra(EXTRA_JUDUL, edit_text_title.text.toString())
            putExtra(EXTRA_PEMINJAM, edit_text_peminjam.text.toString())
            putExtra(EXTRA_KONTAK, edit_text_kontak_peminjam.text.toString())
            putExtra(EXTRA_TGLPINJAM, editTextDatePinjam.text.toString())
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
package com.example.finalprojectmobile

import com.example.finalprojectmobile.daftar_buku.Buku
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_daftar_buku.*

class DaftarBukuActivity : AppCompatActivity() {
    companion object {
        const val ADD_BUKU_REQUEST = 1
        const val EDIT_BUKU_REQUEST = 2
    }
    private lateinit var bukuViewModel: BukuViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_buku)
        buttonAddBuku.setOnClickListener {
            startActivityForResult(
                Intent(this, AddEditBukuActivity::class.java),
                ADD_BUKU_REQUEST
            )
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter = BukuAdapter()
        recycler_view.adapter = adapter

        bukuViewModel = ViewModelProviders.of(this).get(BukuViewModel::class.java)
        bukuViewModel.getAllBuku().observe(this, Observer<List<Buku>> {
            adapter.submitList(it)
        })
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                bukuViewModel.delete(adapter.getBukuAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Buku dihapus!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)
        adapter.setOnItemClickListener(object : BukuAdapter.OnItemClickListener {
            override fun onItemClick(buku: Buku) {
                val intent = Intent(baseContext, AddEditBukuActivity::class.java)
                intent.putExtra(AddEditBukuActivity.EXTRA_ID, buku.id)
                intent.putExtra(AddEditBukuActivity.EXTRA_KODE, buku.kode)
                intent.putExtra(AddEditBukuActivity.EXTRA_JUDUL, buku.judul)
                intent.putExtra(AddEditBukuActivity.EXTRA_PENULIS, buku.penulis)
                intent.putExtra(AddEditBukuActivity.EXTRA_PENERBIT, buku.penerbit)
                intent.putExtra(AddEditBukuActivity.EXTRA_HALAMAN, buku.jml_halaman)
                intent.putExtra(AddEditBukuActivity.EXTRA_TAHUN, buku.tahun)
                intent.putExtra(AddEditBukuActivity.EXTRA_RAK, buku.rak)
                startActivityForResult(intent, EDIT_BUKU_REQUEST)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_buku -> {
                bukuViewModel.deleteAllBuku()
                Toast.makeText(this, "Semua sudah dihapus!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_BUKU_REQUEST && resultCode == Activity.RESULT_OK) {
            val newBuku = Buku(
                data!!.getStringExtra(AddEditBukuActivity.EXTRA_KODE),
                data.getStringExtra(AddEditBukuActivity.EXTRA_JUDUL),
                data.getStringExtra(AddEditBukuActivity.EXTRA_PENULIS),
                data.getStringExtra(AddEditBukuActivity.EXTRA_PENERBIT),
                data.getStringExtra(AddEditBukuActivity.EXTRA_HALAMAN),
                data.getStringExtra(AddEditBukuActivity.EXTRA_TAHUN),
                data.getStringExtra(AddEditBukuActivity.EXTRA_RAK))

            bukuViewModel.insert(newBuku)
            Toast.makeText(this, "Data buku disimpan!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_BUKU_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddEditBukuActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan gagal!", Toast.LENGTH_SHORT).show()
            }
            val updateBuku = Buku(
                data!!.getStringExtra(AddEditBukuActivity.EXTRA_KODE),
                data.getStringExtra(AddEditBukuActivity.EXTRA_JUDUL),
                data.getStringExtra(AddEditBukuActivity.EXTRA_PENULIS),
                data.getStringExtra(AddEditBukuActivity.EXTRA_PENERBIT),
                data.getStringExtra(AddEditBukuActivity.EXTRA_HALAMAN),
                data.getStringExtra(AddEditBukuActivity.EXTRA_TAHUN),
                data.getStringExtra(AddEditBukuActivity.EXTRA_RAK))

            updateBuku.id = data.getIntExtra(AddEditBukuActivity.EXTRA_ID, -1)
            bukuViewModel.update(updateBuku)
            Toast.makeText(this, "Data buku berhasil diupdate!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Data buku tidak disimpan!", Toast.LENGTH_SHORT).show()
        }
    }
}

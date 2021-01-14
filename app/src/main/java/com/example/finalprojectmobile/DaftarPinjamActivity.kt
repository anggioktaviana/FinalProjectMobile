package com.example.finalprojectmobile

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
import com.example.finalprojectmobile.daftar_pinjam.Pinjam
import kotlinx.android.synthetic.main.activity_daftar_pinjam.*

class DaftarPinjamActivity : AppCompatActivity() {
    companion object {
        const val ADD_PINJAM_REQUEST = 1
        const val EDIT_PINJAM_REQUEST = 2
    }
    private lateinit var pinjamViewModel: PinjamViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_pinjam)
        buttonAddPinjam.setOnClickListener {
            startActivityForResult(
                Intent(this, AddEditPinjamActivity::class.java),
                ADD_PINJAM_REQUEST
            )
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter = PinjamAdapter()
        recycler_view.adapter = adapter

        pinjamViewModel = ViewModelProviders.of(this).get(PinjamViewModel::class.java)
        pinjamViewModel.getAllPinjam().observe(this, Observer <List<Pinjam>>{
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
                pinjamViewModel.delete(adapter.getPinjamAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Buku dihapus!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)
        adapter.setOnItemClickListener(object : PinjamAdapter.OnItemClickListener {
            override fun onItemClick(pinjam: Pinjam) {
                val intent = Intent(baseContext, AddEditPinjamActivity::class.java)
                intent.putExtra(AddEditPinjamActivity.EXTRA_ID, pinjam.id)
                intent.putExtra(AddEditPinjamActivity.EXTRA_KODE, pinjam.kode)
                intent.putExtra(AddEditPinjamActivity.EXTRA_JUDUL, pinjam.judul)
                intent.putExtra(AddEditPinjamActivity.EXTRA_PEMINJAM, pinjam.peminjam)
                intent.putExtra(AddEditPinjamActivity.EXTRA_KONTAK, pinjam.kontak_peminjam)
                intent.putExtra(AddEditPinjamActivity.EXTRA_TGLPINJAM, pinjam.tgl_pinjam)
                startActivityForResult(intent, EDIT_PINJAM_REQUEST)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_pinjam, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_pinjam -> {
                pinjamViewModel.deleteAllPinjam()
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
        if (requestCode == ADD_PINJAM_REQUEST && resultCode == Activity.RESULT_OK) {
            val newPinjam = Pinjam(
                data!!.getStringExtra(AddEditPinjamActivity.EXTRA_KODE),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_JUDUL),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_PEMINJAM),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_KONTAK),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_TGLPINJAM))

            pinjamViewModel.insert(newPinjam)
            Toast.makeText(this, "Data buku disimpan!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_PINJAM_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddEditPinjamActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan gagal!", Toast.LENGTH_SHORT).show()
            }
            val updatePinjam = Pinjam(
                data!!.getStringExtra(AddEditPinjamActivity.EXTRA_KODE),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_JUDUL),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_PEMINJAM),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_KONTAK),
                data.getStringExtra(AddEditPinjamActivity.EXTRA_TGLPINJAM))

            updatePinjam.id = data.getIntExtra(AddEditPinjamActivity.EXTRA_ID, -1)
            pinjamViewModel.update(updatePinjam)
            Toast.makeText(this, "Data buku berhasil diupdate!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Data buku tidak disimpan!", Toast.LENGTH_SHORT).show()
        }
    }
}
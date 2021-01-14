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
import com.example.finalprojectmobile.daftar_wishlist.Wishlist
import kotlinx.android.synthetic.main.activity_daftar_wishlist.*

class DaftarWishlistActivity : AppCompatActivity() {
    companion object {
        const val ADD_WISHLIST_REQUEST = 1
        const val EDIT_WISHLIST_REQUEST = 2
    }
    private lateinit var wishlistViewModel: WishlistViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_wishlist)
        buttonAddWishlist.setOnClickListener {
            startActivityForResult(
                Intent(this, AddEditWishlistActivity::class.java),
                ADD_WISHLIST_REQUEST
            )
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val adapter = WishlistAdapter()
        recycler_view.adapter = adapter

        wishlistViewModel = ViewModelProviders.of(this).get(WishlistViewModel::class.java)
        wishlistViewModel.getAllWishlist().observe(this, Observer<List<Wishlist>> {
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
                wishlistViewModel.delete(adapter.getWishlistAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Buku dihapus!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_view)
        adapter.setOnItemClickListener(object : WishlistAdapter.OnItemClickListener {
            override fun onItemClick(wishlist: Wishlist) {
                val intent = Intent(baseContext, AddEditWishlistActivity::class.java)
                intent.putExtra(AddEditWishlistActivity.EXTRA_ID, wishlist.id)
                intent.putExtra(AddEditWishlistActivity.EXTRA_JUDUL, wishlist.judul)
                intent.putExtra(AddEditWishlistActivity.EXTRA_PENULIS, wishlist.penulis)
                intent.putExtra(AddEditWishlistActivity.EXTRA_PENERBIT, wishlist.penerbit)
                intent.putExtra(AddEditWishlistActivity.EXTRA_HALAMAN, wishlist.jml_halaman)
                intent.putExtra(AddEditWishlistActivity.EXTRA_HARGA, wishlist.harga)
                intent.putExtra(AddEditWishlistActivity.EXTRA_PRIORITAS, wishlist.prioritas)
                startActivityForResult(intent, EDIT_WISHLIST_REQUEST)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_wishlist, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_wishlist -> {
                wishlistViewModel.deleteAllWishlist()
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
        if (requestCode == ADD_WISHLIST_REQUEST && resultCode == Activity.RESULT_OK) {
            val newWishlist = Wishlist(
                data!!.getStringExtra(AddEditWishlistActivity.EXTRA_JUDUL),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_PENULIS),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_PENERBIT),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_HALAMAN),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_HARGA),
                data.getIntExtra(AddEditWishlistActivity.EXTRA_PRIORITAS, 1))

                wishlistViewModel.insert(newWishlist)
            Toast.makeText(this, "Data buku disimpan!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_WISHLIST_REQUEST && resultCode == Activity.RESULT_OK) {
            val id = data?.getIntExtra(AddEditWishlistActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Pembaharuan gagal!", Toast.LENGTH_SHORT).show()
            }
            val updateWishlist = Wishlist(
                data!!.getStringExtra(AddEditWishlistActivity.EXTRA_JUDUL),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_PENULIS),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_PENERBIT),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_HALAMAN),
                data.getStringExtra(AddEditWishlistActivity.EXTRA_HARGA),
                data.getIntExtra(AddEditWishlistActivity.EXTRA_PRIORITAS, 1))


            updateWishlist.id = data.getIntExtra(AddEditWishlistActivity.EXTRA_ID, -1)
            wishlistViewModel.update(updateWishlist)
            Toast.makeText(this, "Data buku berhasil diupdate!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Data buku tidak disimpan!", Toast.LENGTH_SHORT).show()
        }
    }
}

package com.example.finalprojectmobile.daftar_wishlist

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalprojectmobile.daftar_buku.Buku

@Dao
interface WishlistDao {
    @Insert //memasukkan note ke db
    fun insert(wishlist: Wishlist)
    @Update //update note
    fun update(wishlist: Wishlist)
    @Delete //menghapus note
    fun delete(wishlist: Wishlist)
    @Query("DELETE FROM wishlist_table")
    fun deleteAllWishlist()
    @Query("SELECT * FROM wishlist_table ORDER BY id DESC")
    fun getAllWishlist(): LiveData<List<Wishlist>>
}
package com.example.finalprojectmobile.daftar_buku

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BukuDao {
    @Insert //memasukkan note ke db
    fun insert(buku: Buku)
    @Update //update note
    fun update(buku: Buku)
    @Delete //menghapus note
    fun delete(buku: Buku)
    @Query("DELETE FROM book_table")
    fun deleteAllBuku()
    @Query("SELECT * FROM book_table ORDER BY id DESC")
    fun getAllBuku(): LiveData<List<Buku>>
}
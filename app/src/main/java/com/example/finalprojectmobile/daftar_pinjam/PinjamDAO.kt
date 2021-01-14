package com.example.finalprojectmobile.daftar_pinjam

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalprojectmobile.daftar_buku.Buku

@Dao
interface PinjamDao {
    @Insert //memasukkan note ke db
    fun insert(pinjam: Pinjam)
    @Update //update note
    fun update(pinjam: Pinjam)
    @Delete //menghapus note
    fun delete(pinjam: Pinjam)
    @Query("DELETE FROM borrow_table")
    fun deleteAllPinjam()
    @Query("SELECT * FROM borrow_table ORDER BY id DESC")
    fun getAllPinjam(): LiveData<List<Pinjam>>
}
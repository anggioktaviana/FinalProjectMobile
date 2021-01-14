package com.example.finalprojectmobile.daftar_pinjam

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "borrow_table")
data class Pinjam (
    var kode: String,
    var judul: String,
    var peminjam: String,
    var kontak_peminjam: String,
    var tgl_pinjam: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //primary key
}
package com.example.finalprojectmobile.daftar_buku

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Buku (
    var kode: String,
    var judul: String,
    var penulis: String,
    var penerbit: String,
    var jml_halaman: String,
    var tahun: String,
    var rak: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //primary key
}
package com.example.finalprojectmobile.daftar_wishlist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_table")
data class Wishlist (
    var judul: String,
    var penulis: String,
    var penerbit: String,
    var jml_halaman: String,
    var harga: String,
    var prioritas: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //primary key
}
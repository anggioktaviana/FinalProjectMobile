package com.example.finalprojectmobile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.finalprojectmobile.daftar_buku.Buku
import com.example.finalprojectmobile.daftar_buku.BukuRepository

class BukuViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: BukuRepository =
        BukuRepository(application)
    private var allBuku: LiveData<List<Buku>> = repository.getAllBuku()
    fun insert(buku: Buku) {
        repository.insert(buku)
    }
    fun update(buku: Buku) {
        repository.update(buku)
    }
    fun delete(buku: Buku) {
        repository.delete(buku)
    }
    fun deleteAllBuku() {
        repository.deleteAllBuku()
    }
    fun getAllBuku(): LiveData<List<Buku>> {
        return allBuku
    }
}
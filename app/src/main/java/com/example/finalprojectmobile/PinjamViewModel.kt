package com.example.finalprojectmobile
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.finalprojectmobile.daftar_pinjam.Pinjam
import com.example.finalprojectmobile.daftar_pinjam.PinjamRepository

class PinjamViewModel(application: Application) : AndroidViewModel(application)
{
    private var repository: PinjamRepository =
        PinjamRepository(application)
    private var allPinjam: LiveData<List<Pinjam>> = repository.getAllPinjam()
    fun insert(pinjam: Pinjam) {
        repository.insert(pinjam)
    }
    fun update(pinjam: Pinjam) {
        repository.update(pinjam)
    }
    fun delete(pinjam: Pinjam) {
        repository.delete(pinjam)
    }
    fun deleteAllPinjam() {
        repository.deleteAllPinjam()
    }
    fun getAllPinjam(): LiveData<List<Pinjam>> {
        return allPinjam
    }
}
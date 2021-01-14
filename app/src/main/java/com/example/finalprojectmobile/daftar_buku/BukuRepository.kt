package com.example.finalprojectmobile.daftar_buku
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class BukuRepository(application: Application){
    private var bukuDao: BukuDao
    private var allBuku: LiveData<List<Buku>>
    init{
        val database: BukuDatabase = BukuDatabase.getInstance(
            application.applicationContext
        )!!
        bukuDao = database.bukuDao()
        allBuku = bukuDao.getAllBuku()
    }
    fun insert(buku: Buku){
        val insertBukuAsyncTak = InsertBukuAsyncTask(bukuDao).execute(buku)
    }
    fun update(buku:Buku){
        val updateBukuAsyncTask = UpdateBukuAsyncTask(bukuDao).execute(buku)
    }
    fun delete(buku: Buku){
        val deleteBukuAsyncTask = DeleteBukuAsyncTask(bukuDao).execute(buku)
    }
    fun deleteAllBuku(){
        val deleteAllBukuAsyncTask = DeleteAllBukuAsyncTask(bukuDao).execute()
    }
    fun getAllBuku(): LiveData<List<Buku>>{
        return allBuku
    }
    companion object{
        private class InsertBukuAsyncTask(bukuDao: BukuDao) : AsyncTask<Buku, Unit, Unit>(){
            val bukuDao = bukuDao
            override fun doInBackground(vararg p0: Buku?) {
                bukuDao.insert(p0[0]!!)
            }
        }
        private class UpdateBukuAsyncTask(bukuDao: BukuDao) : AsyncTask<Buku, Unit, Unit>() {
            val bukuDao = bukuDao
            override fun doInBackground(vararg p0: Buku?) {
                bukuDao.update(p0[0]!!)
            }
        }
        private class DeleteBukuAsyncTask(bukuDao: BukuDao) : AsyncTask<Buku, Unit, Unit>() {
            val bukuDao = bukuDao
            override fun doInBackground(vararg p0: Buku?) {
                bukuDao.delete(p0[0]!!)
            }
        }
        private class DeleteAllBukuAsyncTask(bukuDao: BukuDao) : AsyncTask<Unit, Unit, Unit>() {
            val bukuDao = bukuDao
            override fun doInBackground(vararg p0: Unit?) {
                bukuDao.deleteAllBuku()
            }
        }
    }
}
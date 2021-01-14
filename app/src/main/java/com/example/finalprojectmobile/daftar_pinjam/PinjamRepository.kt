package com.example.finalprojectmobile.daftar_pinjam
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class PinjamRepository(application: Application){
    private var pinjamDao: PinjamDao
    private var allPinjam: LiveData<List<Pinjam>>
    init{
        val database: PinjamDatabase = PinjamDatabase.getInstance(
            application.applicationContext
        )!!
        pinjamDao = database.pinjamDao()
        allPinjam = pinjamDao.getAllPinjam()
    }
    fun insert(pinjam: Pinjam){
        val insertPinjamAsyncTak = InsertPinjamAsyncTask(pinjamDao).execute(pinjam)
    }
    fun update(pinjam: Pinjam){
        val updatePinjamAsyncTask = UpdatePinjamAsyncTask(pinjamDao).execute(pinjam)
    }
    fun delete(pinjam: Pinjam){
        val deletePinjamAsyncTask = DeletePinjamAsyncTask(pinjamDao).execute(pinjam)
    }
    fun deleteAllPinjam(){
        val deleteAllPinjamAsyncTask = DeleteAllPinjamAsyncTask(pinjamDao).execute()
    }
    fun getAllPinjam(): LiveData<List<Pinjam>>{
        return allPinjam
    }
    companion object{
        private class InsertPinjamAsyncTask(pinjamDao: PinjamDao) : AsyncTask<Pinjam, Unit, Unit>(){
            val pinjamDao = pinjamDao
            override fun doInBackground(vararg p0: Pinjam?) {
                pinjamDao.insert(p0[0]!!)
            }
        }
        private class UpdatePinjamAsyncTask(pinjamDao: PinjamDao) : AsyncTask<Pinjam, Unit, Unit>() {
            val pinjamDao = pinjamDao
            override fun doInBackground(vararg p0: Pinjam?) {
                pinjamDao.update(p0[0]!!)
            }
        }
        private class DeletePinjamAsyncTask(pinjamDao: PinjamDao) : AsyncTask<Pinjam, Unit, Unit>() {
            val pinjamDao = pinjamDao
            override fun doInBackground(vararg p0: Pinjam?) {
                pinjamDao.delete(p0[0]!!)
            }
        }
        private class DeleteAllPinjamAsyncTask(pinjamDao: PinjamDao) : AsyncTask<Unit, Unit, Unit>() {
            val pinjamDao = pinjamDao
            override fun doInBackground(vararg p0: Unit?) {
                pinjamDao.deleteAllPinjam()
            }
        }
    }
}
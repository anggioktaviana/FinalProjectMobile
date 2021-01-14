package com.example.finalprojectmobile.daftar_buku

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

//menganotasi kelas menjadi database ruangan dengan tabel (entitas) kelas Note
@Database(entities = [Buku::class], version = 1)
abstract class BukuDatabase : RoomDatabase() {
    abstract fun bukuDao() : BukuDao
    companion object {
        private var instance: BukuDatabase? = null
        fun getInstance(context: Context): BukuDatabase? {
            if (instance == null) {
                synchronized(BukuDatabase::class) {
                    instance = Room.databaseBuilder( //membuat database
                        context.applicationContext,
                        BukuDatabase::class.java, "buku_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
        fun destroyInstance() {
            instance = null
        }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }
    class PopulateDbAsyncTask(db: BukuDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val bukuDao = db?.bukuDao()
        override fun doInBackground(vararg p0: Unit?) {
            bukuDao?.insert(Buku("A01", "101+ Pengetahuan Bikin Kamu Mahir IT", "Feri Sulianta", "Elex Media Komputindo", "250", "2018", "1"))
        }
    }
}
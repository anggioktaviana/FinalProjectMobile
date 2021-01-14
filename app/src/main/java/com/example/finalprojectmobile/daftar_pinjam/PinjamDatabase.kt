package com.example.finalprojectmobile.daftar_pinjam
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.sql.Types.DATE

//menganotasi kelas menjadi database ruangan dengan tabel (entitas) kelas Note
@Database(entities = [Pinjam::class], version = 1)
abstract class PinjamDatabase : RoomDatabase() {
    abstract fun pinjamDao() : PinjamDao
    companion object {
        private var instance: PinjamDatabase? = null
        fun getInstance(context: Context): PinjamDatabase? {
            if (instance == null) {
                synchronized(PinjamDatabase::class) {
                    instance = Room.databaseBuilder( //membuat database
                        context.applicationContext,
                        PinjamDatabase::class.java, "pinjam_database"
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
    class PopulateDbAsyncTask(db: PinjamDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val pinjamDao = db?.pinjamDao()
        override fun doInBackground(vararg p0: Unit?) {
            pinjamDao?.insert(Pinjam("A01", "101+ Pengetahuan Bikin Kamu Mahir IT", "Fahira", "089612142267", "2020-01-01"))
        }
    }
}
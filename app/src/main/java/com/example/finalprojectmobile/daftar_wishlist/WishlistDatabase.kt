package com.example.finalprojectmobile.daftar_wishlist
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

//menganotasi kelas menjadi database ruangan dengan tabel (entitas) kelas Note
@Database(entities = [Wishlist::class], version = 1)
abstract class WishlistDatabase : RoomDatabase() {
    abstract fun wishlistDao() : WishlistDao
    companion object {
        private var instance: WishlistDatabase? = null
        fun getInstance(context: Context): WishlistDatabase? {
            if (instance == null) {
                synchronized(WishlistDatabase::class) {
                    instance = Room.databaseBuilder( //membuat database
                        context.applicationContext,
                        WishlistDatabase::class.java, "wishlist_database"
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
    class PopulateDbAsyncTask(db: WishlistDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val wishlistDao = db?.wishlistDao()
        override fun doInBackground(vararg p0: Unit?) {
            wishlistDao?.insert(Wishlist("Tentang Kamu", "Tere Liye", "Republika", "400", "78.000", 1))
        }
    }
}
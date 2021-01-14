package com.example.finalprojectmobile.daftar_wishlist
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class WishlistRepository(application: Application){
    private var wishlistDao: WishlistDao
    private var allWishlist: LiveData<List<Wishlist>>

    init{
        val database: WishlistDatabase = WishlistDatabase.getInstance(
            application.applicationContext
        )!!
        wishlistDao = database.wishlistDao()
        allWishlist = wishlistDao.getAllWishlist()
    }
    fun insert(wishlist: Wishlist){
        val insertWishlistAsyncTak = InsertWishlistAsyncTask(wishlistDao).execute(wishlist)
    }
    fun update(wishlist: Wishlist){
        val updateWishlistAsyncTask = UpdateWishlistAsyncTask(wishlistDao).execute(wishlist)
    }
    fun delete(wishlist: Wishlist){
        val deleteWishlistAsyncTask = DeleteWishlistAsyncTask(wishlistDao).execute(wishlist)
    }
    fun deleteAllWishlist(){
        val deleteAllWishlistAsyncTask = DeleteAllWishlistAsyncTask(wishlistDao).execute()
    }
    fun getAllWishlist(): LiveData<List<Wishlist>>{
        return allWishlist
    }
    companion object{
        private class InsertWishlistAsyncTask(wishlistDao: WishlistDao) : AsyncTask<Wishlist, Unit, Unit>(){
            val wishlistDao = wishlistDao
            override fun doInBackground(vararg p0: Wishlist?) {
                wishlistDao.insert(p0[0]!!)
            }
        }
        private class UpdateWishlistAsyncTask(wishlistDao: WishlistDao) : AsyncTask<Wishlist, Unit, Unit>() {
            val wishlistDao = wishlistDao
            override fun doInBackground(vararg p0: Wishlist?) {
                wishlistDao.update(p0[0]!!)
            }
        }
        private class DeleteWishlistAsyncTask(wishlistDao: WishlistDao) : AsyncTask<Wishlist, Unit, Unit>() {
            val wishlistDao = wishlistDao
            override fun doInBackground(vararg p0: Wishlist?) {
                wishlistDao.delete(p0[0]!!)
            }
        }
        private class DeleteAllWishlistAsyncTask(wishlistDao: WishlistDao) : AsyncTask<Unit, Unit, Unit>() {
            val wishlistDao = wishlistDao
            override fun doInBackground(vararg p0: Unit?) {
                wishlistDao.deleteAllWishlist()
            }
        }
    }
}
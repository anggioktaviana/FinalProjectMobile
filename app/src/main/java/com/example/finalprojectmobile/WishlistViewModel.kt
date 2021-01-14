package com.example.finalprojectmobile
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.finalprojectmobile.daftar_wishlist.Wishlist
import com.example.finalprojectmobile.daftar_wishlist.WishlistRepository

class WishlistViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: WishlistRepository =
        WishlistRepository(application)
    private var allWishlist: LiveData<List<Wishlist>> = repository.getAllWishlist()
    fun insert(wishlist: Wishlist) {
        repository.insert(wishlist)
    }
    fun update(wishlist: Wishlist) {
        repository.update(wishlist)
    }
    fun delete(wishlist: Wishlist) {
        repository.delete(wishlist)
    }
    fun deleteAllWishlist() {
        repository.deleteAllWishlist()
    }
    fun getAllWishlist(): LiveData<List<Wishlist>> {
        return allWishlist
    }
}
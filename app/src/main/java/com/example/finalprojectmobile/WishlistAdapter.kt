package com.example.finalprojectmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectmobile.daftar_wishlist.Wishlist
import kotlinx.android.synthetic.main.daftar_wishlist_item.view.*

class WishlistAdapter : ListAdapter<Wishlist, WishlistAdapter.WishlistHolder>(
    DIFF_CALLBACK
){
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Wishlist>() {
            override fun areItemsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean {
                return oldItem.judul == newItem.judul && oldItem.penulis == newItem.penulis
                        && oldItem.harga == newItem.harga
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_wishlist_item, parent, false)
        return WishlistHolder(itemView)
    }
    override fun onBindViewHolder(holder: WishlistHolder, position: Int) {
        val currentWishlist: Wishlist = getItem(position)
        holder.textViewTitle.text = currentWishlist.judul
        holder.textViewPenulis.text = currentWishlist.penulis
        holder.textViewHarga.text = currentWishlist.harga
    }
    fun getWishlistAt(position: Int): Wishlist {
        return getItem(position)
    }
    inner class WishlistHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewTitle: TextView = itemView.text_view_title
        var textViewPenulis: TextView = itemView.text_view_penulis
        var textViewHarga: TextView = itemView.text_view_harga
    }
    interface OnItemClickListener {
        fun onItemClick(wishlist: Wishlist)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
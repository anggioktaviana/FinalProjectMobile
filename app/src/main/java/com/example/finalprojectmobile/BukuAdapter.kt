package com.example.finalprojectmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectmobile.daftar_buku.Buku
import kotlinx.android.synthetic.main.daftar_buku_item.view.*

class BukuAdapter : ListAdapter<Buku, BukuAdapter.BukuHolder>(
    DIFF_CALLBACK
){
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Buku>() {
            override fun areItemsTheSame(oldItem: Buku, newItem: Buku): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Buku, newItem: Buku): Boolean {
                return oldItem.judul == newItem.judul && oldItem.penulis == newItem.penulis
                        && oldItem.penerbit == newItem.penerbit
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_buku_item, parent, false)
        return BukuHolder(itemView)
    }
    override fun onBindViewHolder(holder: BukuHolder, position: Int) {
        val currentBuku: Buku = getItem(position)
        holder.textViewTitle.text = currentBuku.judul
        holder.textViewPenulis.text = currentBuku.penulis
        holder.textViewPenerbit.text = currentBuku.penerbit
    }
    fun getBukuAt(position: Int): Buku {
        return getItem(position)
    }
    inner class BukuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        var textViewPenerbit: TextView = itemView.text_view_penerbit
    }
    interface OnItemClickListener {
        fun onItemClick(buku: Buku)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
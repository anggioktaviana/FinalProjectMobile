package com.example.finalprojectmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectmobile.daftar_pinjam.Pinjam
import kotlinx.android.synthetic.main.daftar_pinjam_item.view.*

class PinjamAdapter : ListAdapter<Pinjam, PinjamAdapter.PinjamHolder>(
    DIFF_CALLBACK
){
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pinjam>() {
            override fun areItemsTheSame(oldItem: Pinjam, newItem: Pinjam): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Pinjam, newItem: Pinjam): Boolean {
                return oldItem.judul == newItem.judul && oldItem.peminjam == newItem.peminjam
                        && oldItem.tgl_pinjam == newItem.tgl_pinjam
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinjamHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.daftar_pinjam_item, parent, false)
        return PinjamHolder(itemView)
    }
    override fun onBindViewHolder(holder: PinjamHolder, position: Int) {
        val currentPinjam: Pinjam = getItem(position)
        holder.textViewTitle.text = currentPinjam.judul
        holder.textViewPeminjam.text = currentPinjam.peminjam
        holder.textViewTglPinjam.text = currentPinjam.tgl_pinjam.toString()
    }
    fun getPinjamAt(position: Int): Pinjam {
        return getItem(position)
    }
    inner class PinjamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewTitle: TextView = itemView.text_view_title
        var textViewPeminjam: TextView = itemView.text_view_peminjam
        var textViewTglPinjam: TextView = itemView.text_view_tglpinjam
    }
    interface OnItemClickListener {
        fun onItemClick(pinjam: Pinjam)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
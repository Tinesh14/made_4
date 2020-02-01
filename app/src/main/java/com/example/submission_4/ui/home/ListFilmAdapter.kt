package com.example.submission_4.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission_4.R
import kotlinx.android.synthetic.main.daftaritem.view.*
import java.util.*

class ListFilmAdapter : RecyclerView.Adapter<ListFilmAdapter.ListViewHolder>() {
    private val mData = ArrayList<Film>()
    fun setData(items: ArrayList<Film>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.daftaritem, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(mContext, Detail::class.java)
            moveDetail.putExtra(Detail.EXTRA_MOVIE, mData[position])
            mContext.startActivity(moveDetail)
        }

    }

    override fun getItemCount(): Int {
        return mData.size
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) {
            with(itemView) {
                val image = "https://image.tmdb.org/t/p/w780/${film.poster}"
                Glide.with(itemView.context)
                    .load(image)
                    .dontAnimate()
                    .into(img_item_poster)
                item_judul.text = film.title
                item_tglrilis.text = film.release
                item_deskripsi.text = film.synopsis
            }
        }

    }

}
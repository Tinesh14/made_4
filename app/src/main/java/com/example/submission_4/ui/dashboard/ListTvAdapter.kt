package com.example.submission_4.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission_4.R
import kotlinx.android.synthetic.main.daftaritem.view.*


class ListTvAdapter : RecyclerView.Adapter<ListTvAdapter.ListViewHolder>() {
    private val mData = ArrayList<Tv>()
    fun setData(items: ArrayList<Tv>) {
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
            val movieDetail = Intent(mContext, DetailTv::class.java)
            movieDetail.putExtra(DetailTv.EXTRA_TV, mData[position])
            mContext.startActivity(movieDetail)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: Tv) {
            with(itemView) {
                val image = "https://image.tmdb.org/t/p/w780/${tv.poster}"
                Glide.with(itemView.context)
                    .load(image)
                    .dontAnimate()
                    .into(img_item_poster)
                item_judul.text = tv.title
                item_tglrilis.text = tv.release
                item_deskripsi.text = tv.synopsis
            }
        }
    }
}
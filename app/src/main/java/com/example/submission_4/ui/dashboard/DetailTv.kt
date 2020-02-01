package com.example.submission_4.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.submission_4.R

@SuppressLint("Registered")
class DetailTv : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionbar = supportActionBar

        val imgPoster: ImageView = findViewById(R.id.Poster_film)
        val tvJud: TextView = findViewById(R.id.item_title_film)
        val tvDateRelease: TextView = findViewById(R.id.Release_film1)
        val tvLanguage: TextView = findViewById(R.id.Language_1)
        val tvBudget: TextView = findViewById(R.id.budge)
        val tvRevenue: TextView = findViewById(R.id.revenue)
        val tvSynopsis: TextView = findViewById(R.id.Synopsis_1)

        val movie = intent.getParcelableExtra(EXTRA_TV) as Tv

        actionbar?.title = movie.title
        actionbar?.setDisplayHomeAsUpEnabled(true)
        val image = "https://image.tmdb.org/t/p/w780/${movie.poster}"
        Glide.with(this)
            .load(image)
            .dontAnimate()
            .into(imgPoster)
        tvJud.text = movie.title
        tvDateRelease.text = movie.release
        tvSynopsis.text = movie.synopsis
        tvLanguage.text = movie.language
        tvBudget.text = movie.popularity.toString()
        tvRevenue.text = movie.vote.toString()
    }

    companion object {
        const val EXTRA_TV = " "

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


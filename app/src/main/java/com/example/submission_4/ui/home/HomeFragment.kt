package com.example.submission_4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_4.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var rvFilm: RecyclerView
    private lateinit var adapter: ListFilmAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFilm = view.findViewById(R.id.rv_film)
        rvFilm.setHasFixedSize(true)

        adapter = ListFilmAdapter()
        adapter.notifyDataSetChanged()
        rvFilm.layoutManager = LinearLayoutManager(activity)
        rvFilm.adapter = adapter

        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)
        homeViewModel.setFilm()
        homeViewModel.getFilm().observe(this, Observer { filmItems ->
            if (filmItems != null) {
                adapter.setData(filmItems)
                showLoading(false)
            }
        })
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar2.visibility = View.VISIBLE
        } else {
            progressBar2.visibility = View.GONE
        }
    }

}
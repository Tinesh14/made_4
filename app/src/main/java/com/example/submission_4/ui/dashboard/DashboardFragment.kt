package com.example.submission_4.ui.dashboard

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
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var rvTv: RecyclerView
    private lateinit var adapter: ListTvAdapter
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_dashboard, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTv = view.findViewById(R.id.rv_film2)
        rvTv.setHasFixedSize(true)
        adapter = ListTvAdapter()
        adapter.notifyDataSetChanged()
        rvTv.layoutManager = LinearLayoutManager(activity)
        rvTv.adapter = adapter

        dashboardViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DashboardViewModel::class.java)
        dashboardViewModel.setTv()
        dashboardViewModel.getTv().observe(this, Observer { tvItems ->
            if (tvItems != null) {
                adapter.setData(tvItems)
                showLoading(false)
            }
        })
        showLoading(true)

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
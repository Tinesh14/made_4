package com.example.submission_4.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_4.BuildConfig
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class DashboardViewModel : ViewModel() {

    companion object {
        private const val API_KEY = BuildConfig.TMDB_API_KEY
    }

    val listTv = MutableLiveData<ArrayList<Tv>>()
    internal fun setTv() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<Tv>()
        val url = "https://api.themoviedb.org/3/discover/tv?api_key=$API_KEY&language=en-US"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    for (i in 0 until list.length()) {
                        val tvv = list.getJSONObject(i)
                        val tvItems = Tv()
                        tvItems.title = tvv.getString("name")
                        tvItems.synopsis = tvv.getString("overview")
                        tvItems.release = tvv.getString("first_air_date")
                        tvItems.poster = tvv.getString("poster_path")
                        tvItems.language = tvv.getString("original_language")
                        tvItems.popularity = tvv.getInt("popularity")
                        tvItems.vote = tvv.getInt("vote_count")
                        listItems.add(tvItems)
                    }
                    listTv.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }

    internal fun getTv(): LiveData<ArrayList<Tv>> {
        return listTv
    }
}
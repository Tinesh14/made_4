package com.example.submission_4.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission_4.BuildConfig
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class HomeViewModel : ViewModel() {

    companion object {
        private const val API_KEY = BuildConfig.TMDB_API_KEY
    }

    val listFilm = MutableLiveData<ArrayList<Film>>()
    internal fun setFilm() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<Film>()
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$API_KEY&language=en-US"
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
                        val film = list.getJSONObject(i)
                        val filmItems = Film()
                        filmItems.title = film.getString("title")
                        filmItems.synopsis = film.getString("overview")
                        filmItems.release = film.getString("release_date")
                        filmItems.poster = film.getString("poster_path")
                        filmItems.language = film.getString("original_language")
                        filmItems.popularity = film.getInt("popularity")
                        filmItems.vote = film.getInt("vote_count")
                        listItems.add(filmItems)
                    }
                    listFilm.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }

    internal fun getFilm(): MutableLiveData<ArrayList<Film>> {
        return listFilm
    }
}
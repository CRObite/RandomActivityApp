package com.example.app_random_api

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rk_application.util.NetworkUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
private lateinit var activityText :TextView
private lateinit var buttonGet :TextView
private lateinit var buttonGoWeb :TextView
private lateinit var buttonFavorite :ImageView
private lateinit var loading: ProgressBar
private lateinit var errorText: TextView
private lateinit var noResultText: TextView

class NameFragment : Fragment() {

    companion object{
        private lateinit var activityTHis: ActivityClass
    }

    class QueryTask():  AsyncTask<URL, Void, String>() {
        private var listOfNews: MutableList<ActivityClass> = mutableListOf()

        override fun onPreExecute() {

            loading.visibility = View.VISIBLE
            noResultText.visibility = View.INVISIBLE
            errorText.visibility = View.INVISIBLE
        }

        override fun doInBackground(vararg p0: URL?): String? {

            var response : String? = NetworkUtils.getResponseFromURL(p0[0]!!)

            return response
        }

        override fun onPostExecute(result: String?) {
            var info: String? = null


            if(result != null && result != "" ) {
                try {
                    val jsonResponse: JSONObject = JSONObject(result)

                    activityText.text = jsonResponse.getString("activity")
                    activityTHis = ActivityClass(jsonResponse.getString("activity"),jsonResponse.getString("type"),jsonResponse.getString("link"),false);

                    var list = FavoriteFragment().getListOfFavorite()
                    if(list.any { it.getActivity() == jsonResponse.getString("activity") }) {
                        buttonFavorite.setImageResource(R.drawable.favorite_colored_icon)
                    }


                } catch (e: JSONException){

                    noResultText.visibility = View.VISIBLE
                }

            } else if(result == null || result == ""){
                errorText.visibility = View.VISIBLE

            }

            loading.visibility = View.INVISIBLE

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        var view = inflater.inflate(R.layout.fragment_name, container, false)
        loading = view.findViewById(R.id.loading)
        errorText = view.findViewById(R.id.errorText)
        noResultText = view.findViewById(R.id.noResoultText)

        activityText =view.findViewById(R.id.activity_text)
        buttonGet = view.findViewById(R.id.button_get)
        buttonGoWeb= view.findViewById(R.id.button_goweb)
        buttonFavorite = view.findViewById(R.id.favorite_btn)

        buttonGet.setOnClickListener {
            buttonFavorite.setImageResource(R.drawable.favorite_icon)
            buttonGoWeb.visibility = View.VISIBLE
            buttonFavorite.visibility = View.VISIBLE
            var generatedURL: URL = NetworkUtils.generateURL()
            QueryTask().execute(generatedURL)
            errorText.visibility = View.INVISIBLE

        }



        buttonGoWeb.setOnClickListener {
            buttonGoWeb.visibility = View.INVISIBLE

            buttonFavorite.visibility = View.INVISIBLE
            buttonFavorite.setImageResource(R.drawable.favorite_icon)
            activityText.text = ""
        }


        buttonFavorite.setOnClickListener {

            if(activityTHis != null && !activityTHis.getFavorite()){
                activityTHis.setFavorite(true)
                buttonFavorite.setImageResource(R.drawable.favorite_colored_icon)
                FavoriteFragment().AddNewFavoriteAnime(activityTHis,0)
            }else if(activityTHis != null && activityTHis.getFavorite()){
                activityTHis.setFavorite(false)
                buttonFavorite.setImageResource(R.drawable.favorite_icon)
                FavoriteFragment().RemoveFavoriteAnime(activityTHis,0)
            }

        }
        return view
    }


}
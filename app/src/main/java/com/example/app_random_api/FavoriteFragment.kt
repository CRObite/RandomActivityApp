package com.example.app_random_api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


private var adapter: RecyclerView.Adapter<RecyclerFavoriteAdapter.ViewHolder>? = null
class FavoriteFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?= null
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView

    companion object{
        private var listOfFavorite: MutableList<ActivityClass> = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favorite, container, false)
        layoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recycler_view_favorite)
        recyclerView.layoutManager = layoutManager


        emptyText = view.findViewById(R.id.emptyText)
        if(listOfFavorite.isNullOrEmpty()){
            emptyText.visibility = View.VISIBLE
        } else{
            emptyText.visibility = View.INVISIBLE
        }

        adapter = RecyclerFavoriteAdapter(listOfFavorite)
        recyclerView.adapter = adapter
        return view
    }
    fun AddNewFavoriteAnime(activity: ActivityClass,type: Int){

        listOfFavorite.add(activity)

        if(type==1){

            adapter?.notifyDataSetChanged()
        }
    }

    fun RemoveFavoriteAnime(activity: ActivityClass,type: Int){


        if(!listOfFavorite.isNullOrEmpty()){

            for(i in listOfFavorite){
                if(i.getActivity() == activity.getActivity()){

                    listOfFavorite.remove(i)
                    break

                }
            }
        }


        if(type==1){

            adapter?.notifyDataSetChanged()
        }
    }

    fun getListOfFavorite(): MutableList<ActivityClass>{
        return listOfFavorite
    }
}
package com.example.app_random_api

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class RecyclerFavoriteAdapter(private val listOfFavorite: MutableList<ActivityClass>): RecyclerView.Adapter<RecyclerFavoriteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_card,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  listOfFavorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.itemName.text = listOfFavorite[position].getActivity()

        holder.itemStatus.text = listOfFavorite[position].getType()

        if(!listOfFavorite[position].getFavorite()){
            holder.itemButton.setImageResource(R.drawable.favorite_icon)
        }else if(listOfFavorite[position].getFavorite()){
            holder.itemButton.setImageResource(R.drawable.favorite_colored_icon)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var itemName: TextView
        var itemStatus: TextView
        var itemContainer: RelativeLayout
        var itemButton: ImageView



        init {

            itemName = itemView.findViewById(R.id.person_title)
            itemStatus = itemView.findViewById(R.id.persone_status)
            itemButton = itemView.findViewById(R.id.favorite_btn)
            itemContainer = itemView.findViewById(R.id.container)


            itemButton.setOnClickListener {
                if(!listOfFavorite[position].getFavorite()){
                    itemButton.setImageResource(R.drawable.favorite_colored_icon)
                    listOfFavorite[position].setFavorite(true)

                    val fragmentFragment: FavoriteFragment = FavoriteFragment()
                    fragmentFragment.AddNewFavoriteAnime(listOfFavorite[position],1)



                }else{
                    itemButton.setImageResource(R.drawable.favorite_icon)
                    listOfFavorite[position].setFavorite(false)
                    val fragmentFragment: FavoriteFragment = FavoriteFragment()
                    fragmentFragment.RemoveFavoriteAnime(listOfFavorite[position],1)
                }
            }






        }


    }

}

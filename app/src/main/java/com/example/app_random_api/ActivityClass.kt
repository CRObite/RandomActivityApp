package com.example.app_random_api

class ActivityClass (private var activity: String,private var type: String, private var link: String,private var favorite: Boolean = false){

    fun getFavorite(): Boolean{
        return this.favorite
    }
    fun setFavorite(isfavorite: Boolean){
        this.favorite = isfavorite
    }

    fun getActivity(): String{
        return this.activity
    }
    fun getLink(): String {
        return this.link
    }
    fun getType(): String {
        return this.type
    }

}
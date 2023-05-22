package com.example.app_random_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var bottomNavBar: BottomNavigationView
private lateinit var nameFragment: NameFragment
private lateinit var favoriteFragment: FavoriteFragment
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavBar = findViewById(R.id.bottom_nav)
        nameFragment = NameFragment()
        favoriteFragment = FavoriteFragment()

        supportFragmentManager.beginTransaction().replace(R.id.main_fragment, nameFragment).commit()


        bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.name ->{ supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, nameFragment).commit()
                    bottomNavBar.menu.getItem(1).isChecked = false
                    bottomNavBar.menu.getItem(0).isChecked = true}
                R.id.favorite ->{ supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, favoriteFragment).commit()
                    bottomNavBar.menu.getItem(0).isChecked = false
                    bottomNavBar.menu.getItem(1).isChecked = true}
            }
            false
        }
    }


    fun onModeChanged(){
        bottomNavBar = findViewById(R.id.bottom_nav)
        bottomNavBar.menu.getItem(1).isChecked = false
        bottomNavBar.menu.getItem(0).isChecked = true
    }
}
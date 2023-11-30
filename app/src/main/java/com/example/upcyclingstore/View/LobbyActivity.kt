package com.example.upcyclingstore.View

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upcyclingstore.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class LobbyActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
        private lateinit var bottomNavigationView: BottomNavigationView
        private val lobbyFragment = LobbyFragment()    // 로그인 후 로비
        private val wasteFragment = WasteFragment()  // 폐기물 거래소
        private val mypageFragment = MypageFragment()    // 마이페이지

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_lobby)

            bottomNavigationView = findViewById(R.id.bottomNavigationView)

            bottomNavigationView.setOnItemSelectedListener(this)
            bottomNavigationView.setSelectedItemId(R.id.home)


        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, lobbyFragment)
                        .commit()
                    return true
                }

                R.id.wastetrade -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, wasteFragment)
                        .commit()
                    return true
                }

                R.id.mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, mypageFragment)
                        .commit()
                    return true
                }
            }
            return false
        }

    }
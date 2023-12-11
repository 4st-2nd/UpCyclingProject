package com.example.upcyclingstore.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.upcyclingstore.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class LobbyActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
        private lateinit var bottomNavigationView: BottomNavigationView
        private val lobbyFragment = LobbyFragment()    // 로그인 후 로비
        private val wasteFragment = WasteFragment()  // 폐기물 거래소
        private val mypageFragment = MypageFragment()    // 마이페이지
        private val productFragment = ProductFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_lobby)

            bottomNavigationView = findViewById(R.id.bottomNavigationView)

            bottomNavigationView.setOnItemSelectedListener(this)
            bottomNavigationView.setSelectedItemId(R.id.home)


        }

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.flFragment)
            var backTag:String = ""
                if(currentFragment != null)
                    backTag = setBackFragmentName(currentFragment!!)
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, lobbyFragment)
                        .addToBackStack(backTag)
                        .commit()
                    return true
                }
                R.id.product -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, productFragment)
                        .addToBackStack(backTag)
                        .commit()
                    return true
                }

                R.id.wastetrade -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, wasteFragment)
                        .addToBackStack(backTag)
                        .commit()
                    return true
                }

                R.id.mypage -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flFragment, mypageFragment)
                        .addToBackStack(backTag)
                        .commit()
                    return true
                }
            }
            return false
        }
    //뒤로가기 버튼에 뒤로가능 기능이 없어서 오류생기는데 이걸 무시함
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount > 1) {
            val topBackStackEntry = supportFragmentManager.getBackStackEntryAt(backStackEntryCount - 1)
            val topBackStackTag = topBackStackEntry.name
            // topBackStackTag를 사용하여 트랜잭션의 태그를 확인
            when (topBackStackTag) {
                "home" -> {
                    bottomNavigationView.selectedItemId = R.id.home
                }
                "product" -> {
                    bottomNavigationView.selectedItemId = R.id.product
                }
                "waste" -> {
                    bottomNavigationView.selectedItemId = R.id.wastetrade
                }
                "my" -> {
                    bottomNavigationView.selectedItemId = R.id.mypage
                }
            }
            val fragmentManager = supportFragmentManager
            fragmentManager.popBackStack()
            fragmentManager.popBackStack()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        Log.d("Stack",backStackEntryCount.toString())
    }
    private fun setBackFragmentName(currentFragment:Fragment):String {
        when (currentFragment) {
            is LobbyFragment -> {
                return "home"
            }
            is ProductFragment -> {
                return "product"
            }
            is WasteFragment -> {
                return "waste"
            }
            is MypageFragment -> {
                return "my"
            }
        }
        return ""
    }
}
package com.example.schoolregister

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity_main)
        val view_pager: ViewPager2 = findViewById(R.id.view_pager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        view_pager.adapter = adapter

        TabLayoutMediator(tabLayout,view_pager){tab,position->
            when(position){
                0->{
                    tab.text ="Monday"
                }
                1->{
                    tab.text="Tuesday"
                }
                2->{
                    tab.text="Wednesday"
                }
                3->{
                    tab.text="Thursday"
                }
                4->{
                    tab.text="Friday"
                }
            }
        }.attach()
    }

    fun onClickBack(v: View){
        startActivity(Intent(this, StudentMainActivity::class.java))
    }
}
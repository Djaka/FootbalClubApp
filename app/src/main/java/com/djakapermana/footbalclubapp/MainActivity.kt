package com.djakapermana.footbalclubapp

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.djakapermana.footbalclubapp.R.id.*
import com.djakapermana.footbalclubapp.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name).toString()

        setupViewPager(view_pager_schedule)
        tab_schedule.setupWithViewPager(view_pager_schedule)

    }

    fun setupViewPager(viewPager: ViewPager){
        val adapterFragment = MainAdapter(supportFragmentManager)
        adapterFragment.addFragment(LastMatchFragment(),"Prev Match")
        adapterFragment.addFragment(NextMatchFragment(),"Next Match")
        adapterFragment.addFragment(FavoriteMatchFragment(),"Favorites")
        viewPager.adapter = adapterFragment
        viewPager.offscreenPageLimit = 1
    }
}

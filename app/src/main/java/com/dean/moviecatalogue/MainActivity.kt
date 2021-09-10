package com.dean.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dean.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this@MainActivity,
            factory
        )[MainViewModel::class.java]

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        mainBinding.viewPager.adapter = sectionsPagerAdapter
        mainBinding.tabs.setupWithViewPager(mainBinding.viewPager)
        supportActionBar?.elevation = 0f
    }
}
package com.example.assignmentgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignmentgallery.ui.fragments.display.DisplayFragment
import com.example.assignmentgallery.ui.fragments.list.ListFragment

class MainActivity : AppCompatActivity() {

    lateinit var displayFragment: DisplayFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ListFragment())
                    .commit()
        }
    }

    fun openDisplayFragment(url: String)
    {
        val bundle = Bundle()
        bundle.putString("url", url)
        displayFragment = DisplayFragment()
        displayFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, displayFragment)
                .commit()
    }

}
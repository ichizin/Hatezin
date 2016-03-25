package com.ichizin.hatezin.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ichizin.hatezin.HatezinApplication
import com.ichizin.hatezin.R
import com.ichizin.hatezin.ui.fragments.HotEntryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HatezinApplication.getComponent(this)

        setFragment()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setFragment() {

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = HotEntryFragment.newInstance()

        transaction.add(R.id.main_content, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


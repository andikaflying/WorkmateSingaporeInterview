package com.example.workmatetest.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.workmatetest.R
import com.example.workmatetest.utilities.InjectorUtils
import com.example.workmatetest.viewmodels.ClockViewModel

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.getName()
    private lateinit var clockViewModel: ClockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = InjectorUtils.provideClockViewModelFactory(this)
        clockViewModel = ViewModelProviders.of(this, factory).get(ClockViewModel::class.java)
        clockViewModel.auth().observe(this, Observer {
            if (it != null) {
                clockViewModel.clockIn().observe(this, Observer {
                    if (it != null) {
                        Log.e(TAG, "ID Response : " + it.id)
                    }
                })
            }
        })
    }
}

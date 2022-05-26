package com.arch.starwarssearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arch.starwarssearch.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarWarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
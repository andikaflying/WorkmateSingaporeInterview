package com.example.workmatetest.utilities

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.workmatetest.data.repository.ClockRepository
import com.example.workmatetest.viewmodels.ClockViewModelFactory

object InjectorUtils {

    private fun getClockRepository(context: Context): ClockRepository {
        return ClockRepository(context)
    }

    fun provideClockViewModelFactory(context: Context): ClockViewModelFactory {
        val repository = getClockRepository(context)

        return ClockViewModelFactory(repository)
    }
}
package com.example.workmatetest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workmatetest.data.repository.ClockRepository

class ClockViewModelFactory(private val repository: ClockRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ClockViewModel(repository) as T
}
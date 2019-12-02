package com.example.workmatetest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.workmatetest.data.model.auth.AuthResponse
import com.example.workmatetest.data.model.clock.ClockResponse
import com.example.workmatetest.data.repository.ClockRepository

class ClockViewModel constructor(private val repository: ClockRepository) : ViewModel() {

    private val clockResponse = MediatorLiveData<ClockResponse>()
    private val authResponse = MediatorLiveData<AuthResponse>()
    private val TAG = ClockViewModel::class.java.name

    fun auth(): MediatorLiveData<AuthResponse> {
        val liveData: LiveData<AuthResponse> = repository.auth()
        authResponse.addSource(liveData, authResponse::setValue)

        return authResponse
    }

    fun clockIn(): MediatorLiveData<ClockResponse> {
        val liveData: LiveData<ClockResponse> = repository.clockIn()
        clockResponse.addSource(liveData, clockResponse::setValue)

        return clockResponse
    }

    fun clockOut(): MediatorLiveData<ClockResponse> {
        val liveData: LiveData<ClockResponse> = repository.clockOut()
        clockResponse.addSource(liveData, clockResponse::setValue)

        return clockResponse
    }
}
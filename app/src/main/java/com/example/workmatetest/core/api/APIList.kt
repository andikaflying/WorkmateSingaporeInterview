package com.example.workmatetest.core.api

import com.example.workmatetest.data.model.auth.AuthRequest
import com.example.workmatetest.data.model.auth.AuthResponse
import com.example.workmatetest.data.model.clock.ClockResponse
import com.example.workmatetest.data.model.clock.CoordinateRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface APIList {

    //https://api.helpster.tech/v1/auth/login/
    @POST("/v1/auth/login/")
    public fun auth(@Body authRequest: AuthRequest): Call<AuthResponse>

//    https://api.helpster.tech/v1/staff-requests/26074/clock-in/
    @POST("/v1/staff-requests/26074/clock-in/")
    public fun clockIn(@Body coordinateRequest: CoordinateRequest): Call<ClockResponse>

//    https://api.helpster.tech/v1/staff-requests/26074/clock-out/
    @POST("/v1/staff-requests/26074/clock-out/")
    public fun clockOut(@Body coordinateRequest: CoordinateRequest): Call<ClockResponse>
}
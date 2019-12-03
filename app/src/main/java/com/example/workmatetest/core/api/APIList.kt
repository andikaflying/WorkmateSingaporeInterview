package com.example.workmatetest.core.api

import com.example.workmatetest.data.model.auth.AuthRequest
import com.example.workmatetest.data.model.auth.AuthResponse
import com.example.workmatetest.data.model.clock.ClockResponse
import com.example.workmatetest.data.model.clock.CoordinateRequest
import com.example.workmatetest.data.model.staff_detail.StaffDetailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface APIList {

    @POST("/v1/auth/login/")
    public fun auth(@Body authRequest: AuthRequest): Call<AuthResponse>

    @POST("/v1/staff-requests/26074/clock-in/")
    public fun clockIn(@Body coordinateRequest: CoordinateRequest): Call<ClockResponse>

    @POST("/v1/staff-requests/26074/clock-out/")
    public fun clockOut(@Body coordinateRequest: CoordinateRequest): Call<ClockResponse>

    @GET("/v1/staff-requests/26074/")
    public fun getStaffDetail(): Call<StaffDetailResponse>
}
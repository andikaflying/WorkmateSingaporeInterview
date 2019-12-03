package com.example.workmatetest.data.repository

import android.content.Context
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workmatetest.core.api.APIList
import com.example.workmatetest.core.api.RetrofitManager
import com.example.workmatetest.data.model.auth.AuthRequest
import com.example.workmatetest.data.model.auth.AuthResponse
import com.example.workmatetest.data.model.clock.ClockResponse
import com.example.workmatetest.data.model.clock.CoordinateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClockRepository(var context: Context) {
    val TAG: String = ClockRepository::class.java.name

//    var apiList: APIList;
//
//    init {
//        apiList = null
//    }

    fun auth(): LiveData<AuthResponse> {
        val liveDataResponse: MutableLiveData<AuthResponse> = MutableLiveData()
        var request: AuthRequest = AuthRequest();

//        apiList.auth(request).enqueue(object : Callback<AuthResponse> {
//            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
//                if (response.isSuccessful) {
//                    liveDataResponse.value = response.body()
//                    RetrofitManager.setHeaders(liveDataResponse.value?.key!!)
//                }
//            }
//
//            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
//                Log.e(TAG, t.message)
//            }
//        })

        return liveDataResponse
    }

    fun clockIn(): LiveData<ClockResponse> {

        val liveDataResponse: MutableLiveData<ClockResponse> = MutableLiveData()
        var request: CoordinateRequest = CoordinateRequest();
        RetrofitManager.mRetrofit.create(APIList::class.java).clockIn(request).enqueue(object : Callback<ClockResponse> {
            override fun onResponse(call: Call<ClockResponse>, response: Response<ClockResponse>) {
                if (response.isSuccessful) {
                    liveDataResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<ClockResponse>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })

        return liveDataResponse
    }

    fun clockOut(): LiveData<ClockResponse> {
        val liveDataResponse: MutableLiveData<ClockResponse> = MutableLiveData()
        var request: CoordinateRequest = CoordinateRequest();

//        apiList.clockOut(request).enqueue(object : Callback<ClockResponse> {
//            override fun onResponse(call: Call<ClockResponse>, response: Response<ClockResponse>) {
//                if (response.isSuccessful) {
//                    liveDataResponse.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<ClockResponse>, t: Throwable) {
//                Log.e(TAG, t.message)
//            }
//        })

        return liveDataResponse
    }
}
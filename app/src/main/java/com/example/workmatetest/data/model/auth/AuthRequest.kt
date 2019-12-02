package com.example.workmatetest.data.model.auth

import android.os.Parcel
import android.os.Parcelable

data class AuthRequest(var username:String? = "+6281313272005", var password: String? = "alexander") : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthRequest> {
        override fun createFromParcel(parcel: Parcel): AuthRequest {
            return AuthRequest(parcel)
        }

        override fun newArray(size: Int): Array<AuthRequest?> {
            return arrayOfNulls(size)
        }
    }

}
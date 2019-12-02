package com.example.workmatetest.data.model.clock

data class StaffRequest(
    val client: Client,
    val id: Int,
    val location: Location,
    val position: Position,
    val status: String,
    val title: String,
    val uid: String
)
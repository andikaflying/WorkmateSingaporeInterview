package com.example.workmatetest.data.model.staff_detail

data class Schedule(
    val duration: String,
    val end_time: String,
    val id: Int,
    val recurrences: String,
    val staff_request: Int,
    val start_date: String,
    val start_time: String
)
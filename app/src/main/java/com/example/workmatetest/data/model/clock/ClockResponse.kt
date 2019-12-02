package com.example.workmatetest.data.model.clock

data class ClockResponse(
    val clock_in_latitude: String,
    val clock_in_longitude: String,
    val clock_in_time: String,
    val clock_out_latitude: Any,
    val clock_out_longitude: Any,
    val clock_out_time: Any,
    val id: Int,
    val notes: Any,
    val partner: Partner,
    val schedule: Any,
    val staff_request: StaffRequest,
    val status: String,
    val timesheet: Int
)
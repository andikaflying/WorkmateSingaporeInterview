package com.example.workmatetest.data.model.staff_detail

data class OfferCounts(
    val applied: Int,
    val cancelled: Int,
    val confirmed: Int,
    val contract_ended: Int,
    val no_show: Int,
    val pending_contract: Int,
    val pending_onboarding: Int,
    val rejected: Int,
    val sent: Int,
    val viewed: Int,
    val withdrawn: Int
)
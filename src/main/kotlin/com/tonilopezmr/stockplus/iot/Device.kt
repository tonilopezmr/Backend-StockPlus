package com.tonilopezmr.stockplus.iot

data class Device(
    val mac: String,
    val ip: String,
    val online: Boolean,
    val state: Boolean
)
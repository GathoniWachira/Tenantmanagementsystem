package com.example.tenantmanagement

data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) {
    fun summary(): String {
        return "Tenant Name: $name\nPhone: $phone\nRent: $rent"
    }
}
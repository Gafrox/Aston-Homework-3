package ru.gustavo.astonhomework32

data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val isChecked: Boolean = false
)
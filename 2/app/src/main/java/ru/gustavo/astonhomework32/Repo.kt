package ru.gustavo.astonhomework32

import androidx.lifecycle.MutableLiveData
import io.github.serpro69.kfaker.Faker

object Repo {
    private val contacts = MutableLiveData(generateContacts())
    fun generateContacts(): MutableList<Contact> {
        val contacts = mutableListOf<Contact>()
        var id = 1
        repeat(100) {
            contacts.add(createContact(id))
            id++
        }
        return contacts
    }

    private fun createContact(id: Int): Contact {
        val faker = Faker()
        return Contact(
            id = id,
            firstName = faker.name.firstName(),
            lastName = faker.name.lastName(),
            phoneNumber = faker.phoneNumber.phoneNumber()
        )
    }

    fun deleteContact(list: List<Contact>) {
        contacts.value?.removeAll(list)
    }
}
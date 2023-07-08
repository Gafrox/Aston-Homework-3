package ru.gustavo.astonhomework32

import androidx.lifecycle.MutableLiveData
import io.github.serpro69.kfaker.Faker

object Repo {
    val contacts = MutableLiveData(generateContacts())
    private val checkedSet = hashSetOf<Int>()
    var id = 0
    private fun generateContacts(): MutableList<Contact> {
        val contacts = mutableListOf<Contact>()
        repeat(100) {
            contacts.add(createContact(id))
            id += 1
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

    fun onCheckContact(contact: Contact, isChecked: Boolean) {
        if (contact.isChecked == isChecked) return
        else {
            val checkedContact = contact.copy(isChecked = isChecked)
            if (isChecked) checkedSet.add(checkedContact.id) else checkedSet.remove(checkedContact.id)
        }
    }

    fun deleteContact() {
        val list = contacts.value
        list?.removeIf { checkedSet.contains(it.id) } ?: return
        checkedSet.clear()
        contacts.value = list
    }

    fun editContact(editedContact: Contact) {
        val list = contacts.value
        val index = list?.indexOfFirst { it.id == editedContact.id }
        if (index == -1) return
        if (index != null) {
            list.removeAt(index)
            list.add(index, editedContact)
        }
        contacts.value = list
    }

    fun addContact(newContact: Contact) {
        val list = contacts.value
        list?.add(0, newContact)
        id += 1
        contacts.value = list
    }

    fun swapContact(pos1: Int, pos2: Int) {
        val list = contacts.value
        list?.let {
            val temp = list[pos1]
            list[pos1] = list[pos2]
            list[pos2] = temp
        }
        contacts.value = list
    }
}
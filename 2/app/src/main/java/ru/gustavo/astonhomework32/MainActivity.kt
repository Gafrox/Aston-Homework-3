package ru.gustavo.astonhomework32

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.gustavo.astonhomework32.Repo.addContact
import ru.gustavo.astonhomework32.Repo.contacts
import ru.gustavo.astonhomework32.Repo.deleteContact
import ru.gustavo.astonhomework32.Repo.editContact
import ru.gustavo.astonhomework32.Repo.onCheckContact
import ru.gustavo.astonhomework32.Repo.swapContact
import ru.gustavo.astonhomework32.databinding.ActivityMainBinding
import ru.gustavo.astonhomework32.databinding.DialogEditBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogBinding: DialogEditBinding
    private var targetContact: Contact? = null
    private val adapter: AsyncListDifferDelegationAdapter<Contact> by lazy {
        AsyncListDifferDelegationAdapter(
            ContactDiffCallback(),
            AdapterDelegatesManager(
                adapterDelegate(
                    { contact ->
                        targetContact = contact
                        showDialog(contact)
                    },
                    { contact, b ->
                        onCheckContact(contact, b)
                    })
            )
        )
    }
    private val editDialog: AlertDialog by lazy {
        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.Save)) { _, _ ->
                dialogBinding.apply {
                    val contact = Contact(
                        targetContact?.id ?: Repo.id,
                        editFirstName.text.toString(),
                        editLastName.text.toString(),
                        editPhone.text.toString()
                    )
                    if (targetContact == null) {
                        addContact(contact)
                    } else {
                        editContact(contact)
                        targetContact = null
                    }
                }
            }
            .setNegativeButton(getString(R.string.Cancel)) { cancel, _ ->
                targetContact = null
                cancel.cancel()
            }
            .create()
    }

    private val onSwapContact = { pos1: Int, pos2: Int ->
        swapContact(pos1, pos2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogBinding = DialogEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            recyclerView.adapter = adapter
            ItemTouchHelper(SwapContact(onSwapContact)).attachToRecyclerView(recyclerView)
            contacts.observe(this@MainActivity) {
                val newContact = adapter.itemCount < contacts.value?.size!!
                adapter.items = it.toList()
                if (newContact) {
                    recyclerView.smoothScrollToPosition(0)
                }
            }
            fabAdd.setOnClickListener {
                showAddDialog()
            }
            fabDelete.setOnClickListener {
                deleteContact()
            }
        }
    }

    private fun showDialog(contact: Contact) {
        dialogBinding.apply {
            editFirstName.setText(contact.firstName)
            editLastName.setText(contact.lastName)
            editPhone.setText(contact.phoneNumber)
        }
        editDialog.show()
    }

    private fun showAddDialog() {
        dialogBinding.apply {
            editFirstName.text = null
            editLastName.text = null
            editPhone.text = null
        }
        editDialog.show()
    }
}
package ru.gustavo.astonhomework32

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.gustavo.astonhomework32.databinding.ContactBinding

class Adapter : RecyclerView.Adapter<Adapter.Holder>() {
    private val contacts = ArrayList<Contact>()

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ContactBinding.bind(item)
        fun bind(contact: Contact) = with(binding) {
            firstName.text = contact.firstName
            lastName.text = contact.lastName
            phoneNumber.text = contact.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(contacts[position])

    override fun getItemCount() = contacts.size

    fun addAll(list: List<Contact>) {
        contacts.addAll(list)
    }
}

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}
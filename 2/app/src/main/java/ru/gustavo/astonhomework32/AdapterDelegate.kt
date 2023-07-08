package ru.gustavo.astonhomework32

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.gustavo.astonhomework32.databinding.ContactBinding

fun adapterDelegate(itemClickedListener: (Contact) -> Unit, onChecked: (Contact, Boolean) -> Unit) =
    adapterDelegateViewBinding<Contact, Contact, ContactBinding>(
        { layoutInflater, root -> ContactBinding.inflate(layoutInflater, root, false) }
    ) {
        binding.apply {
            editButton.setOnClickListener {
                itemClickedListener.invoke(item)
            }
            checkBox.setOnCheckedChangeListener { _, b ->
                onChecked.invoke(item, b)
            }
            bind {
                firstName.text = item.firstName
                lastName.text = item.lastName
                phoneNumber.text = item.phoneNumber
                checkBox.isChecked = item.isChecked
            }
        }
    }

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
}

class SwapContact(val onSwap: (Int, Int) -> Unit) :
    ItemTouchHelper.SimpleCallback(
        (ItemTouchHelper.UP or ItemTouchHelper.DOWN),
        (ItemTouchHelper.UP or ItemTouchHelper.DOWN)
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        onSwap(viewHolder.absoluteAdapterPosition, target.absoluteAdapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }
}
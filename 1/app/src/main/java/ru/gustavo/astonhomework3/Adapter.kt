package ru.gustavo.astonhomework3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gustavo.astonhomework3.databinding.CountryBinding

class Adapter : RecyclerView.Adapter<Adapter.Holder>() {
    private val countries = ArrayList<Country>()

    class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CountryBinding.bind(item)
        fun bind(country: Country) = with(binding) {
            flag.setImageResource(country.flag)
            countryName.text = country.name
            id.text = country.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(countries[position])

    override fun getItemCount() = countries.size

    fun addAll(list: List<Country>) {
        countries.addAll(list)
    }
}
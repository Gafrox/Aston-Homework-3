package ru.gustavo.astonhomework32

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gustavo.astonhomework32.Repo.deleteContact
import ru.gustavo.astonhomework32.Repo.generateContacts
import ru.gustavo.astonhomework32.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = Adapter()
    private val contacts = generateContacts()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
            adapter.addAll(contacts)
            fabAdd.setOnClickListener {  }
            fabDelete.setOnClickListener {
            }
        }
    }
}
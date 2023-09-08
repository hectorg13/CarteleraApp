package com.hg.cartelera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hg.cartelera.databinding.ActivityMainBinding
import com.hg.cartelera.viewmodels.PeliculasViewModel
import com.hg.cartelera.views.AdapterPeliculas

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PeliculasViewModel
    private lateinit var adapterPeliculas: AdapterPeliculas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("OnCreatedMainActivity", "binding ok ")
        viewModel = ViewModelProvider(this)[PeliculasViewModel::class.java]
        Log.i("OnCreatedMainActivity", "viewmodel ok")
        setupRecyclerView()

        viewModel.listaPeliculas.observe(this) {
            adapterPeliculas.listaPeliculas = it
            adapterPeliculas.notifyDataSetChanged()
            Log.i("OnCreatedMainActivity", "observe ok ")
        }

        binding.cvCartelera.setOnClickListener {
            viewModel.obtenerCartelera()
            cambiarColorBoton("car")
        }

        binding.cvPopulares.setOnClickListener {
            viewModel.obtenerPopuluares()
            cambiarColorBoton("pop")
        }
        viewModel.obtenerCartelera()
    }

    private fun cambiarColorBoton(boton: String) {
        when (boton) {
            "car" -> {
                binding.cvCartelera.setCardBackgroundColor(resources.getColor(R.color.verde_200))
                binding.cvPopulares.setCardBackgroundColor(resources.getColor(R.color.azul_200))
            }

            "pop" -> {
                binding.cvCartelera.setCardBackgroundColor(resources.getColor(R.color.azul_200))
                binding.cvPopulares.setCardBackgroundColor(resources.getColor(R.color.verde_200))
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 4)
        binding.rvPeliculas.layoutManager = layoutManager
        adapterPeliculas = AdapterPeliculas(this, arrayListOf())
        binding.rvPeliculas.adapter = adapterPeliculas
    }
}
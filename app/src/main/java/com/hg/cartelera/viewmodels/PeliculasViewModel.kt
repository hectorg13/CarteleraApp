package com.hg.cartelera.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hg.cartelera.core.Constants
import com.hg.cartelera.core.RetrofitClient
import com.hg.cartelera.network.PeliculaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculasViewModel: ViewModel() {

    private var _listaPeliculas = MutableLiveData<List<PeliculaModel>>()
    val listaPeliculas : LiveData<List<PeliculaModel>> = _listaPeliculas

    fun obtenerCartelera(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.obtenerCartelera(Constants.API_KEY)
            withContext(Dispatchers.Main){
                _listaPeliculas.value = response.body()!!.resultados.sortedByDescending { it.votoPromedio }
            }
        }
        Log.i("Peliculas ViewModel", "Obtener Cartelera Called ")
    }

    fun obtenerPopuluares(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.obtenerPopulares(Constants.API_KEY)
            withContext(Dispatchers.Main){
                _listaPeliculas.value = response.body()!!.resultados.sortedByDescending { it.votoPromedio }
            }
        }
        Log.i("Peliculas ViewModel", "Obtener Populares Called ")
    }

}
package com.example.wb_8_1.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_8_1.utils.Resource
import com.example.wb_8_1.domain.model.DotaHeroModelDomain
import com.example.wb_8_1.domain.usecase.GetDotaHeroesListUseCase
import kotlinx.coroutines.*
import java.lang.Exception

class MainViewModel(private val getDotaHeroesListUseCase: GetDotaHeroesListUseCase) : ViewModel() {

    private val _dotaHeroesList = MutableLiveData<Resource<List<DotaHeroModelDomain>>>()
    val dotaHeroesList: LiveData<Resource<List<DotaHeroModelDomain>>>
        get() = _dotaHeroesList

    private val vmJob = Job()
    private val vmScope = CoroutineScope(Dispatchers.Main + vmJob)

    init {
        getDotaHeroes()
    }

    fun getDotaHeroes() {
        vmScope.launch {
            fillDotaHeroesList()
        }
    }

    private suspend fun fillDotaHeroesList(){
        _dotaHeroesList.value = Resource.Loading(data = null)
        _dotaHeroesList.value = getDotaHeroesListUseCaseExecuting()
    }

    private suspend fun getDotaHeroesListUseCaseExecuting(): Resource<List<DotaHeroModelDomain>>? {
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(data = getDotaHeroesListUseCase.execute())
            } catch (e: Exception){
                Resource.Error(data = null, message = e.message ?: "Error Occurred!")
            }
        }
    }

    override fun onCleared() {
        vmJob.cancel()
        super.onCleared()
    }

}
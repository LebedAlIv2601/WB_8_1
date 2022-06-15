package com.example.wb_8_1.data.repository

import com.example.wb_8_1.data.local.FileCacheManager
import com.example.wb_8_1.data.network.DotaApiHelper
import com.example.wb_8_1.domain.model.DotaHeroModelDomain
import com.example.wb_8_1.domain.repository.DotaRepository
import javax.inject.Inject

@ExperimentalStdlibApi
class DotaRepositoryImpl @Inject constructor(
    private val apiHelper: DotaApiHelper,
    private val fileCacheManager: FileCacheManager
) : DotaRepository {

    override suspend fun getDotaHeroes(): List<DotaHeroModelDomain> {

        val heroesListLocal = fileCacheManager.getFileData()

        return if(heroesListLocal.isNotEmpty()){
            heroesListLocal
        } else {
            val heroesListNet = apiHelper.getDotaHeroes()
            fileCacheManager.saveToFile(heroesListNet ?: emptyList())
            fileCacheManager.getFileData()
        }
    }
}
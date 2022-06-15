package com.example.wb_8_1.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.wb_8_1.data.model.DotaHeroModelData
import com.example.wb_8_1.data.utils.toData
import com.example.wb_8_1.data.utils.toDomain
import com.example.wb_8_1.domain.model.DotaHeroModelDomain
import com.example.wb_8_1.utils.Constants.DOTA_HEROES_FILE
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import javax.inject.Inject

class FileCacheManager @Inject constructor(private val context: Context){

    private val moshi: Moshi = Moshi.Builder().build()
    @ExperimentalStdlibApi
    private val adapter = moshi.adapter<List<DotaHeroModelData>>()

    @ExperimentalStdlibApi
    suspend fun saveToFile(heroesList: List<DotaHeroModelDomain>): Boolean{


        val heroesJson = adapter.toJson(heroesList.map { it.toData() })

        return try {
            val fileOutputStream = context.openFileOutput(DOTA_HEROES_FILE, MODE_PRIVATE)
            fileOutputStream.write(heroesJson.toByteArray())
            fileOutputStream?.close()
            true
        } catch (e: Exception){
            false
        }
    }

    @ExperimentalStdlibApi
    suspend fun getFileData(): List<DotaHeroModelDomain>{

        return try {

            val fileInputStream = context.openFileInput(DOTA_HEROES_FILE)
            val bytes = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            val heroesJson = String(bytes)
            fileInputStream?.close()

            adapter.fromJson(heroesJson)?.map { it.toDomain() } ?: emptyList()

        } catch (e: Exception){
            e.message?.let { Log.e("JsonError", it) }
            emptyList()
        }
    }

}
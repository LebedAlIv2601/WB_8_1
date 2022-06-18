package com.example.wb_8_1.domain.model

import java.io.Serializable


data class DotaHeroModelDomain(

    val id: Int,

    val name: String,

    val primaryAttr: String,

    val attackType: String,

    val icon: String,

    val img: String,

    val baseHealth: Double,

    val baseHealthRegen: Double,

    val baseMana: Double,

    val baseManaRegen: Double,

    val baseArmor: Double,

    val baseMr: Double,

    val baseAttackMin: Double,

    val baseAttackMax: Double,

    val baseStr: Double,

    val baseAgi: Double,

    val baseInt: Double,

    val strGain: Double,

    val agiGain: Double,

    val intGain: Double,

    val attackRange: Double,

    val moveSpeed: Double,

    val legs: Int
) : Serializable

package com.example.wb_8_1.data.utils

import com.example.wb_8_1.data.model.DotaHeroModelData
import com.example.wb_8_1.domain.model.DotaHeroModelDomain

fun DotaHeroModelData.toDomain(): DotaHeroModelDomain {
    return DotaHeroModelDomain(
        id,
        name,
        primaryAttr,
        attackType,
        icon,
        img,
        baseHealth,
        baseHealthRegen,
        baseMana,
        baseManaRegen,
        baseArmor,
        baseMr,
        baseAttackMin,
        baseAttackMax,
        baseStr,
        baseAgi,
        baseInt,
        strGain,
        agiGain,
        intGain,
        attackRange,
        moveSpeed,
        legs
    )
}

fun DotaHeroModelDomain.toData(): DotaHeroModelData {
    return DotaHeroModelData(
        id,
        name,
        primaryAttr,
        attackType,
        icon,
        img,
        baseHealth,
        baseHealthRegen,
        baseMana,
        baseManaRegen,
        baseArmor,
        baseMr,
        baseAttackMin,
        baseAttackMax,
        baseStr,
        baseAgi,
        baseInt,
        strGain,
        agiGain,
        intGain,
        attackRange,
        moveSpeed,
        legs
    )
}


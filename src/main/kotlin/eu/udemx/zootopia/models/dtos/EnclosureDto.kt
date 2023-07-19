package eu.udemx.zootopia.models.dtos

import eu.udemx.zootopia.models.entities.EnclosureEntity

data class EnclosureDto(
    val name: String
)

fun EnclosureDto.toEntity() = EnclosureEntity(name = name)
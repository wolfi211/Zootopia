package eu.udemx.zootopia.models.dtos

import eu.udemx.zootopia.models.entities.SpeciesEntity

data class SpeciesDto(
    val name: String,
    val commonName: String
)

fun SpeciesDto.toEntity() = SpeciesEntity(name = name, commonName = commonName)
package eu.udemx.zootopia.models.dtos

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.services.EnclosureService
import eu.udemx.zootopia.services.SpeciesService

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = CarnivoreDto::class, name = "CARNIVORE"),
    JsonSubTypes.Type(value = HerbivoreDto::class, name = "HERBIVORE"),
    JsonSubTypes.Type(value = OmnivoreDto::class, name = "OMNIVORE"))
interface AnimalDto {
    fun toEntity(
        speciesService: SpeciesService,
        enclosureService: EnclosureService,
    ): AnimalEntity
}
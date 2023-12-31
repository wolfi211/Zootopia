package eu.udemx.zootopia.models.dtos

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.CarnivoreEntity
import eu.udemx.zootopia.services.EnclosureService
import eu.udemx.zootopia.services.SpeciesService

data class CarnivoreDto(
    val name: String,
    val species: Long,
    val size: Double,
    val age: Short,
    val color: String,
    val pattern: String,
    val enclosure: Long,
    val preys: List<Long>
): AnimalDto {

    override fun toEntity(speciesService: SpeciesService, enclosureService: EnclosureService): AnimalEntity {
        val animal = CarnivoreEntity()
        animal.name = name
        animal.size = size
        animal.age = age
        animal.color = color
        animal.pattern = pattern
        animal.species = speciesService.getSpeciesById(species)
        animal.enclosure = enclosureService.getEnclosureById(enclosure)
        preys.map { preyId ->
            speciesService.getSpeciesById(preyId)?.let { animal.preys.add(it) }
        }
        return animal
    }
}
package eu.udemx.zootopia.models.dtos

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.OmnivoreEntity
import eu.udemx.zootopia.models.repositories.EnclosureRepository
import eu.udemx.zootopia.models.repositories.SpeciesRepository
import eu.udemx.zootopia.services.EnclosureService
import eu.udemx.zootopia.services.SpeciesService
import org.springframework.data.repository.findByIdOrNull

data class OmnivoreDto(
    val name: String,
    val species: Long,
    val size: Double,
    val age: Short,
    val color: String,
    val pattern: String,
    val enclosure: Long,
    val dietList: List<String>,
    val preys: Set<Long>
): AnimalDto {

    override fun toEntity(speciesService: SpeciesService, enclosureService: EnclosureService): AnimalEntity {
        val animal = OmnivoreEntity()
        animal.name = name
        animal.size = size
        animal.age = age
        animal.color = color
        animal.pattern = pattern
        animal.species = speciesService.getSpeciesById(species)
        animal.enclosure = enclosureService.getEnclosureById(enclosure)
        animal.dietList = dietList
        preys.map { preyId ->
            animal.preys.plus(speciesService.getSpeciesById(preyId))
        }
        return animal
    }
}

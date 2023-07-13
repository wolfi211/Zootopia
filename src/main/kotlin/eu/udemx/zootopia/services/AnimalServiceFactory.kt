package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.enums.AnimalType
import org.springframework.stereotype.Service

@Service
class AnimalServiceFactory(
    val animalService: AnimalService,
    val carnivoreService: CarnivoreService,
    val herbivoreService: HerbivoreService,
    val omnivoreService: OmnivoreService
) {
    fun getService(type: AnimalType): AnimalService {
        return when (type) {
            AnimalType.CARNIVORE -> carnivoreService
            AnimalType.HERBIVORE -> herbivoreService
            AnimalType.OMNIVORE -> omnivoreService
            AnimalType.ALL -> animalService
        }
    }
}
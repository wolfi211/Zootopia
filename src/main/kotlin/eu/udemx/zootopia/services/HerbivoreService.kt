package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.repositories.EnclosureRepository
import eu.udemx.zootopia.models.repositories.HerbivoreRepository
import org.springframework.stereotype.Service

@Service
class HerbivoreService(
    override val animalRepository: HerbivoreRepository,
    enclosureRepository: EnclosureRepository
): AnimalService(
    animalRepository,
    enclosureRepository
)
package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.repositories.CarnivoreRepository
import eu.udemx.zootopia.models.repositories.EnclosureRepository
import org.springframework.stereotype.Service

@Service
class CarnivoreService(override val animalRepository: CarnivoreRepository,
                       enclosureRepository: EnclosureRepository
) : AnimalService(
    animalRepository,
    enclosureRepository
)
package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.repositories.EnclosureRepository
import eu.udemx.zootopia.models.repositories.OmnivoreRepository
import org.springframework.stereotype.Service

@Service
class OmnivoreService(
    override val animalRepository: OmnivoreRepository,
    enclosureRepository: EnclosureRepository
): AnimalService(
    animalRepository,
    enclosureRepository
)
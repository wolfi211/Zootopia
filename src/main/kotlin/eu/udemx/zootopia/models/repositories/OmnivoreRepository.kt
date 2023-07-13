package eu.udemx.zootopia.models.repositories

import eu.udemx.zootopia.models.entities.OmnivoreEntity
import org.springframework.stereotype.Repository

@Repository
interface OmnivoreRepository: AnimalRepository<OmnivoreEntity>
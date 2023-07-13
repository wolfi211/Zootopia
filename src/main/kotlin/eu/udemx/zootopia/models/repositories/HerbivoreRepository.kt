package eu.udemx.zootopia.models.repositories

import eu.udemx.zootopia.models.entities.HerbivoreEntity
import org.springframework.stereotype.Repository

@Repository
interface HerbivoreRepository: AnimalRepository<HerbivoreEntity> {
}
package eu.udemx.zootopia.models.repositories

import eu.udemx.zootopia.models.entities.AnimalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository: JpaRepository<AnimalEntity, Long> {
    fun findAllByEnclosureId(id: Long): List<AnimalEntity>
}
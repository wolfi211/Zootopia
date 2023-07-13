package eu.udemx.zootopia.models.repositories

import eu.udemx.zootopia.models.entities.AnimalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository<out T: AnimalEntity>: JpaRepository<@UnsafeVariance T, Long> {
    fun findAllByEnclosureId(id: Long): List<AnimalEntity>
}
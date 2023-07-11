package eu.udemx.zootopia.models.repositories

import eu.udemx.zootopia.models.entities.EnclosureEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnclosureRepository: JpaRepository<EnclosureEntity, Long>
package eu.udemx.zootopia.models.repositories

import eu.udemx.zootopia.models.entities.SpeciesEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpeciesRepository: JpaRepository<SpeciesEntity, Long>
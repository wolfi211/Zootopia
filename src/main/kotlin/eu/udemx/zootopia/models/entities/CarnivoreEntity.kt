package eu.udemx.zootopia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity
open class CarnivoreEntity(
    @ManyToMany val preyList: List<AnimalEntity> = emptyList()
) : AnimalEntity()

package eu.udemx.zootopia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity
open class HerbivoreEntity(
    val dietList: List<String> = emptyList()
) : AnimalEntity()
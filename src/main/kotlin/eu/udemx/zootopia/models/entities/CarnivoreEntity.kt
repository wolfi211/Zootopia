package eu.udemx.zootopia.models.entities

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity
@DiscriminatorValue("CARNIVORE")
data class CarnivoreEntity(
    @ManyToMany val preyList: List<AnimalEntity> = emptyList()
) : AnimalEntity()

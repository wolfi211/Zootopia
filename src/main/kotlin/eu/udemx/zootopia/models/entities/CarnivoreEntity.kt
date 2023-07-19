package eu.udemx.zootopia.models.entities

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
@DiscriminatorValue("CARNIVORE")
open class CarnivoreEntity(
    @ManyToMany
    @JoinTable(
        name = "prey_predators",
        joinColumns = [JoinColumn(name = "predator")],
        inverseJoinColumns = [JoinColumn(name = "prey")]
    )
    open val preys: MutableList<SpeciesEntity> = emptyList<SpeciesEntity>().toMutableList()
) : AnimalEntity()

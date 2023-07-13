package eu.udemx.zootopia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class SpeciesEntity(
    @Id var id: Long = 0,
    var name: String = "",
    @OneToMany val animals: List<AnimalEntity> = emptyList(),
)

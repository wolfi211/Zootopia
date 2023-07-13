package eu.udemx.zootopia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "species")
data class SpeciesEntity(
    @Id var id: Long = 0,
    var name: String = "",
    @OneToMany @JoinColumn(name = "species_id") val animals: List<AnimalEntity> = emptyList(),
)

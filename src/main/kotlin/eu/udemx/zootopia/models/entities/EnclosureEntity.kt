package eu.udemx.zootopia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class EnclosureEntity (
    @Id val id: Long = 0,
    val name: String = "",
    val type: String = "",
    @OneToMany val animalsContained: List<AnimalEntity> = emptyList()
)
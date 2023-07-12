package eu.udemx.zootopia.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class EnclosureEntity (
    @Id var id: Long = 0,
    var name: String = "",
    var type: String = "",
    @OneToMany val animalsContained: List<AnimalEntity> = emptyList()
)
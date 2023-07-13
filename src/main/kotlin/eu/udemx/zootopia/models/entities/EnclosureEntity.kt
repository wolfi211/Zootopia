package eu.udemx.zootopia.models.entities

import jakarta.persistence.*

@Entity
@Table(name = "enclosures")
data class EnclosureEntity (
    @Id var id: Long = 0,
    var name: String = "",
    var type: String = "",
    @OneToMany @JoinColumn(name = "enclosure_id") val animalsContained: List<AnimalEntity> = emptyList()
)
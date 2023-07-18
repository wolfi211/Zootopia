package eu.udemx.zootopia.models.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import eu.udemx.zootopia.services.utils.EnclosureSerializer
import jakarta.persistence.*

@Entity
@Table(name = "enclosures")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id")
@JsonSerialize(using = EnclosureSerializer::class)
data class EnclosureEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String = "",
    @OneToMany
    @JoinColumn(name = "enclosure_id")
    val animalsContained: List<AnimalEntity> = emptyList()
)
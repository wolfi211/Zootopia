package eu.udemx.zootopia.models.entities

import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class AnimalEntity(
    @Id val id: Long = 0,
    open val name: String = "",
    //@Column(name = "legs") open val numberOfLegs: Short = 0,
    open val size: Short = 0,
    open val age: Short = 0,
    open val color: String = "",
    open val pattern: String = "",
    @ManyToMany open val predatorList: List<AnimalEntity> = emptyList(),
    @ManyToOne @Nullable open val enclosure: EnclosureEntity? = null
)

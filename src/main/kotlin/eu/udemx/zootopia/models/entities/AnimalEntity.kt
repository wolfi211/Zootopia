package eu.udemx.zootopia.models.entities

import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class AnimalEntity(
    @Id var id: Long = 0,
    open var name: String = "",
    open var species: String = "",
    //@Column(name = "legs") open var numberOfLegs: Short = 0,
    open var size: Short = 0,
    open var age: Short = 0,
    open var color: String = "",
    open var pattern: String = "",
    @ManyToMany open val predatorList: Set<AnimalEntity> = emptySet(),
    @ManyToOne @Nullable open var enclosure: EnclosureEntity? = null
)

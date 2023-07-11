package eu.udemx.zootopia.models.entities

import jakarta.persistence.*
import java.awt.Color

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class AnimalEntity(
    @Id val id: Long = 0,
    open val name: String = "",
    @Column(name = "legs") open val numberOfLegs: Short = 0,
    open val size: Short = 0,
    open val color: Color = Color.WHITE,
    open val pattern: String = "",
    open val feedingInterval: Byte = 0,
    @ManyToMany open val predatorList: List<AnimalEntity> = emptyList()
)

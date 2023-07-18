package eu.udemx.zootopia.models.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = CarnivoreEntity::class, name = "CARNIVORE"),
    JsonSubTypes.Type(value = HerbivoreEntity::class, name = "HERBIVORE"),
    JsonSubTypes.Type(value = OmnivoreEntity::class, name = "OMNIVORE"))
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id",
    )
abstract class AnimalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = 0,
    open var name: String = "",
    @ManyToOne
    @JoinColumn(name = "species_id")
    open var species: SpeciesEntity? = null,
    open var size: Double = 0.0,
    open var age: Short = 0,
    open var color: String = "",
    open var pattern: String = "",
    @ManyToOne
    @JoinColumn(name = "enclosure_id")
    @Nullable
    open var enclosure: EnclosureEntity? = null
)

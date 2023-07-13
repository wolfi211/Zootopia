package eu.udemx.zootopia.models.entities

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
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
    JsonSubTypes.Type(value = OmnivoreEntity::class, name = "OMNIVORE")
)
abstract class AnimalEntity(
    @Id
    open var id: Long = 0,
    open var name: String = "",
    @ManyToOne
    @JoinColumn(name = "species_id")
    open var species: SpeciesEntity? = null,
    open var size: Short = 0,
    open var age: Short = 0,
    open var color: String = "",
    open var pattern: String = "",
    @ManyToMany
    @JoinTable(
        name = "prey_predators",
        joinColumns = [JoinColumn(name = "prey_id")],
        inverseJoinColumns = [JoinColumn(name = "predator_id")])
    open val predators: Set<AnimalEntity> = emptySet(),
    @ManyToOne
    @JoinColumn(name = "enclosure_id")
    @Nullable
    open var enclosure: EnclosureEntity? = null
)

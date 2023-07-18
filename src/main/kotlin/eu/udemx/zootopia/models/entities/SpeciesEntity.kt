package eu.udemx.zootopia.models.entities

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*

@Entity
@Table(name = "species")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id")
data class SpeciesEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    var name: String = "",
    @Column(name = "common_name")
    var commonName: String = "",
    @OneToMany
    @JoinColumn(name = "species_id")
    @JsonIgnore
    val animals: List<AnimalEntity> = emptyList(),
    @ManyToMany
    @JoinTable(
        name = "prey_predators",
        joinColumns = [JoinColumn(name = "prey_id")],
        inverseJoinColumns = [JoinColumn(name = "predator_id")])
    @JsonIgnore
    open val predators: Set<AnimalEntity> = emptySet(),
)

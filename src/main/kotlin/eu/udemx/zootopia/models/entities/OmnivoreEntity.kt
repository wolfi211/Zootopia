package eu.udemx.zootopia.models.entities

import eu.udemx.zootopia.services.StringListConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity
@DiscriminatorValue("OMNIVORE")
data class OmnivoreEntity(
    @ManyToMany
    val preyList: List<AnimalEntity> = emptyList(),
    @Column(name = "herbivorediet")
    @Convert(converter = StringListConverter::class)
    val dietList: List<String> = emptyList()
): AnimalEntity()
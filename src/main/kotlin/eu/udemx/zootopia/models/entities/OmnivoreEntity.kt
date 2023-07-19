package eu.udemx.zootopia.models.entities

import eu.udemx.zootopia.services.utils.StringListConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity
@DiscriminatorValue("OMNIVORE")
data class OmnivoreEntity(
    @Column(name = "herbivorediet")
    @Convert(converter = StringListConverter::class)
    var dietList: List<String> = emptyList()
): CarnivoreEntity()
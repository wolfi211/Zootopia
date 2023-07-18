package eu.udemx.zootopia.models.enums

import eu.udemx.zootopia.models.dtos.AnimalDto
import eu.udemx.zootopia.models.dtos.CarnivoreDto
import eu.udemx.zootopia.models.dtos.HerbivoreDto
import eu.udemx.zootopia.models.dtos.OmnivoreDto

enum class AnimalType {
    CARNIVORE, HERBIVORE, OMNIVORE, ALL;

    companion object {
        fun byNameIgnoreCaseOrNull(input: String): AnimalType? =
            values().firstOrNull { it.name.equals(input, true) }

        fun fromDtoClassOrNull(input: AnimalDto): AnimalType? {
            return when(input::class) {
                CarnivoreDto::class -> CARNIVORE
                HerbivoreDto::class -> HERBIVORE
                OmnivoreDto::class -> OMNIVORE
                else -> null
            }
        }
    }
}

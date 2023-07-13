package eu.udemx.zootopia.models.enums

enum class AnimalType {
    CARNIVORE, HERBIVORE, OMNIVORE

    companion object {
        fun byNameIgnoreCaseOrNull(input: String): AnimalType? = values().firstOrNull { it.name.equals(input, true) }
    }
}

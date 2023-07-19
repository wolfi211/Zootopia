package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.CarnivoreEntity
import eu.udemx.zootopia.models.entities.EnclosureEntity
import eu.udemx.zootopia.models.repositories.AnimalRepository
import eu.udemx.zootopia.models.repositories.EnclosureRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AnimalService(
    val animalRepository: AnimalRepository<AnimalEntity>,
    val enclosureRepository: EnclosureRepository
) {

    fun getAllAnimals(): List<AnimalEntity> = animalRepository.findAll()

    fun getAnimalById(id: Long): AnimalEntity = animalRepository.findByIdOrNull(id) ?:
        throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getAnimalsByEnclosure(id: Long): List<AnimalEntity> {
        return if(enclosureRepository.existsById(id)) animalRepository.findAllByEnclosureId(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun newAnimal(animal: AnimalEntity): AnimalEntity {
        return if(isEligibleForEnclosure(animal)) animalRepository.save(animal)
        else throw ResponseStatusException(HttpStatus.CONFLICT)
    }

    fun removeAnimal(id: Long) {
        if (animalRepository.existsById(id)) animalRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun updateAnimal(id: Long, animal: AnimalEntity): AnimalEntity {
        return if (animalRepository.existsById(id)) {
            if(isEligibleForEnclosure(animal)) {
                animal.id = id
                animalRepository.save(animal)
            }
            else throw ResponseStatusException(HttpStatus.CONFLICT)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun isEligibleForEnclosure(animal: AnimalEntity): Boolean {
        val enclosure = animal.enclosure ?: return true

        if(isHuntedInEnclosure(enclosure, animal)) return false

        return !(CarnivoreEntity::class.java.isAssignableFrom(animal::class.java) &&
                isAnimalHuntingInEnclosure(enclosure, animal as CarnivoreEntity))
    }

    fun isHuntedInEnclosure(enclosure: EnclosureEntity, animal: AnimalEntity): Boolean {
        for(containedAnimal in enclosure.animalsContained) {
            if(CarnivoreEntity::class.java.isAssignableFrom(containedAnimal::class.java) &&
                isAnimalHuntingOther(containedAnimal as CarnivoreEntity, animal))
                return true
        }
        return false
    }

    fun isAnimalHuntingOther(hunter: CarnivoreEntity, other: AnimalEntity): Boolean {
        for(prey in hunter.preys) {
            if(other.species!!.id == prey.id) return true
        }
        return false
    }

    fun isAnimalHuntingInEnclosure(enclosure: EnclosureEntity, animal: CarnivoreEntity): Boolean {
        for(containedAnimal in enclosure.animalsContained) {
            if(isAnimalHuntingOther(animal, containedAnimal)) return true
        }
        return false
    }
}
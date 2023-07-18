package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.CarnivoreEntity
import eu.udemx.zootopia.models.entities.OmnivoreEntity
import eu.udemx.zootopia.models.enums.AnimalType
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
        return if(isNotEligibleForEnclosure(animal)) animalRepository.save(animal)
        else throw ResponseStatusException(HttpStatus.CONFLICT)
    }

    fun removeAnimal(id: Long) {
        if (animalRepository.existsById(id)) animalRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun updateAnimal(id: Long, animal: AnimalEntity): AnimalEntity {
        return if (animalRepository.existsById(id)) {
            animal.id = id
            animalRepository.save(animal)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun isNotEligibleForEnclosure(animal: AnimalEntity): Boolean {
        val enclosure = animal.enclosure ?: return true

        for(containedAnimal in enclosure.animalsContained) {
            if(AnimalType.fromEntityClassOrNull(containedAnimal) == AnimalType.CARNIVORE) {
                for(prey in (containedAnimal as CarnivoreEntity).preys) {
                    if(animal.species!!.id == prey.id) return false
                }
            } else if (AnimalType.fromEntityClassOrNull(containedAnimal) == AnimalType.OMNIVORE) {
                for(prey in (containedAnimal as OmnivoreEntity).preys) {
                    if(animal.species!!.id == prey.id) return false
                }
            }
        }

        if(AnimalType.fromEntityClassOrNull(animal) == AnimalType.CARNIVORE) {
            for(prey in (animal as CarnivoreEntity).preys) {
                for(containedAnimal in enclosure.animalsContained) {
                    if(prey.id == containedAnimal.species!!.id) return false
                }
            }
        } else if(AnimalType.fromEntityClassOrNull(animal) == AnimalType.OMNIVORE) {
            for(prey in (animal as OmnivoreEntity).preys) {
                for(containedAnimal in enclosure.animalsContained) {
                    if(prey.id == containedAnimal.species!!.id) return false
                }
            }
        }

        return true
    }
}
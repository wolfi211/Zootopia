package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.entities.AnimalEntity
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

    fun newAnimal(animal: AnimalEntity): AnimalEntity = animalRepository.save(animal)

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
}
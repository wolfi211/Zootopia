package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.EnclosureEntity
import eu.udemx.zootopia.models.repositories.AnimalRepository
import eu.udemx.zootopia.models.repositories.EnclosureRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ZootopiaService(
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

    fun getAllEnclosures(): List<EnclosureEntity> = enclosureRepository.findAll()

    fun getEnclosureById(id: Long): EnclosureEntity = enclosureRepository.findByIdOrNull(id) ?:
        throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun newEnclosure(enclosure: EnclosureEntity): EnclosureEntity = enclosureRepository.save(enclosure)

    fun removeEnclosure(id: Long) {
        if(enclosureRepository.existsById(id)) enclosureRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun updateEnclosure(id: Long, enclosure: EnclosureEntity): EnclosureEntity {
        return if(animalRepository.existsById(id)) {
            enclosure.id = id
            enclosureRepository.save(enclosure)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
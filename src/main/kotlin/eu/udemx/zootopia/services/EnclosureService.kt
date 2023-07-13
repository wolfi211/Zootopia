package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.entities.EnclosureEntity
import eu.udemx.zootopia.models.repositories.EnclosureRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EnclosureService(
    val enclosureRepository: EnclosureRepository
) {

    fun getAllEnclosures(): List<EnclosureEntity> = enclosureRepository.findAll()

    fun getEnclosureById(id: Long): EnclosureEntity = enclosureRepository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun newEnclosure(enclosure: EnclosureEntity): EnclosureEntity = enclosureRepository.save(enclosure)

    fun removeEnclosure(id: Long) {
        if(enclosureRepository.existsById(id)) enclosureRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun updateEnclosure(id: Long, enclosure: EnclosureEntity): EnclosureEntity {
        return if(enclosureRepository.existsById(id)) {
            enclosure.id = id
            enclosureRepository.save(enclosure)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
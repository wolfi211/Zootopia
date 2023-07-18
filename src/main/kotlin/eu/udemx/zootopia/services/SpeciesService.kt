package eu.udemx.zootopia.services

import eu.udemx.zootopia.models.entities.SpeciesEntity
import eu.udemx.zootopia.models.repositories.SpeciesRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SpeciesService (
    val speciesRepository: SpeciesRepository
){
    fun getAllSpecies(): List<SpeciesEntity> = speciesRepository.findAll()

    fun getSpeciesById(id: Long): SpeciesEntity? = speciesRepository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun newSpecies(species: SpeciesEntity) = speciesRepository.save(species)

    fun removeSpecies(id: Long) {
        if(speciesRepository.existsById(id)) speciesRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun updateSpecies(id: Long, species: SpeciesEntity): SpeciesEntity {
        return if(speciesRepository.existsById(id)) {
            species.id = id
            speciesRepository.save(species)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
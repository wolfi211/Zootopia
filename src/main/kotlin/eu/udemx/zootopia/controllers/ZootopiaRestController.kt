package eu.udemx.zootopia.controllers

import eu.udemx.zootopia.models.dtos.AnimalDto
import eu.udemx.zootopia.models.entities.*
import eu.udemx.zootopia.models.enums.AnimalType
import eu.udemx.zootopia.services.AnimalService
import eu.udemx.zootopia.services.AnimalServiceFactory
import eu.udemx.zootopia.services.EnclosureService
import eu.udemx.zootopia.services.SpeciesService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ZootopiaRestController (
    val animalService: AnimalService,
    val enclosureService: EnclosureService,
    val animalServiceFactory: AnimalServiceFactory,
    val speciesService: SpeciesService,
) {

    @GetMapping("/animals")
    fun getAllAnimals() = animalService.getAllAnimals()

    @GetMapping("/animals/{id}")
    fun getAnimalById(@PathVariable id: Long) = animalService.getAnimalById(id)

    @GetMapping("/animals/type/{typeString}")
    fun getAnimalByType(@PathVariable typeString: String): List<AnimalEntity> {
        val type = AnimalType.byNameIgnoreCaseOrNull(typeString)
        val service = type?.let { animalServiceFactory.getService(it) }
        return service?.getAllAnimals() ?: emptyList()
    }

    @GetMapping("/enclosures")
    fun getAllEnclosures() = enclosureService.getAllEnclosures()

    @GetMapping("/enclosures/{id}")
    fun getEnclosureById(@PathVariable id: Long) = enclosureService.getEnclosureById(id)

    @GetMapping("/enclosures/{id}/animals")
    fun getAnimalsByEnclosure(@PathVariable id: Long) = animalService.getAnimalsByEnclosure(id)

    @GetMapping("/enclosures/{id}/animals/{typeString}")
    fun getAnimalsByEnclosureAndType(@PathVariable id: Long, @PathVariable typeString: String): List<AnimalEntity> {
        val type = AnimalType.byNameIgnoreCaseOrNull(typeString)
        val service = type?.let { animalServiceFactory.getService(it) }
        return service?.getAnimalsByEnclosure(id) ?: emptyList()
    }

    @GetMapping("/species")
    fun getAllSpecies() = speciesService.getAllSpecies()

    @GetMapping("/species/{id}")
    fun getSpeciesById(@PathVariable id: Long) = speciesService.getSpeciesById(id)

    @PostMapping("/animals")
    fun createAnimal(@RequestBody animal: AnimalDto): AnimalEntity? {
        val type = AnimalType.fromDtoClassOrNull(animal)
        return type?.let {
            animalServiceFactory.getService(it)
                .newAnimal(
                    animal.toEntity(speciesService, enclosureService)
                )
        }
    }

    @PostMapping("/enclosures")
    fun createEnclosure(@RequestBody enclosure: EnclosureEntity) = enclosureService.newEnclosure(enclosure)

    @PostMapping("/species")
    fun createSpecies(@RequestBody species: SpeciesEntity) = speciesService.newSpecies(species)

    @DeleteMapping("/animals/{id}")
    fun deleteAnimal(@PathVariable id: Long) = animalService.removeAnimal(id)

    @DeleteMapping("/enclosures/{id}")
    fun deleteEnclosure(@PathVariable id: Long) = enclosureService.removeEnclosure(id)

    @DeleteMapping("/species/{id}")
    fun deleteSpecies(@PathVariable id: Long) = speciesService.removeSpecies(id)

    @PutMapping("/animals/{id}")
    fun updateAnimal(@PathVariable id: Long, @RequestBody animal: AnimalDto): AnimalEntity? {
        val type = AnimalType.fromDtoClassOrNull(animal)
        return type?.let {
            animalServiceFactory.getService(it)
                .updateAnimal(id,
                    animal.toEntity(speciesService, enclosureService)
                )
        }
    }

    @PutMapping("/enclosures/{id}")
    fun updateEnclosure(@PathVariable id: Long, @RequestBody enclosure: EnclosureEntity) =
        enclosureService.updateEnclosure(id, enclosure)

    @PutMapping("/species/{id}")
    fun updateSpecies(@PathVariable id: Long, @RequestBody species: SpeciesEntity) =
        speciesService.updateSpecies(id, species)
}
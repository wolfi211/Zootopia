package eu.udemx.zootopia.controllers

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.EnclosureEntity
import eu.udemx.zootopia.models.enums.AnimalType
import eu.udemx.zootopia.services.AnimalService
import eu.udemx.zootopia.services.AnimalServiceFactory
import eu.udemx.zootopia.services.EnclosureService
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
    val animalServiceFactory: AnimalServiceFactory
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

    @PostMapping("/animals")
    fun createAnimal(@RequestBody animal: AnimalEntity): AnimalEntity = animalService.newAnimal(animal)

    @PostMapping("/enclosures")
    fun createEnclosure(@RequestBody enclosure: EnclosureEntity): EnclosureEntity = enclosureService.newEnclosure(enclosure)

    @DeleteMapping("/animals/{id}")
    fun deleteAnimal(@PathVariable id: Long) = animalService.removeAnimal(id)

    @DeleteMapping("/enclosures/{id}")
    fun deleteEnclosure(@PathVariable id: Long) = enclosureService.removeEnclosure(id)

    @PutMapping("/animals/{id}")
    fun updateAnimal(@PathVariable id: Long, @RequestBody animal: AnimalEntity) = animalService.updateAnimal(id, animal)

    @PutMapping("/enclosures/{id}")
    fun updateEnclosure(@PathVariable id: Long, @RequestBody enclosure: EnclosureEntity) = enclosureService.updateEnclosure(id, enclosure)
}
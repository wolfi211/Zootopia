package eu.udemx.zootopia.controllers

import eu.udemx.zootopia.models.entities.AnimalEntity
import eu.udemx.zootopia.models.entities.EnclosureEntity
import eu.udemx.zootopia.services.ZootopiaService
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
    val zootopiaService: ZootopiaService
) {

    @GetMapping("/animals")
    fun getAllAnimals() = zootopiaService.getAllAnimals()

    @GetMapping("/animals/{id}")
    fun getAnimalById(@PathVariable id: Long) = zootopiaService.getAnimalById(id)

    @GetMapping("/enclosures")
    fun getAllEnclosures() = zootopiaService.getAllEnclosures()

    @GetMapping("/enclosures/{id}")
    fun getEnclosureById(@PathVariable id: Long) = zootopiaService.getEnclosureById(id)

    @GetMapping("/enclosures/{id}/animals")
    fun getAnimalsByEnclosure(@PathVariable id: Long) = zootopiaService.getAnimalsByEnclosure(id)

    @PostMapping("/animals")
    fun createAnimal(@RequestBody animal: AnimalEntity): AnimalEntity = zootopiaService.newAnimal(animal)

    @PostMapping("/enclosures")
    fun createEnclosure(@RequestBody enclosure: EnclosureEntity): EnclosureEntity = zootopiaService.newEnclosure(enclosure)

    @DeleteMapping("/animals/{id}")
    fun deleteAnimal(@PathVariable id: Long) = zootopiaService.removeAnimal(id)

    @DeleteMapping("/enclosures/{id}")
    fun deleteEnclosure(@PathVariable id: Long) = zootopiaService.removeEnclosure(id)

    @PutMapping("/animals/{id}")
    fun updateAnimal(@PathVariable id: Long, @RequestBody animal: AnimalEntity) = zootopiaService.updateAnimal(id, animal)

    @PutMapping("/enclosures/{id}")
    fun updateEnclosure(@PathVariable id: Long, @RequestBody enclosure: EnclosureEntity) = zootopiaService.updateEnclosure(id, enclosure)
}
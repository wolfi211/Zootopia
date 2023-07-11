package eu.udemx.zootopia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZootopiaApplication

fun main(args: Array<String>) {
    runApplication<ZootopiaApplication>(*args)
}

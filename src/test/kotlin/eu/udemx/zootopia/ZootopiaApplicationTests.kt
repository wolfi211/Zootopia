package eu.udemx.zootopia

import eu.udemx.zootopia.controllers.ZootopiaRestController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ZootopiaApplicationTests(
    @Autowired val controller: ZootopiaRestController
) {
    @Test
    fun contextLoads() {
        assertThat(controller).isNotNull()
    }

}

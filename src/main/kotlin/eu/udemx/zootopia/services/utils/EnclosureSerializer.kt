package eu.udemx.zootopia.services.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import eu.udemx.zootopia.models.entities.EnclosureEntity
import java.io.IOException

class EnclosureSerializer @JvmOverloads constructor(t: Class<EnclosureEntity?>? = null) :
    StdSerializer<EnclosureEntity>(t) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(
        value: EnclosureEntity, jgen: JsonGenerator, provider: SerializerProvider
    ) {
        jgen.writeStartObject()
        jgen.writeNumberField("id", value.id)
        jgen.writeStringField("name", value.name)
        jgen.writeFieldName("animalsContained")
        jgen.writeStartArray()
        value.animalsContained.map { animal ->
            animal.id?.let { jgen.writeNumber(it) }
        }
        jgen.writeEndArray()
        jgen.writeEndObject()
    }
}
package eu.udemx.zootopia.services.utils

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringListConverter: AttributeConverter<List<String>, String> {
    val SPLIT_CHAR = " "

    override fun convertToDatabaseColumn(stringList: List<String> ): String {
        return if(stringList.isNotEmpty()) stringList.joinToString(SPLIT_CHAR) else ""
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        return if(!dbData.isNullOrEmpty()) dbData.split(SPLIT_CHAR) else emptyList()
    }
}
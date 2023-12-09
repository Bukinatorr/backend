package xyz.bukinator.house.model.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringToListConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(attributes: List<String>?): String? {
        return attributes?.joinToString(COMMA)
    }

    override fun convertToEntityAttribute(dbData: String?): List<String>? {
        return dbData?.split(COMMA)?.map { it.trim() }
    }

    companion object {
        private const val COMMA: String = ","
    }
}

package nocast.storeservice.category.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.persistence.CategoryInfo;

import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
@Converter
public class CategoryInfoJsonConverter implements AttributeConverter<Map<String, CategoryInfo>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, CategoryInfo> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting %s to JSON".formatted(CategoryInfo.class.getCanonicalName()), e
            );
        }
    }

    @Override
    public Map<String, CategoryInfo> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting JSON to %s".formatted(CategoryInfo.class.getCanonicalName()), e
            );
        }
    }
}

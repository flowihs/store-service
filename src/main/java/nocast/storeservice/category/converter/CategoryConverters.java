package nocast.storeservice.category.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import nocast.storeservice.category.persistence.CategoryInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.NonNull;

import java.util.Map;

public class CategoryConverters {

    @RequiredArgsConstructor
    @WritingConverter
    public static class MapToJsonConverter implements Converter<Map<String, CategoryInfo>, Json> {

        private final ObjectMapper mapper;

        @Override
        public Json convert(@NonNull Map<String, CategoryInfo> source) {
            try {
                return Json.of(mapper.writeValueAsString(source));
            } catch (Exception e) {
                throw new RuntimeException("Failed to serialize JSON", e);
            }
        }
    }

    @RequiredArgsConstructor
    @ReadingConverter
    public static class JsonToMapConverter implements Converter<Json, Map<String, CategoryInfo>> {

        private final ObjectMapper mapper;

        @Override
        public Map<String, CategoryInfo> convert(@NonNull Json source) {
            try {
                return mapper.readValue(source.asString(),
                        new TypeReference<>() {});
            } catch (Exception e) {
                throw new RuntimeException("Failed to deserialize JSON", e);
            }
        }
    }
}
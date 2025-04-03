package nocast.storeservice.category.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nocast.storeservice.category.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

@Configuration
public class R2dbcConfig {

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions(ObjectMapper objectMapper) {
        return R2dbcCustomConversions.of(
                PostgresDialect.INSTANCE,
                List.of(
                        new CategoryConverters.JsonToMapConverter(objectMapper),
                        new CategoryConverters.MapToJsonConverter(objectMapper)
                )
        );
    }
}

package nocast.storeservice.category.mapper;

import lombok.NonNull;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

public interface RecursiveMapper<F, T> {

    T map(@NonNull F object, int depth);
}

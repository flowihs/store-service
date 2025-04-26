package nocast.storeservice.common.components;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */

public interface Mapper<F, T> {

    T map(F object);

    default T map(F from, T to) {
        return to;
    }
}

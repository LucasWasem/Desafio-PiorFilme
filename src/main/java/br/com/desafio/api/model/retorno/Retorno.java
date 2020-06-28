package br.com.desafio.api.model.retorno;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Classe usada para tratamento de retorno na maioria dos métodos.
 *
 * @author lucas
 * @since 27/06/202
 */
@Data
@Accessors(chain = true)
public class Retorno<T> {

    private String message;
    private Boolean ok;
    private List<T> objs;

    public static Retorno ok(List list) {
        return new Retorno("ok", true, list);
    }

    public static Retorno ok(Object obj) {
        return new Retorno("ok", true, Arrays.asList(obj));
    }

    public static Retorno ok(String message) {
        return new Retorno(message, true, null);
    }

    public static Retorno noOk(String message, List list) {
        return new Retorno(message, false, list);
    }

    public static Retorno noOk(String message, Object obj) {
        return new Retorno(message, false, Arrays.asList(obj));
    }

    public static Retorno noOk(String message) {
        return new Retorno(message, false, null);
    }

    public Retorno() {}
    public Retorno(String message, Boolean ok, List objs) {
        this.message = message;
        this.objs = objs;
        this.ok = ok;
    }
}

/**
 * Fernando Ruíz 23065
 * Erick Guerra 23208
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que crea instancias de la clase manejadorCartas según la elección del usuario.
 */
public class cartasFactory {
    /**
     * Crea una instancia de manejadorCartas según la elección del usuario.
     * @param choice Elección del usuario (1 para HashMap, 2 para TreeMap, 3 para LinkedHashMap).
     * @return Instancia de manejadorCartas.
     */
    public static manejadorCartas createmanejadorCartas(int choice) {
        Map<String, String> cardsMap;
        switch (choice) {
            case 1:
                cardsMap = new HashMap<>();
                break;
            case 2:
                cardsMap = new TreeMap<>();
                break;
            case 3:
                cardsMap = new LinkedHashMap<>();
                break;
            default:
                System.out.println("Opción no válida. Se utilizará HashMap por defecto.");
                cardsMap = new HashMap<>();
                break;
        }
        return new manejadorCartas(cardsMap);
    }
}
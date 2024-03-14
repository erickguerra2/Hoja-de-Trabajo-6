import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class cartasFactory {
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


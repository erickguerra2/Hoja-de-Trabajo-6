/**
 * Fernando Ruíz 23065
 * Erick Guerra 23208
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Clase que maneja las cartas y las colecciones de usuarios.
 */
public class manejadorCartas {
    private Map<String, String> cardsMap;
    private Map<String, Map<String, Integer>> userCollections;

    /**
     * Constructor que inicializa el manejador de cartas con un mapa de cartas proporcionado.
     * @param cardsMap Mapa de cartas.
     */
    public manejadorCartas(Map<String, String> cardsMap) {
        this.cardsMap = cardsMap;
        this.userCollections = new HashMap<>();
    }

    /**
     * Carga las cartas desde un archivo.
     * @param filename Nombre del archivo.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     */
    public void loadCardsFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                cardsMap.put(parts[0].trim(), parts[1].trim());
            }
        }
        scanner.close();
    }

    /**
     * Agrega una carta a la colección de un usuario.
     * @param username Nombre de usuario.
     * @param cardName Nombre de la carta.
     */
    public void addCardToUserCollection(String username, String cardName) {
        if (!userCollections.containsKey(username)) {
            userCollections.put(username, new HashMap<>());
        }
        Map<String, Integer> userCollection = userCollections.get(username);
        userCollection.put(cardName, userCollection.getOrDefault(cardName, 0) + 1);
        System.out.println("Carta agregada correctamente a la colección de " + username + ".");
    }

    /**
     * Muestra el tipo de una carta específica.
     * @param cardName Nombre de la carta.
     */
    public void showCardType(String cardName) {
        if (cardsMap.containsKey(cardName)) {
            System.out.println("Tipo de la carta " + cardName + ": " + cardsMap.get(cardName));
        } else {
            System.out.println("Error: La carta especificada no se encuentra en la colección.");
        }
    }

    /**
     * Muestra la colección de un usuario.
     * @param username Nombre de usuario.
     */
    public void showUserCollection(String username) {
        if (userCollections.containsKey(username)) {
            System.out.println("Colección de " + username + ":");
            Map<String, Integer> userCollection = userCollections.get(username);
            for (Map.Entry<String, Integer> entry : userCollection.entrySet()) {
                System.out.println("Nombre: " + entry.getKey() + ", Cantidad: " + entry.getValue());
            }
        } else {
            System.out.println("No se encontró ninguna colección para el usuario " + username + ".");
        }
    }

    /**
     * Muestra la colección de un usuario ordenada por tipo de carta.
     */
    public void showUserCollectionSortedByType() {
        Map<String, Map<String, Integer>> sortedCollections = new TreeMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : userCollections.entrySet()) {
            Map<String, Integer> userCollection = entry.getValue();

            for (Map.Entry<String, Integer> cardEntry : userCollection.entrySet()) {
                String cardName = cardEntry.getKey();
                String cardType = cardsMap.getOrDefault(cardName, "Desconocido");
                int cardCount = cardEntry.getValue();

                // Crear o actualizar la colección ordenada por tipo
                if (!sortedCollections.containsKey(cardType)) {
                    sortedCollections.put(cardType, new TreeMap<>());
                }
                Map<String, Integer> sortedCollectionByType = sortedCollections.get(cardType);
                sortedCollectionByType.put(cardName, cardCount);
            }
        }

        System.out.println("Colección del usuario ordenada por tipo:");
        for (Map.Entry<String, Map<String, Integer>> typeEntry : sortedCollections.entrySet()) {
            String cardType = typeEntry.getKey();
            Map<String, Integer> sortedCollectionByType = typeEntry.getValue();
            System.out.println("Tipo: " + cardType);
            for (Map.Entry<String, Integer> cardEntry : sortedCollectionByType.entrySet()) {
                String cardName = cardEntry.getKey();
                int cardCount = cardEntry.getValue();
                System.out.println("Nombre: " + cardName + ", Cantidad: " + cardCount);
            }
        }
    }

    /**
     * Muestra todas las cartas disponibles.
     */
    public void showAllCards() {
        System.out.println("Todas las cartas disponibles:");
        for (Map.Entry<String, String> entry : cardsMap.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + entry.getValue());
        }
    }

    /**
     * Muestra todas las cartas disponibles ordenadas por tipo.
     */
    public void showAllCardsSortedByType() {
        List<Map.Entry<String, String>> sortedList = new ArrayList<>(cardsMap.entrySet());
        sortedList.sort(Comparator.comparing(Map.Entry::getValue));

        System.out.println("Todas las cartas disponibles ordenadas por tipo:");
        for (Map.Entry<String, String> entry : sortedList) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + entry.getValue());
        }
    }
}
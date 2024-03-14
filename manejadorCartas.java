import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class manejadorCartas {
    private Map<String, String> cardsMap;
    private Map<String, Map<String, Integer>> userCollections;

    public manejadorCartas(Map<String, String> cardsMap) {
        this.cardsMap = cardsMap;
        this.userCollections = new HashMap<>();
    }

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

    public void addCardToUserCollection(String username, String cardName) {
        if (!userCollections.containsKey(username)) {
            userCollections.put(username, new HashMap<>());
        }
        Map<String, Integer> userCollection = userCollections.get(username);
        userCollection.put(cardName, userCollection.getOrDefault(cardName, 0) + 1);
        System.out.println("Carta agregada correctamente a la colección de " + username + ".");
    }

    public void showCardType(String cardName) {
        if (cardsMap.containsKey(cardName)) {
            System.out.println("Tipo de la carta " + cardName + ": " + cardsMap.get(cardName));
        } else {
            System.out.println("Error: La carta especificada no se encuentra en la colección.");
        }
    }

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

    public void showAllCards() {
        System.out.println("Todas las cartas disponibles:");
        for (Map.Entry<String, String> entry : cardsMap.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + entry.getValue());
        }
    }

    public void showAllCardsSortedByType() {
        List<Map.Entry<String, String>> sortedList = new ArrayList<>(cardsMap.entrySet());
        sortedList.sort(Comparator.comparing(Map.Entry::getValue));

        System.out.println("Todas las cartas disponibles ordenadas por tipo:");
        for (Map.Entry<String, String> entry : sortedList) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + entry.getValue());
        }
    }
}

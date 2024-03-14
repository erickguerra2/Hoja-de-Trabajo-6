import java.util.Scanner;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Seleccione la implementación de Mapa que desea utilizar:");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        int choice = input.nextInt();

        manejadorCartas manager = cartasFactory.createmanejadorCartas(choice);

        try {
            manager.loadCardsFromFile("cards_desc.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo de cartas no encontrado.");
        }

        int option;
        String currentUser = ""; // Usuario actual
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar una carta a tu colección.");
            System.out.println("2. Mostrar el tipo de una carta específica.");
            System.out.println("3. Mostrar tu colección.");
            System.out.println("4. Mostrar tu colección ordenada por tipo.");
            System.out.println("5. Mostrar todas las cartas disponibles.");
            System.out.println("6. Mostrar todas las cartas disponibles ordenadas por tipo.");
            System.out.println("0. Salir.");
            System.out.print("Ingrese la opción deseada: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    input.nextLine(); // Consumir el salto de línea pendiente
                    System.out.print("Ingrese tu nombre de usuario: ");
                    currentUser = input.nextLine();
                    System.out.print("Ingrese el nombre de la carta que deseas agregar a tu colección: ");
                    String cardName = input.nextLine();
                    manager.addCardToUserCollection(currentUser, cardName);
                    break;
                case 2:
                    input.nextLine(); // Consumir el salto de línea pendiente
                    System.out.print("Ingrese el nombre de la carta de la cual deseas conocer el tipo: ");
                    String cardToCheck = input.nextLine();
                    manager.showCardType(cardToCheck);
                    break;
                case 3:
                    manager.showUserCollection(currentUser);
                    break;
                case 4:
                    manager.showUserCollectionSortedByType();
                    break;
                case 5:
                    manager.showAllCards();
                    break;
                case 6:
                    manager.showAllCardsSortedByType();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (option != 0);

        input.close();
    }
}
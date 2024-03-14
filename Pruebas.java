/**
 * Fernando Ruíz 23065
 * Erick Guerra 23208
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Clase que contiene pruebas unitarias para la clase manejadorCartas y cartasFactory.
 */
public class Pruebas {

    private manejadorCartas manager;

    /**
     * Configuración inicial para las pruebas.
     */
    @Before
    public void setUp() {
        Map<String, String> cardsMap = new HashMap<>();
        manager = new manejadorCartas(cardsMap);
    }

    /**
     * Prueba para el método loadCardsFromFile.
     * @throws FileNotFoundException Si el archivo no se encuentra.
     * @throws NoSuchFieldException Si no se encuentra el campo en la clase.
     * @throws IllegalAccessException Si no se puede acceder al campo de la clase.
     */
    @Test
    public void testLoadCardsFromFile() throws FileNotFoundException, NoSuchFieldException, IllegalAccessException {
        manager.loadCardsFromFile("test_cards.txt");

        // Acceder a cardsMap mediante reflexión
        Field cardsMapField = manejadorCartas.class.getDeclaredField("cardsMap");
        cardsMapField.setAccessible(true);
        Map<String, String> cardsMap = (Map<String, String>) cardsMapField.get(manager);

        // Assert
        assertEquals("Card Type", cardsMap.get("Card Name"));
    }

    /**
     * Prueba para el método addCardToUserCollection.
     * @throws NoSuchFieldException Si no se encuentra el campo en la clase.
     * @throws IllegalAccessException Si no se puede acceder al campo de la clase.
     */
    @Test
    public void testAddCardToUserCollection() throws NoSuchFieldException, IllegalAccessException {
        manager.addCardToUserCollection("user1", "card1");

        // Acceder a userCollections mediante reflexión
        Field userCollectionsField = manejadorCartas.class.getDeclaredField("userCollections");
        userCollectionsField.setAccessible(true);
        Map<String, Map<String, Integer>> userCollections = (Map<String, Map<String, Integer>>) userCollectionsField.get(manager);

        // Assert
        assertTrue(userCollections.containsKey("user1"));
        assertEquals(1, userCollections.get("user1").get("card1").intValue());
    }

    /**
     * Prueba para crear un manejadorCartas con HashMap.
     */
    @Test
    public void testCreateManejadorCartasHashMap() {
        manejadorCartas manager = cartasFactory.createmanejadorCartas(1);
        assertNotNull(manager);
        assertTrue(manager instanceof manejadorCartas);
    }

    /**
     * Prueba para crear un manejadorCartas con TreeMap.
     */
    @Test
    public void testCreateManejadorCartasTreeMap() {
        manejadorCartas manager = cartasFactory.createmanejadorCartas(2);
        assertNotNull(manager);
        assertTrue(manager instanceof manejadorCartas);
    }

    /**
     * Prueba para crear un manejadorCartas con LinkedHashMap.
     */
    @Test
    public void testCreateManejadorCartasLinkedHashMap() {
        manejadorCartas manager = cartasFactory.createmanejadorCartas(3);
        assertNotNull(manager);
        assertTrue(manager instanceof manejadorCartas);
    }

    /**
     * Prueba para crear un manejadorCartas con la opción por defecto.
     */
    @Test
    public void testCreateManejadorCartasDefault() {
        manejadorCartas manager = cartasFactory.createmanejadorCartas(4);
        assertNotNull(manager);
        assertTrue(manager instanceof manejadorCartas);
    }
}

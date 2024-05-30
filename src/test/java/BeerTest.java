import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BeerTest {

    @Test
    public void testCreateBeers() {
        // Arrange
        HashMap<String, Integer> beerQuantities = new HashMap<>();
        beerQuantities.put("BeerType1", 10);
        beerQuantities.put("BeerType2", 20);

        HashMap<String, Integer> beerStrengths = new HashMap<>();
        beerStrengths.put("BeerType1", 5);
        beerStrengths.put("BeerType2", 7);

        // Act
        Beer.createBeers(beerQuantities, beerStrengths);

        // Assert
        assertEquals(2, Beer.getBeers().size(), "The number of beer types is incorrect");

        for (Beer beer : Beer.getBeers()) {
            assertTrue(beerQuantities.containsKey(beer.getName()), "Unexpected beer type");
            assertEquals(beerQuantities.get(beer.getName()).intValue(), beer.getQuantity(), "The quantity for " + beer.getName() + " is incorrect");
            assertEquals(beerStrengths.get(beer.getName()).intValue(), beer.getStrength(), "The strength for " + beer.getName() + " is incorrect");
        }
    }
}
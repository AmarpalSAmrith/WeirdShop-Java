package training.weirdshop;

import org.junit.Test;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;

public class RegressionTest {
    @Test
    public void itemsChangeInTheSameWayTheyDidBeforeWeMadeAnyChanges() {
        Item[] items = {
                new Item("Aged Brie", 40, 17),
                new Item ("Backstage Pass", 20, 1),
                new Item ("Gold Coin", 5, 80),
                new Item ("Some other item", 5, 17),
                new Item("Premium Aged Brie", 40, 17),
                new Item ("Premium Backstage Pass", 20, 1),
                new Item ("Premium Gold Coin", 5, 80),
                new Item ("Some other Premium item", 5, 17)
        };

        StringBuilder sb = new StringBuilder();
        WeirdShop weirdShop = new WeirdShop(items);

        addItems(sb, 0, items);

        for (int day = 1; day <= 50; day++) {
            weirdShop.updateQuality();
            addItems(sb, day, items);
        }

        String output = sb.toString();

        String expected =  new Scanner(getClass().getResourceAsStream("/expected-output.txt"), "UTF-8").useDelimiter("\\A").next();

        assertThat(output).isEqualTo(expected);
    }

    private void addItems(StringBuilder sb, int step, Item[] items) {
        sb.append(String.format("\nDay %d:\n", step));

        for (Item item : items)
        {
            sb.append(String.format("  %s: Quality %d, Sell In %d\n", item.name, item.quality, item.sellIn));
        }
    }
}

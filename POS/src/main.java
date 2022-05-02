import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.model.ItemInBasket;
import se.kth.iv1350.pos.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class main {
    private static Sale sale;

    private static ItemDTO itemDummy;
    public static List<ItemInBasket> fakeBasket = new ArrayList<>();
    public static void main(String[] args) {



        sale = new Sale();
        itemDummy = new ItemDTO("dummy", 9999, 99.99, 99);
        sale.addItemToBasket(itemDummy, 1);

        int n = sale.getBasket().get(0).getQuantity();

        System.out.println(n);


    }
}

import org.example.Order;
import org.example.models.Shoe;
import org.example.models.Sweater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private Order order;
    @Before
    public void before() {
        order = new Order();
    }
    @Test
    public void addTwoClothes() {
        order.addClothes(new Shoe());


        order.addClothes(new Sweater());
        Assert.assertEquals(2,order.getClothesList().size());
    }
}

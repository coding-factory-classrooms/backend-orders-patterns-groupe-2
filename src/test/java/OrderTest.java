import org.example.Order;
import org.example.SystemLogger;
import org.example.models.Shoe;
import org.example.models.Sweater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class OrderTest {
    private Order order;

    @Before
    public void before() {
        order = new Order();
    }

    @Test
    public void addTwoClothes() {
        order.addClothes(new Shoe("Botte de ferme",50));
        order.addClothes(new Sweater("Manches longues",20));

        Assert.assertEquals(2,order.getClothesList().size());
    }
}

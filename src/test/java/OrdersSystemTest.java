import org.example.Order;
import org.example.OrdersSytem;
import org.example.SystemLogger;
import org.example.models.Shoe;
import org.example.models.Sweater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class OrdersSystemTest {
    private OrdersSytem ordersSystem;

    private SystemLogger logger;

    @Before
    public void before() {
        ordersSystem = new OrdersSytem();
        logger = Mockito.mock(SystemLogger.class);
    }

    //STORY 1 & 2
    @Test
    public void addCommandWithNewStatement() {
        Order order = new Order();
        order.setState(Order.State.IN_PROGRESS);

        order.setState(Order.State.NEW);
        boolean isNewOrder = ordersSystem.addOrder(order);

        Assert.assertTrue(isNewOrder);
        Assert.assertEquals(Order.State.NEW,ordersSystem.getOrdersList().get(0).getState());
        Assert.assertEquals(1,ordersSystem.getOrdersList().size());
    }

    @Test
    public void addCommandWithOutNewStatement() {
        Order order = new Order();
        order.setState(Order.State.IN_PROGRESS);

        boolean isNewOrder = ordersSystem.addOrder(order);

        Assert.assertFalse(isNewOrder);
        Assert.assertEquals(0,ordersSystem.getOrdersList().size());
    }

    //STORY 7
    @Test
    public void verifyLogsAfterAddingClothes() {
        Order order = new Order();
        order.addClothes(new Shoe("Botte de ferme",50));

        ordersSystem.addOrder(order);

        Assert.assertTrue(ordersSystem.getLogs().size() > 0);
    }
}

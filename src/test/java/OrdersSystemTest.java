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
        logger = Mockito.mock(SystemLogger.class);
        ordersSystem = new OrdersSytem(logger);
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
        ordersSystem = new OrdersSytem(new SystemLogger());
        order.addClothes(new Shoe("Botte de ferme",50));

        ordersSystem.addOrder(order);

        Assert.assertEquals(1, ordersSystem.getLogs().size());
    }

    @Test
    public void verifyLogsWhenChangeOrderState() {
        Order order = new Order();
        order.setStateOrderChangedListener(ordersSystem);
        ordersSystem.addOrder(order);
        order.setState(Order.State.PROCESSED);

        Mockito.verify(logger, Mockito.times(2)).addLog(Mockito.anyString());
    }


    @Test
    public void verifyLogsAddedWhenOrderStateChanged() {
        Order order = new Order();
        ordersSystem = new OrdersSytem(new SystemLogger());
        order.setStateOrderChangedListener(ordersSystem);
        ordersSystem.addOrder(order);
        order.setState(Order.State.PROCESSED);

       Assert.assertEquals(2, ordersSystem.getLogs().size());
    }
}

import org.example.Momento;
import org.example.Order;
import org.example.OrdersSytem;
import org.example.SystemLogger;
import org.example.models.Shoe;
import org.example.models.Sweater;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

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

    //STORY 8
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


    //STORY 9
    @Test
    public void UndoWorksWhenLogsWereSaved() {
        ordersSystem = new OrdersSytem(new SystemLogger());
        Order order = new Order();
        order.addClothes(new Shoe("Botte de ferme",50));
        ordersSystem.addOrder(order); //passe le current momento au dernier ajouté au tableau
        ordersSystem.undo(); //liste de momentos avec 2 liste, ce undo passe le current momento a celui qui est avant l'actuel current momento dans le tableau
        ordersSystem.undo(); //quand on undo sur la liste de momento alors qu'elle n'a pas de momento précédent, ce undo ne fait rien

        Assert.assertEquals(0, ordersSystem.getMomento().getLogs().size());



    }
    //STORY 9
    @Test
    public void UndoWorksWhenTwoOrMoreLogsWereSaved() {
        ordersSystem = new OrdersSytem(new SystemLogger());
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        order1.addClothes(new Shoe("Botte de ferme",50));
        order2.addClothes(new Shoe("Sandale",50));
        order3.addClothes(new Shoe("nike",50));
        ordersSystem.addOrder(order1); //passe le current momento au dernier ajouté au tableau
        ordersSystem.addOrder(order2);
        ordersSystem.addOrder(order3);
        System.out.println(ordersSystem.getMomentoList().indexOf(ordersSystem.getMomento()));
        ordersSystem.undo(); //liste de momentos avec 2 liste, ce undo passe le current momento a celui qui est avant l'actuel current momento dans le tableau

        //System.out.println(ordersSystem.getMomento().getLogs());

        //Assert.assertEquals(1, ordersSystem.getMomentoList().indexOf(ordersSystem.getMomento()));

    }


}

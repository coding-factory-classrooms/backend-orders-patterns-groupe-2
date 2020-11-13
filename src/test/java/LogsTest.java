import org.example.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LogsTest {
    private SystemLogger systemLogger;
    private OrdersSytem ordersSystem;
    private Order order;
    private LogsWriter logsWriter;

    @Before
    public void before() {
        systemLogger = new SystemLogger();
        ordersSystem = new OrdersSytem(systemLogger);
        order = new Order();
        logsWriter = new FileLogsWriter();
    }

    //STORY 13
    @Test
    public void verifyFileOpenedAndTryToWrite() {
        ordersSystem.addOrder(order);
        logsWriter.write(systemLogger.getLogsList());
    }

}

package dal.csci5308;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderResponseTest {
    //I test OrderResponse's gets and sets method
    @Test
    @DisplayName("Test gets and sets")
    void test(){
        OrderResponse response = new OrderResponse();
        response.setResult("failure");
        assertEquals("failure",response.getResult());
        response.setError("Invalid order");
        assertEquals("Invalid order",response.getError());
        response.setErrorMessage("Invalid delivery address");
        assertEquals("Invalid delivery address",response.getErrorMessage());
        ArrayList<Item> list = new ArrayList<Item>();
        response.setOrderItems(list);
        assertEquals(list,response.getOrderItems());
    }
}

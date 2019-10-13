package ksch.laboratory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LabQueriesTest {

    @InjectMocks
    private LabQueriesImpl labQueries;

    @Test
    public void should_provide_list_with_all_available_lab_orders_for_visit() {


        List<LabOrder> retrievedLabOrder = labQueries.getLabOrders();




    }
}

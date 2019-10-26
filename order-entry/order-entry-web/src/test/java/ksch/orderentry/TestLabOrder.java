package ksch.orderentry;

import ksch.laboratory.LabOrder;
import ksch.laboratory.LabOrderCode;

import java.util.UUID;

public class TestLabOrder implements LabOrder {

    private final String loincNumber;

    public TestLabOrder(String loincNumber) {
        this.loincNumber = loincNumber;
    }

    @Override
    public UUID getId() {
        return UUID.randomUUID();
    }

    @Override
    public Status getStatus() {
        return Status.NEW;
    }

    @Override
    public LabOrderCode getRequestedTest() {
        return new LabOrderCode(loincNumber);
    }
}

package dal.csci5308;

public class PARTMANAGERMock implements PARTMANAGER {

    //Robert provided some suggestions to me, about how I can set a particular partNumber return OUT_OF_STOCK
    //and a particular partNumber return NO_LONGER_MANUFACTURED, other numbers return SUCCESS
    @Override
    public PartResponse SubmitPartForManufactureAndDelivery(int partNumber, int quantity, DeliveryAddress deliveryAddress) {
        if(partNumber == 1234)
            return PartResponse.OUT_OF_STOCK;

        if(partNumber == 3456)
            return PartResponse.NO_LONGER_MANUFACTURED;

        return PartResponse.SUCCESS;
    }
}

package dal.csci5308;

//the PARTMANAGER interface provided, the DeliveryAddress class has been separated to a new .java file
public interface PARTMANAGER
{
    public enum PartResponse
    {
        SUCCESS,
        OUT_OF_STOCK,
        NO_LONGER_MANUFACTURED
    }

    // Submit part for manufacture and delivery.
    public PartResponse SubmitPartForManufactureAndDelivery(int partNumber, int quantity, DeliveryAddress deliveryAddress);
}
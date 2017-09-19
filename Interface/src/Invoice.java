
public class Invoice implements Payable {

	private String partNumber;
	private String partDescription;
	private int quantity;
	private double pricePerItem;
	
	public Invoice(String part, String des, int count, double price) {
		partNumber = part;
		partDescription = des;
		quantity = count;
		pricePerItem = price;
	}
	
	public void setPartNumber(String s) {
		partNumber = s;
	}
	
	public void setPartDescription(String s) {
		partDescription = s;
	}
	
	public void setQuantity(int q) {
		quantity = (q < 0) ? 0 : q;
	}
	
	public void setPricePerItem(double d) {
		pricePerItem = (d < 0.0) ? 0.0 : d;
	}
		
	public String getPartNumber() {
		return partNumber;
	}
	
	public String getPartDescription() {
		return partDescription;
	}	
	
	public int getQuantity() {
		return quantity;
	}
	
	public double getPricePerItem() {
		return pricePerItem;
	}
	
	@Override
	public String toString() {
		return String.format("%s : \n%s: %s (%s)\n%s: %d \n%s: $%,.2f", "invoice", "part number", getPartNumber(), getPartDescription(),
				"quantity",getQuantity(), "price per item", getPricePerItem());
	}
	
	@Override
	public double getPaymentAmount() {
		// TODO Auto-generated method stub
		return (double) getQuantity() * getPricePerItem();
	}

}

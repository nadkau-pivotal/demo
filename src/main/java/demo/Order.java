package demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;


@Region("transactions")
public class Order implements Serializable {

	@Id
	private String transactionId;
	private String creditCardType;
	private String creditCardNumber;
	private String retailerName;
	private double amount;
	private String street;
	private String city;
	private int zip;
	private String state;
	private String country;
	private double latitude;
	private double longitude;
	private long timestamp;

	@PersistenceConstructor
	Order(String transactionId, String creditCardType, String creditCardNumber,
			String retailerName, double amount, String street, String city,
			int zip, String state, String country, double latitude,
			double longitude, long timestamp) {

		this.transactionId = transactionId;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.retailerName = retailerName;
		this.amount = amount;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;

	}
	Order(){

	}
	
	public String toString(){
		String str = new String();
		str = str + " [ " + transactionId + ", "
				+ creditCardType + ", "
				+ creditCardNumber + ", "
				+ retailerName + ", "
				+ amount + ", "
				+ street + ", "
				+ city + ", "
				+ zip + ", "
				+ state + ", "
				+ country + ", "
				+ latitude + ", "
				+ longitude + ", "				
				+ timestamp
				+ " ] ";
		return str;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	   public byte[] toBytes() {
	        byte[]bytes;
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try{
	            ObjectOutputStream oos = new ObjectOutputStream(baos);
	            oos.writeObject(this);
	            oos.flush();
	            oos.reset();
	            bytes = baos.toByteArray();
	            oos.close();
	            baos.close();
	        } catch(IOException e){
	            throw new RuntimeException(e);
	        }
	        return bytes;
	    }

	    public static Order fromBytes(byte[] body) {
	        Order obj = null;
	        try {
	            ByteArrayInputStream bis = new ByteArrayInputStream(body);
	            ObjectInputStream ois = new ObjectInputStream(bis); // Error here
	            obj = (Order) ois.readObject();
	            ois.close();
	            bis.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	        return obj;
	    }	
	
}

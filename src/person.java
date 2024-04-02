public class person {
	
	private String name;
	private int phone;
	private String address;
	
	public person(String name) {
		this.name = name;
		}

public person(String name, int phone) {
	this.name = name;
	this.phone = phone;
}

public person(String name, int phone, String address) {
	this.name = name;
	this.phone = phone;
	this.address = address;
 }

public String getName() {return this.name; }
public int getPhone() { return this.phone; }
public String getAddress() { return this.address; }

public void setName(String name) {this.name = name;}
public void setPhone(int phone){this.phone = phone;}
public void setAddress(String address){this.address = address;}
}


package droneProject_212387575;


public class Subscription  implements Comparable<Subscription>{
	private String username;
	private String password;
	private int subCode;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	
	//building constructor
	public Subscription(int subCode ,String firstName, String lastName, String address, String phone, String username, String password) {
		super();
		this.username=username;
		this.password=password;
		this.subCode=subCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}
	
	//getter setters and toString
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	public int getSubCode() {
		return subCode;
	}
	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		System.out.println(this.address+ "qqqqqqqq");
		this.address = address;
		
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Subscription [subCode=" + subCode + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	public int compareTo(Subscription p) {
		return this.getLastName().compareTo(p.getLastName());
	}
	
	

}

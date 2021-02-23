
public class User {

	private String userId;
	private String firstName;
	private String lastName;
	private int version;
	private String company;
	private String companyUser;

	public User(String userId, String firstName, String lastName, int version, String company) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.version = version;
		this.company = company;
		this.companyUser = company + userId;
	}
	
	public String toString() 
    { 
        return userId + ", " + firstName + ", " + lastName + ", " + version + ", " + company; 
    } 

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getCompanyUser() {
		return companyUser;
	}
	
	public void setCompanyUser(String companyUser) {
		this.companyUser = companyUser;
	}

}

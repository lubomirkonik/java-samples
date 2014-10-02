package demo.model;

public class Name implements Comparable<Name> {
	
	private String firstname;
	private String lastname;
	
	public Name(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public String toString() {
		return "Name [firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	@Override
	public int compareTo(Name name) {
		return lastname.compareToIgnoreCase(name.getLastname());
	}
}

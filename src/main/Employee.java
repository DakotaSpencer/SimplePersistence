package main;

public class Employee implements java.io.Serializable {
    Integer id;
    String firstName;
    String lastName;
    Integer hireYear;

    public Employee() {
    }

    public Employee(Integer id, String firstName, String lastName, Integer hireYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireYear = hireYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getHireYear() {
        return hireYear;
    }

    public void setHireYear(Integer hireYear) {
        this.hireYear = hireYear;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID = ")
                .append(id)
                .append("\nFirst Name = ")
                .append(firstName)
                .append("\nLast Name = ")
                .append(lastName)
                .append("\nHire Year = ")
                .append(hireYear);

        return sb.toString();
    }
}

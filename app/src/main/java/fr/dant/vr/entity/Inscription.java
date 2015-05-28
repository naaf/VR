package fr.dant.vr.entity;

/**
 * Created by nasser on 28/05/2015.
 */
public class Inscription {

    // residence

    private String name;

    private int number;

    private String streetName;

    private String city;

    private String zipCode;

    // user
    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String roleUser;

    private String typeUser;

    public Inscription()
    {
        // TODO Auto-generated constructor stub
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getStreetName()
    {
        return streetName;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRoleUser()
    {
        return roleUser;
    }

    public void setRoleUser(String roleUser)
    {
        this.roleUser = roleUser;
    }

    public String getTypeUser()
    {
        return typeUser;
    }

    public void setTypeUser(String typeUser)
    {
        this.typeUser = typeUser;
    }

    @Override
    public String toString()
    {
        return "Inscription [name=" + name + ", number=" + number + ", streetName=" + streetName + ", city=" + city
                + ", zipCode=" + zipCode + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
                + ", password=" + password + ", roleUser=" + roleUser + ", typeUser=" + typeUser + "]";
    }

}

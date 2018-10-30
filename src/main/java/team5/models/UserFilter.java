package team5.models;

public class UserFilter {
    private String email;
    private String name;
    private String surname;

    public  UserFilter(){
        this.email="";
        this.name="";
        this.surname="";
    }

    public UserFilter(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
package fr.afpa.dto;

public class AdminDTO {
    public Long id;
    public String password;
    public String name;
    public String firstname;
    public String mail;

    public AdminDTO(Long id, String password, String name, String firstname, String mail) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
    }
}

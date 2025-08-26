package fr.afpa.dto;

public class StagiaireDTO {
    public Long id;
    public String name;
    public String firstname;
    public String mail;

    public StagiaireDTO() {} 

    public StagiaireDTO(Long id, String name, String firstname, String mail) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
    }

  public Long getId() { return id; }
    public String getName() { return name; }
    public String getFirstname() { return firstname; }
    public String getMail() { return mail; }

    
}

package fr.dant.vr.entity;

/**
 * Created by nasser on 20/05/2015.
 */
public class Contact {
    private String nom;
    private String email;
    private String mobile;

    public Contact() {
    }

    public Contact(String nom, String email, String mobile) {
        this.nom = nom;
        this.email = email;
        this.mobile = mobile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

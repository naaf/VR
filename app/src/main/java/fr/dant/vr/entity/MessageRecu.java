package fr.dant.vr.entity;

import java.util.Date;

/**
 * Created by nasser on 20/05/2015.
 */
public class MessageRecu {
    private String titre;
    private String nom;
    private Date date;
    private String corp;
    private String email;

    public MessageRecu() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

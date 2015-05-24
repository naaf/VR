package fr.dant.vr.entity;

import java.util.Date;

/**
 * Created by nasser on 20/05/2015.
 */
public class MessageRecu {
    private String titre;
    private Date date;
    private String corp;
    private Contact contact;


    public MessageRecu() {
        contact = new Contact();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return contact.getNom();
    }

    public void setNom(String nom) {
        contact.setNom(nom);
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
        return contact.getEmail();
    }

    public void setEmail(String email) {
        contact.setEmail(email);
    }

    public String getMobile() {
        return contact.getMobile();
    }

    public void setMobile(String mobile) {
        contact.setMobile(mobile);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "MessageRecu{" +
                "titre='" + titre + '\'' +
                ", date=" + date +
                ", corp='" + corp + '\'' +
                ", contact=" + contact +
                '}';
    }
}

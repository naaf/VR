package fr.dant.vr.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import fr.dant.vr.tools.Utility;

/**
 * Created by nasser on 20/05/2015.
 */
public class MessageRecu {
    private String titre;
    private Date date;
    private String corp;
    private Contact contact;
    private int id;

    public int getIdFrom() {
        return contact.getId();
    }

    public void setIdFrom(int idFrom) {
        contact.setId(idFrom);
    }

    private int idFrom;



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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static ArrayList<MessageRecu> fromJson(JSONArray jsonArray) {
        ArrayList<MessageRecu> messageRecus = new ArrayList<MessageRecu>();
        try {
            for(int i=0 ; i< jsonArray.length(); i++) {
                MessageRecu msg = new MessageRecu();
                // Deserialize json into object fields
                msg.titre = jsonArray.getJSONObject(i).getString("subject");
                msg.corp = jsonArray.getJSONObject(i).getString("body");
                msg.id = jsonArray.getJSONObject(i).getInt("id");
                msg.setIdFrom(jsonArray.getJSONObject(i).getInt("from")); 
                //msg.date = Utility.stringToDate(jsonArray.getJSONObject(i).getString("createDate"), "dd/MM/yyyy HH:mm:ss");
                messageRecus.add(msg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageRecus;
    }
}

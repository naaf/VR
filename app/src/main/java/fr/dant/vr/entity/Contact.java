package fr.dant.vr.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by nasser on 20/05/2015.
 */
public class Contact  implements Parcelable {
    private String nom;
    private String email;
    private String mobile;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact() {
    }

    public Contact(String nom, String email, String mobile, int id) {
        this.nom = nom;
        this.email = email;
        this.mobile = mobile;
        this.id = id;
    }
    public Contact(Parcel source) {
        this.nom = source.readString();
        this.email = source.readString();
        this.mobile = source.readString();
        this.id = source.readInt();
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(email);
        dest.writeString(mobile);
        dest.writeInt(id);
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {

        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public static ArrayList<Contact> fromJson(JSONArray jsonArray) {
        ArrayList<Contact> messageRecus = new ArrayList<Contact>();
        try {
            for(int i=0 ; i < jsonArray.length(); i++) {
                Contact msg = new Contact();
                // Deserialize json into object fields
                msg.nom = jsonArray.getJSONObject(i).getString("nom");
                msg.email = jsonArray.getJSONObject(i).getString("email");
                msg.id = jsonArray.getJSONObject(i).getInt("id");

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

package fr.dant.vr.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nasser on 20/05/2015.
 */
public class Contact  implements Parcelable {
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
    public Contact(Parcel source) {
        this.nom = source.readString();
        this.email = source.readString();
        this.mobile = source.readString();
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
}

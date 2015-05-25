package fr.dant.vr.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Created by nasser on 20/05/2015.
 */
public class ObjectOffert implements Parcelable {

    private byte[] image1 = null;
    private String titre;
    private String disponibilite;
    private String description;
    private Bitmap bitmap = null;


    public Bitmap getBitmap() {

        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ObjectOffert() {
    }

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public ObjectOffert(byte[] image1, String titre, String description, String disponibilite) {
        this.image1 = image1;
        this.titre = titre;
        this.description = description;
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "ObjectOffert{" +
                "image1=" + Arrays.toString(image1) +
                ", titre='" + titre + '\'' +
                ", disponibilite='" + disponibilite + '\'' +
                ", description='" + description + '\'' +
                ", bitmap=" + bitmap +
                '}';
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(image1 != null)
            dest.writeByteArray(image1);
        dest.writeString(titre);
        dest.writeString(disponibilite);
        dest.writeString(description);


    }
    public ObjectOffert(Parcel source) {
        if(this.image1 != null)
            source.readByteArray(this.image1);
        this.titre = source.readString();
        this.disponibilite = source.readString();
        this.description = source.readString();
    }

    public static final Parcelable.Creator<ObjectOffert> CREATOR = new Parcelable.Creator<ObjectOffert>() {

        @Override
        public ObjectOffert createFromParcel(Parcel source) {
            return new ObjectOffert(source);
        }

        @Override
        public ObjectOffert[] newArray(int size) {
            return new ObjectOffert[size];
        }
    };
}

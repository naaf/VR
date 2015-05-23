package fr.dant.vr;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.dant.vr.entity.Contact;

/**
 * Created by nasser on 20/05/2015.
 */
public class ContactAdopter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> contacts;
    private Context context;
    private int viewRes;
    private Resources res;

    public ContactAdopter(Context context, int textViewResourceId, ArrayList<Contact> contacts) {
        super(context, textViewResourceId, contacts);
        this.contacts = contacts;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Contact contact = contacts.get(position);
        if (contact != null) {
            final TextView nom = (TextView) view.findViewById(R.id.nom);
            nom.setText(contact.getNom());
        }
        return view;
    }

}

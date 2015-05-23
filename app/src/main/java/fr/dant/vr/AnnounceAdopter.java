package fr.dant.vr;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.dant.vr.entity.Announce;

/**
 * Created by nasser on 20/05/2015.
 */
public class AnnounceAdopter extends ArrayAdapter<Announce> {
    private ArrayList<Announce> announces;
    private Context context;
    private int viewRes;
    private Resources res;

    public AnnounceAdopter(Context context, int textViewResourceId, ArrayList<Announce> announces) {
        super(context, textViewResourceId, announces);
        this.announces = announces;
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
        final Announce announce = announces.get(position);
        if (announce != null) {
            final TextView nom = (TextView) view.findViewById(R.id.nom);
            final TextView date = (TextView) view.findViewById(R.id.date);
            final TextView titre = (TextView) view.findViewById(R.id.titre);
            final TextView corp = (TextView) view.findViewById(R.id.corp);
            final TextView role = (TextView) view.findViewById(R.id.corp);

            String announceRole = announce.getRole();
            String announceNom =  announce.getNom();
            if(announceRole != null){
                announceNom = String.format( announceNom +" [%s]", announceRole);
            }
            nom.setText(announceNom);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            date.setText(dateFormat.format(announce.getDate()).toString());
            titre.setText(announce.getTitre());
            corp.setText(announce.getCorp());
        }
        return view;
    }

}

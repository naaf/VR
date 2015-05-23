package fr.dant.vr;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.dant.vr.entity.MessageRecu;

/**
 * Created by nasser on 20/05/2015.
 */
public class MessageListAdopter extends ArrayAdapter<MessageRecu> {
    private ArrayList<MessageRecu> messageRecus;
    private Context context;
    private int viewRes;
    private Resources res;

    public MessageListAdopter(Context context, int textViewResourceId, ArrayList<MessageRecu> messageRecus) {
        super(context, textViewResourceId, messageRecus);
        this.messageRecus = messageRecus;
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
        final MessageRecu messageRecu = messageRecus.get(position);
        if (messageRecu != null) {
            final TextView nom = (TextView) view.findViewById(R.id.nom);
            final TextView date = (TextView) view.findViewById(R.id.date);
            final TextView titre = (TextView) view.findViewById(R.id.titre);
            final TextView corp = (TextView) view.findViewById(R.id.corp);
            final TextView role = (TextView) view.findViewById(R.id.corp);

            String announceNom =  messageRecu.getNom();

            nom.setText(announceNom);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            date.setText(dateFormat.format(messageRecu.getDate()).toString());
            titre.setText(messageRecu.getTitre());
            corp.setText(messageRecu.getCorp());

            // handle clic

            final ImageButton btnReplay = (ImageButton) view.findViewById(R.id.btnReplay);
            final ImageButton btnRemove = (ImageButton) view.findViewById(R.id.btnRemove);
            btnReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("BoxMessage", "Replay");
                    Toast.makeText(context, "Replay", Toast.LENGTH_LONG).show();
                    //passe des info pour l'envois de message
                    Intent intent = new Intent(context, SendMessage.class);
                    context.startActivity(intent);
                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("BoxMessage", "Remove");
                    Toast.makeText(context, "Remove", Toast.LENGTH_LONG).show();
                    //passe des info pour l'envois de message
                }
            });

        }
        return view;
    }


}

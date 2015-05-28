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

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.dant.vr.entity.MessageRecu;
import fr.dant.vr.entity.User;
import fr.dant.vr.ws.RestClient;

/**
 * Created by nasser on 20/05/2015.
 */
public class MessageListAdopter extends ArrayAdapter<MessageRecu> {
    private ArrayList<MessageRecu> messageRecus;
    private Context context;
    private int viewRes;
    private Resources res;
    private final String EXTRA_CONTACT = "contactReply";

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
            if(messageRecu.getDate() != null) {
                date.setText(dateFormat.format(messageRecu.getDate()).toString());
            }
            titre.setText(messageRecu.getTitre());
            corp.setText(messageRecu.getCorp());

            // handle clic

            final ImageButton btnReplay = (ImageButton) view.findViewById(R.id.btnReplay);
            final ImageButton btnRemove = (ImageButton) view.findViewById(R.id.btnRemove);

            btnReplay.setTag(position);
            btnRemove.setTag(position);

            btnReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("MessageListAdopter", "Replay");
                    int position = (Integer) v.getTag();
                    //passe des info pour l'envois de message
                    Intent intent = new Intent(context, SendMessage.class);
                    intent.putExtra(EXTRA_CONTACT, getItem(position).getContact());
                    context.startActivity(intent);
                }
            });

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("MessageListAdopter", "Remove");

                    //envois de la commande de suppression au serveur
                    int position = (Integer) v.getTag();
                    Log.e("Remove :",position + ", " );
                    Toast.makeText(context, "Remove :" +position , Toast.LENGTH_LONG).show();
                    RequestParams params = new RequestParams();
                    params.put("id", getItem(position).getId());
                    remove(getItem(position));
                    invokeWS(params);

                }
            });

        }
        return view;
    }

    private void invokeWS(RequestParams params) {

        RestClient.getClient().get(RestClient.getAbsoluteUrl("/messages/delete"), params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject body) {
                super.onSuccess(statusCode, headers, body);
                Toast.makeText(context, "onSuccess:" + statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //prgDialog.dismiss();

                Log.v("BoxMessage", "onFailure");
                Toast.makeText(context, "onFailure :" + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }




}

package fr.dant.vr;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.dant.vr.entity.ObjectOffert;

/**
 * Created by nasser on 20/05/2015.
 */
public class ObjectOffertAdopter extends ArrayAdapter<ObjectOffert> {
    private ArrayList<ObjectOffert> objectOfferts;
    private Context context;
    private int viewRes;
    private Resources res;

    public ObjectOffertAdopter(Context context, int textViewResourceId, ArrayList<ObjectOffert> objectOfferts) {
        super(context, textViewResourceId, objectOfferts);
        this.objectOfferts = objectOfferts;
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
        final ObjectOffert objectOffert = objectOfferts.get(position);
        if (objectOffert != null) {
            final TextView titre = (TextView) view.findViewById(R.id.titre);
            final TextView description = (TextView) view.findViewById(R.id.description);
            final ImageView img1 = (ImageView) view.findViewById(R.id.img1);
            final ImageView img2 = (ImageView) view.findViewById(R.id.img2);
            final ImageView img3 = (ImageView) view.findViewById(R.id.img3);

            titre.setText(objectOffert.getTitre());
            description.setText(objectOffert.getDescription());
            Bitmap bmp = null;
            if(objectOffert.getImage1() != null) {
                bmp = BitmapFactory.decodeByteArray(objectOffert.getImage1(), 0, objectOffert.getImage1().length);
                img1.setImageBitmap(bmp);
            }
            if(objectOffert.getImage2() != null) {
                bmp = BitmapFactory.decodeByteArray(objectOffert.getImage2(), 0, objectOffert.getImage2().length);
                img2.setImageBitmap(bmp);
            }
            if(objectOffert.getImage3() != null) {
                bmp = BitmapFactory.decodeByteArray(objectOffert.getImage3(), 0, objectOffert.getImage3().length);
                img3.setImageBitmap(bmp);
            }

            //handle button
            final ImageButton addObject = (ImageButton) view.findViewById(R.id.retrieveObject);
            addObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("ObjectOffertAdopter", "RecupererObject");
                    Toast.makeText(context, "RecupererObject", Toast.LENGTH_LONG).show();
                    //passe des info pour

                    //Intent intent = new Intent(Deposit.this, SignUp.class);
                    //startActivity(intent);

                }
            });
        }
        return view;
    }

}

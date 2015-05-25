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
import fr.dant.vr.tools.Utility;

/**
 * Created by nasser on 20/05/2015.
 */
public class ObjectOffertAdopter extends ArrayAdapter<ObjectOffert> {
    private ArrayList<ObjectOffert> objectOfferts;
    private Context context;
    private int viewRes;
    private Resources res;

    private static final int HEIGHT = 120;
    private static final int WIDTH = 150;

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
        final ViewHolder holder;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
            holder = new ViewHolder();
            holder.titre = (TextView)view.findViewById(R.id.objetTitre);
            holder.description = (TextView) view.findViewById(R.id.objetDescription);
            holder.disponibilite = (TextView) view.findViewById(R.id.objetDisponibilite);
            holder.img1 = (ImageView) view.findViewById(R.id.img1);
            holder.retrieveObject = (ImageButton) view.findViewById(R.id.retrieveObject);
            holder.retrieveObject.setTag(position);
            view.setTag(holder);

        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        final ObjectOffert objectOffert = objectOfferts.get(position);
        if (objectOffert != null) {


            if (Utility.isNotNull(objectOffert.getTitre())) {
                holder.titre.setText(objectOffert.getTitre());
            }

            if (Utility.isNotNull(objectOffert.getDisponibilite())) {
                holder.disponibilite.setText(objectOffert.getDisponibilite());
            }
            if (Utility.isNotNull(objectOffert.getDescription())) {
                holder.description.setText(objectOffert.getDescription());
            }

            Bitmap bitmap = objectOffert.getBitmap();
            if (bitmap != null) {
                int height = bitmap.getHeight(), width = bitmap.getWidth();
                Log.e("dimensioin", " h" + height + "w " + width);
                if (height > 1280 && width > 960) {
                    holder.img1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, true));
                }else{
                    holder.img1.setImageBitmap(bitmap);
                }
            }
            else if(objectOffert.getImage1() != null){
                bitmap = BitmapFactory.decodeByteArray(objectOffert.getImage1(), 0, objectOffert.getImage1().length);
                holder.img1.setImageBitmap(bitmap);
            }

            //handle button
            holder.retrieveObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("ObjectOffertAdopter", "RecupererObject");
                    Toast.makeText(context, "Recuperer Object", Toast.LENGTH_LONG).show();

                    int position = (Integer) v.getTag();
                    ObjectOffert obj = getItem(position);
                    obj.setDisponibilite("Pris");
                    notifyDataSetChanged();
                    //passe des info pour
                    //Intent intent = new Intent(Deposit.this, SignUp.class);
                    //startActivity(intent);
                }
            });
        }
        return view;
    }

    static class ViewHolder {
        TextView titre ;
        TextView description ;
        TextView disponibilite;
        ImageView img1;
        ImageButton retrieveObject;
    }

}

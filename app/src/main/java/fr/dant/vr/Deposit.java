package fr.dant.vr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import fr.dant.vr.entity.ObjectOffert;
import fr.dant.vr.tools.Utility;


public class Deposit extends BasicToolBar {

    private final int RESULT_OFFRE_OBJECT = 32;
    private final String EXTRA_OBJET_OFFERT = "objetOffert";

    private ObjectOffertAdopter adapter;
    private ArrayList<ObjectOffert> objectOfferts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        objectOfferts = new ArrayList<ObjectOffert>();

        initList(objectOfferts);

        adapter = new ObjectOffertAdopter(this, R.layout.list_object, objectOfferts);
        final ListView list = (ListView) findViewById(R.id.list_object);
        list.setAdapter(adapter);



        // handle button
        final ImageButton addObject = (ImageButton) findViewById(R.id.objectAdd);
        addObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Deposit", "addObject");
                Toast.makeText(Deposit.this, "addObject", Toast.LENGTH_LONG).show();

                //passe des info pour
                Intent intent = new Intent(getApplicationContext(), SendObject.class);
                startActivityForResult(intent, RESULT_OFFRE_OBJECT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OFFRE_OBJECT && data != null) {
            Log.v("onActivityResult", " RESULT_OFFRE_OBJECT OK");
            ObjectOffert obj = data.getParcelableExtra(EXTRA_OBJET_OFFERT);
            Toast.makeText(Deposit.this, obj.toString(), Toast.LENGTH_LONG).show();
            if(Global.img != null) {
                obj.setBitmap(Global.img);
                addItems(obj);
            }

        } else {
            Log.v("onActivityResult", "RESULT_OFFRE_OBJECT NON");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private void initList(ArrayList<ObjectOffert> androidList) {
        ObjectOffert announce1 = new ObjectOffert();
        announce1.setTitre("Ashraf");
        announce1.setDescription("voila un test de element 1 ");

        androidList.add(announce1);

        ObjectOffert announce2 = new ObjectOffert();
        announce2.setTitre("test");
        announce2.setDescription("voila un test de element 2");
        androidList.add(announce2);

        ObjectOffert announce3 = new ObjectOffert();
        announce3.setTitre("Ashraf");
        announce3.setDescription("voila un test de element 3");
        androidList.add(announce3);
    }
    public static byte[] convertBitmapToByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        } else {
            byte[] b = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                b = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return b;
        }
    }
    public static class Global { static Bitmap img; }

    public void addItems(ObjectOffert obj) {
        adapter.add(obj);
        adapter.notifyDataSetChanged();
        Log.v("addItems", objectOfferts.toString());
    }

}

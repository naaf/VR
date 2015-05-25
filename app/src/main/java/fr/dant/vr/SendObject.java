package fr.dant.vr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import fr.dant.vr.entity.ObjectOffert;


public class SendObject extends ActionBarActivity {

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;

    private static final int HEIGHT = 120;
    private static final int WIDTH = 150;
    private final int RESULT_OFFRE_OBJECT = 32;
    private final String EXTRA_OBJET_OFFERT = "objetOffert";

    private EditText titre;
    private EditText description;
    private ImageView img;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_object);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sendObject);
        toolbar.setTitle("VR");
        setSupportActionBar(toolbar);

        Button btnChargeImage = (Button) findViewById(R.id.charge_image);
        Button btnDonnez = (Button) findViewById(R.id.btn_sendObjet);
        img = (ImageView) findViewById(R.id.img1);

        btnChargeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MainActivity", "Result_KO");
                Toast.makeText(getApplicationContext(), "RESULT_KO", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });

        btnDonnez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "RESULT_KO", Toast.LENGTH_LONG).show();

                titre = (EditText) findViewById(R.id.field_titreObjet);
                description = (EditText) findViewById(R.id.field_descriptionObjet);

                ObjectOffert obj = new ObjectOffert();
                obj.setTitre(titre.getText().toString());
                obj.setDescription(description.getText().toString());
                obj.setDisponibilite("disponible");

                Log.v("Object",obj.toString());
                Intent resultIntent = new Intent(getApplicationContext(), Deposit.class);

                Deposit.Global.img = bitmap;
                resultIntent.putExtra(EXTRA_OBJET_OFFERT, obj);
                setResult(RESULT_OFFRE_OBJECT, resultIntent);

                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        finish();
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
    private byte[] Convert(Bitmap bmp) {
        int bytes = bmp.getByteCount();
        Log.v("Bytes", String.valueOf(bytes));
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        bmp.copyPixelsToBuffer(buffer);
        byte[] array = buffer.array();
        Log.v("Bytes", Arrays.toString(array));
        if (array != null) {
            return array;
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_object, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                bitmap = BitmapFactory.decodeFile(selectedImagePath);
                int height = bitmap.getHeight(), width = bitmap.getWidth();
                if (height > 1280 && width > 960) {
                    img.setImageBitmap(Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false));
                }else{
                    img.setImageBitmap(bitmap);
                }
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

}

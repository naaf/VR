package fr.dant.vr.entity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.dant.vr.tools.Utility;

/**
 * Created by nasser on 28/05/2015.
 */
public class User {
    public static int idUser;
    public static int idResidence;
    public static String role;

    public static void fromJson(JSONObject jsonObject) {
        try {
            idUser = jsonObject.getInt("idUser");
            idResidence = jsonObject.getInt("idResidence");
            role = jsonObject.getString("role");

        } catch (JSONException e) {
            Log.e("User","Json exception");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

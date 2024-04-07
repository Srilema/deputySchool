package com.example.deputyschool;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ApiServices {

    private static String URL_API_SEARCH="https://www.nosdeputes.fr/recherche/";
    private static String URL_END_FORMATJSON= "?format=json";
    private static String URL_AVATAR="https://www.nosdeputes.fr/depute/photo/";

    private static String URL_API_VOTES="https://www.nosdeputes.fr/";

    private static String URL_VOTES_END= "/votes/json";

    public static void searchRequest(Context context, String search, SearchObserver listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API_SEARCH + search + URL_END_FORMATJSON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray= jsonObject.getJSONArray("results");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject object= jsonArray.getJSONObject(i);
                                if(object.getString("document_type").equals("Parlementaire")){
                                    getDeputyInfo(context, object.getString("document_url"), listener);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public static void getDeputyInfo(Context context, String urlInfoDeputy, SearchObserver listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(urlInfoDeputy,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONObject jsonObjectDeputy= jsonObject.getJSONObject("depute");
                            Deputy deputy= new Deputy(jsonObjectDeputy.getInt("id"),
                                    jsonObjectDeputy.getString("prenom"),
                                    jsonObjectDeputy.getString("nom_de_famille"),
                                    jsonObjectDeputy.getString("num_deptmt"),
                                    jsonObjectDeputy.getInt("num_circo"),
                                    jsonObjectDeputy.getString("nom_circo"),
                                    jsonObjectDeputy.getString("parti_ratt_financier"),
                                    jsonObjectDeputy.getString("groupe_sigle"));
                            JSONArray collabosArray = jsonObjectDeputy.getJSONArray("collaborateurs");
                            for (int i=0; i<collabosArray.length();i++){
                                JSONObject collaboObject = collabosArray.getJSONObject(i);
                                String collaboName = collaboObject.getString("collaborateur");
                                deputy.addCollabo(collaboName);
                            }
                            JSONArray emailsArray = jsonObjectDeputy.getJSONArray("emails");
                            for (int i=0; i<emailsArray.length();i++){
                                JSONObject emailObject = emailsArray.getJSONObject(i);
                                String emailName = emailObject.getString("email");
                                deputy.addEmail(emailName);
                            }
                            JSONArray adressesArray = jsonObjectDeputy.getJSONArray("adresses");
                            for (int i=0; i<adressesArray.length();i++){
                                JSONObject adresseObject = adressesArray.getJSONObject(i);
                                String adresseName = adresseObject.getString("adresse");
                                deputy.addAdresse(adresseName);
                            }
                            listener.onReceiveDeputyInfo(deputy);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public static void loadDeputyAvatar(Context context, String deputyName, final ImageView imageView){
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest request = new ImageRequest( URL_AVATAR+deputyName+"/160",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imageView.setImageResource(android.R.drawable.ic_menu_gallery);
                    }
                });
        queue.add(request);
    }

    /*public static void loadDeputyVotes(Context context, String , SearchObserver listener){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(URL_API_VOTES + search + URL_VOTES_END);
        @Override
        public void onResponse(String response){
            try {
                JSONObject jsonObject=new JSONObject(response);
                JSONArray jsonArray= jsonObject.getJSONArray("results");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };*/

}

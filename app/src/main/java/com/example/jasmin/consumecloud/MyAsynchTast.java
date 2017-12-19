package com.example.jasmin.consumecloud;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jasmin on 19/12/2017.
 */

//3 EIngabeparamter, <art der Ressource, Status wie viel Prozent wurde gelesen, rückgabe>
public class MyAsynchTast extends AsyncTask<String, Integer, String> {

    //globale Varaible nimmt parameter im Konstruktor auf
    TextView globalView;
    public MyAsynchTast(TextView param){
        globalView = param;
    }


    //wird in CLoud Thread ausgeführt
    @Override
    protected String doInBackground(String... urls) { //... hier können meherere Strings  durch Komma getrennt übergeben werden
        String jsonString ="";
        for(String urlString: urls){
            //desto mehr KOmmas beim Aufruf nach ...
            //desto öfter geht er durch die for each schleife
            try {
                URL url = new URL(urlString);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                String line ="";

                //jede readLine() fragt an, deswegen muss ich das in Variable sofort auffangen
                while( (line = reader.readLine())!=null ){
                  System.out.println("line=" +line);
                  jsonString+=line;
                }
                //.ready() geht hier jetzt nicht mehr
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



        return jsonString;
    }

    //Wird in Activity Thread ausgeführt
    @Override
    protected void onPostExecute(String s) {
       //Methode für GUI und Daten bekommen sind in völlig voneinander isolierten threads
//ausgabe des Textes in der globalen Variable, die den Parameter von Activity-Klassen enthält
globalView.setText(s);

    }
}

//recht ins inet zu gehen
//verlagerung cloud in eigenen Thread

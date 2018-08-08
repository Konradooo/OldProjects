package com.example.konrad.apzumiproject;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.konrad.apzumiproject.GeneralMethods.apiResults;

/**
 * Created by Konrad on 08.08.2018.
 */

// Uzywamy asyncTaskow w celu pobrania danych dzieki API. Nie blokujemy glownego watku a zadanie leci sobie nezaleznie w tle

public class APIdataSearcher extends AsyncTask<Object, Object, List<APIResults>> {
    @Override
    protected List<APIResults> doInBackground(Object... params) {
        URL url = null;
        // Pamietamy o wyczyszczeniu listy przed ponownym pobraniem danych
        apiResults.clear();
        try {
            url = new URL("https://api.bitbucket.org/2.0/repositories?fields=values.name,values.owner,values.description");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            int responseCode = http.getResponseCode();
            if (responseCode == 200) { //jeżeli ok TO:
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(http.getInputStream()));
                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                // Wchodzimy do tablicy ktora wyciagamy z obiektu
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray valuesArray = (JSONArray) jsonObject.get("values");
                // Przechodzimy po tablicy i wyciagamy kolejne elementy
                List<APIResults> listaObiektow = new ArrayList<>();
                for (int i = 0; i < valuesArray.length(); i++) {
                    JSONObject obj = (JSONObject) valuesArray.get(i);
                    // Wchodzimy jeszcze glebiej i wyciagamy konkretne dane z obiektu tablicy
                    JSONObject singleObjOwner = (JSONObject) obj.get("owner");
                    // Wchodzimy jeszcze glebiej wyciagajac username
                    JSONObject userName = (JSONObject) singleObjOwner.get("username");
                    JSONObject linksObject = (JSONObject) singleObjOwner.get("links");
                    JSONObject awatarObject = (JSONObject) linksObject.get("awatar");
                    JSONObject finalAwatarLink = (JSONObject) awatarObject.get("href");
                }



                JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                List<APIResults> preParse = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    JSONObject singleObjId = (JSONObject) obj;









/
            } else {
                System.out.println("tabla nie obsluzona");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

package com.mishra.mock.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class GetDataFromServer extends AsyncTask<String, String, String>
{

    private final ProgressDialog progressBar;

    public interface OnTaskComplete
    {
        void onTaskCompleted();
    }

    private final String url;
    OnTaskComplete onTaskComplete;
    private static ArrayList<NetworkResponseOuputVO> dataList;


    public GetDataFromServer(String url, OnTaskComplete onTaskComplete, Context context)
    {
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Please wait...");
        progressBar.setCancelable(false);
        progressBar.show();
        this.onTaskComplete = onTaskComplete;
        this.url = url;
        dataList = new ArrayList<>();
    }

    public static ArrayList<NetworkResponseOuputVO> getDataList()
    {
        return dataList;
    }

    @Override
    protected String doInBackground(String... params)
    {
//        param = params[0];
        String result;
        try {
            HttpRequest httpRequest = new HttpRequest(new URL(url));
            httpRequest.preparePost();
            result = httpRequest.sendAndReadString();
        }
        catch (Exception e) {
            Log.e("Exception", e.toString() + "");
            return null;
        }
        Log.e("Results:-----", result);
        return result;
    }

    @Override
    protected void onPostExecute(String result)
    {
        if (result != null) {
            JSONArray jSONArray;

            try {
                jSONArray = new JSONArray(result);
                for (int i = 0; i < jSONArray.length(); i++) {
                    parsJsonResponse(jSONArray.getJSONObject(i));
                }
                ArrayList<NetworkResponseOuputVO> list = getDataList();
                onTaskComplete.onTaskCompleted();
                if (progressBar != null && progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void parsJsonResponse(JSONObject jsonObject) throws JSONException
    {
        NetworkResponseOuputVO responseOuputVO = new NetworkResponseOuputVO();
        responseOuputVO.setId(getStringValueFromKey(jsonObject, "id"));
        responseOuputVO.setName(getStringValueFromKey(jsonObject, "name"));

        JSONObject jsonObjectVehical = (JSONObject) jsonObject.get("fromcentral");
        JSONObject jsonObjectLocation = (JSONObject) jsonObject.get("location");
        makeVehicalData(jsonObjectVehical, responseOuputVO);
        makeVehicalLocationData(jsonObjectLocation, responseOuputVO);
        dataList.add(responseOuputVO);

    }

    private void makeVehicalLocationData(JSONObject jsonObjectLocation, NetworkResponseOuputVO responseOuputVO)
    {
        if (jsonObjectLocation != null) {
            NetworkResponseOuputVO responseOuputVOs = new NetworkResponseOuputVO();
            responseOuputVOs.setLat(jsonObjectLocation.optString("latitude"));
            responseOuputVOs.setLng(jsonObjectLocation.optString("longitude"));
            responseOuputVO.locationList.add(responseOuputVOs);
        }
    }

    private void makeVehicalData(JSONObject jsonObjectVehical, NetworkResponseOuputVO responseOuputVO)
    {
        if (jsonObjectVehical != null) {
            NetworkResponseOuputVO responseOuputVOs = new NetworkResponseOuputVO();
            responseOuputVOs.setCar(jsonObjectVehical.optString("car"));
            responseOuputVOs.setTrain(jsonObjectVehical.optString("train"));
            responseOuputVO.vehicalList.add(responseOuputVOs);

        }
    }

    protected String getStringValueFromKey(JSONObject responseJson, String key) throws JSONException
    {
        if (responseJson.has(key)) {
            return responseJson.getString(key);
        }
        return "";
    }

}
package com.mishra.mock.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest
{
    private URL url;
    private String soapAction;
    ;
    private HttpURLConnection con;
    private OutputStream os;

    public HttpRequest(URL url)
    {
        this.url = url;
    }

    public HttpRequest(String url, String soapAction) throws MalformedURLException
    {
        this.url = new URL(url);
        this.soapAction = soapAction;
    }

    private void done() throws IOException
    {
        con.disconnect();
        os.close();
    }


    private void prepareAll(boolean isPost) throws IOException
    {
        con = (HttpURLConnection) url.openConnection();
        if (isPost) con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("SOAPAction", soapAction);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "text/xml; charset=utf-8");

        os = con.getOutputStream();
    }

    public HttpRequest prepare() throws IOException
    {
        prepareAll(false);
        return this;
    }

    public HttpRequest preparePost() throws IOException
    {
        prepareAll(true);
        return this;
    }

    public HttpRequest withData(String query) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(query);
        writer.close();
        return this;
    }


    public HttpRequest withData(HashMap<String, String> params) throws IOException
    {
        String result = "", and = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result += and + entry.getKey() + "=" + entry.getValue();
            if (and.isEmpty())
                and = "&";
        }
        withData(result);
        return this;
    }

    public boolean send() throws IOException
    {
        boolean status = con.getResponseCode() == HttpURLConnection.HTTP_OK;//Http OK == 200
        done();
        return status; //boolean return to indicate whether it successfully sent
    }

    public String sendAndReadString() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line, response = "";
        while ((line = br.readLine()) != null) response += line;
        done();
        return response;
    }

    public JSONObject sendAndReadJSON() throws JSONException, IOException
    {
        return new JSONObject(sendAndReadString());
    }


}
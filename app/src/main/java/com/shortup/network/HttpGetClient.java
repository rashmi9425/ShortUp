package com.shortup.network;

import android.os.AsyncTask;
import android.util.Log;

import com.shortup.utils.GlobalConstant;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sourin on 03/11/15.
 */
public class HttpGetClient{

    private String url;
    private int responseCode;
    private String response;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void sendGetRequest(){

        if(this.url != null){
            try{
                //https://api-ssl.bitly.com/v3/shorten?access_token=512b33cc519eeb5a59829f12c823ada432f37503&longUrl=http%3A%2F%2Fgoogle.com%2F&format=json
                String CompletedUrl=GlobalConstant.base_url+"/v3/shorten?access_token="+ GlobalConstant.access_token+"&longUrl="+this.url+"&format=json";
                URL url = new URL(CompletedUrl);
                HttpURLConnection mHttpURLConnection = (HttpURLConnection) url.openConnection();
                mHttpURLConnection.setRequestMethod("GET");
                mHttpURLConnection.setRequestProperty("Content-Type", "application/json");
                mHttpURLConnection.connect();
                InputStream mInputStream = mHttpURLConnection.getInputStream();
                // Read the stream
                byte[] b = new byte[1];
                ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
                while ( mInputStream.read(b) != -1)
                    mByteArrayOutputStream.write(b);

                this.response = new String(mByteArrayOutputStream.toByteArray());

                this.responseCode = mHttpURLConnection.getResponseCode();

            }catch (Exception e){
                this.responseCode = 0;
                this.response = "Bad Request";
                e.printStackTrace();
            }
        }

    }

}

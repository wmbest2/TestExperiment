package com.vokal.test.exp.network;

import android.net.http.AndroidHttpClient;
import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NetworkUtilsImpl extends AbstractNetworkImpl {
    private static final String TAG = "NetworkUtils";

    private static AndroidHttpClient sHttpClient = null;

    public String get(String aUrl, HashMap<String, String> aParams) 
        throws Exception 
    {
        
        setupHttpClient();
        HttpGet httpGet = new HttpGet(aUrl + generateQueryString(aParams));

        return performRequest(httpGet, aUrl);
    }

    public String post(String aUrl, HashMap<String, String> aParams)
        throws Exception
    {
        String response = null;

        setupHttpClient();
        HttpPost httpPost = new HttpPost(aUrl);
        httpPost.setEntity(generateParametersEntity(aParams));

        HttpResponse httpResponse = sHttpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();

        if (responseEntity != null) {
            response = EntityUtils.toString(responseEntity);
        }

        return response;
    }

    public String put(String aUrl, HashMap<String, String> aParams)
        throws Exception
    {
        setupHttpClient();
        HttpPut httpPut = new HttpPut(aUrl);
        httpPut.setEntity(generateParametersEntity(aParams));

        return performRequest(httpPut, aUrl);
    }

    public String delete(String aUrl)
        throws Exception
    {
        setupHttpClient();
        HttpDelete httpDelete = new HttpDelete(aUrl);

        return performRequest(httpDelete, aUrl);
    }

    private String performRequest(HttpUriRequest aRequest, String aUrl) 
        throws Exception
    {
        String response = null;

        Log.d(TAG, aRequest.getURI().toString());
        HttpResponse httpResponse = sHttpClient.execute(aRequest);
        HttpEntity responseEntity = httpResponse.getEntity();

        if (responseEntity != null) {
            response = EntityUtils.toString(responseEntity);
        }

        return response;
    }

    private final String generateQueryString(HashMap aParams) {
        String result = "?";

        try {
            if (aParams != null) {
                // Loop over each parameter and add it to the query string
                Iterator iter = aParams.entrySet().iterator();

                while (iter.hasNext()) {
                    Map.Entry param = (Map.Entry) iter.next();

                    result += URLEncoder.encode((String) param.getKey(), "UTF-8") + 
                        "=" + URLEncoder.encode((String) param.getValue(), "UTF-8");

                    if (iter.hasNext()) {
                        result += "&";
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            Log.i(TAG, e.toString());
        }
    
        return result;
    }

    private UrlEncodedFormEntity generateParametersEntity(HashMap<String, String> aParams) 
        throws UnsupportedEncodingException
    {
        Iterator<?> iter = aParams.entrySet().iterator();

        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();

        while (iter.hasNext()) {
            Map.Entry<String, String> param = 
                (Map.Entry<String, String>) iter.next();

            parameters.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }

        return new UrlEncodedFormEntity(parameters);
    }

    public static void setupHttpClient() {
        if (sHttpClient == null) {
            sHttpClient = AndroidHttpClient.newInstance("Vokal"); 
        }
    }

    public static void resetHttpClient() {
        sHttpClient = null;
    }
}

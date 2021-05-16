package com.example.mysudubomb.sourceop;

import com.example.mysudubomb.exception.ErrorResponseCodeException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStreamOP {
    public InputStream getInputStream(String path) throws IOException, ErrorResponseCodeException {
        InputStream in = null;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        int code = connection.getResponseCode();
        if (code == 200) {
            in = connection.getInputStream();
        } else {
            throw  new ErrorResponseCodeException("error request code is"+ code);
        }
        return  in;
    }
}

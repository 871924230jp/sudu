package com.example.mysudubomb.sourceop;

import com.example.mysudubomb.exception.ErrorResponseCodeException;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpOP {

    public InputStream getInputStream (String path) throws IOException ,ErrorResponseCodeException{

        OkHttpClient okHttpClient = new OkHttpClient();


        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        /*call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });*/
        /*异步请求*/

        if (response.isSuccessful()){
            InputStream inputStream = response.body().byteStream();
            return inputStream;

        }else {
            throw  new ErrorResponseCodeException("error ");

        }

    }



}

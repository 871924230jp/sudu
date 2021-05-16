package com.example.mysudubomb.sourceop;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class AssetssStreamOP {
    private Context context;
    public  AssetssStreamOP(Context context){
        this.context=context;
    }

    public InputStream getInputStream(String path) throws IOException {

        return this.context.getAssets().open(path);

    }
}

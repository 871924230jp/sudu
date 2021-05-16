package com.example.mysudubomb.manager;

import android.content.Context;

import com.example.mysudubomb.bean.HealthInfo;
import com.example.mysudubomb.sourceprovider.ICompute;

public class ComputeManager {
    private Context context;
    private HealthInfo healthInfo;

    public ComputeManager(Context context,HealthInfo healthInfo){
        this.context=context;
        this.healthInfo=healthInfo;
    }
    public String getStringFromComput(ICompute iCompute){
        Float result = iCompute.getComputeResult(healthInfo);
        String compute = result.toString().format("%.2f", result);
        return compute;
    }


}

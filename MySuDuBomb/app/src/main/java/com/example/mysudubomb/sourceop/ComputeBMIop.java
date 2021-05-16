package com.example.mysudubomb.sourceop;

import com.example.mysudubomb.bean.HealthInfo;
import com.example.mysudubomb.sourceprovider.ICompute;

public class ComputeBMIop implements ICompute {
    @Override
    public float getComputeResult(HealthInfo healthInfo) {
        float shengao = Float.parseFloat(healthInfo.getShengao());
        float tizhong = Float.parseFloat(healthInfo.getTizhong());
        float bmi = tizhong / ((shengao / 100) * (shengao / 100));
        return bmi;
    }
}

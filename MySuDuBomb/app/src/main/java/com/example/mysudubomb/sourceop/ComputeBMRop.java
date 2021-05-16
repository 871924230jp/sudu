package com.example.mysudubomb.sourceop;

import com.example.mysudubomb.bean.HealthInfo;
import com.example.mysudubomb.sourceprovider.ICompute;

public class ComputeBMRop implements ICompute {
    @Override
    public float getComputeResult(HealthInfo healthInfo) {
        Float bmr;
        float shengao = Float.parseFloat(healthInfo.getShengao());
        float tizhong = Float.parseFloat(healthInfo.getTizhong());
        float nianling = Float.parseFloat(healthInfo.getNianling());
        switch (healthInfo.getXingbie()){
            case "男":
                bmr=66 + (14 * tizhong)+(5 * shengao )-(7 * nianling);
                break;
            case "女":
                bmr=655 + (10 * tizhong)+(2 * shengao )-(5 * nianling);
                break;
            default:
                bmr = null;
                break;
        }
        return bmr;
    }
}

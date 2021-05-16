package com.example.mysudubomb.sourceop;

import com.example.mysudubomb.bean.HealthInfo;
import com.example.mysudubomb.sourceprovider.ICompute;

public class ComputeKGop implements ICompute {
    @Override
    public float getComputeResult(HealthInfo healthInfo) {
        Float kg;
        float shengao = Float.parseFloat(healthInfo.getShengao());
        switch (healthInfo.getXingbie()){
            case "男":
                kg = (shengao - 80) * 7 / 10;
                break;
            case "女":
                kg = (shengao - 70) * 6 / 10;
                break;
            default:
                kg = null;
                break;
        }
        return kg;
    }
}

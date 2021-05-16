package com.example.mysudubomb.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.sourceop.sqlutils.MySqliteHelper;

public class ShareXiangManager {
    private Context context;
    private final MySqliteHelper helper;

    public ShareXiangManager(Context context){
        this.context=context;
        helper = new MySqliteHelper(context);
    }
    public void  deleteFoodInfo(String time){
        SQLiteDatabase db = this.helper.getReadableDatabase();
        int result = db.delete(AppProperties.SHARE_TABLE_NAME, "time = ?",new String[]{time});/*whereclause查询条件  1是全删*/
        /*物理意义是返回 受影响的行数   失败返回0*/
        if (result>0)
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
        db.close();
    }
}

package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysudubomb.R;
import com.example.mysudubomb.adapter.FoodAdapter;
import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.manager.SearchManager;
import com.example.mysudubomb.properties.AppProperties;
import com.example.mysudubomb.utils.NoActionBar;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText et_search;
    private ListView lv_search;
    private SearchManager manager;
    private List<FoodInfo> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        NoActionBar.noActionBar(this);
        initData();
        initView();
        show();
    }

    private void initData() {
        manager = new SearchManager(this);
    }

    private void initView() {
        et_search = (EditText) findViewById(R.id.et_search);
        lv_search = (ListView) findViewById(R.id.lv_search);
    }

    private void show() {
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“搜索”键*/
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String key = et_search.getText().toString().trim();
                    if(TextUtils.isEmpty(key)){
                        Toast.makeText(SearchActivity.this, "不能输入为空", Toast.LENGTH_SHORT).show();
                    }else {
                        if (manager.noSearch()) {
                                Toast.makeText(SearchActivity.this, "找不到", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
                return false;
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}//文本改变之前执行
            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    lv_search.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示ListView
                    showListView();
                }
            }

            public void afterTextChanged(Editable s) { }//文本改变之后执行
        });

    }
    private void showListView() {
        lv_search.setVisibility(View.VISIBLE);
        String content = et_search.getText().toString().trim();
        foodList = manager.getFoodListFromDB(content);


        FoodAdapter foodAdapter = new FoodAdapter(this, foodList,R.layout.food_item);
        lv_search.setAdapter(foodAdapter);

        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                FoodInfo foodInfo = foodList.get(position);
                intent.putExtra(AppProperties.INTENT_KEY,foodInfo);
                intent.setClass(SearchActivity.this, XiangXiActivity.class);
                startActivity(intent);
            }
        });
    }


    public void onclick(View view) {
        manager.closeCursor();
        finish();
    }
}

package com.example.zhufenghua.test;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by zhufenghua on 2017/5/4.
 */

public class MyView extends RelativeLayout {

    Context cont;

    public MyView(final Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.start_btn_page, this);
        Button btn = (Button) findViewById(R.id.start_btn);
        cont = context;
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(context,"新视图测试",LENGTH_LONG).show();
                Intent intent = new Intent(cont, Main2Activity.class);
                cont.startActivity(intent);
            }
        });
    }
    private MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}

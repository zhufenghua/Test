package com.example.zhufenghua.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends Activity {
    private ViewPager viewPager;
    private List<View> listView;
    private LinearLayout ll;
    private View redPoint;
    private int pointWidth;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout) findViewById(R.id.ll);
        redPoint = (View) findViewById(R.id.redPoint);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyPagerAdapter());


        initGrayPoint();

        initPageChangeListener();
        initRadioGroupCheckedChangeListener();
    }


    //加入菜单点击监听，实现ViewPager随点击翻动
    private void initRadioGroupCheckedChangeListener() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int[] ids = new int[]{R.id.firstBtn, R.id.secondBtn, R.id.thirdBtn, R.id.fouthBtn};
                for (int i = 0; i < ids.length; i++) {
                    if (checkedId == ids[i]) {
                        viewPager.setCurrentItem(i);
                        return;

                    }
                }


            }
        });

    }

    //加入 viewPager页面改变监听，实现：
    //1、红点的移动
    //根据页面最终位置确定菜单的正确位置
    private void initPageChangeListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
                params.leftMargin = (int) ((float) pointWidth * positionOffset) + pointWidth * position;
                redPoint.setLayoutParams(params);

            }

            //根据页面的最终位置设置正确的菜单位置
            @Override
            public void onPageSelected(int position) {
                RadioButton t = (RadioButton) radioGroup.getChildAt(position);
                t.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //初始化灰点
    private void initGrayPoint() {

        for (int i = 0; i < listView.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.gray_point);
            int px = DensityUtils.dpi2px(this, 10);

            Log.i("px", "" + px);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(px, px);
            if (i > 0) {
                params.leftMargin = px;
                this.pointWidth = px + px;
            }
            view.setLayoutParams(params);
            ll.addView(view);

        }

    }


    public void goNextActivity(View view) {
        Log.i("click", "click");

        Toast.makeText(this, "goNextActivity", LENGTH_SHORT).show();

    }


    //实现ViewPager适配器，重点在构造时生成各个页面

    private class MyPagerAdapter extends PagerAdapter {
        public MyPagerAdapter() {
            int[] ids = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

            listView = new ArrayList<View>();
            for (int i = 0; i < ids.length; i++) {
                View view = new View(MainActivity.this);
                view.setBackgroundResource(ids[i]);
                listView.add(view);
            }

            //   View view = View.inflate(MainActivity.this,R.layout.start_btn_page,null);

            MyView view = new MyView(MainActivity.this);

            listView.add(view);
        }


        @Override

        public Object instantiateItem(ViewGroup container, int position) {
            View view = listView.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return listView.size();
        }
    }
}

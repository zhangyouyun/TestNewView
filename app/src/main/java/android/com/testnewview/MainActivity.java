package android.com.testnewview;

import android.app.Activity;
import android.com.testnewview.DiyLayout.CustomTextView;
import android.com.testnewview.DiyLayout.DiyLayout;
import android.com.testnewview.Entity.Item;
import android.com.testnewview.adapter.ChildAdapter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public ArrayList<Item> leftlist, middlelist, rightlist = null;
    int screenWidth,all;
    private ListView listView;
    private ChildAdapter childAdapter;//子项适配器

    //--------------------------
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 获取屏幕宽度

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        Constant.displayWidth = displayMetrics.widthPixels;
//        Constant.displayHeight = displayMetrics.heightPixels;

        /*动画效果*/
        ScaleAnimation sa=new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        LayoutAnimationController lac=new LayoutAnimationController(sa,0);
       //左边部分--------------------------
        leftlist = new ArrayList<Item>();
        final CustomTextView btn1 = new CustomTextView(this);
        Item item = new Item("退出全屏", true, btn1, R.drawable.lishuimore13);
        leftlist.add(item);
        DiyLayout one = (DiyLayout) findViewById(R.id.leftlayout);
        one.setScreenWidth(screenWidth);
        one.setDatas(leftlist);
        one.setBackground(getResources().getDrawable(R.drawable.corner_bian));
        one.setLayoutAnimation(lac);
//        Typeface type = Typeface.createFromAsset(getAssets(), "arialbd.ttf");
/*
       item.setTypeface(type);*/
        /*右边部分*/
        String[] child={"大","中","小"};
        String[] other={"一","二","三"};
        rightlist= new ArrayList<Item>();
        final CustomTextView btn = new CustomTextView(this);
        final CustomTextView btn5 = new CustomTextView(this);
        Item d5 = new Item("字体", true, btn5, R.drawable.arrow,child);
        Item da= new Item("关闭", true, btn, R.drawable.del);
        rightlist.add(d5);
        rightlist.add(da);
        DiyLayout three = (DiyLayout) findViewById(R.id.rightlayout);
        three.setScreenWidth(screenWidth);
        three.setLeftwidth(allwidth(leftlist));
        three.setDatas(rightlist);
        three.setBackground(getResources().getDrawable(R.drawable.corner_bian));
       three.setLayoutAnimation(lac);
           /* 中间部分*/
        middlelist = new ArrayList<Item>();
        final CustomTextView btn2 = new CustomTextView(this);
        final CustomTextView btn3 = new CustomTextView(this);
        final CustomTextView btn4 = new CustomTextView(this);
        final CustomTextView btnb = new CustomTextView(this);
        final CustomTextView btnb1 = new CustomTextView(this);
        Item d2 = new Item("签到", true, btn2, R.drawable.tick);
        Item d3 = new Item("返回", true, btn3, R.drawable.back);
        Item d4 = new Item("开始汇报", true, btn4, R.drawable.tickone);
        Item dd = new Item("签到", true, btnb, R.drawable.htbj);
        Item dd1 = new Item("返回", true, btnb1, R.drawable.b);
        middlelist.add(d2);
        middlelist.add(d3);
        middlelist.add(d4);
        middlelist.add(dd);
        middlelist.add(dd1);
        DiyLayout two = (DiyLayout) findViewById(R.id.middlelayout);
        two.setScreenWidth(screenWidth);
        two.setLeftwidth(allwidth(leftlist));
        two.setRightwidth(allwidth(rightlist));
        two.setDatas(middlelist);
        two.setBackground(getResources().getDrawable(R.drawable.corner_bian));
        two.setLayoutAnimation(lac);
        one.setListener(new DiyLayout.MyListener() {

            @Override
            public void showTip(Item data) {
                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        two.setListener(new DiyLayout.MyListener() {
            public void showTip(Item data) {
                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        three.setListener(new DiyLayout.MyListener() {
            public void showTip(Item data) {
                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    //--------------------------
//    public  class Constant {
//        public static int displayWidth;  //屏幕宽度
//        public  static int displayHeight; //屏幕高度
//    }
    //--------------------------
    public int width(int a, int b) {
        int w = 0;
        int c, d = 0;
        c = (a * 3 - b) / 2;
        Log.i("a等于"+a,"b等于"+b);
        d = a - c;
        w = c * 30 + d * 60;
        return w;
    }
    //--------------------------
    public int allwidth(ArrayList<Item> list) {
        int w = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).getName();
            w = w + 48 +width(s.length(), s.getBytes().length);
        }
        return w;
    }
}
package android.com.testnewview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public ArrayList<Item> leftlist, middlelist, rightlist = null;
    int screenWidth, screenHeight, all;
    private ListView listView;
    private ChildAdapter childAdapter;
    //--------------------------
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽
       //左边部分--------------------------
        leftlist = new ArrayList<Item>();
        final CustomTextView btn1 = new CustomTextView(this);
        Item d = new Item("退出全屏", true, btn1, R.drawable.returncase);
        leftlist.add(d);
        DiyLayout a = (DiyLayout) findViewById(R.id.cl);
        a.setScreenWidth(screenWidth);
        a.setDatas(leftlist);
        /*右边部分*/
        String[] child={"大","中","小"};
        rightlist= new ArrayList<Item>();

        final CustomTextView btn = new CustomTextView(this);
        final CustomTextView btn5 = new CustomTextView(this);
        Item d5 = new Item("字体", true, btn5, R.drawable.arrow,child);
        Item da= new Item("关闭", true, btn, R.drawable.del);
        rightlist.add(d5);
        rightlist.add(da);
        DiyLayout a3 = (DiyLayout) findViewById(R.id.cl3);
        a3.setScreenWidth(screenWidth);
        a3.setWidth1(allwidth(leftlist));
        a3.setDatas(rightlist);
           /* 中间部分*/
        middlelist = new ArrayList<Item>();
        final CustomTextView btn2 = new CustomTextView(this);
        final CustomTextView btn3 = new CustomTextView(this);
        final CustomTextView btn4 = new CustomTextView(this);
        final CustomTextView btnb = new CustomTextView(this);
        final CustomTextView btnb1 = new CustomTextView(this);
        Item d2 = new Item("签到", true, btn2, R.drawable.dbicon72);
        Item d3 = new Item("返回", true, btn3, R.drawable.back);
        Item d4 = new Item("开始汇报", true, btn4, R.drawable.iconpendefault);
        Item dd = new Item("签到", true, btnb, R.drawable.b);
        Item dd1 = new Item("返回", true, btnb1, R.drawable.b);
        middlelist.add(d2);
        middlelist.add(d3);
        middlelist.add(d4);
        middlelist.add(dd);
        middlelist.add(dd1);
        DiyLayout a2 = (DiyLayout) findViewById(R.id.cl2);
        a2.setScreenWidth(screenWidth);
        a2.setWidth1(allwidth(leftlist));
        a2.setWidth2(allwidth(rightlist));
        a2.setDatas(middlelist);
        Log.i("screen", "screenWidth" + screenWidth + "screenHeight" + screenHeight);

        a.setListener(new DiyLayout.MyListener() {

            @Override
            public void showTip(Item data) {
                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        a2.setListener(new DiyLayout.MyListener() {
            @Override
            public void showTip(Item data) {
                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        a3.setListener(new DiyLayout.MyListener() {
            @Override
            public void showTip(Item data) {
                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int width(int a, int b) {
        int w = 0;
        int c, d = 0;
        c = (a * 3 - b) / 2;
        d = a - c;
        w = c * 30 + d * 60;
        return w;
    }

    public int allwidth(ArrayList<Item> list) {
        int w = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i).getName();
            w = w + 50 +width(s.length(), s.getBytes().length);
        }
        return w;
    }
}
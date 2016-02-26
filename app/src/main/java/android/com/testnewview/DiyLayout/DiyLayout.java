package android.com.testnewview.DiyLayout;

import android.com.testnewview.Entity.Item;
import android.com.testnewview.R;
import android.com.testnewview.Util;
import android.com.testnewview.adapter.ChildAdapter;
import android.com.testnewview.adapter.ItemAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;

public class DiyLayout extends LinearLayout implements OnClickListener {
	//屏幕宽度
	private int screenWidth,Leftwidth,Rightwidth;
	//子项适配器
	private ItemAdapter itemAdapter;
	private ChildAdapter childAdapter;
	private ListView listview;
	//数据
	ArrayList<Item> datas = null;
	ArrayList<Item> datas2 = null;
	int num ;
	Context context;
	//构造函数
	public DiyLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
		initView();
	}
	public DiyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		initView();
	}
	public DiyLayout(Context context) {
		super(context);
		this.context=context;
		initView();
	}
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	//构造函数
	public void setDatas(ArrayList<Item> datas) {
		this.datas = datas;
		setViews(datas);
	}
	private void initView() {
		this.setOrientation(HORIZONTAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		this.setLayoutParams(params);
		this.setBackground(getResources().getDrawable(R.drawable.corner_normal));
	}
	//--------------------------
	private void setViews( ArrayList<Item> datas) {
		num =Remain();
//		Log.i("aaaaa", "" + num);
		for (int i = 0; i < datas.size(); i++) {
			if(i<num) {
				CustomTextView customTextView = datas.get(i).getTv();
				customTextView.setText(datas.get(i).getName());
				Drawable drawable = getResources().getDrawable(datas.get(i).getId());
				drawable.setBounds(0, 0, 40, 80);

				customTextView.setCompoundDrawables(drawable, null, null, null);
				customTextView.setOnClickListener(this);
				customTextView.setTag(datas.get(i));
				customTextView.setTextSize(16);
				customTextView.setHeight(Util.px2dip(context,200));
				customTextView.setWidth(48 + width(datas.get(i).getName().length(), datas.get(i).getName().getBytes().length));
				this.addView(customTextView);
			}else if(i==num){
				datas2 = new ArrayList<>();
				datas2.add(datas.get(i));
				CustomTextView customTextView = new CustomTextView(getContext());
				customTextView.setText("更多");
//				customTextView.setTextSize(Util.px2dip(context, 16));
				customTextView.setOnClickListener(this);
				customTextView.setTag(datas.get(i));
				customTextView.setHeight(Util.px2dip(context,200));
				customTextView.setWidth(width(datas.get(i).getName().length(), datas.get(i).getName().getBytes().length));
				customTextView.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						showPopupWindow(v);
					}
				});
				this.addView(customTextView);
			}else if(i>=num){
				datas2.add(datas.get(i));
			}
		}
	}
	/*点击时候颜色变化*/
	public void onClick(View view) {
		Item data = (Item) view.getTag();
		if (data.isCheck()) {
			view.setBackground(getResources().getDrawable(R.drawable.corner_click));
//			view.setBackgroundColor(view.getResources().getColor(R.color.newcolor));
		}else{
			view.setBackground(getResources().getDrawable(R.drawable.normal));
////			view.setTextColor(convertView.getResources().getColor(R.color.blackuse));
//			view.setBackgroundColor(view.getResources().getColor(R.color.normal));
		}
		data.setCheck(!data.isCheck());
		if (listener != null) {
			listener.showTip(data);
		}
		if(data.getChild()!=null) {
			showChild(view,data.getChild());
		}
	}
	//--------------------------
	private MyListener listener;

	public MyListener getListener() {
		return listener;
	}

	public void setListener(MyListener listener) {
		this.listener = listener;
	}
	public interface MyListener {
		 void showTip(Item data);
	}
	//--------------------------
	public int width(int a, int b) {
		int w = 0;
		int c, d = 0;
		c = (a * 3 - b)/2;
		d = a - c;
		w = c * 30 + d * 60;
		return w;
	}
//中间控件的长度
	public int Remain(){
		int sw = screenWidth-Leftwidth-Rightwidth;
//		Log.i("aaaaa","screenWidth="+screenWidth+"width1="+Leftwidth+"width2="+Rightwidth);
		for(int i=0;i<datas.size();i++){
			int w =0;
			String s = datas.get(i).getName();
			w =48+width(s.length(),s.getBytes().length);
//			Log.i("aaaaa","sw="+sw+"w"+w);
			if (sw < w) {
				if(w>=60){
					return i;
				}else {
					return i-1;
				}
			}
			sw =sw-w;
		}
		return datas.size();
	};
	//--------------------------
	private void showPopupWindow(View view) {
		// 自定义的布局
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.listview, null);
		 itemAdapter=new ItemAdapter(context,datas2);
		listview=(ListView)contentView.findViewById(R.id.listview);
		listview.setAdapter(itemAdapter);

		final PopupWindow popupWindow = new PopupWindow(contentView,
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.corner_listview));
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		popupWindow.showAsDropDown(view,0,0);
//		popupWindow.setOnDismissListener();,  0, 0Gravity.CENTER,
	}
	// 子项的布局
	private void showChild(View view,String[] s) {
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.childlistview, null);
		childAdapter=new ChildAdapter(context,s);
		listview=(ListView)contentView.findViewById(R.id.childlistview);
		listview.setAdapter(childAdapter);

		final PopupWindow popupWindow = new PopupWindow(contentView,
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.corner_listview));
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		/*WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int xPos = -popupWindow.getWidth() / 2
				+ getCustomTitle().getCenter().getWidth() / 2;

		popupWindow.showAsDropDown(parent, xPos, 4);*/

		/*popupWindow.showAtLocation(view,  Gravity.NO_GRAVITY, 0, 0);*/
		popupWindow.showAsDropDown(view);
		/*, Gravity.CENTER, 0, 0*/
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public int getLeftwidth() {
		return Leftwidth;
	}
	public void setLeftwidth(int Leftwidth) {
		this.Leftwidth = Leftwidth;
	}
	public int getRightwidth() {
		return Rightwidth;
	}
	public void setRightwidth(int Rightwidth) {
		this.Rightwidth = Rightwidth;
	}

}
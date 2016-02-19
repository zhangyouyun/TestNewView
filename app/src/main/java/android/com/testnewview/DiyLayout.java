package android.com.testnewview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;

public class DiyLayout extends LinearLayout implements OnClickListener {
	//屏幕宽度
	private int screenWidth,width1,width2;
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

	private void setViews( ArrayList<Item> datas) {
		num =Remain();
		Log.i("aaaaa", "" + num);
		for (int i = 0; i < datas.size(); i++) {
			if(i<num) {
				CustomTextView btn = datas.get(i).getTv();
				btn.setText(datas.get(i).getName());
				Drawable drawable = getResources().getDrawable(datas.get(i).getId());
				drawable.setBounds(0, 0, 50, 70);
				btn.setCompoundDrawables(drawable, null, null, null);
				btn.setOnClickListener(this);
				btn.setTag(datas.get(i));
				/*控件高度*/
				btn.setHeight(200);
				btn.setWidth(50 + width(datas.get(i).getName().length(), datas.get(i).getName().getBytes().length));
				this.addView(btn);
			}else if(i==num){
				datas2 = new ArrayList<>();
				datas2.add(datas.get(i));
				CustomTextView customTextView = new CustomTextView(getContext());
				customTextView.setText("更多");
				customTextView.setOnClickListener(this);
				customTextView.setTag(datas.get(i));
				customTextView.setHeight(200);
				customTextView.setWidth(width(datas.get(i).getName().length(), datas.get(i).getName().getBytes().length));
				customTextView.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						showPopupWindow(v);
					}
				});
				this.addView(customTextView);
			}else {
				datas2.add(datas.get(i));
			}
		}
	}
	/*点击时候颜色变化*/
	public void onClick(View arg0) {
		Item data = (Item) arg0.getTag();
		if (data.isCheck()) {
			arg0.setBackground(getResources().getDrawable(R.drawable.corner_click));
		}else{
			arg0.setBackground(getResources().getDrawable(R.drawable.corner_bian));
		}
		data.setCheck(!data.isCheck());
		if (listener != null) {
			listener.showTip(data);
		}
		if(data.getChild()!=null) {
			showChild(arg0,data.getChild());
		}
	}

	private MyListener listener;
	public MyListener getListener() {
		return listener;
	}
	public void setListener(MyListener listener) {
		this.listener = listener;
	}
	public interface MyListener {
		public void showTip(Item data);
	}
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
		int sw = screenWidth-width1-width2;
		Log.i("aaaaa","screenWidth="+screenWidth+"width1="+width1+"width2="+width2);
		for(int i=0;i<datas.size();i++){
			int w =0;
			String s = datas.get(i).getName();
			w =50+width(s.length(),s.getBytes().length);
			Log.i("aaaaa","sw="+sw+"w"+w);
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
				R.drawable.corner_bian));
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		popupWindow.showAsDropDown(view, Gravity.CENTER, 0, 0);
//		popupWindow.setOnDismissListener();

	}
	private void showChild(View view,String[] s) {
		// 自定义的布局
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.listview, null);
		childAdapter=new ChildAdapter(context,s);
		listview=(ListView)contentView.findViewById(R.id.listview);
		listview.setAdapter(childAdapter);

		final PopupWindow popupWindow = new PopupWindow(contentView,
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.corner_bian));
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
		popupWindow.showAsDropDown(view, Gravity.CENTER, 0, 0);
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getWidth1() {
		return width1;
	}

	public void setWidth1(int width1) {
		this.width1 = width1;
	}

	public int getWidth2() {
		return width2;
	}

	public void setWidth2(int width2) {
		this.width2 = width2;
	}

}
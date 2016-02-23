package android.com.testnewview.DiyLayout;

import android.com.testnewview.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        initView(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    //--------------------------
    private void initView(Context context) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
                   /*   this.setBackgroundColor(getResources().getDrawable(R.drawable.corner_normal));*/
//        this.setBackground(getResources().getDrawable(R.drawable.corner_bian));
        this.setLayoutParams(params);
        this.setGravity(Gravity.CENTER);
        this.setTextSize(16);
//        this.setPadding(5, 5, 5, 5);
    }
}

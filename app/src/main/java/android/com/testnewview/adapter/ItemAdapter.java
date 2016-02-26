package android.com.testnewview.adapter;

import android.com.testnewview.DiyLayout.CustomTextView;
import android.com.testnewview.Entity.Item;
import android.com.testnewview.R;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
public class ItemAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Item> item;
    private CustomTextView customTextView;
    private LayoutInflater mInflater;
    static class ViewHolder {
        public TextView textView;
    }
    public ItemAdapter(Context context,ArrayList<Item> item) {
        this.context = context;
        this.customTextView=customTextView;
        this.item=item;
        this.mInflater = LayoutInflater.from(context);
    }
    //--------------------------
    public int getCount() {
        return item.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
       final Item i = item.get(position);
        ViewHolder holder=null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.button, null);
            holder.textView=(TextView)convertView.findViewById(R.id.text);
            /*Typeface type = Typeface.createFromAsset(getCon(), "arialbd.ttf");

            one.setTypeface (type);*/
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //--------------------------
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        holder.textView.setLayoutParams(params);
        holder.textView.setText(i.getName());
        holder.textView.setTextColor(convertView.getResources().getColor(R.color.blackuse));
        Drawable drawable = convertView.getResources().getDrawable(i.getId());
        drawable.setBounds(0, 0, 50, 70);
        holder.textView.setCompoundDrawables(drawable, null, null, null);
        holder.textView.setTextSize(16);
//        holder.textView.setBackground(convertView.getResources().getDrawable(R.drawable.corner_click));
        holder.textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, i.getName()+"", Toast.LENGTH_SHORT).show();
                    v.setBackground(v.getResources().getDrawable(R.drawable.selector));
            }
        });
//        holder.textView.setOnTouchListener();
        return convertView;
    }
}
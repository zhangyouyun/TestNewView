package android.com.testnewview.adapter;

import android.com.testnewview.Entity.Item;
import android.com.testnewview.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Auser on 2016/2/19.
 */
public class ChildAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater mInflater;
    String[] child;
//    ArrayList<Aa> aas = new ArrayList<>();
    private Item item;
    static class ViewHolder {
        public TextView textView;
    }
    //=new ArrayList<>()
    public ChildAdapter(Context context,String[] child2) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        child=child2;
    }
    //--------------------------
    public ChildAdapter(Context context) {
        this.context = context;
    }
    public int getCount() {
        return child.length;
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        final String s = child[position];
        ViewHolder holder=null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.button, null);
            holder.textView=(TextView)convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //--------------------------child+
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        holder.textView.setLayoutParams(params);
        holder.textView.setText(child[position]);
        holder.textView.setGravity(17);
        holder.textView.setTextSize(16);
        holder.textView.setPadding(40, 0, 40, 0);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if (item.isCheck()) {
//                    v.setBackground(v.getResources().getDrawable(R.drawable.corner_click));
//                } else {
//                    v.setBackground(v.getResources().getDrawable(R.drawable.normal));
//                }
                Toast.makeText(context, s + "", Toast.LENGTH_SHORT).show();


            }
        });

        return convertView;
    }
}

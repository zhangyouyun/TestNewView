package android.com.testnewview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Auser on 2016/2/19.
 */
public class ChildAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater mInflater;
    String[] child;
    static class ViewHolder {
        public TextView textView;
    }


    //=new ArrayList<>()
    public ChildAdapter(Context context,String[] child2) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        child=child2;
    }
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
        holder.textView.setText(child[position]);
        return convertView;
    }
}

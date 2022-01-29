package as.programmer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Website> websites;

    public GridViewAdapter(Context context, ArrayList<Website> websites){
        this.context = context;
        this.websites = websites;
    }

    @Override
    public int getCount() {
        return websites.size();
    }

    @Override
    public Object getItem(int position) {
        return websites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_item, null, false);
        ImageView websiteImage = convertView.findViewById(R.id.image_view_icon);
        TextView websiteName = convertView.findViewById(R.id.text_view_title);
        Website website = websites.get(position);
        websiteImage.setImageResource(website.getImage());
        websiteName.setText(website.getName());
        return convertView;
    }

}

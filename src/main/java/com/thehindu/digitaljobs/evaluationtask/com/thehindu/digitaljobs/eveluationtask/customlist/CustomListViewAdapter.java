package com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.eveluationtask.customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.thehindu.digitaljobs.evaluationtask.MapDisplayActivity;
import com.thehindu.digitaljobs.evaluationtask.R;

import java.util.List;

/**
 * Created by karthika on 29-05-2015.
 */
public class CustomListViewAdapter extends ArrayAdapter<ListModel> {
    Context context;

    public CustomListViewAdapter(Context context, int resource, List<ListModel> objects) {
        super(context, resource, objects);
        this.context = context;
    }
    private class ViewHolder {
        TextView prjtID;
        TextView prjtName;
        ImageView mapIcon;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
       final  ListModel ListItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_details, null);
            holder = new ViewHolder();
            holder.prjtID = (TextView) convertView
                    .findViewById(R.id.pjtid);
            holder.prjtName = (TextView) convertView
                    .findViewById(R.id.pjtname);
            holder.mapIcon=(ImageView)convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
try {
    holder.prjtID.setText(ListItem.getId() + "");
    holder.prjtName.setText(ListItem.getProjectName());
    holder.mapIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.map));
    holder.mapIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),
                    "Map view to be implemented",
                    Toast.LENGTH_LONG).show();
            context.startActivity(new Intent(context.getApplicationContext(),MapDisplayActivity.class)
                    .putExtra("latitude", ListItem.getLatitude())
                    .putExtra("longitude", ListItem.getLongitude())
                    .putExtra("pjtName", ListItem.getProjectName()));
        }
    });
}catch (Exception e){
    e.getMessage();
}
        return convertView;
    }
}

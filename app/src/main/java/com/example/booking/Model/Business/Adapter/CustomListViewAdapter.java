package com.example.booking.Model.Business.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booking.Model.Entities.Concrete.Property;
import com.example.booking.R;

import java.util.ArrayList;
public class CustomListViewAdapter extends ArrayAdapter<Property> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Property> properties;

    public CustomListViewAdapter(Context context, ArrayList<Property> properties) {
        super(context,0, properties);
        this.context = context;
        this.properties = properties;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return properties.size();
    }

    @Override
    public Property getItem(int position) {
        return properties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return properties.get(position).hashCode();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_item, null);
            holder = new ViewHolder();
            holder.propertyCity = (TextView) convertView.findViewById(R.id.listPropertyCity);
            holder.propertyCountry = (TextView) convertView.findViewById(R.id.listPropertyCountry);
            holder.propertyImage = (ImageView) convertView.findViewById(R.id.listPropertyImage);
            holder.propertyPrice = (TextView) convertView.findViewById(R.id.listPropertyPrice);
            holder.propertyTittle = (TextView) convertView.findViewById(R.id.listPropertyTittle);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        Property property = properties.get(position);

        if(property != null){
            byte[] imgArray=property.getImage();
            Bitmap bitmap= BitmapFactory.decodeByteArray(imgArray,0,imgArray.length);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
            holder.propertyImage.setImageBitmap(resizedBitmap);
            holder.propertyCity.setText(property.getCityName());
            holder.propertyCountry.setText(property.getCountryName());
            holder.propertyTittle.setText(property.getTittle());
            holder.propertyPrice.setText(String.valueOf(property.getPrice()));
        }
        return convertView;
    }
}

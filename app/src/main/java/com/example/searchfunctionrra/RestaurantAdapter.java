package com.example.searchfunctionrra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> restaurantTypes;
    private ArrayList<String> originalList;
    private Filter restaurantFilter;

    public RestaurantAdapter(Context context, ArrayList<String> restaurantTypes) {
        super(context, R.layout.list_item_restaurant, restaurantTypes);
        this.context = context;
        this.restaurantTypes = restaurantTypes;
        this.originalList = new ArrayList<>(restaurantTypes);
        setupFilter();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_restaurant, parent, false);
        }

        ImageView restaurantIcon = convertView.findViewById(R.id.restaurantIcon);
        TextView restaurantName = convertView.findViewById(R.id.restaurantName);

        String name = restaurantTypes.get(position);
        restaurantName.setText(name);

        restaurantIcon.setImageResource(R.drawable.ic_launcher_background);

        return convertView;
    }

    private void setupFilter() {
        restaurantFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(originalList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (String item : originalList) {
                        if (item.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                restaurantTypes.clear();
                restaurantTypes.addAll((List<String>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public Filter getFilter() {
        return restaurantFilter;
    }

}

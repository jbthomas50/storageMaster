package com.example.storagemaster.storagemaster;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ME on 3/16/2018.
 */

public class ShoppingListAdapter extends ArrayAdapter<Item> {
    private final Activity context;
    private final ArrayList<Item> itemList;

    private static final String TAG = "ShoppingListAdapter";

    private int ID = 0;

    public ShoppingListAdapter(Activity context, ArrayList<Item> itemList) {
        super(context, R.layout.shoppingitem, itemList);
        this.context = context;
        this.itemList = itemList;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        ShoppingListAdapter.ViewHolder mainViewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.shoppingitem, null, true);
        ViewHolder viewHolder = new ViewHolder();

        TextView txtTitle = (TextView) view.findViewById(R.id.itemname);
        final TextView quantityView = (TextView) view.findViewById(R.id.quantity);

        txtTitle.setText(itemList.get(position).getItemName());
        quantityView.setText("" + itemList.get(position).getQuantity());

        viewHolder.name = (TextView) view.findViewById(R.id.itemname);
        viewHolder.subtractB = (Button) view.findViewById(R.id.subtractbutton);
        viewHolder.qty = (TextView) view.findViewById(R.id.quantity);
        viewHolder.addB = (Button) view.findViewById(R.id.addbutton);

        viewHolder.subtractB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.get(position).setQuantity(itemList.get(position).getQuantity() - 1);
                quantityView.setText("" + itemList.get(position).getQuantity());
            }
        });

        viewHolder.addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.get(position).setQuantity(itemList.get(position).getQuantity() + 1);
                quantityView.setText("" + itemList.get(position).getQuantity());
            }
        });

        // JAMES - added long click to both add button and subtract button.
        // when long clicked, the buttons will open the slider to edit the quantity of the items
        viewHolder.subtractB.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, SlideBarActivity.class);
                intent.putExtra(MainActivity.POSI, Integer.toString(position));
                intent.putExtra(MainActivity.POSC, Integer.toString(ID));
                context.startActivity(intent);
                return false;
            }
        });

        viewHolder.addB.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, SlideBarActivity.class);
                intent.putExtra(MainActivity.POSI, Integer.toString(position));
                intent.putExtra(MainActivity.POSC, Integer.toString(ID));
                context.startActivity(intent);
                return false;
            }
        });
        view.setTag(viewHolder);


        return view;

    };

    public class ViewHolder {
        TextView name;
        Button subtractB;
        TextView qty;
        Button addB;
    }

    public void setID(int id){
        this.ID = id;
    }
}

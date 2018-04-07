package com.example.storagemaster.storagemaster;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 3/16/2018.
 * This adapter takes a list of items and displays them with a x and + button
 * and a dynamic quantity counter. Displays them on the selected listview.
 * Used for the shopping list
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

        TextView txtTitle = view.findViewById(R.id.itemname);
        final TextView quantityView = view.findViewById(R.id.quantity);

        txtTitle.setText(itemList.get(position).getItemName());
        quantityView.setText("" + itemList.get(position).getQuantity());

        viewHolder.name = view.findViewById(R.id.itemname);
        viewHolder.crossOutB = view.findViewById(R.id.crossoutbutton);
        viewHolder.qty =  view.findViewById(R.id.quantity);
        viewHolder.addB2 = view.findViewById(R.id.addbutton2);

        viewHolder.name.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (!MainActivity.isWindowOpen) {
                    MainActivity.isWindowOpen = true;
                    Intent intent = new Intent(context, NewItem.class);
                    intent.putExtra(MainActivity.POSI, Integer.toString(position));
                    intent.putExtra(MainActivity.POSC, Integer.toString(ID));
                    context.startActivity(intent);
                }
            }
        });

        // JAMES - added click to both add button 2 and cross out button.
        // when clicked, the add button 2 will open the slider to edit the
        // quantity of the items, and the cross out button will cross out
        // the item.
        viewHolder.crossOutB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i(TAG, "item crossed out");
                if(!MainActivity.user.inventory.get(ID).items.get(position).getCrossed()){
                   MainActivity.user.inventory.get(ID).crossItem(position);
                }
                else{
                    MainActivity.user.inventory.get(ID).uncrossItem(position);
                }
                MainActivity.adapterShopping.notifyDataSetChanged();
            }
        });

        viewHolder.addB2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i(TAG, "adding to quantity");
                Intent intent = new Intent(context, SlideBarActivity.class);
                intent.putExtra(MainActivity.POSI, Integer.toString(position));
                intent.putExtra(MainActivity.POSC, Integer.toString(ID));
                context.startActivity(intent);
            }
        });
        view.setTag(viewHolder);


        return view;

    };

    public class ViewHolder {
        TextView name;
        Button crossOutB;
        TextView qty;
        Button addB2;
    }

    public void setID(int id){
        this.ID = id;
    }
}

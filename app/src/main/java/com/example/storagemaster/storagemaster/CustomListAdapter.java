package com.example.storagemaster.storagemaster;

/**
 * Created by Alex the one and only on 2/27/2018.
 */

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<Integer> quantity;

    public CustomListAdapter(Activity context, ArrayList<String> itemname, ArrayList<Integer> quantity) {
        super(context, R.layout.listitem, itemname);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.itemname=itemname;
        this.quantity=quantity;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder mainViewHolder = null;
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.listitem, null, true);
            ViewHolder viewHolder = new ViewHolder();

            TextView txtTitle = (TextView) view.findViewById(R.id.itemname);
            final TextView quantityView = (TextView) view.findViewById(R.id.quantity);

            txtTitle.setText(itemname.get(position));
            quantityView.setText("" + quantity.get(position));

            viewHolder.name = (TextView) view.findViewById(R.id.itemname);
            viewHolder.subtractB = (Button) view.findViewById(R.id.subtractbutton);
            viewHolder.qty = (TextView) view.findViewById(R.id.quantity);
            viewHolder.addB = (Button) view.findViewById(R.id.addbutton);

            viewHolder.subtractB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quantity.set(position, quantity.get(position) - 1);
                    quantityView.setText("" + quantity.get(position));
                }
            });

            viewHolder.addB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    quantity.set(position, quantity.get(position) + 1);
                    quantityView.setText("" + quantity.get(position));
                }
            });
            view.setTag(viewHolder);
        }
        else {
                mainViewHolder = (ViewHolder) view.getTag();
                mainViewHolder.name.setText(getItem(position));
            }
        return view;

    };

    public class ViewHolder {
        TextView name;
        Button subtractB;
        TextView qty;
        Button addB;
    }
}

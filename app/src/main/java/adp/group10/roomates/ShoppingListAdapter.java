package adp.group10.roomates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ninas Dator on 2017-03-31.
 */

public class ShoppingListAdapter extends ArrayAdapter<ShoppingListEntryData> {

    public ShoppingListAdapter(@NonNull Context context, @LayoutRes int resource,
            @NonNull List<ShoppingListEntryData> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.shopping_list_entry_data_item, parent, false);
        }

        TextView tvName = (TextView) view.findViewById(R.id.entryName);
        TextView tvAmount = (TextView) view.findViewById(R.id.entryAmount);
        ShoppingListEntryData item = getItem(position);
        tvName.setText(item.getName());
        tvAmount.setText(item.getAmount());


        Button editButton = (Button) view.findViewById(R.id.editButton);
        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);
        Button blockButton = (Button) view.findViewById(R.id.blockButton);
        Button buyButton = (Button) view.findViewById(R.id.buyButton);
        editButton.setOnClickListener(editButtonClickHandler);
        deleteButton.setOnClickListener(deleteButtonClickHandler );
        blockButton.setOnClickListener(blockButtonClickHandler);
        buyButton.setOnClickListener(buyButtonClickHandler);

        return view;
    }

    View.OnClickListener editButtonClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
            LinearLayout layout = (LinearLayout) v.getParent().getParent();
            TextView nameView = (TextView) layout.findViewById(R.id.entryName);
            TextView amountView = (TextView) layout.findViewById(R.id.entryAmount);
            String name = nameView.getText().toString();
            int amount = Integer.parseInt(amountView.getText().toString());

            Intent intent = new Intent(getContext(), AddEditItemActivity.class);
            Bundle b = new Bundle();
            b.putString("oldName", name);
            b.putInt("oldAmount", amount);
            intent.putExtras(b);
            getContext().startActivity(intent);
        }
    };

    View.OnClickListener deleteButtonClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
            LinearLayout layout = (LinearLayout) v.getParent().getParent();
            TextView nameView = (TextView) layout.findViewById(R.id.entryName);
            TextView amountView = (TextView) layout.findViewById(R.id.entryAmount);
            String name = nameView.getText().toString();
            int amount = Integer.parseInt(amountView.getText().toString());

            EntryData.deleteEntry(name, amount);

            notifyDataSetChanged();
        }
    };

    View.OnClickListener blockButtonClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };

    View.OnClickListener buyButtonClickHandler = new View.OnClickListener() {
        public void onClick(View v) {
        }
    };
}

package com.example.packit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packit.database_managing.DatabaseHelper;
import com.example.packit.R;
import com.example.packit.classes.Item;

import java.util.List;

public class ItemCheckAdapter extends RecyclerView.Adapter<ItemCheckAdapter.ViewHolder> {
    private List<Item> items;
    private DatabaseHelper db;
    private Context appContext;

    public ItemCheckAdapter(Context context, List<Item> Items) {
        this.items = Items;
        this.appContext = context;
        this.db = DatabaseHelper.instanceOfDatabase(appContext);
        //this.activity = activity;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkbox_card, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Item item = items.get(position);
        holder.checkBox.setText(item.getName());

        holder.checkBox.setChecked(item.getChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    item.setChecked(true);
                    db.UpdateItemInDB(item);
                    //IKD if I should add that feature or not...
                    //db.deleteAllJunctionsWithItem(item);
                } else {
                    item.setChecked(false);
                    db.UpdateItemInDB(item);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public Object getItem(int position)
    {
        return items.get(position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.CheckBox);
        }
    }
}

package com.example.packit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemCheckAdapter extends RecyclerView.Adapter<ItemCheckAdapter.ViewHolder> {
    private List<Item> items;
    private DatabaseHelper db;
    private Context appContext;
    //private MainActivity activity;

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
        //db.openDatabase();

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
    }/*

    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.CheckBox);
        }
    }
}

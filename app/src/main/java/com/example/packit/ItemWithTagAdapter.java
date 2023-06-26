package com.example.packit;

        import android.annotation.SuppressLint;
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

public class ItemWithTagAdapter extends RecyclerView.Adapter<ItemWithTagAdapter.ViewHolder> {
    private List<Item> items;
    private DatabaseHelper db;
    private Context appContext;
    private Tag selectedTag;

    public ItemWithTagAdapter(Context context, List<Item> Items, Tag tag) {
        this.items = Items;
        this.appContext = context;
        this.db = DatabaseHelper.instanceOfDatabase(appContext);
        this.selectedTag = tag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkbox_card, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //db.openDatabase();

        final Item item = items.get(position);
        holder.checkBox.setText(item.getName());

        holder.checkBox.setChecked(false);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    items.remove(item);
                    notifyItemRemoved(position);
                    db.deleteJunction(item, selectedTag);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.CheckBox);
        }
    }
}
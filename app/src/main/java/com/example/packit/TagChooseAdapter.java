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

public class TagChooseAdapter extends RecyclerView.Adapter<TagChooseAdapter.ViewHolder> {
    private List<Tag> tags;
    private DatabaseHelper db;
    private Context appContext;
    private Item item;

    public TagChooseAdapter(Context context, List<Tag> Tags, Item selectedItem) {
        this.tags = Tags;
        this.appContext = context;
        this.db = DatabaseHelper.instanceOfDatabase(appContext);
        this.item = selectedItem;
        //this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkbox_card, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        //db.openDatabase();

        final Tag tag = tags.get(position);
        holder.checkBox.setText(tag.getName());

        holder.checkBox.setChecked(db.isItemInTag(item,tag));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return tags.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.CheckBox);
        }
    }
}

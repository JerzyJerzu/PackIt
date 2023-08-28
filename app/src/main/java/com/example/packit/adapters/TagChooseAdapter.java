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
        import com.example.packit.classes.Tag;

        import java.util.ArrayList;
        import java.util.List;

public class TagChooseAdapter extends RecyclerView.Adapter<TagChooseAdapter.ViewHolder> {
    private List<Tag> tags;
    public List<Tag> TempSelectedTags;
    private DatabaseHelper db;
    private Context appContext;
    private Item item;

    public TagChooseAdapter(Context context, List<Tag> Tags, Item selectedItem) {
        this.tags = Tags;
        this.appContext = context;
        this.db = DatabaseHelper.instanceOfDatabase(appContext);
        this.item = selectedItem;
        this.TempSelectedTags = new ArrayList<Tag>();
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

        final Tag tag = tags.get(position);
        holder.checkBox.setText(tag.getName());

        holder.checkBox.setChecked(db.isItemInTag(item,tag));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(item != null) {db.addJunction(tag, item);}
                    else {TempSelectedTags.add(tag);}
                } else {
                    db.deleteJunction(item, tag);
                    if(item != null) {db.deleteJunction(item, tag);}
                    else {TempSelectedTags.remove(tag);}
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

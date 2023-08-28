package com.example.packit.views_helpers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packit.activities.MainItemsList;
import com.example.packit.adapters.ItemCheckAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ItemCheckAdapter adapter;
    private MainItemsList activity;
    public RecyclerItemTouchHelper(ItemCheckAdapter adapter, MainItemsList activity) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);

        this.activity = activity;
        this.adapter = adapter;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }
    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT)
        {
            activity.moveToEditItem(position);
        }
    }
    @Override
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeDirs = super.getSwipeDirs(recyclerView, viewHolder);

        // Disable left swipe for all items
        swipeDirs &= ~ItemTouchHelper.RIGHT; // Remove the LEFT swipe direction

        return swipeDirs;
    }
}

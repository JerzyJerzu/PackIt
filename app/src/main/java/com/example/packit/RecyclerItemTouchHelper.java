package com.example.packit;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ItemCheckAdapter adapter;
    private AcitivityMainItemsList activity;
    public RecyclerItemTouchHelper(ItemCheckAdapter adapter, AcitivityMainItemsList activity) {
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

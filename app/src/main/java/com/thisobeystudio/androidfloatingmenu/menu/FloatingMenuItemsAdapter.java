package com.thisobeystudio.androidfloatingmenu.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thisobeystudio.androidfloatingmenu.R;

import java.util.ArrayList;

/**
 * Created by thisobeystudio on 9/11/17.
 * Copyright: (c) 2017 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

public class FloatingMenuItemsAdapter
        extends RecyclerView.Adapter<FloatingMenuItemsAdapter.MenuItemViewHolder> {

    public interface MenuItemCallbacks {
        @SuppressWarnings("unused")
        void onFloatingMenuItemClick(int pos, String title);
    }

    // menu item onclick callbacks
    private MenuItemCallbacks mCallbacks;

    // context
    private final Context mContext;

    // recipes array
    private final ArrayList<FloatingMenuItem> menuItems;

    class MenuItemViewHolder extends RecyclerView.ViewHolder {

        final TextView menuItemTextView;

        MenuItemViewHolder(View itemView) {
            super(itemView);
            menuItemTextView = itemView.findViewById(R.id.menu_item_text_view);

            if (menuItemTextView == null) {
                throw new RuntimeException("No TextView found with id = 'menu_item_text_view'");
            }
        }
    }

    FloatingMenuItemsAdapter(Context context,
                             final ArrayList<FloatingMenuItem> menuItems) {
        this.mContext = context;
        this.menuItems = menuItems;
    }

    @Override
    public int getItemCount() {
        if (menuItems == null) return 0;
        return menuItems.size();
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MenuItemViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuItemViewHolder holder, int i) {

        if (mContext != null
                && menuItems != null
                && menuItems.get(i)
                != null && !menuItems.get(i).getTitle().isEmpty()) {

            final String menuItemText = menuItems.get(i).getTitle();
            // set menu item text
            holder.menuItemTextView.setText(menuItemText);

            // set Text View Compound Drawable
            setTextViewCompoundDrawable(holder.menuItemTextView, menuItems.get(i).getIcon());

            final int pos = i;
            holder.menuItemTextView.setOnClickListener(view -> {
                if (mCallbacks != null) {
                    mCallbacks.onFloatingMenuItemClick(pos, menuItemText);
                }
            });
        }

    }

    /**
     * @param callbacks menu item click callback
     */
    void setCallbacks(MenuItemCallbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    /**
     * @param textView target text view
     * @param drawable compound drawable icon
     */
    private void setTextViewCompoundDrawable(TextView textView, int drawable) {

        int noDrawable = 0;

        // set menu item icon
        textView.setCompoundDrawablesWithIntrinsicBounds(
                drawable,                           // left
                noDrawable,                         // top
                noDrawable,                         // right
                noDrawable);                        // bottom


    }

}
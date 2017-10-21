package com.thisobeystudio.androidfloatingmenu.menu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thisobeystudio.androidfloatingmenu.R;

import java.util.ArrayList;

/**
 * Created by thisobeystudio on 20/10/17.
 * Copyright: (c) 2017 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

public class FloatingMenuItemsAdapter extends RecyclerView.Adapter<FloatingMenuItemsAdapter.MenuItemViewHolder> {

    public interface MenuItemCallbacks {
        void onFloatingMenuItemClick(int pos);
    }

    // onclick callbacks
    private MenuItemCallbacks mCallbacks;

    private Context mContext;

    // recipes array
    private final ArrayList<FloatingMenuItem> menuItems;

    // menu items icon position
    private FloatingMenu.MenuIconPosition mMenuIconPosition;

    private int mDrawablePadding;

    class MenuItemViewHolder extends RecyclerView.ViewHolder {

        TextView menuItemTextView;

        MenuItemViewHolder(View itemView) {
            super(itemView);
            menuItemTextView = itemView.findViewById(R.id.menu_item_text_view);
        }
    }

    public FloatingMenuItemsAdapter(Context context, ArrayList<FloatingMenuItem> menuItems, FloatingMenu.MenuIconPosition menuIconPosition, int drawablePadding) {
        this.mContext = context;
        this.menuItems = menuItems;
        this.mMenuIconPosition = menuIconPosition;
        this.mDrawablePadding = drawablePadding;
    }

    @Override
    public int getItemCount() {
        if (menuItems == null) return 0;
        return menuItems.size();
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MenuItemViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final MenuItemViewHolder viewHolder, int i) {

        if (mContext != null
                && menuItems != null
                && menuItems.get(i)
                != null && !menuItems.get(i).getTitle().isEmpty()) {

            String menuItemText = menuItems.get(i).getTitle();
            viewHolder.menuItemTextView.setText(menuItemText);

            viewHolder.menuItemTextView.setCompoundDrawablePadding(mDrawablePadding);

            int noDrawable = 0;

            switch (mMenuIconPosition) {
                case NONE:
                    viewHolder.menuItemTextView.setCompoundDrawablesWithIntrinsicBounds(
                            noDrawable,                         // left
                            noDrawable,                         // top
                            noDrawable,                         // right
                            noDrawable);                        // bottom
                    break;
                case LEFT:
                    viewHolder.menuItemTextView.setCompoundDrawablesWithIntrinsicBounds(
                            menuItems.get(i).getIcon(),
                            noDrawable,
                            noDrawable,
                            noDrawable);
                    break;
                case TOP:
                    viewHolder.menuItemTextView.setCompoundDrawablesWithIntrinsicBounds(
                            noDrawable,
                            menuItems.get(i).getIcon(),
                            noDrawable,
                            noDrawable);
                    break;
                case RIGHT:
                    viewHolder.menuItemTextView.setCompoundDrawablesWithIntrinsicBounds(
                            noDrawable,
                            noDrawable,
                            menuItems.get(i).getIcon(),
                            noDrawable);
                    break;
                case BOTTOM:
                    viewHolder.menuItemTextView.setCompoundDrawablesWithIntrinsicBounds(
                            noDrawable,
                            noDrawable,
                            noDrawable,
                            menuItems.get(i).getIcon());
                    break;
                default:
                    break;
            }

            // ad shadow to menu item text view
            viewHolder.menuItemTextView.setShadowLayer(5, -1, 3, Color.BLUE); // fixme move to demofloatingmenu

            final int pos = i;
            viewHolder.menuItemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallbacks != null) {
                        mCallbacks.onFloatingMenuItemClick(pos);
                    }
                }
            });
        }

    }

    // sets recipe click callback
    public void setCallbacks(MenuItemCallbacks callbacks) {
        this.mCallbacks = callbacks;
    }


}
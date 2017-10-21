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

public class FloatingMenuItemsAdapter
        extends RecyclerView.Adapter<FloatingMenuItemsAdapter.MenuItemViewHolder> {

    public interface MenuItemCallbacks {
        void onFloatingMenuItemClick(int pos);
    }

    // menu item onclick callbacks
    private MenuItemCallbacks mCallbacks;

    // context
    private Context mContext;

    // recipes array
    private final ArrayList<FloatingMenuItem> menuItems;

    // menu items icon position
    private FloatingMenu.MenuIconPosition mMenuIconPosition;

    class MenuItemViewHolder extends RecyclerView.ViewHolder {

        TextView menuItemTextView;

        MenuItemViewHolder(View itemView) {
            super(itemView);
            menuItemTextView = itemView.findViewById(R.id.menu_item_text_view);
        }
    }

    FloatingMenuItemsAdapter(Context context,
                             final ArrayList<FloatingMenuItem> menuItems,
                             final FloatingMenu.MenuIconPosition menuIconPosition) {
        this.mContext = context;
        this.menuItems = menuItems;
        this.mMenuIconPosition = menuIconPosition;
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
    public void onBindViewHolder(final MenuItemViewHolder holder, int i) {

        if (mContext != null
                && menuItems != null
                && menuItems.get(i)
                != null && !menuItems.get(i).getTitle().isEmpty()) {

            String menuItemText = menuItems.get(i).getTitle();
            // set menu item text
            holder.menuItemTextView.setText(menuItemText);

            // set Text View Compound Drawable
            setTextViewCompoundDrawable(holder.menuItemTextView, menuItems.get(i).getIcon());

            // ad shadow to menu item text view
            holder.menuItemTextView.setShadowLayer(5, -1, 3, Color.BLUE); // fixme move to demo floating menu

            final int pos = i;
            holder.menuItemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCallbacks != null) {
                        mCallbacks.onFloatingMenuItemClick(pos);
                    }
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
    private void setTextViewCompoundDrawable(TextView textView, int drawable){

        int noDrawable = 0;

        // set menu item icon
        switch (mMenuIconPosition) {
            case NONE:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        noDrawable,                         // left
                        noDrawable,                         // top
                        noDrawable,                         // right
                        noDrawable);                        // bottom
                break;
            case LEFT:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        drawable,
                        noDrawable,
                        noDrawable,
                        noDrawable);
                break;
            case TOP:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        noDrawable,
                        drawable,
                        noDrawable,
                        noDrawable);
                break;
            case RIGHT:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        noDrawable,
                        noDrawable,
                        drawable,
                        noDrawable);
                break;
            case BOTTOM:
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        noDrawable,
                        noDrawable,
                        noDrawable,
                        drawable);
                break;
            default:
                break;
        }

    }

}
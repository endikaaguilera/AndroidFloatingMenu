package com.thisobeystudio.androidfloatingmenu;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.thisobeystudio.androidfloatingmenu.menu.FloatingMenu;
import com.thisobeystudio.androidfloatingmenu.menu.FloatingMenuItem;
import com.thisobeystudio.androidfloatingmenu.menu.FloatingMenuItemsAdapter;

import java.util.ArrayList;

/**
 * Created by thisobeystudio on 20/10/17.
 * Copyright: (c) 2017 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

public class MultiMenuDemoActivity extends AppCompatActivity {

    public ConstraintLayout mParent01, mParent02, mParent03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimenu_demo);
        mParent01 = (ConstraintLayout) findViewById(R.id.multi_menu_container_01);
        mParent02 = (ConstraintLayout) findViewById(R.id.multi_menu_container_02);
        mParent03 = (ConstraintLayout) findViewById(R.id.multi_menu_container_03);
    }

    private void showFloatingMenu(ConstraintLayout parent) {
        final FloatingMenu floatingMenu = new FloatingMenu();

        final FloatingMenu.MenuIconPosition menuIconPosition = FloatingMenu.MenuIconPosition.LEFT;

        FloatingMenuItemsAdapter.MenuItemCallbacks callbacks = new FloatingMenuItemsAdapter.MenuItemCallbacks() {
            @Override
            public void onFloatingMenuItemClick(int pos) {
                Toast.makeText(MultiMenuDemoActivity.this, "Demo menu item clicked pos = " + pos, Toast.LENGTH_SHORT).show();
                floatingMenu.removeMenu();
            }
        };

        floatingMenu.showFloatingMenu(
                MultiMenuDemoActivity.this,
                parent,
                callbacks,
                demoData(),
                menuIconPosition);

    }

    private ArrayList<FloatingMenuItem> demoData() {
        final int happyIcon = R.drawable.ic_happy;
        final int sadIcon = R.drawable.ic_sad;
        ArrayList<FloatingMenuItem> demoMenuItems = new ArrayList<>();
        int totalItems = 15;
        for (int i = 0; i < totalItems; i++) {
            FloatingMenuItem menuItem;
            if (i % 2 == 0) {
                menuItem = new FloatingMenuItem("Demo Menu Item" + i, happyIcon);
            } else {
                menuItem = new FloatingMenuItem("Demo Menu Item" + i, sadIcon);
            }
            demoMenuItems.add(menuItem);
        }
        return demoMenuItems;
    }

    // MainActivity demo button that shows a floating menu
    public void onMultiDemoButtonClick(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        switch (tag) {
            case 1:
                showFloatingMenu(mParent01);
                break;
            case 2:
                showFloatingMenu(mParent02);
                break;
            case 3:
                showFloatingMenu(mParent03);
                break;
        }
    }

}

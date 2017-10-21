package com.thisobeystudio.androidfloatingmenu;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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

public class DemoActivity extends AppCompatActivity
        implements FloatingMenuItemsAdapter.MenuItemCallbacks {

    public ConstraintLayout mParent;
    private FloatingMenu mFloatingMenu = new FloatingMenu();

    // used to retain floating menu visibility on orientation change
    private final String IS_MENU_VISIBLE = "is_menu_visible";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mParent = (ConstraintLayout) findViewById(R.id.main_container);

        if (savedInstanceState != null
                && savedInstanceState.containsKey(IS_MENU_VISIBLE)) {

            if (savedInstanceState.getBoolean(IS_MENU_VISIBLE)) {
                showFloatingMenu();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_show_menu) {
            if (mFloatingMenu.isVisible()) {
                mFloatingMenu.removeMenu();
            } else {
                showFloatingMenu();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save floating menu visibility state
        outState.putBoolean(IS_MENU_VISIBLE, mFloatingMenu.isVisible());
    }

    @Override
    public void onBackPressed() {

        if (mFloatingMenu.isVisible() && mFloatingMenu.isCancelable()) mFloatingMenu.removeMenu();
        else super.onBackPressed();

    }

    @Override
    public void onFloatingMenuItemClick(int pos) {
        Toast.makeText(this, "Demo menu item clicked pos = " + pos, Toast.LENGTH_SHORT).show();
        mFloatingMenu.removeMenu();
    }

    private void showFloatingMenu() {

        final FloatingMenu.MenuIconPosition menuIconPosition = FloatingMenu.MenuIconPosition.LEFT;

        mFloatingMenu.showFloatingMenu(
                DemoActivity.this,
                mParent,
                DemoActivity.this,
                demoData(),
                menuIconPosition);

        final String demoHeaderTitle = "DEMO MENU";
        mFloatingMenu.setHeaderTitle(demoHeaderTitle);

        mFloatingMenu.setHeaderOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoActivity.this, "Menu Header Clicked", Toast.LENGTH_SHORT).show();
            }
        });

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
    public void onDemoButtonClick(View view) {
        showFloatingMenu();
    }

}

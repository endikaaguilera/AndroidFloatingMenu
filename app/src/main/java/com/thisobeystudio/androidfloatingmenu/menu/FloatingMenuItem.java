package com.thisobeystudio.androidfloatingmenu.menu;

/**
 * Created by thisobeystudio on 20/10/17.
 * Copyright: (c) 2017 ThisObey Studio
 * Contact: thisobeystudio@gmail.com
 */

public class FloatingMenuItem {

    String title;
    int icon;

    public FloatingMenuItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

}

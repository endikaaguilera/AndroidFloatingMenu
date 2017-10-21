# AndroidFloatingMenu
Android Simple Centered Floating Menu

# Basic Usage

``private FloatingMenu mFloatingMenu = new FloatingMenu();``

``mFloatingMenu.showFloatingMenu(YourActivity.this, parentConstraintLayout, callbacks, demoData(), menuIconPosition, menuItemIconPadding);``
                
``mFloatingMenu.setMenuProperties(menuWidth, menuHeight, menuCornerRadius, menuBackgroundColor, menuElevation);``

``mFloatingMenu.setHeader(headerTitle, headerHeight, headerPadding, headerTitleColor, headerBackgroundColor);``

``// avoid removeMenu() on header click, so preferably null or specify an OnClickListener
mFloatingMenu.setHeaderOnClickListener(null);
mFloatingMenu.setHeaderOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(DemoActivity.this, "Menu Header Clicked", Toast.LENGTH_SHORT).show();
        // Do Something ...
    }
});``

``// if true menu will be removed onBack pressed with properly OnBackPressed setup
mFloatingMenu.setCancelable(true);``

``// if true menu will be removed on any out of menu touch
mFloatingMenu.setCancelableOnTouchOutside(true);``

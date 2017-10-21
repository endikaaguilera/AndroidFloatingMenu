# AndroidFloatingMenu
Android Simple Centered Floating Menu

# Basic Usage

Initialize FloatingMenu()
``FloatingMenu mFloatingMenu = new FloatingMenu();``

Show Menu:
``  mFloatingMenu.showFloatingMenu(YourActivity.this, parentConstraintLayout, callbacks, demoData(), menuIconPosition, menuItemIconPadding);
  mFloatingMenu.setMenuProperties(menuWidth, menuHeight, menuCornerRadius, menuBackgroundColor, menuElevation);
  mFloatingMenu.setHeader(headerTitle, headerHeight, headerPadding, headerTitleColor, headerBackgroundColor);``


Remove menu
``  mFloatingMenu.removeMenu();``


For prevent removeMenu() on header click, set setHeaderOnClickListener(null) or specify an OnClickListener
``  mFloatingMenu.setHeaderOnClickListener(null);
or
  mFloatingMenu.setHeaderOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
         Toast.makeText(DemoActivity.this, "Menu Header Clicked", Toast.LENGTH_SHORT).show();
          // Do Something ...
      }
  });``


If true menu will be removed onBack pressed with properly OnBackPressed setup
``  mFloatingMenu.setCancelable(true);``


If true menu will be removed on any out of menu touch
``  mFloatingMenu.setCancelableOnTouchOutside(true);``



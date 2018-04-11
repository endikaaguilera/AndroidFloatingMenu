# AndroidFloatingMenu
Android Simple Centered Floating Menu

# Basic Usage

  - Initialize ```java FloatingMenu()```
```java
FloatingMenu mFloatingMenu = new FloatingMenu();
```
  - Show Menu:
```java
mFloatingMenu.showFloatingMenu(YourActivity.this, parentConstraintLayout, callbacks, demoData(), menuIconPosition, menuItemIconPadding);
  mFloatingMenu.setMenuProperties(menuWidth, menuHeight, menuCornerRadius, menuBackgroundColor, menuElevation);
  mFloatingMenu.setHeader(headerTitle, headerHeight, headerPadding, headerTitleColor, headerBackgroundColor);
```
  - Remove menu
```java
mFloatingMenu.removeMenu();
```
  - For prevent removeMenu() on header click, set setHeaderOnClickListener(null) or specify an OnClickListener
```java
mFloatingMenu.setHeaderOnClickListener(null);
or
mFloatingMenu.setHeaderOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View view) {
    Toast.makeText(DemoActivity.this, "Menu Header Clicked", Toast.LENGTH_SHORT).show();
    // Do Something ...
  }
});
```
  - If true menu will be removed onBack pressed with properly OnBackPressed setup
```java
mFloatingMenu.setCancelable(true);
```


If true menu will be removed on any out of menu touch
```java
mFloatingMenu.setCancelableOnTouchOutside(true);
```



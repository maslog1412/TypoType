package com.typo.type;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void menu(View view){
        showPopupMenu(view);
    }
    public void lubing(View view){
        Intent intent = new Intent(this, LubingServiceActivity.class);
        startActivity(intent);
    }
    public void keyboard(View view){
        Intent intent = new Intent(this, KeyBoardServiceActivity.class);
        startActivity(intent);
    }
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        // Inflate the menu resource file
        popupMenu.inflate(R.menu.popup_menu);

        // Set menu item click listener
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item clicks
                if (item.getItemId() == R.id.menu_item_1) {
                    Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_item_2) {
                    Toast.makeText(HomeActivity.this, "Dark Mode", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (item.getItemId() == R.id.menu_item_3) {
                   finishAffinity();
                    return true;
                }  else {
                    return false;
                }

            }
        });

        // Show the popup menu
        popupMenu.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}

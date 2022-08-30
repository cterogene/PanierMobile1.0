package com.example.paniermobil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.paniermobil.R;
import com.example.paniermobil.fragments.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paniermobil.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView badgeCounter;
    int pendingNotifications = 3;
    MenuItem menuItem;
    Fragment homeFragment;
    ProgressBar progressBar;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    FirebaseAuth auth;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu);
        homeFragment = new HomeFragment();
        loadFragment(homeFragment);
    }

    private void loadFragment(Fragment homeFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homeFragment);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        menuItem = menu.findItem(R.id.cart);


        if (pendingNotifications == 0){
            menuItem.setActionView(null);

        }else{
            menuItem.setActionView(R.layout.notification_badge);
            View view = menuItem.getActionView();
            badgeCounter = view.findViewById(R.id.badge_counter);
            badgeCounter.setText(String.valueOf(pendingNotifications));
        }
        return true;

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =  item.getItemId();
        if(id == R.id.home){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
        else if (id == R.id.product){
            startActivity(new Intent(MainActivity.this, new_product.class));
        }
        else if (id == R.id.about){
            startActivity(new Intent(MainActivity.this, about_us.class));
        }
        else if (id == R.id.cart){
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}

package com.example.chezbank2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.chezbank2.Fragments.CardFragment;
import com.example.chezbank2.Fragments.DashboardFragment;
import com.example.chezbank2.Fragments.TransferFragment;
import com.example.chezbank2.LoginSystem.Activities.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();;
    private BottomNavigationView bottomNavView;
    private DashboardFragment dashboardFragment = new DashboardFragment();
    private TransferFragment transferFragment = new TransferFragment();
    private CardFragment cardFragment = new CardFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        bottomNavView = findViewById(R.id.bottomNavView);

        if (fAuth.getCurrentUser() != null){
            setMenu();
        }
        else{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

    }

    private void setMenu(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, dashboardFragment).commit();
        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, dashboardFragment).commit();
                        return true;
                    case R.id.transfer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, transferFragment).commit();
                        return true;
                    case R.id.card:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, cardFragment).commit();
                        return true;
                    case R.id.logout:
                        Logout();
                        return true;
                }
                return false;
            }
        });
    }

    private void Logout(){
        fAuth.signOut();
        Toast.makeText(getApplicationContext(), "You have been loged out!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

}
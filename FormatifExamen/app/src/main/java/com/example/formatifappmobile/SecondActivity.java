package com.example.formatifappmobile;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.formatifappmobile.databinding.ActivityMainBinding;
import com.example.formatifappmobile.databinding.ActivitySecondBinding;
import com.example.formatifappmobile.http.Service;
import com.example.formatifappmobile.http.retrofitUtil;
import com.example.formatifappmobile.transfert.Pokemon;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActionBarDrawerToggle abToggle;
    private ActivitySecondBinding binding;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        NavigationView nv = binding.navView;
        DrawerLayout dl = binding.drawerLayout;


        abToggle = new ActionBarDrawerToggle(this, dl, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.drawer_open);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(R.string.drawer_close);
            }
        };
        dl.addDrawerListener(abToggle);
        abToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Pokédex");
        nv.setNavigationItemSelectedListener(this);
        showPokemon();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(abToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        abToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig){
        abToggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item1) {
            startActivityForItem1();
        } else if (id == R.id.item2) {
            startActivityForItem2();
        }

        // Close the navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startActivityForItem1() {
        Intent i = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void startActivityForItem2() { showToast("L'activity est en cours"); }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showPokemon(){
        Service service = (Service) retrofitUtil.get();

        String id = getIntent().getStringExtra("PokemonId");
        service.getPokemon(id).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()){
                    Pokemon p = response.body();
                    binding.tvHauteur.setText(String.valueOf(p.hauteur));
                    binding.tvPoids.setText(String.valueOf(p.poids));
                    binding.tvNomPokemon.setText(p.nom);


                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}

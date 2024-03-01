package com.example.formatif2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.formatif2021.databinding.ActivityMainBinding;
import com.example.formatif2021.http.Service;
import com.example.formatif2021.http.retrofitUtil;
import com.example.formatif2021.transfert.DateFormatif;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dateFormatif(2003,4,4);
    }

    private void dateFormatif(int annee, int mois, int jour){
        Service service = (Service) retrofitUtil.get();

        service.getDates(annee,mois ,jour ).enqueue(new Callback<List<DateFormatif>>() {
            @Override
            public void onResponse(Call<List<DateFormatif>> call, Response<List<DateFormatif>> response) {
                if(response.isSuccessful())
                {
                    List<DateFormatif> l = response.body();
                    String jour = l.get(1).jourDeLaSemaine;
                    showToast(jour);


                }
            }

            @Override
            public void onFailure(Call<List<DateFormatif>> call, Throwable t) {

            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


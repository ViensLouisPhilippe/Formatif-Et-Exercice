package com.example.formatif2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    DateFormatifAdapter dateFormatifAdapter;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initRecyclerView();
        fillRecyclerView();
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
                    dateFormatifAdapter.list = l;
                    dateFormatifAdapter.notifyDataSetChanged();
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

    private void initRecyclerView(){
        RecyclerView recyclerView = binding.itemlist;
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dateFormatifAdapter = new DateFormatifAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(dateFormatifAdapter);
    }
    private void fillRecyclerView() {
        Service service = (Service) retrofitUtil.get();

        service.getDates(2003,7 ,15 ).enqueue(new Callback<List<DateFormatif>>() {
            @Override
            public void onResponse(Call<List<DateFormatif>> call, Response<List<DateFormatif>> response) {
                if(response.isSuccessful())
                {
                    List<DateFormatif> l = response.body();
                    dateFormatifAdapter.list = l;
                    dateFormatifAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DateFormatif>> call, Throwable t) {

            }
        });
    }
}


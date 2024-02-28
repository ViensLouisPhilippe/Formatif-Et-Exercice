package com.example.pelletier2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private final int number = 3824; // On remplace 123456 par le nombre désiré
    private RepresentationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RepresentationAdapter();
        //On associe l'adapter au recyclerView pour afficher les données
        recyclerView.setAdapter(adapter);

        //On appelle le service pour récupérer les représentations du nombre
        DataService service = RetrofitUtil.get();
        Call<List<Representation>> call = service.getNumberRepresentations(number); // Replace 123456 with the desired number
        call.enqueue(new Callback<List<Representation>>() {
            @Override
            //On récupère la liste des représentations
            public void onResponse(Call<List<Representation>> call, Response<List<Representation>> response) {
                if (response.isSuccessful()) {
                    List<Representation> representations = response.body();
                    //On met à jour les données de l'adapter
                    adapter.setData(representations);
                    //On notifie l'adapter que les données ont changé
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            //On gère les erreurs
            @Override
            public void onFailure(Call<List<Representation>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}



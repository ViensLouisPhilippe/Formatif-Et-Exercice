package com.example.formatif2023question1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.formatif2023question1.databinding.ActivityMainBinding;
import com.example.formatif2023question1.http.Service;
import com.example.formatif2023question1.http.retrofitUtil;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.page1);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.EditText.getText().toString();

                RequeteNombre(id);
            }
        });
    }
    private void RequeteNombre(String id){
        Service service = retrofitUtil.get();
        closeKeyboard();
        service.getNumber(id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this,response.body().toString(), Toast.LENGTH_SHORT).show();

                }else {
                    try {
                        String errorText = response.errorBody().string();
                        if(errorText.equals(""))
                            Snackbar.make(binding.EditText,"vous excéder le maximum de 2147483647, Plus BAS!", Snackbar.LENGTH_LONG).show();
                        else
                            Snackbar.make(binding.EditText,errorText, Snackbar.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Snackbar.make(binding.EditText,e.toString(), Snackbar.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Erreur dans la requête au serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
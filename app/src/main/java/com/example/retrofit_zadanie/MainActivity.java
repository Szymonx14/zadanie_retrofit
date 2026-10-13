package com.example.retrofit_zadanie;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnWyswietl;
    ListView listView;
    JsonPlaceHolderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnWyswietl = findViewById(R.id.btnWyswietl);
        listView = findViewById(R.id.listView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Szymonx14/pytania/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        api = retrofit.create(JsonPlaceHolderApi.class);
        btnWyswietl.setOnClickListener(v -> {

            Call<List<Wiadomosc>> call = api.getWiadomosci();

            call.enqueue(new Callback<List<Wiadomosc>>() {
                @Override
                public void onResponse(Call<List<Wiadomosc>> call, Response<List<Wiadomosc>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Błąd serwera: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Wiadomosc> listaWiadomosci = response.body();

                    if (listaWiadomosci != null) {
                        ArrayAdapter<Wiadomosc> adapter = new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                listaWiadomosci
                        );
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Wiadomosc>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Błąd pobierania danych: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}

package com.example.patryk_spowodowal_katastrofe_titanica_nie_klamie_serio_na_prawde;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ivCwiczenie;
    private TextView tvNazwaCwiczenia;
    private TextView tvSerieAktualne;
    private TextView tvLacznaLiczbaSerii;

    private int licznikSeriiAktualnego = 0;
    private int lacznaLiczbaSerii = 0;

    private final Random random = new Random();

    // Tablica identyfikatorów grafik ćwiczeń (indeks 0 to stan początkowy, 1-6 to ćwiczenia)
    private final int[] tablicaGrafik = {
            R.drawable.cwiczenie0,
            R.drawable.cwiczenie1,
            R.drawable.cwiczenie2,
            R.drawable.cwiczenie3,
            R.drawable.cwiczenie4,
            R.drawable.cwiczenie5,
            R.drawable.cwiczenie6
    };

    // Tablica nazw ćwiczeń
    private final String[] tablicaNazw = {
            "Losuj ćwiczenie",
            "Przysiad",
            "Pompki",
            "Plank",
            "Wykroki",
            "Burpees",
            "Podciąganie"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Powiązanie elementów interfejsu z kodem Java
        ImageView ivSportowiec = findViewById(R.id.ivSportowiec);
        ivCwiczenie = findViewById(R.id.ivCwiczenie);
        tvNazwaCwiczenia = findViewById(R.id.tvNazwaCwiczenia);
        tvSerieAktualne = findViewById(R.id.tvSerieAktualne);
        tvLacznaLiczbaSerii = findViewById(R.id.tvLacznaLiczbaSerii);

        Button btnLosuj = findViewById(R.id.btnLosuj);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnNastepne = findViewById(R.id.btnNastepne);
        Button btnReset = findViewById(R.id.btnReset);

        ivSportowiec.setImageResource(R.drawable.sportowiec);

        // Obsługa przycisku LOSUJ ĆWICZENIE
        btnLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                losujNoweCwiczenie();
            }
        });

        // Obsługa przycisku "+"
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                licznikSeriiAktualnego++;
                aktualizujWidokSerii();
            }
        });

        // Obsługa przycisku "−"
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (licznikSeriiAktualnego > 0) {
                    licznikSeriiAktualnego--;
                    aktualizujWidokSerii();
                }
            }
        });

        // Obsługa przycisku NASTĘPNE ĆWICZENIE
        btnNastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lacznaLiczbaSerii += licznikSeriiAktualnego;
                tvLacznaLiczbaSerii.setText("Łączna liczba serii: " + lacznaLiczbaSerii);
                losujNoweCwiczenie();
            }
        });

        // Obsługa przycisku RESET
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                licznikSeriiAktualnego = 0;
                lacznaLiczbaSerii = 0;
                ivCwiczenie.setImageResource(tablicaGrafik[0]);
                tvNazwaCwiczenia.setText(tablicaNazw[0]);
                tvSerieAktualne.setText("0");
                tvLacznaLiczbaSerii.setText("Łączna liczba serii: 0");
            }
        });
    }

    // Pomocnicza metoda do losowania ćwiczenia z zakresu 1-6
    private void losujNoweCwiczenie() {
        int wylosowana = random.nextInt(6) + 1; // Zwraca liczby od 1 do 6
        ivCwiczenie.setImageResource(tablicaGrafik[wylosowana]);
        tvNazwaCwiczenia.setText(tablicaNazw[wylosowana]);
        licznikSeriiAktualnego = 0;
        aktualizujWidokSerii();
    }

    // Pomocnicza metoda aktualizująca licznik serii na ekranie
    private void aktualizujWidokSerii() {
        tvSerieAktualne.setText(String.valueOf(licznikSeriiAktualnego));
    }
}
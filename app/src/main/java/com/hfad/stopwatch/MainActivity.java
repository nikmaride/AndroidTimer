package com.hfad.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // количество секунд на секундомере
    private int seconds = 0;
    // Секундомер работает?
    private boolean running;

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        // метод для обновления секунжомера. Запуск при создании активности
        runTimer();
    }
    //Запуск секундомера по щелчку
    public void onClickStart(View view) {
        //запуск секундомера
        running = true;
    }
    //Стоп таймера
    public void onClickStop (View view) {
        // остановка секундомера
        running = false;
    }
    //Обнуление таймера
    public void onClickReset (View view) {
        //остановка секундомера и его обнуление
        running = false;
        seconds = 0;
    }
    // Обновление показаний таймера.
    private void runTimer () {
        // получение ссылки на надпись
        final TextView timeView = (TextView) findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            // использование Хэндлер для передачи кода на выполнение
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                // формат seconds в часы, минуты и секунды
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                // задание текста надписи
                timeView.setText(time);
                if (running) {
                    //значение running истинно,увеличение переменной seconds
                    seconds++;
            }
                // запланировать повторное выполнение кода с задержкой в 1 с
                handler.postDelayed(this, 1000);
            }

        });

    }
 }
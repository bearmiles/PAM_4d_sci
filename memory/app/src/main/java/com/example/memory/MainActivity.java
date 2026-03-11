package com.example.memory;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnCard;
    private ImageButton btncard1;
    private ImageButton btncard2;
    private ImageButton btncard3;
    private ImageButton btncard4;
    private ImageButton btncard5;
    private ImageButton btncard6;
    private ImageButton btncard7;
    private Button reset_button;
    private boolean showingFront = true;
    TextView text;

    private int poprzedni = 0;
    private int score = 0;
    private int aktualny = 0; //2
    private Handler handler = new Handler(Looper.getMainLooper());
    private static final long FLIP_BACK_DELAY = 2000L; // 2 s
    private boolean isGuessed = false;

    private List<ImageButton> matchedButtons = new ArrayList<>();
    private ImageButton firstClickedButton = null;
    List<Integer> images = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Istniejący kod !
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCard = findViewById(R.id.btnCard);
        btncard1 = findViewById(R.id.btnCard1);
        btncard2 = findViewById(R.id.btnCard2);
        btncard3 = findViewById(R.id.btnCard3);
        btncard4 = findViewById(R.id.btnCard4);
        btncard5 = findViewById(R.id.btnCard5);
        btncard6 = findViewById(R.id.btnCard6);
        btncard7 = findViewById(R.id.btnCard7);
        reset_button = findViewById(R.id.button);


        text = findViewById(R.id.textView);

        reset_button.setOnClickListener(v -> Reset_Game());

//
//        List<Integer> images = new ArrayList<>();
//        images.add(R.drawable.znak_zakazu);
//        images.add(R.drawable.znak_zakazu);
//        images.add(R.drawable.zakaz_ruchu);
//        images.add(R.drawable.zakaz_ruchu);
//        images.add(R.drawable.zakaz_postoju);
//        images.add(R.drawable.zakaz_postoju);
//        images.add(R.drawable.kadruj);
//        images.add(R.drawable.kadruj);
//
//        Collections.shuffle(images);
            images.add(R.drawable.znak_zakazu);
            images.add(R.drawable.znak_zakazu);
            images.add(R.drawable.zakaz_ruchu);
            images.add(R.drawable.zakaz_ruchu);
            images.add(R.drawable.zakaz_postoju);
            images.add(R.drawable.zakaz_postoju);
            images.add(R.drawable.kadruj);
            images.add(R.drawable.kadruj);

            Collections.shuffle(images);
//
        ImageButton[] buttons = {btnCard, btncard1, btncard2, btncard3, btncard4, btncard5, btncard6, btncard7};
//
        int[] lista = {1,1,2,2,3,3};

         for (int i = 0; i < buttons.length; i++){
                int img_res = images.get(i);
                ImageButton current = buttons[i];
                int index = i;

                current.setOnClickListener(v-> flipCardWithTimer(current, img_res, img_res));
        }
    }

    private void Reset_Game() {
        poprzedni = 0;
        score = 0;
        aktualny = 0;
        firstClickedButton = null;
        text.setText("ilość punktów : 0");

        matchedButtons.clear();

        ImageButton[] buttons = {btnCard, btncard1, btncard2, btncard3, btncard4, btncard5, btncard6, btncard7};
        for (ImageButton b : buttons) {
            b.setImageResource(R.drawable.pobrane1);
            b.setRotationY(0);
        }

        startGame();
    }

    private void startGame() {
        images.clear();

        images.add(R.drawable.znak_zakazu);
        images.add(R.drawable.znak_zakazu);
        images.add(R.drawable.zakaz_ruchu);
        images.add(R.drawable.zakaz_ruchu);
        images.add(R.drawable.zakaz_postoju);
        images.add(R.drawable.zakaz_postoju);
        images.add(R.drawable.kadruj);
        images.add(R.drawable.kadruj);

        Collections.shuffle(images);

        ImageButton[] buttons = {btnCard, btncard1, btncard2, btncard3, btncard4, btncard5, btncard6, btncard7};

        for (int i = 0; i < buttons.length; i++) {
            int img_res = images.get(i);
            ImageButton current = buttons[i];

            current.setOnClickListener(v -> flipCardWithTimer(current, img_res, img_res));
        }
    }


    private void flipCardWithTimer(ImageButton currentButton, int Obrazek_tyl, int idObrazka) {
        if (matchedButtons.contains(currentButton) || currentButton == firstClickedButton){
            return;
        }

        animateFlip(currentButton, R.drawable.pobrane1, Obrazek_tyl);
        if (poprzedni == 0 ){
            poprzedni = idObrazka;
            firstClickedButton = currentButton;
        }else {
            if(poprzedni == idObrazka){
                matchedButtons.add(firstClickedButton);
                matchedButtons.add(currentButton);
                score++;
                text.setText("ilość punktów : " + score);
                poprzedni = 0;
                firstClickedButton = null;
                if (score == 4){
                    Toast.makeText(this, "Brawo Wygrałeś! ", Toast.LENGTH_LONG).show();
                }
            }else {
                ImageButton tempFirst = firstClickedButton;

                handler.postDelayed(() -> {
                    animateFlip(currentButton, Obrazek_tyl, R.drawable.pobrane1);
                    animateFlip(tempFirst, Obrazek_tyl, R.drawable.pobrane1);
                    poprzedni = 0;
                    firstClickedButton = null;
                }, FLIP_BACK_DELAY);
            }
        }
    }


//    private boolean CheckWin(int akt){
//        text = findViewById(R.id.textView);
//        Log.v("TEST", "COUNT:  " + poprzedni);
//        Log.v("TEST", "COUNT2:  " + akt);
//        if (poprzedni != 0 ){
//            if (poprzedni == akt){
//                text.setText("XD");
//                return true;
//            }
//            text.setText("gowno");
//        }
//        poprzedni = akt;
//        return false;
//
//    }
    private void animateFlip(ImageButton button,
                             @DrawableRes int fromRes,
                             @DrawableRes int toRes) {
        // Fazę 1: 0° -> 90° (znika stara strona)
        ObjectAnimator flipOut = ObjectAnimator.ofFloat(button,
                "rotationY", 0f, 90f);
        flipOut.setDuration(150);
        flipOut.setInterpolator(new AccelerateInterpolator());
        // Fazę 2: -90° -> 0° (po zmianie obrazka)
        ObjectAnimator flipIn = ObjectAnimator.ofFloat(button,
                "rotationY", -90f, 0f);
        flipIn.setDuration(150);
        flipIn.setInterpolator(new DecelerateInterpolator());
        flipOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Zmiana obrazka w momencie gdy „karta” jest wąska
                button.setImageResource(toRes); // podmiana drawable
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(flipOut, flipIn);
        set.start();
    }

}


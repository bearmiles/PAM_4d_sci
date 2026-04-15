package com.example.kolkoikrzyzyk;

import android.app.Application;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


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


        Button start = findViewById(R.id.button);
        Button stop = findViewById(R.id.button2);
        stop.setOnClickListener(v -> {
            stop.setEnabled(false);
            startGame();
            if (win){
                stop.setEnabled(true);
            }
        });
        start.setOnClickListener( v-> {
            start.setEnabled(false);
            clearBoard();
            stop.setEnabled(true);
        });

    }



    private void clearBoard(){
        ImageButton img1 = findViewById(R.id.imageButton);
        ImageButton img2 = findViewById(R.id.imageButton2);
        ImageButton img3 = findViewById(R.id.imageButton3);
        ImageButton img4 = findViewById(R.id.imageButton4);
        ImageButton img5 = findViewById(R.id.imageButton5);
        ImageButton img6 = findViewById(R.id.imageButton6);
        ImageButton img7 = findViewById(R.id.imageButton7);
        ImageButton img8 = findViewById(R.id.imageButton8);
        ImageButton img9 = findViewById(R.id.imageButton9);
        ImageButton[] buttons = {img1, img2, img3, img4, img5, img6, img7, img8, img9};

        for (int i = 0; i < buttons.length; i++){
            ImageButton current = buttons[i];
            current.setImageResource(R.drawable.pobrane);
            current.setBackgroundColor(Color.GRAY);
            current.setEnabled(true);
            current.setTag(null);
        }
        isStarted = false;
        gracz_jeden = true;
        gracz_dwa = false;
        win = false;
        gracz1_win = false;
        gracz2_win = false;

    }

    public boolean isStarted = false;
    public boolean gracz_jeden = false;
    public boolean gracz_dwa = false;
    public boolean win = false;
    public boolean gracz1_win = false;
    public boolean gracz2_win = false;


    private void startGame(){
        ImageButton img1 = findViewById(R.id.imageButton);
        ImageButton img2 = findViewById(R.id.imageButton2);
        ImageButton img3 = findViewById(R.id.imageButton3);
        ImageButton img4 = findViewById(R.id.imageButton4);
        ImageButton img5 = findViewById(R.id.imageButton5);
        ImageButton img6 = findViewById(R.id.imageButton6);
        ImageButton img7 = findViewById(R.id.imageButton7);
        ImageButton img8 = findViewById(R.id.imageButton8);
        ImageButton img9 = findViewById(R.id.imageButton9);
        ImageButton[] buttons = {img1, img2, img3, img4, img5, img6, img7, img8, img9};
        TextView txt1 = findViewById(R.id.textView);
        TextView txt2 = findViewById(R.id.textView2);
        isStarted = true;

        gracz_jeden = true;

            for (int i = 0; i < buttons.length; i++) {
                ImageButton current = buttons[i];
                current.setOnClickListener(v-> {
                    if (!win){
                        if (gracz_jeden){
                            FlipCard(current, true);
                        }else if (gracz_dwa){
                            FlipCard(current, false);
                        }
                        CheckWin(img1, img2, img3,img4,img5,img6,img7,img8,img9);

                        if (gracz_jeden){
                            txt1.setTypeface(null, Typeface.BOLD);
                            txt2.setTypeface(null, Typeface.NORMAL);
                        }else if (gracz_dwa){
                            txt1.setTypeface(null, Typeface.NORMAL);
                            txt2.setTypeface(null, Typeface.BOLD);
                        }
                        current.setEnabled(false);
                    }else {
                        Toast.makeText(this, "Jest juz zwyciezca", Toast.LENGTH_LONG).show();
                    }


                });
        }
    }

    private void CheckWin(ImageButton img1, ImageButton img2, ImageButton img3, ImageButton img4, ImageButton img5, ImageButton img6, ImageButton img7, ImageButton img8, ImageButton img9){
        if (img1.getTag() != null &&
            img1.getTag().equals(img2.getTag()) &&
            img2.getTag().equals(img3.getTag())) {
            if (gracz_dwa){
                img1.setBackgroundColor(Color.GREEN);
                img2.setBackgroundColor(Color.GREEN);
                img3.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img1.setBackgroundColor(Color.GREEN);
                img2.setBackgroundColor(Color.GREEN);
                img3.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }else if (img1.getTag() != null &&
                img1.getTag().equals(img4.getTag()) &&
                img4.getTag().equals(img7.getTag())) {
            if (gracz_dwa){
                img1.setBackgroundColor(Color.GREEN);
                img4.setBackgroundColor(Color.GREEN);
                img7.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img1.setBackgroundColor(Color.GREEN);
                img4.setBackgroundColor(Color.GREEN);
                img7.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }else if (img3.getTag() != null &&
                img3.getTag().equals(img5.getTag()) &&
                img5.getTag().equals(img7.getTag())) {
            if (gracz_dwa){
                img3.setBackgroundColor(Color.GREEN);
                img5.setBackgroundColor(Color.GREEN);
                img7.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){img3.setBackgroundColor(Color.GREEN);
                img5.setBackgroundColor(Color.GREEN);
                img7.setBackgroundColor(Color.GREEN);

                gracz2_win = true;
                win=true;
            }
        }else if (img7.getTag() != null &&
                img7.getTag().equals(img8.getTag()) &&
                img8.getTag().equals(img9.getTag())) {
            if (gracz_dwa){
                img7.setBackgroundColor(Color.GREEN);
                img8.setBackgroundColor(Color.GREEN);
                img9.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img7.setBackgroundColor(Color.GREEN);
                img8.setBackgroundColor(Color.GREEN);
                img9.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }else if (img3.getTag() != null &&
                img3.getTag().equals(img6.getTag()) &&
                img6.getTag().equals(img9.getTag())) {
            if (gracz_dwa){
                img3.setBackgroundColor(Color.GREEN);
                img6.setBackgroundColor(Color.GREEN);
                img9.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img3.setBackgroundColor(Color.GREEN);
                img6.setBackgroundColor(Color.GREEN);
                img9.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }else if (img1.getTag() != null &&
                img1.getTag().equals(img4.getTag()) &&
                img4.getTag().equals(img7.getTag())) {
            if (gracz_dwa){
                img1.setBackgroundColor(Color.GREEN);
                img4.setBackgroundColor(Color.GREEN);
                img7.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img1.setBackgroundColor(Color.GREEN);
                img4.setBackgroundColor(Color.GREEN);
                img7.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }else if (img2.getTag() != null &&
                img2.getTag().equals(img5.getTag()) &&
                img5.getTag().equals(img8.getTag())) {
            if (gracz_dwa){
                img2.setBackgroundColor(Color.GREEN);
                img5.setBackgroundColor(Color.GREEN);
                img8.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img2.setBackgroundColor(Color.GREEN);
                img5.setBackgroundColor(Color.GREEN);
                img8.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }else if (img4.getTag() != null &&
                img4.getTag().equals(img5.getTag()) &&
                img5.getTag().equals(img6.getTag())) {
            if (gracz_dwa){
                img4.setBackgroundColor(Color.GREEN);
                img5.setBackgroundColor(Color.GREEN);
                img6.setBackgroundColor(Color.GREEN);
                gracz1_win = true;
                win = true;
            }else if (gracz_jeden){
                img4.setBackgroundColor(Color.GREEN);
                img5.setBackgroundColor(Color.GREEN);
                img6.setBackgroundColor(Color.GREEN);
                gracz2_win = true;
                win=true;
            }
        }

        if (gracz1_win){
            Toast.makeText(this, "BRAWO KOLKO WYGRYWA", Toast.LENGTH_LONG).show();
        }else if (gracz2_win){
            Toast.makeText(this, "BRAWO KRZYZYK WYGRYWA", Toast.LENGTH_LONG).show();

        }
        }
    private void FlipCard(ImageButton img, boolean ktoryGracz){
       if(ktoryGracz){
            img.setImageResource(R.drawable.koleczko);
            img.setTag("O");
            gracz_jeden = false;
            gracz_dwa = true;
       }
       if (!ktoryGracz) {
           img.setImageResource(R.drawable.krzyzykkk);
           img.setTag("X");
           gracz_jeden = true;
           gracz_dwa = false;
       }
    }



}
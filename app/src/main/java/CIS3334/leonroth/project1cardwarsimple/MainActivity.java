package CIS3334.leonroth.project1cardwarsimple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewPlayerCard;
    ImageView imageViewOpponentCard;
    Button buttonPlay;
    FloatingActionButton floatingActionButtonHelp;
    TextView textViewPlayerStack;
    TextView textViewOpponentStack;
    MainViewModel mainViewModel;
    TextView textViewStatus;
    Picasso picasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        imageViewPlayerCard = findViewById(R.id.imageViewPlayerCard);
        imageViewOpponentCard = findViewById(R.id.imageViewOpponentCard);
        textViewPlayerStack = findViewById(R.id.textViewPlayerStack);
        textViewOpponentStack = findViewById(R.id.textViewOpponentStack);
        textViewStatus = findViewById(R.id.textViewStatus);

        setupButtonPlay();
        setupFloatingActionButtonHelp();

        picasso = Picasso.get();
        //picasso.setLoggingEnabled(true);
        //picasso.setIndicatorsEnabled(true);
    }



    private void setupButtonPlay() {
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPlay.setEnabled(false);
                String[] onButtonPlayClick = mainViewModel.onButtonPlayClick();

                Resources res = getResources();
                //Drawable cardback = res.getDrawable(R.drawable.cardback);
                Drawable cardback = ContextCompat.getDrawable(MainActivity.this, R.drawable.cardback);

                picasso.load(onButtonPlayClick[1]).fit().placeholder(cardback).into(imageViewPlayerCard);
                picasso.load(onButtonPlayClick[2]).fit().placeholder(cardback).into(imageViewOpponentCard);

                if(onButtonPlayClick[0].equals("0")){
                    textViewStatus.setText("This hand is a tie.");
                }
                else if(onButtonPlayClick[0].equals("1")){
                    textViewStatus.setText("Player wins this hand.");
                }
                else if(onButtonPlayClick[0].equals("-1")){
                    textViewStatus.setText("Opponent wins this hand.");
                }
                else if(onButtonPlayClick[0].equals("9")){
                    textViewStatus.setText("Problem with comparing cards.");
                }
                else{
                    textViewStatus.setText("Another problem.");
                    Log.d("3334",(onButtonPlayClick[0].getClass()+"***"+onButtonPlayClick[0]+"***"+onButtonPlayClick[1])+"***"+onButtonPlayClick[2]);
                }

                textViewPlayerStack.setText(mainViewModel.getPlayerCardListSize().toString());
                textViewOpponentStack.setText(mainViewModel.getOpponentCardListSize().toString());

                if(mainViewModel.getPlayerCardListSize() > 0 && mainViewModel.getOpponentCardListSize() >0){
                    buttonPlay.setEnabled(true);
                }
                else{
                    endGameDialog();
                }

            }
        });
    }

    private void setupFloatingActionButtonHelp() {
        floatingActionButtonHelp = findViewById(R.id.floatingActionButtonHelp);
        floatingActionButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void endGameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Game Over");
        if(mainViewModel.getPlayerCardListSize() == 0){
            builder.setMessage("Opponent wins! /n Play Again?");
        }
        else if(mainViewModel.getOpponentCardListSize() == 0){
            builder.setMessage("Player wins! /n Play Again?");
        }
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            mainViewModel.newGame();
            textViewStatus.setText("New Game");
            textViewPlayerStack.setText(mainViewModel.getPlayerCardListSize().toString());
            textViewOpponentStack.setText(mainViewModel.getOpponentCardListSize().toString());

            Drawable cardback = ContextCompat.getDrawable(MainActivity.this, R.drawable.cardback);
            picasso.load(R.drawable.cardback).fit().placeholder(cardback).into(imageViewPlayerCard);
            picasso.load(R.drawable.cardback).fit().placeholder(cardback).into(imageViewOpponentCard);

            buttonPlay.setEnabled(true);
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

            finish();
        });

        builder.create().show();
    }





}
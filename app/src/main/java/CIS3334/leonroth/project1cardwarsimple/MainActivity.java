package CIS3334.leonroth.project1cardwarsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewPlayerCard;
    ImageView imageViewOpponentCard;
    Button buttonPlay;
    FloatingActionButton floatingActionButtonHelp;
    TextView textViewPlayerStack;
    TextView textViewOpponentStack;
    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewPlayerCard = findViewById(R.id.imageViewPlayerCard);
        imageViewOpponentCard = findViewById(R.id.imageViewOpponentCard);
        textViewPlayerStack = findViewById(R.id.textViewPlayerStack);
        textViewOpponentStack = findViewById(R.id.textViewOpponentStack);

        setupButtonPlay();
        setupFloatingActionButtonHelp();

        Picasso picasso = Picasso.get();
        picasso.load("https://www.deckofcardsapi.com/static/img/AS.png").into(imageViewOpponentCard);


    }

    private void setupButtonPlay() {
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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

    private Drawable LoadImageFromWebOperations(String url) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }

    }



}
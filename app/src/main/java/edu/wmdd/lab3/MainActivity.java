package edu.wmdd.lab3;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> deck;
    ArrayList<ImageView> diceImageViews;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resetGame();

        final ImageView cardImage = findViewById(R.id.cardImage);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(deck.size() == 0) {
                    resetGame();
                }
                else {
                    int randomInteger;
                    randomInteger = rand.nextInt(deck.size());

                    cardImage.setImageResource(R.drawable.ace_of_spades);

                    Context context = cardImage.getContext();
                    int id = context.getResources().getIdentifier(deck.get(randomInteger), "drawable", context.getPackageName());
                    cardImage.setImageResource(id);

                    deck.remove(randomInteger);
                }

                showNumberCardsLeft();

                if(deck.size() == 0) {
                    Toast.makeText(MainActivity.this, "No more cards! Click the button to restart", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_restart_game) {
            resetGame();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void resetGame() {
        setInitialImage();
        fillDeck();
        showNumberCardsLeft();
    }

    protected void setInitialImage () {
        ImageView cardImage = findViewById(R.id.cardImage);
        Context context = cardImage.getContext();
        int id = context.getResources().getIdentifier("black_joker", "drawable", context.getPackageName());
        cardImage.setImageResource(id);
    }

    protected void showNumberCardsLeft() {
        TextView cards_left = findViewById(R.id.cards_left);
        Integer deckSize = deck.size();
        cards_left.setText("Cards Remaining: " + deckSize.toString());
    }

    private void fillDeck() {

        deck = new ArrayList<String>();

        String [] groups = {"clubs", "diamonds", "hearts", "spades"};
        String [] cards = {"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};

        for (String group: groups)
        {
            for (String card: cards)
            {
                deck.add(card + "_of_" + group);
            }
        }

    }
}

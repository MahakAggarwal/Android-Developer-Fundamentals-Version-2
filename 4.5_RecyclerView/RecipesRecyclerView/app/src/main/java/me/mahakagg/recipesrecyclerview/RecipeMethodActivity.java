package me.mahakagg.recipesrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_method);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String item_name = null;
        if (extras != null) {
           item_name = extras.getString(RecipeListAdapter.EXTRA_NAME);
        }
        TextView heading = findViewById(R.id.food_name);
        ImageView imageView = findViewById(R.id.food_image);
        heading.setText(item_name);
        if (item_name != null) {
            switch (item_name){
                case "Chocolate Mint Bars":
                    imageView.setImageResource(R.drawable.chocolate_mint_bar);
                    break;
                case "Blueberry Cupcakes":
                    imageView.setImageResource(R.drawable.blueberry_cupcakes);
                    break;
                case "Fudge Walnut Brownies":
                    imageView.setImageResource(R.drawable.fudge_brownies);
                    break;
                case "Lemon Cake":
                    imageView.setImageResource(R.drawable.lemon_cake);
                    break;
                case "Blueberry Peach Cobbler":
                    imageView.setImageResource(R.drawable.cobbler);
                    break;
                case "Texas Sheet Cake":
                    imageView.setImageResource(R.drawable.sheet_cake);
                    break;
                case "Espresso Crinkles":
                    imageView.setImageResource(R.drawable.espresso_crinkles);
                    break;
                case "Chocolate Cherry Cookies":
                    imageView.setImageResource(R.drawable.chocolate_cherry_cookies);
                    break;
                case "Vanilla Cheesecake":
                    imageView.setImageResource(R.drawable.cheesecake);
                    break;
                case "Tiramisu":
                    imageView.setImageResource(R.drawable.tiramisu);
                    break;
                case "Carrot Cake":
                    imageView.setImageResource(R.drawable.carrot_cake);
                    break;
                case "Blueberry Ice Cream":
                    imageView.setImageResource(R.drawable.blueberry_ice_cream);
                    break;
                default: break;
           }
        }
    }
}

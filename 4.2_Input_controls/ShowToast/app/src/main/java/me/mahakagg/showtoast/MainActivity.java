package me.mahakagg.showtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

/*
*
* Answers to homework questions
*
* Q1 - What's the most important difference between checkboxes and a RadioGroup of radio buttons?
* A1 - The major difference is that checkboxes enable multiple selections, while a RadioGroup allows only one selection.
*
* Q2 - Which layout group lets you align a set of CheckBox elements vertically?
* A2 - Linear Layout
*
* Q3 - Which of the following is the method of the Checkable interface to check the state of a radio button (that is, whether it has been selected or not)?
* A3 - isChecked()
* 
* */


public class MainActivity extends AppCompatActivity {
    private String message = "Toppings: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastMessage(View view) {
        selectToppings((CheckBox) findViewById(R.id.check_syrup), getString(R.string.syrup));
        selectToppings((CheckBox) findViewById(R.id.check_sprinkles), getString(R.string.sprinkles));
        selectToppings((CheckBox) findViewById(R.id.check_crushed_nuts), getString(R.string.crushed_nuts));
        selectToppings((CheckBox) findViewById(R.id.check_cherries), getString(R.string.cherries));
        selectToppings((CheckBox) findViewById(R.id.check_oreo), getString(R.string.oreo));
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void selectToppings(CheckBox checkBox, String topping){
        if (checkBox.isChecked()){
            if (!message.contains(topping)){
                message = message + " " + topping;
            }
        }
        else{
            if (message.contains(topping)){
                message = message.replace(" " + topping, "");
            }
        }
    }
}

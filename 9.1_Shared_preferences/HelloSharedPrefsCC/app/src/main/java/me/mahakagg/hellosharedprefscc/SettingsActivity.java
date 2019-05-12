package me.mahakagg.hellosharedprefscc;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final String COUNT_SAVE_KEY = "count_save";
    private final String COLOR_KEY = "color";
    private final String SELECTED_SPINNER_ITEM_KEY = "selected_spinner_item";
    private String currentSpinnerSetting;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "me.mahakagg.hellosharedprefs";
    private String spinner_item;
    private Switch countSwitch;
    private Spinner spinner;
    private int spinnerSelectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        spinner = findViewById(R.id.backgroundSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }
        countSwitch = findViewById(R.id.saveCountSwitch);
        if (mPreferences.getBoolean(COUNT_SAVE_KEY, true)) {
            countSwitch.setChecked(true);
        } else {
            countSwitch.setChecked(false);
        }
        String savedSpinnerSetting = mPreferences.getString(SELECTED_SPINNER_ITEM_KEY, "Default");
        if (spinner != null) {
            switch (savedSpinnerSetting) {
                case "Default":
                    spinner.setSelection(0);
                    break;
                case "Black":
                    spinner.setSelection(1);
                    break;
                case "Red":
                    spinner.setSelection(2);
                    break;
                case "Blue":
                    spinner.setSelection(3);
                    break;
                case "Green":
                    spinner.setSelection(4);
                    break;
                default:
                    spinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner_item = parent.getItemAtPosition(position).toString();
        switch (spinner_item) {
            case "Default":
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.default_background);
                currentSpinnerSetting = "Default";
                break;
            case "Black":
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.black);
                currentSpinnerSetting = "Black";
                break;
            case "Red":
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.red_background);
                currentSpinnerSetting = "Red";
                break;
            case "Blue":
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.blue_background);
                currentSpinnerSetting = "Blue";
                break;
            case "Green":
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.green_background);
                currentSpinnerSetting = "Green";
                break;
            default:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.default_background);
                currentSpinnerSetting = "Default";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spinner_item = getResources().getStringArray(R.array.color_array)[0];
        spinnerSelectedColor = ContextCompat.getColor(this, R.color.default_background);
        currentSpinnerSetting = "Default";
    }


    public void saveSettings(View view) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(COLOR_KEY, spinnerSelectedColor);
        editor.putString(SELECTED_SPINNER_ITEM_KEY, currentSpinnerSetting);
        if (countSwitch.isChecked()) {
            editor.putBoolean(COUNT_SAVE_KEY, true);
        } else {
            editor.putBoolean(COUNT_SAVE_KEY, false);
        }
        editor.apply();
        Toast.makeText(this, "Settings have been saved!", Toast.LENGTH_LONG).show();
    }

    public void resetSettings(View view) {
        spinner.setSelection(0);
        countSwitch.setChecked(true);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(COUNT_SAVE_KEY, true);
        editor.putString(SELECTED_SPINNER_ITEM_KEY, "Default");
        editor.putInt(COLOR_KEY, ContextCompat.getColor(this, R.color.default_background));
        editor.apply();
        Toast.makeText(this, "Settings were reset!", Toast.LENGTH_LONG).show();
    }
}

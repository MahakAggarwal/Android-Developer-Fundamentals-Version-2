package me.mahakagg.recipesrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.util.LinkedList;


/*
* Answers to questions
*
* Q1 - Which of the following statements about a RecyclerView is false?
* A1 - You don't need a layout manager with a RecyclerView to handle the hierarchy and layout of View elements.
*
* Q2 - Which of the following is the primary component you need to provide to an adapter a View item and its position within a RecyclerView?
* A2 - RecyclerView.ViewHolder
*
* Q3 - Which interface do you need to implement in order to listen and respond to user clicks in a RecyclerView?
* A3 - View.onClickListener
 * */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private final LinkedList<String> mRecipeNames = new LinkedList<>();
    private final LinkedList<String> mRecipeDesc = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile(getResources().openRawResource(R.raw.recipe_names), mRecipeNames);
        readFile(getResources().openRawResource(R.raw.recipe_desc), mRecipeDesc);
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        RecipeListAdapter mAdapter = new RecipeListAdapter(this, mRecipeNames, mRecipeDesc);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void readFile(InputStream ins, LinkedList<String> list) {
        String contents = null;
        String [] contents_array = null;
        int i;
        try{
            contents = IOUtils.toString(ins);
            System.out.println(contents);
            IOUtils.closeQuietly(ins);
        }
        catch (Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
        }

        if (contents != null) {
            contents_array = contents.split("\\r?\\n");
        }
        if (contents_array != null) {
            for (i = 0; i < contents_array.length ; i++){
                    list.add(i, contents_array[i]);
                    Log.d("ARRAY CONTENTS", contents_array[i]);

            }
        }
    }
}

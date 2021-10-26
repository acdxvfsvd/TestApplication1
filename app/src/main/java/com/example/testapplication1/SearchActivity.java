package com.example.testapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchAdapter mSearchAdapter = new SearchAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchAdapter);

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i += 1) {
            items.add(String.valueOf(i));
        }

        mSearchAdapter.notifyItems(items);

        EditText mEditView = (EditText)findViewById(R.id.editTextSearch);
        mEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("SearchActivity", "before change " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("SearchActivity", "on change " + s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("SearchActivity", "after change " + s);

                if (s.equals("")) {
                    mSearchAdapter.notifyItems(items);
                }
                List<String> new_items = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).contains(s)) {
                        new_items.add(items.get(i));
                    }
                }
                mSearchAdapter.notifyItems(new_items);
            }
        });


    }
}
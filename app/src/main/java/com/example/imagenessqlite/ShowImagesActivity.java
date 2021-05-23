package com.example.imagenessqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

public class ShowImagesActivity extends AppCompatActivity {

    private DatabaseHandler conn;
    private RecyclerView rvImgs;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        conn = new DatabaseHandler(getApplicationContext(), Utilidades.DB_NAME, null, 1);

        rvImgs = findViewById(R.id.rvImgs);
    }

    public void getData(View v){
        adapter = new Adapter(conn.getAllImgs());
        rvImgs.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        rvImgs.setLayoutManager(llm);
        rvImgs.setAdapter(adapter);
    }
}
package com.example.trabalhosqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhosqlite.dao.LivroDao;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements View.OnClickListener {
    Button btnAdd, btnGetAll;
    TextView livro_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnGetAll = findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
    }

    public void onResume(){
        super.onResume();
        listaLivros();
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {
            Intent intent = new Intent(this, LivroDetail.class);
            intent.putExtra("livro_Id", 0);
            startActivity(intent);
        }
    }

    public void listaLivros(){
        LivroDao dao = new LivroDao(this);
        ArrayList<HashMap<String, String>> livrolista = dao.getLivroList();
        ListView lv = getListView();
        if (livrolista.size() != 0) {
            lv.setOnItemClickListener((parent, view1, position, id) -> {
                livro_Id = (TextView) view1.findViewById(R.id.livro_Id);
                String studentId = livro_Id.getText().toString();
                Intent objIndent = new Intent(getApplicationContext(), LivroDetail.class);
                objIndent.putExtra("livro_Id", Integer.parseInt(studentId));
                startActivity(objIndent);
            });
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                    livrolista, R.layout.activity_view_livro_entry,
                    new String[]{"id", "name"},
                    new int[]{R.id.livro_Id, R.id.livro_name});
            setListAdapter(adapter);
        } else {
            Toast.makeText(this, "Nenhum Livro!", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.trabalhosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalhosqlite.dao.LivroDao;
import com.example.trabalhosqlite.model.Livro;


public class LivroDetail extends AppCompatActivity implements View.OnClickListener {

    Button button_save;
    Button button_delete;
    Button button_close;
    EditText editTextNome;
    EditText editTextIsbn;
    EditText editTextAno;
    EditText editTextAutor;
    public int Livro_id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_detail);

        button_save = findViewById(R.id.button_save);
        button_delete = findViewById(R.id.button_delete);
        button_close =  findViewById(R.id.button_close);
        editTextNome =  findViewById(R.id.editarNome);
        editTextIsbn =  findViewById(R.id.editarISBN);
        editTextAno = findViewById(R.id.editarAno);
        editTextAutor = findViewById(R.id.editarAutor);
        button_save.setOnClickListener(this);
        button_delete.setOnClickListener(this);
        button_close.setOnClickListener(this);
        Livro_id = 0;

        Intent intent = getIntent();
        Livro_id = intent.getIntExtra("livro_Id", 0);
        LivroDao dao = new LivroDao(this);

        Livro livro = dao.getLivroById(Livro_id);

        editTextNome.setText(livro.getNome());
        editTextIsbn.setText(String.valueOf(livro.getIsbn()));
        editTextAno.setText(String.valueOf(livro.getAno()));
        editTextAutor.setText(livro.getAutor());
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.button_save)){
            LivroDao dao = new LivroDao(this);
            Livro livro = new Livro();

            livro.setNome(editTextNome.getText().toString());
            livro.setIsbn(Integer.parseInt(editTextIsbn.getText().toString()));
            livro.setAno(Integer.parseInt(editTextAno.getText().toString()));
            livro.setAutor(editTextAutor.getText().toString());
            livro.setLivroid(Livro_id);

            if (Livro_id==0){
                Livro_id = dao.insert(livro);
                Toast.makeText(this,"Novo Livro Adicionado", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                dao.update(livro);
                Toast.makeText(this,"Livro Atualizado",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.button_delete)){
            LivroDao dao = new LivroDao(this);
            dao.delete(Livro_id);
            Toast.makeText(this, "Livro excluído", Toast.LENGTH_SHORT).show();
            finish();
        }else if (view== findViewById(R.id.button_close)){
            finish();
        }
    }

}
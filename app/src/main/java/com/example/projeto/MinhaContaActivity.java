package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MinhaContaActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private Intent i;
    private DBHelper db;
    private List<Utilizador> listaUtilizadores;
    private ArrayAdapter<Utilizador> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        i = getIntent();
        int id = i.getExtras().getInt("id");
        db = new DBHelper(this );
        listaUtilizadores = new ArrayList<>();



        mViewHolder.et_id = findViewById(R.id.et_minhaconta_id);
        mViewHolder.et_username = findViewById(R.id.et_minhaconta_username);
        mViewHolder.et_password = findViewById(R.id.et_minhaconta_password);
        mViewHolder.bt_editar = findViewById(R.id.bt_minhaconta_Editar);
        mViewHolder.bt_eliminar = findViewById(R.id.bt_minhaconta_eliminar);
        mViewHolder.lv_utilizadores = findViewById(R.id.lv_minhaconta);

        mViewHolder.bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(mViewHolder.et_id.getText().toString().trim());
                long res = db.Utilizador_Delete(id);
                if(res > 0){
                    Toast.makeText(MinhaContaActivity.this, "Registro Excluido com sucesso !", Toast.LENGTH_SHORT).show();
                    PreencherListadeUtilizadores();
                } else {
                    Toast.makeText(MinhaContaActivity.this, "Problemas ao excluir registro !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mViewHolder.bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(mViewHolder.et_id.getText().toString());
                String username = mViewHolder.et_username.getText().toString().trim();
                String password = mViewHolder.et_password.getText().toString().trim();
                long res = db.Utilizador_Update(id, username,password);
                if(res > 0){
                    Toast.makeText(MinhaContaActivity.this, "Registro Atualizado com sucesso !", Toast.LENGTH_SHORT).show();
                    PreencherListadeUtilizadores();
                } else {
                    Toast.makeText(MinhaContaActivity.this, "Problemas ao Editar registro !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mViewHolder.lv_utilizadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewHolder.et_id.setText(String.valueOf(listaUtilizadores.get(position).getId()));
                mViewHolder.et_username.setText(listaUtilizadores.get(position).getUsername());
                mViewHolder.et_password.setText(listaUtilizadores.get(position).getPass());
            }
        });

        PreencherDadosUtilizador(id);
        PreencherListadeUtilizadores();

    }

    private void PreencherListadeUtilizadores() {
        listaUtilizadores = db.Utilizador_SelectAll();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaUtilizadores);
        mViewHolder.lv_utilizadores.setAdapter(adapter);
    }

    private void PreencherDadosUtilizador(int id) {
        Utilizador u = db.Utilizador_SelectById(id);
        mViewHolder.et_id.setText(String.valueOf(u.getId()));
        mViewHolder.et_username.setText(u.getUsername());
        mViewHolder.et_password.setText(u.getPass());
    }

    public class ViewHolder{
        EditText et_id;
        EditText et_username;
        EditText et_password;
        Button bt_editar;
        Button bt_eliminar;
        ListView lv_utilizadores;
    }
}
package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mViewHolder.et_usuario = findViewById(R.id.et_cad_username);
        mViewHolder.et_password1 = findViewById(R.id.et_cad_password1);
        mViewHolder.et_password2 = findViewById(R.id.et_cad_password2);
        mViewHolder.bt_cadastrar = findViewById(R.id.bt_cad_entrar);

        db = new DBHelper(this);

        mViewHolder.bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mViewHolder.et_usuario.getText().toString().trim();
                String password1 = mViewHolder.et_password1.getText().toString().trim();
                String password2 = mViewHolder.et_password2.getText().toString().trim();

                if (username.isEmpty()){
                    Toast.makeText(RegistroActivity.this, "Favor inserir o Username !", Toast.LENGTH_SHORT).show();
                } else if (password1.isEmpty() || password2.isEmpty()){
                    Toast.makeText(RegistroActivity.this, "Favor inserir a Password !", Toast.LENGTH_SHORT).show();
                } else if(!password1.equals(password2)){
                    Toast.makeText(RegistroActivity.this, "Passwords diferentes !", Toast.LENGTH_SHORT).show();
                } else {
                    Long res = db.Utilizador_Insert(username, password1);
                    //LIGAÇÃO A BASE DE DADOS PARA REGISTRO
                    if(res > 0){
                        Toast.makeText(RegistroActivity.this, "Usuario cadastrado com Sucesso !", Toast.LENGTH_SHORT).show();
                        Intent i = getIntent();
                        i.putExtra("username",username);
                        setResult(1,i);
                        finish();
                    } else{
                        Toast.makeText(RegistroActivity.this, "Erro o cadastrar o usuario !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private class ViewHolder{
        EditText et_usuario, et_password1, et_password2;
        Button bt_cadastrar;
    }
}
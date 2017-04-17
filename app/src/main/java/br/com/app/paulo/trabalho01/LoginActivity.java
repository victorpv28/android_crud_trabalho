package br.com.app.paulo.trabalho01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.app.paulo.trabalho01.dao.UsuariosDAO;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_APP_PREFERENCES = "APP_PREFERENCE";
    public static final String KEY_LOGIN = "login";

    private UsuariosDAO dao;

    private EditText etLogin;
    private EditText etSenha;
    private Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btLogar = (Button) findViewById(R.id.btLogar);

        dao = new UsuariosDAO(this);

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isLoginValido()){
                    iniciarApp();
                }else {
                    Toast.makeText(getApplicationContext(), "Usuario ou senha invalida", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    // Valida o login
    private boolean isLoginValido() {
        String login = etLogin.getText().toString();
        String senha = etSenha.getText().toString();
        if(dao.findUsuario(login,senha) == true){
            return true;
        } else
            return false;
    }

    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

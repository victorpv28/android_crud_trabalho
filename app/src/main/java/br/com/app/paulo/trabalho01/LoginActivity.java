package br.com.app.paulo.trabalho01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btLogar = (Button) findViewById(R.id.btLogar);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);

        dao = new UsuariosDAO(this);

        if(isConectado()) {
            iniciarApp();
        }

        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isLoginValido()){
                    if(cbManterConectado.isChecked()){
                        manterConectado();
                    }
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
        if(dao.procurarUsuarios(login,senha) == true){
            return true;
        } else
            return false;
    }


    private void manterConectado(){

        String login = etLogin.getText().toString();
        SharedPreferences preferences = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();

    }

    private boolean isConectado(){

        SharedPreferences preferences = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        String login = preferences.getString(KEY_LOGIN, "");
        if (login.equals("")){
            return false;
        }else {
            return true;
        }

    }

    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

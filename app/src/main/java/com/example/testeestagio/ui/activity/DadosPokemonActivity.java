package com.example.testeestagio.ui.activity;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.testeestagio.R;
import com.example.testeestagio.api.Conexao;
import com.example.testeestagio.api.ConsumirJson;
import com.example.testeestagio.model.PokemonDados;



public class DadosPokemonActivity extends Activity {
    private PokemonDados pokemonDados = null;
    private String url = receberRequest(ScanQrcodeActivity.getDadoScan());
    TextView id_pokemon;
    TextView nome_pokemon;
    TextView type_pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dados_pokemon_activity);

        id_pokemon = findViewById(R.id.dados_pokemon_activity_id);
        nome_pokemon = findViewById(R.id.dados_pokemon_activity_name);
        type_pokemon = findViewById(R.id.dados_pokemon_activity_type);

        Tarefa tarefa = new Tarefa();
        tarefa.execute(url);


        //Button Voltar - onClick
        ImageButton button_voltar = findViewById(R.id.button_voltar);
        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private String receberRequest(String result_qrcode){
        String request = "https://pokeapi.co/api/v2/pokemon/";
        String id_pokemon = result_qrcode.replace("id-pokemon-remopt: ", "");
        request += id_pokemon;
        return request;
    }

    private class Tarefa extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String retorno = Conexao.getDados(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            pokemonDados = ConsumirJson.pokemonDados(s);
            exibirDadosPokemon();
        }

        private void exibirDadosPokemon() {
            if (pokemonDados != null) {
                    // BUG JSONException -> incrementar valores
                    id_pokemon.setText("ID " );
                    nome_pokemon.setText("Nome:");

                    //Lista
                    type_pokemon.setText("Nome:");
            }else{
                Toast.makeText(DadosPokemonActivity.this, "Pokemon não encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
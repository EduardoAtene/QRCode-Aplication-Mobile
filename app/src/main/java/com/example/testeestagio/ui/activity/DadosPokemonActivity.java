package com.example.testeestagio.ui.activity;


import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;

import com.example.testeestagio.R;
import com.example.testeestagio.api.Conexao;
import com.example.testeestagio.api.ConexaoImage;
import com.example.testeestagio.api.ConsumirJson;
import com.example.testeestagio.model.PokemonDados;



public class DadosPokemonActivity extends Activity {
    private PokemonDados pokemonDados = null;
    private String url = receberRequest(ScanQrcodeActivity.getDadoScan());
    TextView id_pokemon;
    TextView nome_pokemon;
    TextView type_pokemon;
    ImageView img_pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dados_pokemon_activity);

        id_pokemon = findViewById(R.id.dados_pokemon_activity_id);
        nome_pokemon = findViewById(R.id.dados_pokemon_activity_name);
        type_pokemon = findViewById(R.id.dados_pokemon_activity_type);
        img_pokemon = findViewById(R.id.dados_pokemon_activity_image_pokemon);

        Tarefa tarefa = new Tarefa();
        tarefa.execute(url);
//        ImageTarefa image = new ImageTarefa();
//        image.execute(pokemonDados.getFront_default_img());



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
//            img = ConexaoImage.getDados(strings[1]);

            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            pokemonDados = ConsumirJson.pokemonDados(s);
            ImageTarefa img = new ImageTarefa();
            img.execute(pokemonDados.getFront_default_img());
            exibirDadosPokemon();
        }

        private void exibirDadosPokemon() {
            if (pokemonDados != null) {
                    id_pokemon.setText("ID : " + pokemonDados.getId() );
                    nome_pokemon.setText("Nome: " + pokemonDados.getName() );
                    for(int x=0; x<pokemonDados.getTypes().size();x++){
                        if(x==0){type_pokemon.append(pokemonDados.getType(x));}
                        else{type_pokemon.append(", "+ pokemonDados.getType(x));}

                    }
            }else{
                Toast.makeText(DadosPokemonActivity.this, "Pokemon não encontrado", Toast.LENGTH_SHORT).show();
            }
        }
    }
    class ImageTarefa extends AsyncTask<String,String,String>{
        Bitmap img = null;
        @Override
        protected String doInBackground(String... strings) {
            img = ConexaoImage.getDados(strings[0]);
            return "Foi";
        }

        @Override
        protected void onPostExecute(String s) {
            exibirImagePokemon();
        }

        private void exibirImagePokemon() {
            if (img != null) {
                    img_pokemon.setImageBitmap(img);
            }else{
                Toast.makeText(DadosPokemonActivity.this, "Image not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
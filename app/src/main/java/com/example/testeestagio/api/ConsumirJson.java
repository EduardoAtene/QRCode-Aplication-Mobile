 package com.example.testeestagio.api;



import android.util.Log;

import com.example.testeestagio.model.PokemonDados;

import org.json.JSONArray;
import org.json.JSONObject;


 public class ConsumirJson {


     public static PokemonDados pokemonDados(String conteudo){
        try {
            PokemonDados dadosPokemon = new PokemonDados();
            JSONObject jsonObject = new JSONObject(conteudo);
            JSONArray types_json = jsonObject.getJSONArray("types");

            dadosPokemon.setId(jsonObject.getString("id"));
            dadosPokemon.setName(jsonObject.getString("name"));

            for (int x =0; x<types_json.length();x++){
                dadosPokemon.setTipo(types_json.getJSONObject(x).getJSONObject("type").getString("name"));
            }

            dadosPokemon.setFront_default_img(jsonObject.getJSONObject("sprites").getString("front_default"));
            Log.e("Teste",dadosPokemon.getFront_default_img());

            return dadosPokemon;

        }catch (Exception e){
            // JSONException
            e.printStackTrace();
            return null;
        }
    }
}

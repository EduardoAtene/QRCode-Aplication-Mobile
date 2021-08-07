 package com.example.testeestagio.api;



import com.example.testeestagio.model.PokemonDados;
import org.json.JSONArray;
import org.json.JSONObject;



 public class ConsumirJson {


     public static PokemonDados pokemonDados(String conteudo){
        try {
            PokemonDados dadosPokemon = new PokemonDados();
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   jsonArray = new JSONArray(conteudo);

            jsonObject = jsonArray.getJSONObject(0);

            dadosPokemon.setId(jsonObject.getString("id"));
            dadosPokemon.setName(jsonObject.getString("name"));

            return dadosPokemon;

        }catch (Exception e){
            // JSONException
            e.printStackTrace();
            return null;
        }
    }
}

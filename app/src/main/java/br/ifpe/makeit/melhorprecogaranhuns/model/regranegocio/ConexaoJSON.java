package br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;

/**
 * Created by kaio on 22/07/2018.
 */

public class ConexaoJSON {
    private static final String TAG = "ConexaoJSON";

    public static String downJSON(Context context) {
        String resultado = "";

        try {
            InputStream inputStream = context.getAssets().open("produtos.json");
            int tamanho = inputStream.available();
            byte[] buffer = new byte[tamanho];
            inputStream.read(buffer);
            inputStream.close();

            resultado = new String(buffer);

        } catch (IOException e) {
            Log.d(TAG, "Mensagem de erro io: "+e.getMessage());
        }

        return resultado;
    }

    public static ArrayList<ArqJSON> parserJSON(Context context) {
        ArrayList<ArqJSON> listaProdutos = new ArrayList<>();
        String resultado = downJSON(context);

        try {
            JSONArray arrayProdutos = new JSONArray(resultado);
            for(int i=0; i<arrayProdutos.length(); i++) {
                ArqJSON arqJSON = new ArqJSON();
                JSONObject jo = arrayProdutos.getJSONObject(i);

                arqJSON.setProduto_id(jo.getInt("produto_id"));
                arqJSON.setDescricao(jo.getString("descricao"));
                arqJSON.setNomeFantasia(jo.getString("nomeFantasia"));
                arqJSON.setDataEmissao(jo.getString("dataEmissao"));
                arqJSON.setGtin(jo.getString("gtin"));
                arqJSON.setvUnit(jo.getDouble("vUnit"));
                arqJSON.setEndereco(jo.getString("endereco"));
                arqJSON.setBairro(jo.getString("bairro"));
                arqJSON.setMunicipio(jo.getString("municipio"));

                listaProdutos.add(arqJSON);
            }
        } catch(JSONException e) {
            Log.d(TAG, "Mensagem de erro json: "+e.getMessage());
        }

        return listaProdutos;
    }
}

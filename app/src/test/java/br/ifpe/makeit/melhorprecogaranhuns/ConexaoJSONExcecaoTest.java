package br.ifpe.makeit.melhorprecogaranhuns;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;

/**
 * Created by kaio on 25/07/2018.
 */

public class ConexaoJSONExcecaoTest {
    @Test(expected = IOException.class)
    public void deveDarErroDeExceçãoDownJSON() throws IOException {
        String resultado = "";
        boolean baixado = false;

        try {
            InputStream inputStream = new FileInputStream("produtos.json");
            int tamanho = inputStream.available();
            byte[] buffer = new byte[tamanho];
            inputStream.read(buffer);
            inputStream.close();

            resultado = new String(buffer);

        } catch (IOException e) {
            throw new IOException("Disparou exceção...");
        }

        if(resultado.length() > 1) {
            baixado = true;
        }

        Assert.assertTrue(baixado);
    }

    @Test(expected = RuntimeException.class)
    public void deveDarErroDeExceçãoParserJSON() throws JSONException, RuntimeException {
        ArqJSON arqJSON = new ArqJSON();
        ArrayList<ArqJSON> listaProdutos = new ArrayList<>();
        String resultado = "{\"produto_id\":\"13\",\"bairro\":\"HELIOPOLIS\",\"cep\":\"55296190\",\"cnpj\":\"61585865147926\","+
                "\"codMunicipio\":\"2606002\",\"dataEmissao\":\"2018-04-15T22:01:19-03:00\",\"descricao\":\"REXONA MEN MOTIONSENSE TM\","+
                "\"endereco\":\"AVENIDA PREDO CORREIA 30, GARANHUNS, Brazil\",\"gtin\":\"7791293025537\",\"inscEstadual\":\"067399037\","+
                "\"latitude\":\"\",\"longitude\":\"\",\"municipio\":\"GARANHUNS\",\"ncm\":\"30051090\",\"nomeFantasia\":\"JUNTOS SOMOS MAIS\","+
                "\"razaoSocial\":\"JOSÉ CORDEIRO DA SILVA S.A.\",\"vUnit\":\"8.89\",\"chaveAcesso\":null}";

        try {
            JSONArray arrayProdutos = new JSONArray(resultado);
            for(int i=0; i<arrayProdutos.length(); i++) {
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
            throw new JSONException("Disparou uma exceção...");
        } catch (RuntimeException re) {
            throw new RuntimeException("Disparou uma exceção...");
        }
    }
}

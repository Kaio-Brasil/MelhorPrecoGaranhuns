package br.ifpe.makeit.melhorprecogaranhuns;

import junit.framework.Assert;
import junit.framework.TestCase;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;

/**
 * Created by kaio on 25/07/2018.
 */

public class ArqJSONTest extends TestCase {
    public void testDeveRetornaValoresArmazenadoNoObjeto() {
        ArqJSON arqJSON = new ArqJSON();
        arqJSON.setProduto_id(1);
        arqJSON.setDescricao("Sabonete Dove");
        arqJSON.setvUnit(3.30);
        arqJSON.setDataEmissao("2018-04-15T22:01:19-03:00");
        arqJSON.setCodMunicipio("2606002");
        arqJSON.setCep("55296190");
        arqJSON.setEndereco("AVENIDA PREDO CORREIA 30, GARANHUNS, Brazil");
        arqJSON.setBairro("HELIOPOLIS");
        arqJSON.setMunicipio("GARANHUNS");
        arqJSON.setGtin("7791293025537");
        arqJSON.setInscEstadual("067399037");
        arqJSON.setLatitude("");
        arqJSON.setLongitude("");
        arqJSON.setNomeFantasia("GARANHUNS 1");
        arqJSON.setRazaoSocial("RAIADROGASIL S.A.");
        arqJSON.setCnpj("61585865147926");
        arqJSON.setNcm("30049069");
        arqJSON.setChaveAcesso(null);

        Assert.assertEquals("Sabonete Dove", arqJSON.getDescricao());
        Assert.assertEquals(3.30, arqJSON.getvUnit());
        Assert.assertEquals("AVENIDA PREDO CORREIA 30, GARANHUNS, Brazil", arqJSON.getEndereco());
        Assert.assertEquals("GARANHUNS", arqJSON.getMunicipio());
        Assert.assertEquals("RAIADROGASIL S.A.", arqJSON.getRazaoSocial());

    }
}

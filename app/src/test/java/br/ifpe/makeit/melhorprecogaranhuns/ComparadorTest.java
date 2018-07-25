package br.ifpe.makeit.melhorprecogaranhuns;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;
import br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio.Comparador;

/**
 * Created by kaio on 24/07/2018.
 */

public class ComparadorTest extends TestCase {
    public void testDeveCompararObjetosDeUmaLista() {
        int tipo = 0;

        ArqJSON arqJSON1 = new ArqJSON();
        arqJSON1.setProduto_id(12);
        arqJSON1.setDescricao("Rexono");
        arqJSON1.setvUnit(8.50);

        ArqJSON arqJSON2 = new ArqJSON();
        arqJSON2.setProduto_id(15);
        arqJSON2.setDescricao("Rexono men");
        arqJSON2.setvUnit(9.50);

        ArqJSON arqJSON3 = new ArqJSON();
        arqJSON3.setProduto_id(16);
        arqJSON3.setDescricao("Dental");
        arqJSON3.setvUnit(2.80);

        ArqJSON arqJSON4 = new ArqJSON();
        arqJSON4.setProduto_id(17);
        arqJSON4.setDescricao("Prestobarba");
        arqJSON4.setvUnit(3.89);

        ArqJSON arqJSON5 = new ArqJSON();
        arqJSON5.setProduto_id(17);
        arqJSON5.setDescricao("Prestobarba");
        arqJSON5.setvUnit(3.89);

        List<ArqJSON> listaMaiorPreco = new ArrayList<>();
        listaMaiorPreco.add(arqJSON1);
        listaMaiorPreco.add(arqJSON2);
        listaMaiorPreco.add(arqJSON3);
        listaMaiorPreco.add(arqJSON4);
        listaMaiorPreco.add(arqJSON5);

        List<ArqJSON> listaMenorPreco = new ArrayList<>();
        listaMenorPreco.add(arqJSON1);
        listaMenorPreco.add(arqJSON2);
        listaMenorPreco.add(arqJSON3);
        listaMenorPreco.add(arqJSON4);
        listaMenorPreco.add(arqJSON5);

        List<ArqJSON> listaDescricao = new ArrayList<>();
        listaDescricao.add(arqJSON1);
        listaDescricao.add(arqJSON2);
        listaDescricao.add(arqJSON3);
        listaDescricao.add(arqJSON4);
        listaDescricao.add(arqJSON5);

        Collections.sort(listaMaiorPreco, new Comparador(1));
        Collections.sort(listaMenorPreco, new Comparador(2));
        Collections.sort(listaDescricao, new Comparador(3));

        Assert.assertEquals(9.50, listaMaiorPreco.get(0).getvUnit());
        Assert.assertEquals(2.80, listaMenorPreco.get(0).getvUnit());
        Assert.assertEquals("Dental", listaDescricao.get(0).getDescricao());
    }

}

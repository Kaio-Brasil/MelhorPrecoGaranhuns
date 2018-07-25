package br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio;

import java.util.Comparator;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;

/**
 * Created by kaio on 22/07/2018.
 */

public class Comparador implements Comparator<ArqJSON> {
    private int tipoComparacao;

    public Comparador(int tipoComparacao) {
        this.tipoComparacao = tipoComparacao;
    }

    public int getTipoComparacao() {
        return tipoComparacao;
    }

    public void setTipoComparacao(int tipoComparacao) {
        this.tipoComparacao = tipoComparacao;
    }

    @Override
    public int compare(ArqJSON arq1, ArqJSON arq2) {
        if(tipoComparacao == 1) {
            return compararMaiorParaMenor(arq1, arq2);
        } else if(tipoComparacao == 2) {
            return compararMenorParaMaior(arq1, arq2);
        } else {
            return comparaPorDescricao(arq1, arq2);
        }
    }

    public int compararMenorParaMaior(ArqJSON preco1, ArqJSON preco2) {
        if(preco1.getvUnit() > preco2.getvUnit()) {
            return 1;
        }
        if(preco1.getvUnit() < preco2.getvUnit()) {
            return -1;
        }

        return 0;
    }

    public int compararMaiorParaMenor(ArqJSON preco1, ArqJSON preco2) {
        if(preco1.getvUnit() < preco2.getvUnit()) {
            return 1;
        }
        if(preco1.getvUnit() > preco2.getvUnit()) {
            return -1;
        }

        return 0;
    }

    public int comparaPorDescricao(ArqJSON descricao1, ArqJSON descricao2) {
        return descricao1.getDescricao().compareTo(descricao2.getDescricao());
    }

}

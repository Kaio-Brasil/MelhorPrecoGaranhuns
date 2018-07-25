package br.ifpe.makeit.melhorprecogaranhuns.model.entidade;

import java.util.Objects;

/**
 * Created by kaio on 19/07/2018.
 */

public class ArqJSON {
    private Integer produto_id;
    private String descricao;
    private Double vUnit;
    private String dataEmissao;
    private String codMunicipio;
    private String cep;
    private String endereco;
    private String bairro;
    private String municipio;
    private String gtin;
    private String inscEstadual;
    private String latitude;
    private String longitude;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String ncm;
    private String chaveAcesso;

    public ArqJSON() {}

    public ArqJSON(Integer produto_id, String descricao, Double vUnit, String dataEmissao, String codMunicipio, String cep, String endereco,
                   String bairro, String municipio, String gtin, String inscEstadual, String latitude, String longitude,
                        String nomeFantasia, String razaoSocial, String cnpj, String ncm, String chaveAcesso) {
        this.produto_id = produto_id;
        this.descricao = descricao;
        this.vUnit = vUnit;
        this.dataEmissao = dataEmissao;
        this.codMunicipio = codMunicipio;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.municipio = municipio;
        this.gtin = gtin;
        this.inscEstadual = inscEstadual;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.ncm = ncm;
        this.chaveAcesso = chaveAcesso;
    }

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getvUnit() {
        return vUnit;
    }

    public void setvUnit(Double vUnit) {
        this.vUnit = vUnit;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArqJSON arqJSON = (ArqJSON) o;
        return Objects.equals(produto_id, arqJSON.produto_id) &&
                Objects.equals(gtin, arqJSON.gtin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto_id, gtin);
    }

}

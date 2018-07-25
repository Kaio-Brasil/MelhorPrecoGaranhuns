package br.ifpe.makeit.melhorprecogaranhuns;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;
import br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio.ArqJSONAdapter;
import br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio.Comparador;
import br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio.ConexaoJSON;

public class BuscaProdutosActivity extends AppCompatActivity {
    private static final String TAG = "LeitorActivity";
    private ListView mListView;
    private ArrayList<ArqJSON> arqJSON;
    private ArrayList<ArqJSON> tempList;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_produtos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mListView = (ListView) findViewById(R.id.lista_itens_bp);
        listarItens();
        mostrarQuantItem();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArqJSON arqIntent = (ArqJSON) mListView.getItemAtPosition(i);
                Intent intent = new Intent(BuscaProdutosActivity.this, DetalheItemActivity.class);
                intent.putExtra("CODIGO", arqIntent.getProduto_id());
                intent.putExtra("DESCRICAO", arqIntent.getDescricao());
                intent.putExtra("PRECO", arqIntent.getvUnit());
                intent.putExtra("ESTABELECIMENTO", arqIntent.getNomeFantasia());
                intent.putExtra("ENDERECO", arqIntent.getEndereco());
                intent.putExtra("BAIRRO", arqIntent.getBairro());
                intent.putExtra("CIDADE", arqIntent.getMunicipio());
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(BuscaProdutosActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });
    }

    public void listarItens() {
        arqJSON = ConexaoJSON.parserJSON(this);
        carregarListView(arqJSON);
        mostrarQuantItem();
        tempList = new ArrayList<>();
    }

    public void listarMaiorPreco() {
        if(tempList.size() > 0) {
            Collections.sort(tempList, new Comparador(1));
            carregarListView(tempList);
        } else {
            Collections.sort(arqJSON, new Comparador(1));
            carregarListView(arqJSON);
        }
    }

    public void listarMenorPreco() {
        if(tempList.size() > 0) {
            Collections.sort(tempList, new Comparador(2));
            carregarListView(tempList);
        } else {
            Collections.sort(arqJSON, new Comparador(2));
            carregarListView(arqJSON);
        }
    }

    public void ordenarPorDescricao() {
        if(tempList.size() > 0) {
            Collections.sort(tempList, new Comparador(3));
            carregarListView(tempList);
        } else {
            Collections.sort(arqJSON, new Comparador(3));
            carregarListView(arqJSON);
        }
    }

    public void carregarListView(ArrayList<ArqJSON> ajson) {
        ArrayAdapter adapter = new ArqJSONAdapter(this, ajson);
        mListView.setAdapter(adapter);
    }

    public void mostrarQuantItem() {
        String quantidadeItems = arqJSON.size()>1 ? arqJSON.size()+" resultados encontrados" : arqJSON.size()+" resultado encontrado";
        mTextView = (TextView) findViewById(R.id.quantidade_item);
        mTextView.setText(quantidadeItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.buscaprodutos_activity_searchview, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search_busca).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                tempList = new ArrayList<>();

                for(int i=0; i<arqJSON.size(); i++) {
                    if(arqJSON.get(i).getDescricao().toLowerCase().contains(s.toString().toLowerCase())) {
                        tempList.add(arqJSON.get(i));
                    }
                }
                ArrayAdapter adapter = new ArqJSONAdapter(BuscaProdutosActivity.this, tempList);
                mListView.setAdapter(adapter);
                String quantidadeItems = tempList.size()>1 ? tempList.size()+" resultados encontrados" : tempList.size()+" resultado encontrado";
                mTextView.setText(quantidadeItems);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_max:
                listarMaiorPreco();
                break;
            case R.id.menu_min:
                listarMenorPreco();
                break;
            case R.id.menu_ordem_alfabetica:
                ordenarPorDescricao();
                break;
            case R.id.menu_limpar:
                listarItens();
                break;
        }

        return true;
    }

}

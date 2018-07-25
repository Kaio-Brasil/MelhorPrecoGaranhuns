package br.ifpe.makeit.melhorprecogaranhuns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalheItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Integer codigo = getIntent().getIntExtra("CODIGO", 0);
        TextView codView = (TextView) findViewById(R.id.cod_produto_item);
        codView.setText(String.valueOf(codigo));

        String descricao = getIntent().getStringExtra("DESCRICAO");
        TextView descricaoView = (TextView) findViewById(R.id.nome_produto_item);
        descricaoView.setText(descricao);

        Double preco = getIntent().getDoubleExtra("PRECO", 0);
        TextView precoView = (TextView) findViewById(R.id.preco_item);
        precoView.setText(String.format("%.2f", preco));

        String estabelecimento = getIntent().getStringExtra("ESTABELECIMENTO");
        TextView estabelecimentoView = (TextView) findViewById(R.id.nome_est_item);
        estabelecimentoView.setText(estabelecimento);

        String endereco = getIntent().getStringExtra("ENDERECO");
        TextView enderecoView = (TextView) findViewById(R.id.end_item);
        enderecoView.setText(endereco);

        String bairro = getIntent().getStringExtra("BAIRRO");
        TextView bairroView = (TextView) findViewById(R.id.bairro_item);
        bairroView.setText(bairro);

        String cidade = getIntent().getStringExtra("CIDADE");
        TextView cidadeView = (TextView) findViewById(R.id.cidade_item);
        cidadeView.setText(cidade);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left, R.anim.fade_out);
    }

}

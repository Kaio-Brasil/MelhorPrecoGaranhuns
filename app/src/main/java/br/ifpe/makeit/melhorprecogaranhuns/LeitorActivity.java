package br.ifpe.makeit.melhorprecogaranhuns;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;
import br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio.ArqJSONAdapter;
import br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio.ConexaoJSON;

public class LeitorActivity extends AppCompatActivity {
    private static final String TAG = "LeitorActivity";
    private static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    private ListView mListView;
    private String mResultadoScan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        scannearCodBarras();

        FloatingActionButton mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scannearCodBarras();
            }
        });
    }

    public void listarItensListView() {
        mListView = (ListView) findViewById(R.id.lista_itens);
        ArrayAdapter adapter;
        ArrayList<ArqJSON> arqJSON = ConexaoJSON.parserJSON(this);
        ArrayList<ArqJSON> listarArqJSON = new ArrayList<>();

        for(int i=0; i<arqJSON.size(); i++) {
            if(mResultadoScan.equals(arqJSON.get(i).getGtin())) {
                listarArqJSON.add(arqJSON.get(i));
            }
        }

        if(listarArqJSON != null) {
            adapter = new ArqJSONAdapter(this, listarArqJSON);
            mListView.setAdapter(adapter);
        }

        if(listarArqJSON.isEmpty()) {
            Toast.makeText(this, "Produto não encontrado.", Toast.LENGTH_SHORT).show();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArqJSON arqIntent = (ArqJSON) mListView.getItemAtPosition(i);
                Intent intent = new Intent(LeitorActivity.this, DetalheItemActivity.class);
                intent.putExtra("CODIGO", arqIntent.getProduto_id());
                intent.putExtra("DESCRICAO", arqIntent.getDescricao());
                intent.putExtra("PRECO", arqIntent.getvUnit());
                intent.putExtra("ESTABELECIMENTO", arqIntent.getNomeFantasia());
                intent.putExtra("ENDERECO", arqIntent.getEndereco());
                intent.putExtra("BAIRRO", arqIntent.getBairro());
                intent.putExtra("CIDADE", arqIntent.getMunicipio());
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.move_right);
                ActivityCompat.startActivity(LeitorActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

    }

    public void scannearCodBarras() {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(LeitorActivity.this, "Scanner não encontrado", "Baixar o Scanner Code Activity?", "SIM", "NÃO").show();
            Log.d(TAG,"Erro ao scannear. mensagem de erro: "+anfe);
        }
    }

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {
                    mResultadoScan = intent.getStringExtra("SCAN_RESULT");
                    Log.d(TAG,"Valor do Scan: "+mResultadoScan);
                    listarItensListView();
                } catch (Exception e) {
                    Log.d(TAG, "Erro ao converter uma string para um inteiro. mensagem de erro: "+e);
                }
            }
        }
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

}

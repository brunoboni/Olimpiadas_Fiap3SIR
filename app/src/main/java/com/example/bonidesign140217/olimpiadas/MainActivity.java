package com.example.bonidesign140217.olimpiadas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Dialog dialog;
    private EditText nome, idade;
    private TextView edtnomeD,edtIdadeD,edtperiodoD,edtmodalidadeD;
    private RadioGroup periodos;
    private RadioButton periodoselecionado;
    private Spinner modalidades;
    private Button reservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.txtnome);
        idade = (EditText) findViewById(R.id.txtidade);
        periodos = (RadioGroup) findViewById(R.id.rgperiodo);
        periodoselecionado = (RadioButton) findViewById(periodos.getCheckedRadioButtonId());
        modalidades = (Spinner) findViewById(R.id.spinnermodalidade);
        reservar = (Button) findViewById(R.id.btnreservar);


    }


    public void reservarIngresso(View V) {
        if (nome.getText().length() == 0 || idade.getText().length() == 0) {
            String msg = "";
            if (nome.getText().length() == 0)
                msg = "Nome obrigatorio";
            if (idade.getText().length() == 0)
                msg = "Idade obrigatoria";
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            return;
        }

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogolimpiadas);


        edtnomeD = (TextView) dialog.findViewById(R.id.edtNomeD);
        edtIdadeD = (TextView) dialog.findViewById((R.id.edtIdadeD));
        edtmodalidadeD = (TextView) dialog.findViewById(R.id.edtModalidadeD);
        edtperiodoD = (TextView) dialog.findViewById((R.id.edtPeriodoD));


        //setando no Dialog os dados da MainActivity
        edtnomeD.setText(nome.getText());
        edtIdadeD.setText(idade.getText());
        edtmodalidadeD.setText(modalidades.getSelectedItem().toString());
        edtperiodoD.setText(periodoselecionado.getText());

        dialog.show();
    }

    public void finalizar(View V) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Confirmar reserva");
        b.setMessage("Confirma a reserva?");
        b.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                fechar();
            }
        });

        b.show();
    }


    private void fechar() {
        dialog.dismiss();
        nome.setText("");
        idade.setText("");
        modalidades.setSelection(0);
        periodos.check(R.id.manha);
    }
}

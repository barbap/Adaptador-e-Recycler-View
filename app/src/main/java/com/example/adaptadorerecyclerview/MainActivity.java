package com.example.adaptadorerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adaptadorerecyclerview.EventoDeClickCustomizado;

public class MainActivity extends AppCompatActivity {


    private EditText Number1, Number2;
    private Button calcula;
    private Spinner spinner;
    private RecyclerView recyclerView;


    private String operation = "";
    private Calculo[] lista = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number1 = findViewById(R.id.Number1);
        Number2 = findViewById(R.id.Number2);

        calcula = findViewById(R.id.Calcular);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        AdaptadorRV adaptador = new AdaptadorRV(lista);
        recyclerView.setAdapter(adaptador);

        adaptador.eventoClickDeletar = new EventoDeClickCustomizado<Calculo>() {
            @Override
            public void onItemClick(Calculo item) {
                Toast.makeText(getBaseContext(),"Item deletado",
                Toast.LENGTH_SHORT).show();

                adaptador.getList().remove(item);
                adaptador.notifyDataSetChanged();
            }
        };
        adaptador.eventoClickLinha = new EventoDeClickCustomizado<Calculo>() {
            @Override
            public void onItemClick(Calculo item) {
                Number1.setText(item.number1.toString());
                Number2.setText(item.number2.toString());
                if (item.opereation.equals("+")) {
                    operation = "soma";
                    spinner.setSelection(1,false);
                } else if (item.opereation.equals("-")) {
                    operation = "subtracao";
                    spinner.setSelection(2,false);
                } else if (item.opereation.equals("x")) {
                    operation = "multiplicação";
                    spinner.setSelection(3,false);
                }
                adaptador.notifyDataSetChanged();
            }
        };
        calcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et1 = Number1.getText().toString();
                String et2 = Number2.getText().toString();
                Double et1Convert,et2Convert;

                if(et1.isEmpty()){
                    Number1.setError("Campo obrigatório!");
                    return;
                }else{
                    et1Convert = Double.valueOf(et1);
                }

                if(et2.isEmpty()){
                    Number2.setError("Campo obrigatório!");
                    return;
                }else{
                    et2Convert = Double.valueOf(et2);
                }

                Double res;
                if(operation.equals("Soma")){
                    res = et1Convert + et2Convert;
                    Calculo cal = new Calculo();
                    cal.number1 = et1Convert;
                    cal.number2 = et2Convert;
                    cal.opereation = "+";
                    cal.result = res;

                    cal.calculo = et1Convert + " + " + et2Convert + " = " + res;

                    adaptador.getList().add(cal);
                    adaptador.notifyDataSetChanged();

                }else if(operation.equals("Subtração")){

                    res = et1Convert - et2Convert;
                    Calculo cal = new Calculo();
                    cal.number1 = et1Convert;
                    cal.number2 = et2Convert;
                    cal.opereation = "-";
                    cal.result = res;

                    cal.calculo = et1Convert + " - " + et2Convert + " = " + res;

                    adaptador.getList().add(cal);
                    adaptador.notifyDataSetChanged();

                }else if(operation.equals("Multiplicação")){

                    res = et1Convert * et2Convert;
                    Calculo cal = new Calculo();
                    cal.number1 = et1Convert;
                    cal.number2 = et2Convert;
                    cal.opereation = "x";
                    cal.result = res;

                    cal.calculo = et1Convert + " x " + et2Convert + " = " + res;

                    adaptador.getList().add(cal);
                    adaptador.notifyDataSetChanged();
                }



            }
        });

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adaptadorSpinner = new ArrayAdapter<>(
                getApplicationContext(), android.R.layout.simple_list_item_1
        );

        spinner.setAdapter(adaptadorSpinner);
        adaptadorSpinner.add("Selecione");
        adaptadorSpinner.add("Soma");
        adaptadorSpinner.add("Subtração");
        adaptadorSpinner.add("Multiplicação");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int option = spinner.getSelectedItemPosition();
                operation = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
package com.mas_aplicaciones.supercalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    EditText Display;
    String memoria = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display = findViewById(R.id.Display);
        final Button botonMemoria = findViewById(R.id.botonMem);
        botonMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( memoria.isEmpty() ) {
                    memoria = Display.getText().toString();
                    Display.setText("");
                    botonMemoria.setText(memoria);
                }
                else {
                    Display.append(memoria);
                    memoria = "";
                    botonMemoria.setText(R.string.Memoria);
                }
            }
        });
    }

    public void agregarValor(View vista) {
        Button boton = (Button) vista;
        Display.append(boton.getText());
    }

    public void agregarFuncion(View vista) {
        Button boton = (Button) vista;
        Display.setText(boton.getText() + "(" + Display.getText() + ")");
    }

    public void limpiarEntrada(View vista) {
        Display.setText("");
    }

    public void funcionIgual(View vista) {
        Double resultado = new Expression(Display.getText().toString()).calculate();
        Display.setText(String.valueOf(resultado));
    }
    public void agregarPunto(View vista) {
        String texto = Display.getText().toString();
        if( texto.isEmpty()  ) {
            return;
        }
        Double d = new Expression(texto+".0").calculate();

        if( d.isNaN() ) {
            return;
        }
        Display.append(".");
    }
    public void limpiarTodo(View vista) {
        if( !memoria.isEmpty() ) {
            memoria = "";
            Button botonMemoria = findViewById(R.id.botonMem);
            botonMemoria.setText(R.string.Memoria);
        }
        Display.setText("");
    }

}

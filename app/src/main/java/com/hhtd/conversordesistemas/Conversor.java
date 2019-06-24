package com.hhtd.conversordesistemas;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversor extends AppCompatActivity {
    public String TipoConversion="",Conversion;
    EditText Num;
    TextView Base1_Total, Base2_Total, Base3_Total;
    TextView Base_Inicial, Base1, Base2, Base3;
    public int Numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);
        /*Definir textbox*/
        Num=(EditText)findViewById(R.id.txt_Numero);
        /*Hacer que las letras introducidas sean mayusculas*/
        InputFilter Mayuscula=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    Character Letra=source.charAt(0);
                    if(Character.isLetter(Letra) || Character.isDigit(Letra)) {
                        return ""+Character.toUpperCase(Letra);
                    }
                    else{
                        return "";
                    }
                }
                catch (Exception e){
                    return null;
                }
            }
        };
        /*Activar filtro*/
        Num.setFilters(new InputFilter[]{Mayuscula});
        /*Definir labels*/
        Base_Inicial=(TextView)findViewById(R.id.lbl_Base_Inicial);
        Base1=(TextView)findViewById(R.id.lbl_Base1);
        Base2=(TextView)findViewById(R.id.lbl_Base2);
        Base3=(TextView)findViewById(R.id.lbl_Base3);
        /*Asignar los valores recibidos de la otra activity*/
        TipoConversion = getIntent().getStringExtra("Tipo_Conversion");
        Conversion = getIntent().getStringExtra("Conver");
        /*Cambiar el titulo segun los datos recibidos de la otra activity*/
        this.setTitle(TipoConversion);
        /*Segun los datos recibidos de la otra activity cambiar el texto de los labels*/
        switch (Conversion){
            case "Binario":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("01"));
                Base_Inicial.setText("Binario:");
                Base1.setText("Decimal:");
                Base2.setText("Hexadecimal:");
                Base3.setText("Octal:");
                break;
            case "Decimal":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                /*Cambiar texto de los labels*/
                Base_Inicial.setText("Decimal:");
                Base1.setText("Binario:");
                Base2.setText("Hexadecimal:");
                Base3.setText("Octal:");
                break;
            case "Hexadecimal":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_TEXT);
                /*Validar textbox*/
                InputFilter Hexa=new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        for(int i=start;i<end;i++){
                            String Validar=String.valueOf(source.charAt(i));
                            Pattern Pat=Pattern.compile("[ABCDEFabcdef1234567890]");
                            Matcher Match=Pat.matcher(Validar);
                            boolean Valido=Match.matches();
                            if(!Valido){
                                return "";
                            }
                        }
                        return null;
                    }
                };
                /*Actival filtro*/
                Num.setFilters(new InputFilter[]{Hexa});
                /*Cambiar texto de los labels*/
                Base_Inicial.setText("Hexadecimal:");
                Base1.setText("Binario:");
                Base2.setText("Decimal:");
                Base3.setText("Octal:");
                break;
            case "Octal":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                /*Cambiar texto de los labels*/
                Base_Inicial.setText("Octal:");
                Base1.setText("Binario:");
                Base2.setText("Decimal:");
                Base3.setText("Hexadecimal:");
                break;
        }
    }
    public void Calcular(View v){
        try{
           if(Num.getText().toString().equals("")){
               Num.setText("0");
           }
           else{
               Base1_Total=(TextView)findViewById(R.id.lbl_Base1_Total);
               Base2_Total=(TextView)findViewById(R.id.lbl_Base2_Total);
               Base3_Total=(TextView)findViewById(R.id.lbl_Base3_Total);
               /*Definir variables*/
               Numero=Integer.parseInt(Num.getText().toString());
               /*Segun los datos recibidos de la otra activity cambiar el tipo de operacion para la conversion*/
               switch (Conversion){
                   case "Binario":
                       int Exponente, Decimal, Digito = 0, Aux;
                       boolean esBinario;
                       do {
                           //comprobamos que sea un número binario es decir
                           //que este formado solo por ceros y unos
                           esBinario = true;
                           Aux = Numero;
                           while (Aux != 0) {
                               Digito = Aux % 10; //última cifra del números
                               if (Digito != 0 && Digito != 1) { //si no es 0 ó 1
                                   esBinario = false; //no es un número binario
                               }
                               Aux = Aux / 10; //quitamos la última cifra para repetir el proceso
                           }
                       } while (!esBinario); //se vuelve a pedir si no es binario
                       Exponente = 0;
                       Decimal = 0; //será el equivalente en base decimal
                       Toast.makeText(getApplicationContext(),"Numero: "+Decimal,Toast.LENGTH_SHORT).show();
                       while (Numero != 0) {
                           //se toma la última cifra
                           Digito = Numero % 10;
                           //se multiplica por la potencia de 2 correspondiente y se suma al número
                           Decimal = Decimal + Digito * (int) Math.pow(2, Exponente);
                           //se aumenta el exponente
                           Exponente++;
                           //se quita la última cifra para repetir el proceso
                           Numero = Numero / 10;
                           Toast.makeText(getApplicationContext(),"Binario",Toast.LENGTH_SHORT).show();
                       }
                       Base1_Total.setText(Decimal);
                       break;
                   case "Decimal":
                       Toast.makeText(getApplicationContext(),"Decimal",Toast.LENGTH_SHORT).show();
                       break;
                   case "Hexadecimal":
                       Toast.makeText(getApplicationContext(),"Hexadecimal",Toast.LENGTH_SHORT).show();
                       break;
                   case "Octal":
                       Toast.makeText(getApplicationContext(),"Octal",Toast.LENGTH_SHORT).show();
                       break;
               }
           }
        }
        catch (Exception e){
            /*En caso de error mostrar un mensaje*/
            Toast.makeText(getApplicationContext(),"Error algo paso: \n"+e,Toast.LENGTH_SHORT).show();
        }
    }
    public void Atras(View v){
        CambiarVentana();
    }
    public void CambiarVentana(){
        /*Abir la activity anterior*/
        Intent Ventana=new Intent(getApplicationContext(),Main.class);
        startActivity(Ventana);
    }
}

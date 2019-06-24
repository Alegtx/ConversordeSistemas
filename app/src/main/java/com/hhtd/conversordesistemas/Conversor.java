package com.hhtd.conversordesistemas;

import android.content.Intent;
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
        /*InputFilter Mayuscula=new InputFilter() {
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
        Num.setFilters(new InputFilter[]{Mayuscula});*/
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
                //Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("01"));
                Base_Inicial.setText("Binario:");
                Base1.setText("Decimal:");
                Base2.setText("Hexadecimal:");
                Base3.setText("Octal:");
                break;
            case "Decimal":
                /*Definir el input type*/
                //Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                Base_Inicial.setText("Decimal:");
                Base1.setText("Binario:");
                Base2.setText("Hexadecimal:");
                Base3.setText("Octal:");
                break;
            case "Hexadecimal":
                /*Definir el input type*/
                //Num.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("ABCDEFabcdef"));
                Base_Inicial.setText("Hexadecimal:");
                Base1.setText("Binario:");
                Base2.setText("Decimal:");
                Base3.setText("Octal:");
                break;
            case "Octal":
                /*Definir el input type*/
                //Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                Base_Inicial.setText("Octal:");
                Base1.setText("Binario:");
                Base2.setText("Decimal:");
                Base3.setText("Hexadecimal:");
                break;
        }
    }
    public void Calcular(View v){
        try{
            Base1_Total=(TextView)findViewById(R.id.lbl_Base1_Total);
            Base2_Total=(TextView)findViewById(R.id.lbl_Base2_Total);
            Base3_Total=(TextView)findViewById(R.id.lbl_Base3_Total);
            /*Definir variables*/
            Numero=Integer.parseInt(Num.getText().toString());
            /*Segun los datos recibidos de la otra activity cambiar el tipo de operacion para la conversion*/
            switch (Conversion){
                case "Binario":
                    Toast.makeText(getApplicationContext(),"Binario",Toast.LENGTH_SHORT).show();
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
        catch (Exception e){
            Num.setText("0");
            /*En caso de error mostrar un mensaje*/
            //Toast.makeText(getApplicationContext(),"Error algo paso",Toast.LENGTH_SHORT).show();
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

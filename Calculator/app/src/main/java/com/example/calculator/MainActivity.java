package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnPlus
            ,btnMinus,btnDivide,btnMultiply,btnDot,btnBracket,btnModulo,
    btnEqual,btnClear;
    TextView tvInput,tvOutput;
    String process,modulo;
    boolean checkBrecket=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        createFunctionButtons();
        tvInput.setText("");
        tvOutput.setText("0");


    }
    private void buton(Button btnMinus,String a){
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=tvInput.getText().toString();
                String process2=tvOutput.getText().toString();
                if(process2.equals("")||process2.equals("0")){
                    tvInput.setText(process+ a);
                }else{

                    tvInput.setText(process2+ a);
                    tvOutput.setText("");
                }

            }
        });
    }
    public void initialization(){
      //  btn0=findViewById(R.id.btn0);
      //  btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        btnMultiply=findViewById(R.id.btnMultiply);
        btnMinus=findViewById(R.id.btnMinus);
        btnPlus=findViewById(R.id.btnPlus);
        btnDivide=findViewById(R.id.btnDivide);
        btnDot=findViewById(R.id.btnDot);
        btnBracket=findViewById(R.id.btnBracket);
        btnModulo=findViewById(R.id.btnModulo);
        btnEqual=findViewById(R.id.btnEqual);
        btnClear=findViewById(R.id.btnClear);
        tvInput=findViewById(R.id.operations);
        tvOutput=findViewById(R.id.result);
    }
    public void createFunctionButtons(){
        buton(btn0,"0");
        buton(btn1,"1");
        buton(btn2,"2");
        buton(btn3,"3");
        buton(btn4,"4");
        buton(btn5,"5");
        buton(btn6,"6");
        buton(btn7,"7");
        buton(btn8,"8");
        buton(btn9,"9");
        buton(btnClear,"");
        buton(btnMinus,"-");
        buton(btnPlus,"+");
        buton(btnMultiply,"*");
        buton(btnDivide,"/");
        buton(btnModulo,"%");
        buton(btnDot,".");
        buton(btnMinus,"-");
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvOutput.setText("");
            }
        });


        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=tvInput.getText().toString();

                process=process.replaceAll("%","/100*");
                process=process.replaceAll("x","*");
                Context rrhino=Context.enter();
                rrhino.setOptimizationLevel(-1);
                String finalResult="";
                try{
                    Scriptable scriptable= rrhino.initStandardObjects();
                    finalResult= rrhino.evaluateString(scriptable,process,"javascript",1,null).toString();
                }catch(Exception e){
                    finalResult="0";
                }
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);
                Double d=Double.parseDouble(finalResult);
                tvOutput.setText(df.format(d)+"");
            }
        });
        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBrecket){
                    process=tvInput.getText().toString();
                    tvInput.setText(process+ ")");
                    checkBrecket=false;
                }else{
                    process=tvInput.getText().toString();
                    tvInput.setText(process+ "(");
                    checkBrecket=true;

                }
            }
        });

    }
}
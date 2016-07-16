package com.example.hzg.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point;

    Button btn_divide;
    Button btn_multiply;
    Button btn_add;
    Button btn_cut;
    Button btn_equal;
    Button btn_clear;
    Button btn_delete;
    EditText et_input;
    boolean clear_flag;//清空标示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //实例化按钮
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_point = (Button) findViewById(R.id.btn_point);

        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_cut = (Button) findViewById(R.id.btn_cut);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        et_input = (EditText) findViewById(R.id.et_input);


        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_cut.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        et_input.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                  checkOperate();
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.btn_add:
            case R.id.btn_cut:
            case R.id.btn_multiply:
            case R.id.btn_divide:
//                if (clear_flag) {
//                    clear_flag = false;
//                    str = "";
//                    et_input.setText("");
//                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.btn_clear:
                clear_flag = false;
                et_input.setText("");
                break;
            case R.id.btn_delete:
                if (str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;
        }
    }
    //检查str里是否有操作符号
    private void checkOperate(){
        String exp = et_input.getText().toString();
        if (exp.contains(" ")) {
            clear_flag=false;
        }
    }
//运算结果
    private void getResult() {
        clear_flag = true;
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals("")) {return;}
        int space = exp.indexOf(' ');
        if (!exp.contains(" ")) {return;}
        if (exp.substring(0).equals(space)){return;}
        if (exp.substring(4).equals(space)||exp.substring(4).equals("+")||exp.substring(4).equals("-")||exp.substring(4).equals("×")||exp.substring(4).equals("÷")){return;}
        String s1 = exp.substring(0, space);
        String op = exp.substring(space + 1, space + 2);
        String s2 = exp.substring(space + 3);
        double r = 0;
        double arg1 = Double.parseDouble(s1);
        double arg2 = Double.parseDouble(s2);
        if (op.equals("+")) {
            r = arg1 + arg2;
        } else if (op.equals("-")) {
            r = arg1 - arg2;
        } else if (op.equals("×")) {
            r = arg1 * arg2;
        } else if (op.equals("÷")) {
            if (arg2 == 0) {
                r = 0;
            } else {
                r = arg1 / arg2;
            }
        }

        if (!s1.contains(".") && !s2.contains(".")&&!op.equals("÷")) {
            int result = (int) r;
            et_input.setText(result + "");
        } else {
            et_input.setText(r + "");
        }

    }
}

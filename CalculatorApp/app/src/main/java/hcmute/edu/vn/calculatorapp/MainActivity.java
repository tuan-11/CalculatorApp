package hcmute.edu.vn.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    String [] BODMAS_RULE = {"÷","×","\\+","-"};
    int CURRENT_RULE = -1;
    int calculation = -1; // Phép tính thể hiện qua 0-cộng, 1-trừ, 2-nhân, 3-chia, -1(chưa chọn phép tính)
    boolean showing_results = false;
    TextView tvResult, tvInput;
    MaterialButton buttonClear, buttonBack, buttonPercent, buttonDot, buttonEqual;
    MaterialButton buttonAdd, buttonSubtract, buttonMultiply, buttonDivide;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        tvInput = findViewById(R.id.tv_input);

        button0 = findViewById(R.id.btn_0);
        button1 = findViewById(R.id.btn_1);
        button2 = findViewById(R.id.btn_2);
        button3 = findViewById(R.id.btn_3);
        button4 = findViewById(R.id.btn_4);
        button5 = findViewById(R.id.btn_5);
        button6 = findViewById(R.id.btn_6);
        button7 = findViewById(R.id.btn_7);
        button8 = findViewById(R.id.btn_8);
        button9 = findViewById(R.id.btn_9);

        buttonClear = findViewById(R.id.btn_clear);
        buttonBack = findViewById(R.id.btn_back);
        buttonPercent = findViewById(R.id.btn_percent);
        buttonDot = findViewById(R.id.btn_dot);
        buttonEqual = findViewById(R.id.btn_equal);
        buttonAdd = findViewById(R.id.btn_add);
        buttonSubtract = findViewById(R.id.btn_subtract);
        buttonMultiply = findViewById(R.id.btn_multiply);
        buttonDivide = findViewById(R.id.btn_divide);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number_click("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {number_click("8");}
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {number_click("9");}
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clear_cache();

                buttonDot.setEnabled(true);

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputText = tvInput.getText().toString();

                if(!inputText.isEmpty()){
                    tvInput.setText(inputText.subSequence(0, inputText.length() - 1));
                }

                buttonDot.setEnabled(true);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                operation_click("+");
                calculation = 0;

            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                operation_click("-");
                calculation = 1;

            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                operation_click("×");
                calculation = 2;
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                operation_click("÷");
                calculation = 3;

            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int check = 0;
                String inputText = tvInput.getText().toString();

                if (calculation == -1) { //Nếu bắt đầu một phép tính mới bằng dấu chấm thì tự động thêm số 0 và dấu chấm

                    char[] chars = inputText.toCharArray();
                    for (int i = inputText.length() - 1; i >= 0; i--) {
                        if (Character.toString(chars[i]).equals(".")) {
                            buttonDot.setEnabled(false);
                            return;
                        }
                    }

                    if (tvInput.getText().toString().equals("")) {
                        tvInput.setText("0.");
                    } else {
                        if(showing_results){
                            clear_cache();
                            tvInput.setText("0.");
                        }else{
                            tvInput.setText(inputText + ".");
                        }
                    }

                } else {
                    if(showing_results) {
                        clear_cache();
                        tvInput.setText("0.");
                        return;
                    }
                    char[] chars = inputText.toCharArray();
                    for (int i = inputText.length() - 1; i >= 0; i--) {
                        if (Character.toString(chars[i]).equals("+") || Character.toString(chars[i]).equals("-") || Character.toString(chars[i]).equals("×") || Character.toString(chars[i]).equals("÷")) {
                            check = i;
                            break;
                        }else if (Character.toString(chars[i]).equals(".")) {
                            buttonDot.setEnabled(false);
                            return;
                        }

                    }
                    try{
                        if(!Character.toString(chars[check + 1]).equals("")){
                            tvInput.setText(inputText + ".");
                        }
                    }catch (Exception e){
                        tvInput.setText(inputText + "0.");
                        e.printStackTrace();
                    }

                    // đang sửa
                }


            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = tvInput.getText().toString();

                int begin = 0;
                Double resultPer;
                if(inputText.isEmpty())
                    return;
                if(!showing_results) { // Khi chưa thực hiện phép tính nào trước đó VD 2%=0.02
                    if(calculation == -1){
                        resultPer = Double.valueOf(inputText) * 1/100;
                        tvInput.setText(String.valueOf(resultPer));
                    }else { //VD 1-2% = 1-0.02
                        for(int i = inputText.length() - 1; i>=0; i--){
                            if(inputText.charAt(i) == '+' || inputText.charAt(i) == '-' || inputText.charAt(i) == '×' || inputText.charAt(i) == '÷' || inputText.charAt(i) == '%'){
                                break;
                            } else {
                                begin++;
                            }
                        }
                        resultPer = Double.valueOf(inputText.substring(inputText.length() - begin)) * 1/100;
                        tvInput.setText(inputText.substring(0, inputText.length() - begin) + String.valueOf(resultPer));
                    }
                }else { // Khi đã thực hiện phép tính
                    String per = tvResult.getText().toString().substring(1);
                    resultPer = Double.valueOf(per) * 1/100;
                    tvInput.setText(String.valueOf(resultPer));
                }

                buttonDot.setEnabled(true);
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputText = tvInput.getText().toString();

                if(inputText.isEmpty())
                    return;

                char getLastCharacter = inputText.charAt(inputText.length()-1);
                //VD 12-3
                if(getLastCharacter != '+' && getLastCharacter != '-' && getLastCharacter != '×' && getLastCharacter != '÷' && getLastCharacter != '%' && getLastCharacter != '.'){

                    calculateResult(inputText);

                }else {
                    if(inputText.charAt(inputText.length() - 2) == '.'){ //VD 12.-
                        tvResult.setText("= " + inputText.substring(0, inputText.length()-2));
                    }else {//VD 12-
                        tvResult.setText("= " + inputText.substring(0, inputText.length()-1));
                    }
                }

                showing_results = true;
                buttonDot.setEnabled(true);
            }
        });
    }
    private void number_click(String num) {
        //Nếu đang hiện kết quả (do nhấn dấu bằng) thì xoá kết quả và bắt đầu phép tính mới
        if (showing_results) {
            clear_cache();
        }
        try {
            String inputText = tvInput.getText().toString();
            tvInput.setText(inputText + num);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Dữ liệu nhập không hợp lệ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            clear_cache();
        }

        buttonPercent.setEnabled(true);
        buttonDot.setEnabled(true);
    }

    private void operation_click(String ch){
        String inputText = tvInput.getText().toString();

        if(!showing_results){
            if(inputText.isEmpty()){
                tvInput.setText("0" + ch);
            }else {
                char getLastCharacter = inputText.charAt(inputText.length()-1);
                if(getLastCharacter == '+' || getLastCharacter == '-' || getLastCharacter == '×' || getLastCharacter == '÷'){
                    tvInput.setText(inputText.substring(0, inputText.length()-1) + ch);
                } else {
                    tvInput.setText(inputText + ch);
                }
            }
        }else {
            String per = tvResult.getText().toString().substring(1);
            tvInput.setText(per + ch);
            showing_results = false;
        }

        // sau khi nhập các phép toán thì không đc nhập %
        buttonPercent.setEnabled(false);

        buttonDot.setEnabled(true);
    }
    private void calculateResult(String inputText){

        String inputText2 = inputText;

        if(inputText2.equals("0.0")){
            tvResult.setText("= 0");
            return;
        }

        // nếu chưa thực hiện phép tính
        if(calculation == -1 && !showing_results){
            tvResult.setText("= " + inputText2);
            return;
        }

        while (true){

            char[]chars = inputText2.toCharArray();
            if((inputText2.contains("+") || inputText2.contains("-")) && (inputText2.contains("×") || inputText2.contains("÷"))){
                for(int i = 0; i<inputText2.length(); i++){
                    if(Character.toString(chars[i]).equals("×")){
                        CURRENT_RULE = 1;
                        break;
                    } else if (Character.toString(chars[i]).equals("÷")) {
                        CURRENT_RULE = 0;
                        break;
                    }
                }
            }else if(!inputText2.contains("+") && !inputText2.contains("-") && !inputText2.contains("×") && !inputText2.contains("÷")){
                break;
            }else {
                for(int i = 0; i<inputText2.length(); i++) {
                    if (Character.toString(chars[i]).equals("×")) {
                        CURRENT_RULE = 1;
                        break;
                    }
                    if (Character.toString(chars[i]).equals("÷")) {
                        CURRENT_RULE = 0;
                        break;
                    }
                    if (Character.toString(chars[i]).equals("+")) {
                        CURRENT_RULE = 2;
                        break;
                    }
                    if (Character.toString(chars[i]).equals("-")) {
                        if (inputText2.indexOf('-') == 0) { // dấu âm đứng vị trí đầu

                            for (int j = 1; j < inputText2.length(); j++) { // ở phía sau không còn phép tính -> kết quả
                                if (!Character.toString(chars[j]).equals("+") && !Character.toString(chars[j]).equals("-")) {
                                    CURRENT_RULE = 3;
                                    break;
                                }
                            }

                            for (int k = 1; k < inputText2.length(); k++) { // ở phía sau còn phép tính
                                if (Character.toString(chars[k]).equals("+")) {
                                    CURRENT_RULE = 2;
                                    break;
                                }
                                if (Character.toString(chars[k]).equals("-")) {

                                    CURRENT_RULE = 3;
                                    break;
                                }
                            }

                        } else {
                            CURRENT_RULE = 3;
                        }
                        break;
                    }
                }
            }


            String[] inputArray = inputText2.split(BODMAS_RULE[CURRENT_RULE]);
            if(inputArray[0].isEmpty() && inputArray.length == 2){ // VD -7
                break;
            }

            if(CURRENT_RULE == 3 && inputArray[0].isEmpty()){ // VD -2-3-7...
                inputArray[0] = "-" + inputArray[1]; // lấy dấu âm cho số hạng đầu
                inputArray[1] = inputArray[2];
            }

            StringBuilder firstValue = new StringBuilder();
            StringBuilder secondValue = new StringBuilder();

            System.out.println(firstValue);
            System.out.println(secondValue);

            for(int i = inputArray[0].length() - 1; i>=0; i--){
                if(inputArray[0].charAt(i) == '+' || inputArray[0].charAt(i) == '-' || inputArray[0].charAt(i) == '×' || inputArray[0].charAt(i) == '÷'){
                    if(inputArray[0].charAt(i) == '-'){
                        firstValue.insert(0, inputArray[0].charAt(i));
                    }
                    break;
                } else {
                    firstValue.insert(0, inputArray[0].charAt(i));
                }
            }

            for(int i = 0; i<inputArray[1].length(); i++){
                if(inputArray[1].charAt(i) == '+' || inputArray[1].charAt(i) == '-' || inputArray[1].charAt(i) == '×' || inputArray[1].charAt(i) == '÷'){
                    break;
                } else {
                    secondValue.append(inputArray[1].charAt(i));
                }
            }
            Double results = 0.;

            switch (BODMAS_RULE[CURRENT_RULE]){
                case "+":
                case "\\+":
                    results = Double.parseDouble(firstValue.toString()) + Double.parseDouble(secondValue.toString());
                    break;
                case "-":
                    results = Double.parseDouble(firstValue.toString()) - Double.parseDouble(secondValue.toString());
                    break;
                case "×":
                    results = Double.parseDouble(firstValue.toString()) * Double.parseDouble(secondValue.toString());
                    break;
                case "÷":
                    if (Double.parseDouble(secondValue.toString()) != 0) {
                        results = Double.parseDouble(firstValue.toString()) / Double.parseDouble(secondValue.toString());
                    }else {
                        tvResult.setText("= Không thể chia");
                        return;
                    }
                    break;
                default:
                    results = 0.;
            }

            inputText2 = inputText2.replaceFirst(firstValue + BODMAS_RULE[CURRENT_RULE] + secondValue, String.valueOf(results));
            CURRENT_RULE = -1;
        }

        calculation = -1;

        try {
            if(inputText2.endsWith(".0")){
                inputText2 = inputText2.replace(".0","");
                tvResult.setText("= " + inputText2);
            }else {
                Double inputText3 = decimalFormat(Double.valueOf(inputText2), 8);
                tvResult.setText("= " + String.valueOf(inputText3));
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            clear_cache();
        }
    }
    private double decimalFormat(double decimal_num, int zero_num) {

        double scale = Math.pow(10, zero_num);

        return Math.round(decimal_num * scale) / scale;
    }
    private void clear_cache() {
        tvInput.setText("");
        tvResult.setText("");
        CURRENT_RULE = -1;
        calculation = -1;
        showing_results = false;
    }
}
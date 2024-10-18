package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Обработчик нажатий на цифровые кнопки
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();

                // Если оператор нажат, начинаем ввод второго числа
                if (isOperatorPressed) {
                    textView.setText(buttonText);
                    isOperatorPressed = false;
                } else {
                    // Добавляем нажатую цифру к текущему числу
                    String currentText = textView.getText().toString();
                    if (currentText.equals("0")) {
                        textView.setText(buttonText); // Убираем 0 перед числом
                    } else {
                        textView.append(buttonText);
                    }
                }
            }
        };

        // Назначение обработчика на цифровые кнопки
        findViewById(R.id.button0).setOnClickListener(numberClickListener);
        findViewById(R.id.button1).setOnClickListener(numberClickListener);
        findViewById(R.id.button2).setOnClickListener(numberClickListener);
        findViewById(R.id.button3).setOnClickListener(numberClickListener);
        findViewById(R.id.button4).setOnClickListener(numberClickListener);
        findViewById(R.id.button5).setOnClickListener(numberClickListener);
        findViewById(R.id.button6).setOnClickListener(numberClickListener);
        findViewById(R.id.button7).setOnClickListener(numberClickListener);
        findViewById(R.id.button8).setOnClickListener(numberClickListener);
        findViewById(R.id.button9).setOnClickListener(numberClickListener);
        findViewById(R.id.buttonDot).setOnClickListener(numberClickListener);

        // Обработчики для операторов
        findViewById(R.id.buttonPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator("+");
            }
        });

        findViewById(R.id.buttonMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator("-");
            }
        });

        findViewById(R.id.buttonMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator("*");
            }
        });

        findViewById(R.id.buttonDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator("/");
            }
        });

        // Обработчик для кнопки "="
        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!operator.isEmpty()) {
                    secondNumber = Double.parseDouble(textView.getText().toString());
                    double result = calculate(firstNumber, secondNumber, operator);
                    textView.setText(String.valueOf(result));
                    operator = "";
                }
            }
        });

        // Обработчик для кнопки "C" (очистка)
        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("0");
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
                isOperatorPressed = false;
            }
        });
    }

    // Метод обработки операторов
    private void processOperator(String op) {
        firstNumber = Double.parseDouble(textView.getText().toString());
        operator = op;
        isOperatorPressed = true;
    }

    // Метод выполнения вычислений
    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return 0; // Обработка деления на 0
                }
            default:
                return 0;
        }
    }
}

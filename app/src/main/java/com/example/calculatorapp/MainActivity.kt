package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.AdapterView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var keyBoardGRV: GridView
    lateinit var keyBoardList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keyBoardGRV = findViewById(R.id.keyboard)
        keyBoardList = ArrayList<GridViewModal>()

        keyBoardList = keyBoardList + GridViewModal("CE", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("C", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("BS", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("/", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("7", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("8", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("9", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("x", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("4", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("5", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("6", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("-", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("1", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("2", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("3", Category.OPERAND)
        keyBoardList = keyBoardList + GridViewModal("+", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("+/-", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("0", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal(".", Category.OPERATOR)
        keyBoardList = keyBoardList + GridViewModal("=", Category.OPERATOR)


        val keyboardAdapter = GridRVAdapter(keyboardList = keyBoardList, this@MainActivity)

        keyBoardGRV.adapter = keyboardAdapter



        val expTV:TextView = findViewById(R.id.expTV)

        keyBoardGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            var expString:String = expTV.text.toString()
            val input:GridViewModal = keyBoardList[position];


            when(input.keyName) {
                "BS" -> {
                    if (expString.length > 1) {
                        expTV.setText(expString.substring(0, expString.length - 1))
                    }else {
                        expTV.setText("0")
                    }
                }
                "C" -> expTV.setText("0")
                "="-> expTV.setText(calculator(expString).toString())
                else -> {
                    if(expString == "0") {
                        expString = keyBoardList[position].keyName;
                    }else {
                        expString += keyBoardList[position].keyName;
                    }
                    expTV.setText(expString)
                }
            }

        }

    }


    fun calculator(expresstion: String) : Int {
        var result:Int = 0;
        var num1:String = ""
        var num2:String = ""
        var oper: Char? = null
        for (c in expresstion) {
            if(c == '+' || c == '-' || c == 'x' ||c == '/') {
                if(oper != null) {
                    println("operator " + c);
                    when(oper) {
                        '+' -> {
                            println(num1 + "//" + num2);
                            result = (num1.toInt() + num2.toInt())
                        }
                        '-' -> result = (num1.toInt() - num2.toInt())
                        'x' -> result = (num1.toInt() * num2.toInt())
                        '/' -> result = (num1.toInt() / num2.toInt())
                    }
                    num1 = result.toString();
                    num2 = "";
                }
                oper = c;
            }else {
                println("add number " + c);
                if(oper == null) {
                    num1 += c
                }else {
                    num2 +=c
                }
            }
        }
        when(oper) {
            '+' -> {
                println(num1 + "//" + num2);
                result = (num1.toInt() + num2.toInt())
            }
            '-' -> result = (num1.toInt() - num2.toInt())
            'x' -> result = (num1.toInt() * num2.toInt())
            '/' -> result = (num1.toInt() / num2.toInt())
        }
        println(result);
        return result
    }

}
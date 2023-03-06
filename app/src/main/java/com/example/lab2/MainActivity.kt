package com.example.lab2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab2.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding

    private fun sqr(x: Double): Double = x * x

    private fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c

    private fun quadraticEquation(a: Double, b: Double, c: Double): String {
        val discriminant = discriminant(a, b, c)
        if (discriminant < 0){
            return "Корней нет!"
        }
        else if (discriminant == 0.0 && a != 0.0 && b != 0.0){
            val x1 = -b / 2 * a
            return "Т.к. D = 0\nX1 = X2 = $x1"
        }
        else{
            if (a == 0.0 && b != 0.0){
                val x1 = -c / b
                return "Корень один, т.к. уравнение линейное\nX1 = $x1"
            }
            else if(a == 0.0 && b == 0.0 && c == 0.0){
                return "Уравнение не квадратное, корни могут принимать любое значение"
            }
            else if(a == 0.0 && b == 0.0 && c != 0.0){
                return "Выражение не является уравнением"
            }
            else {
                val x1 = (-b + sqrt(discriminant.toDouble())) / (2 * a)
                val x2 = (-b - sqrt(discriminant.toDouble())) / (2 * a)
                return "X1 = $x1 \nX2 = $x2"
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.button.setOnClickListener() {
            try {
                val a = bindingClass.number1.text.toString().toDouble()
                val b = bindingClass.number2.text.toString().toDouble()
                val c = bindingClass.number3.text.toString().toDouble()
                val decision = quadraticEquation(a, b, c)
                //          bindingClass.textView.text = decision.contentToString()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Решение")
                builder.setMessage(decision)
                builder.setPositiveButton("Ок") { dialogInterface, which ->
                    Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
                }
                builder.show()
            }
            catch (e: Exception){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Ошибка")
                builder.setMessage("Проверьте правильность ввода")
                builder.setPositiveButton("Ок") { dialogInterface, which ->
                    Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show()
                }
                builder.show()
            }
        }
    }
}
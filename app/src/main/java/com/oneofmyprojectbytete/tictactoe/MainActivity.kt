package com.oneofmyprojectbytete.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.oneofmyprojectbytete.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var activePlayerTurn: Boolean = true

    val winningPosition: Array<IntArray> =
        arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),//row
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),//columns
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)//crowss

        )

    val gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.d("Activity", (binding.btn0).text.toString())
        (binding.btn0).setOnClickListener {
            val gameStatePointer = 0
            buttonSetIcon(it.findViewById(R.id.btn_0), gameStatePointer)
            Log.d("Activity", (binding.btn0).text.toString())
        }
        (binding.btn1).setOnClickListener {
            val gameStatePointer = 1
            buttonSetIcon(binding.btn1, gameStatePointer)
            Log.d("Activity", binding.btn1.text.toString())
        }
        binding.btn2.setOnClickListener {
            Log.d("Activity", "button2 is clicked")
            val gameStatePointer = 2
            buttonSetIcon(binding.btn2, gameStatePointer)
        }
        binding.btn3.setOnClickListener {
            val gameStatePointer = 3
            buttonSetIcon(binding.btn3, gameStatePointer)
        }
        binding.btn4.setOnClickListener {
            val gameStatePointer = 4
            buttonSetIcon(binding.btn4, gameStatePointer)
        }
        binding.btn5.setOnClickListener {
            val gameStatePointer = 5
            buttonSetIcon(binding.btn5, gameStatePointer)
        }
        binding.btn6.setOnClickListener {
            val gameStatePointer = 6
            buttonSetIcon(binding.btn6, gameStatePointer)
        }
        binding.btn7.setOnClickListener {
            val gameStatePointer = 7
            buttonSetIcon(binding.btn7, gameStatePointer)
        }
        binding.btn8.setOnClickListener {
            val gameStatePointer = 8
            buttonSetIcon(binding.btn8, gameStatePointer)
        }
    }


    private fun buttonSetIcon(button: Button, gameStatePointer: Int) {

        if (!(button.text.equals(""))) {
            return
        } else {
            if (activePlayerTurn) {
                button.text = "X"
                gameState[gameStatePointer] = 0
            } else {
                button.text = "O"
                gameState[gameStatePointer] = 1
            }
        }


        if (checkWinner()) {

            if (activePlayerTurn) {
                resultDialog("Winner")
            } else
                resultDialog("Lose")

        } else
            activePlayerTurn = !activePlayerTurn

        if (isFinish()) {
            resultDialog("Draw")
        }

    }

    fun checkWinner(): Boolean {
        var winnerResult = false
        for (i in winningPosition) {

            Log.d("Activity12", i[0].toString())

            if (gameState[(i[0])] == gameState[(i[1])]
                && gameState[(i[1])] == gameState[(i[2])]
                && gameState[(i[2])] != 2
            ) {
                winnerResult = true
            }
        }
        return winnerResult
    }

    fun playAgain() {
        activePlayerTurn = true
        for (i in 0..8) {
            gameState[i] = 2
        }
        binding.btn0.text = ""
        binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""

    }

    fun resultDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Result")
            .setMessage(message)
            .setCancelable(false)

            .setPositiveButton("Try Again") { _, _ ->
                playAgain()
            }
            .show()
    }

    fun isFinish(): Boolean {
        return !gameState.contains(2)

    }
}

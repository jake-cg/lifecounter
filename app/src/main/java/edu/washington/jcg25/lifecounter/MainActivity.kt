package edu.washington.jcg25.lifecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val parentLayout = findViewById<LinearLayout>(R.id.parentLayout)

        var playerCount = 4

        val newPlayer: Button = Button(this)
        newPlayer.text = "Add Player"
        newPlayer.setOnClickListener { _ ->
            if(playerCount < 8){
                playerCount++
                var player: Player = createPlayer("Player$playerCount", parentLayout)
                parentLayout.addView(player.layout)
            } else {
                val toast = Toast.makeText(applicationContext, "Too many players", Toast.LENGTH_SHORT)
                toast.show();
            }
        }
        parentLayout.addView(newPlayer)

        for(i in 1..4) {
            var player: Player = createPlayer("Player$i", parentLayout)
            parentLayout.addView(player.layout)
        }
    }

    private fun createPlayer(name: String, parent: LinearLayout): Player {
        val layout: LinearLayout = LinearLayout(this)

        val playerLabel: TextView = TextView(this);
        val button1: Button = Button(this)
        val button2: Button = Button(this)
        val button3: Button = Button(this)
        val button4: Button = Button(this)

        val loss : TextView = TextView(this)

        return Player(name, 5, layout, parent, loss, playerLabel, button1, button2, button3, button4)
    }

    fun lose(name: String) {

    }
}

class Player (playerName: String, playerHealth: Int, layout: LinearLayout, parent: LinearLayout, lossMessage: TextView, playerLabel: TextView, button1: Button, button2: Button, button3: Button, button4: Button) {

    private val name = playerName
    private var health = playerHealth
    private var label = playerLabel
    public var layout = layout
    private var parent = parent
    private var loss = lossMessage
    private var btn1 = button1
    private var btn2 = button2
    private var btn3 = button3
    private var btn4 = button4


    init {
        label.textSize = 20f
        label.text = "$name: $health"

        createButton(button1, 1)
        createButton(button2, -1)
        createButton(button3, 5)
        createButton(button4, -5)

        layout.addView(playerLabel)
        layout.addView(button1)
        layout.addView(button2)
        layout.addView(button3)
        layout.addView(button4)
    }

    private fun createButton(button: Button, change: Int) {
        if (change == 1) {
            button.text = "+"
            button.setOnClickListener { _ ->
                health++
                label.text = "$name: $health"
            }
        } else if (change == -1) {
            button.text = "-"
            button.setOnClickListener { _ ->
                health--
                label.text = "$name: $health"
                if(health <= 0){
                    loss.text = "$name LOSES!"
                    btn1.isEnabled = false;
                    btn2.isEnabled = false;
                    btn3.isEnabled = false;
                    btn4.isEnabled = false;
                    parent.addView(loss)
                }
            }
        } else if (change == 5) {
            button.text = "+5"
            button.setOnClickListener { _ ->
                health += 5
                label.text = "$name: $health"
            }
        } else if (change == -5) {
            button.text = "-5"
            button.setOnClickListener { _ ->
                health -= 5
                label.text = "$name: $health"
                if(health <= 0){
                    loss.text = "$name LOSES!"
                    btn1.isEnabled = false;
                    btn2.isEnabled = false;
                    btn3.isEnabled = false;
                    btn4.isEnabled = false;
                    parent.addView(loss)
                }
            }
        }
    }
}


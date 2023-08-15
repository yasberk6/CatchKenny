package com.berko.kennyyakalma

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.berko.kennyyakalma.databinding.ActivityMainBinding
import java.util.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())
    var p0=0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        //ımagearray
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        hideresim()

        //countdowntimer
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                binding.timetext.text ="Time : ${p0/1000}"
            }


            override fun onFinish() {
                binding.timetext.text="Time : 0"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                //alert dialog
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("GAME OVER")
                alert.setMessage("RESTART THE GAME ?")
                alert.setPositiveButton("YES",DialogInterface.OnClickListener({dialogInterface, i ->
                    //restart
                    val intentFromMain=intent
                    finish()
                    startActivity(intentFromMain)
                }))

                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                })
                alert.show()

            }

        }.start()



    }
    fun hideresim(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random = Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }


        }
        handler.post(runnable)

    }
    fun increaseScore(view : View){
        score = score + 1
        binding.scoreText.text = "Score : ${score}"
    }
}
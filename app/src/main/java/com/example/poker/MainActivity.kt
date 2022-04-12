package com.example.poker

import android.gesture.Gesture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.poker.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity() , View.OnTouchListener, GestureDetector.OnGestureListener{

    private lateinit var gDetector: GestureDetector
    private lateinit var binding: ActivityMainBinding

    var color:String ="" //花色
    var number :Int = 1 //點數

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.img.setOnTouchListener(this)
        binding.text.text = "撲克牌魔術"
        gDetector = GestureDetector(this, this)
    }

    override fun onTouch(v:View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
//            binding.text.text = "手指按下"
            color = ""
        }
        else if (event?.action == MotionEvent.ACTION_UP){
//            binding.text.text = "手指彈開"
            if(color==""){
                binding.img.setImageResource(R.drawable.joker)
            }
            else{
                var res:Int = getResources().getIdentifier(color + number.toString(),
                    "drawable", getPackageName())
                binding.img.setImageResource(res)
            }

        }
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //        binding.text.text = "拖曳"
        if(p0!!.x>=p1!!.x){
            if(p0!!.y >= p1!!.y){ //左上
                color = "c"
            }
            else {  //左下
                color = "d"
            }
        }
        else {
            if(p0!!.y >= p1!!.y){   //右上
                color = "h"
            }
            else {
                color = "s"
            }
        }

        //處理點數
        number =abs(p0!!.y.toInt() - p1!!.y.toInt())/(binding.img.height/26)+1
        if(number>13){
            number=13
        }

        return true
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

}
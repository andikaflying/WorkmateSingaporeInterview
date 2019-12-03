package com.example.workmatetest.views

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.workmatetest.R
import com.example.workmatetest.utilities.InjectorUtils
import com.example.workmatetest.viewmodels.ClockViewModel
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.getName()
    private lateinit var clockViewModel: ClockViewModel
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var button2: Button
    private lateinit var txvClockIn: TextView
    private lateinit var txvClockOut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = InjectorUtils.provideClockViewModelFactory(this)
        clockViewModel = ViewModelProviders.of(this, factory).get(ClockViewModel::class.java)
//        clockViewModel.auth().observe(this, Observer {
//            if (it != null) {
//                clockViewModel.clockIn().observe(this, Observer {
//                    if (it != null) {
//                        Log.e(TAG, "ID Response : " + it.id)
//                    }
//                })
//            }
//        })
//        clockViewModel.clockIn().observe(this, Observer {
//            if (it != null) {
//                Log.e(TAG, "ID Response : " + it.id)
//            }
//        })
//        showDialog(this, "Test dialog")
        showDialog()
        button2 = findViewById(R.id.button2)
        txvClockIn = findViewById(R.id.txvClockIn)
        txvClockOut = findViewById(R.id.txvClockOut)

        button2.setOnClickListener {
            showDialog()
        }
    }

    var status = 0
    var handler: Handler = Handler()
    var dialog: Dialog? = null
    var text: ProgressBar? = null
    var text2: TextView? = null
    var button: Button? = null

    var isAlreadyClockIn = false;

    fun showDialog() {
        dialog = Dialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.progress_bar)
        text = dialog!!.findViewById<View>(R.id.progress_horizontal) as ProgressBar
        text2 = dialog!!.findViewById(R.id.value123)
        button = dialog!!.findViewById(R.id.button)

        dialog!!.show()
        val window: Window? = dialog!!.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        var status: Int;
        var limitTime: Long = 10000

        countDownTimer = object: CountDownTimer(limitTime, 1000) {
            override fun onFinish() {
                if (isAlreadyClockIn == false) {
                    clockViewModel.clockIn().observe(this@MainActivity, Observer {
                        if (it != null) {
                            Log.e(TAG, "ID Response : " + it.id)
                            setTime(txvClockIn)
                            isAlreadyClockIn = true
                            dialog!!.dismiss()

                        }
                    })
                } else {
                    clockViewModel.clockOut().observe(this@MainActivity, Observer {
                        if (it != null) {
                            Log.e(TAG, "ID Response : " + it.id)
                            setTime(txvClockOut)
                            isAlreadyClockIn = false
                        }
                    })
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                status = ((limitTime - millisUntilFinished) / 1000).toInt()
                text!!.progress = status * 10
                text2!!.text = status.toString()
            }
        }
        countDownTimer.start()

        button!!.setOnClickListener {
            countDownTimer.cancel();
            dialog!!.dismiss();
        }
    }

    fun showDialog(activity: Activity?, msg: String?) {
        dialog = Dialog(activity!!)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.progress_bar)
        text = dialog!!.findViewById<View>(R.id.progress_horizontal) as ProgressBar
        text2 = dialog!!.findViewById(R.id.value123)
        dialog!!.show()
        val window: Window? = dialog!!.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        Thread(Runnable {
            while (status < 100) {
                status += 1
                try {
                    Thread.sleep(200)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                handler.post {
                    text!!.progress = status
                    text2!!.setText(status.toString())
                    if (status == 100) {
                        status = 0
                    }
                }
            }
        }).start()
    }

    fun setTime(textView: TextView) {
        val formatter = SimpleDateFormat("HH:mm")
        val date = Date(System.currentTimeMillis())

        textView.setText(formatter.format(date))
    }
}

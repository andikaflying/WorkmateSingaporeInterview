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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.workmatetest.R
import com.example.workmatetest.databinding.MainBinding
import com.example.workmatetest.utilities.InjectorUtils
import com.example.workmatetest.viewmodels.ClockViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.getName()
    private lateinit var clockViewModel: ClockViewModel
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var binding: MainBinding

    var status = 0
    var handler: Handler = Handler()
    var dialog: Dialog? = null
    var progressBar: ProgressBar? = null
    var btnCancel: Button? = null

    var isAlreadyClockIn = false;
    var isAlreadyWork = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main)

        val factory = InjectorUtils.provideClockViewModelFactory(this)
        clockViewModel = ViewModelProviders.of(this, factory).get(ClockViewModel::class.java)

        clockViewModel.getStaffDetail().observe(this@MainActivity, Observer {
            if (it != null) {
                binding.txvJob.text = it.position.name;
                binding.txvCompany.text = it.client.name;
                binding.txvPrice.text = "Rp. " + it.wage_amount;
                binding.txvPerDay.text = it.wage_type;
                binding.txvAddress.text = it.location.address.street_1;
                binding.txvManagerName.text = it.manager.name;
                binding.txvContact.text = it.manager.phone;
            }
        })

        binding.btnClock.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog() {
        dialog = Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.loading)
        progressBar = dialog!!.findViewById<View>(R.id.progress_horizontal) as ProgressBar
        btnCancel = dialog!!.findViewById(R.id.btnCancel)

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
                            setTime(binding.txvClockIn)
                            isAlreadyClockIn = true
                            dialog!!.dismiss()
                            binding.btnClock.setText("Clock Out");

                        }
                    })
                } else {
                    clockViewModel.clockOut().observe(this@MainActivity, Observer {
                        if (it != null) {
                            setTime(binding.txvClockOut)
                            isAlreadyClockIn = false
                            dialog!!.dismiss()
                            binding.btnClock.visibility = View.GONE;
                        }
                    })
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                status = ((limitTime - millisUntilFinished) / 1000).toInt()
                progressBar!!.progress = status * 10
            }
        }
        countDownTimer.start()

        btnCancel!!.setOnClickListener {
            countDownTimer.cancel();
            dialog!!.dismiss();
        }
    }

    fun setTime(textView: TextView) {
        val formatter = SimpleDateFormat("HH:mm")
        val date = Date(System.currentTimeMillis())

        textView.setText(formatter.format(date))
    }
}

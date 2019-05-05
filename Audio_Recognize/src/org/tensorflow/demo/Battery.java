package org.tensorflow.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;





public class Battery extends Activity {
    public TextView battery_percent_textview;
    public ProgressBar battery_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        battery_percent_textview=(TextView) findViewById(R.id.battery_percent);
        battery_bar=(ProgressBar) findViewById(R.id.battery_bar);

        mHandler.postDelayed(task,1000);
    }

    public void Update_UI(){
        battery_percent_textview.setText(SpeechActivity.battery_percent+"%");
        battery_bar.setProgress(SpeechActivity.battery_percent);
    }

    public Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                default:
                    Update_UI();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public Runnable task = new Runnable() {
        @Override
        public void run() {
            Update_UI();
            mHandler.sendEmptyMessage(1);
            mHandler.postDelayed(this,5000);
        }
    };
}

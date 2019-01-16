package aniviakid.arduinoblink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.sintef.jarduino.JArduino;

public class MainActivity extends AppCompatActivity {

    JArduino arduino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arduino=new Blink(getApplicationContext());
        arduino.runArduinoProcess();
    }
}

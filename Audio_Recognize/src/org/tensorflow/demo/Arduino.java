package org.tensorflow.demo;

import android.content.Context;
import android.content.DialogInterface;

import org.sintef.jarduino.AnalogPin;
import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.PWMPin;
import org.sintef.jarduino.PinMode;
import org.sintef.jarduino.UsbArduino;




public class Arduino extends UsbArduino {
    PinMode OUTPUT=PinMode.OUTPUT;

    DigitalPin DIR1_RIGHT=DigitalPin.PIN_12;//2路直流电机方向控制引脚
    DigitalPin DIR2_RIGHT=DigitalPin.PIN_11;
    DigitalPin DIR1_LEFT=DigitalPin.PIN_8;
    DigitalPin DIR2_LEFT=DigitalPin.PIN_9;

    PWMPin PWM_LEFT=PWMPin.PWM_PIN_6;//2路电机调速引脚
    PWMPin PWM_RIGHT=PWMPin.PWM_PIN_5;
    AnalogPin MotorAdjustmengPin=AnalogPin.A_1;


    public int mode;//静止为0，前进为1，后退为2
    public int direction;//直线为0，左拐为1，右拐为2
    public int wheelSpeed = 95;
    public int week_wheelSpeed=24;

    public Arduino(Context appContext) {
        super(appContext);
    }

    @Override
    protected void setup() {
        pinMode(DIR1_RIGHT, PinMode.OUTPUT);
        pinMode(DIR2_RIGHT, OUTPUT);
        pinMode(DIR1_LEFT, OUTPUT);
        pinMode(DIR2_LEFT, OUTPUT);

        pinMode(DigitalPin.PIN_6, OUTPUT);
        pinMode(DigitalPin.PIN_5, OUTPUT);
    }

    @Override
    protected void loop() {
        if(mode==0){
            stopMotor();
        }
        else if (mode==1){
            if(direction==0) motorsWrite(wheelSpeed,wheelSpeed);
            else if (direction==1) motorsWrite(week_wheelSpeed,wheelSpeed);
            else if (direction==2) motorsWrite(wheelSpeed,week_wheelSpeed);
        }
        else if (mode==2){
            if(direction==0) motorsWrite(-1*wheelSpeed,-1*wheelSpeed);
            else if (direction==1) motorsWrite(-1*week_wheelSpeed,-1*wheelSpeed);
            else if (direction==2) motorsWrite(-1*wheelSpeed,-1*week_wheelSpeed);
        }
        delay(100);
    }
    //电机控制子程序
    void motorsWrite(int speedLeft,int speedRight)
    {
        float motorAdjustment = MotorAdjustment();
        if(motorAdjustment<0){
            speedRight*=(1+motorAdjustment);
        }
        else{
            speedLeft*=(1-motorAdjustment);
        }
        if(speedRight>0) //如果PWM数值小于0，表示运行方向变化
        {
            digitalWrite(DIR1_RIGHT,DigitalState.LOW);
            digitalWrite(DIR2_RIGHT, DigitalState.HIGH);
        }
        else
        {
            digitalWrite(DIR1_RIGHT, DigitalState.HIGH);
            digitalWrite(DIR2_RIGHT, DigitalState.LOW);
        }
        analogWrite(PWM_RIGHT, (byte)(Math.abs(speedRight) & 0xff));

        if(speedLeft > 0)
        {
            digitalWrite(DIR1_LEFT,DigitalState.LOW);
            digitalWrite(DIR2_LEFT,DigitalState.HIGH);
        }
        else
        {
            digitalWrite(DIR1_LEFT,DigitalState.HIGH);
            digitalWrite(DIR2_LEFT,DigitalState.LOW);
        }
        analogWrite(PWM_LEFT, (byte)(Math.abs(speedLeft) & 0xff));
    }
    //停止电机
    void stopMotor()
    {
        motorsWrite(0,0);
    }
    //轮子校正程序，当2个电机在同等条件下转速不同时需要校正，使小车可以走直线，如果没有预定义校正功能，返回0，表示没有校正
    float  MotorAdjustment(){
        //#ifdef MOTORADJUSTMENT
                double tmp=map(analogRead(MotorAdjustmengPin),0,1023,-30,30)/100.0;
                float motorAdjustment=(float)tmp;
                return motorAdjustment;
        //#else
                //return 0;
       // #endif
    }
}

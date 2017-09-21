package vadyaprod.gametask.model;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Timer;

/**
 * Created by Vadya on 20.09.17.
 */

public class GameProcess {
    CountDownTimer timer;
    long countDownPeriod;

    public void setTextTimer(TextView textTimer) {
        this.textTimer = textTimer;
    }

    private TextView textTimer;

    public int getPrizePoint() {
        return mPrizePoint;
    }

    private int mPrizePoint;

    public int getPenaltyPoint() {
        return mPenaltyPoint;
    }

    private int mPenaltyPoint;

    public GameProcess(TextView textView){
        countDownPeriod = 30000;
        createCountDownTimer();
        mPrizePoint = 0;
        mPenaltyPoint = 0;
        textTimer = textView;
    }



    public void reduceCountDownTimer(){
        if (timer != null)
        timer.cancel();
        timer = new CountDownTimer(countDownPeriod - 5000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textTimer.setText("Timer " + millisUntilFinished / 1000);
                countDownPeriod=millisUntilFinished;
                mPenaltyPoint++;

            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }


    public void createCountDownTimer() {
        if (timer != null)
        timer.cancel();
        timer = new CountDownTimer(countDownPeriod + 5000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                textTimer.setText("Timer " + millisUntilFinished / 1000);
                countDownPeriod=millisUntilFinished;
                mPrizePoint++;
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }
}

package vadyaprod.gametask.model;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

import vadyaprod.gametask.container.GameResultContainer;
import vadyaprod.gametask.ui.GameFragment;

/**
 * Created by Vadya on 20.09.17.
 */

public class GameProcess {
    CountDownTimer timer;
    long countDownPeriod;
    private GameFragment mGameFragment;

    public void setTextTimer(TextView textTimer) {
        this.textTimer = textTimer;
    }

    private TextView textTimer;
    private TextView textPoints;
    private boolean isShowTime;

    public boolean isActive() {
        return misActive;
    }

    private boolean misActive;

    public int getPrizePoint() {
        return mPrizePoint;
    }

    private int mPrizePoint;

    public int getPenaltyPoint() {
        return mPenaltyPoint;
    }

    private int mPenaltyPoint;

    public void stop(){
        timer.onFinish();
        timer.cancel();
        timer = null;
    }

    public GameProcess(TextView textView, TextView textPoints, GameFragment fragment, boolean isShow){
        countDownPeriod = 30000;
        createCountDownTimer();
        mPrizePoint = 0;
        mPenaltyPoint = 0;
        textTimer = textView;
        this.textPoints = textPoints;
        mGameFragment = fragment;
        isShowTime = isShow;
        misActive = true;
    }



    public void reduceCountDownTimer(){
        if (timer != null)
        timer.cancel();
        mPenaltyPoint++;
        timer = new CountDownTimer(countDownPeriod - 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (isShowTime)
                    textTimer.setText("\rServer time is: 00-00-00" + "\n\rTimer " + millisUntilFinished / 1000);
                else
                textTimer.setText("Timer " + millisUntilFinished / 1000);
                textPoints.setText("Points: " + mPrizePoint + " Penalty: " + mPenaltyPoint);
                countDownPeriod=millisUntilFinished;

            }

            @Override
            public void onFinish() {
                Date date = new Date();
                GameResult gameResult = new GameResult(mPrizePoint, mPenaltyPoint,
                        date, date );
                GameResultContainer.addResult(gameResult);
                mGameFragment.onSwitchToResluts();
                misActive = false;
            }
        };
        timer.start();
    }


    public void createCountDownTimer() {
        if (timer != null)
        timer.cancel();
        mPrizePoint++;
        timer = new CountDownTimer(countDownPeriod + 1000, 1) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (isShowTime)
                    textTimer.setText("\rServer time is: 00-00-00" + "\n\rTimer " + millisUntilFinished / 1000);
                else
                textTimer.setText("Timer " + millisUntilFinished / 1000);
                textPoints.setText("Points: " + mPrizePoint + " Penalty: " + mPenaltyPoint);
                countDownPeriod=millisUntilFinished;
            }

            @Override
            public void onFinish() {
                Date date = new Date();
                GameResult gameResult = new GameResult(mPrizePoint, mPenaltyPoint,
                        date, date );
                GameResultContainer.addResult(gameResult);
                mGameFragment.onSwitchToResluts();
                misActive = false;
            }
        };
        timer.start();
    }
}

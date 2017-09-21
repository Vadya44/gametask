package vadyaprod.gametask.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;

/**
 * Created by Vadya on 20.09.17.
 */

public class GameResult {
    public int getPrizePoints() {
        return mPrizePoints;
    }

    private int mPrizePoints;

    public int getPenaltyPoints() {
        return mPenaltyPoints;
    }

    private int mPenaltyPoints;

    public String getAppTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(mAppTime);
    }

    private Date mAppTime;

    public String getServerTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(mServerTime);
    }

    private Date mServerTime;

    public GameResult(int prizePoints, int penaltyPoints, Date appTime, Date serverTime)
    {
        this.mPrizePoints = prizePoints;
        this.mPenaltyPoints = penaltyPoints;
        this.mAppTime = appTime;
        this.mServerTime = serverTime;
    }
}

package vadyaprod.gametask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vadya on 02.10.17.
 */

public class Data {
    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        mTimeStamp = timeStamp;
    }

    @SerializedName("timestamp")
    private String mTimeStamp;
    public Data(String timestamp){
        this.mTimeStamp = timestamp;
    }
}

package vadyaprod.gametask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vadya on 02.10.17.
 */

public class ServerInfo {
    public Data getDate() {
        return mDate;
    }

    public void setDate(Data date) {
        mDate = date;
    }

    @SerializedName("data")
    private Data mDate;


    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    @SerializedName("code")
    private String mCode;
}

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
        /*this.m0dur = dur;
        this.mCoin = coin;
        this.mLoan = loan;
        this.mTimeStamp = timeZone;
        this.mTimeShift = timeShift;
        this.mVersionApi = versionApi;
        */
    }

    public String getM0dur() {
        return m0dur;
    }

    public void setM0dur(String m0dur) {
        this.m0dur = m0dur;
    }

    @SerializedName("0dur")
    private String m0dur;

    public String getCoin() {
        return mCoin;
    }

    public void setCoin(String coin) {
        mCoin = coin;
    }

    @SerializedName("coin")
    private String mCoin;

    public String getLoan() {
        return mLoan;
    }

    public void setLoan(String loan) {
        mLoan = loan;
    }

    @SerializedName("loan")
    private String mLoan;

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    @SerializedName("time_zone_label")
    private String mTimeZone;

    public String getTimeShift() {
        return mTimeShift;
    }

    public void setTimeShift(String timeShift) {
        mTimeShift = timeShift;
    }

    @SerializedName("time_zone_shift")
    private String mTimeShift;

    public String getVersionApi() {
        return mVersionApi;
    }

    public void setVersionApi(String versionApi) {
        mVersionApi = versionApi;
    }

    @SerializedName("version_api")
    private String mVersionApi;
}

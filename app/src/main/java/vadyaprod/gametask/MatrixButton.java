package vadyaprod.gametask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Vadya on 19.09.17.
 */

@SuppressLint("AppCompatCustomView")
public class MatrixButton extends Button {
    private boolean isActive = true;

    public boolean isClickedYet() {
        return mclickedYet;
    }

    public void setClickedYet(boolean clickedYet) {
        this.mclickedYet = clickedYet;
    }

    private boolean mclickedYet = false;

    public String getCounter() {
        return String.valueOf(mCounter);
    }

    public boolean isActive(){
        return isActive;
    }

    public void setCounter(int counter) {
        isActive = false;
        mCounter = counter;
    }


    private int mCounter;
    public MatrixButton(Context context) {
        super(context);
        mCounter = 0;
    }

    public void upCounter(){
        ++this.mCounter;
        this.mclickedYet = true;
    }



}

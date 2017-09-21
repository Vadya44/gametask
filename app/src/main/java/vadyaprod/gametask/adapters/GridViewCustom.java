package vadyaprod.gametask.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import vadyaprod.gametask.MatrixButton;
import vadyaprod.gametask.R;
import vadyaprod.gametask.model.GameProcess;

/**
 * Created by Vadya on 19.09.17.
 */

public class GridViewCustom extends BaseAdapter {
    ArrayList<MatrixButton> mMatrixButtons;
    private Context mContext;
    private TextView mTextView;
    private boolean isFirstClick;
    private GameProcess mGameProcess;



    public GridViewCustom(Context context, ArrayList<MatrixButton> matrixButtonArrayList,
                          TextView textView) {
        this.mContext = context;
        this.mMatrixButtons = matrixButtonArrayList;
        this.mTextView = textView;
        this.isFirstClick = true;
    }

    @Override
    public final int getCount() {

        return mMatrixButtons.size();

    }

    @Override
    public final Button getItem(int position) {
        return mMatrixButtons.get(position);
    }

    @Override
    public final long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MatrixButton button;
        if (convertView == null) {
            button = new MatrixButton(mContext);
            button.setText(mMatrixButtons.get(position).getCounter());
            button.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setBackgroundColor(Color.WHITE);

        } else {
            button = (MatrixButton) convertView;
        }
        button.setId(position);
        button.setHeight(button.getWidth());
        button.setText(mMatrixButtons.get(position).getCounter());
        if (!mMatrixButtons.get(position).isActive())
        {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            button.setBackgroundColor(Color.GRAY);
        }
        else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMatrixButtons.get(position).isActive()) {
                        if (isFirstClick)
                        {
                            mGameProcess = new GameProcess(mTextView);
                            isFirstClick = false;
                        }


                        if (mMatrixButtons.get(position).isClickedYet())
                            mGameProcess.reduceCountDownTimer();
                        else mGameProcess.createCountDownTimer();



                        mMatrixButtons.get(position).upCounter();
                        button.setText(mMatrixButtons.get(position).getCounter());
                    }
                }
            });
        }

        return button;
    }
}

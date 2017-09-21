package vadyaprod.gametask.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vadyaprod.gametask.MatrixButton;
import vadyaprod.gametask.R;
import vadyaprod.gametask.model.GameField;
import vadyaprod.gametask.model.GameProcess;
import vadyaprod.gametask.model.GameResult;

/**
 * Created by Vadya on 19.09.17.
 */

public class GameFragment extends Fragment {
    private final String INPUTED_X = "com.vadyaprod.GameTask.inputed_x";
    private final String INPUTED_Y = "com.vadyaprod.GameTask.inputed_y";
    private final String SHOW_TIME = "com.vadyaprod.GameTask.show_time";
    private final String SHOW_COORDS = "com.vadyaprod.GameTask.show_coords";

    private static GameFragment sGameFragment;

    private List<Button> mButtons;
    private GameField mGameField;
    private int mInputedX;
    private int mInputedY;
    private boolean mIsShowTime;
    private boolean mIsShowCoords;

    private boolean isFirstClick = true;
    private GameProcess mGameProcess;

    ArrayList<MatrixButton> data = new ArrayList<>();
    private GridView list;

    public void onSwitchToResluts(){
        ResultFragment resultFragment = new ResultFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null).commit();
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sGameFragment = this;
        Bundle extras = this.getArguments();
        if (extras != null) {
            mInputedY = extras.getInt(INPUTED_X);
            mInputedX = extras.getInt(INPUTED_Y);
            mIsShowCoords = extras.getBoolean(SHOW_COORDS);
            mIsShowTime = extras.getBoolean(SHOW_TIME);
        }
        if (mIsShowCoords){
            mInputedX++;
            mInputedY++;
        }
        final View v = inflater.inflate(R.layout.fragment_game, container, false);


        LinearLayout layoutVertical = (LinearLayout)v.findViewById(R.id.table_grid);
        //create a new TableLayout
        TableLayout table = null;


        final MatrixButton[][] buttonArray = new MatrixButton[mInputedX][mInputedY];
        table = new TableLayout(getContext());
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);
        for (int row = 0; row < mInputedX; row++) {
            TableRow currentRow = new TableRow(getContext());
            for (int button = 0; button < mInputedY; button++) {
                final MatrixButton currentButton = new MatrixButton(getContext());
                currentButton.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                currentButton.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                final int i = row;
                final int j = button;

                currentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            if (isFirstClick)
                            {
                                mGameProcess = new GameProcess((TextView)v.findViewById(R.id.timer_text_view),
                                        (TextView)v.findViewById(R.id.points) ,sGameFragment, mIsShowTime);
                                isFirstClick = false;
                            }


                            if (buttonArray[i][j].isClickedYet())
                                mGameProcess.reduceCountDownTimer();
                            else mGameProcess.createCountDownTimer();



                        buttonArray[i][j].upCounter();
                        currentButton.setText(buttonArray[i][j].getCounter());
                        }
                });

                if (mIsShowCoords && row == mInputedX - 1) {
                    currentButton.setText(String.valueOf(button));
                    currentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
                if (mIsShowCoords && button == 0){
                    currentButton.setText(String.valueOf((mInputedX - row - 1) * 10));
                    currentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
                // you could initialize them here
                // you can store them
                buttonArray[row][button] = currentButton;
                // and you have to add them to the TableRow
                currentRow.addView(currentButton);
            }
            // a new row has been constructed -> add to table
            table.addView(currentRow);
        }
        layoutVertical.addView(table);


        if (mIsShowTime)
        {
            TextView tv = (TextView)v.findViewById(R.id.timer_text_view);
            tv.setText("\rServer time is: 00-00-00" + "\n\r");
        }


        /*list = (GridView) v.findViewById(R.id.grid_view);
        list.setNumColumns(mInputedX);


        for (int i = 0; i < mInputedX; i++) {
            for(int j=0; j < mInputedY; j++)
            {
                MatrixButton matrixButton = new MatrixButton(getContext());
                if (mIsShowCoords) {
                    if (mIsShowCoords && j == 0) {
                        matrixButton.setCounter((mInputedX - i - 1) * 10);
                    }
                    if (mIsShowCoords && i == mInputedX - 1) {
                        matrixButton.setCounter(j);
                    }
                }
                data.add(matrixButton);
            }
        }


        GridViewCustom adapter = new GridViewCustom(getContext(), data);
        list.setAdapter(adapter);
        */



        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mGameProcess != null) {
            if (mGameProcess.isActive())
                mGameProcess.stop();
        }
    }

}

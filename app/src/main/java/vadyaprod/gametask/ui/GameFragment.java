package vadyaprod.gametask.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vadyaprod.gametask.MatrixButton;
import vadyaprod.gametask.R;
import vadyaprod.gametask.adapters.GridViewCustom;
import vadyaprod.gametask.model.GameField;

/**
 * Created by Vadya on 19.09.17.
 */

public class GameFragment extends Fragment {
    private final String INPUTED_X = "com.vadyaprod.GameTask.inputed_x";
    private final String INPUTED_Y = "com.vadyaprod.GameTask.inputed_y";
    private final String SHOW_TIME = "com.vadyaprod.GameTask.show_time";
    private final String SHOW_COORDS = "com.vadyaprod.GameTask.show_coords";

    private List<Button> mButtons;
    private GameField mGameField;
    private GridViewCustom mGridView;
    private TextView mTextView;
    private int mInputedX;
    private int mInputedY;
    private boolean mIsShowTime;
    private boolean mIsShowCoords;

    ArrayList<MatrixButton> data = new ArrayList<>();
    private GridView list;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle extras = this.getArguments();
        if (extras != null) {
            mInputedY = extras.getInt(INPUTED_Y);
            mInputedX = extras.getInt(INPUTED_X);
            mIsShowCoords = extras.getBoolean(SHOW_COORDS);
            mIsShowTime = extras.getBoolean(SHOW_TIME);
        }
        if (mIsShowCoords){
            mInputedX++;
            mInputedY++;
        }
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        for (int i = 0; i < mInputedX; i++) {
            for(int j=0; j < mInputedY; j++)
            {
                MatrixButton matrixButton = new MatrixButton(getContext());
                if (mIsShowCoords && mInputedY == mInputedX) {
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
        mTextView = v.findViewById(R.id.timer_text_view);
        GridViewCustom adapter = new GridViewCustom(getContext(), data, mTextView);
        list = (GridView) v.findViewById(R.id.grid_view);
        list.setAdapter(adapter);
        list.setNumColumns(mInputedX);



        return v;
    }

}

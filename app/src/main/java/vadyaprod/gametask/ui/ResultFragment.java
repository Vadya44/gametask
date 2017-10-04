package vadyaprod.gametask.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import vadyaprod.gametask.MatrixButton;
import vadyaprod.gametask.R;
import vadyaprod.gametask.container.GameResultContainer;
import vadyaprod.gametask.model.GameField;
import vadyaprod.gametask.model.GameProcess;
import vadyaprod.gametask.model.GameResult;

/**
 * Created by Vadya on 21.09.17.
 */

public class ResultFragment extends Fragment {
    private Timer timer;
    private TimerTask timerTask;


        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {

            final View v = inflater.inflate(R.layout.fragment_game, container, false);


            LinearLayout layoutVertical = (LinearLayout)v.findViewById(R.id.table_grid);
            //create a new TableLayout
            TableLayout table = null;


            table = new TableLayout(getContext());
            table.setStretchAllColumns(true);
            TableRow mainRow = new TableRow(getContext());
            TextView textview = new TextView(getContext());
            textview.setText(String.format("%-17s%-17s%-17s%-17s%-17s", "Game", "Points", "Penalty", "App Time", "Server Time" ));
            textview.setTextColor(Color.BLACK);
            textview.setTextSize(10);
            mainRow.addView(textview);
            table.addView(mainRow);
            table.setShrinkAllColumns(true);
                for (int i = 0; i < GameResultContainer.get().getGameResults().size(); i++) {
                    TableRow currentRow = new TableRow(getContext());
                    TextView textviewRes = new TextView(getContext());
                    textviewRes.setTextSize(10);
                    textviewRes.setText(String.format("\t" +"%-21s%-21s%-21s%-25s%-25s", i , GameResultContainer.get().getGameResults().get(i).getPrizePoints()
                    , GameResultContainer.get().getGameResults().get(i).getPenaltyPoints(), GameResultContainer.get().getGameResults().get(i).getAppTime(),
                            GameResultContainer.get().getGameResults().get(i).getServerTime()));
                    currentRow.addView(textviewRes);
                    table.addView(currentRow);
                }
                // a new row has been constructed -> add to table
                layoutVertical.addView(table);



            return v;
        }



}

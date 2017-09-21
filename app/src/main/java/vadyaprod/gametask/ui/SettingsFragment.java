package vadyaprod.gametask.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import vadyaprod.gametask.MainActivity;
import vadyaprod.gametask.R;
import vadyaprod.gametask.model.GameField;

/**
 * Created by Vadya on 19.09.17.
 */

public class SettingsFragment extends Fragment {
    private Spinner mspinnerX;
    private Spinner mspinnerY;
    private Button mbuttonStart;
    private CheckBox mshowTime;
    private CheckBox mshowCoordinates;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);


        mspinnerX = v.findViewById(R.id.spinnerX);
        mspinnerY = v.findViewById(R.id.spinnerY);
        Integer[] items = new Integer[]{5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(v.getContext(),
                android.R.layout.simple_spinner_item, items);
        mspinnerY.setAdapter(adapter);
        mspinnerX.setAdapter(adapter);

        mshowCoordinates = v.findViewById(R.id.showingCoordinates);
        mshowTime = v.findViewById(R.id.showTime);

        mbuttonStart = v.findViewById(R.id.button_start_game);
        mbuttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameField gameField = new GameField(Integer.parseInt(mspinnerX.getSelectedItem().toString()),
                        Integer.parseInt(mspinnerY.getSelectedItem().toString()), mshowCoordinates.isChecked(),
                        mshowTime.isChecked(), getActivity());
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.setGameFragment(gameField);


            }
        });
        return v;
    }
}

package vadyaprod.gametask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import vadyaprod.gametask.model.GameField;
import vadyaprod.gametask.ui.GameFragment;
import vadyaprod.gametask.ui.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private final String INPUTED_X = "com.vadyaprod.GameTask.inputed_x";
    private final String INPUTED_Y = "com.vadyaprod.GameTask.inputed_y";
    private final String SHOW_TIME = "com.vadyaprod.GameTask.show_time";
    private final String SHOW_COORDS = "com.vadyaprod.GameTask.show_coords";

    ArrayList<MatrixButton> data = new ArrayList<>();
    private GridView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new SettingsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    public void setGameFragment(GameField gameField){

        Bundle args = new Bundle();
        args.putBoolean(SHOW_COORDS, gameField.isShowCoordinates());
        args.putBoolean(SHOW_TIME, gameField.isShowServerTime());
        args.putInt(INPUTED_X, gameField.getWidth());
        args.putInt(INPUTED_Y, gameField.getHeight());
        GameFragment gameFragment = new GameFragment();
        gameFragment.setArguments(args);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, gameFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

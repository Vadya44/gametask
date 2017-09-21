package vadyaprod.gametask.container;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vadyaprod.gametask.model.GameResult;

/**
 * Created by Vadya on 20.09.17.
 */

public class GameResultContainer {
    private static GameResultContainer sResultContainer;


    public List<GameResult> getGameResults() {
        return mGameResults;
    }

    private List<GameResult> mGameResults;

    public static GameResultContainer get(){
        if (sResultContainer == null)
            sResultContainer= new GameResultContainer();
        return sResultContainer;
    }

    public static void addResult(GameResult gameResult){
        get().getGameResults().add(gameResult);
    }

    private GameResultContainer(){
        mGameResults = new ArrayList<>();
    }

}

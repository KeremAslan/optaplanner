package problems.tsp.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.tsp.domain.Location;
import problems.tsp.domain.TspSolution;

import java.math.BigDecimal;
import java.util.List;

public class EasyCalculator implements EasyScoreCalculator<TspSolution> {

    public Score calculateScore(TspSolution tspSolution) {
        BigDecimal softscore = new BigDecimal(0);
        List<Location> locations = tspSolution.getLocations();
        for(int i=0; i < locations.size(); i++){
            softscore.add( new BigDecimal(calculateDistance(locations.get(0), locations.get(i+1))));
        }
        return SimpleBigDecimalScore.valueOf(softscore);
    }


    private double calculateDistance(Location loc1, Location loc2){
        return Math.sqrt(
                Math.pow(loc1.getLongitude()-loc2.getLongitude(), 2) +
                Math.pow(loc1.getLatitude()-loc2.getLatitude(), 2)
        );
    }
}



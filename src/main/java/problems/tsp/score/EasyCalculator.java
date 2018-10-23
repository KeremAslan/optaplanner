package problems.tsp.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.tsp.domain.Assignment;
import problems.tsp.domain.Location;
import problems.tsp.domain.TspSolution;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EasyCalculator implements EasyScoreCalculator<TspSolution> {

    public Score calculateScore(TspSolution tspSolution) {
//        BigDecimal softscore = new BigDecimal(0);
        Set<Location> setOfVisitedLocations = new HashSet<>();
        int hardscore = 0;
        int softscore = 0;
        List<Assignment> assignments = tspSolution.getAssignments();
        for(int i=1; i < assignments.size(); i++){
            Location loc1 = assignments.get(i-1).getLocation();
            Location loc2 = assignments.get(i).getLocation();

            if(loc1 != null){
                if(i == 0){
                    setOfVisitedLocations.add(loc1);
                }

                if(loc2 != null){
                    setOfVisitedLocations.add(loc2);
                    softscore += calculateDistance(loc1, loc2);
                }
            }

        }
        int diff = tspSolution.getLocations().size() - setOfVisitedLocations.size();
        if(diff > 0){
            hardscore += diff;
        }
//        return SimpleBigDecimalScore.valueOf(softscore);
        return HardSoftScore.valueOf(hardscore, softscore);
    }


    private double calculateDistance(Location loc1, Location loc2){
        return Math.sqrt(
                Math.pow(loc1.getLongitude()-loc2.getLongitude(), 2) +
                Math.pow(loc1.getLatitude()-loc2.getLatitude(), 2)
        );
    }
}



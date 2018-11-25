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


    @Override
    //Note: OptaPlanner's default configuration is to maximize any function.
    // So a minimization problem involves negative score ( and constraints)
    public Score calculateScore(TspSolution tspSolution) {
        int hardScore = 0;
        int softscore = 0;

        Set<Location> visitedLocations = new HashSet<>();
        for(int i =0; i < tspSolution.getAssignments().size(); i++) {
            Assignment currentAssignment;
            Location currentLocation;
            Assignment nextAssignment;
            Location nextLocation;
            // close loop if end of route
            if(i == tspSolution.getAssignments().size()-1) {
                currentAssignment = tspSolution.getAssignments().get(i);
                nextAssignment = tspSolution.getAssignments().get(0);
            } else {
                currentAssignment = tspSolution.getAssignments().get(i);
                nextAssignment = tspSolution.getAssignments().get(i+1);
            }

            currentLocation = currentAssignment.getLocation();
            nextLocation = nextAssignment.getLocation();

            if(currentLocation==null || nextLocation == null){
                hardScore--;
                continue;
            }

            if(visitedLocations.contains(currentLocation)) {
                hardScore--;
            }

            visitedLocations.add(currentLocation);
            // The benchmarks use the rounded Eucledian distance
            softscore -= Math.round(calculateDistance(currentLocation, nextLocation));

        }
//        int diff = visitedLocations.size() - tspSolution.getAssignments().size();
//        if( diff > 0) {
//            hardScore -= diff;
//        }

        return HardSoftScore.valueOf(hardScore,  softscore);
    }

    private double calculateDistance(Location loc1, Location loc2){
        return Math.sqrt(
                Math.pow(loc1.getLongitude()-loc2.getLongitude(), 2) +
                Math.pow(loc1.getLatitude()-loc2.getLatitude(), 2)
        );
    }
}



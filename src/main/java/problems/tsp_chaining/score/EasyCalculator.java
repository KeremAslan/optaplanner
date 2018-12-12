package problems.tsp_chaining.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import problems.tsp_chaining.domain.DistanceType;
import problems.tsp_chaining.domain.Location;
import problems.tsp_chaining.domain.TspSolution;
import problems.tsp_chaining.domain.Visit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EasyCalculator implements EasyScoreCalculator<TspSolution> {

    @Override
    public Score calculateScore(TspSolution tspSolution) {
        int hardScore = 0;
        int softscore = 0;

        Set<Location> visitedLocations = new HashSet<>();

        List<Visit> visits = tspSolution.getVisits();
        for(int i =0; i < visits.size(); i++) {
            Visit currentVisit;
            Location currentLocation;
            Visit nextVisit;
            Location nextLocation;
            // close loop if end of route
            if(i == visits.size()-1) {
                currentVisit = visits.get(i);
                nextVisit = visits.get(0);
            } else {
                currentVisit = visits.get(i);
                nextVisit = visits.get(i+1);
            }

            currentLocation = currentVisit.getLocation();
            nextLocation = nextVisit.getLocation();

            if(currentLocation==null || nextLocation == null){
                hardScore--;
                continue;
            }

            if(visitedLocations.contains(currentLocation)) {
                hardScore--;
            }

            visitedLocations.add(currentLocation);
            // The benchmarks use the rounded Eucledian distance
            softscore -= currentLocation.getDistanceTo(nextLocation, DistanceType.STRAIGHT_LINE);

        }
//        int diff = visitedLocations.size() - tspSolution.getAssignments().size();
//        if( diff > 0) {
//            hardScore -= diff;
//        }
        return HardSoftScore.valueOf(hardScore,  softscore);
    }
}

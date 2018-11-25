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

//    public Score calculateScore(TspSolution tspSolution) {
////        BigDecimal softscore = new BigDecimal(0);
//        Set<Location> univistedLocations = new HashSet<>(tspSolution.getLocations());
//
//        int hardscore = 0;
//        int softscore = 0;
//        List<Assignment> assignments = tspSolution.getAssignments();
//
//        Set<Location> visitedLocations = new HashSet<>();
//
//        for(int i=0; i < tspSolution.getAssignments().size()-1; i++){
//
//            Location location = tspSolution.getAssignments().get(i).getLocation();
//            visitedLocations.add(location);
//
//            if(location == null) {
//                hardscore++;
//            } else if(visitedLocations.contains(location)){
//                hardscore++;
//            } else {
//                if(i == tspSolution.getAssignments().size()){
//                    break;
//                }
//
//                Location nextLocation = tspSolution.getAssignments().get(i+1).getLocation();
//                if(nextLocation ==null) {
//                    hardscore++;
//                } else {
//                    System.out.println(location + "-" +  nextLocation);
//                    softscore += calculateDistance(location, nextLocation);
//                }
//
//            }
//        }
//
//        return HardSoftScore.valueOf(hardscore, -softscore);
//    }


    @Override
    public Score calculateScore(TspSolution tspSolution) {
        int hardScore = 0;
        double softscore = 0;

        Set<Location> visitedLocations = new HashSet<>();
        for(int i =0; i < tspSolution.getAssignments().size()-1; i++) {
            Assignment currentAssignment = tspSolution.getAssignments().get(i);
            Location currentLocation = currentAssignment.getLocation();

            Assignment nextAssignment = tspSolution.getAssignments().get(i+1);
            Location nextLocaton = nextAssignment.getLocation();

            if(currentLocation==null || nextLocaton == null){
                hardScore++;
                continue;
            }

            if(visitedLocations.contains(currentLocation)) {
                hardScore++;
            }

            visitedLocations.add(currentLocation);




//            System.out.println(currentLocation+ " -- " + nextLocaton );
            softscore -= calculateDistance(currentLocation, nextLocaton);


        }

        return HardSoftScore.valueOf(-hardScore, (int) -softscore);
    }

    private double calculateDistance(Location loc1, Location loc2){
        return Math.sqrt(
                Math.pow(loc1.getLongitude()-loc2.getLongitude(), 2) +
                Math.pow(loc1.getLatitude()-loc2.getLatitude(), 2)
        );
    }
}



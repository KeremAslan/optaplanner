package problems.tsp.score.kaggle;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.tsp.app.TspApp;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.domain.Visit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TspKaggleEasyCalculator implements EasyScoreCalculator<TspSolution> {


    @Override
    //Note: OptaPlanner's default configuration is to maximize any function.
    // So a minimization problem involves negative score ( and constraints)
    public Score calculateScore(TspSolution tspSolution) {
        int hardScore = 0;
        int softScore = 0;
        int doubleVisits = 0;
        Set<Standstill> alreadyVisited = new HashSet<>();

        List<Visit> visits = tspSolution.getVisits();
        Set<Visit> tailVisitSet = new HashSet<>(visits);

        Standstill domicile = tspSolution.getDomicile();
        alreadyVisited.add(domicile);
        Standstill lastVisit = domicile;
        while (lastVisit.getNextVisit() != null) {
            // TODO implement penalty! using shadow variables
//            hardScore -= penalty;
            Standstill nextVisit = lastVisit.getNextVisit();
            int penalty = 0;
//            boolean cond1 = nextVisit.getPosition() % 10 == 0;
//            boolean cond2 = nextVisit.getLocation().getId() % 10 != 0;
            if (nextVisit.getPosition() % 10 == 0 && nextVisit.getLocation().getId() % 10 != 0) {
                penalty = 1;
            }
            hardScore -= penalty;
            if (alreadyVisited.contains(nextVisit)) {
                alreadyVisited.add(nextVisit);
                doubleVisits++;
            }
//            softScore -= lastVisit.getDistanceTo(nextVisit);

            softScore -= ((Visit) nextVisit).getDistanceFromPreviousStandstill();
            lastVisit = nextVisit;

        }
        // close loop
        hardScore -= doubleVisits;
        softScore -= domicile.getDistanceTo(lastVisit);
        return HardSoftScore.valueOf(hardScore,  softScore);
    }

}



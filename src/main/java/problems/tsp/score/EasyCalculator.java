package problems.tsp.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.Visit;
import problems.tsp.domain.Location;
import problems.tsp.domain.TspSolution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EasyCalculator implements EasyScoreCalculator<TspSolution> {


    @Override
    //Note: OptaPlanner's default configuration is to maximize any function.
    // So a minimization problem involves negative score ( and constraints)
    public Score calculateScore(TspSolution tspSolution) {
        int hardScore = 0;
        int softScore = 0;

        List<Visit> visits = tspSolution.getVisits();
        Set<Visit> tailVisitSet = new HashSet<>(visits);

        //first determine the distance for the chain. i.e. Anchor -> Visit 1 -> Visit 2 -> etc.
        for(Visit visit : visits) {
            Standstill previousStandstill = visit.getPreviousStandstill();
            if (previousStandstill != null) {
                softScore -= Math.round(visit.getDistanceFromPreviousStandstill());
                if (previousStandstill instanceof Visit) {
                    tailVisitSet.remove(previousStandstill);
                }
            }
        }

        // then close loop from Visit n -> Anchor.
        Domicile domicile = tspSolution.getDomicile();
        for (Visit tailVisit : tailVisitSet) {
            if (tailVisit.getPreviousStandstill() != null) {
                softScore -= tailVisit.getDistanceTo(domicile);
            }
        }

        return HardSoftScore.valueOf(hardScore,  softScore);
    }

}



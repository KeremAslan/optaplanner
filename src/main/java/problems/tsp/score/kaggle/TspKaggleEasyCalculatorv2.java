package problems.tsp.score.kaggle;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.tsp.app.TspApp;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.domain.Visit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TspKaggleEasyCalculatorv2 implements EasyScoreCalculator<TspSolution> {


    @Override
    //Note: OptaPlanner's default configuration is to maximize any function.
    // So a minimization problem involves negative score ( and constraints)
    public Score calculateScore(TspSolution tspSolution) {
        int hardScore = 0;
        double softScore = 0;

        List<Visit> visits = tspSolution.getVisits();
        Set<Visit> tailVisitSet = new HashSet<>(visits);

        //first determine the distance for the chain. i.e. Anchor -> Visit 1 -> Visit 2 -> etc.
        for(Visit visit : visits) {
            Standstill previousStandstill = visit.getPreviousStandstill();
            if (previousStandstill != null) {
                if (TspApp.coursera) {
                    softScore -= Math.round(visit.getDistanceFromPreviousStandstill());
                } else {
                    softScore -= visit.getDistanceFromPreviousStandstill();
                }

                if (visit.penalize()) {
                    hardScore--;
                }

                if (previousStandstill instanceof Visit) {
                    tailVisitSet.remove(previousStandstill);
                }
            }
        }

        // then close loop from Visit n -> Anchor.
        Domicile domicile = tspSolution.getDomicile();
        for (Visit tailVisit : tailVisitSet) {
            if (tailVisit.getPreviousStandstill() != null) {
                if (TspApp.coursera) {
                    softScore -= tailVisit.getDistanceTo(domicile);
                } else {
                    softScore -= Math.round(tailVisit.getDistanceTo(domicile));
                }

            }
        }

//        return
        return HardSoftDoubleScore.valueOf(Integer.valueOf(hardScore).doubleValue(), softScore);
    }

}



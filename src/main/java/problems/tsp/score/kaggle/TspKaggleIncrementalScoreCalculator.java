package problems.tsp.score.kaggle;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.domain.Visit;

public class TspKaggleIncrementalScoreCalculator extends AbstractIncrementalScoreCalculator<TspSolution> {

    private Domicile domicile;

    private int hardscore;
    private int score;

    //TODO implement coursera eval method
    @Override
    public void resetWorkingSolution(TspSolution tspSolution) {
        domicile = tspSolution.getDomicile();
        hardscore = 0;
        score = 0;
        for(Visit visit : tspSolution.getVisits()) {
            insert(visit);
        }
    }

    private void insert(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        Standstill nextStandstill = visit.getNextVisit();
        if (previousStandstill != null) {
            score -= visit.getDistanceFromPreviousStandstill();
//            score += previousStandstill.getDistanceTo(nextStandstill);
//            score -= visit.getDistanceTo(nextStandstill);
//            if (visit.getPosition() % 10 == 0) {
//                if (visit.getLocation().getId() % 10 != 0) {
//                    hardscore -= 1;
//                }
//            }

            Integer position = visit.getPosition();
            if (position != null) {
                hardscore -= visit.getPosition() % 10 == 0 ?
                        visit.getLocation().getId() % 10 != 0 ? 1 : 0 : 0;
            }
//            score -= Math.round(visit.getDistanceFromPreviousStandstill());
//            // HACK: This counts too much, but the insert/retracts balance each other out
//            score += Math.round(previousStandstill.getDistanceTo(domicile));
//            score -= Math.round(visit.getDistanceTo(domicile));
        }
    }

    private void retract(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        Standstill nextStandstill = visit.getNextVisit();
        if(previousStandstill != null) {
            score += visit.getDistanceFromPreviousStandstill();
            Integer position = visit.getPosition();
            if (position != null) {
                hardscore += visit.getPosition() % 10 == 0 ?
                        visit.getLocation().getId() % 10 == 0 ? 1 : 0 : 0;
            }

//            score += previousStandstill.getDistanceTo(nextStandstill);

//            score += Math.round(visit.getDistanceFromPreviousStandstill());
//            // HACK: This counts too much, but the insert/retracts balance each other out
//            // Calculate distance when visit has no previous standstill anymore
//            score -= Math.round(previousStandstill.getDistanceTo(domicile));
//            score += Math.round(visit.getDistanceTo(domicile));
        }
    }

    @Override
    public void beforeEntityAdded(Object o) {
        // do nothing because in the TSP problem no entities are added or removed
    }

    @Override
    public void afterEntityAdded(Object o) {
        insert((Visit) o);
    }

    @Override
    public void beforeVariableChanged(Object o, String s) {
        retract((Visit) o);
    }

    @Override
    public void afterVariableChanged(Object o, String s) {
        insert((Visit) o);
    }

    @Override
    public void beforeEntityRemoved(Object o) {
        retract((Visit) o);
    }

    @Override
    public void afterEntityRemoved(Object o) {
        // do nothing because in the tsp porblem no entities are added or removed
    }

    @Override
    public Score calculateScore() {
        return HardSoftScore.valueOf(0, score);
    }
}

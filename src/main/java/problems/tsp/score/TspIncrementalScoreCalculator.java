package problems.tsp.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.domain.Visit;

public class TspIncrementalScoreCalculator extends AbstractIncrementalScoreCalculator<TspSolution> {

    private Domicile domicile;

    private int score;

    //TODO implement coursera eval method
    @Override
    public void resetWorkingSolution(TspSolution tspSolution) {
        domicile = tspSolution.getDomicile();
        score = 0;
        for(Visit visit : tspSolution.getVisits()) {
            insert(visit);
        }
    }

    private void insert(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        if (previousStandstill != null) {
            score -= Math.round(visit.getDistanceFromPreviousStandstill());
            // HACK: This counts too much, but the insert/retracts balance each other out
            score += Math.round(previousStandstill.getDistanceTo(domicile));
            score -= Math.round(visit.getDistanceTo(domicile));
        }
    }

    private void retract(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        if(previousStandstill != null) {
            score += Math.round(visit.getDistanceFromPreviousStandstill());
            // HACK: This counts too much, but the insert/retracts balance each other out
            // Calculate distance when visit has no previous standstill anymore
            score -= Math.round(previousStandstill.getDistanceTo(domicile));
            score += Math.round(visit.getDistanceTo(domicile));
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

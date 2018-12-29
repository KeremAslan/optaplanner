package problems.tsp.score.kaggle;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.domain.Visit;

public class TspKaggleIncrementalScoreCalculatorv2 extends AbstractIncrementalScoreCalculator<TspSolution> {

    private Domicile domicile;

    private int hardscore;
    private int score;

    @Override
    public void resetWorkingSolution(TspSolution tspSolution) {
        domicile = tspSolution.getDomicile();
        hardscore = 0;
        score = 0;
        for (Visit visit: tspSolution.getVisits()) {
            insert(visit);
        }
    }

    private void insert(Standstill standstill) {
        if (standstill instanceof Visit) {
            Visit visit = (Visit) standstill;
            Standstill previousStandstill = visit.getPreviousStandstill();
            if (previousStandstill != null) {
                score -= visit.getDistanceFromPreviousStandstill();
                // close loop
                score += domicile.getDistanceTo(previousStandstill);
                score -= domicile.getDistanceTo(visit);

                Integer position = visit.getPosition();
                if (position != null) {
                    hardscore -= visit.getPosition() % 10 == 0 ?
                            visit.getLocation().getId() % 10 != 0 ? 1 : 0 : 0;
                }
            }
        } else if( standstill instanceof Domicile){
            // instance of domicile
            // do nothing
        }
    }

    private void retract(Standstill standstill) {
        if (standstill instanceof Visit) {
            Visit visit = (Visit) standstill;
            Standstill previousStandstill = visit.getPreviousStandstill();
            if (previousStandstill != null) {
                score += visit.getDistanceFromPreviousStandstill();
                // close loop
                score -= domicile.getDistanceTo(previousStandstill);
                score += domicile.getDistanceTo(visit);

                Integer position = visit.getPosition();
                if (position != null) {
                    hardscore += visit.getPosition() % 10 == 0 ?
                            visit.getLocation().getId() % 10 != 0 ? 1 : 0 : 0;
                }
            }
        } else if( standstill instanceof Domicile){
            // instance of domicile
            // do nothing
        }
    }

    @Override
    public void beforeEntityAdded(Object o) {
        retract((Standstill) o);
    }

    @Override
    public void afterEntityAdded(Object o) {
        insert((Standstill) o);
    }

    @Override
    public void beforeVariableChanged(Object o, String s) {
        retract((Standstill) o);
    }

    @Override
    public void afterVariableChanged(Object o, String s) {
        insert((Standstill) o);
    }

    @Override
    public void beforeEntityRemoved(Object o) {

    }

    @Override
    public void afterEntityRemoved(Object o) {

    }

    @Override
    public Score calculateScore() {
        return HardSoftScore.valueOf(hardscore, score);
    }
}

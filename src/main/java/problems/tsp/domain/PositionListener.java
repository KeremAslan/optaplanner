package problems.tsp.domain;

import org.optaplanner.core.impl.domain.variable.listener.VariableListener;
import org.optaplanner.core.impl.score.director.ScoreDirector;


public class PositionListener implements VariableListener<Visit> {

    @Override
    public void beforeEntityAdded(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector scoreDirector, Visit visit) {
        updatePosition(scoreDirector, visit);
    }

    @Override
    public void beforeVariableChanged(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector scoreDirector, Visit visit) {
        updatePosition(scoreDirector, visit);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector scoreDirector, Visit visit) {

    }

    // NOTE position of domicile is not set  as this is never updated!
    private void updatePosition(ScoreDirector scoreDirector, Visit visit) {
        Standstill standstill = visit;
        Standstill previousStandstill = visit.getPreviousStandstill();

        Integer previousStandstillPostition;

        if (previousStandstill == null) {
            previousStandstillPostition = null;
        } else if (previousStandstill instanceof Domicile) {
            previousStandstillPostition = 1;
        } else {
            previousStandstillPostition = previousStandstill.getPosition();
        }

        if (previousStandstillPostition != null) {
            do {
                scoreDirector.beforeVariableChanged(visit, "position");
                int currentPosition = previousStandstillPostition + 1;
                standstill.setPosition(currentPosition);
                scoreDirector.afterVariableChanged(visit, "position");
                standstill = standstill.getNextVisit();
                previousStandstillPostition = currentPosition;
            } while (standstill != null);
        }


    }
}

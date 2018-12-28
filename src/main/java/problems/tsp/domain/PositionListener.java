package problems.tsp.domain;

import org.optaplanner.core.impl.domain.variable.listener.VariableListener;
import org.optaplanner.core.impl.score.director.ScoreDirector;


public class PositionListener implements VariableListener<Visit> {

    @Override
    public void beforeEntityAdded(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void beforeVariableChanged(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void beforeEntityRemoved(ScoreDirector scoreDirector, Visit visit) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector scoreDirector, Visit visit) {

    }

    private void updatePosition(ScoreDirector scoreDirector, Visit visit) {
        TspSolution tspSolution = (TspSolution) scoreDirector.getWorkingSolution();
        Domicile domicile = tspSolution.getDomicile();

        Integer position = visit.getPosition();



    }
}

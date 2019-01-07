package problems.tsp.score.kaggle;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.domain.Visit;

public class TspKaggleIncrementalScoreCalculator extends AbstractIncrementalScoreCalculator<TspSolution> {

    private Domicile domicile;

    private int hardscore;
    private double score;

    //TODO implement coursera eval method
    @Override
    public void resetWorkingSolution(TspSolution tspSolution) {
        domicile = tspSolution.getDomicile();
        hardscore = 0;
        score = 0.0;
        for(Visit visit : tspSolution.getVisits()) {
            insert(visit);
            insertPosition(visit);
//            insertNextVisit(visit);
        }
    }

    private void insert(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        if (previousStandstill != null) {
            score -= visit.getDistanceFromPreviousStandstill();

//            // HACK: This counts too much, but the insert/retracts balance each other out
            score += previousStandstill.getDistanceTo(domicile);
            score -= visit.getDistanceTo(domicile);
        }
    }

    private void retract(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();

        if(previousStandstill != null) {
            score += visit.getDistanceFromPreviousStandstill();
//            // HACK: This counts too much, but the insert/retracts balance each other out
//            // Calculate distance when visit has no previous standstill anymore
            score -= previousStandstill.getDistanceTo(domicile);
            score += visit.getDistanceTo(domicile);
        }
    }

    private void retractNextVisit(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        if (previousStandstill != null) {
            score += visit.getDistanceFromPreviousStandstill();
        }
    }

    private void insertNextVisit(Visit visit) {
        Standstill previousStandstill = visit.getPreviousStandstill();
        if (previousStandstill != null) {
            score -= visit.getDistanceFromPreviousStandstill();
        }
    }

    private void retractPosition(Visit visit) {
        Integer position = visit.getPosition();
        if (position != null) {

            if (visit.getLocation().getId() % 10 == 0) {
                // a 10x location

                if (position % 10 == 0) {
                    hardscore--;
                } else {
                    hardscore++;
                    // do nothing
//                    hardscore++;
                }

            } else {
                if (position % 10 == 0) {
                    hardscore--;
                }
            }
        }

    }

    private void insertPosition(Visit visit) {
        Integer position = visit.getPosition();
        if (position!= null) {

            if (visit.getLocation().getId() % 10 == 0) {
                if (position % 10 == 0) {
                    hardscore++;
                } else {
                    hardscore--;
//                    hardscore++;
                }
            } else {
                if (position % 10 == 0) {
                    hardscore--;
                }
            }
        }

    }



    @Override
    public void beforeEntityAdded(Object o) {
        // do nothing because in the TSP problem no entities are added or removed
    }

    @Override
    public void afterEntityAdded(Object o) {
        if (o instanceof Domicile) {
            return;
        }

        insert((Visit) o);
//        insertPosition((Visit) o);
//        insertNextVisit((Visit) o);
    }

    @Override
    public void beforeVariableChanged(Object o, String s) {
        if (o instanceof Domicile) {
            return;
        }
        switch (s) {
            case "previousStandstill":
                retract((Visit) o);
                break;
            case "nextVisit":
//                retractNextVisit((Visit) o);
                break;
            case "position":
                retractPosition((Visit) o);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized variable change " + s);
        }
    }

    @Override
    public void afterVariableChanged(Object o, String s) {
        if (o instanceof Domicile) {
            return;
        }

        switch (s) {
            case "previousStandstill":
                insert((Visit) o);
                break;
            case "nextVisit":
//                insertNextVisit((Visit) o);
                break;
            case "position":
                insertPosition((Visit) o);
                break;
            default:
                throw new IllegalArgumentException("Unrecognized variable change " + s);
        }
    }

    @Override
    public void beforeEntityRemoved(Object o) {
        if (o instanceof Domicile) {
            return;
        }
        retract((Visit) o);
//        retractPosition((Visit) o);
//        retractNextVisit((Visit) o);
    }

    @Override
    public void afterEntityRemoved(Object o) {
        // do nothing because in the tsp porblem no entities are added or removed
    }

    @Override
    public Score calculateScore() {
//        System.out.println(hardscore);
        return HardSoftDoubleScore.valueOf(hardscore, score);
    }
}

package problems.tsp.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;

import java.util.List;

@PlanningSolution
public class TspSolution {


    private List<Location> locations;
    private SimpleBigDecimalScore score;

    public TspSolution(){

    }

    public TspSolution(List<Location> locations){
        this.locations = locations;
    }

    @PlanningEntityCollectionProperty
    public List<Location> getLocations() {
        return locations;
    }

    @PlanningScore
    public SimpleBigDecimalScore getScore() {
        return score;
    }

    public void setScore(SimpleBigDecimalScore score){
        this.score = score;
    }
}

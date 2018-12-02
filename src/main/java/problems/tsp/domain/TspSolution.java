package problems.tsp.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;

import java.util.List;

@PlanningSolution
public class TspSolution {


    private List<Location> locations;

    private List<Assignment> assignments;

//    private SimpleBigDecimalScore score;
    private HardSoftScore score;

    public TspSolution(){

    }

    public TspSolution(List<Location> locations, List<Assignment> assignments){
        this.locations = locations;
        this.assignments = assignments;
    }

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id="locations")
    public List<Location> getLocations() {
        return locations;
    }

    @PlanningEntityCollectionProperty
    public List<Assignment> getAssignments() {
        return assignments;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score){
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("TspSolution{[");
        for(Assignment assignment : getAssignments()) {
            sb.append("(");
            sb.append(assignment.getPosition()).append(")");
            sb.append(assignment.getLocation().getId());
            sb.append("-");
        }
        sb.append("}");
        return sb.toString();
    }
}

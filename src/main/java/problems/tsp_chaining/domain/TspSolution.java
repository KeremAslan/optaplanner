package problems.tsp_chaining.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import problems.tsp_chaining.domain.Location;

import java.util.List;

@PlanningSolution
public class TspSolution {

    private String name;
    private List<Location> locations;
    private HardSoftScore score;

    private List<Visit> visits;

    public TspSolution() {

    }

    public TspSolution(List<Location> locations, List<Visit> visits) {
        this.locations = locations;
        this.visits = visits;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ProblemFactCollectionProperty
    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "visitRange")
    public List<Visit> getVisits() {
        return visits;
    }


    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}

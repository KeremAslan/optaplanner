package problems.tsp.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.Collections;
import java.util.List;

@PlanningSolution
public class TspSolution {


    private String name;

    /** Starting point of TSP tour */
    private Domicile domicile;

    private List<Location> locations;

    private List<Visit> visits;

    private HardSoftScore score;

    public TspSolution(){

    }

    public TspSolution(List<Location> locations, List<Visit> visits){
        this.locations = locations;
        this.visits = visits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ProblemFactProperty
    public Domicile getDomicile() {
        return domicile;
    }

    public void setDomicile(Domicile domicile) {
        this.domicile = domicile;
    }

    @ProblemFactCollectionProperty
    public List<Location> getLocations() {
        return locations;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "visitRange")
    public List<Visit> getVisits() {
        return visits;
    }

    @ValueRangeProvider(id= "domicileRange")
    public List<Domicile> getDomicileRange() {
        return Collections.singletonList(domicile);
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score){
        this.score = score;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("TspSolution{[");
        for(Visit visit : visits) {
//            sb.append("(");
            if (visit.getLocation() != null) {
                sb.append(visit.getLocation().getId());
            }
            sb.append("-");
        }
        sb.append("}");
        return sb.toString();
    }
}

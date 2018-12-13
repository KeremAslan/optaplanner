package problems.tsp.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@PlanningSolution
public class TspSolution implements Serializable {


    private String name;

    /** Starting point of TSP tour */
    private Domicile domicile;

    private List<Location> locations;

    private List<Visit> visits;

    private HardSoftScore score;

    public TspSolution(){

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

    @ValueRangeProvider(id= "domicileRange")
    public List<Domicile> getDomicileRange() {
        return Collections.singletonList(domicile);
    }

    @ProblemFactCollectionProperty
    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "visitRange")
    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
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
        sb.append("TspSolution{# ");

        sb.append(visits.size()+1).append("[");


        Standstill standstill = domicile;

        while (true) {
            sb.append(standstill.getLocation().getId());
            standstill = findNextVisit(standstill);
            if (standstill != null) {
                sb.append("-");
            } else {
                break;
            }
        }
        sb.append("]}");

        return sb.toString();
    }

    public Visit findNextVisit(Standstill standstill) {
        for (Visit visit : this.getVisits()) {
            if(visit.getPreviousStandstill() == standstill) {
                return visit;
            }
        }

        return null;
    }
}

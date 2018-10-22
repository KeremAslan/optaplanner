package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.List;

@PlanningEntity
public class Tour {

    private List<Location> route;

    public Tour(){

    }

    public Tour(List<Location> route){
        this.route = route;
    }


    public List<Location> getRoute(){
        return route;
    }

    @PlanningVariable(valueRangeProviderRefs = "tour")
    public void setRoute(List<Location> route){
        this.route = route;
    }


}

package problems.tsp.domain;

import java.util.List;

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

    public void setRoute(List<Location> route){
        this.route = route;
    }
}

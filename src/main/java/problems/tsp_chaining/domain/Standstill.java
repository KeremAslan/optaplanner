package problems.tsp_chaining.domain;

public interface Standstill {

    Location getLocation();

    double getDistanceTo(Standstill standstill);
}

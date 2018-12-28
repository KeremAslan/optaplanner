package problems.tsp.domain;

public interface Standstill {

  Location getLocation();

  double getDistanceTo(Standstill standstill);

//  Visit getNextVisit();
//  void setNextVisit(Visit visit);
}

package problems.tsp.domain;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

public class CustomerNearbyDistanceMeter implements NearbyDistanceMeter<Visit, Standstill> {

  @Override
  public double getNearbyDistance(Visit o, Standstill standstill) {
    return o.getDistanceTo(standstill);
//    return o.getDistanceTo(standstill);
  }
}

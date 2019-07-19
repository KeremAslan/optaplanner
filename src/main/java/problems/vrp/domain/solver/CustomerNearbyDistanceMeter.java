package problems.vrp.domain.solver;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Standstill;

public class CustomerNearbyDistanceMeter extends NearbyDistanceMeter<Customer, Standstill> {

  @Override
  public double getNearbyDistance(Customer customer, Standstill standstill) {
    return customer.getDistanceTo(standstill);
  }
}

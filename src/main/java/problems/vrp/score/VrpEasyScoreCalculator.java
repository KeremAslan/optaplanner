package problems.vrp.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Standstill;
import problems.vrp.domain.Vehicle;
import problems.vrp.domain.VrpSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VrpEasyScoreCalculator implements EasyScoreCalculator<VrpSolution> {

  @Override
  public Score calculateScore(VrpSolution vrpSolution) {
    double hardScore = 0;
    double softScore = 0;

    Map<Vehicle, Integer> vehicleDemandMap = new HashMap<>();
    List<Vehicle> vehicles = vrpSolution.getVehicles();
    List<Customer> customers = vrpSolution.getCustomers();

    for(Vehicle vehicle: vehicles) {
      vehicleDemandMap.put(vehicle, 0);
    }

    for(Customer customer: customers) {
      Standstill previousLocation = customer.getPreviousLocation();

      if (previousLocation != null) {
        Vehicle vehicle = customer.getVehicle();

        vehicleDemandMap.put(vehicle, vehicleDemandMap.get(vehicle) + customer.getDemand());

        softScore -= customer.getDistanceFromPreviousStandstill();

        // loop back to depot
        if (customer.getNextCustomer() == null) {
          softScore -= customer.getLocation().getDistanceTo(vehicle.getLocation());
        }
      }

    }

    for (Map.Entry<Vehicle, Integer> entry: vehicleDemandMap.entrySet()) {
      int capacity = entry.getKey().getCapacity();
      int demand = entry.getValue();

      if (demand > capacity) {
        hardScore -= (demand - capacity);
      }
    }

    return HardSoftDoubleScore.valueOf(hardScore, softScore);

  }
}

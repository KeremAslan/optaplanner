package problems.vrp.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardmediumsoftlong.HardMediumSoftLongScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Standstill;
import problems.vrp.domain.Vehicle;
import problems.vrp.domain.VrpSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SintefEasyScoreCalculator implements EasyScoreCalculator<VrpSolution> {

  @Override
  public Score calculateScore(VrpSolution vrptwSolution) {

    int hardScore = 0;
    int numberOfVehicles = 0;
    double totalDistance = 0.0;

    Map<Vehicle, Integer> vehicleDemandMap = new HashMap<>();
    List<Vehicle> vehicles = vrptwSolution.getVehicles();
    List<Customer> customers = vrptwSolution.getCustomers();

    for(Vehicle vehicle: vehicles) {
      vehicleDemandMap.put(vehicle, 0);
    }

    for (Customer customer: vrptwSolution.getCustomers()) {
      Standstill previousStandstill = customer.getPreviousStandstill();

      Vehicle vehicle = customer.getVehicle();

      if (previousStandstill != null) {
        totalDistance -= customer.getDistanceFromPreviousStandstill();
        vehicleDemandMap.put(vehicle, vehicleDemandMap.get(vehicle) + customer.getDemand());

        if (customer.getNextCustomer() != null) {
          totalDistance -= customer.getLocation().getDistanceTo(vehicle.getDepot().getLocation());
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

    return HardMediumSoftLongScore.valueOf(hardScore, numberOfVehicles, Math.round(totalDistance));
  }
}
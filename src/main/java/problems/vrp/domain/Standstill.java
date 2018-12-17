package problems.vrp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Standstill {

  Location getLocation();

  double getDistanceTo(Standstill standstill);

  Vehicle getVehicle();

  @InverseRelationShadowVariable(sourceVariableName = "previousLocation")
  Customer getNextCustomer();
  void setNextCustomer(Customer nextCustomer);
}

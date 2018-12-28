package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Standstill {

  Location getLocation();

  double getDistanceTo(Standstill standstill);

  @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
  Visit getNextVisit();
  void setNextVisit(Visit visit);
}

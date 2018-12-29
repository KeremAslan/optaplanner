package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Standstill {

  Location getLocation();

  double getDistanceTo(Standstill standstill);

  @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
  Visit getNextVisit();
  void setNextVisit(Visit visit);

  @PlanningId
  Integer getId();
  void setId(Integer id);
}

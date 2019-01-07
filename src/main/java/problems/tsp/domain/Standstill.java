package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.entity.PlanningPin;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

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

  @PlanningPin
  boolean isPinned();
//  void setPinned(boolean setPinned);

  Integer getPosition();
  void setPosition(Integer position);
}

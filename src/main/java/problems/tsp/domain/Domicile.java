package problems.tsp.domain;


import org.optaplanner.core.api.domain.entity.PlanningPin;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import java.io.Serializable;

/**
 * A domicile represents the home of a "salesman" and is an "anchor" in a list of chain.
 * Therefore it has no previousStandstill.
 */

public class Domicile implements Standstill, Serializable {

  private Integer id;

  private Location location;

  private Integer position;

  private Visit nextVisit;

  private boolean isPinned;


  @Override
  public Location getLocation() {
    return location;
  }

 @Override
  public double getDistanceTo(Standstill standstill) {
    return location.getDistanceTo(standstill);
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "Domicile{" +
        "location=" + location +
        '}';
  }

    @Override
    public Visit getNextVisit() {
        return nextVisit;
    }

    @Override
    public void setNextVisit(Visit visit) {
        this.nextVisit = visit;
    }

    @Override
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

//    @PlanningId
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isPinned() {
        return isPinned;
    }

//    @Override
    public void setPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }
}

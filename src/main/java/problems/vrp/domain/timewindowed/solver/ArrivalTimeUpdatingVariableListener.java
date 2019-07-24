package problems.vrp.domain.timewindowed.solver;

import org.optaplanner.core.impl.domain.variable.listener.VariableListener;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Standstill;
import problems.vrp.domain.Vehicle;
import problems.vrp.domain.timewindowed.TimeWindowedCustomer;
import problems.vrp.domain.timewindowed.TimewindowedDepot;

public class ArrivalTimeUpdatingVariableListener implements VariableListener<Customer> {

  @Override
  public void beforeEntityAdded(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void afterEntityAdded(ScoreDirector scoreDirector, Customer customer) {
    if (customer instanceof TimeWindowedCustomer) {
      updateArrivalTime(scoreDirector, (TimeWindowedCustomer) customer);
    }
  }

  @Override
  public void beforeVariableChanged(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void afterVariableChanged(ScoreDirector scoreDirector, Customer customer) {
    if (customer instanceof TimeWindowedCustomer) {
      updateArrivalTime(scoreDirector, (TimeWindowedCustomer) customer);
    }
  }

  @Override
  public void beforeEntityRemoved(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void afterEntityRemoved(ScoreDirector scoreDirector, Customer customer) {

  }

  protected void updateArrivalTime(ScoreDirector scoreDirector, TimeWindowedCustomer sourceCustomer) {
    Standstill previousStandstill = sourceCustomer.getPreviousStandstill();
    // departure time is either departure time of previous customer or departure time of depot
    Long departureTime = previousStandstill == null ? null
        : previousStandstill instanceof TimeWindowedCustomer
        ? ((TimeWindowedCustomer) previousStandstill).getDepartureTime()
        : ((TimewindowedDepot) ((Vehicle) previousStandstill).getDepot()).getStartTime();

    TimeWindowedCustomer customer = sourceCustomer;
    Long arrivalTime = calculateArrivalTime(sourceCustomer, departureTime);

    while (customer != null && !customer.getArrivalTime().equals(arrivalTime)) {
      scoreDirector.beforeVariableChanged(customer, "arrivalTime");
      customer.setArrivalTime(arrivalTime);
      scoreDirector.afterVariableChanged(customer, "arrivalTime");
      departureTime = customer.getDepartureTime();
      customer = (TimeWindowedCustomer) customer.getNextCustomer();
      arrivalTime = calculateArrivalTime(customer, departureTime);
    }

  }

  private Long calculateArrivalTime(TimeWindowedCustomer customer, Long previousDepartureTime) {
    if (customer == null || customer.getPreviousStandstill() == null) {
      return null;
    }

    if (customer.getPreviousStandstill() instanceof Vehicle) {
      // PreviousStandstill is the Vehicle, so we leave from the Depot at the best suitable time

      return Math.max(customer.getStartTime(),
         Math.round(previousDepartureTime + customer.getDistanceFromPreviousStandstill()));
    }
    return Math.round(previousDepartureTime + customer.getDistanceFromPreviousStandstill());
  }
}

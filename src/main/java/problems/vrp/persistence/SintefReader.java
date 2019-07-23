package problems.vrp.persistence;

import org.apache.commons.lang3.StringUtils;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Depot;
import problems.vrp.domain.Location;
import problems.vrp.domain.Vehicle;
import problems.vrp.domain.VrpSolution;
import problems.vrp.domain.timewindowed.TimeWindowedCustomer;
import problems.vrp.domain.timewindowed.TimewindowedDepot;
import problems.vrp.domain.timewindowed.TimewindowedVehicle;
import problems.vrp.domain.timewindowed.VrptwSolution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SintefReader {

  private final static int CUSTOMER_NO = 0;
  private final static int X_COORD = 1;
  private final static int Y_COORD = 2;
  private final static int DEMAND = 3;
  private final static int READY_TIME = 4;
  private final static int DUE_TIME = 5;
  private final static int SERVICE_TIME = 6;

  private final static int DEPOT_LINE = 10;
  private final static int VEHICLES_LINE = 5;
  private final static int NUMBER_OF_VEHICLES = 0;
  private final static int VEHICLE_CAPACITY = 1;



  public VrpSolution read(File file) {
    List<Location> locationList = new ArrayList<>();
    List<Vehicle> vehicleList = new ArrayList<>();
    List<Customer> customerList = new ArrayList<>();
    List<Depot> depotList = new ArrayList<>();

    VrptwSolution vrptwSolution = new VrptwSolution();

    try {
      BufferedReader br = new BufferedReader( new FileReader(file));
      String line;

      int lineNumber = 1;

      int customerCount = 0;
      while ( (line = br.readLine()) != null && !line.contains("EOF")) {

        if (lineNumber == VEHICLES_LINE) {
          String[] row = StringUtils.split(StringUtils.trim(line));

          int numberOfVehicles = Integer.parseInt(row[NUMBER_OF_VEHICLES]);
          int vehicleCapacity = Integer.parseInt(row[VEHICLE_CAPACITY]);

          for (int i=0; i<numberOfVehicles; i++) {
            Vehicle vehicle = new TimewindowedVehicle();
            //TechDebt: move to constructor instead
            vehicle.setCapacity(vehicleCapacity);
            vehicle.setPlanningId(String.valueOf(i));
            vehicleList.add(vehicle);
          }
        } else if (lineNumber >= DEPOT_LINE){
          String[] row = StringUtils.split(StringUtils.trim(line));
          int id = Integer.parseInt(row[CUSTOMER_NO]);
          int xCoord = Integer.parseInt(row[X_COORD]);
          int yoord = Integer.parseInt(row[Y_COORD]);
          int demand = Integer.parseInt(row[DEMAND]);
          int readyTime = Integer.parseInt(row[READY_TIME]);
          int dueTime = Integer.parseInt(row[DUE_TIME]);
          int serviceTime = Integer.parseInt(row[SERVICE_TIME]);

          Location location = new Location(id, xCoord, yoord);
          if (lineNumber == DEPOT_LINE ) {
            Depot depot = new TimewindowedDepot(location, readyTime, dueTime);
            depotList.add(depot);
            for (Vehicle vehicle: vehicleList) {
              vehicle.setDepot(depot);
            }
          } else if (lineNumber > DEPOT_LINE) {
            customerCount++;
            Customer customer = new TimeWindowedCustomer(customerCount, location, readyTime, dueTime, serviceTime, demand);
            customerList.add(customer);
            locationList.add(location);
          }
        }

        lineNumber++;

      }
    } catch (FileNotFoundException ex ) {

    } catch (IOException ioEx) {

    }

    vrptwSolution.setCustomers(customerList);
    vrptwSolution.setDepotList(depotList);
    vrptwSolution.setLocations(locationList);
    vrptwSolution.setVehicles(vehicleList);

    return vrptwSolution;
  }
}

package problems.vrp.persistence;

import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Depot;
import problems.vrp.domain.Location;
import problems.vrp.domain.Vehicle;
import problems.vrp.domain.VrpSolution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VrpProblemGenerator implements SolutionFileIO<VrpSolution> {


  @Override
  public String getInputFileExtension() {
    return null;
  }

  @Override
  public VrpSolution read(File file) {
    List<Location> locationList =  new ArrayList<>();
    List<Vehicle> vehicles = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Depot> depots = new ArrayList<>();

    VrpSolution vrpSolution = new VrpSolution();

    int lineNumber = 0;
    String line;
    int startline = 0;

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      while ( (line = br.readLine()) != null && !line.contains("EOF")) {

        int nrCustomers = 0;
        int nrVehicles = 0;
        int capacity = 0;
        if(lineNumber == 0) {
          String[] splitted = line.split(" ");
          nrCustomers = Integer.parseInt(splitted[0]);
          nrVehicles = Integer.parseInt(splitted[1]);
          capacity = Integer.parseInt(splitted[2]);

          //create and set vehicles
          for (int i=0; i<nrVehicles; i++) {
            Vehicle vehicle = new Vehicle();

            vehicle.setCapacity(capacity);
            vehicles.add(vehicle);
          }
        }


        if(lineNumber > startline ) {
          String[] splitted = line.split(" ");
          int demand = Integer.parseInt(splitted[0]);
          double lat = Double.parseDouble(splitted[1]);
          double lon = Double.parseDouble(splitted[2]);

          Location location = new Location(lineNumber-1, lat, lon);
          if (lineNumber==1) {
            Depot depot = new Depot();
            depot.setLocation(location);
            depots.add(depot);
          } else {
            Customer customer = new Customer();
            customer.setDemand(demand);
            customer.setLocation(location);
            locationList.add(location);
            customers.add(customer);

          }
        }

        lineNumber++;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    for (Vehicle vehicle: vehicles) {
      vehicle.setDepot(depots.get(0));
    }

    vrpSolution.setCustomers(customers);
    vrpSolution.setLocations(locationList);
    vrpSolution.setVehicles(vehicles);
    vrpSolution.setDepotList(depots);

    return vrpSolution;
  }

  @Override
  public void write(VrpSolution vrpSolution, File file) {


  }
}

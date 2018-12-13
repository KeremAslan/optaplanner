package problems.tsp.persistence;

import problems.tsp.domain.Domicile;
import problems.tsp.domain.Visit;
import problems.tsp.domain.Location;
import problems.tsp.domain.TspSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TspProblemGenerator {

    public enum Dataset{
      WESTERN_SAHARA,
      ZIMBABWE,
      URUGUAY;

    }

    public static TspSolution createTspProblem(Dataset dataset){
      List<Location> locations = new ArrayList<>();
      List<Visit> visits = new ArrayList<>();

      TspSolution tspSolution = new TspSolution();

      String path = "";
      int lineNumber = 1;
      String line;
      int count = 0;
      int startLine = 0;
      int endLine = 0;

      if(Dataset.WESTERN_SAHARA == dataset){
        path = "src/main/resources/problems/tsp/data/wi29.txt";
        startLine = 8;
        endLine = 36;

      } else if (Dataset.ZIMBABWE == dataset) {
        path = "src/main/resources/problems/tsp/data/zi929.txt";
        startLine = 8;
        endLine = 937;
      } else if (Dataset.URUGUAY == dataset) {
        path = "src/main/resources/problems/tsp/data/ur734.txt";
        startLine = 8;
        endLine = 742;
      } else {
        return tspSolution;
      }

      try {
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine()) != null){
          if( lineNumber >= startLine && lineNumber < endLine){

            String[] splitted = line.split(" ");
            Location location = new Location(Integer.valueOf(splitted[0]), Double.valueOf(splitted[1]), Double.valueOf(splitted[2]));

            // Note that the non-variable fields need to be set as planner does not configure these
            if(count == 0) {
              Domicile domicile = new Domicile();
              domicile.setLocation(location);
              tspSolution.setDomicile(domicile);
            } else {
              Visit visit = new Visit();
              visit.setLocation(location);
              locations.add(location);
              visits.add(visit);
            }
            count++;
          }
          lineNumber++;

        }
      } catch (IOException e){
        e.printStackTrace();
      }

      tspSolution.setVisits(visits);
      tspSolution.setLocations(locations);
      return tspSolution;
    }


}

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
      ZIMBABWE;

    }

    public static TspSolution createTspProblem(Dataset dataset){
        List<Location> locations = new ArrayList<>();
        List<Visit> visits = new ArrayList<>();

        TspSolution tspSolution = new TspSolution();
        if(Dataset.WESTERN_SAHARA == dataset){
            try {
                String path = "src/main/resources/problems/tsp/data/wi29.txt";
                Stream<String> stream =  Files.lines(Paths.get(path));
//                stream.forEach(line -> System.out.println(line));
//                stream.filter(line -> )
                // TODO replace by stream
                BufferedReader br = new BufferedReader(new FileReader(path));
                int lineNumber = 1;
                String line;
                int count = 0;
                while((line = br.readLine()) != null){
                    if( lineNumber >= 8 && lineNumber < 36){

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
              throw new RuntimeException("Couldn't read data for " + dataset);
            }

        } else {
          try {
            String path = "src/main/resources/problems/tsp/data/zi929.txt";

            BufferedReader br = new BufferedReader(new FileReader(path));
            int lineNumber = 1;
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
              if( lineNumber >= 8 && lineNumber < 937){

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
            throw new RuntimeException("Couldn't read data for " + dataset);
          }
        }
        tspSolution.setVisits(visits);
        tspSolution.setLocations(locations);
        return tspSolution;
    }


}

package problems.tsp.persistence;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tsp.app.TspApp;
import problems.tsp.domain.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class TspProblemGenerator implements SolutionFileIO<TspSolution> {

  Logger LOGGER = LoggerFactory.getLogger(TspProblemGenerator.class);

  String inputFileExtension = ".txt";
    public enum Dataset{
      WESTERN_SAHARA,
      ZIMBABWE,
      URUGUAY
    }

  @Override
  public String getInputFileExtension() {
    return inputFileExtension;
  }

  @Override
  public TspSolution read(File file) {
    List<Location> locations = new ArrayList<>();
    List<Visit> visits = new ArrayList<>();

    TspSolution tspSolution = new TspSolution();

    int lineNumber = 1;
    String line;
    int count = 0;
    int startLine;

    if (file.getName().endsWith(inputFileExtension)) {
      startLine = 8;
    } else {
      startLine = 2;
      TspApp.coursera = true;
    }


    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      while((line = br.readLine()) != null && !line.contains("EOF")){
        if( lineNumber >= startLine){
          String[] splitted = line.split(" ");
          Location location;
          if (TspApp.coursera) {
            location = new Location(count-1, Double.valueOf(splitted[0]), Double.valueOf(splitted[1]));
          } else {
            location = new Location(Integer.valueOf(splitted[0]), Double.valueOf(splitted[1]), Double.valueOf(splitted[2]));

          }

          // Note that the non-variable fields need to be set as planner does not configure these
          if(count == 0) {
            Domicile domicile = new Domicile();

            domicile.setId(count);
            domicile.setLocation(location);
            tspSolution.setDomicile(domicile);
          } else {
            Visit visit = new Visit();
            visit.setLocation(location);
            locations.add(location);
            visits.add(visit);
            visit.setId(count);
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

  @Override
  public void write(TspSolution tspSolution, File file) {

  }

  public String getCourseraOutput(TspSolution tspSolution) {
    Standstill domicile = tspSolution.getDomicile();

    HardSoftScore score = tspSolution.getScore();

    StringBuilder sb= new StringBuilder();
    sb.append(score.getSoftScore()).append(" 0\n");
    while(true) {
      Standstill next = tspSolution.findNextVisit(domicile);
      if(next == null) {
        break;
      } else {
        sb.append(((Visit) next).getId());
      }
    }
    return sb.toString();
  }

  public TspSolution createTspProblem(Dataset dataset){

      String path;
      if(Dataset.WESTERN_SAHARA == dataset){
        path = "src/main/resources/problems/tsp/data/wi29.txt";
      } else if (Dataset.ZIMBABWE == dataset) {
        path = "src/main/resources/problems/tsp/data/zi929.txt";
      } else if (Dataset.URUGUAY == dataset) {
        path = "src/main/resources/problems/tsp/data/ur734.txt";
      } else {
        LOGGER.warn("Unspecified dataset");
        throw new RuntimeException("Unknown dataset!");
      }
      return read(new File(path));

    }


}

package problems.tsp_chaining.persistence;

import problems.tsp_chaining.domain.Location;
import problems.tsp_chaining.domain.TspSolution;
import problems.tsp_chaining.domain.Visit;

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
    }

    public static TspSolution createTspProblem(problems.tsp_chaining.persistence.TspProblemGenerator.Dataset dataset){
        List<Location> locations = new ArrayList<>();
        List<Visit> visits = new ArrayList<>();
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
                int assignmentPos = 0;
                while((line = br.readLine()) != null){
                    if( lineNumber>= 8 && lineNumber < 36){
                        String[] splitted = line.split(" ");
                        Location location = new Location(Integer.valueOf(splitted[0]), Double.valueOf(splitted[1]), Double.valueOf(splitted[2]));
//                        Assignment assignment = new Assignment(assignmentPos);
                        Visit visit = new Visit();
                        locations.add(location);
                        visits.add(visit);
                        assignmentPos++;
                    }
                    lineNumber++;
                }
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        return new TspSolution(locations, visits);
    }
}

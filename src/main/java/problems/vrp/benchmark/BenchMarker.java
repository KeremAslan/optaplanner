package problems.vrp.benchmark;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;

import java.io.File;

public class BenchMarker {

  public static void main(String[] args) {

    PlannerBenchmarkFactory plannerBenchmarkFactory = PlannerBenchmarkFactory.createFromXmlFile(new File("src/main/resources/problems/vrp/solver/vrpBenchmarkingConfig"));
    PlannerBenchmark plannerBenchmark = plannerBenchmarkFactory.buildPlannerBenchmark();
    plannerBenchmark.benchmark();
  }
}

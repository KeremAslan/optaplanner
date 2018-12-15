package problems.tsp.benchmark;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.core.api.solver.SolverFactory;
import problems.tsp.domain.TspSolution;
import problems.tsp.persistence.TspProblemGenerator;


import java.io.File;

public class Benchmarker {

  public static void main(String[] args) {

    PlannerBenchmarkFactory plannerBenchmarkFactory = PlannerBenchmarkFactory.createFromXmlFile(new File("src/main/resources/problems/tsp/solver/PlannerBenchmarkConfig.xml"));
    PlannerBenchmark plannerBenchmark = plannerBenchmarkFactory.buildPlannerBenchmark();
    plannerBenchmark.benchmark();


  }

}

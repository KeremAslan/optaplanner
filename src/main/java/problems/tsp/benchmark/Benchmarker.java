package problems.tsp.benchmark;

import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.core.api.solver.SolverFactory;
import problems.tsp.domain.TspSolution;

import java.io.File;

public class Benchmarker {

  public static void main(String[] args) {

    SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp/solver/TspSolverConfig.xml"));
    PlannerBenchmarkFactory plannerBenchmarkFactory = PlannerBenchmarkFactory.createFromSolverFactory(solverFactory);


  }

}

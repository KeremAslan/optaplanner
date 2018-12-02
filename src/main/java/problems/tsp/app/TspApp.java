package problems.tsp.app;


import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tsp.domain.Assignment;
import problems.tsp.domain.TspSolution;
import problems.tsp.persistence.TspProblemGenerator;

import java.io.File;

public class TspApp {

    public static final int OPTIMUM_SCORE = -27603;

    public static final Logger LOGGER = LoggerFactory.getLogger(TspApp.class);

    public static void main(String[] args){
        //build solver


//        ClassLoader cl = new ClassLoader();
        SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp/solver/TspSolverConfig.xml"));
        Solver<TspSolution> solver = solverFactory.buildSolver();

        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromSolverFactory(solverFactory);


        //Load Western Sahara Dataset
        TspSolution problem = TspProblemGenerator.createTspProblem(TspProblemGenerator.Dataset.WESTERN_SAHARA);
        // TODO Load another TSP Dataset for benchmarking purposes

        TspSolution solution = solver.solve(problem);
        HardSoftScore score = solution.getScore();

        PlannerBenchmark plannerBenchmark = benchmarkFactory.buildPlannerBenchmark(solution, problem);
//        plannerBenchmark.benchmark();
        System.out.println("HardScore is " + score.getHardScore() + " soft score is "+ score.getSoftScore());

        System.out.println(solution);

        printScoreInPerc(score);

    }

    private static void printScoreInPerc(HardSoftScore score) {
        System.out.println("Score " +  String.format("%.2f",OPTIMUM_SCORE*1.0/score.getSoftScore()*1.0 * 100) + "% ");

    }
}

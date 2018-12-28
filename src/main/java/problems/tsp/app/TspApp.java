package problems.tsp.app;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.apache.commons.cli.*;
import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tsp.domain.TspSolution;
import problems.tsp.persistence.TspProblemGenerator;

import java.io.File;
import javax.xml.crypto.Data;

public class TspApp {

    public static final int OPTIMUM_SCORE_WESTERN_SAHARA = -27603;
    public static final int OPTIMUM_SCORE_ZIMBABWE = -95345;
    public static final int OPTIMUM_SCORE_URUGUAY = -79114;

    public static final Logger LOGGER = LoggerFactory.getLogger(TspApp.class);

    public static final TspProblemGenerator.Dataset DATASET = TspProblemGenerator.Dataset.URUGUAY;

    public static boolean coursera = true;

    public static void main(String[] args){

        // configure logback
        // assume SLF4J is bound to logback in the current environment
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
        StatusPrinter.print(lc);

        LOGGER.info("Starting application");


        File file = getFile(args);

        //build solver
//        SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp/solver/TspSolverConfig_v1.xml"));
        SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp/solver/TspSolverConfig_v2.xml"));

        Solver<TspSolution> solver = solverFactory.buildSolver();

        //Load Dataset
//        TspSolution problem = new TspProblemGenerator().createTspProblem(DATASET);
        TspSolution problem = new TspProblemGenerator().read(file);

        // solve
        TspSolution solution = solver.solve(problem);
        HardSoftScore score = solution.getScore();

        LOGGER.info("Best found solution is {}",  score);
        LOGGER.info("Solution {}", solution);

        printScoreInPerc(score);

    }

    public static void printScoreInPerc(HardSoftScore score) {
      int optimumScore = 0;
      if (TspProblemGenerator.Dataset.WESTERN_SAHARA == DATASET) {
        optimumScore = OPTIMUM_SCORE_WESTERN_SAHARA;
      } else if (TspProblemGenerator.Dataset.ZIMBABWE == DATASET) {
        optimumScore = OPTIMUM_SCORE_ZIMBABWE;
      } else if (TspProblemGenerator.Dataset.URUGUAY == DATASET) {
          optimumScore = OPTIMUM_SCORE_URUGUAY;
      }
      LOGGER.info("Score is of best known {}%", String.format("%.2f", optimumScore *1.0/score.getSoftScore()*1.0 * 100));
    }

    public static File getFile(String[] args) {
        Options options = new Options();
        options.addOption("f", true, "file to solve");

        CommandLineParser parser = new DefaultParser();
        String path;
        try {
            CommandLine cmd = parser.parse( options, args);
            path = cmd.getOptionValue("f");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't parse CLI argument");
        }

        if(path.isEmpty()) {
            throw new RuntimeException("No path specified");
        } else {
            return new File(path);
        }
    }
}

package problems.tsp.app;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.apache.commons.cli.*;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tsp.domain.Domicile;
import problems.tsp.domain.Standstill;
import problems.tsp.domain.TspSolution;
import problems.tsp.persistence.TspProblemGenerator;

import java.io.File;

public class TspApp {

    public static final int OPTIMUM_SCORE_WESTERN_SAHARA = -27603;
    public static final int OPTIMUM_SCORE_ZIMBABWE = -95345;
    public static final int OPTIMUM_SCORE_URUGUAY = -79114;

    public static final Logger LOGGER = LoggerFactory.getLogger(TspApp.class);

    public static final TspProblemGenerator.Dataset DATASET = TspProblemGenerator.Dataset.URUGUAY;

    public static boolean coursera = false;
    static String fileToWriteTo = null;

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
        TspProblemGenerator problemGenerator = new TspProblemGenerator();
//        TspSolution problem = new TspProblemGenerator().createTspProblem(DATASET);
        TspSolution problem = problemGenerator.read(file);

        // solve
        TspSolution solution = solver.solve(problem);
        HardSoftDoubleScore score = solution.getScore();

        Domicile domicile = solution.getDomicile();
        Standstill s = domicile.getNextVisit();
        int hardPenalties = 0;
        while (s != null) {
            if (s.getPosition() % 10 == 0 && s.getLocation().getId() % 10 != 0) {
                hardPenalties++;
            }
            s = s.getNextVisit();
        }
        LOGGER.info("# of hard penalties {}", hardPenalties);
        LOGGER.info("Best found solution is {}",  score);
        LOGGER.info("Solution {}", solution);

        if (fileToWriteTo != null) {
            problemGenerator.write(solution, new File (fileToWriteTo));
        }
        printScoreInPerc(score);

    }

    public static void printScoreInPerc(HardSoftDoubleScore score) {
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
        options.addOption("w", true, "file to write to");

        CommandLineParser parser = new DefaultParser();
        String path;
        try {
            CommandLine cmd = parser.parse( options, args);
            path = cmd.getOptionValue("f");
            if (cmd.hasOption("f")) {
                fileToWriteTo = cmd.getOptionValue("w");
            }
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

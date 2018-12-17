package problems.vrp.app;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import problems.tsp.app.TspApp;
import problems.vrp.domain.VrpSolution;
import problems.vrp.persistence.VrpProblemGenerator;

import java.io.File;

public class VrpApp {


  public static final Logger LOGGER = LoggerFactory.getLogger(VrpApp.class);


  public static void main(String[] args) {
    // configure logback
    // assume SLF4J is bound to logback in the current environment
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    // print logback's internal status
    StatusPrinter.print(lc);

    // parse input file
    String fileToRead = null;
    if (args.length > 0) {
      fileToRead = args[0];
      LOGGER.info("File to read: {}", fileToRead);
    }


    if (fileToRead != null) {

      VrpSolution vrpSolution = new VrpProblemGenerator().read(new File(fileToRead));
      SolverFactory solverFactory = SolverFactory.createFromXmlFile(new File("src/main/resources/problems/vrp/solver/vrpSolverConfig"));

      Solver<VrpSolution> solver = solverFactory.buildSolver();

      VrpSolution solved = solver.solve(vrpSolution);
      HardSoftDoubleScore score = solved.getScore();

    }

  }
}

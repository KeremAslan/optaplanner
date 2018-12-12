package problems.tsp_chaining.app;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import problems.tsp_chaining.domain.TspSolution;
import problems.tsp_chaining.persistence.TspProblemGenerator;

import java.io.File;

public class TspChainingApp {

    public static final int OPTIMUM_SCORE = -27603;

    public static void main (String[] args) {

        SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp_chaining/solver/TspSolverConfig.xml"));

        Solver<TspSolution> solver = solverFactory.buildSolver();

        TspSolution initialProblem = TspProblemGenerator.createTspProblem(TspProblemGenerator.Dataset.WESTERN_SAHARA);

        TspSolution solution = solver.solve(initialProblem);
        HardSoftScore score = solution.getScore();

        System.out.println("Score " + score);
    }
}

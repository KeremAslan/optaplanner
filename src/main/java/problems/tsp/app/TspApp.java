package problems.tsp.app;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simplebigdecimal.SimpleBigDecimalScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import problems.tsp.domain.Assignment;
import problems.tsp.domain.TspSolution;
import problems.tsp.persistence.TspProblemGenerator;

import java.io.File;

public class TspApp {

    public static void main(String[] args){
        //build solver


//        ClassLoader cl = new ClassLoader();
        SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp/solver/TspSolverConfig.xml"));
        Solver<TspSolution> solver = solverFactory.buildSolver();


        TspSolution problem = TspProblemGenerator.createTspProblem(TspProblemGenerator.Dataset.WESTERN_SAHARA);

        for(Assignment assignment: problem.getAssignments()){
            System.out.println(assignment);
        }

        TspSolution solution = solver.solve(problem);
        HardSoftScore score = solution.getScore();

        System.out.println("HardScore is " + score.getHardScore() + " soft score is "+ score.getSoftScore());
        for(Assignment assignment: solution.getAssignments()){
            System.out.println(assignment);
        }

    }
}

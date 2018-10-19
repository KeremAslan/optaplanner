package problems.tsp.app;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.io.File;

public class TspApp {

    public static void main(String[] args){
        //build solver
//        System.out.println(System.getProperty("java.class.path"));
        System.out.println(
                System.getProperty("user.dir")
        );

//        ClassLoader cl = new ClassLoader();
        SolverFactory solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/tsp/solver/TspSolverConfig.xml"));
        Solver solver = solverFactory.buildSolver();

        solverFactory.buildSolver();
//        File f = new File("src/main/resources/problems/tsp/solver/TspSolverConfig.xml");
//        System.out.println(f.getAbsolutePath());
//        SolverFactory solverFactory = SolverFactory.createFromXmlResource("main/resources/problems/tsp/solver/TspSolverConfig.xml");
//        solverFactory.

    }
}

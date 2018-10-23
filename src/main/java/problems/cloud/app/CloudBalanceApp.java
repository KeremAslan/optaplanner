package problems.cloud.app;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import problems.cloud.domain.CloudBalance;
import problems.cloud.domain.CloudComputer;
import problems.cloud.domain.CloudProcess;
import problems.cloud.persistence.CloudBalanceGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudBalanceApp {

    public static void main(String[] args) {

        SolverFactory<CloudBalance> solverFactory = SolverFactory.createFromXmlFile( new File("src/main/resources/problems/cloud/solver/CloudBalanceSolverConfig.xml"));

        Solver<CloudBalance> solver = solverFactory.buildSolver();

        CloudBalance cloudBalance = new CloudBalanceGenerator().createCloudBalance(20, 100);

        HardSoftScore initScore = cloudBalance.getScore();

        System.out.println("Initial sore is " + initScore);

        CloudBalance solution = solver.solve(cloudBalance);

        HardSoftScore score = solution.getScore();

        System.out.println("Total score is " + score);
        System.out.println("-------------------------------------------");

        Map<CloudComputer, List<CloudProcess>> grouped = groupAssignments(solution);
//        printSolution(grouped);
        System.out.println("-------------------------------------------");


//        System.out.println(solver.explainBestScore());
    }

    public static void printSolution(Map<CloudComputer, List<CloudProcess>> grouped){

        for(Map.Entry<CloudComputer, List<CloudProcess>> entry: grouped.entrySet()){
            System.out.println("Computer " + entry.getKey());
            int consumedCPUPower = 0;
            int consumedRAM = 0;
            int consumedNetworkBandwidth = 0;
            for(CloudProcess cloudProcess: entry.getValue()){
                System.out.println("\t" + cloudProcess);
                consumedCPUPower += cloudProcess.getRequiredCpuPower();
                consumedRAM += cloudProcess.getRequiredMemory();
                consumedNetworkBandwidth += cloudProcess.getRequiredNetworkBandwidth();
            }
            System.out.println("Total CPU consumed: " + consumedCPUPower +
                    " Total RAM consumed "+ consumedRAM +
                    " Total networkbandwidth consumed " + consumedNetworkBandwidth);
        }
    }


    public static Map<CloudComputer, List<CloudProcess>> groupAssignments(CloudBalance cloudBalance){
        List<CloudComputer> computerList = cloudBalance.getComputerList();
        List<CloudProcess> processList = cloudBalance.getProcessList();
        Map<CloudComputer, List<CloudProcess>> assignments = new HashMap<CloudComputer, List<CloudProcess>>();
        for(CloudProcess process : processList){
            CloudComputer computer = process.getComputer();
            if(assignments.containsKey(computer)){
                assignments.get(computer).add(process);
            } else {
                List<CloudProcess> processes = new ArrayList<CloudProcess>();
                processes.add(process);
                assignments.put(computer, processes);
            }
        }

        return assignments;


    }
}

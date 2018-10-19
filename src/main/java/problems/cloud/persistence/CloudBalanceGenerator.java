package problems.cloud.persistence;

import problems.cloud.domain.CloudBalance;
import problems.cloud.domain.CloudComputer;
import problems.cloud.domain.CloudProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CloudBalanceGenerator {


    public CloudBalance createCloudBalance(int computerListSize, int processListSize){
        List<CloudComputer> computerList = new ArrayList<CloudComputer>();
        List<CloudProcess> processList = new ArrayList<CloudProcess>();


        Random rand = new Random();
        rand.setSeed(40);

        int maxCost = 7;
        int minCost = 2;
        int maxComputerCPUPower = 50;
        int minComputerCPUPower = 30;
        int maxComputerMemory = 128;
        int maxNetworkBandWith = 500;
        for(int i=0; i < computerListSize; i++){
            CloudComputer computer = new CloudComputer( (i+1),
                    rand.nextInt(maxComputerCPUPower)+minComputerCPUPower,
                    maxComputerMemory,
                    maxNetworkBandWith,
                    rand.nextInt(maxCost)+minCost );
            computerList.add(computer);
        }

        int maxCPUPower = 4;
        int maxMemory = 32;
        int networkBandwithMax = 5;
        for(int i=0; i < processListSize; i++){
            CloudProcess process = new CloudProcess(i+1,
                    rand.nextInt(maxCPUPower)+1,
                    rand.nextInt(maxMemory)+1,
                    rand.nextInt(networkBandwithMax)+1
                    );
            processList.add(process);
        }

        CloudBalance cloudBalance = new CloudBalance(computerList, processList);
        return cloudBalance;
    }
}

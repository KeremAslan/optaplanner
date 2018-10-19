package problems.cloud.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.cloud.domain.CloudBalance;
import problems.cloud.domain.CloudComputer;
import problems.cloud.domain.CloudProcess;

public class EasyCalculator implements EasyScoreCalculator<CloudBalance> {

    public Score calculateScore(CloudBalance cloudBalance) {


        int hardScore = 0;
        int softScore = 0;

        for(CloudComputer cloudComputer: cloudBalance.getComputerList()){
            int cpuPowerUsage = 0;
            int memoryUsage = 0;
            int networkBandwidthUsage = 0;
            boolean used = false;

            //calculate usage
            for(CloudProcess cloudProcess: cloudBalance.getProcessList()){
                if(cloudComputer.equals(cloudProcess.getComputer())){
                    cpuPowerUsage += cloudProcess.getRequiredCpuPower();
                    memoryUsage += cloudProcess.getRequiredMemory();
                    networkBandwidthUsage += cloudProcess.getRequiredNetworkBandwidth();
                    used = true;
                }
            }

            //Hard constraints
            int cpuPowerAvailable = cloudComputer.getCpuPower() - cpuPowerUsage;
            if (cpuPowerAvailable < 0) {
                hardScore += cpuPowerAvailable;
            }

            int memoryAvailable = cloudComputer.getMemory() - memoryUsage;
            if (memoryAvailable < 0) {
                hardScore += memoryAvailable;
            }
            int networkBandwidthAvailable = cloudComputer.getNetworkBandwidth() - networkBandwidthUsage;
            if (networkBandwidthAvailable < 0) {
                hardScore += networkBandwidthAvailable;
            }


            // Soft constraints
            if (used) {
                softScore -= cloudComputer.getCost();
            }
        }
        return HardSoftScore.valueOf(hardScore, softScore);
    }
}

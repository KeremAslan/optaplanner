package problems.cloud.score;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import problems.cloud.domain.CloudBalance;
import problems.cloud.domain.CloudComputer;
import problems.cloud.domain.CloudProcess;

public class SimpleScore implements EasyScoreCalculator<CloudBalance> {

    public Score calculateScore(CloudBalance cloudBalance) {

            int softScore = 0;

            for (CloudComputer cloudComputer : cloudBalance.getComputerList()) {
                int cpuPowerUsage = 0;
                int memoryUsage = 0;
                int networkBandwidthUsage = 0;
                boolean used = false;

                //calculate usage
                for (CloudProcess cloudProcess : cloudBalance.getProcessList()) {
                    if (cloudComputer.equals(cloudProcess.getComputer())) {
                        used = true;
                    }
                }

                // Soft constraints
                if (used) {
                    softScore -= cloudComputer.getCost();
                }
            }
            return org.optaplanner.core.api.score.buildin.simple.SimpleScore.valueOf(softScore);

    }
}

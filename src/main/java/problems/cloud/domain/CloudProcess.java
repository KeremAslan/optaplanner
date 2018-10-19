package problems.cloud.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = CloudProcessDifficultyComparison.class)
public class CloudProcess {

    private long id;
    private int requiredCpuPower;
    private int requiredMemory;
    private int requiredNetworkBandwith;

    private CloudComputer computer;

    public CloudProcess(){

    }

    public CloudProcess(long id, int requiredCpuPower, int requiredMemory, int requiredNetworkBandwith){
        this.id = id;
        this.requiredCpuPower = requiredCpuPower;
        this.requiredMemory = requiredMemory;
        this.requiredNetworkBandwith = requiredNetworkBandwith;

    }

    public int getRequiredCpuPower() {
        return requiredCpuPower;
    }

      public int getRequiredMemory() {
        return requiredMemory;
    }

    public int getRequiredNetworkBandwidth() {
        return requiredNetworkBandwith;
    }

    @PlanningVariable(valueRangeProviderRefs = {"computerRange"})
    public CloudComputer getComputer() {
        return computer;
    }


    public void setComputer(CloudComputer computer) {
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "CloudProcess{" +
                "id=" + id +
                ", requiredCpuPower=" + requiredCpuPower +
                ", requiredMemory=" + requiredMemory +
                ", requiredNetworkBandwith=" + requiredNetworkBandwith +
                ", computer=" + computer +
                '}';
    }
}

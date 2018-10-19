package problems.cloud.domain;


import org.optaplanner.core.api.domain.entity.PlanningEntity;

//@PlanningEntity
public class CloudComputer {

    private int id;
    private int cpuPower;
    private int memory;
    private int networkBandwidth;
    private int cost;

    public CloudComputer(int id, int cpuPower, int memory, int networkBandwidth, int cost){
//        super(id);
        this.id = id;
        this.cpuPower = cpuPower;
        this.memory = memory;
        this.networkBandwidth = networkBandwidth;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }


    public int getCpuPower() {
        return cpuPower;
    }


    public int getMemory() {
        return memory;
    }


    public int getNetworkBandwidth() {
        return networkBandwidth;
    }


    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "CloudComputer{" +
                "id=" + id +
                ", cpuPower=" + cpuPower +
                ", memory=" + memory +
                ", networkBandwidth=" + networkBandwidth +
                ", cost=" + cost +
                '}';
    }
}

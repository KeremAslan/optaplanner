package problems.cloud.domain;


import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import problems.cloud.score.SimpleScore;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.List;

@PlanningSolution
public class CloudBalance {

    private List<CloudComputer> computerList;
    private List<CloudProcess> processList;
    private HardSoftScore score;

//    private SimpleScore score;

    public CloudBalance(){

    }

    public CloudBalance(List<CloudComputer> computerList, List<CloudProcess> processList){
        this.computerList = computerList;
        this.processList = processList;
    }

    @ValueRangeProvider(id ="computerRange")
    @ProblemFactCollectionProperty
    public List<CloudComputer> getComputerList() {
        return computerList;
    }

    @PlanningEntityCollectionProperty
    public List<CloudProcess> getProcessList() {
        return processList;
    }

    public void setScore(HardSoftScore score){
        this.score = score;
    }


    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }
}

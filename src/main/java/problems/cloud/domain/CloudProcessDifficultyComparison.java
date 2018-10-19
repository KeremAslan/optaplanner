package problems.cloud.domain;

import java.util.Comparator;

public class CloudProcessDifficultyComparison implements Comparator<CloudProcess> {

    public int compare(CloudProcess o1, CloudProcess o2) {
        if(o1.getRequiredMemory() > o2.getRequiredMemory()){
            return 1;
        } else if (o1.getRequiredMemory() < o2.getRequiredMemory()){
            return -1;
        } else {
            return 0;
        }
    }
}

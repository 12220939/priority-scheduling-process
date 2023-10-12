import java.util.PriorityQueue;

class ProcessEntity implements Comparable<ProcessEntity> {
    private int processId;
    private String processName;
    private int priority;

    public ProcessEntity(int processId, String processName, int priority) {
        this.processId = processId;
        this.processName = processName;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(ProcessEntity other) {
        // Higher priority processes come first
        return Integer.compare(other.getPriority(), this.priority);
    }


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    int processID;
    String processName;
    int priority;

    public Process(int processID, String processName, int priority) {
        this.processID = processID;
        this.processName = processName;
        this.priority = priority;
    }
}

class PriorityQueue {
    private List<Process> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
        heap.add(null);
    }

    public void insert(Process process) {
        heap.add(process);
        int currentIndex = heap.size() - 1;

        while (currentIndex > 1) {
            int parentIndex = currentIndex / 2;
            if (heap.get(currentIndex).priority < heap.get(parentIndex).priority) {
                Process temp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public Process dequeue() {
        if (heap.size() <= 1) {
            return null; 
        }

        Process minPriorityProcess = heap.get(1);

        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int currentIndex = 1;
        while (true) {
            int leftChildIndex = 2 * currentIndex;
            int rightChildIndex = 2 * currentIndex + 1;
            int smallestIndex = currentIndex;

            if (leftChildIndex < heap.size() && heap.get(leftChildIndex).priority < heap.get(currentIndex).priority) {
                smallestIndex = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && heap.get(rightChildIndex).priority < heap.get(smallestIndex).priority) {
                smallestIndex = rightChildIndex;
            }

            if (smallestIndex != currentIndex) {
                Process temp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(smallestIndex));
                heap.set(smallestIndex, temp);
                currentIndex = smallestIndex;
            } else {
                break;
            }
        }

        return minPriorityProcess;
    }

    public boolean isEmpty() {
        return heap.size() <= 1;
    }

    public int size() {
        return heap.size() - 1;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of process you want to create : ");
        int processess = sc.nextInt();

        for (int i = 0; i < processess; i++) {
            System.out.print("Enter the process ID (in numbers): ");
            int processID = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter the process name (in String/words): ");
            String processName = sc.nextLine();
            System.out.print("Enter the process Priority (in numbers): ");
            int processPriority = sc.nextInt();
            priorityQueue.insert(new Process(processID, processName, processPriority));
        }

        while (!priorityQueue.isEmpty()) {
            Process process = priorityQueue.dequeue();
            System.out.println("Dequeued: " + process.processName + " (Priority: " + process.priority + ")");
        }

        sc.close();
    }
}


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PatienceSort {

    public static void main(String[] args) {
    }
    static int patienceSort(int[] array){
            List<Stack<Integer>> stacks = new ArrayList<>();
            int iterationCount = 0;
            for (int i : array) {
                boolean elem_add = false;
                for (Stack<Integer> stack : stacks) {
                    if (i < stack.peek()) {
                        stack.push(i);
                        elem_add = true;
                        iterationCount++;
                        break;
                    }
                }
                if (!elem_add) {
                    Stack<Integer> newDeck = new Stack<>();
                    newDeck.push(i);
                    stacks.add(newDeck);
                }
            }

            int[] sortedArray = new int[array.length];
            int index = 0;

            while (!stacks.isEmpty()) {
                int smallestIndex = 0;
                for (int i = 1; i < stacks.size(); i++) {
                    if (stacks.get(i).peek() < stacks.get(smallestIndex).peek()) {
                        smallestIndex = i;
                        iterationCount++;
                    }
                }
                sortedArray[index] = stacks.get(smallestIndex).pop();
                index++;
                if (stacks.get(smallestIndex).empty()) {
                    stacks.remove(smallestIndex);
                }
            }
            return iterationCount;
    }
}

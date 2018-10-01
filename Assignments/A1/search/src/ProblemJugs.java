import java.util.HashSet;
import java.util.Set;

public class ProblemJugs extends Problem {

    static final int jug12 = 0;
    static final int jug8 = 1;
    static final int jug3 = 2;
//    static final int floor = 3;

    boolean goal_test(Object state) {
        StateJugs jug_state = (StateJugs) state;

        if (jug_state.jugsArray[jug3] == 1 || jug_state.jugsArray[jug8] == 1 || jug_state.jugsArray[jug12] == 1)
            return true;
        else return false;
    }

    Set<Object> getSuccessors(Object state) {

        Set<Object> set = new HashSet<Object>();
        StateJugs jug_state = (StateJugs) state;

        //Let's create without any constraint, then remove the illegal ones
        StateJugs successor_state;

        //Fill 12 gallon jug
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 12;
        successor_state.jugsArray[jug8] += 0;
        successor_state.jugsArray[jug3] += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //Fill 8 gallon jug
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 0;
        successor_state.jugsArray[jug8] += 8;
        successor_state.jugsArray[jug3] += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //Fill 3 gallon jug
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 0;
        successor_state.jugsArray[jug8] += 0;
        successor_state.jugsArray[jug3] += 3;
        if (isValid(successor_state)) set.add(successor_state);

        //Move 12 to 8
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] -= 8;
        successor_state.jugsArray[jug8] += 8;
        successor_state.jugsArray[jug3] += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //Move 12 to 3
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] -= 3;
        successor_state.jugsArray[jug8] += 0;
        successor_state.jugsArray[jug3] += 3;
        if (isValid(successor_state)) set.add(successor_state);

        //Move 8 to 12
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 8;
        successor_state.jugsArray[jug8] -= 8;
        successor_state.jugsArray[jug3] += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //Move 8 to 3
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 0;
        successor_state.jugsArray[jug8] -= 3;
        successor_state.jugsArray[jug3] += 3;
        if (isValid(successor_state)) set.add(successor_state);

        //Move 3 to 12
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 3;
        successor_state.jugsArray[jug8] += 0;
        successor_state.jugsArray[jug3] -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //Move 3 to 8
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 0;
        successor_state.jugsArray[jug8] += 3;
        successor_state.jugsArray[jug3] -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //Pour out 12
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] -= 12;
        successor_state.jugsArray[jug8] += 0;
        successor_state.jugsArray[jug3] += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //Pour out 8
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 0;
        successor_state.jugsArray[jug8] -= 8;
        successor_state.jugsArray[jug3] += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //Pour out 3
        successor_state = new StateJugs(jug_state);
        successor_state.jugsArray[jug12] += 0;
        successor_state.jugsArray[jug8] += 0;
        successor_state.jugsArray[jug3] -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        return set;
    }

    private boolean isValid(StateJugs state)
    {
        //Checking to see if any element of the array is negative
        for (int i=0; i<6; i++)
            if (state.jugsArray[i] < 0) return false;

        //Checking to see if the numbers of jugnibals, missionaries, and boat
        //are more then 3,3,1 respectively
        if (goal_test(state)){
            return true;
        }
        //Now, checking if amount of water in jugs are overfilled
        if(state.jugsArray[jug12] > 12 || state.jugsArray[jug8] > 8 || state.jugsArray[3] > 3){
            return false;
        }

        return true;
    }

    double step_cost(Object fromState, Object toState) { return 1; }

    public double h(Object state) { return 0; }


    public static void main(String[] args) throws Exception {
        ProblemJugs problem = new ProblemJugs();
        int[] jugsArray = {0,0,0,0,0,0};
        problem.initialState = new StateJugs(jugsArray);

        Search search  = new Search(problem);

        //Tree search methods
        System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
        System.out.println("DepthFirstTreeSearch:\t" + search.DepthFirstTreeSearch());
        System.out.println("UniformCostTreeSearch:\t" + search.UniformCostTreeSearch());
        System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
        System.out.println("AstarTreeSearch:\t" + search.AstarTreeSearch());
        System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());

        //Graph search methods
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t" + search.UniformCostGraphSearch());
        System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("AstarGraphSearch:\t" + search.AstarGraphSearch());
        System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
    }
}

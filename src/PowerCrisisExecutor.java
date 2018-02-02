import java.util.ArrayList;
import java.util.List;

public class PowerCrisisExecutor {

    private final static int N = 17;
    private final static int m = 5;
    private final static int REGION_13 = 13;
    private ArrayList<Integer> regions;
    private ArrayList<Integer> turnedOffRegions;
    private int counter = 0;
    private boolean validSeries = false;
    private int smallestNumber = 0;

    private void init() {
        regions = getRegions();
        turnedOffRegions = new ArrayList<>();
    }

    private ArrayList<Integer> getRegions() {
        regions = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            regions.add(i);
        }
        return regions;
    }

    private void readArray(List<?> array) {
        if (array != null && !array.isEmpty()) {
            System.out.println(array.toString());
        }
    }

    private void buildSeries(int limit) {
        for (int i = 0; i < limit; i++) {
            if (turnedOffRegions.isEmpty()) {
                addRegion(i);
            } else {
                counter++;
                if (counter == m) {
                    addRegion(i);
                }
            }
        }

//        readArray(turnedOffRegions);

        if (turnedOffRegions.size() != N) {
            regions.removeAll(turnedOffRegions);
            buildSeries(regions.size());
        } else {
            readArray(turnedOffRegions);
            int lastIndex = turnedOffRegions.size() - 1;
            int lastRegion = turnedOffRegions.get(lastIndex);
            if (lastRegion == REGION_13) {
                System.out.println("Random number valid because the las region turned off is: " + REGION_13);
            } else {
                System.out.println("Random number invalid because the las region turned off is: " + lastRegion);
            }
        }
    }

    private void addRegion(int i) {
        int regionSelected;
        regionSelected = regions.get(i);
        turnedOffRegions.add(regionSelected);
        counter = 0;
    }

    // TODO
    private void calculateSmallestNumber() {
        int index = 1;
        while (index < N && !validSeries) {
            init();
            buildSeries3(N, index);

            index++;
        }

        System.out.println("!!!THE RANDOM NUMBER THAT MEETS THE CONDITIONS IS: " + smallestNumber);
    }

    private void buildSeries3(int limit, int randomNumber) {

        for (int i = 0; i < limit; i++) {
            if (turnedOffRegions.isEmpty()) {
                addRegion(i);
            } else {
                counter++;
                if (counter == randomNumber) {
                    addRegion(i);
                }
            }
        }

//        readArray(turnedOffRegions);

        if (turnedOffRegions.size() != N) {
            regions.removeAll(turnedOffRegions);
            buildSeries3(regions.size(), randomNumber);
        } else {
            readArray(turnedOffRegions);
            int lastIndex = turnedOffRegions.size() - 1;
            int lastRegion = turnedOffRegions.get(lastIndex);
            if (lastRegion == REGION_13) {
                smallestNumber = randomNumber;
                validSeries = true;
                System.out.println(smallestNumber + " Is the Random number valid because the last region turned off is: " + REGION_13);
            } else {
                validSeries = false;
                System.out.println("Random number invalid because the last region turned off is: " + lastRegion);
            }
        }
    }

    public static void main(String[] args) {
        PowerCrisisExecutor powerCrisisExecutor = new PowerCrisisExecutor();
        powerCrisisExecutor.init();
//        powerCrisisExecutor.buildSeries(N);

        powerCrisisExecutor.calculateSmallestNumber();
    }

//:1,6,11,16,5,12,2,9,17,10,4,15,14,3,8,13,7
}

import java.util.ArrayList;
import java.util.Scanner;

public class PowerCrisisExecutor {
    private final static int REGION_13 = 13;
    private ArrayList<Integer> regions;
    private ArrayList<Integer> turnedOffRegions;
    private int N;
    private int counter = 0;
    private boolean validSeries = false;


    private void init(int N) {
        regions = getRegions(N);
        turnedOffRegions = new ArrayList<>();
    }

    private ArrayList<Integer> getRegions(int N) {
        regions = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            regions.add(i);
        }
        return regions;
    }

    private void addRegion(int i) {
        int regionSelected;
        regionSelected = regions.get(i);
        turnedOffRegions.add(regionSelected);
        counter = 0;
    }

    private void calculateSmallestNumber() {
        int index = 1;
        while (index < N && N >= REGION_13 && N < 100 && !validSeries) {
            init(N);
            buildSeries(N, index);

            if (!validSeries) {
                index++;
            }
        }
        validSeries = false;
        System.out.println(index);
    }

    private void buildSeries(int limit, int m) {

        getNextRegion(limit, m);

        if (turnedOffRegions.size() != N) {
            regions.removeAll(turnedOffRegions);
            buildSeries(regions.size(), m);
        } else {
            int lastIndex = turnedOffRegions.size() - 1;
            int lastRegion = turnedOffRegions.get(lastIndex);
            validSeries = lastRegion == REGION_13;
        }
    }

    private void getNextRegion(int limit, int m) {
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
    }

    private void start() {
        readRegions();
        calculateSmallestNumber();

        if (N > 0) {
            start();
        }
    }

    private void readRegions() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        if (N == 0) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        PowerCrisisExecutor powerCrisisExecutor = new PowerCrisisExecutor();
        powerCrisisExecutor.start();
    }
}

import java.util.ArrayList;
import java.util.List;

public class PowerCrisisExecutor {

    private final static int N = 17;
    private int m;
    private int lastRegion;
    private ArrayList<Integer> regions;
    private ArrayList<Integer> turnedOffRegions;
    private int counter = 0;

    private void init() {
        m = 7;
        lastRegion = 13;
        regions = getRegions();
        turnedOffRegions = new ArrayList<>();
    }

    private ArrayList<Integer> getRegions() {
        regions = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            regions.add(i);
        }
        readArray(regions);
        return regions;
    }

    private void readArray(List<?> array) {
        if (array != null && !array.isEmpty()) {
            System.out.println(array.toString());
        }
    }

    private void buildSeries(int limit) {
        int regionSelected;

        for (int i = 0; i < limit; i++) {
            if (turnedOffRegions.isEmpty()) {
                regionSelected = regions.get(i);
                turnedOffRegions.add(regionSelected);
                counter = 0;
            } else {
                counter++;
                if (counter == m) {
                    regionSelected = regions.get(i);
                    turnedOffRegions.add(regionSelected);
                    counter = 0;
                }
            }
        }

        readArray(turnedOffRegions);

        if (turnedOffRegions.size() != N) {
            regions.removeAll(turnedOffRegions);
            buildSeries(regions.size());
        }
    }

    public static void main(String[] args) {
        PowerCrisisExecutor powerCrisisExecutor = new PowerCrisisExecutor();
        powerCrisisExecutor.init();
        powerCrisisExecutor.buildSeries(N);
    }

//:1,6,11,16,5,12,2,9,17,10,4,15,14,3,8,13,7
}

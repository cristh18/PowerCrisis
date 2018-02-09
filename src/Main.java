import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class PowerCrisisExecutor {
    private final static int REGION_13 = 13;
    private static ArrayList<Integer> regions;
    private static ArrayList<Integer> turnedOffRegions;
    private static int N = -1;
    private static int counter = 0;
    private static boolean validSeries = false;


    private static void init(int N) {
        regions = getRegions(N);
        turnedOffRegions = new ArrayList<>();
    }

    private static ArrayList<Integer> getRegions(int N) {
        regions = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            regions.add(i);
        }
        return regions;
    }

    private static void addRegion(int i) {
        int regionSelected;
        regionSelected = regions.get(i);
        turnedOffRegions.add(regionSelected);
        counter = 0;
    }

    private static void calculateSmallestNumber() {
        int index = 1;
        if (N < 100 && N >= 13) {
            while (!validSeries) {
                init(N);
                buildSeries(index);

                if (!validSeries) {
                    index++;
                }
            }
            validSeries = false;
            System.out.println(index);
        }
    }


    private static void buildSeries(int m) {

        while (turnedOffRegions.size() < N) {
            getNextRegion(regions.size(), m);
            regions.removeAll(turnedOffRegions);
        }

        int lastIndex = turnedOffRegions.size() - 1;
        int lastRegion = turnedOffRegions.get(lastIndex);
        validSeries = lastRegion == REGION_13;

    }

    private static void getNextRegion(int limit, int m) {
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

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            calculateSmallestNumber();
        }
    }
}


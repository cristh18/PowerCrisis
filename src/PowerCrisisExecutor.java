import java.io.IOException;
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

//    private static void calculateSmallestNumber() {
//        int index = 1;
//        if (N < 100 && N >= 13) {
//            while (index < 240 && !validSeries) {
//                init(N);
//                buildSeries(N, index);
//
//                if (!validSeries) {
//                    index++;
//                }
//            }
//            validSeries = false;
//            System.out.println(index);
//        }
//    }

    private static void calculateSmallestNumber() {
        int index = 1;
        if (N < 100 && N >= 13) {
            while (!validSeries) {
                init(N);
                otherCode(index);

                if (!validSeries) {
                    index++;
                }
            }
            validSeries = false;
            System.out.println(index);
        }
    }


    private static void buildSeries(int limit, int m) {

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


    private static void otherCode(int m) {

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

    private static void start() throws IOException, NumberFormatException {
//        while (N != 0) {
//            Scanner sc = new Scanner(System.in);
//            if (sc.hasNextInt()) {
//                N = sc.nextInt();
//                calculateSmallestNumber();
//            }
//        }


        String number = "18,58,74,61,87,51,50,82,57,56,99,30,34,97,81,41,24,17,52,73,42,54,37,84,88,14,90,70,80,48,96,19,39,47,13,98,76,68,53,35,62,91,65,46,28,95,93,16,64,44,79,21,72,94,22,40,29,86,60,77,36,75,92,38,55,78,26,67,20,49,43,15,63,31,45,33,89,71,66,69,32,27,23,83,59,25,85,0";

        String[] arrayNumber = number.split(",");

        for (String anArrayNumber : arrayNumber) {
            N = Integer.parseInt(anArrayNumber);
            calculateSmallestNumber();
        }

//        N = 57;
//        calculateSmallestNumber();
    }

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class UnfairLottery {
    public static void main(String[] args) {
        String prizesInput;
        String names;
        Integer averagePrize, totalPrize = 0;
        ArrayList<Integer[]> prizesOutput = new ArrayList<Integer[]>();
        ArrayList<Integer> sumOfPrizesArray = new ArrayList<Integer>();
        ArrayList<Integer> prizesList = new ArrayList<>();
        String delimiters = "[,]";
        String[] namesArray;


//        prompt user to enter prizes and contestants
        System.out.println("Welcome to the Unfair Lottery\nPlease enter the prizes separated by a comma:");
        Scanner scanner = new Scanner(System.in);
        prizesInput = scanner.nextLine();
//        prizesInput = " 1100, 700, 300, 70,1000, 100, 700, 250, 80,900, 200, 600, 300, 100, 30,800, 300, 600, 300, 100, 20,800, 300, 400, 400, 120, 50,750, 350, 400, 400, 100, 50";
        System.out.println("Enter the contestants names separated by a comma:");
        names = scanner.nextLine();
//        names = "One,Two,Three,Four,Five,Six,Seven";
        scanner.close();


//        System.out.println(prizesInput);
//        System.out.println(names);

//        remove whitespace and create array of names and prizes
        namesArray = names.replaceAll("\\s", "").split(delimiters);


        String[] tempPrizesArray = prizesInput.replaceAll("\\s", "").split(delimiters);
        int[] prizesArray = StringArrToIntArr(tempPrizesArray);
//        get sum of prizes
        for (int prize : prizesArray) {
            prizesList.add(prize);
            totalPrize += prize;
        }
//        get average of prizes
        averagePrize = totalPrize / namesArray.length;


        //first round distribution
//        sort prizelist in descending order to assign the highest values first
        Collections.sort(prizesList, Collections.reverseOrder());
        for (int i = 0; i < namesArray.length; i++) {
//        create placeholder for prize distribution
            prizesOutput.add(new Integer[]{prizesList.get(0)});
//            placeholder for sum of prizes
            sumOfPrizesArray.add(new Integer(0));
//            remove assigned prize from prizelist
            prizesList.remove(0);
        }
//
//                final round of distribution
        while (prizesList.size() > 0) {
//            get an array  with sum of prizes for each winner
            sumOfPrizesArray = populateSumofPrizesArray(prizesOutput, prizesList, sumOfPrizesArray);
//           get the winner with the least prize
            int minValue = Collections.min(sumOfPrizesArray);
            int indexOfMin = sumOfPrizesArray.indexOf(minValue);
//            Assign the largest prize to the winner with the least prize
            Integer[] toSet = Arrays.copyOf(prizesOutput.get(indexOfMin), prizesOutput.get(indexOfMin).length + 1);
            toSet[prizesOutput.get(indexOfMin).length] = prizesList.get(0);
            prizesOutput.set(indexOfMin, toSet);
            prizesList.remove(0);
        }


//        Display winners and their prizes
        for (int i = 0; i < prizesOutput.size(); i++) {
            System.out.print(namesArray[i] + ": ");
            for (int j = 0; j < prizesOutput.get(i).length; j++)
                System.out.print("$" + prizesOutput.get(i)[j] + " ");

            System.out.println();
        }
//end of main
    }

    public static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }

    public static Integer calculateTmpSum(ArrayList<Integer[]> items, int currentItemArray, Integer currentPrice) {
        int sum = currentPrice;
        for (int j = 0; j < items.get(currentItemArray).length; j++) {
            sum += items.get(currentItemArray)[j];
        }

        return sum;
    }

    public static ArrayList<Integer> populateSumofPrizesArray(ArrayList<Integer[]> prizesOutput, ArrayList<Integer> prizesList, ArrayList<Integer> sumOfPrizesArray) {
        Integer sumOfPrizes;
        for (int i = 0; i < prizesOutput.size() && prizesList.size() > 0; i++) {
            for (int j = 0; j < prizesOutput.get(i).length; j++) {
                sumOfPrizes = calculateTmpSum(prizesOutput, i, 0);
                sumOfPrizesArray.set(i, sumOfPrizes);
            }
        }
        return sumOfPrizesArray;
    }
}
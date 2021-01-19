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
        ArrayList<Integer> prizesList = new ArrayList<>();
        String delimiters = "[,]";
        String[] namesArray;


//        prompt user to enter prizes and contestants
        System.out.println("Welcome to the Unfair Lottery\nPlease enter the prizes separated by a comma:");
        Scanner scanner = new Scanner(System.in);
        prizesInput = scanner.nextLine();
//        prizesInput = "100,800,200,500,400,1000 ";
        System.out.println("Enter the contestants names separated by a comma:");
        names = scanner.nextLine();
//        names = "Joshua,Mahesh,Lilian";
        scanner.close();


//        System.out.println(prizesInput);
//        System.out.println(names);

//        remove whitespace and create array of names and prizes
        namesArray = names.replaceAll("\\s", "").split(delimiters);


        String[] tempPrizesArray = prizesInput.replaceAll("\\s", "").split(delimiters);
        int[] prizesArray = StringArrToIntArr(tempPrizesArray);
        for (int prize : prizesArray) {
            prizesList.add(prize);
            totalPrize += prize;
        }

        for (int prize : prizesList) {
            System.out.print(prize + " ");
        }


        averagePrize = totalPrize / namesArray.length;

        System.out.println("\n" + averagePrize + " -ave prize\n" + namesArray.length + " :num of pple");

        //first round distribution
//        sort prizelist in descending order to assign the highest values first
        Collections.sort(prizesList, Collections.reverseOrder());
        for (int i = 0; i < namesArray.length; i++) {
//        create placeholder for prize distribution
            prizesOutput.add(new Integer[]{prizesList.get(0)});
//            remove assigned prize from prizelist
            prizesList.remove(0);
        }
//        second round of distribution
//        check whether prizes remain
        while (prizesList.size() > 0) {
//            ensure sum of prize will be less than or equal to average
            Integer lastSum = 0;
            for (int i = 0; i < prizesOutput.size(); i++) {
                Integer tmpSum = 0;
                for (int j = 0; j < prizesOutput.get(i).length; j++) {
                    tmpSum = prizesOutput.get(i)[j] + prizesList.get(0);
                    if (tmpSum <= averagePrize) {

                        Integer[] toSet = Arrays.copyOf(prizesOutput.get(i), prizesOutput.get(i).length + 1);
                        toSet[prizesOutput.get(i).length] = prizesList.get(0);
                        prizesOutput.set(i, toSet);
                        prizesList.remove(0);
//                        System.out.print(" $ " + prizesOutput.get(i)[j]);
                        break;
                    }
                }

                System.out.println();
            }
        }


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

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by nadman on 19.03.16.
 */
public class InversionsWithMerge {
    private static int[][] inputtedArray;
    private static int countUsers;
    private static int countFilms;
    private static int aimUser;

    public static int getAimUser() {
        return aimUser;
    }

    public static void setAimUser(int aimUser) {
        InversionsWithMerge.aimUser = aimUser;
    }

    public static int[][] getInputtedArray() {
        return inputtedArray;
    }

    public static void setInputtedArray(int[][] inputtedArray) {
        InversionsWithMerge.inputtedArray = inputtedArray;
    }

    public static int getCountUsers() {
        return countUsers;
    }

    public static void setCountUsers(int countUsers) {
        InversionsWithMerge.countUsers = countUsers;
    }

    public static int getCountFilms() {
        return countFilms;
    }

    public static void setCountFilms(int countFilms) {
        InversionsWithMerge.countFilms = countFilms;
    }

    public static void main(String[] args) {

        Scanner read = null;
        try {
            read = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert read != null;
        setCountUsers(read.nextInt());

        setCountFilms(read.nextInt());

        setAimUser(Integer.parseInt(args[1]));

        setInputtedArray(readIntArrayFromFile(read, getCountUsers(), getCountFilms()));

        printArray();

        setInputtedArray(convertInArray());

        System.out.println();

        printArray();

        System.out.println("Aim user : " + getAimUser());
    }

    private static int[][] readIntArrayFromFile(Scanner read, int rows, int columns){
        int returnArray[][];

        returnArray = new int[rows][columns + 1];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns + 1; j++)
                returnArray[i][j] = read.nextInt();
        return returnArray;
    }

    private static void printArray(){
        for (int i = 0; i < getCountUsers(); i++){
            for (int j = 0; j < getCountFilms() + 1; j++){
                System.out.print(getInputtedArray()[i][j] + " ");
            }
            System.out.println();
        }
    }

    //n^3
    private static int[][] convertInArray(){
        int [][] returnArray = getInputtedArray();
        int [] aimArray = new int[getCountFilms()];

        System.arraycopy(returnArray[getAimUser() - 1], 1, aimArray, 0, getCountFilms() + 1 - 1);

        for (int i = 0; i < getCountUsers(); i ++)
            for (int j = 1; j < getCountFilms() + 1; j++)
                for (int key = 0; key < getCountFilms(); key++)
                    if (returnArray[i][j] == aimArray[key]) {
                        returnArray[i][j] = key + 1;
                        break;
                    }
        return returnArray;
    }

    private void merge(int []A, int p, int q, int r){
        int n1 = q - p + 1, n2 = r - q;
        int L[] = new int[n1 + 1], R[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++)
            L[i] = A[p + i];

        for (int i = 0; i < n2; i++)
            R[i] = A[q + i + 1];

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;

        for(int k = p; k <= r; k++){
            if(L[i] <= R[j]){
                A[k] = L[i];
                i++;
            }
            else {
                A[k] = R[j];
                j++;
            }
        }
    }

    public void mergeSort(int []A, int left, int right){
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(A, left, middle);
            mergeSort(A, middle + 1, right);
            merge(A, left, middle, right);
        }
    }

}
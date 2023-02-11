package tp_securite_info;


import java.util.Scanner;

public class Algo_generateurCle {
    public static int[] generateKeys(int K, int H, int shiftLeft, int shiftRight) {
        int k1_ = K & 240;
        int k2_ = K & 15;
        int k1 = (k1_ ^ k2_) << shiftLeft;
        int k2 = (k2_ & k1_) >> shiftRight;
        return new int[] {k1, k2};
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrer la cles K (8 bits): ");
            int K = scanner.nextInt();
            System.out.print("Entrer la permutation H: ");
            int H = scanner.nextInt();
            System.out.print("Entrer le decalage a gauche: ");
            int shiftLeft = scanner.nextInt();
            System.out.print("Entrer le decalage a droite: ");
            int shiftRight = scanner.nextInt();
            int[] keys = generateKeys(K, H, shiftLeft, shiftRight);
            System.out.println("k1: " + Integer.toBinaryString(keys[0]));
            System.out.println("k2: " + Integer.toBinaryString(keys[1]));
        }
    }
}

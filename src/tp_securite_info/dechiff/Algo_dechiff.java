
package tp_securite_info.dechiff;

class Algo_dechiff {
  // Déclaration et initialisation des constantes k1 et k2
  private static final int k1 = 0b1010;
  private static final int k2 = 0b1100;
  
  // Déclaration et initialisation de la permutation π
  private static final int[] permutation = {4, 6, 0, 2, 7, 3, 1, 5};

  // Méthode pour déchiffrer le bloc C
  public static int decrypt(int input) {
    // Applique la permutation π à l'entrée
    int permutationLecture = permute(input, permutation);
    
    // Sépare l'entrée en deux blocs de 4 bits
    int g2 = permutationLecture >>> 4;
    int d2 = permutationLecture & 0b1111;
    
    // Premier round de l'algorithme
    int g1 = reversePermute(d2 ^ k2);
    int d1 = g2 ^ (g1 | k2);
    
    // Deuxième round de l'algorithme
    int g0 = reversePermute(d1 ^ k1);
    int d0 = g1 ^ (g0 | k1);
    
    // Concaténation de G0 et D0
    int n = (g0 << 4) | d0;
    
    // Applique la permutation inverse π^-1 à N
    return reversePermute(n);
  }
  
  // Méthode pour permuter les bits d'une entrée en utilisant une permutation
  private static int permute(int lire, int[] permutation) {
    int sortie = 0;
    for (int i = 0; i < permutation.length; i++) {
      int bit = (lire >> permutation[i]) & 1;
      sortie |= bit << i;
    }
    return sortie;
  }
  
  // Méthode pour permuter les bits d'une entrée en utilisant l'inverse de la permutation
  private static int reversePermute(int input) {
    int output = 0;
    for (int i = 0; i < permutation.length; i++) {
      int bit = (input >> i) & 1;
      output |= bit << permutation[i];
    }
    return output;
  }
  
   public static void main(String[] args) {
        // TODO code application logic here
        int C = 0b10101010;
        int decrypted = Algo_dechiff.decrypt(C);
        System.out.println("message crypté : " + Integer.toBinaryString(C));
        System.out.println("message déchiffré : " + Integer.toBinaryString(decrypted));
       
    }
}



















/**
 *
 * @author Prince
 */
        /*
public class Algo_dechiff {

    /**
     * @param args the command line arguments
     */
        /*
  private static final int[] permutation = {4, 6, 0, 2, 7, 3, 1, 5};
  private static final int k1 = 0b1010;
  private static final int k2 = 0b1100;

  private static final int[] PI = {4, 6, 0, 2, 7, 3, 1, 5};
  private static final int[] PI_INVERSE = {3, 5, 7, 1, 6, 0, 2, 4};
  private static final int P = 2013;
  private static final int[] KEYS = {k1, k2};

  public static int decrypt(int C) {
    int[] C_bits = divideInto4BitBlocks(C);
    int[] G = new int[2];
    int[] D = new int[2];
    int G1, D1, G0, D0;

    // First Round
    G[1] = permuteInv(C_bits[1] ^ KEYS[1]);
    D[1] = C_bits[0] ^ (G[1] | KEYS[1]);

    // Second Round
    G[0] = permuteInv(D[1] ^ KEYS[0]);
    D[0] = G[1] ^ (G[0] | KEYS[0]);

    int N = (G[0] << 4) | D[0];
    return permuteInv(N);
  }

  private static int[] divideInto4BitBlocks(int C) {
    int[] result = new int[2];
    result[0] = C >> 4;
    result[1] = C & 0xF;
    return result;
  }

  private static int permuteInv(int input) {
    int result = 0;
    for (int i = 0; i < permutation.length; i++) {
      int bit = (input >> permutation[i]) & 1;
      result |= (bit << i);
    }
    return result;
  
}
    public static void main(String[] args) {
        // TODO code application logic here
        int C = 0b10101010;
        int decrypted = Algo_dechiff.decrypt(C);
        System.out.println("Bloc crypté : " + Integer.toBinaryString(C));
        System.out.println("Bloc déchiffré : " + Integer.toBinaryString(decrypted));
       
    }
    
}
**/
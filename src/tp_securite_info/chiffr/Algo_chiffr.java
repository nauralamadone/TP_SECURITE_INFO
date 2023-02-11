package tp_securite_info.chiffr;

public class Algo_chiffr {
   
  private int[] pi = {4, 6, 0, 2, 7, 3, 1, 5};
  private int[] pi_inv = {3, 0, 2, 4, 6, 1, 7, 5};
  private int[] P = {2, 0, 1, 3};
  private int[] keys = {1, 2};
  
  public int[] encrypt(int[] N) {
    int[] Cle = new int[8];
    
    // permutation de pi
    int[] N_permutation = applicationPermutation(N, pi);
    
    // Divide N par 2 et en 4bit 
    int[] G0 = new int[4];
    int[] D0 = new int[4];
    System.arraycopy(N_permutation, 0, G0, 0, 4);
    System.arraycopy(N_permutation, 4, D0, 0, 4);
    
  
    int[] G1 = xor(applicationPermutation(G0, P), intToBinary(keys[0], 4));
    int[] D1 = xor(G0, or(G0, intToBinary(keys[0], 4)));
    
    
    int[] G2 = xor(applicationPermutation(G1, P), intToBinary(keys[1], 4));
    int[] D2 = xor(G1, or(G1, intToBinary(keys[1], 4)));
    
    // Concatenation de G2 et D2
    System.arraycopy(G2, 0, Cle, 0, 4);
    System.arraycopy(D2, 0, Cle, 4, 4);
    
    //inverse permutation de pi_inv
    Cle = applicationPermutation(Cle, pi_inv);
    
    return Cle;
  }
  
  private int[] applicationPermutation(int[] N, int[] permutation) {
    int[] permutations = new int[N.length];
    for (int i = 0; i < permutation.length; i++) {
      permutations[i] = N[permutation[i]];
    }
    return permutations;
  }
  
  private int[] xor(int[] a, int[] b) {
    int[] resultat = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      resultat[i] = a[i] ^ b[i];
    }
    return resultat;
  }
  
  private int[] or(int[] a, int[] b) {
    int[] resultat = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      resultat[i] = a[i] | b[i];
    }
    return resultat;
  }
  
  private int[] intToBinary(int n, int Bits) {
    int[] binary = new int[Bits];
    
    
    for (int i = 0; i < Bits; i++) {
      binary[Bits - 1 - i] = n & 1;
      n = n ;
      n = n >> 1;
    }
    return binary;
  }
  
  
  public class ClassPrincipal {
    
  public static void main(String[] args) {
    //Instance Algo_chiffr
    Algo_chiffr chiffrer = new Algo_chiffr();
    
    int[] N = {0, 1, 1, 1, 1, 1, 1, 0};
    int[] chiff = chiffrer.encrypt(N);
    System.out.print("sortie de msg chiffré : ");
    for (int i = 0; i < chiff.length; i++) {
      System.out.print(chiff[i] + " ");
    }
     System.out.println("");
  }
    }
}


 

 


/**
 *
 * @author Amanda Grams
 */
public class Strassen {

    public static void main(String[] args) {

        /**
         * ---------------------------------------INICIO - Criando duas matrizes  de 4x4-----------------------------------------------   */
        
        //Define aqui o tamanho da Matriz.
        int[][] A = new int[2][2];
        int[][] B = new int[2][2];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                //Preenchendo matriz A
                A[i][j] = i + j;
                //Preenchendo matriz B
                B[i][j] = j * 2 + i;
            }
        }
        /**
         * ---------------------------------------FIM - Criando duas matrizes de 4x4-----------------------------------------------------   */

        /** ---------------------------------------INICIO - Teste para ver se a * Matriz A e B são pótências de 2----------------------*/
        
        if (verificaSeEPotenciade2(A.length) == false || verificaSeEPotenciade2(B.length) == false) {
            System.out.println("A Matriz não é potencia de 2, portanto o Algoritmo não se aplica.");
        } else {

            /**
             * ---------------------------------------FIM - Teste para ver se a  Matriz A e B são pótências de 2------------------------------- */
           
            //Se passou no teste para ver se a matriz é potência de 2 Visualiza a matriz A e B     
            
            /** ---------------------------------------INICIO - Visualiza Matriz A------------------------------------------------------------------- */
            System.out.println("****************************************************");
            System.out.println("Matriz A");
            imprime(A);
            /** ---------------------------------------FIM - Visualiza Matriz A-----------------------------------------------  */

            /** ---------------------------------------INICIO - Visualiza Matriz B----------------------------------------------  */
            System.out.println("****************************************************");
            System.out.println("Matriz B");
            imprime(B);
            /** ---------------------------------------FIM - Visualiza Matriz B-----------------------------------------------  */
            
            /*** ---------------------------------------INICIO - MultiplicaMatriz A * B---------------------------------------------- */
            System.out.println();
             System.out.println("****************************************************");
            System.out.println("Resultado Multiplicação Strassen ");          
            MultiplicaMatriz(A, B);

           /*** ---------------------------------------FIM - MultiplicaMatriz A * B---------------------------------------------- */
        }
    }

    /**
     * ---------------------------------------INICIO - Verifica se a Matriz é potência de 2---------------------------------------------- */
    public static boolean verificaSeEPotenciade2(int tamanhoMatriz) {
        //Preserva tamanho da Matriz de Entrada
        int tamanhoMatrizEntrada = tamanhoMatriz;
        int filt = 0;

        if (tamanhoMatriz == 2) {
            //System.out.println("A Matriz de tamnho  "+ tamanhoMatrizEntrada + "  X  "+tamanhoMatrizEntrada+ ",   é potência de 2");
            return true;
        }

        for (int cont = tamanhoMatriz; cont >= 2; cont--) {
            tamanhoMatriz = tamanhoMatriz / 2;
            filt = tamanhoMatriz;
            cont = tamanhoMatriz;
        }
        if ((filt != 2) || (filt > 2)) {
            //System.out.println("A Matriz de tamnho  "+ tamanhoMatrizEntrada + "  X  "+tamanhoMatrizEntrada+ ",  não é potência de 2");
            return false;
        } else {
            // System.out.println("A Matriz de tamnho  "+ tamanhoMatrizEntrada + "  X  "+tamanhoMatrizEntrada+ ",   é potência de 2");
            return true;
        }

    }

    /** ---------------------------------------FIM - Verifica se a Matriz é  potência de 2---------------------------------------------- */
    
    public static int[][] soma(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        // imprime(C);   
        return C;
    }

    public static int[][] subtracao(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        //imprime(C);   
        return C;
    }

    public static int[][] multiplicacao(int[][] A, int[][] B) {
        int[][] C = new int[A.length][A.length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        //imprime(C);   
        return C;
    }

    public static void imprime(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void MultiplicaMatriz(int[][] A, int[][] B) {
        //Decompoem  a matriz 

        int metade = A.length / 2;

        int[][] A11 = new int[metade][metade];
        int[][] A12 = new int[metade][metade];
        int[][] A13 = new int[metade][metade];
        int[][] A14 = new int[metade][metade];
        int[][] B11 = new int[metade][metade];
        int[][] B12 = new int[metade][metade];
        int[][] B13 = new int[metade][metade];
        int[][] B14 = new int[metade][metade];
        for (int i = 0; i < metade; i++) {
            for (int j = 0; j < metade; j++) {
                A11[i][j] = A[i][j];
                B11[i][j] = B[i][j];
                A12[i][j] = A[i][metade + j];
                B12[i][j] = B[i][metade + j];
                A13[i][j] = A[metade + i][j];
                B13[i][j] = B[metade + i][j];
                A14[i][j] = A[metade + i][metade + j];
                B14[i][j] = B[metade + i][metade + j];
            }
        }
        int[][] M1 = multiplicacao((soma(A11, A14)), (soma(B11, B14)));
        int[][] M2 = multiplicacao((soma(A13, A14)), (B11));
        int[][] M3 = multiplicacao((A11), (subtracao(B12, B14)));
        int[][] M4 = multiplicacao((A14), (subtracao(B13, B11)));
        int[][] M5 = multiplicacao((soma(A11, A12)), (B14));
        int[][] M6 = multiplicacao((subtracao(A13, A11)), (soma(B11, B12)));
        int[][] M7 = multiplicacao((subtracao(A12, A14)), (soma(B13, B14)));
        int[][] C11 = soma((soma(M1, subtracao(M4, M5))), M7);
        int[][] C12 = soma(M3, M5);
        int[][] C21 = soma(M2, M4);
        int[][] C22 = soma((soma(subtracao(M1, M2), M3)), M6);
        int[][] C = new int[A.length][A.length];
        for (int i = 0; i < metade; i++) {
            for (int j = 0; j < metade; j++) {
                C[i][j] = C11[i][j];
                C[i][metade + j] = C12[i][j];
                C[metade + i][j] = C21[i][j];
                C[metade + i][metade + j] = C22[i][j];
            }
        }
        System.out.println("****************************************************");
         imprime(C);
        System.out.println("****************************************************");
    }
}

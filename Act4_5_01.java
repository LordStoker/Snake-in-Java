package Act4_5;
import Act4_1.UtilitatsArrays;
import Act4_2.UtilitatsMatrius;
import Act4_3.UtilitatsConsola;
/**
 *
 * @author tomas
 */
public class Act4_5_01 {
        static int SIMBOL_BUIT = 0;
        static int SIMBOL_CUC = 1;

    public static void main(String[] args) {
        
        final int MEDIDATABLA = UtilitatsConsola.llegirSencer("Mida del tablero:");
        int [][] tabla = UtilitatsMatrius.generaMatriu(MEDIDATABLA, 0, 0);
        int [] cuc = UtilitatsArrays.generaArray(2, 0, tabla.length-1); 
        int accio = 0;

        emplenaTauler(tabla, cuc);
                //Bucle mostrando la matriz(tabla) realizando el movimiento y llamando el procedimiento para cambiar posición, borrar gusano y ponerlo en el nuevo lugar.
        do{
        UtilitatsMatrius.mostrarMatriu(tabla);
        accio = UtilitatsConsola.llegirSencer("ACCIONES: 8: ARRIBA, 2: ABAJO, 4: IZQUIERDA, 6: DERECHA 0:SALIR ");
        
        cambiaPosicio(tabla, cuc, accio);
        
        }while(accio != 0);
    }
    //LE INDICAMOS A LA MATRIZ DE TABLA EN QUÉ ÍNDICES ESTÁ EL GUSANO.
    public static void emplenaTauler (int[][] tabla, int[] cuc){
        tabla[cuc[0]][cuc[1]] = SIMBOL_CUC;
    }
    
    //"MOVEMOS EL GUSANO DE 1 EN 1 A LA SIGUIENTE CASILLA, PRIMERO VACIAMOS DE LA TABLA EL GUSANO, PONIÉNDOLE UN 0 Y TRAS LA ACCIÓN, INDICAMOS A LA MATRIZ EL NUEVO LUGAR DEL GUSANO
    public static void cambiaPosicio (int[][] tabla, int[] cuc, int accio){
        int tamaño = tabla.length;
        
        tabla[cuc[0]][cuc[1]] = SIMBOL_BUIT;
        switch (accio){
            case 8:
                cuc[0] = cuc[0] == 0 ? cuc[0] = tamaño - 1 : cuc[0]-1; // Si cuc[0] es igual a 0, simulamos que sale por abajo al darle el valor del tamaño de la matriz -1. Si no, le suma 1
                break;
            case 2:
                cuc[0] = cuc[0] == tamaño -1 ? cuc[0] = 0 : cuc[0]+1; //Lo mismo pero por abajo. 
                break;
            case 4:
                cuc[1] = cuc[1] == 0 ? cuc[0] = tamaño -1 : cuc[1]-1;
                break;
            case 6:
                cuc[1] = cuc[1] == tamaño - 1 ? cuc[1] = 0: cuc[1]+1;
                break;            
        }
        tabla[cuc[0]][cuc[1]] = SIMBOL_CUC;
    }
}
    


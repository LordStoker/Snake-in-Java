
package Act4_5;
import Act4_1.UtilitatsArrays;
import Act4_2.UtilitatsMatrius;
import Act4_3.UtilitatsConsola;

/**
 *
 * @author tomas
 */
public class Act4_5_03 {
        static int SIMBOL_BUIT = 0;
        static int SIMBOL_CUC = 1;
        static int SIMBOL_FULLES = 9;

    public static void main(String[] args) {
        
        final int MEDIDATABLA = UtilitatsConsola.llegirSencer("Mida del tablero: ");
        final int NFULLES = UtilitatsConsola.llegirSencer("Introduce el número de hojas: ");
        int [][] tabla = UtilitatsMatrius.generaMatriu(MEDIDATABLA, 0, 0);
        int [] cuc = UtilitatsArrays.generaArray(2, 0, tabla.length-1);
        //int [] fulles = UtilitatsArrays.generaArray(NFULLES, 0, tabla.length-1);
        int accio;
        
//Bucle mostrando la matriz(tabla) 
        emplenaTauler(tabla, cuc, NFULLES);
//Coge un valor por pantalla que usa en el método de cambiar posición.
        do{
        UtilitatsMatrius.mostrarMatriu(tabla);
        accio = UtilitatsConsola.llegirSencer("ACCIONES: 8: ARRIBA, 2: ABAJO, 4: IZQUIERDA, 6: DERECHA 0:SALIR ");
        
        cambiaPosicio(tabla, cuc, accio);
        
        }while(accio != 0);
    }
    
     //"MOVEMOS EL GUSANO DE 1 EN 1 A LA SIGUIENTE CASILLA, PRIMERO GUARDAMOS EL TAMAÑO DE LA TABLA, VACIAMOS DE LA TABLA EL GUSANO, PONIÉNDOLE UN 0 Y TRAS LA ACCIÓN, INDICAMOS A LA MATRIZ EL NUEVO LUGAR DEL GUSANO
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
        if(tabla[cuc[0]][cuc[1]] == SIMBOL_FULLES){
            añadirHoja(tabla);
        }
        tabla[cuc[0]][cuc[1]] = SIMBOL_CUC;
    }
        
    //LE INDICAMOS A LA MATRIZ DE TABLA EN QUÉ ÍNDICES ESTÁ EL GUSANO Y LAS N HOJAS. COMRPOBAMOS QUE NO SE SOBRESCRIBA EL GUSANO CON LAS HOJAS NI OTRAS HOJAS.
    public static void emplenaTauler (int[][] tabla, int[] cuc, int NFULLES){
        tabla[cuc[0]][cuc[1]] = SIMBOL_CUC;
        boolean hojaOk;
        int i = 0;
        
        while(i < NFULLES){
            hojaOk = false;
            int [] fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);            
            do{
                if(tabla[fulles[0]][fulles[1]] == SIMBOL_CUC){
                    fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);                    
                }
                else if(tabla[fulles[0]][fulles[1]] == SIMBOL_FULLES){
                    fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);
                }
                else{
                tabla[fulles[0]] [fulles[1]] = SIMBOL_FULLES;
                hojaOk = true;
                i++;
                
                }
            
            }while(!hojaOk);
        }
    }
    //MÉTODO PARA AÑADIR NUEVA HOJA SI EL GUSANO SE COME UNA, COMPROBANDO LA POSICIÓN NO SEA NI UN GUSANO NI OTRA HOJA
    public static void añadirHoja (int [][] tabla){
        int [] fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);
        boolean huecoVacio = false;        
        while(!huecoVacio){
            if(tabla[fulles[0]][fulles[1]] == SIMBOL_CUC){
                fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);
            }            
            else if(tabla[fulles[0]][fulles[1]] == SIMBOL_FULLES){
                fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);
            }   
            else{
                tabla[fulles[0]][fulles[1]] = SIMBOL_FULLES;
                    huecoVacio = true;
            }
        }
    }
}

    


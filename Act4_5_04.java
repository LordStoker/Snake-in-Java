
package Act4_5;

import Act4_1.UtilitatsArrays;
import Act4_2.UtilitatsMatrius;
import Act4_3.UtilitatsConsola;
import Act4_4.UtilitatsClasses;
import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class Act4_5_04 {
    static int SIMBOL_BUIT = 0;
    static int SIMBOL_CUC = 1;
    static int SIMBOL_CAPCUC = 2;
    static int SIMBOL_FULLES = 9;
    static int MEDIDATABLA;
    static int NFULLES ;
    static int [][] tabla;
    static ArrayList<int[]> cuc = new ArrayList<>();  
    static int accio;
    static int fulles[];

    public static void main(String[] args) {
        
        final int MEDIDATABLA = UtilitatsConsola.llegirSencer("Mida del tablero: "); 
        final int NFULLES = UtilitatsConsola.llegirSencer("Introduce el número de hojas: ");    
        tabla = UtilitatsMatrius.generaMatriu(MEDIDATABLA, 0, 0); 
        
//LE DECIMOS DONDE ESTÁ EL GUSANO DENTRO LA MATRIZ(TABLA)
        emplenaTauler(tabla, cuc, NFULLES);
//Bucle mostrando la matriz(tabla) Coge un valor por pantalla que usa en el método de cambiar posición.
        do{
   
        UtilitatsMatrius.mostrarMatriu(tabla);
        accio = UtilitatsConsola.llegirSencer("ACCIONES: 8: ARRIBA, 2: ABAJO, 4: IZQUIERDA, 6: DERECHA 0:SALIR ");
        
        cambiaPosicio(tabla, cuc, accio);
        
        }while(accio != 0);
    }
    
     //GUARDAMOS LA FILA Y LA COLUMNA DEL ÚLTIMO Y EL PRIMER ELEMENTO DE CUC, SEGUN LA ACCIÓN SUMAMOS, RESTAMOS O DA LA VUELTA EN FUNCIÓN DE SU POSICIÓN ACTUAL
    public static void cambiaPosicio (int[][] tabla, ArrayList<int[]> cuc, int accio){
        int tamaño = tabla.length;
        int[] posCucCap = {cuc.get(cuc.size()-1)[0], cuc.get(cuc.size()-1)[1]};
        int[] posCucCua = {cuc.get(0)[0], cuc.get(0)[1]};
        
        
        tabla[posCucCap[0]] [posCucCap[1]] = SIMBOL_CUC;
        switch (accio){
            case 8:
                posCucCap[0] = posCucCap[0] == 0 ? tamaño -1 : posCucCap[0]-1;
                break;
            case 2:
                posCucCap[0] = posCucCap[0] == tamaño-1 ? 0 : posCucCap[0]+1;
                break;
            case 4:
                posCucCap[1] = posCucCap[1] == 0 ? tamaño-1 : posCucCap[1]-1;
                break;
            case 6:
                posCucCap[1] = posCucCap[1] == tamaño-1 ? 0 : posCucCap[1]+1;         
                break;            
        }
//AÑADIMOS LA NUEVA COORDENADA SEGÚN LA ACCIÓN, SI HAY HOJA, SOLO AÑADISMO, SINO HAY HOJA AÑADIMOS Y BORRAMOS LA PRIMERA(COLA)        
        if(tabla[posCucCap[0]][posCucCap[1]] == SIMBOL_FULLES){
            cuc.add(posCucCap);
            tabla[posCucCap[0]][posCucCap[1]] = SIMBOL_CAPCUC;
            añadirHoja(tabla);
            
        }
        else if (tabla[posCucCap[0]] [posCucCap[1]] == SIMBOL_BUIT){
            cuc.add(posCucCap);
            cuc.remove(0);
            tabla[posCucCap[0]][posCucCap[1]] = SIMBOL_CAPCUC;
        }

        
    }
        
    //LE INDICAMOS A LA MATRIZ DE TABLA EN QUÉ ÍNDICES ESTÁ EL GUSANO Y LAS N HOJAS. COMRPOBAMOS QUE NO SE SOBRESCRIBA EL GUSANO CON LAS HOJAS NI OTRAS HOJAS.
    public static void emplenaTauler (int[][] tabla, ArrayList<int[]> cuc, int NFULLES){
        final int MIN = 0, MAX=tabla.length-1;
        int [] cucPos = UtilitatsArrays.generaArray(2, MIN, MAX);        
        cuc.add(cucPos);        
        tabla[cucPos[0]] [cucPos[1]] = SIMBOL_CAPCUC;
        
        int i = 0;
        
        while(i < NFULLES){
            añadirHoja(tabla);
            i++;
        }
    }
    //MÉTODO PARA AÑADIR NUEVA HOJA SI EL GUSANO SE COME UNA, COMPROBANDO LA POSICIÓN NO SEA NI UN GUSANO NI OTRA HOJA
    public static void añadirHoja (int [][] tabla){
        fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);
        boolean huecoVacio = false;        
        while(!huecoVacio){
            if(tabla[fulles[0]][fulles[1]] == SIMBOL_BUIT){
                tabla[fulles[0]][fulles[1]] = SIMBOL_FULLES;
                huecoVacio = true;
            }
            else
                fulles = UtilitatsArrays.generaArray(2, 0, tabla.length-1);                        
        }
    }
}

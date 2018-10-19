package view.backing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.Util;

public class Prueba2 {
    public Prueba2() {
        super();
    }

    public static void main(String[] args) {
        Prueba2 prueba2 = new Prueba2();
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList( 9, 5, 0, 7,99));
        String zack = "Mujer";
        int pivot = 7;
        int ini = 0;
        int fin = pivot;
        int cant = numbers.size();

        
    Util.Log("cant:"+cant);
    Util.Log("Total:"+numbers);
        while (zack.equalsIgnoreCase("Mujer")) {
            List<Integer> head = new ArrayList<Integer>();
            if( cant > pivot){
                head = numbers.subList(ini,fin);
                
                ini = fin;
                    fin = ini + pivot;
                    cant = cant - pivot;
                
                //Util.Log("cant:"+cant);
                //Util.Log("fin:"+fin);
            } else{
                head = numbers.subList(ini, numbers.size());
                zack = "Hombre";
            }
 
            Util.Log(head + "");

        }


    }
}

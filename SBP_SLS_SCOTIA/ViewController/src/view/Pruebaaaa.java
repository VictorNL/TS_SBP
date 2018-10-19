package view;

public class Pruebaaaa {
    public Pruebaaaa() {
        super();
    }

    public static void main(String[] args) {
        Pruebaaaa pruebaaaa = new Pruebaaaa();
    
        double cantSupxCan = 0.0;
        double resultadoPorc = 0.0;
        
        
        
        //resultadoPorc =  Math.round(((100/3)/100))*10.0/10.0;//truncar datos!!!!!
        resultadoPorc = Math.round((3.555)* 100.0) / 100.0;
        
        Util.Log(resultadoPorc + "");
    }
}

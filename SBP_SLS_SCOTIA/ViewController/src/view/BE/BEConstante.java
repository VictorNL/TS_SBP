package view.BE;

public class BEConstante {
    public BEConstante() {
        super();
    }

    public static String datasource = "apextest"; // Cambiar x ambiente
    
    public static String cod_cloudOracle = "QRY01";    
    public static String cod_cloudSftp = "QRY02";
    
    public static class EstadoProceso {
        public static String Iniciado = "INICIADO";
        public static String Detenido = "DETENIDO";
    }
}

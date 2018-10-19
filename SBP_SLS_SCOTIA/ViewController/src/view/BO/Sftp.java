package view.BO;

import Dao.Conexion;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.InputStream;

import view.BE.BEConstante;

import view.Util;

/**
 * Clase de ayuda para manejo de sftp
 */
public class Sftp {
    
    public Sftp() {
        super();
    }

    /**
     * 
     * @param psNombreArchivo Nombre del archivo
     * @return
     * @throws JSchException
     * @throws SftpException
     */
    public static InputStream getFile(String psNombreArchivo) throws JSchException, SftpException{
        Conexion bec = Util.DatosConexionSBP(BEConstante.cod_cloudSftp);
        InputStream isReturn = null;
        try {
            String host = System.getProperty("http.proxyHost");

            JSch jsch = new JSch();
            Session session = null;

            session = jsch.getSession(bec.getUserName(), bec.getHostName(), Integer.parseInt(bec.getPort()));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(bec.getPassword());
            session.setProxy(new ProxyHTTP(host, 80));
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp)channel;
            sftpChannel.cd(bec.getKeyStoreLocation());
            isReturn = sftpChannel.get(psNombreArchivo);

            //sftpChannel.exit();
            //session.disconnect();
            Util.Log("Exito :: Archivo obtenido");

        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
        return isReturn;
    }

    /**
     *
     * @param psNombreArchivo Nombre del archivo
     * @param pisArchivo Archivo a guardar
     * @throws JSchException
     * @throws SftpException
     */
    public static void setFile(String psNombreArchivo, InputStream pisArchivo) throws JSchException, SftpException {

        Conexion bec = Util.DatosConexionSBP(BEConstante.cod_cloudSftp);
        try {

            String host = System.getProperty("http.proxyHost");
            String port = System.getProperty("http.proxyPort");

            JSch jsch = new JSch();
            Session session = null;

            session = jsch.getSession(bec.getUserName(), bec.getHostName(), Integer.parseInt(bec.getPort()));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(bec.getPassword());

            session.setProxy(new ProxyHTTP(host, 80));
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp)channel;
            sftpChannel.cd(bec.getKeyStoreLocation());
            sftpChannel.put(pisArchivo, psNombreArchivo);
            sftpChannel.exit();
            session.disconnect();

            Util.Log("Exito :: Archivo guardado");

        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }

}

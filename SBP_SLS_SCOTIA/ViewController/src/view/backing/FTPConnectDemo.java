package view.backing;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import com.jcraft.jsch.SftpException;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class FTPConnectDemo {
    public static void main(String args[]) throws FileNotFoundException {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("root", "10.20.30.63", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("oracle");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            
            sftpChannel.cd("/root/");
            
          //  sftpChannel.get("contactos_lead.txt", "contactos_lead.txt"); // de ftp a local
         /*   sftpChannel.cd("/root");
            File f = new File("contactos_lead.txt");
            sftpChannel.put(new FileInputStream(f), f.getName()); */
            
            String prueba = "HOLA MUNDO";

            InputStream inputStream = new ByteArrayInputStream(prueba.getBytes());
            
            sftpChannel.put(inputStream, "prueba.txt");
         
            
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace(); 
        } catch (SftpException e) {
            e.printStackTrace();
        } 
    }
    }

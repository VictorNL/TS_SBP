package view.backing;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import dao.Feedback;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.TimeZone;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.apache.commons.dbutils.DbUtils;

import view.Util;

public class SBP_SLS_ARC_FB {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtHora;
    private String mataHilo = "F";

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void crearArchivo() {
        
        List<Feedback> listadoFb = new ArrayList<Feedback>();
       
        listadoFb = listFb();
        
        generaCsvFb(listadoFb);
    }
    
    public void generaCsvFb(List<Feedback> listFeedback) {
        try {
            
            //FECHA HORA ARCHIVO
                     
             final Date currentTimeFecCreaArc = new Date();
            
             final SimpleDateFormat sdfFecCreaArc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
             // Give it to me in GMT time.
             sdfFecCreaArc.setTimeZone(TimeZone.getTimeZone("GMT-5"));
             
             String fecCreaArc = sdfFecCreaArc.format(currentTimeFecCreaArc);
             Util.Log("Fecha creacion archivo --> " + fecCreaArc);

            String host = System.getProperty("http.proxyHost");

            JSch jsch = new JSch();

            Session session = null;
            
            Util.Log("inicio usuario");
            
            //SFTP SBP

        /*    session = jsch.getSession("usftp311", "190.116.5.24", 22);

            session.setConfig("StrictHostKeyChecking", "no");

            session.setPassword("uwy2FUmt");*/
            
            //SFTP CLOUD SBP
            
            session = jsch.getSession("ccmg-tVW", "sftp.us2.cloud.oracle.com", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("Evol2018");

            session.setProxy(new ProxyHTTP(host, 80));
            
            Util.Log("inicia conexión");

            session.connect();
            
            Util.Log("termina conexión");

            Channel channel = session.openChannel("sftp");
            
            channel.connect();
            
            ChannelSftp sftpChannel = (ChannelSftp)channel;

            //RUTA SBP
           //     sftpChannel.cd("/nfshome/sbpftp04/usftp311/");
            
            //RUTA CLOUD
           sftpChannel.cd("/upload/ReadTxt/");


            String cabeceraArchivoFb = "IDVendor,Vendor,IDCampaña,Nombre Campaña,IDAgente,Agente Vendor,IDTipoGestion,Tipo Gestión,IDGrupoGestion,Grupo Gestión,IDDetalleGestion,Detalle Gestión,Tipo Documento,Número Documento,Contacto,Teléfono,Fecha / Hora,Fecha Agendamiento,Observaciones,CodigoProducto";
            String detalleArchivoFb = "";
            
            for (Feedback listFb : listFeedback) {
                
                detalleArchivoFb += listFb.getIdCanal() + "," + listFb.getCanal() + "," + listFb.getCodCpg() + "," +
                listFb.getNomCpg() + "," + listFb.getIdEjVenta() + "," + listFb.getEjVenta() + "," + listFb.getTipoGestion() + "," +
                listFb.getNomTipGest() + "," + listFb.getGrupoGestion() + "," + listFb.getNomGrupGest() + "," + listFb.getDetGestion() + 
                "," + listFb.getNomDetGest() + "," + listFb.getTipDoc() + "," + listFb.getNumDoc() + "," + listFb.getContacto() + 
                "," + listFb.getTelefono() + "," + listFb.getFecGestion() + " " + listFb.getHoraFeedback() + "," + listFb.getFec2daLlam() + "," + listFb.getObs() + "," + listFb.getCodProd() + "\n";
            }
            
            String archivo = cabeceraArchivoFb + "\n" + detalleArchivoFb;

            Util.Log(archivo);

            InputStream inputStream = new ByteArrayInputStream(archivo.getBytes());

            sftpChannel.put(inputStream, "Feedback " + fecCreaArc + ".csv");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Feedback> listFb(){
        
        List<Feedback> listFb = new ArrayList<Feedback>();
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idLead = "";       
        String nombreFb = "";       
        String idCanal = "";       
        String canal = "";       
        String idJefCanal = "";       
        String jefCanal = "";       
        String idSup = "";       
        String sup = "";       
        String idEjVenta = "";       
        String ejVenta = "";       
        String telefono = "";       
        String tipoGestion = "";       
        String grupoGestion = "";       
        String detalleGestion = "";       
        String auxFeedbackContactado = "";       
        String auxFeedbackContactadoEfec = "";       
        String auxFeedbackVenta = "";       
        String auxFeedbackRevenueVenta = "";       
        String nomTipGest = "";       
        String nomGrupGest = "";       
        String nomDetGest = "";       
        String fecGestion = "";       
        String observacion = "";       
        String telfAd = "";       
        String dirAd = "";       
        String emailAd = "";       
        String idCpg = "";       
        String cpgNom = "";       
        String montoDesem = "";       
        String fecha2daLlam = "";       
        String contacto = "";       
        String numDoc = "";       
        String tipDoc = "";       
        String prod = "";       
        String flagInbound = "";       
        String flagOutbound = "";       
        String horaFeedback = "";       
        String auxFeedbackAgendado = "";
        String codProd = "";
        
        try {
            
            String query = "select nvl(idLead, '0') IDLEAD," +                    
                            "nvl(nombreFb, '-') NOMBREFB ," + 
                            "nvl(idCanal, '-') IDCANAL ," + 
                            "nvl(canal, '-') CANAL ," + 
                            "nvl(idJefCanal, '-') IDJEFCANAL ," + 
                            "nvl(jefCanal, '-') JEFCANAL ," + 
                            "nvl(idSup, '-') IDSUP ," + 
                            "nvl(sup, '-') SUP ," + 
                            "nvl(idEjVenta, '-') IDEJVENTA ," + 
                            "nvl(ejVenta, '-') EJVENTA ," + 
                            "nvl(telefono, '-') TELEFONO ," + 
                            "nvl(tipoGestion, '-') TIPOGESTION ," + 
                            "nvl(grupoGestion, '-') GRUPOGESTION ," + 
                            "nvl(detalleGestion, '-') DETALLEGESTION ," + 
                            "nvl(auxFeedbackContactado, '-') AUXFEEDBACKCONTACTADO ," + 
                            "nvl(auxFeedbackContactadoEfec, '-') AUXFEEDBACKCONTACTADOEFEC ," + 
                            "nvl(auxFeedbackVenta, '-') AUXFEEDBACKVENTA ," + 
                            "nvl(auxFeedbackRevenueVenta, '-') AUXFEEDBACKREVENUEVENTA ," + 
                            "nvl(nomTipGest, '-') NOMTIPGEST ," + 
                            "nvl(nomGrupGest, '-') NOMGRUPGEST ," + 
                            "nvl(nomDetGest, '-') NOMDETGEST ," + 
                            "nvl(fecGestion, '-') FECGESTION ," + 
                            "nvl(observacion, '-') OBSERVACION ," + 
                            "nvl(telfAd, '-') TELFAD ," + 
                            "nvl(dirAd, '-') DIRAD ," + 
                            "nvl(emailAd, '-') EMAILAD ," + 
                            "nvl(idCpg, '-') IDCPG ," + 
                            "nvl(cpgNom, '-') CPGNOM ," + 
                            "nvl(montoDesem, '-') MONTODESEM ," + 
                            "nvl(fecha2daLlam, '-') FECHA2DALLAM ," + 
                            "nvl(contacto, '-') CONTACTO ," + 
                            "nvl(numDoc, '-') NUMDOC ," + 
                            "nvl(tipDoc, '-') TIPDOC ," + 
                            "nvl(prod, '-') PROD ," + 
                            "nvl(flagInbound, '-') FLAGINBOUND ," + 
                            "nvl(flagOutbound, '-') FLAGOUTBOUND ," + 
                            "nvl(horaFeedback, '-') HORAFEEDBACK ," + 
                            "nvl(auxFeedbackAgendado, '-') AUXFEEDBACKAGENDADO ," + 
                            "nvl(COD_PROD, '-') COD_PROD " + 
                            "from sbp_sls_info_lead_fb where trunc(sysdate) - 1 = to_date(fecgestion, 'yyyy-MM-dd')";
            
            Util.Log(query);
            
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();
            
            while (rs.next()) {
            
                Feedback fb = new Feedback();
                
                idLead = rs.getString("IDLEAD");     
                nombreFb = rs.getString("NOMBREFB");     
                idCanal = rs.getString("IDCANAL");     
                canal = rs.getString("CANAL");     
                idJefCanal = rs.getString("IDJEFCANAL");     
                jefCanal = rs.getString("JEFCANAL");     
                idSup = rs.getString("IDSUP");     
                sup = rs.getString("SUP");     
                idEjVenta = rs.getString("IDEJVENTA");     
                ejVenta = rs.getString("EJVENTA");     
                telefono = rs.getString("TELEFONO");     
                tipoGestion = rs.getString("TIPOGESTION");     
                grupoGestion = rs.getString("GRUPOGESTION");     
                detalleGestion = rs.getString("DETALLEGESTION");     
                auxFeedbackContactado = rs.getString("AUXFEEDBACKCONTACTADO");     
                auxFeedbackContactadoEfec = rs.getString("AUXFEEDBACKCONTACTADOEFEC");     
                auxFeedbackVenta = rs.getString("AUXFEEDBACKVENTA");     
                auxFeedbackRevenueVenta = rs.getString("AUXFEEDBACKREVENUEVENTA");     
                nomTipGest = rs.getString("NOMTIPGEST");     
                nomGrupGest = rs.getString("NOMGRUPGEST");     
                nomDetGest = rs.getString("NOMDETGEST");     
                fecGestion = rs.getString("FECGESTION");     
                observacion = rs.getString("OBSERVACION");     
                telfAd = rs.getString("TELFAD");     
                dirAd = rs.getString("DIRAD");     
                emailAd = rs.getString("EMAILAD");     
                idCpg = rs.getString("IDCPG");     
                cpgNom = rs.getString("CPGNOM");     
                montoDesem = rs.getString("MONTODESEM");     
                fecha2daLlam = rs.getString("FECHA2DALLAM");     
                contacto = rs.getString("CONTACTO");     
                numDoc = rs.getString("NUMDOC");     
                tipDoc = rs.getString("TIPDOC");     
                prod = rs.getString("PROD");     
                flagInbound = rs.getString("FLAGINBOUND");     
                flagOutbound = rs.getString("FLAGOUTBOUND");     
                horaFeedback = rs.getString("HORAFEEDBACK");     
                auxFeedbackAgendado = rs.getString("AUXFEEDBACKAGENDADO");
                codProd = rs.getString("COD_PROD");
                
                fb.setIdLead(idLead);
                fb.setNomFb(nombreFb);
                fb.setIdCanal(idCanal);
                fb.setCanal(canal);
                fb.setIdjefCanal(idJefCanal);
                fb.setJefCanal(jefCanal);
                fb.setIdSup(idSup);
                fb.setSup(sup);
                fb.setIdEjVenta(idEjVenta);
                fb.setEjVenta(ejVenta);
                fb.setTelefono(telefono);
                fb.setTipoGestion(tipoGestion);
                fb.setGrupoGestion(grupoGestion);
                fb.setDetGestion(detalleGestion);
                fb.setAuxContactado(auxFeedbackContactado);
                fb.setAuxContactadoEfec(auxFeedbackContactadoEfec);
                fb.setAuxVenta(auxFeedbackVenta);
                fb.setAuxRevenueVenta(auxFeedbackRevenueVenta);
                fb.setNomTipGest(nomTipGest);
                fb.setNomGrupGest(nomGrupGest);
                fb.setNomDetGest(nomDetGest);
                fb.setFecGestion(fecGestion);
                fb.setObs(observacion);
                fb.setTelfAd(telfAd);
                fb.setDirAd(dirAd);
                fb.setEmailAd(emailAd);
                fb.setCodCpg(idCpg);
                fb.setNomCpg(cpgNom);
                fb.setMontoDesem(montoDesem);
                fb.setFec2daLlam(fecha2daLlam);
                fb.setContacto(contacto);
                fb.setNumDoc(numDoc);
                fb.setTipDoc(tipDoc);
                fb.setProd(prod);
                fb.setFlagInbound(flagInbound);
                fb.setFlagOutbound(flagOutbound);
                fb.setHoraFeedback(horaFeedback);
                fb.setAuxFeedbackAgendado(auxFeedbackAgendado);
                fb.setCodProd(codProd);
                
                listFb.add(fb);
                
            }
            
            Util.Log("tamaño fb list --> " + listFb.size());
            
        } catch (SQLException e) {
                  Util.Log(e + "error");
              } finally {
                  DbUtils.closeQuietly(rs);
                  DbUtils.closeQuietly(st);
                  DbUtils.closeQuietly(conn);
              }

        return listFb;
    }

    public void setTxtHora(RichInputText txtHora) {
        this.txtHora = txtHora;
    }

    public RichInputText getTxtHora() {
        return txtHora;
    }

    public void timer(ActionEvent actionEvent) {
      
        final Date currentTimeFecGest = new Date();

        //final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        final SimpleDateFormat sdfFecGest = new SimpleDateFormat("ss");

        // Give it to me in GMT time.
        sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        
        String fecEjecucion = sdfFecGest.format(currentTimeFecGest);
        
        Util.Log("fecEjecucion --> " + fecEjecucion);
        
        String hora = txtHora.getValue().toString();
        
        Util.Log("hora -- > " + hora);
        
        ejecutaHilo(hora);
        
    }

    public void matarProceso(ActionEvent actionEvent) {
      
      mataHilo = "Y";
      
      Util.Log("mataHilo --> " + mataHilo);
      
      String hora = txtHora.getValue().toString();
    
      Util.Log("hora -- > " + hora);
    
      ejecutaHilo(hora);
      
    }

    public class TaskRunST implements Runnable {
        
        String horaActual = "";
        
       public TaskRunST(String horaActual) {
            
                this.horaActual = horaActual;
                
            }

        public void run() throws RuntimeException{}

    }
    
    public void ejecutaHilo(String horaActual){
        
        Thread th = null;
        
        final long timeInterval = 1000;
        
        try{
            th = new Thread(new TaskRunST(horaActual) {
                
            @Override
            public void run() throws RuntimeException  {
                
            while (true) {
                
                if(!mataHilo.equalsIgnoreCase("F")){
                
                Util.Log("mataHilo --> " + mataHilo);
                
                   break; 
                   
                } else {
                    
                final Date currentTimeFecGest = new Date();

                final SimpleDateFormat sdfFecGest = new SimpleDateFormat("ss");
                
                //final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                // Give it to me in GMT time.
                sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                
                String fecEjecucion = sdfFecGest.format(currentTimeFecGest);
                
                Util.Log("horaActual --> " + horaActual);
                
                Util.Log("fecEjecucion --> " + fecEjecucion);
                    
                Util.Log("mataHilo --> " + mataHilo);
                
            if(horaActual.equalsIgnoreCase(fecEjecucion)){
                
                crearArchivo();
                
                Util.Log("se ejecutó a las  " + horaActual + " coincidió a las " + fecEjecucion);
                
            }
        
            try {
                
            Thread.sleep(timeInterval);
                
            } catch (InterruptedException e) {
                
            e.printStackTrace();
            }
            }
            }

           
        }
                
        });
        
        th.start();

        }catch(Exception e){
        System.out.println(e);
        }
    }
}

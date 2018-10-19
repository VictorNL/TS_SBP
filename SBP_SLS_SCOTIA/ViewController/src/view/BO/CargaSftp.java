package view.BO;

import Client.MergeLeadInvoke;
import Dao.SimplifiedImportActivity;

import WsEjRep.ServiceBiPublisher;

import dao.Lead;
import dao.NuevoDestino;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import oracle.jdbc.internal.OracleTypes;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.io.IOUtils;

import view.BE.BEConstante;

import view.Util;


    /* *************************************************************************
     *       Autor : Fernan Palomino
     *       Fecha : 24/05/2018
     * ********************************************************************** */
public class CargaSftp {
    
    public CargaSftp() {
        super();
    }

    /* *************************************************************************
     * Configuracion carga de feedback                                         *
     ************************************************************************* */
       
    public void IniciarEnvioFeedback(Integer piIntervalo ) {
        try {
            
            // Guardar inicio proceso
            this.setEstadoProceso("QRY01", BEConstante.EstadoProceso.Iniciado);            
            
            // Invocar hilo de carga
            new HiloEnvioFeedback(piIntervalo).start();
            
            Util.Log("Exito :: proceso iniciado");
            
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }

    public void DetenerEnvioFeedback() {
        try {
            this.setEstadoProceso("QRY01", BEConstante.EstadoProceso.Detenido);
            Util.Log("Exito :: proceso detenido");
        } catch (Exception ex) {
            Util.Log("Exception " + ex.getMessage());
        }        
    }
    
    public void EnvioFeedback() {
        try {

            // leer archivo csv
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);

            ZipEntry zel = new ZipEntry("Lead.csv");
            zos.putNextEntry(zel);
            byte[] btl = IOUtils.toByteArray(Sftp.getFile("Lead.csv"));
            zos.write(btl, 0, btl.length);
            zos.closeEntry();

            ZipEntry zef = new ZipEntry("Feedback_Lead_c.csv");
            zos.putNextEntry(zef);
            byte[] btf = IOUtils.toByteArray(Sftp.getFile("Feedback.csv"));
            zos.write(btf, 0, btf.length);
            zos.closeEntry();

            zos.close();

            // enviar
            String str = client.Base64.byteArrayToBase64(bos.toByteArray());
            SimplifiedImportActivity p = new MergeLeadInvoke().submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);
            
            // Registrar
            this.mergeCabecera(p.getJobID(), 
                               p.getStatus(), 
                               p.getSubmittedBy(), 
                               p.getObservaciones());
            
            Util.Log("Exito :: Envio completado");

        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }

    public void setEstadoProceso(String psProceso, String psEstado) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String query = "BEGIN SBP_SLS_CONFIG_PKG.pr_setEstadoProceso(?,?); END;";
            conn = new sbp.utils.Connection().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, psProceso);
            ps.setString(2, psEstado);
            ps.execute();
            
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            Util.Log("SQLException :: " + ex.getMessage());
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }
    
    public String getEstadoProceso(String psProceso) {
        CallableStatement st = null;
        Connection conn = null;
        
        String sReturn = "";

        try {
            String sQuery = "BEGIN SBP_SLS_CONFIG_PKG.pr_getEstadoProceso(?,?); END;";
            conn = new sbp.utils.Connection().getConnection();
            st = conn.prepareCall(sQuery);
            st.setString(1, psProceso);
            st.registerOutParameter(2, OracleTypes.VARCHAR);
            st.execute();

            // Valores de retorno
            sReturn = st.getString(2);

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Util.Log("SQLException :: " + ex.getMessage());
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return sReturn;
    }

    public void mergeCabecera(String pCargaID, 
                              String pEstado, 
                              String pUsuario, 
                              String pObservaciones) {

        PreparedStatement ps = null;
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_mergeCabecera(?,?,?,?); END;";

        try {

            cnx = new sbp.utils.Connection().getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, pCargaID);
            ps.setString(2, pEstado);
            ps.setString(3, pUsuario);
            ps.setString(4, pObservaciones);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage());
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage());
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    /**
     * Proceso en parallelo que enviara los feedback
     */
    private class HiloEnvioFeedback extends Thread {
        
        private Integer iIntervalo; // Intervalo de ejecuccion de la carga
        
        public HiloEnvioFeedback(Integer piIntervalo) {
            this.iIntervalo = piIntervalo;
        }
        
        @Override
        public void run() {
            
            while (!this.stopProceso()) {
                
                // Iniciar carga
                CargaSftp.this.EnvioFeedback();

                // 30 segundos
                try {
                    Thread.sleep(iIntervalo * 60000);
                } catch (InterruptedException ex) {
                    Util.Log("InterruptedException :: " + ex.getMessage());
                }
            }
        }
        
        private boolean stopProceso(){
            Boolean b = false;
            try {
                b = Boolean.parseBoolean(new CargaSftp().getEstadoProceso("QRY01"));
            } catch (Exception ex) {
                Util.Log("Exception :: " + ex.getMessage());
                b = true;
            }
            return b;
        }
    }


    /* *************************************************************************
     * Envio de asignacion                                         *
     ************************************************************************* */
    /**
     *
     * @param plstNuevoDestino
     * @param psCampania
     */
    public void EnviarResource(List<NuevoDestino> plstNuevoDestino, String psCampania) {
        
        try {
            // Obtener cantidad de lead a traer
            int iCantidadLead = 0;
            for (NuevoDestino nd : plstNuevoDestino) {
                iCantidadLead += Integer.parseInt(nd.getCantidad());
            }            

            // Obtener los lead's a asignar
            List<Lead> leads = new ServiceBiPublisher().obtenerLead(iCantidadLead + "", plstNuevoDestino.get(0).getCanal(), psCampania);

            // Armar los string
            int idx = 0;
            String sLead = "LeadNumber" + "\n";
            String sReso = "LeadNumber,ResourceId" + "\n";
            for (NuevoDestino nd : plstNuevoDestino) {
                for (int i = 0; i < Integer.parseInt(nd.getCantidad()); i++) {
                    sLead += leads.get(idx).getLeadNumber() + "\n";
                    sReso += leads.get(idx).getLeadNumber() + "," + nd.getResourceId() + "\n";
                    idx ++;
                }
            }
            
            // Dejar csv en el sftp
            Sftp.setFile("Lead.csv", new ByteArrayInputStream(sLead.getBytes()));
            Sftp.setFile("Lead_Resource.csv", new ByteArrayInputStream(sReso.getBytes()));
            
            // Generar los archivos zip
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);
            
            ZipEntry zel = new ZipEntry("Lead.csv");
            zos.putNextEntry(zel);
            byte[] bl = sLead.getBytes();
            zos.write(bl, 0, bl.length);
            zos.closeEntry();

            ZipEntry zer = new ZipEntry("Lead_Resource.csv");
            zos.putNextEntry(zer);
            byte[] br = sLead.getBytes();
            zos.write(br, 0, br.length);
            zos.closeEntry();

            zos.close();

            // Invocar el SimplifiedImportActivity
            SimplifiedImportActivity p = new SimplifiedImportActivity();
            p.setJobID(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            //SimplifiedImportActivity p = new MergeLeadInvoke().submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", client.Base64.byteArrayToBase64(bos.toByteArray()));
            
            // Mensaje
            Util.Log("Exito :: Envio completado. Job ID :: " + p.getJobID());
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }
    
    
    
    
}

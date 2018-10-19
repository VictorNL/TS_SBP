package view.backing;

import Client.MergeLeadInvoke;
import Dao.ImportActivityStatus;
import Dao.SimplifiedImportActivity;
import WsEjRep.ServiceBiPublisher;
import client.ImportLeadResource;
import com.oracle.xmlns.oracle.apps.marketing.commonmarketing.mktimport.model.ImportJobReturnParams;
import dao.Contacto;
import dao.lista.ListaContacto;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jdbc.OracleTypes;
import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.UploadedFile;
import view.Util;

public class SBP_SLS_CARGA_MASIVA {

    private RichForm f1;
    private RichDocument d1;
    private UploadedFile file;
    private RichInputFile if1;
    private RichInputText txtCxg;
    private RichInputText txtUsu;
    private RichInputDate idIni;
    private RichInputDate idFin;
    private RichTable tcM;
    private RichPopup pmensaje;
    private RichOutputLabel olMensaje;
    private RichInputText txtPll;
    private RichInputText itIdL;

    public void setF1(RichForm f1) { this.f1 = f1; } public RichForm getF1() { return f1; }
    public void setD1(RichDocument d1) { this.d1 = d1; } public RichDocument getD1() { return d1; }
    public void setFile(UploadedFile file) { this.file = file; } public UploadedFile getFile() { return file; }
    public void setIf1(RichInputFile if1) { this.if1 = if1; } public RichInputFile getIf1() { return if1; }
    public void setTxtCxg(RichInputText txtCxg) { this.txtCxg = txtCxg; } public RichInputText getTxtCxg() { return txtCxg; }
    public void setTxtUsu(RichInputText txtUsu) { this.txtUsu = txtUsu; } public RichInputText getTxtUsu() { return txtUsu; }
    public void setIdIni(RichInputDate idIni) { this.idIni = idIni; } public RichInputDate getIdIni() { return idIni; }
    public void setIdFin(RichInputDate idFin) { this.idFin = idFin; } public RichInputDate getIdFin() { return idFin; }
    public void setTcM(RichTable tcM) { this.tcM = tcM; } public RichTable getTcM() { return tcM; }
    public void setPmensaje(RichPopup pmensaje) { this.pmensaje = pmensaje; } public RichPopup getPmensaje() { return pmensaje; }
    public void setOlMensaje(RichOutputLabel olMensaje) { this.olMensaje = olMensaje; } public RichOutputLabel getOlMensaje() { return olMensaje; }
    public void setTxtPll(RichInputText txtPll) { this.txtPll = txtPll; } public RichInputText getTxtPll() { return txtPll; }
    public void setItIdL(RichInputText itIdL) { this.itIdL = itIdL; } public RichInputText getItIdL() { return itIdL; }
    
    private Integer iHilTotPll;

    public void setIHilTotPll(Integer iHilTotPll) { this.iHilTotPll = iHilTotPll; } public Integer getIHilTotPll() { return iHilTotPll; }
    
    public void onBeforePhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
            this.onLoad();
        }
    }

    private boolean isPostback() {
        return Boolean.TRUE.equals(resolveExpression("#{adfFacesContext.postback}"));
    }

    private Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }

    public void onLoad() {
        try {
            this.txtUsu.setValue((String)resolveExpression("#{viewScope.UserID2}"));
            
           // this.txtCxg.setValue("10000");
            this.txtCxg.setValue("100");
            
            this.txtPll.setValue("10");
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }    

    public static Integer obtenerLoteID() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int idCarga = 0;

        try {
            String query = "select SBP_SLS_CM_LOTE_S.nextval ID_LOTE from dual";
            sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
            conn = sbpCnx.getConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                idCarga = rs.getInt("ID_LOTE");
            }

        } catch (SQLException ex) {
            Util.Log("SQLException :: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        return idCarga;
    }

    private boolean processFileValid(){
        boolean r = true;
        try {
            if (Util.nvl(this.txtCxg, "").isEmpty() &&
                Util.nvl(this.txtPll, "").isEmpty() ){
                r = false;
                this.Mensaje("Ingrese los parametros de carga");
            }
            
            
        } catch (Exception ex){
            this.Mensaje(ex.getMessage());
            r = false;
        }
        return r;
    }

    /** ------------------------------------------------------------------------
     * Metodo para enviar una carga de contactos
     * -------------------------------------------------------------------------
     */
    public void processFileUploadContacto(ActionEvent actionEvent) {
        try {
            if (this.processFileValid()){
                this.processFileUpload("QRY01");
            }           
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    /** ------------------------------------------------------------------------
     * Metodo para enviar una carga de contactos
     * -------------------------------------------------------------------------
     */
    public void processFileUploadContactoCsv(ActionEvent actionEvent) {
        try {
            if (this.processFileValid()){
                this.processFileUpload("QRY03");
            }
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    /** ------------------------------------------------------------------------
     * Metodo para enviar una  carga de lead's
     * -------------------------------------------------------------------------
     */
    public void processFileUploadLead(ActionEvent actionEvent) {
        try {
            if (this.processFileValid()){
                
                this.processFileUpload("QRY02");
            }
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }
    
    public void processFileUpload(String psQuery){
        try {
            int iLoteID = this.obtenerLoteID();
            
            // Iniciar hilo
            HiloCarga h = new HiloCarga(psQuery, iLoteID);
            h.start();
            
            // Enviar mensaje de confirmacion
            this.Mensaje("Lote N° " + iLoteID + ". Se cargó el archivo");
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }
    
    public void processFileUpload_OLD1(String pQuery, int piLoteID) {
        Util.Log("Inicio");
        ArrayList<String> items = null;
        try {

            // Obtener el archivo cargado
            UploadedFile uf = this.getFile();
            ZipInputStream zis = new ZipInputStream(uf.getInputStream());
            ZipEntry ze = zis.getNextEntry();
            int ordenL = 0;
            int ordenR = 0;
            
            Util.Log("Inicio insercion a tablas con validacion");
            while (ze != null) {
                // Recorrer el zip
                DataInputStream dis = new DataInputStream(zis);
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = dis.readLine()) != null) {
                    sb.append(line + " \n");
                }

                // Recorrer el string buffer
                ArrayList<String> rows = new ArrayList<String>(Arrays.asList(sb.toString().split("\n")));
                for (String r : rows) {
                    items = new ArrayList<String>(Arrays.asList(r.split(",")));
                    try {
                        // Validaciones
                        String sObservaciones = "";

                        if (pQuery.equalsIgnoreCase("QRY01")) {
                            sObservaciones = (ordenL == 0 ? "" : this.validarContacto(items));
                            if (sObservaciones.isEmpty()) {
                                // Insertar los contactos en tablas temporales
                                this.insertarContacto(items, piLoteID, ordenL);
                                ordenL ++;
                            }
                        }

                        if (pQuery.equalsIgnoreCase("QRY02")) {
                            sObservaciones = (ordenL == 0 ? "" : this.validarLead(items));
                            if (sObservaciones.isEmpty()) {
                                // Insertar los lead's en tablas temporales
                                this.insertarLead(items, piLoteID, ordenL);
                                ordenL ++;
                            }
                        }

                        if (pQuery.equalsIgnoreCase("QRY03")) {
                            sObservaciones = (ordenL == 0 ? "" : this.validarContacto(items));
                            if (sObservaciones.isEmpty()) {
                                // Insertar los lead's en tablas temporales
                                this.insertarContacto(items, piLoteID, ordenL);
                                ordenL++;
                            }
                        }
                        
                        // Si no pasaron las validaciones registrar en rechazados
                        if (!sObservaciones.isEmpty()) {
                            this.insertarRechazados(piLoteID, r, sObservaciones, pQuery);
                            ordenR ++;
                        }

                    } catch (Exception ex) {
                        this.insertarRechazados(piLoteID, r, ex.getMessage(), pQuery);
                        ordenR ++;
                    }
                }

                // Siguiente archivo
                ze = zis.getNextEntry();
            }
            
            Util.Log("Inicio envio de grupos");
            if (ordenL -1 > 0) {
                // Enviar los archivos
                this.enviarGrupo(piLoteID, (ordenL - 1), pQuery);   
            } else {
                // Crear con error
                this.mergeCabecera(piLoteID, 0, // pGrupoID
                                   "0", // pCargaID
                                   pQuery, "ERROR", // pEstado
                                   "", // pObservaciones
                                   0) // pCantidad
                    ;
            }
            

        } catch (IOException e) {
            this.Mensaje("Lote N° " + piLoteID + ". IO.. " + e.getMessage());
        } catch (Exception e) {
            this.Mensaje("Lote N° " + piLoteID + ". Ex.. " + e.getMessage());
        }
        Util.Log("Final");
    }

    public void processFileUpload_OLD2(String pQuery, int piLoteID) {
        Util.Log("Inicio");
        ArrayList<String> items = null;
        try {

            // Obtener el archivo cargado
            UploadedFile uf = this.getFile();
            ZipInputStream zis = new ZipInputStream(uf.getInputStream());
            ZipEntry ze = zis.getNextEntry();
            int ordenL = 0;
            int ordenR = 0;
            
            Util.Log("Inicio insercion a tablas");
            while (ze != null) {
                // Recorrer el zip
                DataInputStream dis = new DataInputStream(zis);
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = dis.readLine()) != null) {
                    sb.append(line + " \n");
                }
                
                // Recorrer el string buffer
                ArrayList<String> rows = new ArrayList<String>(Arrays.asList(sb.toString().split("\n")));
                for (String r : rows) {
                    items = new ArrayList<String>(Arrays.asList(r.split(",")));
                    try {
                        Integer iOK = 0;
                        
                        if (pQuery.equalsIgnoreCase("QRY01")) {
                            // Insertar los contactos en tablas temporales
                            iOK = this.insertarContacto(items, piLoteID, ordenL);
                            ordenL += iOK; 
                        }

                        if (pQuery.equalsIgnoreCase("QRY02")) {
                            // Insertar los lead's en tablas temporales
                            iOK = this.insertarLead(items, piLoteID, ordenL);
                            ordenL += iOK;
                        }

                        if (pQuery.equalsIgnoreCase("QRY03")) {
                            // Insertar los lead's en tablas temporales
                            iOK = this.insertarContacto(items, piLoteID, ordenL);
                            ordenL += iOK;
                        }

                        // Si no pasaron las validaciones registrar en rechazados
                        if (iOK == 0) {
                            this.insertarRechazados(piLoteID, r, "Campos imcompletos", pQuery);
                            ordenR++;
                        }

                    } catch (Exception ex) {
                        this.insertarRechazados(piLoteID, r, ex.getMessage(), pQuery);
                        ordenR++;
                    }
                }

                // Siguiente archivo
                ze = zis.getNextEntry();
            }
            Util.Log("Inicio Validacion lote");

            // Validar lo registros insertados y obtener el nuevo de OK's
            ordenL = this.validarLote(piLoteID, pQuery);

            Util.Log("Inicio envio de grupos");
            if (ordenL > 0) {
                // Enviar los archivos
                this.enviarGrupo(piLoteID, ordenL, pQuery);
            } else {
                // Crear con error
                this.mergeCabecera(piLoteID, 
                                   1 ,       // pGrupoID 
                                   "0" ,     // pCargaID 
                                   pQuery, 
                                   "Error" , // pEstado 
                                   "Archivo erroneo" ,      // pObservaciones 
                                   0         // pCantidad
                                   );
            }

        } catch (IOException e) {
            this.Mensaje("Lote N° " + piLoteID + ". IO.. " + e.getMessage());
        } catch (Exception e) {
            this.Mensaje("Lote N° " + piLoteID + ". Ex.. " + e.getMessage());
        }
        Util.Log("Final");
    }

    public void processFileUpload(String pQuery, int piLoteID) {
        Util.Log("Inicio");
        ArrayList<String> items = null;
        try {

            // Obtener el archivo cargado
            UploadedFile uf = this.getFile();
            ZipInputStream zis = new ZipInputStream(uf.getInputStream());
            ZipEntry ze = zis.getNextEntry();
            int ordenL = 0;
            int ordenR = 0;

            Util.Log("Inicio insercion a tablas");
            while (ze != null) {
                // Recorrer el zip
                DataInputStream dis = new DataInputStream(zis);
                String line = null;
                while ((line = dis.readLine()) != null) {
                    
                    // Insertar data
                    items = new ArrayList<String>(Arrays.asList((line).split(",")));
                    try {
                        Integer iOK = 0;

                        if (pQuery.equalsIgnoreCase("QRY01")) {
                            // Insertar los contactos en tablas temporales
                            iOK = this.insertarContacto(items, piLoteID, ordenL);
                            ordenL += iOK;
                        }

                        if (pQuery.equalsIgnoreCase("QRY02")) {
                            // Insertar los lead's en tablas temporales
                           
                            iOK = this.insertarLead(items, piLoteID, ordenL);
                            ordenL += iOK;
                            
                            Util.Log("ordenL --> " + ordenL);
                        }

                        if (pQuery.equalsIgnoreCase("QRY03")) {
                            // Insertar los lead's en tablas temporales
                            iOK = this.insertarContacto(items, piLoteID, ordenL);
                            ordenL += iOK;
                        }

                        // Si no pasaron las validaciones registrar en rechazados
                        if (iOK == 0) {
                            this.insertarRechazados(piLoteID, line, "Campos imcompletos - ", pQuery);
                            ordenR++;
                        }

                    } catch (Exception ex) {
                        this.insertarRechazados(piLoteID, line, ex.getMessage(), pQuery);
                        ordenR++;
                    }
                }

                // Siguiente archivo
                ze = zis.getNextEntry();
            }
            Util.Log("Inicio Validacion lote");
            
            Util.Log("piLoteID --> " + piLoteID);

            // Validar lo registros insertados y obtener el nuevo de OK's
        //    ordenL = this.validarLote(piLoteID, pQuery);
            
            Util.Log("ordenL --> " + ordenL);
            
            // Inicio envio de grupos
            if (ordenL > 0) {
                // Enviar los archivos
                this.enviarGrupo(piLoteID, ordenL, pQuery);
            } else {
                // Crear con error
                this.mergeCabecera(piLoteID, 
                                   0, // pGrupoID
                                   "0", // pCargaID
                                   pQuery, "Error", // pEstado
                                   "Archivo erroneo", // pObservaciones
                                   0) // pCantidad
                                   ;
            }

        } catch (IOException e) {
            this.Mensaje("Lote N° " + piLoteID + ". IO.. " + e.getMessage());
        } catch (Exception e) {
            this.Mensaje("Lote N° " + piLoteID + ". Ex.. " + e.getMessage());
        }
        Util.Log("Final");
    }

    public Integer insertarContacto(List<String> pItems, int pLoteID, int pNroOrden) {
        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;
        Integer iReturn = 0;

        try {
            String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_insertarContacto(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
            cnx = sbpCnx.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setInt(1, pLoteID);
            ps.setInt(2, pNroOrden);
            int i = 3;
            for (String s : pItems) {
                ps.setString(i, s);
                i++;
            }
            ps.execute();
            ps.close();
            cnx.close();
            
            iReturn = 1;
        } catch (SQLException ex) {
            Util.Log(ex.getMessage());
            return 0;
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
        return iReturn;
    }

    public Integer insertarLead(List<String> pItems, int pLoteID, int pNroOrden) {
        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;
        Integer iReturn = 0;
        try {
            String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_insertarLead(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
            cnx = sbpCnx.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setInt(1, pLoteID);
            ps.setInt(2, pNroOrden);
            int i = 3;
            for (String s : pItems) {
                ps.setString(i, s);
                i++;
            }
            ps.execute();
            ps.close();
            cnx.close();
            
            iReturn = 1;
        } catch (SQLException ex) {
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
        return iReturn;
    }

    public void insertarRechazados(int pLoteID, String pTrama, String pObservaciones, String pQuery) {
        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_insertarRechazados(?,?,?,?); END;";
            conn = connection.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, pLoteID);
            ps.setString(2, pTrama);
            ps.setString(3, pObservaciones);
            ps.setString(4, pQuery);
            ps.execute();
            
            // cerrar conexion
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public void enviarGrupo(int piLoteID, int piCantidad, String psQuery) {
        Util.Log("Inicio");
        try {            
            // Calculos de grupos
            int iEnvTot = (int)Math.ceil(piCantidad / Double.parseDouble(Util.nvl(this.txtCxg, "1")));
        
            Util.Log("iEnvTot --> " + iEnvTot);
        
            int iMaxPll = Integer.parseInt(Util.nvl(this.txtPll, "15"));
        
            Util.Log("iMaxPll --> " + iMaxPll);
        
            int iMaxFor = iEnvTot < iMaxPll ? iEnvTot : iMaxPll;
        
            Util.Log("iMaxFor --> " + iMaxFor);
        
            int iCxg = Integer.parseInt(Util.nvl(this.txtCxg, "0"));
            
            Util.Log("iCxg --> " + iCxg);
                        
            int iIni = 1;
            int iFin = iCxg;
            int iFinAux = iFin;            

            for (int iGrupoID = 1; iGrupoID <= iMaxFor; iGrupoID++) {
               
                this.enviarGrupoHilo(psQuery, piLoteID, iGrupoID, iIni, iFin);

                iIni += iCxg;
                iFinAux += iCxg;
                iFin = (iFinAux < piCantidad ? iFinAux : piCantidad);
            }

            // Enviar grupos(hilos) restantes
            int iResEnv = iEnvTot - iMaxFor;
            Util.Log("iResEnv -- >" + iResEnv);
            if (iResEnv > 0) {
                int iIniB = iFinAux - iCxg;
                Thread.sleep(200000);
                
                // Hilo de Envio de Individual
                HiloBusqueda h = new HiloBusqueda(psQuery, 
                                                  piLoteID, 
                                                  iCxg, 
                                                  iIniB, 
                                                  piCantidad, 
                                                  iMaxFor, 
                                                  iEnvTot, 
                                                  iMaxPll);
                h.start();
            } 
            
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }
    
    public void enviarGrupoHilo(String psQuery, int piLoteID, int piGrupoID, int piInicio, int piFin){
        try {
            HiloEnvio h = new HiloEnvio(psQuery, 
                                        piLoteID, 
                                        piGrupoID, 
                                        piInicio, 
                                        piFin);
            h.start();
        } catch (Exception ex){
            Util.Log(ex.getMessage());
        }
    }

    public SimplifiedImportActivity enviarGrupoContacto(int piLoteID, int piInicio, int piFin, int piGrupoID, String psQuery) {
        
        SimplifiedImportActivity p = new SimplifiedImportActivity();
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoContacto(?,?,?,?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, piLoteID);
            st.setInt(2, piInicio);
            st.setInt(3, piFin);
            st.setInt(4, piGrupoID);
            st.setString(5, Util.nvl(this.txtUsu,""));
            st.registerOutParameter(6, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(6);

            if (rs1 != null) {
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    ZipEntry ze =  new ZipEntry("Contact.csv");
                    zos.putNextEntry(ze);

                    while (rs1.next()) {
                        String trama1 = rs1.getString("partynumber").trim() + "," +
                                        rs1.getString("personfirstname").trim() + "," +
                                        rs1.getString("personmiddlename").trim() + "," +
                                        rs1.getString("personlastname").trim() + "," +
                                        rs1.getString("personsecondlastname").trim() + "," +
                                        rs1.getString("dateofbirth").trim() + "," +
                                        rs1.getString("maritalstatus").trim() + "," +
                                        rs1.getString("gender").trim() + "," +
                                        rs1.getString("emailaddress").trim() + "," +
                                        rs1.getString("tipodocumento").trim() + "," +
                                        rs1.getString("numerodocumento").trim() + "," +
                                        rs1.getString("celular01").trim() + "," +
                                        rs1.getString("celular02").trim() + "," +
                                        rs1.getString("celular03").trim() + "," +
                                        rs1.getString("edad").trim() + "," +
                                        rs1.getString("telefono01").trim() + "," +
                                        rs1.getString("telefono02").trim() + "," +
                                        rs1.getString("telefono03").trim() + "," +
                                        rs1.getString("sl01nom").trim() + "," +
                                        rs1.getString("flagautorizacion").trim() + "," +
                                        rs1.getString("flagcuentasueldo").trim() + "," +
                                        rs1.getString("banco1_deu_rcctc").trim() + "," +
                                        rs1.getString("banco1_deuda_rccpp").trim() + "," +
                                        rs1.getString("banco1_lin_rcctc").trim() + "," +
                                        rs1.getString("banco1_nombre_rccpp").trim() + "," +
                                        rs1.getString("banco1_nombre_rcctc").trim() + "," +
                                        rs1.getString("banco2_deu_rcctc").trim() + "," +
                                        rs1.getString("banco2_deuda_rccpp").trim() + "," +
                                        rs1.getString("banco2_lin_rcctc").trim() + "," +
                                        rs1.getString("banco2_nombre_rccpp").trim() + "," +
                                        rs1.getString("banco2_nombre_rcctc").trim() + "," +
                                        rs1.getString("ban_fin_caj_edp_deu_rcctc").trim() + "," +
                                        rs1.getString("ban_fin_caj_edp_lin_rcctc").trim() + "," +
                                        rs1.getString("sbs_ban_fin_caj_edp_rccpp").trim() + "," +
                                        rs1.getString("PartySiteNumber").trim() + "," +
                                        rs1.getString("country").trim() + "," +
                                        rs1.getString("address1").trim() + "," +
                                        rs1.getString("state").trim() + "," +
                                        rs1.getString("city").trim() + "," +
                                        rs1.getString("county").trim() + "," +
                                        rs1.getString("id_lote").trim() + "," +
                                        rs1.getString("id_grupo").trim() + "\n";

                        byte[] bt = trama1.getBytes();
                        zos.write(bt, 0, bt.length);
                    }
                    zos.closeEntry();
                    zos.close();

                    rs1.close();
                    st.close();
                    cnx.close();

                    String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "CONTACT", str);

                    // Insertar a historial
                    this.insertarHistorial(piLoteID, piGrupoID, psQuery);
                    
                } catch (Exception ex) {
                    Util.Log("zip :: " + ex.getMessage());
                }
            }

        } catch (Exception ex) {
            Util.Log("Exception : " + ex);
        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }

        return p;
    }

    public ImportJobReturnParams enviarGrupoContactoCsv(int piLoteID, int piInicio, int piFin, int piGrupoID, String psQuery) {

        ImportJobReturnParams p = new ImportJobReturnParams();
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoContacto(?,?,?,?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, piLoteID);
            st.setInt(2, piInicio);
            st.setInt(3, piFin);
            st.setInt(4, piGrupoID);
            st.setString(5, Util.nvl(this.txtUsu, ""));
            st.registerOutParameter(6, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(6);

            if (rs1 != null) {

                StringBuffer sb = new StringBuffer();
                try {

                    while (rs1.next()) {
                        String trama1 = rs1.getString("partynumber").trim() + "," + 
                                        rs1.getString("personfirstname").trim() + "," + 
                                        rs1.getString("personmiddlename").trim() + "," + 
                                        rs1.getString("personlastname").trim() + "," + 
                                        rs1.getString("personsecondlastname").trim() + "," + 
                                        rs1.getString("dateofbirth").trim() + "," + 
                                        rs1.getString("maritalstatus").trim() + "," + 
                                        rs1.getString("gender").trim() + "," + 
                                        rs1.getString("emailaddress").trim() + "," + 
                                        rs1.getString("tipodocumento").trim() + "," + 
                                        rs1.getString("numerodocumento").trim() + "," + 
                                        rs1.getString("celular01").trim() + "," + 
                                        rs1.getString("celular02").trim() + "," + 
                                        rs1.getString("celular03").trim() + "," + 
                                        rs1.getString("edad").trim() + "," + 
                                        rs1.getString("telefono01").trim() + "," + 
                                        rs1.getString("telefono02").trim() + "," + 
                                        rs1.getString("telefono03").trim() + "," + 
                                        rs1.getString("sl01nom").trim() + "," + 
                                        rs1.getString("flagautorizacion").trim() + "," + 
                                        rs1.getString("flagcuentasueldo").trim() + "," + 
                                        rs1.getString("banco1_deu_rcctc").trim() + "," + 
                                        rs1.getString("banco1_deuda_rccpp").trim() + "," + 
                                        rs1.getString("banco1_lin_rcctc").trim() + "," + 
                                        rs1.getString("banco1_nombre_rccpp").trim() + "," + 
                                        rs1.getString("banco1_nombre_rcctc").trim() + "," + 
                                        rs1.getString("banco2_deu_rcctc").trim() + "," + 
                                        rs1.getString("banco2_deuda_rccpp").trim() + "," + 
                                        rs1.getString("banco2_lin_rcctc").trim() + "," + 
                                        rs1.getString("banco2_nombre_rccpp").trim() + "," + 
                                        rs1.getString("banco2_nombre_rcctc").trim() + "," + 
                                        rs1.getString("ban_fin_caj_edp_deu_rcctc").trim() + "," + 
                                        rs1.getString("ban_fin_caj_edp_lin_rcctc").trim() + "," + 
                                        rs1.getString("sbs_ban_fin_caj_edp_rccpp").trim() + "," + 
                                        rs1.getString("PartySiteNumber").trim() + "," + 
                                        rs1.getString("country").trim() + "," + 
                                        rs1.getString("address1").trim() + "," + 
                                        rs1.getString("state").trim() + "," + 
                                        rs1.getString("city").trim() + "," + 
                                        rs1.getString("county").trim() + "," + 
                                        rs1.getString("id_lote").trim() + "," + 
                                        rs1.getString("id_grupo").trim() + "\n";
                        sb.append(trama1);
                    }

                    rs1.close();
            
                    st.close();
                    cnx.close();
                    
                    
                    //Util.Log("StringBuffer : " + sb);
                    ImportLeadResource ilr = new ImportLeadResource();
                    Util.Log(piLoteID + "-" + piGrupoID + "-" + "SBP - Importar LeadResource, Contactos CSV");
                    p = ilr.importActivity(sb,piLoteID + "-" + piGrupoID + "-" + "SBP - Importar LeadResource, Contactos CSV", "300000112934378");

                    // Enviar a historial
                    this.insertarHistorial(piLoteID, piGrupoID, psQuery);

                } catch (Exception ex) {
                    Util.Log("zip :: " + ex.getMessage());
                }
            }

        } catch (Exception ex) {
            Util.Log("Exception : " + ex);

        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }

        return p;
    }

    public SimplifiedImportActivity enviarGrupoLead(int piLoteID, int piInicio, int piFin, int piGrupoID, String psQuery) {       
        
       
        SimplifiedImportActivity p = new SimplifiedImportActivity();
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoLead(?,?,?,?,?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, piLoteID);
            st.setInt(2, piInicio);
            st.setInt(3, piFin);
            st.setInt(4, piGrupoID);
            st.setString(5, Util.nvl(this.txtUsu, ""));
            st.registerOutParameter(6, OracleTypes.CURSOR);
            st.registerOutParameter(7, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(6);
            rs2 = (ResultSet)st.getObject(7);

            if (rs1 != null) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    ZipEntry ze = new ZipEntry("Lead.csv");
                    zos.putNextEntry(ze);

                    while (rs1.next()) {
                        String trama1 = rs1.getString("LeadNumber").trim() + "," + 
                                        rs1.getString("LeadName").trim() + "," + 
                                        rs1.getString("OwnerId").trim() + "," + 
                                        rs1.getString("PRIMARYCONTACTNUMBER").trim() + "," + 
                                        rs1.getString("CpgSBP_LEAD".trim()) + "," + 
                                        rs1.getString("PartnerId").trim() + "," + 
                                        rs1.getString("Flujo").trim() + "," + 
                                        rs1.getString("MontoMin").trim() + "," + 
                                        rs1.getString("MontoMax").trim() + "," + 
                                        rs1.getString("PlazoMin").trim() + "," + 
                                        rs1.getString("PlazoMax").trim() + "," + 
                                        rs1.getString("TEAMin").trim() + "," + 
                                        rs1.getString("TEAMax").trim() + "," + 
                                        rs1.getString("TCEAMin").trim() + "," + 
                                        rs1.getString("TCEAMax").trim() + "," + 
                                        rs1.getString("TCNombreBIN1").trim() + "," + 
                                        rs1.getString("TCPCT1").trim() + "," + 
                                        rs1.getString("TCTEA1").trim() + "," + 
                                        rs1.getString("TCNombreBIN2").trim() + "," + 
                                        rs1.getString("TCPCT2").trim() + "," + 
                                        rs1.getString("TCTEA2").trim() + "," + 
                                        rs1.getString("TCNombreBIN3").trim() + "," + 
                                        rs1.getString("TCPCT3").trim() + "," + 
                                        rs1.getString("TCTEA3").trim() + "," + 
                                        rs1.getString("TCNombreBIN4").trim() + "," + 
                                        rs1.getString("TCPCT4").trim() + "," + 
                                        rs1.getString("TCTEA4").trim() + "," + 
                                        rs1.getString("Propension").trim() + "," + 
                                        rs1.getString("Decil").trim() + "," + 
                                        rs1.getString("MontoProducto").trim() + "," + 
                                        rs1.getString("Cuota").trim() + "," + 
                                        rs1.getString("Producto").trim() + "," + 
                                        rs1.getString("PROMOCION").trim() + "," +
                                        rs1.getString("id_lote").trim() + "," + 
                                        rs1.getString("id_grupo").trim() + "\n";

                        byte[] bt = trama1.getBytes();
                        
                        Util.Log("TRAMA 1 -- >" + trama1);
                        zos.write(bt, 0, bt.length);
                    }

                    // Cerrar Entry
                    zos.closeEntry();

                    if (rs2 != null) {

                        ZipEntry zef = new ZipEntry("Feedback_Lead_c.csv");
                        zos.putNextEntry(zef);

                        while (rs2.next()) {
                            String trama2 = rs2.getString("LEAD_NUMBER").trim() + "," + 
                                            rs2.getString("RecordName").trim() + "," + 
                                            rs2.getString("TipoGestion").trim() + "," + 
                                            rs2.getString("GrupoGestion").trim() + "," + 
                                            rs2.getString("DetalleGestion").trim() + "," + 
                                            rs2.getString("AuxFeedbackInicial").trim() + "\n";

                            byte[] bt = trama2.getBytes();
                            Util.Log("TRAMA 2 -- >" + trama2);
                            zos.write(bt, 0, bt.length);
                        }
                        rs2.close();

                        // cerrar el Entry (Feedbacks.csv)
                        zos.closeEntry();

                    }

                    // Cerrar el zip
                    zos.close();

                    // cerrar conexiones
                    rs1.close();
                    st.close();
                    cnx.close();
                    Util.Log("2");
                    String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);
                    Util.Log("3");
                
                    if (p.getJobID().equalsIgnoreCase("0")) {
                        Util.Log("p.getJobID   : " + p.getJobID());
                        Util.Log("p.getStatus  : " + p.getStatus());
                        Util.Log("p.getMensaje : " + p.getMensaje());
                    }
                    else {
                        // Enviar a historial lo cargado
                        this.insertarHistorial(piLoteID, piGrupoID, psQuery);
                    }

                } catch (Exception ex) {
                    Util.Log("fos :: " + ex.getMessage());
                    ex.printStackTrace();
                    p.setObservaciones(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            Util.Log("Exception : " + ex);
            p.setObservaciones(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(rs2);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }

        return p;
    }

    public void insertarHistorial(int piLoteID, int piGrupoID, String psQuery) {

        PreparedStatement ps = null;
        sbp.utils.Connection cnx = new sbp.utils.Connection();
        Connection conn = null;

        try {
            String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_insertarHistorial(?,?,?); END;";
            conn = cnx.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, piLoteID);
            ps.setInt(2, piGrupoID);
            ps.setString(3, psQuery);
            ps.execute();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    public static String validarContacto(List<String> pItems) {
        String sObservaciones = "";
        try {

            // Validacion cantidad campos
            if (pItems.size() != 40) {
                sObservaciones += "Campos incompletos";
            } else {
                // Validar PartyNumber
                if (pItems.get(0).isEmpty()/* || !Util.isNumeric(pItems.get(0))*/) {
                    sObservaciones += "PartyNumber incorrecto, ";
                }

                // Validar PersonFirstName
                if (pItems.get(1).isEmpty()) {
                    sObservaciones += "PersonFirstName vacio, ";
                }

                // Validar PersonLastName
                if (pItems.get(3).isEmpty()) {
                    sObservaciones += "PersonLastName vacio, ";
                }

                // Validar PartySiteNumber
                if (pItems.get(34).isEmpty()/* || !Util.isNumeric(pItems.get(34))*/) {
                    sObservaciones += "PartySiteNumber incorrecto ";
                }
            }
        } catch (Exception ex) {
            sObservaciones = ex.getMessage();
        }
        return sObservaciones;
    }

    public static String validarLead(List<String> pItems) {
        
        String sObservaciones = "";

        try {

            // Validacion cantidad campos
            if (pItems.size() != 33) {
                sObservaciones = "Campos incompletos";
            } else {
                // Validar LeadNumber
                if (pItems.get(0).isEmpty()) {
                    sObservaciones += "LeadNumber incorrecto, ";
                }

                // Validar LeadName
                if (pItems.get(1).isEmpty()) {
                    sObservaciones += "LeadName vacio, ";
                }

                // Validar existe contacto
              /*  if (!existeContacto(pItems.get(3))) {
                    sObservaciones += "Contacto no existe";
                }*/
            }
        } catch (Exception ex) {
            sObservaciones = ex.getMessage();
        }
        return sObservaciones;
    }
    
    public static Integer validarLote(int piLoteID, String psQuery) {
        Util.Log("Inicio");
        Connection cnx = null;
        CallableStatement st = null;
        Integer iReturn = 0;
        
        Util.Log("piLoteID --> " + piLoteID);
        
        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_validarLote(?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, piLoteID);
            st.setString(2, psQuery);
            st.registerOutParameter(3, OracleTypes.INTEGER);
            st.execute();
            
            // Valores de retorno
            iReturn = st.getInt(3);
            
            Util.Log("iReturn --> " + iReturn);
            
            cnx.close();
            st.close();
            
        } catch (Exception ex){
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        
        return iReturn;
    }
    
    public static Boolean existeContacto(String sValor) {
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;
        Boolean bExiste = false;
        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_existeContacto(?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setString(1, sValor);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(2);

            if (rs1 != null) {
                //Util.Log("Devolvio resultados");
                while (rs1.next()) {
                    bExiste = Boolean.parseBoolean(rs1.getString("EXISTE_CONTACTO"));
                    //Util.Log(bExiste);
                }
                rs1.close();
            }

            cnx.close();
            st.close();

        } catch (Exception ex) {
            Util.Log("Exception : " + ex.getMessage());
            bExiste = false;
        }         
        finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        return bExiste;
    }

    /** ------------------------------------------------------------------------
     * Metodo para reenviar una carga 
     * -------------------------------------------------------------------------
     * @param pLoteID
     * @param pGrupoID
     * @param pQuery
     */
    public void reenviarGrupo(int pLoteID, int pGrupoID, String pQuery){
        SimplifiedImportActivity p = new SimplifiedImportActivity();
        String sEstado = "Reenviado";
        try {
            
            // Reenviar grupo
            if (pQuery.equalsIgnoreCase("QRY01")){
                p = this.reenviarGrupoContacto(pLoteID, pGrupoID);
            } else if (pQuery.equalsIgnoreCase("QRY02")){
                p = this.reenviarGrupoLead(pLoteID, pGrupoID);
            } else if (pQuery.equalsIgnoreCase("QRY03")) {
                ImportJobReturnParams p2 = this.reenviarGrupoContactoCsv(pLoteID, pGrupoID);
                p.setJobID(p2.getJobId().getValue() + "");
                p.setStatus(p2.getResult().getValue());
                p.setObservaciones(p2.getResult().getValue());
            }

            // Setear observaciones
            if (p.getJobID().equalsIgnoreCase("") || p.getJobID().isEmpty()) {
                Util.Log(p.getMensaje());
                p.setObservaciones(Util.nvl(p.getStatus() + ". " + p.getFailureCause(), p.getMensaje()));
                sEstado = "OBSERVADO";
            }

            // actualizar estatus e ID_CARGA
            this.mergeCabecera(pLoteID, pGrupoID, p.getJobID(), pQuery, sEstado, p.getObservaciones(), 0);
            
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    public SimplifiedImportActivity reenviarGrupoContacto(int pLoteID, int pGrupoID) {

        SimplifiedImportActivity p = new SimplifiedImportActivity();
            
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoContactoReenvio(?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, pLoteID);
            st.setInt(2, pGrupoID);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(3);

            // Construir el zip
            if (rs1 != null) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    ZipEntry ze =  new ZipEntry("Contact.csv");
                    zos.putNextEntry(ze);
                    
                    while (rs1.next()) {
                        String trama1 = rs1.getString("partynumber").trim() + "," + 
                                        rs1.getString("personfirstname").trim() + "," + 
                                        rs1.getString("personmiddlename").trim() + "," + 
                                        rs1.getString("personlastname").trim() + "," + 
                                        rs1.getString("personsecondlastname").trim() + "," + 
                                        rs1.getString("dateofbirth").trim() + "," + 
                                        rs1.getString("maritalstatus").trim() + "," + 
                                        rs1.getString("gender").trim() + "," + 
                                        rs1.getString("emailaddress").trim() + "," + 
                                        rs1.getString("tipodocumento").trim() + "," + 
                                        rs1.getString("numerodocumento").trim() + "," + 
                                        rs1.getString("celular01").trim() + "," + 
                                        rs1.getString("celular02").trim() + "," + 
                                        rs1.getString("celular03").trim() + "," + 
                                        rs1.getString("edad").trim() + "," + 
                                        rs1.getString("telefono01").trim() + "," + 
                                        rs1.getString("telefono02").trim() + "," + 
                                        rs1.getString("telefono03").trim() + "," + 
                                        rs1.getString("sl01nom").trim() + "," + 
                                        rs1.getString("flagautorizacion").trim() + "," + 
                                        rs1.getString("flagcuentasueldo").trim() + "," + 
                                        rs1.getString("banco1_deu_rcctc").trim() + "," + 
                                        rs1.getString("banco1_deuda_rccpp").trim() + "," + 
                                        rs1.getString("banco1_lin_rcctc").trim() + "," + 
                                        rs1.getString("banco1_nombre_rccpp").trim() + "," + 
                                        rs1.getString("banco1_nombre_rcctc").trim() + "," + 
                                        rs1.getString("banco2_deu_rcctc").trim() + "," + 
                                        rs1.getString("banco2_deuda_rccpp").trim() + "," + 
                                        rs1.getString("banco2_lin_rcctc").trim() + "," + 
                                        rs1.getString("banco2_nombre_rccpp").trim() + "," + 
                                        rs1.getString("banco2_nombre_rcctc").trim() + "," + 
                                        rs1.getString("ban_fin_caj_edp_deu_rcctc").trim() + "," + 
                                        rs1.getString("ban_fin_caj_edp_lin_rcctc").trim() + "," + 
                                        rs1.getString("sbs_ban_fin_caj_edp_rccpp").trim() + "," + 
                                        rs1.getString("PartySiteNumber").trim() + "," + 
                                        rs1.getString("country").trim() + "," + 
                                        rs1.getString("address1").trim() + "," + 
                                        rs1.getString("state").trim() + "," + 
                                        rs1.getString("city").trim() + "," + 
                                        rs1.getString("county").trim() + "," + 
                                        rs1.getString("id_lote").trim() + "," + 
                                        rs1.getString("id_grupo").trim() + "\n";
    
                        byte[] bt = trama1.getBytes();
                        zos.write(bt, 0, bt.length);
    
                    }
                    zos.closeEntry();
                    zos.close();

                    String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "CONTACT", str);
                    
                } catch (Exception ex) {
                    Util.Log("zip :: " + ex.getMessage());
                }

                rs1.close();
            }

        } catch (Exception ex) {
            Util.Log("Exception : " + ex);
            p.setObservaciones(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        
        return p;
    }

    public SimplifiedImportActivity reenviarGrupoLead(int pLoteID, int pGrupoID) {

        SimplifiedImportActivity p = new SimplifiedImportActivity();
        
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoLeadReenvio(?,?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, pLoteID);
            st.setInt(2, pGrupoID);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(3);
            rs2 = (ResultSet)st.getObject(4);
            // Construir el zip
            if (rs1 != null) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    ZipEntry ze = new ZipEntry("Lead.csv");
                    zos.putNextEntry(ze);

                    while (rs1.next()) {
                        String trama1 = rs1.getString("LeadNumber").trim() + "," + 
                                        rs1.getString("LeadName").trim() + "," + 
                                        rs1.getString("OwnerId").trim() + "," + 
                                        rs1.getString("PRIMARYCONTACTNUMBER").trim() + "," + 
                                        rs1.getString("CpgSBP_LEAD").trim() + "," + 
                                        rs1.getString("PartnerId").trim() + "," + 
                                        rs1.getString("Flujo").trim() + "," + 
                                        rs1.getString("MontoMin").trim() + "," + 
                                        rs1.getString("MontoMax").trim() + "," + 
                                        rs1.getString("PlazoMin").trim() + "," + 
                                        rs1.getString("PlazoMax").trim() + "," + 
                                        rs1.getString("TEAMin").trim() + "," + 
                                        rs1.getString("TEAMax").trim() + "," + 
                                        rs1.getString("TCEAMin").trim() + "," + 
                                        rs1.getString("TCEAMax").trim() + "," + 
                                        rs1.getString("TCNombreBIN1").trim() + "," + 
                                        rs1.getString("TCPCT1").trim() + "," + 
                                        rs1.getString("TCTEA1").trim() + "," + 
                                        rs1.getString("TCNombreBIN2").trim() + "," + 
                                        rs1.getString("TCPCT2").trim() + "," + 
                                        rs1.getString("TCTEA2").trim() + "," + 
                                        rs1.getString("TCNombreBIN3").trim() + "," + 
                                        rs1.getString("TCPCT3").trim() + "," + 
                                        rs1.getString("TCTEA3").trim() + "," + 
                                        rs1.getString("TCNombreBIN4").trim() + "," + 
                                        rs1.getString("TCPCT4").trim() + "," + 
                                        rs1.getString("TCTEA4").trim() + "," + 
                                        rs1.getString("Propension").trim() + "," + 
                                        rs1.getString("Decil").trim() + "," + 
                                        rs1.getString("MontoProducto").trim() + "," + 
                                        rs1.getString("Cuota").trim() + "," + 
                                        rs1.getString("Producto").trim() + "," + 
                                        rs1.getString("id_lote").trim() + "," + 
                                        rs1.getString("id_grupo").trim() + "\n";

                        byte[] bt = trama1.getBytes();
                        zos.write(bt, 0, bt.length);
    
                    }
                    zos.closeEntry();

                    if (rs2 != null) {

                        ZipEntry zef = new ZipEntry("Feedback_Lead_c.csv");
                        zos.putNextEntry(zef);
                        
                        while (rs2.next()) {
                            String trama2 = rs2.getString("LEAD_NUMBER").trim() + "," + 
                                            rs2.getString("RecordName").trim() + "," + 
                                            rs2.getString("TipoGestion").trim() + "," + 
                                            rs2.getString("GrupoGestion").trim() + "," + 
                                            rs2.getString("DetalleGestion").trim() + "," + 
                                            rs2.getString("AuxFeedbackInicial").trim() + "\n";

                            byte[] bt = trama2.getBytes();
                            zos.write(bt, 0, bt.length);
                        }
                        rs2.close();
                        
                        // cerrar el Entry (Feedbacks.csv)
                        zos.closeEntry();
                    }
                
                    // Cerrar el zip
                    zos.close();

                    String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                } catch (Exception ex) {
                    Util.Log("fos :: " + ex.getMessage());
                }
    
                rs1.close();
            }
            st.close();
            cnx.close();

        } catch (Exception ex) {
            Util.Log("Exception : " + ex);
        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(rs2);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        
        return p;
    }

    public ImportJobReturnParams reenviarGrupoContactoCsv(int pLoteID, int pGrupoID) {

        ImportJobReturnParams p = new ImportJobReturnParams();
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoContactoReenvio(?,?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, pLoteID);
            st.setInt(2, pGrupoID);
            st.setString(3, Util.nvl(this.txtUsu, ""));
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(4);

            if (rs1 != null) {

                StringBuffer sb = new StringBuffer();
                try {

                    while (rs1.next()) {
                        String trama1 = rs1.getString("partynumber").trim() + "," + 
                                        rs1.getString("personfirstname").trim() + "," + 
                                        rs1.getString("personmiddlename").trim() + "," + 
                                        rs1.getString("personlastname").trim() + "," + 
                                        rs1.getString("personsecondlastname").trim() + "," + 
                                        rs1.getString("dateofbirth").trim() + "," + 
                                        rs1.getString("maritalstatus").trim() + "," + 
                                        rs1.getString("gender").trim() + "," + 
                                        rs1.getString("emailaddress").trim() + "," + 
                                        rs1.getString("tipodocumento").trim() + "," + 
                                        rs1.getString("numerodocumento").trim() + "," + 
                                        rs1.getString("celular01").trim() + "," + 
                                        rs1.getString("celular02").trim() + "," + 
                                        rs1.getString("celular03").trim() + "," + 
                                        rs1.getString("edad").trim() + "," + 
                                        rs1.getString("telefono01").trim() + "," + 
                                        rs1.getString("telefono02").trim() + "," + 
                                        rs1.getString("telefono03").trim() + "," + 
                                        rs1.getString("sl01nom").trim() + "," + 
                                        rs1.getString("flagautorizacion").trim() + "," + 
                                        rs1.getString("flagcuentasueldo").trim() + "," + 
                                        rs1.getString("banco1_deu_rcctc").trim() + "," + 
                                        rs1.getString("banco1_deuda_rccpp").trim() + "," + 
                                        rs1.getString("banco1_lin_rcctc").trim() + "," + 
                                        rs1.getString("banco1_nombre_rccpp").trim() + "," + 
                                        rs1.getString("banco1_nombre_rcctc").trim() + "," + 
                                        rs1.getString("banco2_deu_rcctc").trim() + "," + 
                                        rs1.getString("banco2_deuda_rccpp").trim() + "," + 
                                        rs1.getString("banco2_lin_rcctc").trim() + "," + 
                                        rs1.getString("banco2_nombre_rccpp").trim() + "," + 
                                        rs1.getString("banco2_nombre_rcctc").trim() + "," + 
                                        rs1.getString("ban_fin_caj_edp_deu_rcctc").trim() + "," + 
                                        rs1.getString("ban_fin_caj_edp_lin_rcctc").trim() + "," + 
                                        rs1.getString("sbs_ban_fin_caj_edp_rccpp").trim() + "," + 
                                        rs1.getString("PartySiteNumber").trim() + "," + 
                                        rs1.getString("country").trim() + "," + 
                                        rs1.getString("address1").trim() + "," + 
                                        rs1.getString("state").trim() + "," + 
                                        rs1.getString("city").trim() + "," + 
                                        rs1.getString("county").trim() + "," + 
                                        rs1.getString("id_lote").trim() + "," + 
                                        rs1.getString("id_grupo").trim() + "\n";
                        sb.append(trama1);
                    }

                    ImportLeadResource ilr = new ImportLeadResource();
                    p = ilr.importActivity(sb, "SBP - Importar LeadResource, Contactos CSV", "300000112934378");

                } catch (Exception ex) {
                    Util.Log("zip :: " + ex.getMessage());
                }

                rs1.close();
            }
            
            st.close();
            cnx.close();

        } catch (Exception ex) {
            Util.Log("Exception : " + ex);

        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }

        return p;
    }
    
    public void mergeCabecera(int pLoteID, int pGrupoID, String pCargaID, String pQuery, String pEstado, String pObservaciones, int pCantidad) {
        Util.Log("Inicio");
        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_mergeCabecera(?,?,?,?,?,?,?,?); END;";

        try {

            cnx = sbpCnx.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setInt(1, pLoteID);
            ps.setInt(2, pGrupoID);
            ps.setString(3, pCargaID);
            ps.setString(4, pQuery);
            ps.setString(5, pEstado);
            ps.setString(6, Util.nvl(this.txtUsu, ""));
            ps.setString(7, pObservaciones);
            ps.setInt(8, pCantidad);

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

    /** ------------------------------------------------------------------------
     * Metodo para eliminar una carga
     * -------------------------------------------------------------------------
     * @param pLoteID
     * @param pGrupoID
     * @param sQuery
     */
    public void eliminar(int pLoteID, int pGrupoID, String sQuery, String sJobId) {
        try {
            if (!sJobId.equalsIgnoreCase("0")) {
                if (sQuery.equalsIgnoreCase("QRY02")){
                    this.enviarEliminarLead(pLoteID, pGrupoID);
                }
            }
            this.eliminarHistorial(pLoteID, pGrupoID, sQuery);
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }
    
    public SimplifiedImportActivity enviarEliminarLead(int pLoteID, int pGrupoID) {
        
        SimplifiedImportActivity p = new SimplifiedImportActivity();
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs1 = null;

        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerGrupoLeadEliminar(?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, pLoteID);
            st.setInt(2, pGrupoID);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.execute();

            rs1 = (ResultSet)st.getObject(3);

            if (rs1 != null) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    ZipEntry ze =  new ZipEntry("Lead.csv");
                    zos.putNextEntry(ze);

                    while (rs1.next()) {
                        String trama1 = rs1.getString("trama").trim();
    
                            byte[] bt = trama1.getBytes();
                            zos.write(bt, 0, bt.length);
    
                    }
                    zos.closeEntry();

                    // Cerrar el zip
                    zos.close();

                    String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                } catch (Exception ex) {
                    Util.Log("fos :: " + ex.getMessage());
                }

                rs1.close();
            }
            st.close();
            cnx.close();

        } catch (Exception ex) {
            Util.Log("Exception : " + ex);
            p.setObservaciones(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        return p;
    }

    public void eliminarHistorial(int pLoteID, int pGrupoID, String pQuery) {
        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_eliminarHistorial(?,?,?); END;";

        try {

            cnx = sbpCnx.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setInt(1, pLoteID);
            ps.setInt(2, pGrupoID);
            ps.setString(3, pQuery);

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

    public void cbBuscar_click(ActionEvent actionEvent) {
        try {
            this.buscarCargas();
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }
    
    public void buscarCargas(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Validaciones
        if ((Util.nvl(this.idIni, "").isEmpty() ||
             Util.nvl(this.idFin, "").isEmpty()) &&
             Util.nvl(this.itIdL, "").isEmpty()) {
        
            this.Mensaje("Ingrese parametros de busqueda");
            return;
        } else {
            
            if (Util.nvl(this.itIdL, "").isEmpty()){
            
                try {
                
                    Date di = sdf.parse(sdf.format(this.idIni.getValue()));
                    Date df = sdf.parse(sdf.format(this.idFin.getValue()));
                
                    if (df.before(di)) {
                        this.Mensaje("Ingrese un rango correcto de fechas");
                        return;
                    }
                } catch (ParseException ex) {
                    this.Mensaje(ex.getMessage());
                    return;
                } catch (Exception ex) {
                    this.Mensaje(ex.getMessage());
                    return;
                }
            }
        }
        
        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs = null;
        
        try {
            
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerCargas(?,?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);

            st.setString(1, !Util.nvl(this.idIni, "").isEmpty() ? sdf.format(this.idIni.getValue()) : "");
            st.setString(2, !Util.nvl(this.idFin, "").isEmpty() ? sdf.format(this.idFin.getValue()) : "");
            st.setInt(3, Integer.parseInt(Util.nvl(this.itIdL, "0")));
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.execute();
            rs = (ResultSet)st.getObject(4);
            
            // Obtener la tabla (pantalla)
            CollectionModel cm = (CollectionModel)this.tcM.getValue();
            JUCtrlHierBinding hb = (JUCtrlHierBinding)cm.getWrappedData(); //devuelve el table binding
            DCIteratorBinding ib = hb.getDCIteratorBinding();

            // Eliminar data existente
            int t = ib.getRowSetIterator().getRowCount();
            for (int i = 0; i < t; i++) {
                ib.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            // Mostrar dato en la tabla (pantalla)
            Row r = null;
            if (rs != null) {
                while (rs.next()) {
                    r = ib.getNavigatableRowIterator().createRow();
                    ib.getNavigatableRowIterator().insertRow(r);
                    r.setNewRowState(Row.STATUS_INITIALIZED);
                    
                    r.setAttribute("IdLote", rs.getString("ID_LOTE"));
                    r.setAttribute("IdGrupo", rs.getString("ID_GRUPO"));                    
                    r.setAttribute("FechaCargaS", rs.getString("FECHA_CARGA_S"));
                    r.setAttribute("TipoCarga", rs.getString("TIPO_CARGA"));
                    r.setAttribute("DescripcionCarga", rs.getString("DESCRIPCION_CARGA"));
                    r.setAttribute("IdCargaSbp", rs.getString("ID_CARGA_SBP"));
                    r.setAttribute("UsuarioCarga", rs.getString("USUARIO_CARGA"));
                    r.setAttribute("Cantidad", rs.getString("CANTIDAD"));
                    r.setAttribute("Observaciones", rs.getString("OBSERVACIONES"));
                    
                    String sEstado = rs.getString("ESTADO");
                    //Util.Log("1::" + sEstado);
                    if (!sEstado.equalsIgnoreCase("Completed") && 
                        !sEstado.equalsIgnoreCase("Observado") ) {
                        
                        if (rs.getString("ID_CARGA_SBP") != null &&
                            !rs.getString("ID_CARGA_SBP").equalsIgnoreCase("0")) {

                            MergeLeadInvoke mlc = new MergeLeadInvoke();
                            ImportActivityStatus s = mlc.getImportActivityStatus(Util.DatosConexionSBP(), rs.getString("ID_CARGA_SBP"));
                            
                            // Actualizar estado de carga
                            this.mergeCabecera(Integer.parseInt(rs.getString("ID_LOTE")), 
                                               Integer.parseInt(rs.getString("ID_GRUPO")), 
                                               rs.getString("ID_CARGA_SBP"), 
                                               rs.getString("TIPO_CARGA"), 
                                               s.getStatus(), 
                                               s.getLoadErrors().equalsIgnoreCase("0") ? "" : s.getLoadErrors(),
                                               0);
                            
                            sEstado = s.getStatus();
                            //Util.Log("2::" + sEstado);
                        } 
                    } 
                    
                    r.setAttribute("Estado", sEstado);
                    //Util.Log("3::" + sEstado);

                }
                rs.close();
            }
            
            st.close();
            cnx.close();

        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
    }

    public void cbReenviar_click(ActionEvent actionEvent) {
        try {
            // Obtener data
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsCmView1Iterator");
            Row r = ib.getViewObject().getCurrentRow();
            
            Util.Log(r.getAttribute("IdLote").toString());
            Util.Log(r.getAttribute("IdGrupo").toString());
            Util.Log(r.getAttribute("TipoCarga").toString());
            
            // Reenviar carga
            this.reenviarGrupo(Integer.parseInt(r.getAttribute("IdLote").toString()), 
                               Integer.parseInt(r.getAttribute("IdGrupo").toString()), 
                               r.getAttribute("TipoCarga").toString());
            
            // Actualizar listado
            this.buscarCargas();
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    public void cbEliminar_click(ActionEvent actionEvent) {
        try {
            // Obtener data
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsCmView1Iterator");
            Row r = ib.getViewObject().getCurrentRow();

            //Util.Log(r.getAttribute("IdLote").toString());
            //Util.Log(r.getAttribute("IdGrupo").toString());
            //Util.Log(r.getAttribute("TipoCarga").toString());
            //Util.Log(r.getAttribute("IdCargaSbp").toString());

            // Eliminar carga
            this.eliminar(Integer.parseInt(r.getAttribute("IdLote").toString()), 
                          Integer.parseInt(r.getAttribute("IdGrupo").toString()), 
                          r.getAttribute("TipoCarga").toString(), 
                          r.getAttribute("IdCargaSbp").toString());

            // Actualizar listado
            this.buscarCargas();
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }
    
    private void Mensaje(String sMensaje) {
        // setear valor mensaje
        this.olMensaje.setValue(sMensaje);
        
        // abrir popup
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPmensaje().show(hints);

    }

    public void cbSincronizar_click_20180122(ActionEvent actionEvent) {
        ServiceBiPublisher sbp = new ServiceBiPublisher();

        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;

        try {
            // Obtener listado de Contactos
            ListaContacto lc = sbp.obtenerContactos();
            
            String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_mergeContacto(?,?); END;";
            cnx = sbpCnx.getConnection();
            ps = cnx.prepareStatement(query);
            
            // Actualizar contacto
            for (Contacto c : lc.Contactos) {
                ps.setString(1, c.getPrimaryContactId());
                ps.setString(2, c.getPartyNumber());

                ps.execute();
            }
            ps.close();
            cnx.close();
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }

    public void cbSincronizar_click(ActionEvent actionEvent) {
        ServiceBiPublisher sbp = new ServiceBiPublisher();
        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;

        try {

            // Obtener listado de Contactos
            ListaContacto lc = sbp.obtenerContactos();
            
            Util.Log(lc.Contactos.size() + "lc.Contactos.size()");

            if (lc.Contactos.size() > 0){
                
                // establecer conexion
                cnx = sbpCnx.getConnection();
                String query = "";
                // Truncar tabla contactos
                query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_truncarContacto; END;";
                ps = cnx.prepareStatement(query);
                ps.execute();
                // Insertar contactos
                query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_insertarContacto(?,?); END;";
                ps = cnx.prepareStatement(query);
    
                // Actualizar contacto
                for (Contacto c : lc.Contactos) {
                    ps.setString(1, c.getPrimaryContactId());
                    ps.setString(2, c.getPartyNumber());
                    ps.execute();
                }
                ps.close();
                cnx.close();
            }

        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }

    public Integer getHilosParalelo(int piLoteID, int piMaxPll) {

        Connection cnx = null;
        CallableStatement st = null;
        ResultSet rs = null;
        
        Integer iEstadoCon = 0;
        Integer iResultado = 0;
        try {
            String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_obtenerCargas(?,?,?); end;";

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setInt(1, piLoteID);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.registerOutParameter(3, OracleTypes.VARCHAR);
            st.execute();
            rs = (ResultSet)st.getObject(2);
            int iTotalRegistros = Integer.parseInt(st.getObject(3).toString());
            Util.Log("iTotalRegistros :: " + iTotalRegistros);
            if (rs != null) {
                while (rs.next()) {
                    String sEstadoAux = rs.getString("ESTADO");
                    // Buscar los 
                    if (!sEstadoAux.equalsIgnoreCase("Completed")){
                        MergeLeadInvoke mlc = new MergeLeadInvoke();
                        ImportActivityStatus s = mlc.getImportActivityStatus(Util.DatosConexionSBP(), rs.getString("ID_CARGA_SBP"));

                        // Actualizar estado de carga
                        this.mergeCabecera(Integer.parseInt(rs.getString("ID_LOTE")), 
                                           Integer.parseInt(rs.getString("ID_GRUPO")), 
                                           rs.getString("ID_CARGA_SBP"), 
                                           rs.getString("TIPO_CARGA"), 
                                           s.getStatus(), 
                                           s.getLoadErrors().equalsIgnoreCase("0") ? "" : s.getLoadErrors(), 
                                           0);
                        sEstadoAux = s.getStatus();
                    }
                    
                  
                    // Contar los "Completed"
                    if (sEstadoAux.equalsIgnoreCase("Completed") ||
                        sEstadoAux.equalsIgnoreCase("Completed with errors") ||
                        sEstadoAux.equalsIgnoreCase("Unsuccessful")) {
                        iEstadoCon ++;
                    }
                }
                rs.close();
                st.close();
                cnx.close();

                Util.Log( "iEstadoCon :: " + iEstadoCon);
                
                iResultado = iTotalRegistros - iEstadoCon;
                
            }
            
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
            iResultado = piMaxPll;
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        return iResultado;
    }

    public void processFileUploadXML(String pQuery, int piLoteID) {
        ArrayList<String> items = null;
        try {

            // Obtener el archivo cargado
            UploadedFile uf = this.getFile();
            ZipInputStream zis = new ZipInputStream(uf.getInputStream());
            ZipEntry ze = zis.getNextEntry();
            int ordenL = 0;
            int ordenLAux = 1;  
            int ordenR = 0;
            
            // Incializar trama XML
            
            String sXML = "";
            if (pQuery.equalsIgnoreCase("QRY01") || pQuery.equalsIgnoreCase("QRY03")) {
                sXML = "<CONTACTOS>";  
            } else if (pQuery.equalsIgnoreCase("QRY02")) {
                sXML = "<LEADS>";
            } 

            while (ze != null) {
                // Recorrer el zip
                DataInputStream dis = new DataInputStream(zis);
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = dis.readLine()) != null) {
                    sb.append(line + " \n");
                }

                // Recorrer el string buffer
                ArrayList<String> rows = new ArrayList<String>(Arrays.asList(sb.toString().split("\n")));
                
                // Maximo de registros a insertar en xml
                int iLimit = rows.size();
                iLimit = iLimit < Integer.parseInt(this.txtCxg.getValue().toString()) ? iLimit : Integer.parseInt(this.txtCxg.getValue().toString());
                iLimit = iLimit < 10000 ? iLimit : 10000;                
                Util.Log("iLimit :: " + iLimit);

                for (String r : rows) {
                    items = new ArrayList<String>(Arrays.asList(r.split(",")));
                    try {
                        // Validaciones
                        String sObservaciones = "";

                        if (pQuery.equalsIgnoreCase("QRY01")) {
                            sObservaciones = (ordenL == 0 ? "" : this.validarContacto(items));
                            if (sObservaciones.isEmpty()) {
                                // Insertar los contactos en tablas temporales
                                this.insertarContacto(items, piLoteID, ordenL);
                                ordenL++;
                            }
                        }

                        if (pQuery.equalsIgnoreCase("QRY02")) {
                            sObservaciones = (ordenL == 0 ? "" : this.validarLead(items));
                            if (sObservaciones.isEmpty()) {
                                // Insertar los lead's en tablas temporales         
                                sXML += this.armarXML(items, piLoteID, ordenL);
                                
                                if (ordenLAux < 21) {                           
                                    ordenLAux++;
                                } else {
                                    sXML += "</LEADS>";
                                    
                                    Util.Log("XML :: "  + sXML);
                                    this.insertarLead(sXML);
                                    ordenLAux = 1;
                                    sXML = "<LEADS>";
                                }
                                ordenL++;
                            }
                        }

                        if (pQuery.equalsIgnoreCase("QRY03")) {
                            sObservaciones = (ordenL == 0 ? "" : this.validarContacto(items));
                            if (sObservaciones.isEmpty()) {
                                // Insertar los lead's en tablas temporales
                                this.insertarContacto(items, piLoteID, ordenL);
                                ordenL++;
                            }
                        }

                        // Si no pasaron las validaciones registrar en rechazados
                        if (!sObservaciones.isEmpty()) {
                            this.insertarRechazados(piLoteID, r, sObservaciones, pQuery);
                            ordenR++;
                        }

                    } catch (Exception ex) {
                        this.insertarRechazados(piLoteID, r, ex.getMessage(), pQuery);
                        ordenR++;
                    }
                }

                // Siguiente archivo
                ze = zis.getNextEntry();
            }

            if (ordenL - 1 > 0) {

                // =======================================================================
                // Enviar los archivos
                this.enviarGrupo(piLoteID, (ordenL - 1), pQuery);

                // =======================================================================
                // Mensaje
                //this.Mensaje("Lote N° " + piLoteID + ". Se cargó el archivo");
            } else {
                //this.Mensaje("Lote N° " + piLoteID + ". No se cargó el archivo.");
            }


        } catch (IOException e) {
            this.Mensaje("Lote N° " + piLoteID + ". IO.. " + e.getMessage());
        } catch (Exception e) {
            this.Mensaje("Lote N° " + piLoteID + ". Ex.. " + e.getMessage());
        }
    }
    
    public String armarXML(List<String> plItems, int piLoteID, int piOrden) {
        String sXML = "<LEAD>";
        try {
            sXML += "<E01>" + piLoteID + "</E01>";
            sXML += "<E02>" + 0 + "</E02>";
            sXML += "<E03>" + piOrden + "</E03>";
            //sXML += "<E04>" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "</E04>";
            sXML += "<E05>" + Util.nvl(this.txtUsu,"") + "</E05>";
            sXML += "<E06>" + plItems.get(0) + "</E06>";
            sXML += "<E07>" + plItems.get(1) + "</E07>";
            sXML += "<E08>" + plItems.get(2) + "</E08>";
            sXML += "<E09>" + plItems.get(3) + "</E09>";
            sXML += "<E10>" + plItems.get(4) + "</E10>";
            sXML += "<E11>" + plItems.get(5) + "</E11>";
            sXML += "<E12>" + plItems.get(6) + "</E12>";
            sXML += "<E13>" + plItems.get(7) + "</E13>";
            sXML += "<E14>" + plItems.get(8) + "</E14>";
            sXML += "<E15>" + plItems.get(9) + "</E15>";
            sXML += "<E16>" + plItems.get(10) + "</E16>";
            sXML += "<E17>" + plItems.get(11) + "</E17>";
            sXML += "<E18>" + plItems.get(12) + "</E18>";
            sXML += "<E19>" + plItems.get(13) + "</E19>";
            sXML += "<E20>" + plItems.get(14) + "</E20>";
            sXML += "<E21>" + plItems.get(15) + "</E21>";
            sXML += "<E22>" + plItems.get(16) + "</E22>";
            sXML += "<E23>" + plItems.get(17) + "</E23>";
            sXML += "<E24>" + plItems.get(18) + "</E24>";
            sXML += "<E25>" + plItems.get(19) + "</E25>";
            sXML += "<E26>" + plItems.get(20) + "</E26>";
            sXML += "<E27>" + plItems.get(21) + "</E27>";
            sXML += "<E28>" + plItems.get(22) + "</E28>";
            sXML += "<E29>" + plItems.get(23) + "</E29>";
            sXML += "<E30>" + plItems.get(24) + "</E30>";
            sXML += "<E31>" + plItems.get(25) + "</E31>";
            sXML += "<E32>" + plItems.get(26) + "</E32>";
            sXML += "<E33>" + plItems.get(27) + "</E33>";
            sXML += "<E34>" + plItems.get(28) + "</E34>";
            sXML += "<E35>" + plItems.get(29) + "</E35>";
            sXML += "<E36>" + plItems.get(30) + "</E36>";
            sXML += "<E37>" + plItems.get(31) + "</E37>";
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
        sXML += "</LEAD>";
        return sXML;
    }

    public void insertarLead(String psXML) {
        PreparedStatement ps = null;
        sbp.utils.Connection sbpCnx = new sbp.utils.Connection();
        Connection cnx = null;

        try {
            String query = "BEGIN SBP_SLS_CARGA_MASIVA_PKG.pr_insertarLead(?); END;";
            cnx = sbpCnx.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, psXML);
            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException ex) {
            Util.Log(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }

    public class HiloCarga extends Thread {
        
        private String sQuery;
        private int iLoteID;

        public void setSQuery(String sQuery) { this.sQuery = sQuery; } public String getSQuery() { return sQuery; }
        public void setILoteID(int iLoteID) { this.iLoteID = iLoteID; } public int getILoteID() { return iLoteID; }
        
        // Constructor
        public HiloCarga(String psQuery, int piLoteID){
            this.sQuery = psQuery;
            this.iLoteID = piLoteID;
        }
        
        @Override
        public void run(){            
            // Registrar los archivos en temporales
            SBP_SLS_CARGA_MASIVA.this.processFileUpload(this.sQuery, this.iLoteID);
            //SBP_SLS_CARGA_MASIVA.this.processFileUploadXML(this.sQuery, this.iLoteID);
        }
    }
    
    public class HiloEnvio extends Thread {
        
        private String sQuery;
        private int iLoteID;
        private int iGrupoID;
        private int iInicio;
        private int iFin;

        public void setSQuery(String sQuery) { this.sQuery = sQuery; } public String getSQuery() { return sQuery; }
        public void setILoteID(int iLoteID) { this.iLoteID = iLoteID; } public int getILoteID() { return iLoteID; }
        public void setIGrupoID(int iGrupoID) { this.iGrupoID = iGrupoID; } public int getIGrupoID() { return iGrupoID; }
        public void setIInicio(int iInicio) { this.iInicio = iInicio; } public int getIInicio() { return iInicio; }
        public void setIFin(int iFin) { this.iFin = iFin; } public int getIFin() { return iFin; }
        
        // Constructor
        public HiloEnvio(String psQuery, int piLoteID, int piGrupoID, int piInicio, int piFin){
            this.sQuery = psQuery;
            this.iLoteID = piLoteID;
            this.iGrupoID = piGrupoID;
            this.iInicio = piInicio;
            this.iFin = piFin;
        }
        
        @Override
        public void run(){
            try {
                
                SimplifiedImportActivity p = new SimplifiedImportActivity();
                String sEstado = "Enviado";
    
                if (this.sQuery.equalsIgnoreCase("QRY01")) {
                    p = SBP_SLS_CARGA_MASIVA.this.enviarGrupoContacto(this.iLoteID, this.iInicio, this.iFin, this.iGrupoID, this.sQuery);
                } else if (this.sQuery.equalsIgnoreCase("QRY02")){
                    p = SBP_SLS_CARGA_MASIVA.this.enviarGrupoLead(this.iLoteID, this.iInicio, this.iFin, this.iGrupoID, this.sQuery);
                } else if (this.sQuery.equalsIgnoreCase("QRY03")) {
                    ImportJobReturnParams p2 = SBP_SLS_CARGA_MASIVA.this.enviarGrupoContactoCsv(this.iLoteID, this.iInicio, this.iFin, this.iGrupoID, this.sQuery);
                    p.setJobID(p2.getJobId().getValue() + "");
                    p.setStatus(p2.getResult().getValue());
                    p.setObservaciones(p2.getResult().getValue());
                }
                
                Util.Log("this.iGrupoID :: " + this.iGrupoID + " - p.getJobID : " + p.getJobID() + " - p.getObservaciones : " + p.getObservaciones());
                
                // Setear observaciones
                if (p.getJobID().equalsIgnoreCase("") || p.getJobID().isEmpty()) {
                    Util.Log(p.getMensaje());
                    p.setObservaciones(Util.nvl(p.getStatus() + p.getFailureCause(), p.getMensaje()));
                    sEstado = "Observado";
                }
    
                // Actualizar estado
                SBP_SLS_CARGA_MASIVA.this.mergeCabecera(this.iLoteID, this.iGrupoID, p.getJobID(), this.sQuery, sEstado, p.getObservaciones(), 0);
                
                //Thread.sleep(20000);
                
            } catch (Exception ex) {
                Util.Log(ex.getMessage());
            }
        }
    }
    
    public class HiloBusqueda extends Thread {

        private Integer iHilAcu; // grupos(Hilos) ejecutados
        private Integer iEnvTot; // Cantidad total de grupos(hilos) a enviar
        private Integer iMaxPll; // Maximo de grupos(hilos) en paralelo

        public void setIHilAcu(Integer iHilAcu) { this.iHilAcu = iHilAcu; } public Integer getIHilAcu() { return iHilAcu; }
        public void setIEnvTot(Integer iEnvTot) { this.iEnvTot = iEnvTot; } public Integer getIEnvTot() { return iEnvTot; }
        public void setIMaxPll(Integer iMaxPll) { this.iMaxPll = iMaxPll; } public Integer getIMaxPll() { return iMaxPll; }
        
        private String sQuery;
        private int iLoteID;
        private int iGrupoRango;
        private int iInicio;
        private int iFin;

        public void setSQuery(String sQuery) { this.sQuery = sQuery; } public String getSQuery() { return sQuery; }
        public void setILoteID(int iLoteID) { this.iLoteID = iLoteID; } public int getILoteID() { return iLoteID; }
        public void setIGrupoRango(int iGrupoRango) { this.iGrupoRango = iGrupoRango; } public int getIGrupoRango() { return iGrupoRango; }
        public void setIInicio(int iInicio) { this.iInicio = iInicio; } public int getIInicio() { return iInicio; }
        public void setIFin(int iFin) { this.iFin = iFin; } public int getIFin() { return iFin; }
        
        private Integer iMaxConsultas;
    
        public void setIMaxConsultas(Integer iMaxConsultas) { this.iMaxConsultas = iMaxConsultas; } public Integer getIMaxConsultas() { return iMaxConsultas; }
        
        // Constructor
        public HiloBusqueda(){}
                                                            
        public HiloBusqueda(String psQuery, int piLoteID, int piGrupoRango, int piInicio, int piFin, int piHilAcu, int piEnvTot, int piMaxPall){
            this.sQuery = psQuery;
            this.iLoteID = piLoteID;
            this.iGrupoRango = piGrupoRango;
            this.iInicio = piInicio;
            this.iFin = piFin;
            
            this.iHilAcu = piHilAcu;
            this.iEnvTot = piEnvTot;
            this.iMaxPll = piMaxPall;
        }
        
        @Override
        public void run(){
            try {
                // Inicializar variables
                int iHilAcuAux = this.iHilAcu;
                this.setIMaxConsultas(0);
                        
                // Rango a trabajar
                int iIniTmp = this.iInicio + 1;
                int iFinAux = this.iInicio + this.iGrupoRango;
                int iFinTmp = iFinAux;

                //while (iHilAcuAux < this.iEnvTot) {
                while (!this.stop(iHilAcuAux)){   
                    int iHilPll = SBP_SLS_CARGA_MASIVA.this.getHilosParalelo(this.iLoteID, this.iMaxPll);
                    
                    Util.Log("LOTE ID :: " + this.iLoteID + " - Hilo :: " + this.iHilAcu + " - Hilos en paralello :: " + iHilPll);
                    
                    if (iHilPll < this.iMaxPll) {
                        iHilAcuAux ++;
                        this.iMaxConsultas ++;

                        //Util.Log("Rangos de envio :: " + iIniTmp + " - " + iFinTmp);
                        
                        HiloEnvio h = new HiloEnvio(this.sQuery, this.iLoteID, iHilAcuAux, iIniTmp, iFinTmp);
                        h.start();
                        
                        iIniTmp += this.iGrupoRango;
                        iFinAux += this.iGrupoRango;
                        iFinTmp = (iFinAux < this.iFin ? iFinAux : this.iFin);
                        
                    }
        
                }
               

                Thread.sleep(300000); // 30 segundos
                Util.Log("Termino los hilos");
                
            } catch (Exception ex) {
                Util.Log(ex.getMessage());
            }
        }
        
        private boolean stop(int piHilAcuAux){
            Boolean b = false;
            try {
                if (this.iMaxConsultas > 2884){
                    b = true;
                    Util.Log("Stop by iMaxConsultas");
                }
                
                if (this.iEnvTot <= (piHilAcuAux)) {
                    b = true;
                    Util.Log("Stop by piHilAcuAux");
                }
                 
            } catch (Exception ex) {
                Util.Log(ex.getMessage());
            }
            return b;
        }

    }
}


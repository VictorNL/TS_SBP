package view.backing;

import Client.MergeLeadInvoke;

import WsEjRep.ServiceBiPublisher;

import client.Base64;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import dao.Canal;

import dao.Dosificador;
import dao.DosificadorLista;
import dao.EjVenta;
import dao.LeadRandom;

import java.io.ByteArrayOutputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import java.util.zip.ZipEntry;
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
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jdbc.internal.OracleTypes;

import org.apache.commons.dbutils.DbUtils;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_CONF_CPG_SP {
    private RichForm f1;
    private RichDocument d1;
    private RichPopup pconfirmacion;
    private RichOutputLabel olPopupMensaje;
    private RichInputText itUsuCfg;
    private RichInputText itIDCamp;
    private RichInputText itLeaDia;
    private RichInputText itPorRep;
    private RichInputText itCanLea;
    private RichTable tdatos;
    private RichSelectBooleanCheckbox sbPauCpg;

    private String SBP_CONF_CPG_SP = "SBP_CONF_CPG_SP";
    private RichInputNumberSpinbox inEdaDes;
    private RichInputNumberSpinbox inEdaHas;
    private RichInputText itIdCana;
    private RichInputText itIDSupe;
    private RichInputText itNomCpg;
    private RichSelectOneChoice socEstCiv;
    private RichSelectOneChoice socGenero;
    private RichSelectBooleanCheckbox sbcCtaSue;

    public void setF1(RichForm f1) { this.f1 = f1; } public RichForm getF1() { return f1; }
    public void setD1(RichDocument d1) { this.d1 = d1; } public RichDocument getD1() { return d1; }
    public void setPconfirmacion(RichPopup pconfirmacion) { this.pconfirmacion = pconfirmacion; } public RichPopup getPconfirmacion() { return pconfirmacion; }
    public void setOlPopupMensaje(RichOutputLabel olPopupMensaje) { this.olPopupMensaje = olPopupMensaje; } public RichOutputLabel getOlPopupMensaje() { return olPopupMensaje; }
	
    public void setItUsuCfg(RichInputText itUsuCfg) { this.itUsuCfg = itUsuCfg; } public RichInputText getItUsuCfg() { return itUsuCfg; }
    public void setItIDCamp(RichInputText itIDCamp) { this.itIDCamp = itIDCamp; } public RichInputText getItIDCamp() { return itIDCamp; }
    public void setItNomCpg(RichInputText itNomCpg) { this.itNomCpg = itNomCpg; } public RichInputText getItNomCpg() { return itNomCpg; }
    public void setItIdCana(RichInputText itIdCana) { this.itIdCana = itIdCana; } public RichInputText getItIdCana() { return itIdCana; }
    public void setItIDSupe(RichInputText itIDSupe) { this.itIDSupe = itIDSupe; } public RichInputText getItIDSupe() { return itIDSupe; }
    public void setItLeaDia(RichInputText itLeaDia) { this.itLeaDia = itLeaDia; } public RichInputText getItLeaDia() { return itLeaDia; }
    public void setItPorRep(RichInputText itPorRep) { this.itPorRep = itPorRep; } public RichInputText getItPorRep() { return itPorRep; }
    public void setItCanLea(RichInputText itCanLea) { this.itCanLea = itCanLea; } public RichInputText getItCanLea() { return itCanLea; }
    public void setSbPauCpg(RichSelectBooleanCheckbox sbPauCpg) { this.sbPauCpg = sbPauCpg; } public RichSelectBooleanCheckbox getSbPauCpg() { return sbPauCpg; }
	
    public void setInEdaDes(RichInputNumberSpinbox inEdaDes) { this.inEdaDes = inEdaDes; } public RichInputNumberSpinbox getInEdaDes() { return inEdaDes; }
    public void setInEdaHas(RichInputNumberSpinbox inEdaHas) { this.inEdaHas = inEdaHas; } public RichInputNumberSpinbox getInEdaHas() { return inEdaHas; }
	
    public void setTdatos(RichTable tdatos) { this.tdatos = tdatos; } public RichTable getTdatos() { return tdatos; }

    /* *************************************************************************
     * Eventos Iniciales
     ************************************************************************* */

    public void onBeforePhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !this.isPostback()) {
            this.onLoad();
        }
    }
    private boolean isPostback() {
        return Boolean.TRUE.equals(this.resolveExpression("#{adfFacesContext.postback}"));
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
        
        createInsertProd();
        
        createInsertProd2();
        
        createInsertProd3();
        
        ADFContext ctx = ADFContext.getCurrent();
            
        try {
             this.itUsuCfg.setValue((String)this.resolveExpression("#{viewScope.userId2}"));
             this.itIDCamp.setValue((String)this.resolveExpression("#{viewScope.cpgId2}"));                
        } catch (Exception ex) {
            Util.Log("Exception 1 :: " + ex.getMessage());
        }
                
        try {
            Canal cnl = new ServiceBiPublisher().obtenerCanal(Util.nvl(this.itUsuCfg, "0")).get(0);
            
            // Almacenar en session datos
            //ctx.getSessionScope().put("CampaignId", this.itIDCamp.getValue().toString());
            //ctx.getSessionScope().put("CaNaLiD", cnl.getCanalId());
            //ctx.getSessionScope().put("uSeRiD", cnl.getIdSup());
            ctx.getSessionScope().put("userLog", this.itUsuCfg.getValue());
            
            this.itNomCpg.setValue(new ServiceBiPublisher().obtenerNomCpg(Util.nvl(this.itIDCamp, "0")).get(0).getCpgNom());
            this.itIdCana.setValue(cnl.getCanalId());
            this.itIDSupe.setValue(cnl.getIdSup());
            this.getDatosConfiguracion();
            
        } catch (Exception ex) {
            Util.Log("Exception 2 :: " + ex.getMessage());
        }
    }


    /* *************************************************************************
     * Eventos de Botones
     ************************************************************************* */

    public void cbGuardar_click(ActionEvent actionEvent) {
        try {
            this.setDatosConfiguracion();
            this.getDatosConfiguracion();
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }
    public void cbConsultar_click(ActionEvent actionEvent) {
        this.setPorcentajeNuevosAsignar();
    }
    public void cbDosificar_click(ActionEvent actionEvent) {
        try {
            
            /* *****************************************************************
             * Validaciones
             **************************************************************** */
            // Lead disponibles
            if (Util.nvl(this.itLeaDia, "0").equalsIgnoreCase("0")){
                Util.MostrarMensajeKO("Consulte la cantidad de lead's a asignar");
                return;
            }
            
            // Configuracion de dosificar
            Double dSum = 0.0;
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsLeadDosifVView1Iterator");
            ViewObject vo = ib.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);

            if (rsi.getRowCount() > 0) {
                while (rsi.hasNext()) {
                    dSum += Double.parseDouble(rsi.next().getAttribute("PorcAsignado").toString());
                }
            }

            if (dSum > 100.0) {
                Util.MostrarMensajeKO("Las cantidades configuradas superan el 100%");
                return;
            }

            /* *****************************************************************
             * Confirmacion
             **************************************************************** */
            ADFContext.getCurrent().getSessionScope().put(this.SBP_CONF_CPG_SP, "cbDosificar_click");
            this.olPopupMensaje.setValue("Seguro que desea asignar");
            this.getPconfirmacion().show(new RichPopup.PopupHints());
            
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }
 
    /* *************************************************************************
     * Eventos de Botones de Confirmacion
     ************************************************************************* */
    
    public void cbAceptar_click(ActionEvent actionEvent) {
        this.getPconfirmacion().hide();
        try {
            // Session de objetos
            String strEvento = ADFContext.getCurrent().getSessionScope().get(this.SBP_CONF_CPG_SP).toString();
            String strMensaje = "";
            
            if (strEvento.equalsIgnoreCase("cbDosificar_click")) {
                this.dosificar();
                strMensaje = "Datos dosificados";
            }

            // Mostrar estado de carga
            Util.MostrarMensajeOK(strMensaje);
        } catch (Exception ex) {
            Util.MostrarMensajeKO("Exception :: " + ex.getMessage());
        }
    }
    public void cbCancelar_click(ActionEvent actionEvent) {
        this.getPconfirmacion().hide();
    }


    /* *************************************************************************
     * Metodos
     ************************************************************************* */
    
    private void getDatosConfiguracion() {
        
        ServiceBiPublisher bi = new ServiceBiPublisher();

        Connection cnx = null;
        CallableStatement cs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        // Limpiar campos
        this.itLeaDia.setValue("");
        this.itPorRep.setValue("");
        this.sbPauCpg.setValue(Boolean.parseBoolean("False"));

        try {

            String sQuery = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_getConfiguracion(?,?,?,?,?); END;";
        
            cnx = new sbp.utils.Connection().getConnection();
            cs = cnx.prepareCall(sQuery);
            cs.setString(1, Util.nvl(this.itIDCamp, ""));
            cs.setString(2, Util.nvl(this.itIdCana, ""));
            cs.setString(3, Util.nvl(this.itIDSupe, ""));
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.execute();

            rs1 = (ResultSet)cs.getObject(4);
            rs2 = (ResultSet)cs.getObject(5);

            if (rs1 != null) {
                while (rs1.next()) { // Resulset de un registro
                    this.itLeaDia.setValue(rs1.getString("CANTIDAD"));
                    this.itPorRep.setValue(rs1.getString("PORC_REP"));
                    this.sbPauCpg.setValue(Boolean.parseBoolean(rs1.getString("PAUSA_SUPERVISOR")));
                }
            }
            
            List<String[]> lEjecDosif = new ArrayList<String[]>();
            if (rs2 != null) {
                while (rs2.next()){
                    String[] sEjeDos = new String[8];
                    sEjeDos[0] = rs2.getString("ID_EJECUTIVO");
                    sEjeDos[1] = rs2.getString("CANT_LEAD_BOLSA");
                    sEjeDos[2] = rs2.getString("PORC_ASIGNADO");
                    sEjeDos[3] = rs2.getString("CANTIDAD");
                    sEjeDos[4] = rs2.getString("PORC_REP");
                    sEjeDos[5] = rs2.getString("CALC_REPETIDOS");
                    sEjeDos[6] = rs2.getString("CALC_NUEVOS");
                    sEjeDos[7] = rs2.getString("TOTAL");
                    
                    Util.Log("sEjeDos[0] --> " + sEjeDos[0]);
                    Util.Log("sEjeDos[1] --> " + sEjeDos[1]);
                    Util.Log("sEjeDos[2] --> " + sEjeDos[2]);
                    Util.Log("sEjeDos[3] --> " + sEjeDos[3]);
                    Util.Log("sEjeDos[4] --> " + sEjeDos[4]);
                    Util.Log("sEjeDos[5] --> " + sEjeDos[5]);
                    Util.Log("sEjeDos[6] --> " + sEjeDos[6]);
                    Util.Log("sEjeDos[7] --> " + sEjeDos[7]);
                    
                    lEjecDosif.add(sEjeDos);
                }
            }
            
            Util.Log("lEjecDosif.size :: " + lEjecDosif.size());

            
            Util.Log("ID SUP --> " + Util.nvl(this.itIDSupe, "0"));
            

            // Obtener los ejecutivos del supervisor
            List<EjVenta> lEjecutivos = bi.obtenerEjVenta(Util.nvl(this.itIDSupe, "0"));


            Util.Log("lista lEjecutivos --> " + lEjecutivos.size());
            DosificadorLista dl = new DosificadorLista();
            

            
            if (lEjecutivos.size() > 0) {
                for (EjVenta e : lEjecutivos){
                    Dosificador d = new Dosificador();
                    d.setCampania(Util.nvl(this.itIDCamp, "0"));
                    d.setNomCampania(Util.nvl(this.itNomCpg, "0"));
                    d.setCanal(e.getIdCanal());
                    d.setNomCanal(e.getCanal());
                    d.setIdJefCan(e.getIdJefCan());
                    d.setNomJefCan(e.getJefCan());
                    d.setSupervisor(e.getIdSup());
                    d.setNomSup(e.getSup());
                    d.setEjecutivo(e.getIdEjVenta());
                    d.setNomEjec(e.getEjVenta());
   
                    // Datos x defecto
                    d.setCantLeadBolsa("0");
                    d.setPorcAsignado("0");
                    d.setCantidad(Integer.parseInt(itLeaDia.getValue().toString()));
                    d.setPorcentaje(Double.parseDouble(itPorRep.getValue().toString()));
                    
                  //  d.setCantidad(Util.nvl(this.itLeaDia, "0"));
                  //  d.setPorcentaje(Util.nvl(this.itPorRep, "0"));
                    d.setCalcRepetidos("0");
                    d.setCalcNuevos(Util.nvl(this.itLeaDia, "0"));
                    d.setTotal(Util.nvl(this.itLeaDia, "0"));
                    
                    Util.Log("itleadia --> " + Util.nvl(this.itLeaDia, "0"));
                    Util.Log("itPorRep --> " + Util.nvl(this.itPorRep, "0"));
                    Util.Log("cant --> 1 " + d.getCantidad());
            
                    // Datos
                    for (String[] s : lEjecDosif) {
                        if (e.getIdEjVenta().equalsIgnoreCase(s[0])){
                            Util.Log("Ejecutivos :: " + e.getIdEjVenta() + "<-->" + s[0]); 
                            
                            Util.Log("s[3] --> " + s[3]);
                            d.setCantLeadBolsa(s[1]);
                            d.setPorcAsignado(s[2]);
                            d.setCantidad(s[3]);
                            d.setPorcentaje(s[4]);
                            d.setCalcRepetidos(s[5]);
                            d.setCalcNuevos(s[6]);
                            d.setTotal(s[7]);
                        }
                    }
                    
                    dl.Datos.add(d);
                    Util.Log("TAMAÑO DL.DATOS --> " + dl.Datos.size());
                }
            }
            Util.Log("5");
            // 
            if (dl.Datos.size() > 0) {
                
                Util.Log("6");
                // Limpiar grilla
                CollectionModel cm = (CollectionModel)this.tdatos.getValue();
                JUCtrlHierBinding hb = (JUCtrlHierBinding)cm.getWrappedData(); //devuelve el table binding
                DCIteratorBinding ib = hb.getDCIteratorBinding();
                Util.Log("7");
                // Eliminar data existente
                
                int t = ib.getRowSetIterator().getRowCount();
                
                Util.Log("8");
                
                for (int i = 0; i < t; i++) {
                    ib.getRowSetIterator().getRowAtRangeIndex(0).remove();
                }
                
                Util.Log("9");
                // Llenar grilla
                Row r = null;
                if (lEjecutivos.size() > 0) {
                    for (Dosificador d : dl.Datos) {
                        r = ib.getNavigatableRowIterator().createRow();
                        ib.getNavigatableRowIterator().insertRow(r);
                        r.setNewRowState(Row.STATUS_INITIALIZED);
                        
                        Util.Log("d.getCantidad() --> " + d.getCantidad());
                        Util.Log("d.getPorcentaje() --> " + d.getPorcentaje());
                        
                        r.setAttribute("IdCampania", d.getCampania());
                        r.setAttribute("NombreCampania", d.getNomCampania());
                        r.setAttribute("IdCanal", d.getCanal());
                        r.setAttribute("NombreCanal", d.getNomCanal());
                        r.setAttribute("IdSupervisor", d.getSupervisor());
                        r.setAttribute("NombreSupervisor", d.getNomSup());
                        r.setAttribute("IdEjecutivo", d.getEjecutivo());
                        r.setAttribute("NombreEjecutivo", d.getNomEjec());
                        r.setAttribute("CantLeadBolsa", d.getCantLeadBolsa());
                        r.setAttribute("PorcAsignado", d.getPorcAsignado());
                        r.setAttribute("Cantidad", d.getCantidad());
                        r.setAttribute("PorcRep", d.getPorcentaje());
                        r.setAttribute("CalcRepetidos", d.getCalcRepetidos());
                        r.setAttribute("CalcNuevos", d.getCalcNuevos());
                        r.setAttribute("Total", d.getTotal());
                        r.setAttribute("NombreJefeCanal", d.getNomJefCan());
                    }
                }            
            }
            Util.Log("10");  
            rs1.close();
            rs2.close();

            cs.close();
            cnx.close();

        } catch (SQLException ex) {
            Util.Log("SQLException :: " + ex.getMessage());
            Util.MostrarMensajeKO("SQLException :: " + ex.getMessage());
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
            Util.MostrarMensajeKO("Exception :: " + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(rs1);
            DbUtils.closeQuietly(rs2);
            DbUtils.closeQuietly(cs);
            DbUtils.closeQuietly(cnx);
        }
    }
    private void setDatosConfiguracion() {
        // Validaciones
        Util.Log("itLeaDia --> " + Util.nvl(this.itLeaDia, "0"));
        Util.Log("itPorRep --> " + Util.nvl(this.itPorRep, "0"));
        if (Util.nvl(this.itLeaDia, "0").equals("0") ||
            Util.nvl(this.itPorRep, "0").equals("0")) {
            Util.MostrarMensajeKO("Ingrese datos");        
            return;
        }
        
        PreparedStatement ps = null;
        Connection cnx = null;
        try {
            
            String sQuery = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_setConfiguracion(?,?,?,?,?,?,?); END;";
            cnx = new sbp.utils.Connection().getConnection();
            ps = cnx.prepareStatement(sQuery);

            ps.setString(1, Util.nvl(this.itIDCamp, ""));
            ps.setString(2, Util.nvl(this.itIdCana, ""));
            ps.setString(3, Util.nvl(this.itIDSupe, ""));
            ps.setInt(4, Integer.parseInt(Util.nvl(this.itLeaDia, "0")));
            ps.setInt(5, Integer.parseInt(Util.nvl(this.itPorRep, "0")));
            ps.setString(6, Util.nvl(this.sbPauCpg, "False"));
            ps.setString(7, Util.nvl(this.itNomCpg, ""));
            ps.execute();
            ps.close();
            cnx.close();
            
        } catch (SQLException ex) {
            Util.Log("SQLException :: " + ex.getMessage());
            Util.MostrarMensajeKO("setDatosConfiguracion :: SQLException :: "  + ex.getMessage());
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
            Util.MostrarMensajeKO("setDatosConfiguracion :: Exception ::" + ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    private void setPorcentajeNuevosAsignar() {
        try {

            int iCal = Integer.parseInt(Util.nvl(this.inEdaDes, "0")) * 
                       Integer.parseInt(Util.nvl(this.inEdaHas, "0"));
            this.itCanLea.setValue(iCal);
            ADFContext.getCurrent().getSessionScope().put("itCanLea", Util.nvl(this.itCanLea, "0")); // para ser usado en SbpSlsLeadDosifVImpl

            Double iSum = 0.0;
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsLeadDosifVView1Iterator");
            ViewObject vo = ib.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);

            if (rsi.getRowCount() > 0) {
                Row r;
                while (rsi.hasNext()) {
                    r = rsi.next();

                    int iCan = Integer.parseInt(r.getAttribute("Cantidad").toString());
                    r.setAttribute("PorcAsignado", (iCan * 100 / iCal) + "");
                    iSum += (iCan * 100.0 / iCal);
                }
            }

            if (iSum > 100.0) {
                Util.MostrarMensajeKO("Las cantidades configuradas superan el 100%");
            }

        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }
    public void setTotales() {
        try {
            Double dSumTot = 0.0;
            
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsLeadDosifVView1Iterator");
            ViewObject vo = ib.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);
            if (rsi.getRowCount() > 0) {
                Row r;
                while (rsi.hasNext()) {
                    r = rsi.next();                    
                    dSumTot += Double.parseDouble(r.getAttribute("").toString());
                }
            }
                        
        } catch (Exception ex) {
            Util.Log("Exception :: " + ex.getMessage());
        }
    }
    public void dosificar() {

        String sLoteEjecucion = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        
        DosificadorLista dlCfg = new DosificadorLista();
        DosificadorLista dlAsg = new DosificadorLista();
        Integer iCanLea = 0;
        
        List<LeadRandom> listRdmLead = new ArrayList<LeadRandom>();
        List<LeadRandom> listRdmLeadSinc = new ArrayList<LeadRandom>();

        // Obtener datos de la grilla
        try {


            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsLeadDosifVView1Iterator");
            ViewObject vo = ib.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);
            
            if (rsi.getRowCount() > 0) {
                Row r;
                Dosificador d;
                while (rsi.hasNext()) {
                    r = rsi.next();
                    d = new Dosificador();
                    d.setCampania(r.getAttribute("IdCampania").toString());
                    d.setCanal(r.getAttribute("IdCanal").toString());
                    d.setSupervisor(r.getAttribute("IdSupervisor").toString());
                    d.setEjecutivo(r.getAttribute("IdEjecutivo").toString());
                    d.setCantidad(r.getAttribute("CalcNuevos").toString());
                    
                    Util.Log("ejecutivo --> " + d.getEjecutivo());
                    Util.Log("cantidad --> " + d.getCantidad());
                    
                    d.setNomCampania(r.getAttribute("NombreCampania").toString());
                    d.setNomCanal(r.getAttribute("NombreCanal").toString());
                    d.setNomJefCan(r.getAttribute("NombreJefeCanal").toString());
                    d.setNomSup(r.getAttribute("NombreSupervisor").toString());
                    d.setNomEjec(r.getAttribute("NombreEjecutivo").toString());
                    
                    Util.Log("calcnuevo " + r.getAttribute("CalcNuevos").toString());
                    
                    dlCfg.Datos.add(d);
                    iCanLea += Integer.parseInt(r.getAttribute("CalcNuevos").toString());
                }
            }
            
            Util.Log("DATOS SIZE --> " + dlCfg.Datos.size());

            // Obtener las nuevas leads por campaña y canal
            if (dlCfg.Datos.size() > 0) {
                listRdmLead = new ServiceBiPublisher().obtenerLeadRandom(Util.nvl(this.itIDCamp, "0"), 
                                                                         Util.nvl(this.itIdCana, "0"), 
                                                                         Util.nvl(this.itIDSupe, "0"), 
                                                                         iCanLea.toString());
                
                Util.Log("listRdmLead size --> " + listRdmLead.size());
                Util.Log("iCanLea.toString() --> " + iCanLea.toString());
                
                    
                Util.Log("3");                  
                // Recorrer ArrayList armado
                for (Dosificador dos : dlCfg.Datos) {
 
                    Util.Log("ejecutivo --> " + dos.getEjecutivo());
                    try {
                        Util.Log("dos.getCantidad() --> " + dos.getCantidad());
                        int cant =  dos.getCantidad();
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                    
 
                    // Insertar dosificacion
                    for (int i = 0; i < Integer.parseInt(dos.getCantidad().toString()); i++) {
                        Util.Log("3.1");      
                        String iLeadId = listRdmLead.get(0).getLeadId();
                        String sLeadNumber = listRdmLead.get(0).getLeadNumber();               
                        Util.Log("3.2");     
                        // Insertar en tablas dosificacion Apex
                        DosificacionEjecutivoBO.insertDataDosificador(dos.getCampania(), 
                                                                      dos.getCanal(), 
                                                                      dos.getSupervisor(), 
                                                                      dos.getEjecutivo(), 
                                                                      iLeadId, 
                                                                      sLeadNumber, 
                                                                      sLeadNumber, 
                                                                      dos.getNomEjec(), 
                                                                      dos.getNomSup(), 
                                                                      dos.getNomJefCan(), 
                                                                      dos.getNomCanal(), 
                                                                      dos.getNomCampania(),
                                                                      "");
                        Util.Log("4");               
                        // Lead sincronizacion Flag = 'Y'
                        listRdmLeadSinc.add(new LeadRandom(iLeadId, sLeadNumber));
                        dlAsg.Datos.add(new Dosificador(sLeadNumber, 
                                                        dos.getEjecutivo(),
                                                        dos.getNomEjec(),
                                                        dos.getNomSup(),
                                                        dos.getNomJefCan(),
                                                        dos.getNomCanal(),
                                                        dos.getNomCampania()));
                        
                        listRdmLead.remove(0);                        
                        
                    }
                    Util.Log("5");       
                    // Insertar log
                    DosificacionEjecutivoBO.registrarLog(sLoteEjecucion, "Campaña " + dos.getCampania().toString() + " " +
                                                                         "Canal " + dos.getCanal().toString() + " " + 
                                                                         "Supervisor " + dos.getSupervisor().toString() + " " + 
                                                                         "Ejecutivos " + dos.getEjecutivo().toString() + " " + 
                                                                         ":: " + dos.getCantidad().toString() + " " + 
                                                                         "Leads registradas");

                }
                // Limpiar los ejecutivos
                dlCfg = new DosificadorLista();
            }
            Util.Log("6");
            // Dosificacion de Lead's en Apex
            DosificacionEjecutivoBO.ordenar(Util.nvl(this.itIDCamp, "0"), Util.nvl(this.itIDSupe, "0"));

            // Armar el txt de sincronizacion (actualiza el check Nuevo_c)
            DosificacionEjecutivoBO.actualizarChecknuevo(listRdmLeadSinc);
            
            /* *****************************************************************
             * Enviar Asignacion a OSC
             **************************************************************** */ 
            String sTrama1 = "LeadNumber" + "\n";
            String sTrama2 = "LeadNumber,ResourceId" + "\n";
            String sTrama3 = "LEAD_NUMBER,RecordName,EjecutivoVenta_c,Supervisor_c,JefeCanal_c,Canal_c,NombreCampana_c" + "\n";
            
            for (Dosificador d2 : dlAsg.Datos) {
                sTrama1 += d2.getLeadNumber() + "\n";
                sTrama2 += d2.getLeadNumber() + "," + 
                           d2.getEjecutivo() + "\n";
                sTrama3 += d2.getLeadNumber() + "," + 
                           d2.getLeadNumber() + "," + 
                           d2.getEjecutivo() + "," + 
                           d2.getNomSup() + "," + 
                           d2.getNomJefCan() + "," + 
                           d2.getNomCanal() + "," + 
                           d2.getNomCampania() + "\n";
            }
            
            Util.Log("sTrama1 :: " + sTrama1);
            Util.Log("sTrama2 :: " + sTrama2);
            Util.Log("sTrama3 :: " + sTrama3);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);
            ZipEntry zeL = new ZipEntry("Lead.csv");
            zos.putNextEntry(zeL);
            byte[] b1 = sTrama1.getBytes();
            zos.write(b1, 0, b1.length);
            zos.closeEntry();

            ZipEntry zeR = new ZipEntry("Lead_Resource.csv");
            zos.putNextEntry(zeR);
            byte[] bR = sTrama2.getBytes();
            zos.write(bR, 0, bR.length);
            zos.closeEntry();

            ZipEntry zeF = new ZipEntry("Feedback_Lead_c.csv");
            zos.putNextEntry(zeF);
            byte[] bF = sTrama3.getBytes();
            zos.write(bF, 0, bF.length);
            zos.closeEntry();

            zos.close();
            
            new MergeLeadInvoke().submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", Base64.byteArrayToBase64(bos.toByteArray()));
            
        } catch (Exception ex) {
            Util.MostrarMensajeKO("Exception :: " + ex.getMessage());
        }
    }


    public void setSocEstCiv(RichSelectOneChoice socEstCiv) {
        this.socEstCiv = socEstCiv;
    }

    public RichSelectOneChoice getSocEstCiv() {
        return socEstCiv;
    }

    public void setSocGenero(RichSelectOneChoice socGenero) {
        this.socGenero = socGenero;
    }

    public RichSelectOneChoice getSocGenero() {
        return socGenero;
    }

    public void setSbcCtaSue(RichSelectBooleanCheckbox sbcCtaSue) {
        this.sbcCtaSue = sbcCtaSue;
    }

    public RichSelectBooleanCheckbox getSbcCtaSue() {
        return sbcCtaSue;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String createInsertProd() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsertProd2() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsertProd3() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert2");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
}

package view.backing;

import java.text.SimpleDateFormat;

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
import oracle.adf.view.faces.bi.component.pivotTable.HeaderCellRange;
import oracle.adf.view.faces.bi.component.pivotTable.HeaderCellSelectionSet;
import oracle.adf.view.faces.bi.component.pivotTable.Selection;
import oracle.adf.view.faces.bi.component.pivotTable.UIPivotTable;
import oracle.adf.view.faces.bi.model.HeaderCellIndex;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.dss.util.DataAccess;

import oracle.dss.util.QDR;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.BE.AsigReasigReporteBE;
import view.BE.AsigReasigReporteLista;

import view.BO.AsigReasigReporteBO;

import view.Util;

public class SBP_SLS_AS_REASIG {
    private RichForm f1;
    private RichDocument d1;
    private RichTable tblReasig;
    private RichInputText txtUsuario;
    private RichInputText txtFecFin;
    private RichInputText txtxFecIni;
    private RichInputDate txtFechaFin;
    private RichInputDate txtFechaIni;
    private RichTable tblReasigJEFCAN;
    private UIPivotTable pivotTable1;
    private UIPivotTable pivotTable2;
    private RichTable tlbReasigSUPERV;

    public void setF1(RichForm f1) { this.f1 = f1; }
    public RichForm getF1() { return f1; }
    public void setD1(RichDocument d1) { this.d1 = d1; }
    public RichDocument getD1() { return d1; }
    public void setTblReasig(RichTable tblReasig) { this.tblReasig = tblReasig; }
    public RichTable getTblReasig() { return tblReasig; }
    public void setTxtUsuario(RichInputText txtUsuario) { this.txtUsuario = txtUsuario; }
    public RichInputText getTxtUsuario() { return txtUsuario; }
    public void setTxtFecFin(RichInputText txtFecFin) { this.txtFecFin = txtFecFin; }
    public RichInputText getTxtFecFin() { return txtFecFin; }
    public void setTxtxFecIni(RichInputText txtxFecIni) { this.txtxFecIni = txtxFecIni; }
    public RichInputText getTxtxFecIni() { return txtxFecIni; }
    public void setTxtFechaFin(RichInputDate txtFechaFin) { this.txtFechaFin = txtFechaFin; }
    public RichInputDate getTxtFechaFin() { return txtFechaFin; }
    public void setTxtFechaIni(RichInputDate txtFechaIni) { this.txtFechaIni = txtFechaIni; }
    public RichInputDate getTxtFechaIni() { return txtFechaIni; }
    public void setTblReasigJEFCAN(RichTable tblReasigJEFCAN) { this.tblReasigJEFCAN = tblReasigJEFCAN; }
    public RichTable getTblReasigJEFCAN() { return tblReasigJEFCAN; }
    public void setPivotTable1(UIPivotTable pivotTable1) { this.pivotTable1 = pivotTable1; }
    public UIPivotTable getPivotTable1() { return pivotTable1; }
    public void setPivotTable2(UIPivotTable pivotTable2) { this.pivotTable2 = pivotTable2; }
    public UIPivotTable getPivotTable2() { return pivotTable2; }
    public void setTlbReasigSUPERV(RichTable tlbReasigSUPERV) { this.tlbReasigSUPERV = tlbReasigSUPERV; }
    public RichTable getTlbReasigSUPERV() { return tlbReasigSUPERV; }
    
    public void onBeforePhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
            onLoad();
        }
    }

    private boolean isPostback() {
        return Boolean.TRUE.equals(resolveExpression("#{adfFacesContext.postback}"));
    }

    private Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            Application app = facesContext.getApplication();
            ExpressionFactory elFactory = app.getExpressionFactory();
            ELContext elContext = facesContext.getELContext();
            ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
            return valueExp.getValue(elContext);
        } catch (Exception e) {
            Util.Log(e.getMessage());
            return null;
        }
    }

    public void onLoad() {
        try {
            ADFContext ctx = ADFContext.getCurrent();                  
            this.txtUsuario.setValue((String)resolveExpression("#{viewScope.usuario2}"));
            ctx.getSessionScope().put("usuarioLog", this.txtUsuario.getValue().toString());
            Util.Log("usuarioLog :: " + ctx.getSessionScope().get("usuarioLog"));
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
    }

    public void ejecutarSBP(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        try {            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
            Row rw = null;
            
            AsigReasigReporteBE param = new AsigReasigReporteBE();
            param.setUsuarioEjecucion(ctx.getSessionScope().get("usuarioLog") == null ? "" : ctx.getSessionScope().get("usuarioLog").toString());
            param.setFechaInicio(formatoFecha.format(this.txtFechaIni.getValue()));
            param.setFechaFin(formatoFecha.format(this.txtFechaFin.getValue()));
            param.setQuerySQL("QRY01");            
            
            AsigReasigReporteLista listReasig = AsigReasigReporteBO.obtenerReporte(param);
    
            Util.Log("Cantidad de registros obtenidos :: " + listReasig.Lista.size());
    
            CollectionModel cm= (CollectionModel)this.tblReasig.getValue();
            JUCtrlHierBinding hb = (JUCtrlHierBinding)cm.getWrappedData(); //devuelve el table binding
            DCIteratorBinding ib =  hb.getDCIteratorBinding();
            
            // Limpiar tabla
            int iRowCount = ib.getRowSetIterator().getRowCount();
            for (int i = 0; i < iRowCount; i++) {
                ib.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            
            // Poblar tabla
            for (AsigReasigReporteBE ent : listReasig.Lista) {
    
                rw = ib.getNavigatableRowIterator().createRow();
                ib.getNavigatableRowIterator().insertRow(rw);
                rw.setNewRowState(Row.STATUS_INITIALIZED);
    
                rw.setAttribute("UsuarioEjecucion", ent.getUsuarioEjecucion());
                rw.setAttribute("UsuarioEjecucionPerfil", ent.getUsuarioEjecucionPerfil());
                rw.setAttribute("CampaniaId", ent.getCampaniaID());
                rw.setAttribute("CampaniaNombre", ent.getCampaniaNombre());
                rw.setAttribute("CanalId", ent.getCanalID());
                rw.setAttribute("CanalNombre", ent.getCanalNombre());
                rw.setAttribute("JefeCanalId", ent.getJefeCanalID());
                rw.setAttribute("JefeCanalNombre", ent.getJefeCanalNombre());
                rw.setAttribute("SupervisorId", ent.getSupervisorID());
                rw.setAttribute("SupervisorNombre", ent.getSupervisorNombre());
                rw.setAttribute("EjecutivoId", ent.getEjecutivoID());
                rw.setAttribute("EjecutivoNombre", ent.getEjecutivoNombre());
                rw.setAttribute("Fecha", ent.getFecha());
                rw.setAttribute("CantidadAsignada", ent.getCantidadAsignada());
                rw.setAttribute("CantidadReasignada", ent.getCantidadReasignada());
            }
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
    }

    public void obtenerDatosJC(ActionEvent actionEvent) {
        ADFContext ctx = ADFContext.getCurrent();
        AsigReasigReporteBE param;
        try {
            param = new AsigReasigReporteBE();
            // Obtener datos header
            Selection selH = this.pivotTable1.getSelection();
            HeaderCellSelectionSet hcss = selH.getRowHeaderCells();
            DataAccess daH = this.pivotTable1.getDataModel().getDataAccess();
            HeaderCellRange hcr = hcss.iterator().next();
            HeaderCellIndex hci = hcr.getStartIndex(this.pivotTable1.getDataModel());
            QDR qdrH = daH.getValueQDR(hci.getSlice(), hci.getLayer(), daH.QDR_WITHOUT_PAGE); 
            
            String sCpgNo = ""; try { sCpgNo = (String)qdrH.getDimMember("CampaniaNombre").getData(); } catch (Exception e) { sCpgNo = ""; };
            String sCnlNo = ""; try { sCnlNo = (String)qdrH.getDimMember("CanalNombre").getData(); } catch (Exception e) { sCnlNo = ""; };
            String sJefNo = ""; try { sJefNo = (String)qdrH.getDimMember("JefeCanalNombre").getData(); } catch (Exception e) { sJefNo = ""; };
            String sAsign = ""; try { sAsign = (String)qdrH.getDimMember("CantidadAsignada").getData(); } catch (Exception e) { sAsign = ""; };
            String sReasi = ""; try { sReasi = (String)qdrH.getDimMember("CantidadReasignada").getData(); } catch (Exception e) { sReasi = ""; };  
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();                  
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsAsigReasReporteView1Iterator");
            RowSetIterator rsi = ib.getRowSetIterator();
            rsi.reset();
            Row row;
            
            while (rsi.hasNext()) {
                row = rsi.next();
                // 
                if (((String)row.getAttribute("CampaniaNombre")).equalsIgnoreCase(sCpgNo) &&
                    ((String)row.getAttribute("CanalNombre")).equalsIgnoreCase(sCnlNo) &&
                    ((String)row.getAttribute("JefeCanalNombre")).equalsIgnoreCase(sJefNo)) {
                    param.setCampaniaID(row.getAttribute("CampaniaId").toString());
                    param.setCanalID(row.getAttribute("CanalId").toString());             
                }
            }

            // mostrar pivot de supervisores
            this.ejecutarJC(actionEvent, param);
            
        } catch (Exception e) {
            Util.Log("8");
            Util.Log("Exception :: " + e.getMessage());
        }
    }

    public void ejecutarJC(ActionEvent actionEvent, AsigReasigReporteBE param) {        
        ADFContext ctx = ADFContext.getCurrent();   
        try {      

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
            Row rw = null;
            
            param.setFechaInicio(formatoFecha.format(this.txtFechaIni.getValue()));
            param.setFechaFin(formatoFecha.format(this.txtFechaFin.getValue()));
            param.setQuerySQL("QRY02");
            
            AsigReasigReporteLista listReasig = AsigReasigReporteBO.obtenerReporte(param);
    
            Util.Log("Cantidad de registros obtenidos :: " + listReasig.Lista.size());
    
            CollectionModel cm = (CollectionModel)this.tblReasigJEFCAN.getValue();
            JUCtrlHierBinding hb = (JUCtrlHierBinding)cm.getWrappedData(); //devuelve el table binding
            DCIteratorBinding ib = hb.getDCIteratorBinding();
    
            // Limpiar tabla
            int iRowCount = ib.getRowSetIterator().getRowCount();
            for (int i = 0; i < iRowCount; i++) {
                ib.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            
            // Poblar tabla
            for (AsigReasigReporteBE ent : listReasig.Lista) {
    
                rw = ib.getNavigatableRowIterator().createRow();
                ib.getNavigatableRowIterator().insertRow(rw);
                rw.setNewRowState(Row.STATUS_INITIALIZED);
    
                rw.setAttribute("UsuarioEjecucion", ent.getUsuarioEjecucion());
                rw.setAttribute("UsuarioEjecucionPerfil", ent.getUsuarioEjecucionPerfil());
                rw.setAttribute("CampaniaId", ent.getCampaniaID());
                rw.setAttribute("CampaniaNombre", ent.getCampaniaNombre());
                rw.setAttribute("CanalId", ent.getCanalID());
                rw.setAttribute("CanalNombre", ent.getCanalNombre());
                rw.setAttribute("JefeCanalId", ent.getJefeCanalID());
                rw.setAttribute("JefeCanalNombre", ent.getJefeCanalNombre());
                rw.setAttribute("SupervisorId", ent.getSupervisorID());
                rw.setAttribute("SupervisorNombre", ent.getSupervisorNombre());
                rw.setAttribute("EjecutivoId", ent.getEjecutivoID());
                rw.setAttribute("EjecutivoNombre", ent.getEjecutivoNombre());
                rw.setAttribute("Fecha", ent.getFecha());
                rw.setAttribute("CantidadAsignada", ent.getCantidadAsignada());
                rw.setAttribute("CantidadReasignada", ent.getCantidadReasignada());
            }
            
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
    }

    public void obtenerDatosSUP(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        AsigReasigReporteBE param = null;
        try {
            
            param = new AsigReasigReporteBE();
            
            // =================================================================
            // Obtener datos
            Selection sel = this.pivotTable2.getSelection();
            HeaderCellSelectionSet dcss = sel.getRowHeaderCells();
            DataAccess da = this.pivotTable2.getDataModel().getDataAccess();
            HeaderCellRange cell = dcss.iterator().next();
            HeaderCellIndex start = cell.getStartIndex(this.pivotTable2.getDataModel());
            
            QDR qdr = da.getValueQDR(start.getSlice(), start.getLayer(), da.QDR_WITHOUT_PAGE);            
            
            String sCpgNo = ""; try { sCpgNo = (String)qdr.getDimMember("CampaniaNombre").getData(); } catch (Exception e) { sCpgNo = ""; };
            String sCnlNo = ""; try { sCnlNo = (String)qdr.getDimMember("CanalNombre").getData(); } catch (Exception e) { sCnlNo = ""; };
            String sJefNo = ""; try { sJefNo = (String)qdr.getDimMember("JefeCanalNombre").getData(); } catch (Exception e) { sJefNo = ""; };
            String sSupNo = ""; try { sSupNo = (String)qdr.getDimMember("SupervisorNombre").getData(); } catch (Exception e) { sSupNo = ""; };
            String sAsign = ""; try { sAsign = (String)qdr.getDimMember("CantidadAsignada").getData(); } catch (Exception e) { sAsign = ""; };
            String sReasi = ""; try { sReasi = (String)qdr.getDimMember("CantidadReasignada").getData(); } catch (Exception e) { sReasi = ""; };
            
            Util.Log("sCpgNo :: " + sCpgNo);            
            Util.Log("sCnlNo :: " + sCnlNo);            
            Util.Log("sJefNo :: " + sJefNo);          
            Util.Log("sSupNo :: " + sSupNo);          
            Util.Log("sAsign :: " + sAsign);            
            Util.Log("sReasi :: " + sReasi);
            
            DCBindingContainer bc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();                  
            DCIteratorBinding ib = bc.findIteratorBinding("SbpSlsAsigReasReporte2View1Iterator");
            RowSetIterator rsi = ib.getRowSetIterator();
            rsi.reset();
            Row row;
            
            while (rsi.hasNext()) {
                row = rsi.next();
                
                // 
                if (((String)row.getAttribute("CampaniaNombre")).equalsIgnoreCase(sCpgNo) &&
                    ((String)row.getAttribute("CanalNombre")).equalsIgnoreCase(sCnlNo) &&
                    ((String)row.getAttribute("JefeCanalNombre")).equalsIgnoreCase(sJefNo) &&
                    ((String)row.getAttribute("SupervisorNombre")).equalsIgnoreCase(sSupNo)) {
                    
                    param.setCampaniaID(row.getAttribute("CampaniaId").toString());
                    param.setCanalID(row.getAttribute("CanalId").toString());
                    param.setSupervisorID(row.getAttribute("SupervisorId").toString());
                }
            }
            
            // mostrar pivot de supervisores
            this.ejecutarSUP(actionEvent, param);
            
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }        
    }

    public void ejecutarSUP(ActionEvent actionEvent, AsigReasigReporteBE param) {        
        ADFContext ctx = ADFContext.getCurrent();   
        try {      
            // Imprimir parametros
            //Util.Log(param.getFechaInicio());
            //Util.Log(param.getFechaFin());
            //Util.Log(param.getCampaniaID());
            //Util.Log(param.getCanalID());
            //Util.Log(param.getSupervisorID());
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");    
            Row rw = null;
            
            param.setFechaInicio(formatoFecha.format(this.txtFechaIni.getValue()));
            param.setFechaFin(formatoFecha.format(this.txtFechaFin.getValue()));
            param.setQuerySQL("QRY03");
            
            AsigReasigReporteLista listReasig = AsigReasigReporteBO.obtenerReporte(param);
    
            Util.Log("Cantidad de registros obtenidos :: " + listReasig.Lista.size());
    
            // Setear los valores de la tabla
            CollectionModel cm= (CollectionModel)this.tlbReasigSUPERV.getValue();
            JUCtrlHierBinding hb = (JUCtrlHierBinding)cm.getWrappedData(); //devuelve el table binding
            DCIteratorBinding ib =  hb.getDCIteratorBinding();
    
            // Limpiar tabla
            int iRowCount = ib.getRowSetIterator().getRowCount();
            for (int i = 0; i < iRowCount; i++) {
                ib.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            
            // Poblar tabla
            for (AsigReasigReporteBE ent : listReasig.Lista) {
    
                rw = ib.getNavigatableRowIterator().createRow();
                ib.getNavigatableRowIterator().insertRow(rw);
                rw.setNewRowState(Row.STATUS_INITIALIZED);
    
                rw.setAttribute("UsuarioEjecucion", ent.getUsuarioEjecucion());
                rw.setAttribute("UsuarioEjecucionPerfil", ent.getUsuarioEjecucionPerfil());
                rw.setAttribute("CampaniaId", ent.getCampaniaID());
                rw.setAttribute("CampaniaNombre", ent.getCampaniaNombre());
                rw.setAttribute("CanalId", ent.getCanalID());
                rw.setAttribute("CanalNombre", ent.getCanalNombre());
                rw.setAttribute("JefeCanalId", ent.getJefeCanalID());
                rw.setAttribute("JefeCanalNombre", ent.getJefeCanalNombre());
                rw.setAttribute("SupervisorId", ent.getSupervisorID());
                rw.setAttribute("SupervisorNombre", ent.getSupervisorNombre());
                rw.setAttribute("EjecutivoId", ent.getEjecutivoID());
                rw.setAttribute("EjecutivoNombre", ent.getEjecutivoNombre());
                rw.setAttribute("Fecha", ent.getFecha());
                rw.setAttribute("CantidadAsignada", ent.getCantidadAsignada());
                rw.setAttribute("CantidadReasignada", ent.getCantidadReasignada());
            }
            
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
    }

}


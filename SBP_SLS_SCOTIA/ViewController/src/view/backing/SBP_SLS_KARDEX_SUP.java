package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.BE.AsigReasigReporteBE;
import view.BE.AsigReasigReporteLista;

import view.BO.AsigReasigReporteBO;

import view.Util;

public class SBP_SLS_KARDEX_SUP {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtUsuario;
    private RichInputText txtxCpg;
    private RichInputDate txtFechaIni;
    private RichInputDate txtFechaFin;
    private RichTable tlbReasigSUPERV;

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

    public void setTxtUsuario(RichInputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public RichInputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtxCpg(RichInputText txtxCpg) {
        this.txtxCpg = txtxCpg;
    }

    public RichInputText getTxtxCpg() {
        return txtxCpg;
    }

    public void setTxtFechaIni(RichInputDate txtFechaIni) {
        this.txtFechaIni = txtFechaIni;
    }

    public RichInputDate getTxtFechaIni() {
        return txtFechaIni;
    }

    public void setTxtFechaFin(RichInputDate txtFechaFin) {
        this.txtFechaFin = txtFechaFin;
    }

    public RichInputDate getTxtFechaFin() {
        return txtFechaFin;
    }
    
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
            this.txtUsuario.setValue((String)resolveExpression("#{viewScope.idUser2}"));
            ctx.getSessionScope().put("usuarioLog", this.txtUsuario.getValue().toString());
            Util.Log("usuarioLog :: " + ctx.getSessionScope().get("usuarioLog"));
            
            obtenerCanal(this.txtUsuario.getValue().toString());
            
            this.txtxCpg.setValue((String)resolveExpression("#{viewScope.cpgId2}"));
            ctx.getSessionScope().put("cpg", this.txtxCpg.getValue().toString());
            Util.Log("cpg :: " + ctx.getSessionScope().get("cpg"));
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
    }
    
    public void obtenerCanal(String userName) {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher canal = new ServiceBiPublisher();

        String jefeCanal = "";
        String idJefeCanal = "";
        String canalId = "";
        String supId = "";
        String nomCanal = "";
        String sup = "";
        String idEjecVenta = "";
        String ejVenta = "";


        List<Canal> lista2 = new ArrayList<Canal>();
        lista2 = canal.obtenerCanal(userName);

        for (Canal canalList : lista2) {

            jefeCanal = canalList.getJefCanal();
            idJefeCanal = canalList.getIdJefCanal();
            canalId = canalList.getCanalId();
            supId = canalList.getIdSup();
            nomCanal = canalList.getCanal();
            sup = canalList.getSup();
            idEjecVenta = canalList.getIdEjVenta();
            ejVenta = canalList.getEjVenta();

        }

        ctx.getSessionScope().put("CaNaLiD", canalId);
        ctx.getSessionScope().put("uSeRiD", supId);
        ctx.getSessionScope().put("JefECaNAl", jefeCanal);
    }

    public void ejecutarSup(ActionEvent actionEvent) {
        ADFContext ctx = ADFContext.getCurrent();
        
        String idCanal = ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();
        Util.Log(idCanal + "idCanal");

        String supId = ctx.getSessionScope().get("uSeRiD") == null ? "" : ctx.getSessionScope().get("uSeRiD").toString();
        
        try {            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
            Row rw = null;
            
            AsigReasigReporteBE param = new AsigReasigReporteBE();
            param.setUsuarioEjecucion("SUPERVISOR");
            param.setFechaInicio(formatoFecha.format(this.txtFechaIni.getValue()));
            param.setFechaFin(formatoFecha.format(this.txtFechaFin.getValue()));
            param.setCampaniaID(this.txtxCpg.getValue().toString());
            param.setCanalID(idCanal);
            param.setSupervisorID(supId);
            param.setQuerySQL("QRY03");            
            
            AsigReasigReporteLista listReasig = AsigReasigReporteBO.obtenerReporteSup(param);
        
            Util.Log("Cantidad de registros obtenidos :: " + listReasig.Lista.size());
        
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

    public void setTlbReasigSUPERV(RichTable tlbReasigSUPERV) {
        this.tlbReasigSUPERV = tlbReasigSUPERV;
    }

    public RichTable getTlbReasigSUPERV() {
        return tlbReasigSUPERV;
    }
}

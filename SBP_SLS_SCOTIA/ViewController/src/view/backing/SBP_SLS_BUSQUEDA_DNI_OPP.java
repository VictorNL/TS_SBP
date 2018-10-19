package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;
import dao.CpgLeadContAccProdFb;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
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
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.jbo.Row;

import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_BUSQUEDA_DNI_OPP {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichInputText it1;
    private RichCommandButton cb1;
    private RichInputText txtDni;
    private RichTable tblDni;
    private RichInputText txtUsuario;

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

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }


    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setTxtDni(RichInputText txtDni) {
        this.txtDni = txtDni;
    }

    public RichInputText getTxtDni() {
        return txtDni;
    }
    
    public void buscarDatos(ActionEvent actionEvent) {
        
        String monedaLead = "";
        String signoMon = "";
        
        CollectionModel _tablaDni = (CollectionModel)tblDni.getValue();
        JUCtrlHierBinding _adfTableDniBinding = (JUCtrlHierBinding)_tablaDni.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itDni = _adfTableDniBinding.getDCIteratorBinding();
        
      
       txtDni.getValue();
       
       if(txtDni.getValue() != null){
       
       String dni = txtDni.getValue().toString();
      
        Row rw = null;
        
        ServiceBiPublisher oppXdni = new ServiceBiPublisher();
        List<CpgLeadContAccProdFb> opp = new ArrayList<CpgLeadContAccProdFb>();
        
        opp = oppXdni.obtenerLeadProdContAccFbCpgXDni(dni);
        
        CollectionModel _tablaSup = (CollectionModel)tblDni.getValue();
        JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();

        int b = 0;
        b = itSup.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }

    Util.Log(opp.size() + "TAMAÑO");
        if(opp.size() > 0){
            
        for (CpgLeadContAccProdFb listOpp : opp) {
         
            rw = itSup.getNavigatableRowIterator().createRow();
            itSup.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);
            
            monedaLead = listOpp.getMonLead();
            
            if(monedaLead.equalsIgnoreCase("PEN")){
                signoMon = "S/ .";
            } else if(monedaLead.equalsIgnoreCase("US")){
                signoMon = "$";
            }

            rw.setAttribute("IdLead", listOpp.getIdLead());
            rw.setAttribute("LeadName", listOpp.getNomLead());
            rw.setAttribute("Producto", listOpp.getNomProd());
            rw.setAttribute("NomCont", listOpp.getNombre() + " " + listOpp.getApellidoPaterno());
            rw.setAttribute("Monto", signoMon + Util.formatearMonto(listOpp.getMonto()));
            rw.setAttribute("TcNomBin1", listOpp.getTcNombreBIN1());
            rw.setAttribute("TcNomBin2", listOpp.getTcNombreBIN2());
            rw.setAttribute("TcNomBin3", listOpp.getTcNombreBIN3());
            
            rw.setAttribute("Dni", listOpp.getNumeroDni());
        } 
        } else {

            b = itSup.getRowSetIterator().getRowCount();

            for (int i = 0; i < b; i++) {
                itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            
            FacesContext fctx = FacesContext.getCurrentInstance();
                         fctx.addMessage("",
                                         new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay leads para el dni ingresado",
                                                      ""));
        }
        } else {
           
            int b = 0;
            b = itDni.getRowSetIterator().getRowCount();

            for (int i = 0; i < b; i++) {
                itDni.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            FacesContext fctx = FacesContext.getCurrentInstance();
                         fctx.addMessage("",
                                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar DNI",
                                                      ""));
        }
    }

    
    public void obtenerDatos(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = "";
        String LeadName = "";
        String bin01 = "";
        String monto = "";
        String nomContacto = "";
        String dni = "";
        String telefono = "-";

        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsLeadXDniView1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idLead = rowSelected.getAttribute("IdLead").toString();
        LeadName = rowSelected.getAttribute("LeadName").toString();
        bin01 = rowSelected.getAttribute("TcNomBin1").toString(); 
        monto = rowSelected.getAttribute("Monto").toString(); 
        nomContacto = rowSelected.getAttribute("NomCont").toString(); 
        dni = rowSelected.getAttribute("Dni").toString(); 
        
        
       /* ctx.getSessionScope().put("LEADID", idLead);
        ctx.getSessionScope().put("LEADNOM", LeadName);
        ctx.getSessionScope().put("nombreBin", bin01);
        ctx.getSessionScope().put("monto", monto);
        ctx.getSessionScope().put("nomContacto", nomContacto);
        ctx.getSessionScope().put("dni", dni);*/
        
        Util.Log(idLead + "ID LEAD CAPTURADO");
        Util.Log(LeadName + "NOM LEAD CAPTURADO");
        Util.Log(bin01 + "NOM BIN CAPTURADO");
        Util.Log(monto + "monto CAPTURADO");
        
        String idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String idSup = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        String canal = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        String jefCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
        String idJefCanal = ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        String sup = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
        String ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String idEjVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
        
        
        Util.redirec("SBP_SLS_SPEECH_VENTA_NEW?telfActual=" + telefono + "&contacto=" + nomContacto +
                  "&montoDesem=" + monto + "&prod=" + bin01 + "&idLead=" + idLead + "&idCanal=" + idCanal +
                  "&canal=" + canal + "&idJefCanal=" + idJefCanal + "&jefCanal=" + jefCanal + "&idSup=" +
                  idSup + "&sup=" + sup + "&idEjVenta=" + idEjVenta + "&ejVenta=" + ejVenta + "&numDoc=" + dni);
        
    }

    
    public void onBeforePhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId().getOrdinal() ==
            Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
            onLoad();
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
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, expression,
                                            Object.class);
        return valueExp.getValue(elContext);
    }
    
    public void onLoad() {
        
        ADFContext ctx = ADFContext.getCurrent();
    
        txtUsuario.setValue((String) resolveExpression("#{viewScope.userId2}"));
        
        String usuario = txtUsuario.getValue().toString();
        
        Util.Log(usuario + "USUARIO");
        
        ctx.getSessionScope().put("ejVentaDni", usuario);
        
        obtenerCanal(usuario);
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

        ctx.getSessionScope().put("IdCan", canalId);
        ctx.getSessionScope().put("SupId", supId);
        ctx.getSessionScope().put("Canal", nomCanal);
        ctx.getSessionScope().put("JefeCanal", jefeCanal);
        ctx.getSessionScope().put("IdJefeCanal", idJefeCanal);
        ctx.getSessionScope().put("Super", sup);
        ctx.getSessionScope().put("EjecVenta", ejVenta);
        ctx.getSessionScope().put("IdEjecVenta", idEjecVenta);
        ctx.getSessionScope().put("userLog", userName);
    }

    public void setTblDni(RichTable tblDni) {
        this.tblDni = tblDni;
    }

    public RichTable getTblDni() {
        return tblDni;
    }

    public void setTxtUsuario(RichInputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public RichInputText getTxtUsuario() {
        return txtUsuario;
    }

    public void obtenerDatos2(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = "";
        String LeadName = "";
        String bin02 = "";
        String nomContacto = "";
        String monto = "";
        String dni = "";
        String telefono = "-";

        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsLeadXDniView1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idLead = rowSelected.getAttribute("IdLead").toString();
        LeadName = rowSelected.getAttribute("LeadName").toString();
        bin02 = rowSelected.getAttribute("TcNomBin2").toString(); 
        monto = rowSelected.getAttribute("Monto").toString(); 
        nomContacto = rowSelected.getAttribute("NomCont").toString(); 
        dni = rowSelected.getAttribute("Dni").toString(); 
        
        /*     ctx.getSessionScope().put("LEADID", idLead);
        ctx.getSessionScope().put("LEADNOM", LeadName);
        ctx.getSessionScope().put("nombreBin", bin02);
        ctx.getSessionScope().put("nomContacto", nomContacto);
        ctx.getSessionScope().put("monto", monto);
        ctx.getSessionScope().put("dni", dni);*/
        
        Util.Log(idLead + "ID LEAD CAPTURADO");
        Util.Log(LeadName + "NOM LEAD CAPTURADO");
        Util.Log(bin02 + "NOM BIN CAPTURADO");
        
        String idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String idSup = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        String canal = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        String jefCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
        String idJefCanal = ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        String sup = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
        String ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String idEjVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
        
        
        Util.redirec("SBP_SLS_SPEECH_VENTA_NEW?telfActual=" + telefono + "&contacto=" + nomContacto +
                  "&montoDesem=" + monto + "&prod=" + bin02 + "&idLead=" + idLead + "&idCanal=" + idCanal +
                  "&canal=" + canal + "&idJefCanal=" + idJefCanal + "&jefCanal=" + jefCanal + "&idSup=" +
                  idSup + "&sup=" + sup + "&idEjVenta=" + idEjVenta + "&ejVenta=" + ejVenta + "&numDoc=" + dni);
        
    }

    public void obtenerDatos3(ActionEvent actionEvent) {
       
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = "";
        String LeadName = "";
        String bin03 = "";
        String nomContacto = "";
        String monto = "";
        String dni = "";
        String telefono = "-";

        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsLeadXDniView1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idLead = rowSelected.getAttribute("IdLead").toString();
        LeadName = rowSelected.getAttribute("LeadName").toString();
        bin03 = rowSelected.getAttribute("TcNomBin3").toString(); 
        monto = rowSelected.getAttribute("Monto").toString(); 
        nomContacto = rowSelected.getAttribute("NomCont").toString(); 
        dni = rowSelected.getAttribute("Dni").toString(); 
        
      /*  ctx.getSessionScope().put("LEADID", idLead);
        ctx.getSessionScope().put("LEADNOM", LeadName);
        ctx.getSessionScope().put("nombreBin", bin03);
        ctx.getSessionScope().put("nomContacto", nomContacto);
        ctx.getSessionScope().put("monto", monto);
        ctx.getSessionScope().put("dni", dni);*/
        
        Util.Log(idLead + "ID LEAD CAPTURADO");
        Util.Log(LeadName + "NOM LEAD CAPTURADO");
        Util.Log(bin03 + "NOM BIN CAPTURADO");
        
        String idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
                String idSup = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
                String canal = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
                String jefCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
                String idJefCanal = ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
                String sup = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
                String ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
                String idEjVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
                
                
                Util.redirec("SBP_SLS_SPEECH_VENTA_NEW?telfActual=" + telefono + "&contacto=" + nomContacto +
                          "&montoDesem=" + monto + "&prod=" + bin03 + "&idLead=" + idLead + "&idCanal=" + idCanal +
                          "&canal=" + canal + "&idJefCanal=" + idJefCanal + "&jefCanal=" + jefCanal + "&idSup=" +
                          idSup + "&sup=" + sup + "&idEjVenta=" + idEjVenta + "&ejVenta=" + ejVenta + "&numDoc=" + dni);
        
    }
}

package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;

import dao.Validador;
import dao.VentaObsBackOf;

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
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_VENTA_OBS_BO {
    private RichForm f1;
    private RichDocument d1;
    private RichOutputLabel txtCantVentas;
    private RichTable tblRepBO;
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

    public void setTxtCantVentas(RichOutputLabel txtCantVentas) {
        this.txtCantVentas = txtCantVentas;
    }

    public RichOutputLabel getTxtCantVentas() {
        return txtCantVentas;
    }

    public void setTblRepBO(RichTable tblRepBO) {
        this.tblRepBO = tblRepBO;
    }

    public RichTable getTblRepBO() {
        return tblRepBO;
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
        
        txtUsuario.setValue((String) resolveExpression("#{viewScope.userName2}"));
        
        String userName = txtUsuario.getValue().toString();
        
        Util.Log("userName --> " + userName);
        
        ctx.getSessionScope().put("userName", userName);
        
        obtenerCanal(userName);
        
        Util.Log("ENTRA AL ON LOAD");
        
    }
    
    
    public void obtenerCanal(String userName) {

           ADFContext ctx = ADFContext.getCurrent();

           ServiceBiPublisher canal = new ServiceBiPublisher();

           String canalId = "";
           String valId = "";
           String nomCanal = "";
           String validador = "";


           List<Validador> lista2 = new ArrayList<Validador>();
           lista2 = canal.obtenerCanalVal(userName);

           for (Validador valList : lista2) {

              
               canalId = valList.getIdCanal();
               valId = valList.getIdValidador();
               nomCanal = valList.getNomCanal();
               validador = valList.getNomValidador();
              

           }

           ctx.getSessionScope().put("canId", canalId);
        
       }
    
    public void obtenerDatos(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = "";
        String LeadName = "";

        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsRepBackOfVO1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idLead = rowSelected.getAttribute("idLead").toString();
        LeadName = rowSelected.getAttribute("leadName").toString();
        
        ctx.getSessionScope().put("IDLEAD", idLead);
        ctx.getSessionScope().put("NOMLEAD", LeadName);
        
        Util.Log(idLead + "ID LEAD CAPTURADO");
        Util.Log(LeadName + "NOM LEAD CAPTURADO");
      
        
    }

    public void setTxtUsuario(RichInputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public RichInputText getTxtUsuario() {
        return txtUsuario;
    }
 
    public void buscarVentas(ActionEvent actionEvent) {
      
        ADFContext ctx = ADFContext.getCurrent();
        
        String idCanal = ctx.getSessionScope().get("canId") == null ? "" : ctx.getSessionScope().get("canId").toString();
        
        Util.Log(idCanal + "ID CANAL");
        
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<VentaObsBackOf> listBackOf = new ArrayList<VentaObsBackOf>();

        listBackOf = can.obtenerVentasObsBackOf(idCanal);
        
        txtCantVentas.setValue(listBackOf.size());

        Util.Log(listBackOf.size() + "tamaño");

        CollectionModel _tablaCanal= (CollectionModel)tblRepBO.getValue();
        JUCtrlHierBinding _adfTableCanalBinding = (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCanal =  _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        b = itCanal.getRowSetIterator().getRowCount();
        for (int i = 0; i < b; i++) {
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        for (VentaObsBackOf listVentasBO : listBackOf) {
            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            rw.setAttribute("idEjVenta", listVentasBO.getIdEjVenta());
            rw.setAttribute("ejVenta", listVentasBO.getEjVenta());
            rw.setAttribute("dni", listVentasBO.getDni());
            rw.setAttribute("producto", listVentasBO.getProducto());
            rw.setAttribute("monto", Util.formatearMonto(listVentasBO.getMonto()));
            rw.setAttribute("telf", listVentasBO.getTelf());
            rw.setAttribute("leadName", listVentasBO.getLeadName());
            rw.setAttribute("idLead", listVentasBO.getIdLead());
            rw.setAttribute("estado", listVentasBO.getEstado());
            rw.setAttribute("motivo", listVentasBO.getMotivoObs());
            
            
        }
        
    }
}

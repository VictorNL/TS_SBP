package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.RepAgendaEjVenta;

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
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_REP_AGENDAS_EJ_V {
    private RichForm f1;
    private RichDocument d1;
    private RichOutputLabel txtCantAgendas;
    private RichInputText txtUsuario;
    private RichTable tblRep;

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

    public void setTxtCantAgendas(RichOutputLabel txtCantAgendas) {
        this.txtCantAgendas = txtCantAgendas;
    }

    public RichOutputLabel getTxtCantAgendas() {
        return txtCantAgendas;
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
        
        txtUsuario.setValue((String) resolveExpression("#{viewScope.userName2}"));     
       
    }
    
    
    
    private int nvl(RowSetIterator rowSetIterator, int i) {
        int res = 0;
        if(rowSetIterator != null){
            res = i;
        } else {
            res = rowSetIterator.getRowCount();
        }
        return res;
    }

    public void setTxtUsuario(RichInputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public RichInputText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTblRep(RichTable tblRep) {
        this.tblRep = tblRep;
    }

    public RichTable getTblRep() {
        return tblRep;
    }

    public void buscar(ActionEvent actionEvent) {
        
        String userName = txtUsuario.getValue().toString();
        
        Util.Log(userName + "USER NAME");
       
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<RepAgendaEjVenta> listAgendasEjVenta = new ArrayList<RepAgendaEjVenta>();

        listAgendasEjVenta = can.obtenerRepAgendasXEjVenta(userName);
        
        Util.Log(listAgendasEjVenta.size() + "tamaño");
        
        String cantAgendas = listAgendasEjVenta.size() + "";        
        
        txtCantAgendas.setValue(cantAgendas);
        
        CollectionModel _tablaCanal= (CollectionModel)tblRep.getValue();
        JUCtrlHierBinding _adfTableCanalBinding = (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCanal =  _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        
        b = itCanal.getRowSetIterator().getRowCount();
        
        for (int i = 0; i < b; i++) {
            Util.Log("ENTRA IF REMOVE");
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        
        for (RepAgendaEjVenta listGnrlEv : listAgendasEjVenta) {
            
            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);
            
            rw.setAttribute("ejVenta", listGnrlEv.getEjVenta());
            rw.setAttribute("producto", listGnrlEv.getProducto());
            rw.setAttribute("idLead", listGnrlEv.getIdLead());
            rw.setAttribute("dni", listGnrlEv.getDni());
            rw.setAttribute("monto", Util.formatearMonto(listGnrlEv.getMonto()));
            rw.setAttribute("telf", listGnrlEv.getTelf());
            rw.setAttribute("idEjVenta", listGnrlEv.getIdEjVenta());
            rw.setAttribute("fecha2daLlam", listGnrlEv.getFecha2daLlam());
            
        }
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
}

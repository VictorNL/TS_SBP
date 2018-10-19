package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.EjVenta;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_REP_VENTA_EV {
    private RichForm f1;
    private RichDocument d1;
    private RichOutputLabel txtCantVentas;
    private RichTable tblRep;
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

    public void setTblRep(RichTable tblRep) {
        this.tblRep = tblRep;
    }

    public RichTable getTblRep() {
        return tblRep;
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
        
        String userName = txtUsuario.getValue().toString();
        
        Util.Log(userName + "USER NAME");
        
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<EjVenta> listGnrlEjVenta = new ArrayList<EjVenta>();

        listGnrlEjVenta = can.obtenerReVentaEv(userName);

        Util.Log(listGnrlEjVenta.size() + "tamaño");

        CollectionModel _tablaCanal= (CollectionModel)tblRep.getValue();
        JUCtrlHierBinding _adfTableCanalBinding = (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCanal =  _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        b = nvl(itCanal.getRowSetIterator(),0);
        for (int i = 0; i < b; i++) {
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        for (EjVenta listGnrlEv : listGnrlEjVenta) {
            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            rw.setAttribute("EjVenta", listGnrlEv.getEjVenta());
            rw.setAttribute("Prod", listGnrlEv.getProd());
            rw.setAttribute("Dni", listGnrlEv.getDni());
            rw.setAttribute("Monto", Util.formatearMonto(listGnrlEv.getMonto()));
            rw.setAttribute("Telf", listGnrlEv.getTelf());
            rw.setAttribute("IdEjVenta", listGnrlEv.getIdEjVenta());
            txtCantVentas.setValue(listGnrlEv.getCantVentas());
            
        }
        
    }

    public void setTxtUsuario(RichInputText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public RichInputText getTxtUsuario() {
        return txtUsuario;
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
}

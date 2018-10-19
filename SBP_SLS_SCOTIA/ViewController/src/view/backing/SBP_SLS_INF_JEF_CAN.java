package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;

import dao.GnrlJefCan;

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

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_INF_JEF_CAN {
    private RichForm f1;
    private RichDocument d1;
    private RichTable tblInf;
    private RichInputText txtUserName;

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

    public void setTblInf(RichTable tblInf) {
        this.tblInf = tblInf;
    }

    public RichTable getTblInf() {
        return tblInf;
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
        
        txtUserName.setValue((String) resolveExpression("#{viewScope.userName2}"));
        
        String userName = txtUserName.getValue().toString();
        
        Util.Log(userName + "USER NAME");
        
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<GnrlJefCan> listGnrl = new ArrayList<GnrlJefCan>();

        listGnrl = can.obtenerGnrlJefCan(userName);

        Util.Log(listGnrl.size() + "tamaño");


        CollectionModel _tablaCanal= (CollectionModel)tblInf.getValue();
        JUCtrlHierBinding _adfTableCanalBinding = (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCanal =  _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        b = nvl(itCanal.getRowSetIterator(),0);
        for (int i = 0; i < b; i++) {
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        
        for (GnrlJefCan listGnrlJefCan : listGnrl) {

            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdCpg", listGnrlJefCan.getIdCpg());
            rw.setAttribute("Nomcpg", listGnrlJefCan.getNomCpg());
            rw.setAttribute("Cantleadcpg", Util.formatearMonto(listGnrlJefCan.getCantLeadCpg()));
            rw.setAttribute("Idsup", listGnrlJefCan.getIdSup());
            rw.setAttribute("Nomsup", listGnrlJefCan.getNomSup());
            
            rw.setAttribute("Cantleadsup", listGnrlJefCan.getCantLeadSup());
            rw.setAttribute("Idejventa", listGnrlJefCan.getIdEjVenta());
            rw.setAttribute("Nomejventa", listGnrlJefCan.getNomEjVenta());
           
            rw.setAttribute("Cantleadejventa", listGnrlJefCan.getCantLeadEjVenta());
            
            rw.setAttribute("CantVentEjVenta", listGnrlJefCan.getCantVentaEjVenta());
            System.out.println(listGnrlJefCan.getCantVentaEjVenta() + "ventas");
        }
        
    }

    public void setTxtUserName(RichInputText txtUserName) {
        this.txtUserName = txtUserName;
    }

    public RichInputText getTxtUserName() {
        return txtUserName;
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

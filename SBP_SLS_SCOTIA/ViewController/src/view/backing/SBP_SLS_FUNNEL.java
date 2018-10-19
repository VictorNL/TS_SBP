package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;
import dao.Funnel;
import dao.GnrlJefCan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_FUNNEL {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl1;
    private RichTable t1;
    private RichMessages m1;
    private RichTable tblFunnel;
    private RichSelectOneChoice txtCpgNom;
    private RichInputText txtCpgId;
    private RichInputText txtCanalId;

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

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
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
        
        insertarDataCanal();
        insertarDataCpg();
    }


    public void buscar(ActionEvent actionEvent) {
        
        if(txtCpgId.getValue() == null || txtCanalId.getValue() == null){
      
            FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "Debe seleccionar un valor"  ,
                                                                          ""));
            
        } else {
                       
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<Funnel> listFunnel = new ArrayList<Funnel>();

        listFunnel = can.obtenerFunnel(txtCpgId.getValue().toString(), txtCanalId.getValue().toString());
        
        Util.Log(txtCpgId.getValue().toString() + "CPGID");
            
        Util.Log(txtCanalId.getValue().toString() + "CANALID");

        Util.Log(listFunnel.size() + "tamaño");


        CollectionModel _tablaCanal= (CollectionModel)tblFunnel.getValue();
        JUCtrlHierBinding _adfTableCanalBinding = (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCanal =  _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        for (int i = 0; i < b; i++) {
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        
        for (Funnel listInfFunnel : listFunnel) {

            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdJefCan", listInfFunnel.getIdJefCan());
            rw.setAttribute("JefCan", listInfFunnel.getJefCan());
            rw.setAttribute("IdSup", listInfFunnel.getIdSup());
            rw.setAttribute("Sup", listInfFunnel.getSup());
            rw.setAttribute("IdEjVenta", listInfFunnel.getIdEjVenta());
            
            rw.setAttribute("EjVenta", listInfFunnel.getEjVenta());
            rw.setAttribute("CantLeads", listInfFunnel.getCantLeads());
            rw.setAttribute("CantFb", listInfFunnel.getCantFb());
            rw.setAttribute("CantCont", listInfFunnel.getCantCont());
            rw.setAttribute("CantContEf", listInfFunnel.getCantContEf());
            rw.setAttribute("CantVentas", listInfFunnel.getCantVenta());
            
            if(listInfFunnel.getCantLeads().equalsIgnoreCase("0")){
                
             rw.setAttribute("FbLeads", "0");
             rw.setAttribute("ContLeads","0");
             rw.setAttribute("ContEfLeads","0");
             rw.setAttribute("EfectBase","0");
             
            } else {
                
            rw.setAttribute("FbLeads", Math.floor(Double.parseDouble(listInfFunnel.getCantFb())/Double.parseDouble(listInfFunnel.getCantLeads())));
            rw.setAttribute("ContLeads", Math.floor(Double.parseDouble(listInfFunnel.getCantCont())/Double.parseDouble(listInfFunnel.getCantLeads())));
            rw.setAttribute("ContEfLeads", Math.floor(Double.parseDouble(listInfFunnel.getCantContEf())/Double.parseDouble(listInfFunnel.getCantLeads())));
            rw.setAttribute("EfectBase", Math.floor(Double.parseDouble(listInfFunnel.getCantVenta())/Double.parseDouble(listInfFunnel.getCantLeads())));
                   }
        }
        }
    }

    public void setTblFunnel(RichTable tblFunnel) {
        this.tblFunnel = tblFunnel;
    }

    public RichTable getTblFunnel() {
        return tblFunnel;
    }
    
    
    public void insertarDataCanal(){
        
        ServiceBiPublisher canal = new ServiceBiPublisher();
        
        List<Canal> lista2 = new ArrayList<Canal>();
        lista2 = canal.obtenerDatosCan();
        
        Util.Log(lista2.size() + "TAMAÑOS CANAL");
      
        PreparedStatement prepareStatement = null;
        
        for(Canal listCan : lista2){
            
    

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;
        
        try {

            String query =
                "BEGIN SBP_SLS_FUNNEL_PKG.insert_datos_canal(?,?); END;";

            Util.Log(query);

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);

            prepareStatement.setString(1, listCan.getCanalId());
            prepareStatement.setString(2, listCan.getCanal());
            
            prepareStatement.execute();
           
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "MENSAJE");
        } finally {
            DbUtils.closeQuietly(prepareStatement);
            DbUtils.closeQuietly(conn);
        }
        }
    }
    
    public void insertarDataCpg(){
        
        ServiceBiPublisher canal = new ServiceBiPublisher();
        
        List<Canal> lista2 = new ArrayList<Canal>();
        lista2 = canal.obtenerDatosCpg();
        
        Util.Log(lista2.size() + "TAMAÑOS CPG");
      
        PreparedStatement prepareStatement = null;
        
        for(Canal listCan : lista2){
            
    

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;
        
        try {

            String query =
                "BEGIN SBP_SLS_FUNNEL_PKG.insert_datos_cpg(?,?); END;";

            Util.Log(query);

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);

            prepareStatement.setString(1, listCan.getIdCpg());
            prepareStatement.setString(2, listCan.getNomCpg());
            
            prepareStatement.execute();
           
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "MENSAJE");
        } finally {
            DbUtils.closeQuietly(prepareStatement);
            DbUtils.closeQuietly(conn);
        }
        }
    }

    public void setTxtCpgNom(RichSelectOneChoice txtCpgNom) {
        this.txtCpgNom = txtCpgNom;
    }

    public RichSelectOneChoice getTxtCpgNom() {
        return txtCpgNom;
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void setTxtCanalId(RichInputText txtCanalId) {
        this.txtCanalId = txtCanalId;
    }

    public RichInputText getTxtCanalId() {
        return txtCanalId;
    }
}

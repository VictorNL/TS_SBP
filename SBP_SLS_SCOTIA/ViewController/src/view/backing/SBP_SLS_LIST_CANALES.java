package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Campania;
import dao.Canal;
import dao.EjVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

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
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_LIST_CANALES {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl3;
    private RichOutputLabel ol1;
    private RichPanelGroupLayout pgl4;
    private RichCommandButton cb1;
    private RichPanelGroupLayout pgl2;
    private RichMessages m1;
    private RichTable t1;
    private RichTable tblCanal;
    private RichInputText txtCpgId;
    private RichOutputText txtCantidad;

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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }


    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }
    
    public void setTblCanal(RichTable tblCanal) {
        this.tblCanal = tblCanal;
    }

    public RichTable getTblCanal() {
        return tblCanal;
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public String nomCpg(String idCpg) {

        String nomCpg = "";
        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> Nomcpg = new ArrayList<Campania>();

        Nomcpg = cpg.obtenerNomCpg(idCpg);

        for (Campania cpgList : Nomcpg) {
            nomCpg = cpgList.getCpgNom();
        }

        Util.Log(nomCpg + "NOM CPG");
        return nomCpg;
    }

    public void buscarCanales(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();      
        
        List<Canal> listCanKardex = new ArrayList<Canal>();
        
        String cpgId = ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();
        
        String nomCpg = ctx.getSessionScope().get("cpanaNom") == null ? "" : ctx.getSessionScope().get("cpanaNom").toString();
        
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<Canal> listCanal = new ArrayList<Canal>();
        
        List<Canal> listIdJefCan = new ArrayList<Canal>();

        listCanal = can.obtenerCanalesLead(cpgId);

        CollectionModel _tablaCanal= (CollectionModel)tblCanal.getValue();
        JUCtrlHierBinding _adfTableCanalBinding =
            (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCanal =
            _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        b = itCanal.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (Canal listCan : listCanal) {

            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);
            
            if(!listCan.getCant().equalsIgnoreCase("0")){            
      
            rw.setAttribute("IdCanal", listCan.getCanalId());
            rw.setAttribute("NomCanal", listCan.getCanal());
            rw.setAttribute("Cantidad", Integer.parseInt(listCan.getCant()));
            rw.setAttribute("PartyNumber", listCan.getPartyNumber());
            
            }           
        }
        
        for (Canal listCan : listCanal) {
            Canal canKardex = new Canal();
            canKardex.setCanalId(listCan.getCanalId());
            canKardex.setCanal(listCan.getCanal());
            canKardex.setIdCpg(cpgId);
            canKardex.setNomCpg(nomCpg);
            canKardex.setCant(listCan.getCant());
            listCanKardex.add(canKardex);
        }
        ctx.getSessionScope().put("listCanKardex", listCanKardex);

    }
    
    public void limpiarTablaCanal() {

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;


        String query = "truncate table SBP_SLS_CANAL_TEMP";


        Util.Log("entr");

        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            Util.Log(query + "query");
            prepareStatement.execute();
            prepareStatement.close();
            
            Util.Log("OOOOOOOOOOK");
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "excepcion" + e.getMessage() +
                               "IT DOES NOT WORK");
        } catch (NullPointerException e) {
            System.out.print(e + "excepcion" + e.getMessage() +
                             "NullPointerException caught");
        } catch (Exception e) {
            Util.Log(e + "excepcion" + e.getMessage() + "Exception");
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);

        }

    }
    
    public void obtenerDatos(ActionEvent actionEvent) {
        
        limpiarTablaCanal();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idCanal = "";
        String cantidad = "";
        String idCanalIns = "";
        String nomCanal = "";
        String partyNumber = "";
        String partyNumberIns = "";
        
        PreparedStatement prepareStatement = null;
                
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsListCanal2View1Iterator");
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idCanal = rowSelected.getAttribute("IdCanal").toString();
        cantidad = rowSelected.getAttribute("Cantidad").toString();
        partyNumber = rowSelected.getAttribute("PartyNumber").toString();
        
        ctx.getSessionScope().put("IdCaNaL", idCanal);
        ctx.getSessionScope().put("CaNtiDaD", cantidad);
        ctx.getSessionScope().put("PartYNumbEr", partyNumber);
        
        Util.Log(idCanal + "IDSUPER CAPTURADO");
        Util.Log(cantidad + "CANT CAPTURADO");
    
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
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }

    public void onLoad() {
        
        ADFContext ctx = ADFContext.getCurrent();                  
        
        this.txtCpgId.setValue((String) resolveExpression("#{viewScope.cpgId2}"));
        
        String cpgId = this.txtCpgId.getValue().toString();
        
        Util.Log(cpgId + "CPGID");
        
        ctx.getSessionScope().put("CampanaId", cpgId);
        
        String nomCpg = nomCpg(cpgId);
        
        ctx.getSessionScope().put("cpanaNom", nomCpg);

    }


    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void setTxtCantidad(RichOutputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public RichOutputText getTxtCantidad() {
        return txtCantidad;
    }
}

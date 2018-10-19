package view.backing;

import Client.MergeLeadInvoke;
import Client.OportunidadLib;

import Dao.SimplifiedImportActivity;

import WsEjRep.ServiceBiPublisher;

import client.ImportLeadResource;
import client.ImportOpportunityResource;

import com.oracle.xmlns.adf.svc.types.ProcessControl;
import com.oracle.xmlns.adf.svc.types.ReturnMode;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.Opportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.DeleteOpportunityPartner;

import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.revenues.revenueservice.RevenuePartner;

import dao.Campania;
import dao.Canal;

import dao.Lead;
import dao.LeadDelete;
import dao.NuevoDestino;

import dao.OptyDelete;

import java.io.ByteArrayOutputStream;

import java.net.SocketException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import oracle.adf.view.rich.component.rich.RichPanelWindow;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.BO.AsigReasigReporteBO;

import view.Util;

public class SBP_SLS_REASIG_CAN_SCT {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichOutputLabel ol1;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichCommandButton cb3;
    private RichCommandButton cb4;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichPanelGroupLayout pgl6;
    private RichOutputLabel ol2;
    private RichOutputLabel ol3;
    private RichOutputLabel ol4;
    private RichMessages m1;
    private RichTable t1;
    private RichOutputLabel txtPorcAsig;
    private RichOutputLabel txtCantTotal;
    private RichTable tblCan;
    private RichOutputLabel txtTotalOpp;
    private RichInputText txtCantidad;
    private RichInputText txtPorc;
    private RichOutputLabel cantAsig;
    private RichOutputLabel txtCantAsig;
    private RichPopup popConfirm;
    private RichOutputLabel txtPorcAsignado;
    private RichOutputLabel txtTotalLead;

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

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setCb3(RichCommandButton cb3) {
        this.cb3 = cb3;
    }

    public RichCommandButton getCb3() {
        return cb3;
    }

    public void setCb4(RichCommandButton cb4) {
        this.cb4 = cb4;
    }

    public RichCommandButton getCb4() {
        return cb4;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setOl2(RichOutputLabel ol2) {
        this.ol2 = ol2;
    }

    public RichOutputLabel getOl2() {
        return ol2;
    }

    public void setOl3(RichOutputLabel ol3) {
        this.ol3 = ol3;
    }

    public RichOutputLabel getOl3() {
        return ol3;
    }

    public void setOl4(RichOutputLabel ol4) {
        this.ol4 = ol4;
    }

    public RichOutputLabel getOl4() {
        return ol4;
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

    public void setTblCan(RichTable tblCan) {
        this.tblCan = tblCan;
    }

    public RichTable getTblCan() {
        return tblCan;
    }

    public void setTxtTotalOpp(RichOutputLabel txtTotalOpp) {
        this.txtTotalOpp = txtTotalOpp;
    }

    public RichOutputLabel getTxtTotalOpp() {
        return txtTotalOpp;
    }

    public void setTxtCantidad(RichInputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public RichInputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtPorc(RichInputText txtPorc) {
        this.txtPorc = txtPorc;
    }

    public RichInputText getTxtPorc() {
        return txtPorc;
    }

    public void setCantAsig(RichOutputLabel cantAsig) {
        this.cantAsig = cantAsig;
    }

    public RichOutputLabel getCantAsig() {
        return cantAsig;
    }

    public void setTxtCantAsig(RichOutputLabel txtCantAsig) {
        this.txtCantAsig = txtCantAsig;
    }

    public RichOutputLabel getTxtCantAsig() {
        return txtCantAsig;
    }

    public void setTxtPorcAsig(RichOutputLabel txtPorcAsig) {
        this.txtPorcAsig = txtPorcAsig;
    }

    public RichOutputLabel getTxtPorcAsig() {
        return txtPorcAsig;
    }

    public void setTxtCantTotal(RichOutputLabel txtCantTotal) {
        this.txtCantTotal = txtCantTotal;
    }

    public RichOutputLabel getTxtCantTotal() {
        return txtCantTotal;
    }

    public void setPopConfirm(RichPopup popConfirm) {
        this.popConfirm = popConfirm;
    }

    public RichPopup getPopConfirm() {
        return popConfirm;
    }


    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String agregar() {

        boolean valida;

        valida = validacionAgregar();

        if (valida == false) {

            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("CreateInsert");
            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                return null;
            }
        }
        return null;
    }

    public boolean validacionAgregar() {

        ADFContext ctx = ADFContext.getCurrent();

        String cantidadOpp =
            ctx.getSessionScope().get("CaNtiDaD") == null ? "" :
            ctx.getSessionScope().get("CaNtiDaD").toString();


        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;


        totalOp = Double.parseDouble(cantidadOpp);

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsCanalView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());
                totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsig").toString());

                rsi.next();
            }

            currRow = rsi.last();

            totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());
            totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsig").toString());

        }

        this.txtPorcAsig.setValue(totalPorcAsig);
        this.txtCantTotal.setValue(totalCant);

        if (Double.parseDouble(txtPorcAsig.getValue().toString()) >= 100 ||
            Double.parseDouble(txtCantTotal.getValue().toString()) >=
            totalOp) {
            FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "no hay oportunidades por asignar",
                                                                          ""));

            Util.Log("cumple");
            return true;


        }

        else {

            Util.Log("no cumple");
            return false;
        }
    }

    public String guardar() {

        boolean valida;

        valida = validacionGuardar();

        if (valida == false) {

            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("Commit");
            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                return null;
            }
        }
        return null;
    }

    public boolean validacionGuardar() {


        ADFContext ctx = ADFContext.getCurrent();

        String cantidadOpp =
            ctx.getSessionScope().get("CaNtiDaD") == null ? "" :
            ctx.getSessionScope().get("CaNtiDaD").toString();


        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        // ServiceBiPublisher client = new ServiceBiPublisher();

        totalOp = Double.parseDouble(cantidadOpp);

        Util.Log(totalOp + "");

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsCanalView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());
                totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsig").toString());

                rsi.next();
            }

            currRow = rsi.last();

            totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());
            totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsig").toString());


        }

        txtPorcAsig.setValue(totalPorcAsig);
        txtCantTotal.setValue(totalCant);

        if (Double.parseDouble(txtPorcAsig.getValue().toString()) > 100 ||
            Double.parseDouble(txtCantTotal.getValue().toString()) > totalOp) {
            FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "no hay oportunidades por asignar",
                                                                          ""));

            Util.Log("cumple");
            return true;


        }

        else {

            Util.Log("no cumple");
            return false;
        }
    }


    /*  public void asignarOpp(ActionEvent actionEvent) throws SocketException {

        reasignarOpp();

        OportunidadLib opty = new OportunidadLib();

        String idAsig = "";
        String totalCant = "";

        List<Canal> canal = new ArrayList<Canal>();

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsCanalView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {

                currRow = rsi.getCurrentRow();

                totalCant = currRow.getAttribute("Cantidad").toString();
                idAsig = currRow.getAttribute("IdCanal").toString();

                canal = listOppAsigSct(totalCant);

                Util.Log(canal.size() + "opp size");

                ProcessOpportunity procOpp = new ProcessOpportunity();

                ProcessControl procCon = new ProcessControl();
                procOpp.setChangeOperation("Merge");

                Util.Log("//----------------ASIGNACIÓN--------------------//");
                for (Canal canl : canal) {

                    Opportunity oppty = new Opportunity();

                    RevenuePartner revPar = new RevenuePartner();

                    oppty.setOptyId(Long.parseLong(canl.getOptyId()));
                    revPar.setOptyId(Long.parseLong(canl.getOptyId()));
                    revPar.setPartOrgPartyId(Long.parseLong(idAsig.toString()));
                    oppty.getRevenuePartnerPrimary().add(revPar);
                    procOpp.getOpportunity().add(oppty);

                    Util.Log(Long.parseLong(canl.getOptyId()));
                    Util.Log(Long.parseLong(idAsig.toString()) +
                                       "idasig");

                }

                ReturnMode ret = ReturnMode.KEY;
                procCon.setReturnMode(ret);

                ReturnMode retu = ReturnMode.FULL;
                procCon.setExceptionReturnMode(retu);

                procCon.setPartialFailureAllowed(true);

                procOpp.setProcessControl(procCon);

                opty.processOpportunity(procOpp);

                Util.Log("//----------------ASIGNACIÓN--------------------//");

                rsi.next();
            }

            currRow = rsi.last();

            totalCant = currRow.getAttribute("Cantidad").toString();
            idAsig = currRow.getAttribute("IdCanal").toString();

            canal = listOppAsigSct(totalCant);

            ProcessOpportunity procOpp = new ProcessOpportunity();

            ProcessControl procCon = new ProcessControl();
            procOpp.setChangeOperation("Merge");

            Util.Log("//----------------ASIGNACIÓN--------------------//");
            for (Canal canl : canal) {

                Opportunity oppty = new Opportunity();

                RevenuePartner revPar = new RevenuePartner();

                oppty.setOptyId(Long.parseLong(canl.getOptyId()));
                revPar.setOptyId(Long.parseLong(canl.getOptyId()));
                revPar.setPartOrgPartyId(Long.parseLong(idAsig.toString()));
                oppty.getRevenuePartnerPrimary().add(revPar);
                procOpp.getOpportunity().add(oppty);

                Util.Log(Long.parseLong(canl.getOptyId()));
                Util.Log(Long.parseLong(idAsig.toString()) +
                                   "idasig");

            }

            ReturnMode ret = ReturnMode.KEY;
            procCon.setReturnMode(ret);

            ReturnMode retu = ReturnMode.FULL;
            procCon.setExceptionReturnMode(retu);

            procCon.setPartialFailureAllowed(true);

            procOpp.setProcessControl(procCon);

            opty.processOpportunity(procOpp);
            Util.Log("//----------------ASIGNACIÓN--------------------//");
        }
    }*/

    /* public void reasignarOpp() throws SocketException {


        ADFContext ctx = ADFContext.getCurrent();

        String idCanal =
            ctx.getSessionScope().get("IdCaNaL") == null ? "" : ctx.getSessionScope().get("IdCaNaL").toString();

        OportunidadLib opty = new OportunidadLib();

        String totalCant = "";

        ServiceBiPublisher opp = new ServiceBiPublisher();
        List<Canal> canalOpp = new ArrayList<Canal>();

        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding("SbpSlsCanalView1Iterator");

        ViewObject voTableData = dcItteratorBindings.getViewObject();

        Row rowSelected = voTableData.getCurrentRow();

        totalCant = rowSelected.getAttribute("Cantidad").toString();

        Util.Log(totalCant + "cant");
        Util.Log(idCanal + "id");

        canalOpp = opp.eliminarLeadSctJc(idCanal, totalCant);

        Util.Log(canalOpp.size() + "tamaño");

        DeleteOpportunityPartner delOppPart = new DeleteOpportunityPartner();
        RevenuePartner revPart = new RevenuePartner();
        PreparedStatement prepareStatement = null;

        for (Canal listCanal : canalOpp) {
            sbp.utils.Connection connection = new sbp.utils.Connection();
            PreparedStatement st = null;
            Connection conn = null;


            String query =
                "BEGIN SBP_SLS_OPP_QUIT_PKG.pr_insert_opp_quitada(?,?,?); END;";

            try {

                conn = connection.getConnection();
                prepareStatement = conn.prepareStatement(query);
                prepareStatement.setString(1, listCanal.getOptyId());
                prepareStatement.setString(2, listCanal.getCanalId());
                prepareStatement.setString(3, listCanal.getOptyNumber());

                prepareStatement.execute();
                prepareStatement.close();
                conn.close();

            } catch (SQLException e) {
                Util.Log(e + "excepcion" + e.getMessage() +
                                   "IT DOES NOT WORK");
            } catch (NullPointerException e) {
                System.out.print(e + "excepcion" + e.getMessage() +
                                 "NullPointerException caught");
            } catch (Exception e) {
                Util.Log(e + "excepcion" + e.getMessage() +
                                   "Exception");
            } finally {
                DbUtils.closeQuietly(st);
                DbUtils.closeQuietly(conn);

            }

            revPart.setOptyId(Long.parseLong(listCanal.getOptyId()));
            revPart.setPartOrgPartyId(Long.parseLong(listCanal.getCanalId()));
            delOppPart.setOpportunityPartnerVO(revPart);
            delOppPart.getOpportunityPartnerVO();
            opty.deleteOpportunityPartner(delOppPart);

            Util.Log(listCanal.getOptyId() + "OPTYID");
            Util.Log(listCanal.getCanalId() + "PARTORGPARTYID");
            Util.Log(listCanal.getOptyNumber() + "OPTYNUMBER");

        }
    }
*/

    public List<Canal> listOppAsigSct(String cantidad) {

        List<Canal> listOppCanal = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String optyId = "";


        try {
            String query = "select opty_id OPTY_ID from SBP_SLS_OPP_QUITADAS_SCT_CAN where rownum < " + cantidad + " + 1";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Canal can = new Canal();
                optyId = rs.getString("OPTY_ID");

                can.setOptyId(optyId);
                listOppCanal.add(can);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listOppCanal;
    }


    public List<Canal> listCanales() {

        List<Canal> listCan = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String canalId = "";
        String canal = "";
        String partyNumber = "";

        try {
            String query =
                "select id_canal ID_CANAL, nom_canal NOM_CANAL, party_number PARTY_NUMBER from SBP_SLS_CANAL_TEMP";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Canal can = new Canal();
                canalId = rs.getString("ID_CANAL");
                canal = rs.getString("NOM_CANAL");
                partyNumber = rs.getString("PARTY_NUMBER");


                can.setCanalId(canalId);
                can.setCanal(canal);
                can.setPartyNumber(partyNumber);
                listCan.add(can);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listCan;
    }

    public String porcTotal() {

        double porc = 0.0;
        String porcTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsCanalView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.next();
                porc += Math.floor(Double.parseDouble(currRow.getAttribute("PorcAsig").toString()));
                porcTotal = Double.toString(porc);
            }
        }
        Util.Log(porcTotal + "PORC TOTAL");
        return porcTotal;

    }
    
    

    public void mostrarDatos() {
       
     

        ADFContext ctx = ADFContext.getCurrent();
        Integer cantLead = Integer.parseInt(calcularCantLead());
        ctx.getSessionScope().put("CaNtiDaD", cantLead);

        String idCanal = ctx.getSessionScope().get("IdCaNaL") == null ? "" : ctx.getSessionScope().get("IdCaNaL").toString();
        
        String cpgId = ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();
        
        Util.Log(idCanal + "IDA CAAAAANALA");
        
        ServiceBiPublisher can = new ServiceBiPublisher();

        this.txtTotalLead.setValue(cantLead);

        List<Canal> listCanal = new ArrayList<Canal>();
        
        listCanal = can.obtenerListCanOk(idCanal);

        Integer cantCan = listCanal.size();

        CollectionModel _tablaCan = (CollectionModel)tblCan.getValue();
        JUCtrlHierBinding _adfTableCanBinding = (JUCtrlHierBinding)_tablaCan.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCan = _adfTableCanBinding.getDCIteratorBinding();
        
        int r = itCan.getRowSetIterator().getRowCount();
        for (int i = 0; i < r; i++) {
            itCan.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        Row rw = null;

        for (Canal listadoCanal : listCanal) {

            rw = itCan.getNavigatableRowIterator().createRow();
            itCan.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            rw.setAttribute("IdCanal", listadoCanal.getCanalId());
            rw.setAttribute("NomCanal", listadoCanal.getCanal());
            rw.setAttribute("PartyNumber", listadoCanal.getPartyNumber());
            rw.setAttribute("Cantidad", cantLead / cantCan);

        }
    }

    public String calcularCantLead() {

        ADFContext ctx = ADFContext.getCurrent();

        String cantReasig =
            ctx.getSessionScope().get("CaNtiDaD") == null ? "" :
            ctx.getSessionScope().get("CaNtiDaD").toString();

        String totalLead = "";

        totalLead = cantReasig;

        Util.Log(totalLead + "TOTALLLLLLL");
        return totalLead;

    }


    public String cantCanales() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String cantCanal = "";

        try {
            String query = "select count(id_canal) CANT_CANAL from SBP_SLS_CANAL_TEMP";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                cantCanal = rs.getString("CANT_CANAL");
            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(cantCanal + "CANT SUP");
        return cantCanal;
    }


    public String cantLeadxCan() {

        double cantLead = 0.0;
        double cantCan = 0.0;
        double resultadoLeadx = 0.0;


        //--------------CANT LEAD---------------------------//
        cantLead = Double.parseDouble(calcularCantLead());
        cantCan = Double.parseDouble(cantCanales());
        resultadoLeadx = cantLead / (cantCan);
        int resultadoInt = (int)resultadoLeadx;

        String resultadoLeadXSuper = Integer.toString(resultadoInt);

        //--------------CANT LEAD---------------------------//

        Util.Log(resultadoLeadXSuper + "RESULTADO");
       // txtTotalOpp.setValue(cantLead);
        //txtCantidad.setValue(Util.formatearMonto(resultadoLeadXSuper));
        return resultadoLeadXSuper;
    }

    public String porcAsignado() {

        double cantCan = 0.0;
        double resultadoPorc = 0.0;

        cantCan = Double.parseDouble(cantCanales());

        resultadoPorc = Math.round((100 / (cantCan)) * 100.0) / 100.0;

        String resultadoPorce = Double.toString(resultadoPorc);


       // txtPorc.setValue(resultadoPorce);
        //txtPorcAsignado.setValue(resultadoPorce);
        Util.Log(resultadoPorc + "RESULTADO PORC");
        return resultadoPorce;

    }


    public List<NuevoDestino> listDest() {

        List<NuevoDestino> listDestino = new ArrayList<NuevoDestino>();

        String canalId = "";
        String cant = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsCanalView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                NuevoDestino dest = new NuevoDestino();

                currRow = rsi.next();

                canalId = currRow.getAttribute("IdCanal").toString();
                cant = currRow.getAttribute("Cantidad").toString();

                dest.setResourceId(canalId);
                dest.setCantidad(cant);
                listDestino.add(dest);


            }
        }
        return listDestino;

    }

    public String cantLeadAsigCanSup() {

        int cant = 0;
        String cantTotalLead = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsCanalView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                currRow = rsi.next();

                cant +=
                        Integer.parseInt(currRow.getAttribute("Cantidad").toString());
                Util.Log(cant + "CAAAAAAAANT");

                cantTotalLead = Integer.toString(cant);

            }
        }
        return cantTotalLead;

    }


    //-----------------TAMAÑO DE LA LISTA--------------------//

    public void cantOppCanSct() {

        String cantTotal = "";

        cantTotal = cantLeadAsigCanSup();


        Util.Log(cantTotal + "CANTIDAD LISTA");
    }

    //-----------------TAMAÑO DE LA LISTA--------------------//


    public void asignar() {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher lead = new ServiceBiPublisher();

        List<Canal> leads = new ArrayList<Canal>();

        List<Canal> leadListadoTabla = new ArrayList<Canal>();

        List<NuevoDestino> newDest = new ArrayList<NuevoDestino>();

        String totalCant = "";

        String idCanal =
            ctx.getSessionScope().get("IdCaNaL") == null ? "" : ctx.getSessionScope().get("IdCaNaL").toString();

        String cpgId =
            ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();

        Util.Log(cpgId + "CPG");
        String partyNumber =
            ctx.getSessionScope().get("PartYNumbEr") == null ? "" :
            ctx.getSessionScope().get("PartYNumbEr").toString();

        totalCant = cantLeadAsigCanSup();
        Util.Log(totalCant + "TOTAL CANT");

        leads = lead.eliminarLeadSctJc(idCanal, totalCant, cpgId);

        Util.Log(leads.size() + "TAMAÑO LEAD");

        newDest = listDest();

        Util.Log(newDest.size() + "TAMAÑO NEW DEST");

        Util.Log(partyNumber + "PARTY NUMBER");


        insertarDataReasigList(leads, newDest);

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        leadListadoTabla = listLeadCan(idCarga);

        reasignarSctCan(leadListadoTabla);
        
        AsigReasigReporteBO.GenerarSBP_CAN(newDest);
    }

    public List<Canal> listLeadCan(String idCarga) {

        List<Canal> listaLeadsCan = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String leadNumber = "";
        String canalId = "";


        try {
            String query =
                "select lead_number LEAD_NUMBER, partner_id PARTNER_ID from SBP_SLS_TBL_REASIG_CAN where id_sec = " +
                idCarga + " order by numero_orden";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Canal lead = new Canal();
                leadNumber = rs.getString("LEAD_NUMBER");
                canalId = rs.getString("PARTNER_ID");

                lead.setLeadNumber(leadNumber);
                lead.setCanalId(canalId);
                listaLeadsCan.add(lead);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(listaLeadsCan.size() + "LISTA LIST LEAD");
        return listaLeadsCan;
    }

    public void insertarDataReasigList(List<Canal> plstLeads,
                                       List<NuevoDestino> plstNuevoDestino) {

        Integer intIdxLead = 0;
        String idCarga = obtenerIdCarga();

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        Util.Log("inicio");

        for (NuevoDestino desti : plstNuevoDestino) {

            for (int i = 0; i < Integer.parseInt(desti.getCantidad()); i++) {

                // Obtener lead
                Canal lead = plstLeads.get(intIdxLead);
                intIdxLead++;

                try {


                    String query =
                        "BEGIN SBP_SLS_REASIG_CAN_PKG.insert_datos(?,?,?); END;";

                    conn = connection.getConnection();
                    prepareStatement = conn.prepareStatement(query);
                    prepareStatement.setString(1, lead.getLeadNumber());
                    prepareStatement.setString(2, desti.getResourceId());
                    prepareStatement.setString(3, idCarga);

                    prepareStatement.execute();
                    prepareStatement.close();
                    conn.close();


                } catch (SQLException e) {

                } finally {
                    DbUtils.closeQuietly(prepareStatement);
                    DbUtils.closeQuietly(conn);
                }

            }
        }

        // Actualizar secuencial interno del lote
        actNumOrd();


        Util.Log("fin");
    }

    public void actNumOrd() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        int cargaId = Integer.parseInt(idCarga);


        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        Util.Log("inicio act num orden");


        try {


            String query =
                "BEGIN SBP_SLS_ACT_FLAG_RA_CAN_PKG.act_num_orden(?); END;";

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1, cargaId);

            prepareStatement.execute();
            Util.Log("entra");
            prepareStatement.close();
            conn.close();


        } catch (SQLException e) {

        } finally {
            DbUtils.closeQuietly(prepareStatement);
            DbUtils.closeQuietly(conn);
        }
    }

    public void eliminarDatosProc() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        int cargaId = Integer.parseInt(idCarga);


        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        try {


            String query =
                "BEGIN SBP_SLS_ACT_FLAG_RA_CAN_PKG.eliminar_data_proc(?); END;";

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setInt(1, cargaId);

            prepareStatement.execute();
            Util.Log("entra");
            prepareStatement.close();
            conn.close();


        } catch (SQLException e) {

        } finally {
            DbUtils.closeQuietly(prepareStatement);
            DbUtils.closeQuietly(conn);
        }
    }

    public String obtenerIdCarga() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idCarga = "";


        try {
            String query =
                "select SBP_SLS_ID_REASIG_CAN_S.nextval ID_CARGA from dual";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                idCarga = rs.getString("ID_CARGA");
            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(idCarga + "ID CARGA");
        ctx.getSessionScope().put("idCarga", idCarga);
        return idCarga;
    }


    public void reasignarSctCan(List<Canal> plstLeads) {

        SimplifiedImportActivity p = new SimplifiedImportActivity();
        
        String strTrama1;
        String strCabeceraLead1 = "LeadNumber,PartnerId" + "\n";
        Integer intIdxLead = 0;

        int pivot = 10;
        int ini = 0;
        int fin = pivot;
        int cant = plstLeads.size();

        
        try {
                  
             while (cant > 0) {
                  
                  if (cant > pivot) {
                      ByteArrayOutputStream bos = new ByteArrayOutputStream();

                      ZipOutputStream zos = new ZipOutputStream(bos);
                      ZipEntry ze =  new ZipEntry("Lead.csv");
                      zos.putNextEntry(ze);
                      
                      byte[] cabeceraLead1 = strCabeceraLead1.getBytes();
                      
                      zos.write(cabeceraLead1, 0, cabeceraLead1.length);
                      
                      List<Canal> listaLeads = new ArrayList<Canal>();
        
                  listaLeads = plstLeads.subList(ini, fin);
                  
                  intIdxLead = 0;
                  
                  for (int i = 0; i < listaLeads.size(); i++) {

                      Canal lead = listaLeads.get(intIdxLead);
                      intIdxLead++;
                  
                      strTrama1 = lead.getLeadNumber() + "," + lead.getCanalId() + "\n";
                      
                      Util.Log(strTrama1 + "strTrama1 if");
                  
                      byte[] bt = strTrama1.getBytes();
                      zos.write(bt, 0, bt.length);
                      
                  }
                          // Cerrar Entry
                          zos.closeEntry();
                              
                      
                      String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                      
                      Util.Log(str + "str if");
                      
                      MergeLeadInvoke mlc = new MergeLeadInvoke();
                      p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);
                      
                  } else {
                      
                        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
                        ZipOutputStream zos1 = new ZipOutputStream(bos1);
                        ZipEntry ze =  new ZipEntry("Lead.csv");
                        zos1.putNextEntry(ze);
                      
                      byte[] cabeceraLead1 = strCabeceraLead1.getBytes();
                                               
                       zos1.write(cabeceraLead1, 0, cabeceraLead1.length);
                       
                       List<Canal> listaLeads = new ArrayList<Canal>();

                      intIdxLead = 0;
                      
                      listaLeads = plstLeads.subList(ini, plstLeads.size());

                      for (int i = 0; i < listaLeads.size(); i++) {

                          Canal lead = listaLeads.get(intIdxLead);
                          intIdxLead++;
                      
                      
                          strTrama1 = lead.getLeadNumber() + "," + lead.getCanalId() + "\n";
                          
                          Util.Log(strTrama1 + "strTrama1 else");
                      
                          byte[] bt = strTrama1.getBytes();
                         
                          zos1.write(bt, 0, bt.length);
                          
                          
                      }
                              // Cerrar Entry
                              zos1.closeEntry();        
                      
                      String str = client.Base64.byteArrayToBase64(bos1.toByteArray());
                      Util.Log(str + "str else");
                      MergeLeadInvoke mlc = new MergeLeadInvoke();
                      p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);
                      
                  }
                      ini = fin;

                      fin = ini + pivot;

                      cant = cant - pivot;
                 
                      Util.Log(ini + "ini d");
                      Util.Log(fin + "fin d");
                      Util.Log(cant + "cant d");
                  }  
                    //  eliminarDataProcesada();
                 

              } catch (Exception ex) {
                  Util.Log("[Error] : " + ex.getMessage());
                  FacesContext.getCurrentInstance().addMessage("",
                                                               new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                                ex.getMessage(),
                                                                                ""));
              }

          }


    public void actualizaFlagLead(String idCarga, int ini, int fin) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        PreparedStatement prepareStatement = null;


        try {
            String query =
                "BEGIN SBP_SLS_ACT_FLAG_RA_CAN_PKG.act_flag(?,?,?); END;";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, idCarga);
            prepareStatement.setInt(2, ini);
            prepareStatement.setInt(3, fin);

            prepareStatement.execute();
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

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
        
        String cpgId = ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();

        mostrarDatos();
      //  cantLeadxCan();
      //  porcAsignado();

    }


    public void confimAsig(ActionEvent actionEvent) {

        String cantLead = cantLeadxCan();

        if (!cantLead.equalsIgnoreCase("0")) {


            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            String totalCant = cantLeadAsigCanSup();
            String totalPorc = porcTotal();
            txtCantAsig.setValue("Está seguro que desea asignar la cantidad de: " +
                                 totalCant + " correspondiente al " +
                                 totalPorc + "% de los leads");
            if (Double.parseDouble(totalPorc) <= 100) {
                getPopConfirm().show(hints);
            } else {
                FacesContext.getCurrentInstance().addMessage("",
                                                             new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                              "La cantidad asignada no debe exceder el 100% de los leads disponibles",
                                                                              ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "Ingrese un valor mayor a 0",
                                                                          ""));
        }
    }

    public void aceptarAsig(ActionEvent actionEvent) {
        asignar();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopConfirm().hide();
        FacesContext.getCurrentInstance().addMessage("",
                                                     new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                      "Espere 5 - 10 minutos que termina el proceso de reasignación",
                                                                      ""));
    }

    public void cancelarAsig(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopConfirm().hide();
    }

    public void setTxtPorcAsignado(RichOutputLabel txtPorcAsignado) {
        this.txtPorcAsignado = txtPorcAsignado;
    }

    public RichOutputLabel getTxtPorcAsignado() {
        return txtPorcAsignado;
    }

    public void setTxtTotalLead(RichOutputLabel txtTotalLead) {
        this.txtTotalLead = txtTotalLead;
    }

    public RichOutputLabel getTxtTotalLead() {
        return txtTotalLead;
    }
}

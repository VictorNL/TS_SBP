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
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.OpportunityResource;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.DeleteOpportunityReference;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.DeleteOpportunityResource;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunity;

import dao.Canal;

import dao.EjVenta;

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

public class SBP_SLS_REASIG_JC_SUP {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichPanelGroupLayout pgl4;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichCommandButton cb3;
    private RichCommandButton cb4;
    private RichCommandButton cb5;
    private RichPanelGroupLayout pgl3;
    private RichTable t1;
    private RichMessages m1;
    private RichTable tblReasigSup;
    private RichOutputLabel txtTotalPorc;
    private RichOutputLabel txtPorcAsig;
    private RichOutputLabel txtCantTotal;
    private RichTable tblSup;
    private RichOutputLabel txtTotalOpp;
    private RichInputText txtCantidad;
    private RichInputText txtPorc;
    private RichOutputLabel txtCantASig;
    private RichOutputLabel txtCantAsig;
    private RichPopup popupConfirm;
    private RichOutputLabel txtSupActual;
    private RichOutputLabel txtMontoRestante;
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

    public void setCb5(RichCommandButton cb5) {
        this.cb5 = cb5;
    }

    public RichCommandButton getCb5() {
        return cb5;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
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

    public void setTblReasigSup(RichTable tblReasigSup) {
        this.tblReasigSup = tblReasigSup;
    }

    public RichTable getTblReasigSup() {
        return tblReasigSup;
    }

    public void setTxtTotalPorc(RichOutputLabel txtTotalPorc) {
        this.txtTotalPorc = txtTotalPorc;
    }

    public RichOutputLabel getTxtTotalPorc() {
        return txtTotalPorc;
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

    public void setTblSup(RichTable tblSup) {
        this.tblSup = tblSup;
    }

    public RichTable getTblSup() {
        return tblSup;
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

    public void setTxtCantASig(RichOutputLabel txtCantASig) {
        this.txtCantASig = txtCantASig;
    }

    public RichOutputLabel getTxtCantASig() {
        return txtCantASig;
    }

    public void setTxtCantAsig(RichOutputLabel txtCantAsig) {
        this.txtCantAsig = txtCantAsig;
    }

    public RichOutputLabel getTxtCantAsig() {
        return txtCantAsig;
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }

    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    /*   public void buscarSupervisores(String idJefCanal) {


        Row rw = null;

        ServiceBiPublisher sup = new ServiceBiPublisher();

        List<Canal> listReasignSup = new ArrayList<Canal>();

        listReasignSup = sup.obtenerSupXJefCan(idJefCanal);


        Util.Log(listReasignSup.size() + "tamaño");


        CollectionModel _tablaReasignSup =
            (CollectionModel)tblReasigSup.getValue();
        JUCtrlHierBinding _adfTableReasignSupBinding =
            (JUCtrlHierBinding)_tablaReasignSup.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itReasignSup =
            _adfTableReasignSupBinding.getDCIteratorBinding();

        int b = 0;
        b = itReasignSup.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itReasignSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (Canal listReasignSuper : listReasignSup) {

            rw = itReasignSup.getNavigatableRowIterator().createRow();
            itReasignSup.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            rw.setAttribute("IdJefCanal", listReasignSuper.getIdJefCanal());
            rw.setAttribute("JefCanal", listReasignSuper.getJefCanal());
            rw.setAttribute("IdSup", listReasignSuper.getIdSup());
            rw.setAttribute("NomSup", listReasignSuper.getSup());
        }
    }
*/

    public String porcAsignado() {

        double cantSupxCan = 0.0;
        double resultadoPorc = 0.0;

        cantSupxCan = Double.parseDouble(cantSupXcan());

        resultadoPorc = Math.round((100 / (cantSupxCan)) * 100.0) / 100.0;

        String resultadoLeadXSuper = Double.toString(resultadoPorc);

      //  txtPorc.setValue(resultadoLeadXSuper);
      //  txtPorcAsig.setValue(resultadoLeadXSuper);
        Util.Log(resultadoLeadXSuper + "RESULTADO PORC");
        return resultadoLeadXSuper;

    }

    public void poblarSup(String idJefCan, String idSup) {

        limpiarTablaReasigSupEv();

        ServiceBiPublisher canal = new ServiceBiPublisher();

        List<EjVenta> lista2 = new ArrayList<EjVenta>();
        lista2 = canal.reasigLeadJcSu(idJefCan, idSup);

        PreparedStatement prepareStatement = null;

        for (EjVenta ejVentaList : lista2) {

            sbp.utils.Connection connection = new sbp.utils.Connection();
            PreparedStatement st = null;
            Connection conn = null;

            String query = "BEGIN SBP_SLS_JC_SUP_PKG.pr_insert_sup(?,?,?,?,?,?); END;";

            try {

                conn = connection.getConnection();
                prepareStatement = conn.prepareStatement(query);
                prepareStatement.setString(1, ejVentaList.getIdCanal());
                prepareStatement.setString(2, ejVentaList.getCanal());
                prepareStatement.setString(3, ejVentaList.getIdJefCan());
                prepareStatement.setString(4, ejVentaList.getJefCan());
                prepareStatement.setString(5, ejVentaList.getIdSup());
                prepareStatement.setString(6, ejVentaList.getSup());

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
        }
    }

    public void limpiarTablaReasigSupEv() {

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;


        String query = "truncate table SBP_SLS_LIST_JC_X_SUP_TEMP";


        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
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
            Util.Log(e + "excepcion" + e.getMessage() + "Exception");
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);

        }
    }

    public String agregar() {
        boolean valida;

        valida = validacionAgregar();

        if (valida == false) {

            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                return null;
            }
        }
        return null;
    }

    public boolean validacionAgregar() {

        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;


        ADFContext ctx = ADFContext.getCurrent();

        String cantOppSup =
            ctx.getSessionScope().get("CantSupOpp") == null ? "" :
            ctx.getSessionScope().get("CantSupOpp").toString();

        totalOp = Double.parseDouble(cantOppSup);

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant +=
                        Double.parseDouble(currRow.getAttribute("Cantidad").toString());


                totalPorcAsig +=
                        Double.parseDouble(currRow.getAttribute("PorcQuitar").toString());


                rsi.next();
            }

            currRow = rsi.last();

            totalCant +=
                    Double.parseDouble(currRow.getAttribute("Cantidad").toString());


            totalPorcAsig +=
                    Double.parseDouble(currRow.getAttribute("PorcQuitar").toString());


        }

        txtPorcAsig.setValue(totalPorcAsig);
        txtCantTotal.setValue(totalCant);

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
            OperationBinding operationBinding = bindings.getOperationBinding("Commit");
            Object result = operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                return null;
            }
        }
        return null;
    }

    public boolean validacionGuardar() {

        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        ADFContext ctx = ADFContext.getCurrent();

        String cantOppSup =
            ctx.getSessionScope().get("CantSupOpp") == null ? "" :
            ctx.getSessionScope().get("CantSupOpp").toString();

        // ServiceBiPublisher client = new ServiceBiPublisher();

        totalOp = Double.parseDouble(cantOppSup);

        Util.Log(totalOp + "");

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());
                totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcQuitar").toString());

                rsi.next();
            }

            currRow = rsi.last();

            totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());
            totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcQuitar").toString());

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

    public void reasignarOppJcSup() throws SocketException {

        ADFContext ctx = ADFContext.getCurrent();

        String idSup =
            ctx.getSessionScope().get("SUPerId") == null ? "" : ctx.getSessionScope().get("SUPerId").toString();

        OportunidadLib opty = new OportunidadLib();

        String totalCant = "";

        ServiceBiPublisher opp = new ServiceBiPublisher();
        List<OptyDelete> oportunidad = new ArrayList<OptyDelete>();

        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        ViewObject voTableData = dcItteratorBindings.getViewObject();

        Row rowSelected = voTableData.getCurrentRow();

        totalCant = rowSelected.getAttribute("Cantidad").toString();

        Util.Log(oportunidad.size() + "tamaño");
        Util.Log(totalCant + "cant");
        Util.Log(idSup + "id");

        //  oportunidad = opp.eliminarOpty(idSup, totalCant);

        DeleteOpportunityResource delOpp = new DeleteOpportunityResource();
        OpportunityResource oppRes = new OpportunityResource();
        PreparedStatement prepareStatement = null;


        for (OptyDelete oport : oportunidad) {
            sbp.utils.Connection connection = new sbp.utils.Connection();
            PreparedStatement st = null;
            Connection conn = null;


            String query =
                "BEGIN SBP_SLS_OPP_DESASIG_JC_SUP_PKG.pr_insert_opp_quitada(?,?,?); END;";

            try {

                conn = connection.getConnection();
                prepareStatement = conn.prepareStatement(query);
                prepareStatement.setString(1, oport.getOptyId());
                prepareStatement.setString(2, oport.getResourceId());
                prepareStatement.setString(3, oport.getOptyNumber());

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
            oppRes.setOptyId(Long.parseLong(oport.getOptyId()));
            oppRes.setOptyResourceId(Long.parseLong(oport.getOptyResourceId()));
            delOpp.setOpportunityResourceVO(oppRes);
            delOpp.getOpportunityResourceVO();
            opty.deleteOpportunityResource(delOpp);

            Util.Log(oport.getOptyId() + "OPTYID");
            Util.Log(oport.getOptyResourceId() + "OPTYRESOURCEID");

        }
    }

    public void asignarLead() {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher leads = new ServiceBiPublisher();

        List<LeadDelete> lead = new ArrayList<LeadDelete>();

        List<LeadDelete> leadListadoTabla = new ArrayList<LeadDelete>();

        List<NuevoDestino> newDest = new ArrayList<NuevoDestino>();

        String totalCant = "";

        String idSup =
            ctx.getSessionScope().get("SUPerId") == null ? "" : ctx.getSessionScope().get("SUPerId").toString();

        String cpgId =
            ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();


        totalCant = cantLeadAsigCanSup();

        lead = leads.eliminarLead(idSup, totalCant, cpgId);

        newDest = listDest();

        insertarDataReasigList(lead, newDest);

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        leadListadoTabla = listLeadSup(idCarga);

        reasignarJcSup(leadListadoTabla, idSup);
        
        AsigReasigReporteBO.GenerarJC_SUP(newDest);


    }

    public List<LeadDelete> listLeadSup(String idCarga) {

        List<LeadDelete> listaLeadsSup = new ArrayList<LeadDelete>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String leadNumber = "";
        String supId = "";


        try {
            String query =
                "select lead_number LEAD_NUMBER, sup_id SUP_ID from SBP_SLS_TBL_REASIG_SUP where id_sec = " +
                idCarga;

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                LeadDelete lead = new LeadDelete();
                leadNumber = rs.getString("LEAD_NUMBER");
                supId = rs.getString("SUP_ID");

                lead.setLeadNumber(leadNumber);
                lead.setResourceId(supId);
                listaLeadsSup.add(lead);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(listaLeadsSup.size() + "LISTA LIST LEAD");
        return listaLeadsSup;
    }

    public String obtenerIdCarga() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idCarga = "";


        try {
            String query =
                "select SBP_SLS_ID_REASIG_SUP_S.nextval ID_CARGA from dual";

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

    public void insertarDataReasigList(List<LeadDelete> plstLeads,
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
                LeadDelete lead = plstLeads.get(intIdxLead);
                intIdxLead++;

                try {


                    String query =
                        "BEGIN SBP_SLS_REASIG_SUP_PKG.insert_datos(?,?,?); END;";

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
        
        actNumOrd();
        
        Util.Log("fin");
    }
    
    
    public void actNumOrd(){
        
        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();
        
       int cargaId = Integer.parseInt(idCarga);

        
        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        Util.Log("inicio act num orden");


    try {


        String query = "BEGIN SBP_SLS_REASIG_SUP_PKG.act_num_orden(?); END;";

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
    
    public void eliminarDataProc(){
        
        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();
        
       int cargaId = Integer.parseInt(idCarga);

        
        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

    try {


        String query = "BEGIN SBP_SLS_REASIG_SUP_PKG.eliminar_data_proc(?); END;";

        conn = connection.getConnection();
        prepareStatement = conn.prepareStatement(query);
        prepareStatement.setInt(1, cargaId);

        prepareStatement.execute();
        prepareStatement.close();
        conn.close();


                } catch (SQLException e) {

                } finally {
                    DbUtils.closeQuietly(prepareStatement);
                    DbUtils.closeQuietly(conn);
                }
    }


    public void reasignarJcSup(List<LeadDelete> plstLeads,
                               String pstrResourceID) {
        
        SimplifiedImportActivity p = new SimplifiedImportActivity();
        
        String strTrama1;
        String strTrama2;
        String strTrama3;
        String strCabeceraLead1 = "LeadNumber" + "\n";
        String strCabeceraLead2 = "LeadNumber,ActionCode,ResourceId" + "\n"; 
        Integer intIdxLead = 0;

        int pivot = 1;
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
                      
                      List<LeadDelete> listaLeads = new ArrayList<LeadDelete>();
   
                  listaLeads = plstLeads.subList(ini, fin);
                  
                  intIdxLead = 0;
                  
                  for (int i = 0; i < listaLeads.size(); i++) {

                      LeadDelete lead = listaLeads.get(intIdxLead);
                      intIdxLead++;
                  
                      strTrama1 = lead.getLeadNumber();
                      
                      Util.Log(strTrama1 + "strTrama1 if");
                  
                      byte[] bt = strTrama1.getBytes();
                      zos.write(bt, 0, bt.length);
                      
                  }
                          // Cerrar Entry
                          zos.closeEntry();
                              
                              ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                              zos.putNextEntry(zef);
                              
                              byte[] cabeceraLead = strCabeceraLead2.getBytes();
                              zos.write(cabeceraLead, 0, cabeceraLead.length);
                      intIdxLead = 0;
                              for (int i = 0; i < listaLeads.size(); i++) {
                                  
                                  LeadDelete lead = listaLeads.get(intIdxLead);
                                  intIdxLead++;
                              
                                  strTrama2 = lead.getLeadNumber() + "," + "DELETE" + "," + pstrResourceID + "\n";
                                  strTrama3 = lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() + "\n";
                              
                                  Util.Log(strTrama2 + "strTrama2 if");
                                  
                                  Util.Log(strTrama3 + "strTrama3 if");
                                  
                                  byte[] bt = strTrama2.getBytes();
                                  byte[] bt2 = strTrama3.getBytes();
                                  
                                  zos.write(bt, 0, bt.length);
                                  zos.write(bt2, 0, bt2.length);
                                  
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
                       
                       List<LeadDelete> listaLeads = new ArrayList<LeadDelete>();

                      intIdxLead = 0;
                      
                      listaLeads = plstLeads.subList(ini, plstLeads.size());

                      for (int i = 0; i < listaLeads.size(); i++) {

                          LeadDelete lead = listaLeads.get(intIdxLead);
                          intIdxLead++;
                      
                      
                          strTrama1 = lead.getLeadNumber() + "\n";
                          
                          Util.Log(strTrama1 + "strTrama1 else");
                      
                          byte[] bt = strTrama1.getBytes();
                         
                          zos1.write(bt, 0, bt.length);
                          
                          
                      }
                              // Cerrar Entry
                              zos1.closeEntry();
                        
                      ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                      zos1.putNextEntry(zef);
                      byte[] cabeceraLead = strCabeceraLead2.getBytes();
                      zos1.write(cabeceraLead, 0, cabeceraLead.length);
                      
                                  intIdxLead = 0;
                                  for (int i = 0; i < listaLeads.size(); i++) {
                                      
                                      LeadDelete lead = listaLeads.get(intIdxLead);
                                      intIdxLead++;
                                  
                                      strTrama2 = lead.getLeadNumber() + "," + "DELETE" + "," + pstrResourceID + "\n";
                                      strTrama3 = lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() + "\n";
                                  
                                  
                                      Util.Log(strTrama2 + "strTrama2 else");
                                      
                                      Util.Log(strTrama3 + "strTrama3 else");
                                      
                                      
                                      byte[] bt = strTrama2.getBytes();
                                      byte[] bt2 = strTrama3.getBytes();
                                      

                                     
                                      zos1.write(bt, 0, bt.length);
                                      zos1.write(bt2, 0, bt2.length);
                                      
        
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
            String query = "BEGIN SBP_SLS_REASIG_SUP_PKG.act_flag(?,?,?); END;";

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

    public void asignarOppJcSup(ActionEvent actionEvent) throws SocketException {

        reasignarOppJcSup();

        OportunidadLib opty = new OportunidadLib();

        String idAsig = "";
        String totalCant = "";

        List<OptyDelete> oportunidad = new ArrayList<OptyDelete>();

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {

                currRow = rsi.getCurrentRow();

                totalCant = currRow.getAttribute("Cantidad").toString();
                idAsig = currRow.getAttribute("IdSup").toString();

                Util.Log(totalCant + "CANTIDAD1");
                oportunidad = listOppAsigJcSup(totalCant);

                Util.Log(oportunidad.size() + "opp size");

                ProcessOpportunity procOpp = new ProcessOpportunity();

                ProcessControl procCon = new ProcessControl();
                procOpp.setChangeOperation("Merge");

                Util.Log("//----------------ASIGNACIÓN--------------------//");
                for (OptyDelete oport : oportunidad) {

                    Opportunity oppty = new Opportunity();
                    OpportunityResource opRes = new OpportunityResource();

                    oppty.setOptyId(Long.parseLong(oport.getOptyId()));
                    opRes.setOptyId(Long.parseLong(oport.getOptyId()));
                    opRes.setResourceId(Long.parseLong(idAsig.toString()));
                    oppty.getOpportunityResource().add(opRes);
                    procOpp.getOpportunity().add(oppty);

                    Util.Log(Long.parseLong(oport.getOptyId()) + "");
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
            idAsig = currRow.getAttribute("IdSup").toString();

            Util.Log(totalCant + "CANTIDAD2");
            oportunidad = listOppAsigJcSup(totalCant);

            ProcessOpportunity procOpp = new ProcessOpportunity();

            ProcessControl procCon = new ProcessControl();
            procOpp.setChangeOperation("Merge");

            Util.Log("//----------------ASIGNACIÓN ULTIMA--------------------//");
            for (OptyDelete oport : oportunidad) {

                Opportunity oppty = new Opportunity();
                OpportunityResource opRes = new OpportunityResource();

                oppty.setOptyId(Long.parseLong(oport.getOptyId()));
                opRes.setOptyId(Long.parseLong(oport.getOptyId()));
                opRes.setResourceId(Long.parseLong(idAsig.toString()));
                oppty.getOpportunityResource().add(opRes);
                procOpp.getOpportunity().add(oppty);

                Util.Log(Long.parseLong(oport.getOptyId()) + "");
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
            Util.Log("//----------------ASIGNACIÓN ULTIMA--------------------//");
        }
    }


    public List<OptyDelete> listOppAsigJcSup(String cantidad) {

        List<OptyDelete> listOpp = new ArrayList<OptyDelete>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String optyId = "";


        try {
            String query =
                "select opty_id OPTY_ID from SBP_SLS_OPP_QUITADAS_JC_SUP where rownum < " +
                cantidad + " + 1";

            Util.Log(query + "QUERY");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                OptyDelete opp = new OptyDelete();
                optyId = rs.getString("OPTY_ID");

                opp.setOptyId(optyId);
                listOpp.add(opp);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return listOpp;
    }
    
    // Fernan Palomino
    // 06/10/2017
    public void mostrarDatos() {
        
        int cantEj = 0;
        
        Util.Log("Inicio");
        try {
            
            ADFContext ctx = ADFContext.getCurrent();
            Integer cantLead = Integer.parseInt(calcularCantLead());
            ctx.getSessionScope().put("CantSupLead", cantLead);
            
            txtTotalLead.setValue(cantLead);
            
            // Obtener de session la lista de supervisores
            List<Canal> listSupe = (List<Canal>)ctx.getSessionScope().get("ListSupCan"); 
            
            // Obtener de session el IdSupervisor que se quiataran leads
            String idSupDel = ctx.getSessionScope().get("SUPerId") == null ? "" : ctx.getSessionScope().get("SUPerId").toString();
            
            CollectionModel _tablaSup = (CollectionModel)tblSup.getValue();
            JUCtrlHierBinding _adfTableSupcBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
            DCIteratorBinding itSup = _adfTableSupcBinding.getDCIteratorBinding();

            for (int i = 0; i < itSup.getRowSetIterator().getRowCount(); i++) {
                itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            Row rw = null;
            
            Integer cantSup = listSupe.size() - 1;
            
            Util.Log(cantSup + "CANT SUP LIST");
            
            for(Canal Sup : listSupe){
                if (!Sup.getIdSup().equalsIgnoreCase(idSupDel)) {
                cantEj += Integer.parseInt(Sup.getCantEjXSup());
                }
            }
            
            Util.Log(cantEj + "cantEj");

            for (Canal Sup : listSupe) {

                if (!Sup.getIdSup().equalsIgnoreCase(idSupDel)) { // Excluir al sup seleccionado
                    
                    rw = itSup.getNavigatableRowIterator().createRow();
                    itSup.getNavigatableRowIterator().insertRow(rw);
                    rw.setNewRowState(Row.STATUS_INITIALIZED);
        
                    rw.setAttribute("IdCanal", Sup.getCanalId());
                    rw.setAttribute("NomCanal", Sup.getCanal());
                    rw.setAttribute("IdJefCanal", Sup.getIdJefCanal());
                    rw.setAttribute("JefCanal", Sup.getJefCanal());
                    rw.setAttribute("IdSup", Sup.getIdSup());
                    rw.setAttribute("NomSup", Sup.getSup());
                    rw.setAttribute("Cantidad", (cantLead/cantEj)* Integer.parseInt(Sup.getCantEjXSup()));
        
                    ctx.getSessionScope().put("CaNaLiD", Sup.getCanalId());
                }
            }
            
            // dividir en parte iguales la cantidad de lead
            double cantleadReas = Double.parseDouble(ctx.getSessionScope().get("CantSupLead").toString());
            double cantSupeCana = listSupe.size() - 1;
            double porcResignac = Math.round((100 / (cantleadReas)) * 100.0) / 100.0;
                
            ctx.getSessionScope().put("cantLeadTot", cantleadReas); // Evaluar si es necesario
            
            // Mostrar datos
            this.txtTotalLead.setValue(cantleadReas);
            this.txtSupActual.setValue(ctx.getSessionScope().get("nomSUP").toString());
           // this.txtCantidad.setValue(Util.formatearMonto(((int)cantleadReas/cantSupeCana) + ""));
          //  this.txtPorc.setValue(porcResignac + "");
           // this.txtPorcAsig.setValue(porcResignac + "");
            
        } catch (Exception e){
            Util.Log(e.getMessage());
        }        
        Util.Log("Fin");
    }

    // Obsoleto
    public void mostrarDatos(String idCanal) {

        ADFContext ctx = ADFContext.getCurrent();

        List<Canal> listSupe = new ArrayList<Canal>();

        listSupe = listSup(idCanal);

        Util.Log(listSupe.size() + "TAMAÑOOOOOOOOOOOO");
        
        CollectionModel _tablaSup = (CollectionModel)tblSup.getValue();
        JUCtrlHierBinding _adfTableSupcBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itSup = _adfTableSupcBinding.getDCIteratorBinding();

        for (int i = 0; i < itSup.getRowSetIterator().getRowCount(); i++) {
            itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        Row rw = null;

        for (Canal listadoSup : listSupe) {

            rw = itSup.getNavigatableRowIterator().createRow();
            itSup.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdCanal", listadoSup.getCanalId());
            rw.setAttribute("NomCanal", listadoSup.getCanal());
            rw.setAttribute("IdJefCanal", listadoSup.getIdJefCanal());
            rw.setAttribute("JefCanal", listadoSup.getJefCanal());
            rw.setAttribute("IdSup", listadoSup.getIdSup());
            rw.setAttribute("NomSup", listadoSup.getSup());

            Util.Log(listadoSup.getCanalId() + "CANALAAAAAAAAAAAAA");

            ctx.getSessionScope().put("CaNaLiD", listadoSup.getCanalId());


        }

    }

    public List<Canal> listSup(String idCanal) {

        List<Canal> listSuper = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String canalId = "";
        String canal = "";
        String idJefCanal = "";
        String jefCanal = "";
        String idSup = "";
        String sup = "";

        try {
            String query =
                "select id_canal ID_CANAL, nom_canal NOM_CANAL,id_jef_canal ID_JEF_CANAL, nom_jef_canal NOM_JEF_CANAL, id_sup ID_SUP, nom_sup NOM_SUP from SBP_SLS_LIST_JC_X_SUP_TEMP where id_canal = " + idCanal;

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {

                Canal can = new Canal();
                canalId = rs.getString("ID_CANAL");
                canal = rs.getString("NOM_CANAL");
                idJefCanal = rs.getString("ID_JEF_CANAL");
                jefCanal = rs.getString("NOM_JEF_CANAL");
                idSup = rs.getString("ID_SUP");
                sup = rs.getString("NOM_SUP");


                can.setCanalId(canalId);
                can.setCanal(canal);
                can.setIdJefCanal(idJefCanal);
                can.setJefCanal(jefCanal);
                can.setIdSup(idSup);
                can.setSup(sup);
                listSuper.add(can);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return listSuper;
    }

    public String calcularCantLead() {

        ADFContext ctx = ADFContext.getCurrent();
        String cantReasig =
            ctx.getSessionScope().get("CantSupLead") == null ? "" :
            ctx.getSessionScope().get("CantSupLead").toString();
        
        Util.Log(cantReasig + "CANT");
        
        String totalLead = "";

        totalLead = cantReasig;

        return totalLead;

    }

    public String cantSupXcan() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCanal =
            ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String cantSup = "";


        try {
            String query =
                "select count(id_canal) CANT_SUP from SBP_SLS_LIST_JC_X_SUP_TEMP where id_canal = " +
                idCanal;

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                cantSup = rs.getString("CANT_SUP");
            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(cantSup + "CANT SUP");
        return cantSup;
    }

    public String cantLeadxSup() {

        ADFContext ctx = ADFContext.getCurrent();

        String nomSup =
            ctx.getSessionScope().get("nomSUP") == null ? "" : ctx.getSessionScope().get("nomSUP").toString();


        double cantLead = 0.0;
        double cantSup = 0.0;
        double resultadoLeadxSup = 0.0;

        //--------------CANT LEAD---------------------------//
        cantLead = Double.parseDouble(calcularCantLead());
        cantSup = Double.parseDouble(cantSupXcan());
        resultadoLeadxSup = cantLead / (cantSup);
        int resultadoInt = (int)resultadoLeadxSup;

        String resultadoLeadXSuper = Integer.toString(resultadoInt);

        ctx.getSessionScope().put("cantLeadTot", cantLead);

        //--------------CANT OPP---------------------------//

        Util.Log(cantSup + "CANT SU");
        Util.Log(resultadoLeadXSuper + "RESULTADO");

      //  txtTotalLead.setValue(cantLead);
        txtSupActual.setValue(nomSup);
      //  txtCantidad.setValue(resultadoLeadXSuper);

        return resultadoLeadXSuper;
    }

    public List<NuevoDestino> listDest() {

        List<NuevoDestino> listDestino = new ArrayList<NuevoDestino>();

        String idSup = "";
        String cant = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                NuevoDestino dest = new NuevoDestino();

                currRow = rsi.next();

                idSup = currRow.getAttribute("IdSup").toString();
                cant = currRow.getAttribute("Cantidad").toString();

                dest.setResourceId(idSup);
                dest.setCantidad(cant);
                listDestino.add(dest);

            }
        }
        return listDestino;

    }

    public String cantLeadAsigCanSup() {

        int cant = 0;
        String cantTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                currRow = rsi.next();

                cant += Integer.parseInt(currRow.getAttribute("Cantidad").toString());
                cantTotal = Integer.toString(cant);

            }
        }
        return cantTotal;

    }


    //-----------------TAMAÑO DE LA LISTA--------------------//

    public void cantOppJcSup() {

        String cant = "";
        cant = cantLeadAsigCanSup();


        Util.Log(cant + "CANTIDAD");
    }

    //-----------------TAMAÑO DE LA LISTA--------------------//

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
        Util.Log("Inicio");
        
        try {
            ADFContext ctx = ADFContext.getCurrent();
    
            String idJefCanal =
                ctx.getSessionScope().get("JefCanId") == null ? "" :
                ctx.getSessionScope().get("JefCanId").toString();
    
            String idSup = ctx.getSessionScope().get("SUPerId") == null ? "" : ctx.getSessionScope().get("SUPerId").toString();
            String idCanal = ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();
    
            poblarSup(idJefCanal, idSup);
            //mostrarDatos(idCanal);
            this.mostrarDatos(); // FP@Evol 06/10%2017
            //cantLeadxSup();
           //porcAsignado();
            leadRestante();

        } catch (Exception e) {
            Util.Log("Exception :: " + e.getMessage());
        }
        Util.Log("Fin");
    }

    public void asignar(ActionEvent actionEvent) {
        asignarLead();
    }

    public String porcTotal() {


        double porc = 0.0;
        String porcTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsListSupXJcReasignView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {


                currRow = rsi.next();

                porc +=
                        Math.floor(Double.parseDouble(currRow.getAttribute("PorcQuitar").toString()));
                porcTotal = Double.toString(porc);

            }
        }
        Util.Log(porcTotal + "PORC TOTAL");
        return porcTotal;

    }

    public String leadRestante() {

        ADFContext ctx = ADFContext.getCurrent();

        String leadTotal = cantLeadAsigCanSup();

        Util.Log(leadTotal + "LEAD TOTALESaaaaaaaaaaaaaaa");

        String cantLead =
            ctx.getSessionScope().get("cantLeadTot") == null ? "" :
            ctx.getSessionScope().get("cantLeadTot").toString();

        double cantRestante = Double.parseDouble(leadTotal) - Double.parseDouble(cantLead);

        String cantidadRestanteLead = Double.toString(cantRestante);

        Util.Log(cantidadRestanteLead + "LEAD TOTALES");

        txtMontoRestante.setValue(cantidadRestanteLead);

        return cantidadRestanteLead;

    }

    public void confirmAsig(ActionEvent actionEvent) {


        String cantLead = cantLeadxSup();

        if (!cantLead.equalsIgnoreCase("0")) {

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            String totalCant = cantLeadAsigCanSup();
            String totalPorc = porcTotal();
            txtCantAsig.setValue("Está seguro que desea asignar la cantidad de: " +
                                 totalCant + " correspondiente al " +
                                 totalPorc + "% de los Leads");
            if(Double.parseDouble(totalPorc) <= 100){
            getPopupConfirm().show(hints);
            }else{
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
        asignarLead();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirm().hide();
        FacesContext.getCurrentInstance().addMessage("",
                                                             new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                              "Espere 5 - 10 minutos que termina el proceso de reasignación",
                                                                              ""));
    }

    public void cancelarAsig(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirm().hide();
    }

    public void setTxtSupActual(RichOutputLabel txtSupActual) {
        this.txtSupActual = txtSupActual;
    }

    public RichOutputLabel getTxtSupActual() {
        return txtSupActual;
    }

    public void setTxtMontoRestante(RichOutputLabel txtMontoRestante) {
        this.txtMontoRestante = txtMontoRestante;
    }

    public RichOutputLabel getTxtMontoRestante() {
        return txtMontoRestante;
    }

    public void setTxtTotalLead(RichOutputLabel txtTotalLead) {
        this.txtTotalLead = txtTotalLead;
    }

    public RichOutputLabel getTxtTotalLead() {
        return txtTotalLead;
    }
}

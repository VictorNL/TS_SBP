package view.backing;


import Client.MergeLeadInvoke;
import Client.OportunidadLib;

import Dao.SimplifiedImportActivity;

import WsEjRep.ServiceBiPublisher;

import client.ImportLeadResource;
import client.ImportOpportunityResource;

import client.MergeCargaLeadInvoke;

import com.oracle.xmlns.adf.svc.types.ProcessControl;
import com.oracle.xmlns.adf.svc.types.ReturnMode;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.Opportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.OpportunityResource;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.DeleteOpportunityResource;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunity;

import dao.Canal;
import dao.EjVenta;
import dao.LeadDelete;
import dao.NuevoDestino;
import dao.OptyDelete;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.SocketException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import javax.xml.parsers.ParserConfigurationException;

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
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
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

import org.xml.sax.SAXException;

import view.BO.AsigReasigReporteBO;

import view.Util;

public class SBP_ASOG_EJ_VENT {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelAccordion pa1;
    private RichShowDetailItem sdi1;
    private RichCommandLink cl1;
    private RichCommandLink cl2;
    private RichCommandLink cl3;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichPanelGroupLayout pgl3;
    private RichInputText it1;
    private RichMessages m1;
    private RichTable t1;
    private RichPanelGroupLayout pgl5;
    private RichPanelGroupLayout pgl6;
    private RichPanelGroupLayout pgl4;
    private RichCommandButton cb1;
    private RichInputText txtEjecId;
    private RichTable tblEjecVent;
    private RichOutputLabel txtPorcAsig;
    private RichOutputLabel txtCantTotal;
    private RichTable tblEjec;
    private RichOutputLabel txtTotalOpp;
    private RichInputText txtCantidad;
    private RichInputText txtPorc;
    private RichPopup popupConfirmar;
    private RichOutputLabel txtCantAsig;
    private RichOutputLabel txtTotalLead;
    private RichInputText txtCantLeadFiltro;

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

    public void setPa1(RichPanelAccordion pa1) {
        this.pa1 = pa1;
    }

    public RichPanelAccordion getPa1() {
        return pa1;
    }

    public void setSdi1(RichShowDetailItem sdi1) {
        this.sdi1 = sdi1;
    }

    public RichShowDetailItem getSdi1() {
        return sdi1;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setCl3(RichCommandLink cl3) {
        this.cl3 = cl3;
    }

    public RichCommandLink getCl3() {
        return cl3;
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

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }


    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
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

    public void setTxtEjecId(RichInputText txtEjecId) {
        this.txtEjecId = txtEjecId;
    }

    public RichInputText getTxtEjecId() {
        return txtEjecId;
    }

    public void setTblEjecVent(RichTable tblEjecVent) {
        this.tblEjecVent = tblEjecVent;
    }

    public RichTable getTblEjecVent() {
        return tblEjecVent;
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

    public void setTblEjec(RichTable tblEjec) {
        this.tblEjec = tblEjec;
    }

    public RichTable getTblEjec() {
        return tblEjec;
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

    public void setPopupConfirmar(RichPopup popupConfirmar) {
        this.popupConfirmar = popupConfirmar;
    }

    public RichPopup getPopupConfirmar() {
        return popupConfirmar;
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
            ctx.getSessionScope().get("Cantidad") == null ? "" :
            ctx.getSessionScope().get("Cantidad").toString();


        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        // ServiceBiPublisher client = new ServiceBiPublisher();

        totalOp = Double.parseDouble(cantidadOpp);

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigEvXSupView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant +=
                        Double.parseDouble(currRow.getAttribute("Cantidad").toString());


                totalPorcAsig +=
                        Double.parseDouble(currRow.getAttribute("PorcAsig").toString());


                rsi.next();
            }

            currRow = rsi.last();

            totalCant +=
                    Double.parseDouble(currRow.getAttribute("Cantidad").toString());


            totalPorcAsig +=
                    Double.parseDouble(currRow.getAttribute("PorcAsig").toString());


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

    public List<OptyDelete> listOppAsig(String cantidad) {

        List<OptyDelete> listOpp = new ArrayList<OptyDelete>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String optyId = "";


        try {
            String query =
                "select opty_id OPTY_ID from SBP_SLS_OPP_QUITADAS where estado = 0 and rownum < " +
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

    public void actualizaEstadoOpty(String optyId) {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;


        try {

            String query =
                "update SBP_SLS_OPP_QUITADAS set estado = '1' where opty_id = " +
                optyId;

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);
            st.execute();


        } catch (SQLException e) {
            Util.Log(e + "error");
        } catch (Exception ea) {
            Util.Log(ea + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

    }

    public boolean validacionGuardar() {


        ADFContext ctx = ADFContext.getCurrent();

        String cantidadOpp =
            ctx.getSessionScope().get("Cantidad") == null ? "" :
            ctx.getSessionScope().get("Cantidad").toString();


        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        totalOp = Double.parseDouble(cantidadOpp);


        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigEvXSupView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant +=
                        Double.parseDouble(currRow.getAttribute("Cantidad").toString());


                totalPorcAsig +=
                        Double.parseDouble(currRow.getAttribute("PorcAsig").toString());


                rsi.next();
            }

            currRow = rsi.last();

            totalCant +=
                    Double.parseDouble(currRow.getAttribute("Cantidad").toString());


            totalPorcAsig +=
                    Double.parseDouble(currRow.getAttribute("PorcAsig").toString());


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

    public void eliminar(ActionEvent actionEvent) {

        DCBindingContainer bindingsReg = this.getDCBindingsContainer();
        DCIteratorBinding itorBindingReg =
            bindingsReg.findIteratorBinding("SbpSlsAsigEvXSupView1Iterator");

        ViewObject voReg = itorBindingReg.getViewObject();
        Row[] selectedRowsReg = voReg.getFilteredRows("eliminar", true);
        for (Row currRowReg : selectedRowsReg) {

            currRowReg.remove();
        }
    }

    public void poblarEjVenta(String supId, String idEjVenta) {

        limpiarTablaReasigSupEv();

        ServiceBiPublisher canal = new ServiceBiPublisher();


        List<EjVenta> lista2 = new ArrayList<EjVenta>();
        lista2 = canal.reasigLeadSuEv(supId, idEjVenta);

        PreparedStatement prepareStatement = null;


        for (EjVenta ejVentaList : lista2) {

            sbp.utils.Connection connection = new sbp.utils.Connection();
            PreparedStatement st = null;
            Connection conn = null;


            String query =
                "BEGIN SBP_SLS_SUP_EV_PKG.pr_insert_ej_venta(?,?,?,?,?,?); END;";


            try {

                conn = connection.getConnection();
                prepareStatement = conn.prepareStatement(query);
                prepareStatement.setString(1, ejVentaList.getIdCanal());
                prepareStatement.setString(2, ejVentaList.getCanal());
                prepareStatement.setString(3, ejVentaList.getIdSup());
                prepareStatement.setString(4, ejVentaList.getSup());
                prepareStatement.setString(5, ejVentaList.getIdEjVenta());
                prepareStatement.setString(6, ejVentaList.getEjVenta());

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
    }

    public void limpiarTablaReasigSupEv() {

        PreparedStatement prepareStatement = null;


        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;


        String query = "truncate table SBP_SLS_LIST_EV_X_SUP_TEMP";


        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.execute();
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "excepcion" + e.getMessage() + "IT DOES NOT WORK");
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

    public void limpiarTablaOppQuitadas() {

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;


        String query = "truncate table SBP_SLS_OPP_QUITADAS";

        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            Util.Log(query + "query");
            prepareStatement.execute();
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "excepcion" + e.getMessage() + "IT DOES NOT WORK");
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

    public List<Canal> listEjecutivos() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCanal =
            ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();


        List<Canal> listEjec = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String canalId = "";
        String canal = "";
        String idSup = "";
        String sup = "";
        String idEjVenta = "";
        String ejVenta = "";

        try {
            String query =
                "select id_canal ID_CANAL, nom_canal NOM_CANAL, id_sup ID_SUP, nom_sup NOM_SUP, id_ej_venta ID_EJ_VENTA, nom_ej_venta NOM_EJ_VENTA from SBP_SLS_LIST_EV_X_SUP_TEMP where id_canal = " +
                idCanal;

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                Canal can = new Canal();
                canalId = rs.getString("ID_CANAL");
                canal = rs.getString("NOM_CANAL");
                idSup = rs.getString("ID_SUP");
                sup = rs.getString("NOM_SUP");
                idEjVenta = rs.getString("ID_EJ_VENTA");
                ejVenta = rs.getString("NOM_EJ_VENTA");


                can.setCanalId(canalId);
                can.setCanal(canal);
                can.setIdSup(idSup);
                can.setSup(sup);
                can.setIdEjVenta(idEjVenta);
                can.setEjVenta(ejVenta);
                listEjec.add(can);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listEjec;
    }

    public String calcularCantLead() {

        ADFContext ctx = ADFContext.getCurrent();

        String cantReasig =
            ctx.getSessionScope().get("Cantidad") == null ? "" :
            ctx.getSessionScope().get("Cantidad").toString();

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
                "select count(id_canal) CANT_SUP from SBP_SLS_LIST_EV_X_SUP_TEMP where id_canal = " +
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

        return cantSup;
    }

    public void mostrarDatos() {

        ADFContext ctx = ADFContext.getCurrent();
        Integer cantLead = Integer.parseInt(calcularCantLead());
        ctx.getSessionScope().put("Cantidad", cantLead);

        txtTotalLead.setValue(cantLead);

        Integer cantSup = listEjecutivos().size();

        List<Canal> listEjVenta = new ArrayList<Canal>();

        listEjVenta = listEjecutivos();

        CollectionModel _tablaEjec = (CollectionModel)tblEjec.getValue();
        JUCtrlHierBinding _adfTableEjecBinding =
            (JUCtrlHierBinding)_tablaEjec.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itEjec = _adfTableEjecBinding.getDCIteratorBinding();


        int b = 0;
        b = itEjec.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itEjec.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        Row rw = null;

        for (Canal listadoEjec : listEjVenta) {

            rw = itEjec.getNavigatableRowIterator().createRow();
            itEjec.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdCanal", listadoEjec.getCanalId());
            rw.setAttribute("NomCanal", listadoEjec.getCanal());
            rw.setAttribute("IdSup", listadoEjec.getIdSup());
            rw.setAttribute("NomSup", listadoEjec.getSup());
            rw.setAttribute("IdEjVenta", listadoEjec.getIdEjVenta());
            rw.setAttribute("Cantidad", cantLead / cantSup);
            rw.setAttribute("NomEjVenta", listadoEjec.getEjVenta());

        }

    }

    public String cantLeadxSup() {

        double cantLead = 0.0;
        double cantSupxCan = 0.0;
        double resultadoLeadxSup = 0.0;


        //--------------CANT LEAD---------------------------//
        cantLead = Double.parseDouble(calcularCantLead());
        cantSupxCan = Double.parseDouble(cantSupXcan());
        resultadoLeadxSup = cantLead / (cantSupxCan);
        int resultadoInt = (int)resultadoLeadxSup;


        String resultadoLeadXSuper = Integer.toString(resultadoInt);

        //--------------CANT LEAD---------------------------//

        Util.Log(resultadoLeadXSuper + "RESULTADO");
        // txtTotalOpp.setValue(cantLead);
        // txtCantidad.setValue(Util.formatearMonto(resultadoLeadXSuper));
        return resultadoLeadXSuper;
    }


    public String porcAsignado() {

        double cantSupxCan = 0.0;
        double resultadoPorc = 0.0;

        cantSupxCan = Double.parseDouble(cantSupXcan());

        resultadoPorc = Math.floor((100 / (cantSupxCan)) * 100.0) / 100.0;

        String resultadoOppXSuper = Double.toString(resultadoPorc);

        //   txtPorc.setValue(resultadoOppXSuper);
        //  txtPorcAsig.setValue(resultadoOppXSuper);
        Util.Log(resultadoOppXSuper + "RESULTADO PORC");
        return resultadoOppXSuper;

    }

    public List<NuevoDestino> listDest() {

        List<NuevoDestino> listDestino = new ArrayList<NuevoDestino>();

        String idEjVenta = "";
        String cant = "";
        String canal = "";
        String sup = "";
        String ejecutivo = "";


        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigEvXSupView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                NuevoDestino dest = new NuevoDestino();

                currRow = rsi.next();

                idEjVenta = currRow.getAttribute("IdEjVenta").toString();
                cant = currRow.getAttribute("Cantidad").toString();
                canal = currRow.getAttribute("NomCanal").toString();
                sup = currRow.getAttribute("NomSup").toString();
                ejecutivo = currRow.getAttribute("NomEjVenta").toString();

                dest.setResourceId(idEjVenta);
                dest.setCantidad(cant);

                dest.setCanal(canal);
                dest.setSupervisor(sup);
                dest.setEjecutivo(ejecutivo);
                listDestino.add(dest);

            }
        }
        return listDestino;

    }


    public void cantOppJcSup() {

        String cant = "";

        cant = cantLeadAsigCanSup();

    }


    public void cantLeadXFiltro(String idEjVenta, String cpgId, String sexo, String estCivil, String flagClubSueldo,
                                String edadMin, String edadMax){
        
        ServiceBiPublisher leads = new ServiceBiPublisher();

        String cantLeadFiltro = "";
        
        cantLeadFiltro = leads.eliminarLeadNewCant(idEjVenta, cpgId, sexo, estCivil, flagClubSueldo, edadMin, edadMax);
       
        Util.Log(cantLeadFiltro + "cantLeadFiltro");
        
        txtCantLeadFiltro.setValue(cantLeadFiltro);
        
    }
  
  
    public void asignarLead() {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher leads = new ServiceBiPublisher();

        List<LeadDelete> lead = new ArrayList<LeadDelete>();

        List<LeadDelete> leadListadoTabla = new ArrayList<LeadDelete>();

        List<NuevoDestino> newDest = new ArrayList<NuevoDestino>();

        String totalCant = "";

        String idEjVenta =
            ctx.getSessionScope().get("EjVentaId") == null ? "" : ctx.getSessionScope().get("EjVentaId").toString();

        String cpgId =
            ctx.getSessionScope().get("cpanaId") == null ? "" : ctx.getSessionScope().get("cpanaId").toString();

        String sexo =
            ctx.getSessionScope().get("Sexo") == null ? "" : ctx.getSessionScope().get("Sexo").toString();

        totalCant = cantLeadAsigCanSup();
        
        txtCantAsig.setValue(totalCant);
        
        Util.Log(totalCant + "TOTAL CANT");

        lead = leads.eliminarLeadNew(idEjVenta, totalCant, cpgId, sexo);
        
        Util.Log(lead.size() + "lead.SIZE");

        newDest = listDest();

        insertarDataReasigList(lead, newDest);

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        leadListadoTabla = listLeadEj(idCarga);

        reasignarSupEjVenta(leadListadoTabla, idEjVenta);

        AsigReasigReporteBO.GenerarSUP_EJ(newDest);

    }


    public List<LeadDelete> listLeadEj(String idCarga) {

        List<LeadDelete> listaLeadsEj = new ArrayList<LeadDelete>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String leadNumber = "";
        String ejecId = "";
        String fbNom = "";
        String ejecutivo = "";
        String supervisor = "";
        String jefCanal = "";
        String canal = "";
        String cpgNom = "";
        String fbIni = "";
        String tipGest = "";
        String grupGest = "";
        String detGest = "";


        try {
            String query =
                "select lead_number LEAD_NUMBER, ejec_vent_id EJEC_VENT_ID, fb_nom FB_NOM, ejecutivo EJECUTIVO, supervisor SUPERVISOR, jef_canal JEF_CANAL, canal CANAL, cpg_nom CPG_NOM, fb_inicial FB_INICIAL, tip_gest TIP_GEST, grup_dest GRUP_GEST, det_gest DET_EST from SBP_SLS_TBL_REASIG_EV where id_sec = " +
                idCarga;

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                LeadDelete lead = new LeadDelete();
                leadNumber = rs.getString("LEAD_NUMBER");
                ejecId = rs.getString("EJEC_VENT_ID");
                fbNom = rs.getString("FB_NOM");
                ejecutivo = rs.getString("EJECUTIVO");
                supervisor = rs.getString("SUPERVISOR");
                jefCanal = rs.getString("JEF_CANAL");
                canal = rs.getString("CANAL");
                cpgNom = rs.getString("CPG_NOM");
                fbIni = rs.getString("FB_INICIAL");
                tipGest = rs.getString("TIP_GEST");
                grupGest = rs.getString("GRUP_GEST");
                detGest = rs.getString("DET_EST");


                lead.setLeadNumber(leadNumber);
                lead.setResourceId(ejecId);
                lead.setLeadNumber(fbNom);
                lead.setEjecutivo(ejecutivo);
                lead.setSupervisor(supervisor);
                lead.setJefCanal(jefCanal);
                lead.setCanal(canal);
                lead.setCampania(cpgNom);
                lead.setFbIni(fbIni);
                lead.setTipGest(tipGest);
                lead.setGrupGest(grupGest);
                lead.setDetGest(detGest);
                listaLeadsEj.add(lead);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(listaLeadsEj.size() + "LISTA LIST LEAD");
        return listaLeadsEj;
    }


    public void insertarDataReasigList(List<LeadDelete> plstLeads,
                                       List<NuevoDestino> plstNuevoDestino) {

        ADFContext ctx = ADFContext.getCurrent();

        String jefCanal =
            ctx.getSessionScope().get("JefECaNAl") == null ? "" : ctx.getSessionScope().get("JefECaNAl").toString();

        Util.Log(jefCanal + "JEFE CANAL");
        String nomCpg =
            ctx.getSessionScope().get("cpanaNom") == null ? "" : ctx.getSessionScope().get("cpanaNom").toString();


        Util.Log(plstLeads.size() + "TAMAÑO LISTA LEAD REA");

        Integer intIdxLead = 0;
        String idCarga = obtenerIdCarga();

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        for (NuevoDestino desti : plstNuevoDestino) {

            for (int i = 0; i < Integer.parseInt(desti.getCantidad()); i++) {

                // Obtener lead
                LeadDelete lead = plstLeads.get(intIdxLead);
                intIdxLead++;

                try {


                    String query =
                        "BEGIN SBP_SLS_REASIG_EV_PKG.insert_datos(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

                    conn = connection.getConnection();
                    prepareStatement = conn.prepareStatement(query);
                    prepareStatement.setString(1, lead.getLeadNumber());
                    prepareStatement.setString(2, desti.getResourceId());
                    prepareStatement.setString(3, idCarga);
                    prepareStatement.setString(4, lead.getLeadNumber());
                    prepareStatement.setString(5, desti.getEjecutivo());
                    prepareStatement.setString(6, desti.getSupervisor());
                    prepareStatement.setString(7, jefCanal);
                    prepareStatement.setString(8, desti.getCanal());
                    prepareStatement.setString(9, nomCpg);
                    prepareStatement.setString(10, "1");
                    prepareStatement.setString(11, "3");
                    prepareStatement.setString(12, "12");
                    prepareStatement.setString(13, "38");

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
    }

    public void actNumOrd() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        int cargaId = Integer.parseInt(idCarga);


        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        try {


            String query =
                "BEGIN SBP_SLS_REASIG_EV_PKG.act_num_orden(?); END;";

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


    public void eliminarDataProcesada() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

        int cargaId = Integer.parseInt(idCarga);

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        try {


            String query =
                "BEGIN SBP_SLS_REASIG_EV_PKG.eliminar_data_proc(?); END;";

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

    public String obtenerIdCarga() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idCarga = "";


        try {
            String query =
                "select SBP_SLS_ID_REASIG_EV_S.nextval ID_CARGA from dual";

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


    public String obtenerRandomEv() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idRandom = "";


        try {
            String query =
                "select SBP_SLS_ID_REASIG_EV_RANDOM_S.nextval ID_CARGA from dual";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                idRandom = rs.getString("ID_CARGA");
            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(idRandom + "ID CARGA");
        ctx.getSessionScope().put("idCarga", idRandom);
        return idRandom;
    }

    public void toZip(String ZipName, String csvName,
                      List<LeadDelete> plstLeads, String pstrResourceID) {

        String strTrama1;
        String strTrama2;
        String strTrama3;
        Boolean exito = false;
        FileOutputStream fos;

        Util.Log("Inicio");
        String sl = "\r\n";

        try {
            fos = new FileOutputStream("/customer/scratch/prueba.zip");

            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze = new ZipEntry(csvName + ".csv");
            zos.putNextEntry(ze);

            String st =
                ("Lead.LeadNumber,LeadResource.ActionCode,LeadResource.ResourceId,Feedback.LEAD_NUMBER,Feedback.RecordName,Feedback.Ejecutivo,Feedback.Supervisor,Feedback.JefeCanal,Feedback.Canal,Feedback.NombreCampana,Feedback.AuxFeedbackInicial,Feedback.TipoGestion,Feedback.GrupoGestion,Feedback.DetalleGestion" +
                 sl);
            byte[] bt = st.getBytes();
            zos.write(bt, 0, bt.length);

            for (LeadDelete lead : plstLeads) {

                String idRandom = obtenerRandomEv();

                strTrama1 =
                        lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() +
                        "," + "" + "," + lead.getLeadNumber() + "," +
                        lead.getEjecutivo() + "," + lead.getSupervisor() +
                        "," + lead.getJefCanal() + "," + lead.getCanal() +
                        "," + lead.getCampania() + "," + lead.getFbIni() +
                        "," + "" + "," + "" + "," + "";
                Util.Log(strTrama1 + "strTrama1");

                byte[] bd = strTrama1.getBytes();


                Util.Log(bt.toString());
                zos.write(bd, 0, bd.length);

                strTrama2 =
                        "" + "," + "" + "," + "" + "," + lead.getLeadNumber() +
                        "," + idRandom + "," + lead.getEjecutivo() + "," +
                        lead.getSupervisor() + "," + lead.getJefCanal() + "," +
                        lead.getCanal() + "," + lead.getCampania() + "," +
                        "0" + "," + lead.getTipGest() + "," +
                        lead.getGrupGest() + "," + lead.getDetGest();

                Util.Log(strTrama2 + "strTrama2");

                byte[] bd2 = strTrama2.getBytes();

                zos.write(bd2, 0, bd2.length);

                strTrama3 =
                        "" + "," + "DELETE" + "," + pstrResourceID + "," + "" +
                        "," + "" + "," + "" + "," + "" + "," + "" + "," + "" +
                        "," + "" + "," + "" + "," + "" + "," + "" + "," + "";

                Util.Log(strTrama3 + "strTrama3");

                byte[] bd3 = strTrama3.getBytes();

                zos.write(bd3, 0, bd3.length);
            }


            Util.Log(zos.toString() + "ZIP");


            zos.closeEntry();
            zos.close();


            Util.Log("Zip creado correctamente");

        } catch (FileNotFoundException e) {
            Util.Log("FileNotFoundException : " + e.getMessage());
            Util.Log("FileNotFoundException : " + e.getStackTrace() + "");
        } catch (IOException e) {
            Util.Log("IOException : " + e.getMessage());
            Util.Log("IOException : " + e.getStackTrace() + "");
        }

    }


    public void reasignarSupEjVenta(List<LeadDelete> plstLeads,
                                    String pstrResourceID) {

        SimplifiedImportActivity p = new SimplifiedImportActivity();


        String strTrama1;
        String strTrama2;
        String strTrama3;
        String strTramaCabecera1 = "LeadNumber" + "\n";
        String strTramaCabecera2 = "LeadNumber,ActionCode,ResourceId" + "\n";
        String strTramaFeedback = "LEAD_NUMBER,RecordName,EjecutivoVenta_c,Supervisor_c,JefeCanal_c,Canal_c,NombreCampana_c,AuxFeedbackInicial_c,TipoGestion_c,GrupoGestion_c,DetalleGestion_c" +
            "\n";
        String strTrama4;
        String strTrama5;
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
                    ZipEntry ze = new ZipEntry("Lead.csv");
                    zos.putNextEntry(ze);

                    byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                    zos.write(cabeceraLead1, 0, cabeceraLead1.length);

                    List<LeadDelete> listaLeads = new ArrayList<LeadDelete>();

                    listaLeads = plstLeads.subList(ini, fin);
                    
                    intIdxLead = 0;

                    for (int i = 0; i < listaLeads.size(); i++) {

                        LeadDelete lead = listaLeads.get(intIdxLead);
                        intIdxLead++;

                        //actualizarDataReasigTabla("D0137508", pstrResourceID, lead.getResourceId(), lead.getEjecutivo());
                        actualizarDataReasigTabla(lead.getLeadNumber(), pstrResourceID, lead.getResourceId(), lead.getEjecutivo());

                        strTrama1 = lead.getLeadNumber() + "\n";

                        Util.Log(strTrama1 + "strTrama1 if");

                        byte[] bt = strTrama1.getBytes();
                        zos.write(bt, 0, bt.length);

                    }
                    // Cerrar Entry
                    zos.closeEntry();

                    ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                    zos.putNextEntry(zef);
                    byte[] cabeceraLead = strTramaCabecera2.getBytes();
                    zos.write(cabeceraLead, 0, cabeceraLead.length);

                    intIdxLead = 0;
                    for (int i = 0; i < listaLeads.size(); i++) {

                        LeadDelete lead = listaLeads.get(intIdxLead);
                        intIdxLead++;
                        

                        strTrama2 =
                                lead.getLeadNumber() + "," + "DELETE" + "," +
                                pstrResourceID + "\n";
                        strTrama3 =
                                lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() +
                                "\n";


                        byte[] bt = strTrama2.getBytes();
                        byte[] bt2 = strTrama3.getBytes();


                        zos.write(bt, 0, bt.length);
                        zos.write(bt2, 0, bt2.length);


                    }
                    // Cerrar Entry
                    zos.closeEntry();


                    Util.Log("2");


                    ZipEntry zefe = new ZipEntry("Feedback_Lead_c.csv");
                    zos.putNextEntry(zefe);

                    byte[] cabeceraFb = strTramaFeedback.getBytes();

                    zos.write(cabeceraFb, 0, cabeceraFb.length);

                    intIdxLead = 0;
                    for (int i = 0; i < listaLeads.size(); i++) {

                        LeadDelete lead = listaLeads.get(intIdxLead);
                        intIdxLead++;

                        strTrama4 =
                                lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                "," + lead.getResourceId() + "," +
                                lead.getSupervisor() + "," +
                                lead.getJefCanal() + "," + lead.getCanal() +
                                "," + lead.getCampania() + "," + "1" + "," +
                                "" + "," + "" + "," + "" + "\n";

                        strTrama5 =
                                lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                "," + pstrResourceID + "," +
                                lead.getSupervisor() + "," +
                                lead.getJefCanal() + "," + lead.getCanal() +
                                "," + lead.getCampania() + "," + "0" + "," +
                                "3" + "," + "12" + "," + "38" + "\n";

                        Util.Log(strTrama4 + "strTrama4 if");

                        Util.Log(strTrama5 + "strTrama5 if");

                        byte[] bt = strTrama4.getBytes();
                        byte[] bt2 = strTrama5.getBytes();


                        zos.write(bt, 0, bt.length);
                        zos.write(bt2, 0, bt2.length);


                    }
                    // Cerrar Entry
                    zos.closeEntry();

                    String str =
                        client.Base64.byteArrayToBase64(bos.toByteArray());

                    Util.Log(str + "str");

                    MergeLeadInvoke mlc = new MergeLeadInvoke();
               //     p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                } else {

                    ByteArrayOutputStream bos1 = new ByteArrayOutputStream();

                    ZipOutputStream zos1 = new ZipOutputStream(bos1);
                    ZipEntry ze = new ZipEntry("Lead.csv");
                    zos1.putNextEntry(ze);

                    byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                    zos1.write(cabeceraLead1, 0, cabeceraLead1.length);

                    List<LeadDelete> listaLeads = new ArrayList<LeadDelete>();
                    
                    intIdxLead = 0;

                    listaLeads = plstLeads.subList(ini, plstLeads.size());

                    for (int i = 0; i < listaLeads.size(); i++) {
                       
                        LeadDelete lead = listaLeads.get(intIdxLead);
                        intIdxLead++;
                        
                        actualizarDataReasigTabla(lead.getLeadNumber(), pstrResourceID, lead.getResourceId(), lead.getEjecutivo());
                        //actualizarDataReasigTabla("D0138212", pstrResourceID, lead.getResourceId(), lead.getEjecutivo());

                        strTrama1 = lead.getLeadNumber() + "\n";

                        Util.Log(strTrama1 + "strTrama1  else");

                        byte[] bt = strTrama1.getBytes();


                        zos1.write(bt, 0, bt.length);


                    }
                    // Cerrar Entry
                    zos1.closeEntry();

                    ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                    zos1.putNextEntry(zef);
                    byte[] cabeceraLead = strTramaCabecera2.getBytes();
                    zos1.write(cabeceraLead, 0, cabeceraLead.length);

                    intIdxLead = 0;
                    for (int i = 0; i < listaLeads.size(); i++) {

                        LeadDelete lead = listaLeads.get(intIdxLead);
                        intIdxLead++;

                        strTrama2 =
                                lead.getLeadNumber() + "," + "DELETE" + "," +
                                pstrResourceID + "\n";
                        strTrama3 =
                                lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() +
                                "\n";


                        Util.Log(strTrama2 + "strTrama2 else");

                        Util.Log(strTrama3 + "strTrama3  else");


                        byte[] bt = strTrama2.getBytes();
                        byte[] bt2 = strTrama3.getBytes();


                        zos1.write(bt, 0, bt.length);
                        zos1.write(bt2, 0, bt2.length);


                    }
                    // Cerrar Entry
                    zos1.closeEntry();


                    ZipEntry zefe = new ZipEntry("Feedback_Lead_c.csv");
                    zos1.putNextEntry(zefe);

                    byte[] cabeceraFb = strTramaFeedback.getBytes();

                    zos1.write(cabeceraFb, 0, cabeceraFb.length);

                    intIdxLead = 0;
                    for (int i = 0; i < listaLeads.size(); i++) {

                        LeadDelete lead = listaLeads.get(intIdxLead);
                        intIdxLead++;

                        strTrama4 =
                                lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                "," + lead.getResourceId() + "," +
                                lead.getSupervisor() + "," +
                                lead.getJefCanal() + "," + lead.getCanal() +
                                "," + lead.getCampania() + "," + "1" + "," +
                                "" + "," + "" + "," + "" + "\n";

                        strTrama5 =
                                lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                "," + pstrResourceID + "," +
                                lead.getSupervisor() + "," +
                                lead.getJefCanal() + "," + lead.getCanal() +
                                "," + lead.getCampania() + "," + "0" + "," +
                                "3" + "," + "12" + "," + "38" + "\n";

                        Util.Log(strTrama4 + "strTrama4 else");

                        Util.Log(strTrama5 + "strTrama5 else");

                        byte[] bt = strTrama4.getBytes();
                        byte[] bt2 = strTrama5.getBytes();


                        zos1.write(bt, 0, bt.length);
                        zos1.write(bt2, 0, bt2.length);


                    }
                    // Cerrar Entry
                    zos1.closeEntry();


                    String str =
                        client.Base64.byteArrayToBase64(bos1.toByteArray());
                    Util.Log(str + "str");
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                  //  p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                }
                ini = fin;

                fin = ini + pivot;

                cant = cant - pivot;

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
            String query = "BEGIN SBP_SLS_REASIG_EV_PKG.act_flag(?,?,?); END;";

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

        String idSup =
            ctx.getSessionScope().get("SperId") == null ? "" : ctx.getSessionScope().get("SperId").toString();

        String idEjVenta = 
            ctx.getSessionScope().get("EjVentaId") == null ? "" : ctx.getSessionScope().get("EjVentaId").toString();
        
        String cpgId =
            ctx.getSessionScope().get("cpanaId") == null ? "" : ctx.getSessionScope().get("cpanaId").toString();

        String sexo =
            ctx.getSessionScope().get("Sexo") == null ? "" : ctx.getSessionScope().get("Sexo").toString();

        String estCivil =
            ctx.getSessionScope().get("estCivil") == null ? "" : ctx.getSessionScope().get("estCivil").toString();

        String flagClubSueldo =
            ctx.getSessionScope().get("flagClubSueldo") == null ? "" : ctx.getSessionScope().get("flagClubSueldo").toString();

        String edadMin =
            ctx.getSessionScope().get("edadMin") == null ? "" : ctx.getSessionScope().get("edadMin").toString();

        String edadMax =
            ctx.getSessionScope().get("edadMax") == null ? "" : ctx.getSessionScope().get("edadMax").toString();

        poblarEjVenta(idSup, idEjVenta);
        
        mostrarDatos();
        
        Util.Log(idEjVenta + "idEjVenta");
        
        Util.Log(cpgId + "cpgId");
        
        Util.Log(sexo + "sexo");
        
        Util.Log(estCivil + "estCivil");
        
        Util.Log(flagClubSueldo + "flagClubSueldo");
        
        Util.Log(edadMin + "edadMin");
        
        Util.Log(edadMax + "edadMax");
        
        cantLeadXFiltro(idEjVenta, cpgId, sexo, estCivil, flagClubSueldo, edadMin, edadMax);

    }

    public void asignar(ActionEvent actionEvent) {

        confirmarAsig();

    }

    public String cantLeadAsigCanSup() {

        int cant = 0;

        String cantTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigEvXSupView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);


        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                currRow = rsi.next();

                cant +=
                        Integer.parseInt(currRow.getAttribute("Cantidad").toString());

                cantTotal = Integer.toString(cant);

            }
        }

        return cantTotal;

    }

    public String porcTotal() {


        double porc = 0.0;
        String porcTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigEvXSupView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {


                currRow = rsi.next();

                porc +=
                        Math.floor(Double.parseDouble(currRow.getAttribute("PorcAsig").toString()));
                porcTotal = Double.toString(porc);

            }
        }
        return porcTotal;

    }


    public void confirmarAsig() {

        try {

            String cantLead = cantLeadxSup();
            if (!cantLead.equalsIgnoreCase("0")) {

                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                String totalCant = cantLeadAsigCanSup();
                String totalPorc = porcTotal();
                txtCantAsig.setValue("Está seguro que desea asignar la cantidad de: " +
                                     totalCant + " correspondiente al " +
                                     totalPorc + "% de los leads");
                if (Double.parseDouble(totalPorc) <= 100) {
                    getPopupConfirmar().show(hints);
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
        } catch (Exception e) {
            Util.Log(e.getMessage());
            Util.Log(e.getStackTrace() + "");
        }

    }

    public void aceptarAsign(ActionEvent actionEvent) {
        asignarLead();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirmar().hide();
        FacesContext.getCurrentInstance().addMessage("",
                                                     new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                      "Espere 5 - 10 minutos que termina el proceso de reasignación",
                                                                      ""));
    }

    public void cancelarAsig(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirmar().hide();
    }

    public void setTxtCantAsig(RichOutputLabel txtCantAsig) {
        this.txtCantAsig = txtCantAsig;
    }

    public RichOutputLabel getTxtCantAsig() {
        return txtCantAsig;
    }

    public void setTxtTotalLead(RichOutputLabel txtTotalLead) {
        this.txtTotalLead = txtTotalLead;
    }

    public RichOutputLabel getTxtTotalLead() {
        return txtTotalLead;
    }

    public void setTxtCantLeadFiltro(RichInputText txtCantLeadFiltro) {
        this.txtCantLeadFiltro = txtCantLeadFiltro;
    }

    public RichInputText getTxtCantLeadFiltro() {
        return txtCantLeadFiltro;
    }
    
    
    public void actualizarDataReasigTabla(String leadNumber, String idEjDel, String idEjNew, String nomEjecNew) {

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

                try {

                   // String query = "BEGIN SBP_SLS_REASIG_EV_PKG.actualizar_data_reasig(?,?,?,?); END;";
                    String query = "update sbp_sls_lead_dosif_x_user set id_ejecutivo = " + idEjNew + ", ejecutivo = '" + nomEjecNew + "' where lead_number = '" + leadNumber + "' and id_ejecutivo = " + idEjDel;

                    Util.Log(query + "QUERY");
                    conn = connection.getConnection();
                    prepareStatement = conn.prepareStatement(query);
                  /*  Util.Log(leadNumber + "leadNumber");
                    prepareStatement.setString(1, leadNumber);
                    Util.Log(idEjDel + "idEjDel");
                    prepareStatement.setString(2, idEjDel);
                    Util.Log(idEjNew + "idEjNew");
                    prepareStatement.setString(3, idEjNew);
                    Util.Log(nomEjecNew + "nomEjecNew");
                    prepareStatement.setString(4, nomEjecNew);*/

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

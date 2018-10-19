package view.backing;

import Client.MergeLeadInvoke;
import Client.OportunidadLib;

import Dao.SimplifiedImportActivity;

import dao.Lead;

import WsEjRep.ServiceBiPublisher;

import client.ImportLeadResource;
import client.ImportOpportunityResource;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;

import com.jcraft.jsch.SftpException;

import com.oracle.xmlns.adf.svc.types.ProcessControl;
import com.oracle.xmlns.adf.svc.types.ReturnMode;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.Opportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.OpportunityResource;

import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunity;


import dao.Canal;
import dao.CpgLeadContAccProdFb;
import dao.LeadDelete;
import dao.NuevoDestino;
import dao.Oportunidad;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.InputStream;

import java.net.SocketException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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

import view.Util;

public class SBP_ASIG_CPG_SP {
    String cabecera;
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichInputText it1;
    private RichCommandButton cb3;
    private RichCommandButton cb4;
    private RichMessages m1;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichInputText txtResourceId;
    private RichInputText txtTotal;
    private RichOutputLabel txtPorcAsig;
    private RichOutputLabel txtCantTotal;

    private RichTable tblSup;
    private RichOutputLabel txtTotalOpp;
    private RichInputText txtCantidad;
    private RichInputText txtPorc;

    private RichPopup popupConfirmar;
    private RichInputText txtUserName;
    private RichInputText txtCpgId;
    private RichOutputLabel txtCantAsig;
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

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
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

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
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


    public void setTxtResourceId(RichInputText txtResourceId) {
        this.txtResourceId = txtResourceId;
    }

    public RichInputText getTxtResourceId() {
        return txtResourceId;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setTxtTotal(RichInputText txtTotal) {
        this.txtTotal = txtTotal;
    }

    public RichInputText getTxtTotal() {
        return txtTotal;
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


    public void setPopupConfirmar(RichPopup popupConfirmar) {
        this.popupConfirmar = popupConfirmar;
    }

    public RichPopup getPopupConfirmar() {
        return popupConfirmar;
    }


    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

 /*   public String guardar() {

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

    }*/


  /*  public String agregar() {

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

    }*/

  /*  public boolean validacionAgregar() {

        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        totalOp = Double.parseDouble(calcularCantLead());

        Util.Log(totalOp + "CANTIDAD OPP TOTAL");

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigJcSupView1Iterator");

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
    }*/

  /*  public boolean validacionGuardar() {

        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        totalOp = Double.parseDouble(calcularCantLead());


        Util.Log(totalOp + "TOTAL OPP CANT");

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigJcSupView1Iterator");

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
    }*/


  /*  public void eliminar(ActionEvent actionEvent) {
        Util.Log("Inicio de proceso de eliminación");
        DCBindingContainer bindingsReg = this.getDCBindingsContainer();
        DCIteratorBinding itorBindingReg =
            bindingsReg.findIteratorBinding("SbpSlsAsigJcSupView1Iterator");

        ViewObject voReg = itorBindingReg.getViewObject();
        Row[] selectedRowsReg = voReg.getFilteredRows("eliminar", true);
        for (Row currRowReg : selectedRowsReg) {

            Util.Log("entra");
            currRowReg.remove();
        }
    }*/

    public String cantLeadAsigCanSup() {

        int cant = 0;
        String cantTotalLead = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigJcSupView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {

                currRow = rsi.next();

                cant +=
                        Integer.parseInt(currRow.getAttribute("Cantidad").toString());

                cantTotalLead = Integer.toString(cant);

            }
        }
        return cantTotalLead;

    }


    public void asignarOppCanSup(ActionEvent actionEvent) {
        asignarCanSup();

    }


    public List<NuevoDestino> listDest() {

        List<NuevoDestino> listDestino = new ArrayList<NuevoDestino>();

        String idSup = "";
        String cant = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsAsigJcSupView1Iterator");

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
    
    public void poblarTablaSup(String canalId) {


        ServiceBiPublisher canal = new ServiceBiPublisher();

        //buscar usuarios a cargo de un jefe de canal
        List<Canal> lista2 = new ArrayList<Canal>();
        lista2 = canal.obtenerSup(canalId);

        Util.Log(lista2.size() + "TAMAÑO LIST SUP");
        PreparedStatement prepareStatement = null;


        for (Canal supList : lista2) {

            sbp.utils.Connection connection = new sbp.utils.Connection();
            PreparedStatement st = null;
            Connection conn = null;


            String query =
                "BEGIN SBP_SLS_ASIG_JC_SUP_PKG.pr_insert_sup(?,?,?,?,?); END;";


            try {
                Util.Log("TRY");
                conn = connection.getConnection();
                prepareStatement = conn.prepareStatement(query);
                prepareStatement.setString(1, supList.getCanalId());
                prepareStatement.setString(2, supList.getCanal());
                prepareStatement.setString(3, supList.getIdSup());
                prepareStatement.setString(4, supList.getSup());
                prepareStatement.setString(5, supList.getPartyNumber());
                prepareStatement.execute();
                prepareStatement.close();
                conn.close();

            } catch (SQLException e) {
                Util.Log(e + "excepcion" + e.getMessage() +
                                   "IT DOES NOT WORK");
            } catch (NullPointerException e) {
                Util.Log(e + "excepcion" + e.getMessage() +
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

    public String calcularCantLead() {

        ADFContext ctx = ADFContext.getCurrent();

        String idCanal =
            ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();

        String idCpg =
            ctx.getSessionScope().get("CampaignId") == null ? "" : ctx.getSessionScope().get("CampaignId").toString();


        ServiceBiPublisher client = new ServiceBiPublisher();

        String totalOp = "";

        totalOp = client.obtenerCantLead(idCpg, idCanal);

        return totalOp;

    }


    public List<Canal> listSupervisores() {
        
        ServiceBiPublisher canal = new ServiceBiPublisher();

        ADFContext ctx = ADFContext.getCurrent();

        String idCanal =
            ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();


        List<Canal> listSuperv = new ArrayList<Canal>();
        
        listSuperv = canal.obtenerSupNuevo(idCanal);

     /*   Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String canalId = "";
        String canal = "";
        String idSup = "";
        String sup = "";
        String partyNumber = "";


        try {
            String query =
                "select id_canal ID_CANAL, nom_canal NOM_CANAL, id_sup ID_SUP, nom_sup NOM_SUP, party_number PARTY_NUMBER from SBP_SLS_ASIG_JC_SUP where id_canal = " +
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
                partyNumber = rs.getString("PARTY_NUMBER");

                can.setCanalId(canalId);
                can.setCanal(canal);
                can.setIdSup(idSup);
                can.setSup(sup);
                can.setPartyNumber(partyNumber);
                listSuperv.add(can);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
*/

        return listSuperv;
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
                "select count(id_canal) CANT_SUP from SBP_SLS_ASIG_JC_SUP where id_canal = " +
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


    public void mostrarDatos() {

        List<Canal> listSup = new ArrayList<Canal>();

        listSup = listSupervisores();

        Util.Log(listSup.size() + "TAMAÑO");
        CollectionModel _tablaSup = (CollectionModel)tblSup.getValue();
        JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();


        int b = 0;
        b = itSup.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        Row rw = null;
        
        
        ADFContext ctx = ADFContext.getCurrent();
        Integer cantLead = Integer.parseInt(calcularCantLead());
        ctx.getSessionScope().put("cantTotalAsig", cantLead);
        
        txtTotalLead.setValue(cantLead);
        
        

        //Integer cantSup = listSup.size();
        int cantEj = 0;
        for(Canal listadoSup : listSup){
            
            cantEj += Integer.parseInt(listadoSup.getCantEjXSup());
            
        }
        
        Util.Log(cantEj + "CANT EJ");
        for (Canal listadoSup : listSup) {

            rw = itSup.getNavigatableRowIterator().createRow();
            itSup.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdCanal", listadoSup.getCanalId());
            rw.setAttribute("NomCanal", listadoSup.getCanal());
            rw.setAttribute("IdSup", listadoSup.getIdSup());
            rw.setAttribute("NomSup", listadoSup.getSup());
            rw.setAttribute("PartyNumber", listadoSup.getPartyNumber());
            rw.setAttribute("Cantidad", (cantLead/cantEj)* Integer.parseInt(listadoSup.getCantEjXSup()));
            
            
            //rw.setAttribute("PorcAsig", cantLead/cantSup*100);

            Util.Log(listadoSup.getCanalId() + "CANAL");

        }
    }

    public String cantLeadxSup() {

        ADFContext ctx = ADFContext.getCurrent();

        double cantLead = 0.0;
        double cantSupxCan = 0.0;
        double resultadoLeadxSup = 0.0;


        //--------------CANT OPP---------------------------//
        cantLead = Double.parseDouble(calcularCantLead());
        cantSupxCan = Double.parseDouble(cantSupXcan());
        resultadoLeadxSup = cantLead / cantSupxCan;
        int resultadoInt = (int)resultadoLeadxSup;

        String resultadoLeadXSuper = Integer.toString(resultadoInt);

        //--------------CANT OPP---------------------------//

        Util.Log(resultadoLeadXSuper + "RESULTADO");
        ctx.getSessionScope().put("cantTotalAsig", cantLead);
//        txtTotalOpp.setValue(cantLead);
  //      txtCantidad.setValue(Util.formatearMonto(resultadoLeadXSuper));
        
        return resultadoLeadXSuper;
    }

    public String porcAsignado() {

        double cantSupxCan = 0.0;
        
        double resultadoPorc = 0.0;

        cantSupxCan = Double.parseDouble(cantSupXcan());

        resultadoPorc = Math.round((100 / cantSupxCan) * 100.0) / 100.0;

        String resultadoLeadXSuper = Double.toString(resultadoPorc);

       // txtPorc.setValue(resultadoLeadXSuper);

        Util.Log(resultadoLeadXSuper + "RESULTADO PORC");
        
        return resultadoLeadXSuper;

    }

    public String porcTotal() {

        double porc = 0.0;
        
        String porcTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsAsigJcSupView1Iterator");

        ViewObject vo = dcItteratorBindings.getViewObject();
        
        RowSetIterator rsi = vo.createRowSetIterator(null);

        if (rsi.getRowCount() > 0) {

            Row currRow;

            while (rsi.hasNext()) {


                currRow = rsi.next();


                porc +=
                        Double.parseDouble(currRow.getAttribute("PorcAsig").toString());
                porcTotal = Double.toString(porc);

            }
        }
        Util.Log(porcTotal + "PORC TOTAL");
        return porcTotal;

    }

    public void asignarCanSup() {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher leads = new ServiceBiPublisher();

        List<Lead> lead = new ArrayList<Lead>();

        List<Lead> leadListadoTabla = new ArrayList<Lead>();

        List<NuevoDestino> newDest = new ArrayList<NuevoDestino>();

        String totalCant = cantLeadAsigCanSup();

        String idCanal = ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();

        String idCpg = ctx.getSessionScope().get("CampaignId") == null ? "" : ctx.getSessionScope().get("CampaignId").toString();

        lead = leads.obtenerLead(totalCant, idCanal, idCpg);

        Util.Log(lead.size() + "LIST LEAD");

        newDest = listDest();
        Util.Log(newDest.size() + "LIST DESTINO");

        insertarDataAsigList(lead, newDest);

        String idCarga = ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();
            
        leadListadoTabla = listLead(idCarga);
        
        asignarLeadTxt(leadListadoTabla);
        
        generarTxt(lead);

    }

    public void insertarDataAsigList(List<Lead> plstLeads,
                                     List<NuevoDestino> plstNuevoDestino) {
        
       Integer intIdxLead = 0;
        String idCarga = obtenerIdCarga();

        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;

        for (NuevoDestino desti : plstNuevoDestino) {

            for (int i = 0; i < Integer.parseInt(desti.getCantidad()); i++) {

                // Obtener lead
                Lead lead = plstLeads.get(intIdxLead);
                intIdxLead++;

                try {

                    String query =
                        "BEGIN SBP_SLS_PKG_INS_ASIG_PKG.insert_datos(?,?,?); END;";

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
    }
    
    
    public void actNumOrd(){
        
        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();
        
       int cargaId = Integer.parseInt(idCarga);

        
        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;


    try {


        String query = "BEGIN SBP_SLS_ACT_FLAG_ASIG_PKG.act_num_orden(?); END;";

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

    public void eliminarDataProc(){
        
        ADFContext ctx = ADFContext.getCurrent();

        String idCarga =
            ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();
        
       int cargaId = Integer.parseInt(idCarga);

        
        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;


    try {


        String query = "BEGIN SBP_SLS_ACT_FLAG_ASIG_PKG.eliminar_data_proc(?); END;";

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
                "select SBP_SLS_ID_ASIG_S.nextval ID_CARGA from dual";

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
        
        ctx.getSessionScope().put("idCarga", idCarga);
        return idCarga;
    }

    public void actualizaFlagLead(String idCarga, int ini, int fin) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        PreparedStatement prepareStatement = null;


        try {
            String query = "BEGIN SBP_SLS_ACT_FLAG_ASIG_PKG.act_flag(?,?,?); END;";

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

    public List<Lead> listLead(String idCarga) {

          List<Lead> listaLeads = new ArrayList<Lead>();

          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          String leadNumber = "";
          String resourceId = "";


          try {
              String query =
                  "select lead_number LEAD_NUMBER, resource_id RESOURCE_ID from SBP_SLS_TBL_ASIG where id_sec = " +
                  idCarga + " order by numero_orden";

              Util.Log(query + "QUERY");

              sbp.utils.Connection connection = new sbp.utils.Connection();

              conn = connection.getConnection();

              st = conn.prepareStatement(query);

              rs = st.executeQuery();

              while (rs.next()) {
                  Lead lead = new Lead();
                  leadNumber = rs.getString("LEAD_NUMBER");
                  resourceId = rs.getString("RESOURCE_ID");

                  lead.setLeadNumber(leadNumber);
                  lead.setResourceId(resourceId);
                  listaLeads.add(lead);

              }

          } catch (SQLException e) {
              Util.Log(e + "error");
          } finally {
              DbUtils.closeQuietly(rs);
              DbUtils.closeQuietly(st);
              DbUtils.closeQuietly(conn);
          }

          Util.Log(listaLeads.size() + "LISTA LIST LEAD");
          return listaLeads;
      }

    public void asignarLeadTxt(List<Lead> plstLeads) {
            
            SimplifiedImportActivity p = new SimplifiedImportActivity();
            
            String strTrama1;
            String strTrama2;
            String strCabeceraLead1 = "LeadNumber" + "\n";
            String strCabeceraLead2 = "LeadNumber,ResourceId" + "\n"; 
            Integer intIdxLead = 0;

            int pivot = 10000;
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
                          
                          List<Lead> listaLeads = new ArrayList<Lead>();
                          
                          Util.Log("hola if");
                          
                      listaLeads = plstLeads.subList(ini, fin);

                     intIdxLead = 0;
                      for (int i = 0; i < listaLeads.size(); i++) {
                          
                          Lead lead = listaLeads.get(intIdxLead);
                          
                          intIdxLead++;
                          
                          strTrama1 = lead.getLeadNumber() + "\n";
                          
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
                                      
                                      Lead lead = listaLeads.get(intIdxLead);
                                      intIdxLead++;
                                  
                                      strTrama2 = lead.getLeadNumber() + "," + lead.getResourceId() + "\n";
                                  
                                      Util.Log(strTrama2 + "strTrama2 if");
                                      
                                      byte[] bt = strTrama2.getBytes();
                                      
                                      zos.write(bt, 0, bt.length);
                                      
                                  }
                                  // Cerrar Entry
                                  zos.closeEntry(); 
                          
                          String str = client.Base64.byteArrayToBase64(bos.toByteArray());
                          
                          Util.Log(str + "str if");
                          
                          MergeLeadInvoke mlc = new MergeLeadInvoke();
                          p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);
                          Util.Log(cant + "CANT");
                      } else {
                          ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
                          ZipOutputStream zos1 = new ZipOutputStream(bos1);
                          ZipEntry ze =  new ZipEntry("Lead.csv");
                          zos1.putNextEntry(ze);
                          
                          byte[] cabeceraLead1 = strCabeceraLead1.getBytes();
                          
                          zos1.write(cabeceraLead1, 0, cabeceraLead1.length);
                          
                          List<Lead> listaLeads = new ArrayList<Lead>();
                        
                          intIdxLead = 0;
                         
                          listaLeads = plstLeads.subList(ini, plstLeads.size());

                          for (int i = 0; i < listaLeads.size(); i++) {

                              Lead lead = listaLeads.get(intIdxLead);
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
                                          
                                          Lead lead = listaLeads.get(intIdxLead);
                                          intIdxLead++;
                                      
                                          strTrama2 = lead.getLeadNumber() + "," + lead.getResourceId() + "\n";
                                      
                                      
                                          Util.Log(strTrama2 + "strTrama2 else");
                                          
                                          
                                          byte[] bt = strTrama2.getBytes();

                                          zos1.write(bt, 0, bt.length);
                                          
            
                                      }
                                      // Cerrar Entry
                                      zos1.closeEntry(); 
            
                          
                          String str = client.Base64.byteArrayToBase64(bos1.toByteArray());
                          Util.Log(str + "str else");
                          MergeLeadInvoke mlc = new MergeLeadInvoke();
                          p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);
                          Util.Log(p.getJobID() + "JOB ID");
                      }
                          ini = fin;

                          fin = ini + pivot;

                          cant = cant - pivot;
                     
                     Util.Log(cant + "cant final");
                     
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

        ctx.getSessionScope().put("CaNaLiD", canalId);
        ctx.getSessionScope().put("uSeRiD", supId);
        
        ctx.getSessionScope().put("eJec", ejVenta);
        ctx.getSessionScope().put("SuPper", sup);
        ctx.getSessionScope().put("JeFCan", jefeCanal);
        ctx.getSessionScope().put("Canaaal", nomCanal);
        ctx.getSessionScope().put("userLog", userName);
        
        
    }


    //-----------------METODO PARA EJECUTAR METODOS ANTES O DESPUES DE CARGAR LA PANTALLA(INI)--------//

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

        txtUserName.setValue((String)resolveExpression("#{viewScope.userId2}"));
        obtenerCanal(txtUserName.getValue().toString());

        txtCpgId.setValue((String)resolveExpression("#{viewScope.cpgId2}"));

        String campaignId = txtCpgId.getValue().toString();
        ctx.getSessionScope().put("CampaignId", campaignId);

        String idCanal = ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();

       poblarTablaSup(idCanal);
       mostrarDatos();
       // cantLeadxSup();
       // porcAsignado();


    }
    //-----------------METODO PARA EJECUTAR METODOS ANTES O DESPUES DE CARGAR LA PANTALLA(FIN)--------//

    public void aceptarAsign(ActionEvent actionEvent) {
        asignarCanSup();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirmar().hide();
        FacesContext.getCurrentInstance().addMessage("",
                                                             new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                              "Espere 5 - 10 minutos que termina el proceso de asignación",
                                                                              ""));
    }

    public void confAsig(ActionEvent actionEvent) {


        String cantLead = cantLeadxSup();

        if (!cantLead.equalsIgnoreCase("0")) {

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            String totalPorc = porcTotal();
            String totalCant = cantLeadAsigCanSup();
            
            txtCantAsig.setValue("Está seguro que desea asignar la cantidad de: " +
                                 totalCant + " correspondiente al " +
                                 totalPorc + "% de los leads");

            if(Double.parseDouble(totalPorc) <= 100){
            getPopupConfirmar().show(hints);
            } else{
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

    public void cancelarAsig(ActionEvent actionEvent) {

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirmar().hide();
    }

    public void setTxtUserName(RichInputText txtUserName) {
        this.txtUserName = txtUserName;
    }

    public RichInputText getTxtUserName() {
        return txtUserName;
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
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

    public void confGrabArch(ActionEvent actionEvent) {

        String cantLead = cantLeadxSup();

        if (!cantLead.equalsIgnoreCase("0")) {

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            String totalPorc = porcTotal();
            String totalCant = cantLeadAsigCanSup();
            
            txtCantAsig.setValue("Está seguro que desea asignar la cantidad de: " +
                                 totalCant + " correspondiente al " +
                                 totalPorc + "% de los leads");

            if(Double.parseDouble(totalPorc) <= 100){
            getPopupConfirmar().show(hints);
            } else{
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
    //PDG_22
    public void aceptarGrabArch(ActionEvent actionEvent) {
        asignarGrabArch();//
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirmar().hide();
        FacesContext.getCurrentInstance().addMessage("",
                                                             new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                              "Espere 5 - 10 minutos que termina el proceso de asignación",
                                                                              ""));
    }
    //PDG_33
    public void cancelarGrabArch(ActionEvent actionEvent) {

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        getPopupConfirmar().hide();
    }
    //PDG_40001
    public void asignarGrabArch() {
        
       ADFContext ctx = ADFContext.getCurrent();
       ServiceBiPublisher leads = new ServiceBiPublisher();
       List<Lead> lead = new ArrayList<Lead>();
       List<NuevoDestino> newDestArchivo = new ArrayList<NuevoDestino>();
       String totalCant = cantLeadAsigCanSup();
       String idCanal =ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();
    
       String idCpg = ctx.getSessionScope().get("CampaignId") == null ? "" : ctx.getSessionScope().get("CampaignId").toString();
       lead = leads.obtenerLead(totalCant, idCanal, idCpg);
       newDestArchivo = listDest();
       insertarGrabArch(lead, newDestArchivo);//PDG_A      
        
    }
    //PDG_4002
    public void insertarGrabArch(List<Lead> plstLeads, List<NuevoDestino> plstNuevoDestino2) {
        
        ServiceBiPublisher leads = new ServiceBiPublisher();
        List<NuevoDestino> newDest = new ArrayList<NuevoDestino>();
        Integer intIdxLead = 0;
        cabecera = "ID|CELULAR_1|CELULAR_2|CELULAR_3|TELEFONO_1|TELEFONO_2|TELEFONO_3|NOMBRE_PRODUCTO|DNI|NOMBRE_CONTACTO|APELLIDO_PATERNO|APELLIDO_MATERNO"+ "\n";

        for (NuevoDestino desti : plstNuevoDestino2) {

            //Recorrer los leads
            for (int i = 0; i < Integer.parseInt(desti.getCantidad()); i++) {
                Util.Log("OSC 111 ************************************************");
                // Obtener lead
                Lead lead = plstLeads.get(intIdxLead);
                intIdxLead++;
                //OK
                Util.Log("OSC 111 LEAD_ID        :"+lead.getIdLead());
                
                List<CpgLeadContAccProdFb> cpgLeadContAccProdFb = new ArrayList<CpgLeadContAccProdFb>();
                cpgLeadContAccProdFb = leads.obtenerLeadProdContAccFbCpg(lead.getIdLead().toString());
                newDest = listDest();
                insertarGrabArchProd(cpgLeadContAccProdFb, newDest);/////////////////////////////////////
                
                Util.Log("OSC 111 ************************************************");

            }
        }
        
        
        //Final Envio al sftp
        try {
        
        ADFContext ctx = ADFContext.getCurrent();
        String nomContacto = ctx.getSessionScope().get("contactoFileSpeech") == null ? "" : ctx.getSessionScope().get("contactoFileSpeech").toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Util.Log(dateFormat.format(date) + "DATE");
        String host = System.getProperty("http.proxyHost");
        JSch jsch = new JSch();
        Session session = null;
        Util.Log("OSC_81");
        session = jsch.getSession("root", "191.98.147.250", 22);
        Util.Log("OSC_82");
        session.setConfig("StrictHostKeyChecking", "no");
        Util.Log("OSC_83");
        session.setPassword("oracle");
        Util.Log("OSC_84");
        session.setProxy(new ProxyHTTP(host, 80));
        Util.Log("OSC_85");
        Util.Log("OSC_86");
        session.connect();
        Util.Log("OSC_87");
        Channel channel = session.openChannel("sftp");
        Util.Log("OSC_87.1");
        channel.connect();
        Util.Log("OSC_87.2");
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        Util.Log("OSC_87.3");
        sftpChannel.cd("/root/Documents/");
        String nomFile = "file_productos_lead";//////////////////////////
        Util.Log("OSC NOMBRE DE ARCHIVO : " + nomFile);
        //String speechVenta = "codigospeech"; 
        InputStream inputStream = new ByteArrayInputStream(cabecera.getBytes());
        sftpChannel.put(inputStream, nomFile + ".csv");
        
        } catch (JSchException e) {
        Util.Log("OSC_100 : ");
        e.printStackTrace();
        
        } catch (Exception e){
        Util.Log("OSC_101 : ");
        e.printStackTrace();
        }
        
        
    }
    //PDG_4003
    public void insertarGrabArchProd(List<CpgLeadContAccProdFb> plstCpgLeadContAccProdFb, 
                                     List<NuevoDestino> plstNuevoDestino){
        
        
       
        String idLead = null;
        String telefono1 = null;
        String telefono2 = null;
        String telefono3 = null;
        String nombreProducto = null;
        String nombreContacto = null;
        String celular1 = null;
        String celular2 = null;
        String celular3 = null;
        String dni = null;
        String apellidoPaternoContacto = null;
        String apellidoMaternoContacto = null;
        
        
    try{
                            
        Integer intIdxProducto = 0;
        
            for (NuevoDestino desti : plstNuevoDestino) {
            
            Util.Log("OSC CANTIDAD LEADS: "+Integer.parseInt(desti.getCantidad().toString()));
                
                for (int i = 0; i < Integer.parseInt(desti.getCantidad()); i++) {
    
                    //Obtener Producto
                    CpgLeadContAccProdFb CpgLeadContAccProdFb2 = plstCpgLeadContAccProdFb.get(intIdxProducto);
                    intIdxProducto++;
                
                    idLead = CpgLeadContAccProdFb2.getIdLead().toString();
                    telefono1 = CpgLeadContAccProdFb2.getTelefono01().toString();
                    telefono2 = CpgLeadContAccProdFb2.getTelefono02().toString();
                    telefono3 = CpgLeadContAccProdFb2.getTelefono03().toString();
                    
                    celular1 = CpgLeadContAccProdFb2.getCelular01().toString();
                    celular2 = CpgLeadContAccProdFb2.getCelular02().toString();
                    celular3 = CpgLeadContAccProdFb2.getCelular03().toString();
                    dni      = CpgLeadContAccProdFb2.getNumeroDni().toString();
                    
                    nombreProducto = CpgLeadContAccProdFb2.getNomProd().toString();
                    nombreContacto = CpgLeadContAccProdFb2.getNombre().toString();
                    apellidoPaternoContacto = CpgLeadContAccProdFb2.getApellidoPaterno().toString();
                    apellidoMaternoContacto = CpgLeadContAccProdFb2.getApellidoMaterno().toString();
                    
                    cabecera = cabecera+idLead+"|"+celular1+"|"+celular2+"|"+celular3+"|"+telefono1+"|"+telefono2+"|"+telefono3+"|"+
                                        nombreProducto+"|"+dni+"|"+nombreContacto+"|"+apellidoPaternoContacto+ "|"+apellidoMaternoContacto+ "\n";
                        
                    /*
                    Util.Log("OSC 222 ************************************************");
                    Util.Log("OSC 222 LEAD_ID        :"+CpgLeadContAccProdFb2.getIdLead());
                    Util.Log("OSC_222 TELEFONO 1     :"+CpgLeadContAccProdFb2.getTelefono01());
                    Util.Log("OSC_222 TELEFONO 2     :"+CpgLeadContAccProdFb2.getTelefono02());
                    Util.Log("OSC_222 TELEFONO 3     :"+CpgLeadContAccProdFb2.getTelefono03()); 
                    Util.Log("OSC_222 NOMBRE PRODUCTO:"+CpgLeadContAccProdFb2.getNomProd());
                    Util.Log("OSC_222 CONTACTO       :"+CpgLeadContAccProdFb2.getNombre());
                    Util.Log("OSC 222 ************************************************");
                    */
                    
                }
            }
                
               
                    
    } catch (Exception e){
    Util.Log("OSC_LEAD_NIVEL_1. EXCEPCION :"+e.getMessage());
    } finally {
    Util.Log("OSC FINALY");
      }
            
    }
    
    public void generarTxt(List<Lead> listCsv) {
         try {            
             
             ADFContext ctx = ADFContext.getCurrent();
             
            String nomContacto = ctx.getSessionScope().get("contactoFileSpeech") == null ? "" : ctx.getSessionScope().get("contactoFileSpeech").toString();             
             
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Date date = new Date();
             Util.Log(dateFormat.format(date) + "DATE");

             String fecha = dateFormat.format(date);
             
             String host = System.getProperty("http.proxyHost");
             String port= System.getProperty("http.proxyPort");
             
             JSch jsch = new JSch();
             
             Session session = null;
             
            session = jsch.getSession("ccmg-tVW", "sftp.us2.cloud.oracle.com", 22);
                        
            session.setConfig("StrictHostKeyChecking", "no");
            
            session.setPassword("Evol2018");
             
            session.setProxy(new ProxyHTTP(host, 80)); 
                 
             session.connect();
             
             Channel channel = session.openChannel("sftp");
             channel.connect();
             ChannelSftp sftpChannel = (ChannelSftp)channel;

             sftpChannel.cd("/upload/TramaBuro/");
             
             String nomFile = "speechVenta "  + nomContacto + "-" + fecha;
             Util.Log(nomFile + "nomFile");
             
             String cabecera = "";
             
             String speechVenta = "";
             
             String tramaBuro = "";
             
             for(Lead list : listCsv){
                 
             cabecera = "LAST NAME, FIRST NAME, VENDOR LEAD CODE, PROVINCE, CITY, ADDRESS1, ADDRESS2, ADDRESS3, POSTAL CODE, SECURITY PHRASE, EMAIL, PHONE NUMBER" + "\n";

             speechVenta += list.getDni() + "," + list.getNombre() + " " + list.getApellido() + "," + "-" + "," + "-" + "," + "-" + "," + 
                             list.getBin01() + "," + list.getBin02() + "," + list.getBin03() + "," + list.getFlagAut() + "," + list.getDireccion() + "," + list.getScore() + "," + list.getTelf() + "\n";
                 
             Util.Log(speechVenta);
                 
             tramaBuro = cabecera + speechVenta;
             

             }
             
             
             InputStream inputStream = new ByteArrayInputStream(tramaBuro.getBytes());
             sftpChannel.put(inputStream, "Trama Buro.csv");
             sftpChannel.exit();
             session.disconnect();
             
         } catch (JSchException e) {
             
             e.printStackTrace();
             
         } catch (SftpException e) {
             
             e.printStackTrace();
             
             } catch (Exception e){
             
                e.printStackTrace();
             
             }
     }
    
}


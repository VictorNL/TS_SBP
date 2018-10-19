package view.backing;


import WsEjRep.ServiceBiPublisher;

import client.MergeEntityInvoke;


import dao.Campania;
import dao.Canal;
import dao.EjVenta;
import dao.Items;

import dao.LoginUser;

import java.io.IOException;

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

import javax.xml.parsers.ParserConfigurationException;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelAccordion;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;

import org.apache.myfaces.trinidad.model.CollectionModel;

import org.xml.sax.SAXException;

import view.Util;

public class SBP_SLS_PRINC {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelAccordion pa1;
    private RichShowDetailItem sdi1;
    private RichCommandLink cl1;
    private RichCommandLink cl2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl3;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputDate id1;
    private RichInputDate id2;
    private RichInputText it3;
    private RichMessages m1;
    private RichCommandButton cb3;
    private RichInputText txtCodCpg;
    private RichInputText txtNomCpg;
    private RichInputText txtPorcAsig;
    private RichInputText txtCantOf;
    private RichInputText txtObjectName;
    private RichSelectBooleanCheckbox txtCheckBox;
    private RichOutputText txtCpgId2;
    private RichInputText txtPerCpg;
    private RichInputText txtCantOpp;
    private RichInputText txtUser;
    private RichInputText txtFecIni;
    private RichInputText txtFecFin;
    private RichInputText txtProd;

    private String idUser;
    private RichInputText txtUsuarioId;
    private RichInputText txtActivo;
    private RichTable tblEjxSup;
    private RichInputText txtCantLead;

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

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setId2(RichInputDate id2) {
        this.id2 = id2;
    }

    public RichInputDate getId2() {
        return id2;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }


    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setCb3(RichCommandButton cb3) {
        this.cb3 = cb3;
    }

    public RichCommandButton getCb3() {
        return cb3;
    }

    public void setTxtCodCpg(RichInputText txtCodCpg) {
        this.txtCodCpg = txtCodCpg;
    }

    public RichInputText getTxtCodCpg() {
        return txtCodCpg;
    }

    public void setTxtNomCpg(RichInputText txtNomCpg) {
        this.txtNomCpg = txtNomCpg;
    }

    public RichInputText getTxtNomCpg() {
        return txtNomCpg;
    }


    public void setTxtPorcAsig(RichInputText txtPorcAsig) {
        this.txtPorcAsig = txtPorcAsig;
    }

    public RichInputText getTxtPorcAsig() {
        return txtPorcAsig;
    }

    public void setTxtCantOf(RichInputText txtCantOf) {
        this.txtCantOf = txtCantOf;
    }

    public RichInputText getTxtCantOf() {
        return txtCantOf;
    }

    public void setTxtObjectName(RichInputText txtObjectName) {
        this.txtObjectName = txtObjectName;
    }

    public RichInputText getTxtObjectName() {
        return txtObjectName;
    }

    public void setTxtCheckBox(RichSelectBooleanCheckbox txtCheckBox) {
        this.txtCheckBox = txtCheckBox;
    }

    public RichSelectBooleanCheckbox getTxtCheckBox() {
        return txtCheckBox;
    }

    public void setTxtCpgId2(RichOutputText txtCpgId2) {
        this.txtCpgId2 = txtCpgId2;
    }

    public RichOutputText getTxtCpgId2() {
        return txtCpgId2;
    }

    public void setTxtPerCpg(RichInputText txtPerCpg) {
        this.txtPerCpg = txtPerCpg;
    }

    public RichInputText getTxtPerCpg() {
        return txtPerCpg;
    }

    public void setTxtCantOpp(RichInputText txtCantOpp) {
        this.txtCantOpp = txtCantOpp;
    }

    public RichInputText getTxtCantOpp() {
        return txtCantOpp;
    }

    public void setTxtUser(RichInputText txtUser) {
        this.txtUser = txtUser;
    }

    public RichInputText getTxtUser() {
        return txtUser;
    }

    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void mostrarDatosCpg(String cpgId, String userName) {

        String cantOf = "";

        ADFContext ctx = ADFContext.getCurrent();

        String canalId = "";
        String campaignId = "";
        String userId = "";

        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> listCpg = new ArrayList<Campania>();

        listCpg = cpg.obtenerCpg2(cpgId, userName);
        Util.Log(userName + "nom usuario");


        for (Campania listCamp : listCpg) {

            txtCodCpg.setValue(listCamp.getCpgId());
            campaignId = listCamp.getCpgId();
            txtNomCpg.setValue(listCamp.getCpgNom());
            txtProd.setValue(listCamp.getProd());
            txtFecIni.setValue(listCamp.getFecIni());
            txtFecFin.setValue(listCamp.getFecFin());
            txtPerCpg.setValue(listCamp.getPeriodoCpg());
            txtCheckBox.setValue(listCamp.getActivo());
            txtActivo.setValue(listCamp.getActivo());

            cantOf = listCamp.getCantOf();

            Util.Log(listCamp.getActivo() + "ESTADO");

            canalId = listCamp.getCanalId();
            userId = listCamp.getUserId();
        }

        ctx.getSessionScope().put("CaNaLiD", canalId);
        ctx.getSessionScope().put("CampaignId", campaignId);
        ctx.getSessionScope().put("uSeRiD", userId);
        ctx.getSessionScope().put("cantOf", cantOf);

    }

    public String obtenerUser(String userName) {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher user = new ServiceBiPublisher();

        String partyId = "";
        List<LoginUser> listNomUsu = new ArrayList<LoginUser>();
        listNomUsu = user.obtenerLoginUser(userName);

        for (LoginUser listaNomUsu : listNomUsu) {
            partyId = listaNomUsu.getPartyId();
        }

        Util.Log(partyId + "PARTY ID");
        ctx.getSessionScope().put("PaRtYiD", partyId);

        return partyId;
    }

    public void buscarItem(ActionEvent actionEvent) {

        ServiceBiPublisher item = new ServiceBiPublisher();

        List<Items> lista2 = new ArrayList<Items>();
        lista2 = item.obtenerItems();

        PreparedStatement prepareStatement = null;


        for (Items itemList : lista2) {

            sbp.utils.Connection connection = new sbp.utils.Connection();
            PreparedStatement st = null;
            Connection conn = null;


            String query = "BEGIN SBP_SLS_ITEM.pr_insert_item(?,?);END;";


            try {

                conn = connection.getConnection();
                prepareStatement = conn.prepareStatement(query);
                prepareStatement.setLong(1, itemList.getIdItem());
                prepareStatement.setString(2, itemList.getNomItem());

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

            Util.Log(itemList.getIdItem() + "");
            Util.Log(itemList.getNomItem());

        }

    }

    //boton guardar y cerrar

    public void actualizarCampania() {

        ADFContext ctx = ADFContext.getCurrent();
        String idCpg =
            ctx.getSessionScope().get("campId") == null ? "" : ctx.getSessionScope().get("campId").toString();
        String nomCpg =
            ctx.getSessionScope().get("nombCpg") == null ? "" : ctx.getSessionScope().get("nombCpg").toString();
        String porcAsig =
            ctx.getSessionScope().get("%Asig") == null ? "" : ctx.getSessionScope().get("%Asig").toString();
        String prod =
            ctx.getSessionScope().get("prod") == null ? "" : ctx.getSessionScope().get("prod").toString();
        String fecIni =
            ctx.getSessionScope().get("fecIni") == null ? "" : ctx.getSessionScope().get("fecIni").toString();
        String fecFin =
            ctx.getSessionScope().get("fecFin") == null ? "" : ctx.getSessionScope().get("fecFin").toString();
        String cantOf =
            ctx.getSessionScope().get("cantOf") == null ? "" : ctx.getSessionScope().get("cantOf").toString();
        String perCpg =
            ctx.getSessionScope().get("perCpg") == null ? "" : ctx.getSessionScope().get("perCpg").toString();
        String cantOpp =
            ctx.getSessionScope().get("cantOpp") == null ? "" : ctx.getSessionScope().get("cantOpp").toString();


        MergeEntityInvoke mergeEntity = new MergeEntityInvoke();

        if (nomCpg == null || porcAsig == null || fecIni == null ||
            prod == null || fecFin == null || cantOf == null ||
            txtObjectName.getValue() == null) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen campos incompletos",
                                             ""));

        }

        String resultado;

        double porcentaje = 0.0;

        Util.Log(porcAsig + "porc asig 123");
        porcentaje =
                Math.floor((Double.parseDouble(porcAsig) / 100)) * 10.0 / 10.0;

        Util.Log(txtFecIni.getValue() + "");

        String valor = "";

        Util.Log(txtCheckBox.getValue().toString());


        if (txtCheckBox.getValue().toString().equalsIgnoreCase("true")) {
            valor = "true";
        } else {
            valor = "false";
        }


        try {

            resultado =
                    mergeEntity.mergeEntity(idCpg, nomCpg, prod, porcAsig, fecIni,
                                            fecFin, cantOf, perCpg,
                                            cantOpp,
                                            txtObjectName.getValue().toString());

            double porcent = Double.parseDouble(porcAsig) * 100;
            String porcentajeFinal = String.valueOf(porcent);

            txtCodCpg.setValue(idCpg);
            txtNomCpg.setValue(nomCpg);
            txtPorcAsig.setValue(porcentajeFinal + "%");
            txtProd.setValue(prod);
            txtFecIni.setValue(fecIni);
            txtFecFin.setValue(fecFin);
            txtCantOf.setValue(cantOf);
            txtPerCpg.setValue(perCpg);
            txtCantOpp.setValue(cantOpp);
            txtCheckBox.setValue(valor);


        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }

        Util.Log(valor);
    }

    public void obtenerCanal(String idUsuario) {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher canal = new ServiceBiPublisher();

        String canalId = "";
        String idSup = "";
        String partyNumber = "";
        String idEjVenta = "";


        List<Canal> lista2 = new ArrayList<Canal>();

        Util.Log(idUsuario + "ID Usuario");
        lista2 = canal.obtenerCanal(idUsuario);

        for (Canal canalList : lista2) {
            canalId = canalList.getCanalId();
            idSup = canalList.getIdSup();
            idEjVenta = canalList.getIdEjVenta();
            partyNumber = canalList.getPartyNumber();
        }

        ctx.getSessionScope().put("IdCan", canalId);
        ctx.getSessionScope().put("iDsUpEr", idSup);
        ctx.getSessionScope().put("partyNumber", partyNumber);
        ctx.getSessionScope().put("ejecutivoVenta", idEjVenta);

        Util.Log(canalId + "canalID");
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

    public void listarEjecutivos(String cpgId, String userName) {

        Row rw = null;

        ServiceBiPublisher sup = new ServiceBiPublisher();

        List<EjVenta> listEjVenta = new ArrayList<EjVenta>();

        listEjVenta = sup.obtenerEjVentaXSup(userName, cpgId );

        Util.Log(listEjVenta.size() + "tamaño");

        CollectionModel _tablaEjVenta= (CollectionModel)tblEjxSup.getValue();
        JUCtrlHierBinding _adfTableEjVentaBinding = (JUCtrlHierBinding)_tablaEjVenta.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itEjVenta =  _adfTableEjVentaBinding.getDCIteratorBinding();   
        
        int b = 0;
        
        try {
            b = itEjVenta.getRowSetIterator().getRowCount();
        }
        
        catch (NullPointerException e) {
                System.out.print(e + "excepcion" + e.getMessage() +
                                 "NullPointerException caught");
            } catch (Exception e) {
                Util.Log(e + "excepcion" + e.getMessage() +
                                   "Exception");
            }
        
        
        Util.Log("b --> " + b);
        
        Util.Log("3");
        for (int i = 0; i < b; i++) {
            itEjVenta.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }
        Util.Log("4");

        for (EjVenta listEjec : listEjVenta) {

            rw = itEjVenta.getNavigatableRowIterator().createRow();
            itEjVenta.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);
            Util.Log("5");
            rw.setAttribute("IdSup", listEjec.getIdSup());
            rw.setAttribute("NomSup", listEjec.getSup());
            rw.setAttribute("IdEjVenta", listEjec.getIdEjVenta());
            
            rw.setAttribute("NomEjVenta", listEjec.getEjVenta());
            Util.Log("6");
            rw.setAttribute("Cantidad", Integer.parseInt(listEjec.getCantidadLead()));
            rw.setAttribute("CantLeadGest", listEjec.getCantLeadGest());
            Util.Log(listEjec.getCantidadLead() + "CANT");

        }
    }

    public void onLoad() {

        ADFContext ctx = ADFContext.getCurrent();

        //obtener parámetro
        txtUsuarioId.setValue((String)resolveExpression("#{viewScope.UserID2}"));

        String nomUser = txtUsuarioId.getValue().toString();

        String cpgId = txtCpgId2.getValue().toString();

        Util.Log(txtUsuarioId.getValue().toString() + "NOM USER");

        ctx.getSessionScope().put("nomUser", nomUser);

        ctx.getSessionScope().put("cpanaId", cpgId);
        
        ctx.getSessionScope().put("userLog", nomUser);
        
        infCpg(txtCpgId2.getValue().toString(), txtUsuarioId.getValue().toString());

    }

    public void obtenerDatosCantLead() {

        ADFContext ctx = ADFContext.getCurrent();

        int cant = 0;

        String cantTotal = "";

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings =
            binding.findIteratorBinding("SbpSlsListEvXSupView1Iterator");

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

        Util.Log("1");
        String cantOf =
            ctx.getSessionScope().get("cantOf") == null ? "" : ctx.getSessionScope().get("cantOf").toString();

        double cantidadOfer = Double.parseDouble(cantOf);

        double porcentajeAsig = Math.floor((cant / cantidadOfer) * 100.0);
        Util.Log("2");
        Util.Log(cantTotal + "cantTotal");
        Util.Log(porcentajeAsig + "porcentajeAsig");

        txtCantLead.setValue(Util.formatearMonto(cantTotal));
        txtPorcAsig.setValue(porcentajeAsig + "%");

        Util.Log("3");
    }

    public void infCpg(String cpgId, String userName) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String cantOf = "";

        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> listCpg = new ArrayList<Campania>();

        listCpg = cpg.obtenerCpg2(cpgId, userName);

        if (listCpg.size() != 0) {

            for (Campania listCamp : listCpg) {

                txtCodCpg.setValue(listCamp.getCpgId());
                txtNomCpg.setValue(listCamp.getCpgNom());
                txtProd.setValue(listCamp.getProd());
                txtFecIni.setValue(listCamp.getFecIni());
                txtFecFin.setValue(listCamp.getFecFin());
                txtCantOf.setValue(Util.formatearMonto(listCamp.getCantOf()));
                txtPerCpg.setValue(listCamp.getPeriodoCpg());
                txtCheckBox.setValue(listCamp.getActivo());
                txtActivo.setValue(listCamp.getActivo());
                cantOf = listCamp.getCantOf();
               
                Util.Log(listCamp.getActivo() + "ESTADO");

            }
            
            ctx.getSessionScope().put("cantOf", cantOf);
            
            listarEjecutivos(userName, cpgId);
            obtenerDatosCantLead();
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Procesados",
                                             ""));


        } else {

        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage("",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay leads asignados a esta campaña",
                                         ""));

        }
    }


    public void procesarInf(ActionEvent actionEvent) {

        infCpg(txtCpgId2.getValue().toString(),
               txtUsuarioId.getValue().toString());
    }

    public void setTxtFecIni(RichInputText txtFecIni) {
        this.txtFecIni = txtFecIni;
    }

    public RichInputText getTxtFecIni() {
        return txtFecIni;
    }

    public void setTxtFecFin(RichInputText txtFecFin) {
        this.txtFecFin = txtFecFin;
    }

    public RichInputText getTxtFecFin() {
        return txtFecFin;
    }

    public void setTxtProd(RichInputText txtProd) {
        this.txtProd = txtProd;
    }

    public RichInputText getTxtProd() {
        return txtProd;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setTxtUsuarioId(RichInputText txtUsuarioId) {
        this.txtUsuarioId = txtUsuarioId;
    }

    public RichInputText getTxtUsuarioId() {
        return txtUsuarioId;
    }

    public void setTxtActivo(RichInputText txtActivo) {
        this.txtActivo = txtActivo;
    }

    public RichInputText getTxtActivo() {
        return txtActivo;
    }

    public void setTblEjxSup(RichTable tblEjxSup) {
        this.tblEjxSup = tblEjxSup;
    }

    public RichTable getTblEjxSup() {
        return tblEjxSup;
    }

    public void setTxtCantLead(RichInputText txtCantLead) {
        this.txtCantLead = txtCantLead;
    }

    public RichInputText getTxtCantLead() {
        return txtCantLead;
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

package view.backing;

import Client.MergeLeadClient;

import Dao.Conexion;
import Dao.Proceso;
import Dao.SpeechVenta;

import WsEjRep.ServiceBiPublisher;

import dao.CpgLeadContAccProdFb;
import dao.SpeechVentaBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Date;
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
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.commons.dbutils.DbUtils;

import view.Util;

public class SBP_SLS_VENTA_SPEECH {
    private RichForm f1;
    private RichDocument d1;
    private RichOutputText txtNomCliente1;
    private RichOutputText txtNomVendedor;
    private RichOutputText txtMsjBienv;
    private RichOutputLabel txtNomTarjeta;
    private RichOutputLabel txtProm1;
    private RichOutputLabel txtProm2;
    private RichOutputLabel txtProm3;
    private RichOutputText txtNomCli2;
    private RichOutputText txtProducto;
    private RichOutputText txtMontoProd;
    private RichOutputText txtDni;
    private RichOutputText txtNomCli3;
    private RichOutputText txtNomCli6;
    private RichOutputText txtTea;
    private RichOutputText txtTcea;
    private RichInputText txtMembAnual;
    private RichInputText txtDesgravamen;
    private RichOutputText txtEstadoCuenta;
    private RichOutputText txtEstadoCta2;
    private RichOutputText txtNomCli7;
    private RichOutputText txtSup;
    private RichInputText txtTelfCasa;
    private RichInputText txtCelular;
    private RichInputText txtCorreo;
    private RichInputText txtTelfRef;
    private RichSelectOneChoice txtParent;
    private RichSelectOneChoice cboEstCiv;
    private RichSelectOneChoice cboCliGen;
    private RichInputText txtNomCony1;
    private RichInputText txtNomCony2;
    private RichInputText txtApeMatCony;
    private RichInputDate txtFecNacCony;
    private RichInputText txtDniCony;
    private RichInputText txtApePatCony;
    private RichInputText txtGenCony;
    private RichInputText txtDir;
    private RichInputText txtRef;
    private RichInputText txtNomEmp;
    private RichInputText txtRucEmp;
    private RichInputDate txtFecIng;
    private RichInputText txtDirEmp;
    private RichInputText txtRefEmp;
    private RichInputText txtTelfEmp;
    private RichInputText txtAnexo;
    private RichSelectOneChoice cboCeLaCa;
    private RichSelectOneChoice cboLuEnIn;
    private RichSelectOneChoice cboLuEnDe;
    private RichInputDate txtFecNac;
    private RichInputText txtTelfLlam;
    private RichInputText txtCodDeptDom;
    private RichSelectOneChoice cboCliDep;
    private RichSelectOneChoice txtProvincia;
    private RichInputText txtCodProvincia;
    private RichSelectOneChoice txtDistrito;
    private RichInputText txtCodDistrito;
    private RichInputText txtNomCli4;
    private RichInputText txtNomCli5;
    private RichInputText txtCicloFact;
    private RichSelectBooleanCheckbox txtLDPDD;
    private RichInputText txtCodDeptEmp;
    private RichInputText txtCodProvEmp;
    private RichInputText txtCodDistEmp;
    private RichSelectOneChoice txtDeptEmp;
    private RichSelectOneChoice txtProvEmp;
    private RichSelectOneChoice txtDistEmp;
    private RichSelectOneChoice cboCoGene;
    private RichSelectOneChoice cboConLab;
    private RichSelectOneChoice cboCicFac;
    private RichSelectBooleanCheckbox txtCheckFlaDis;

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

    public void setTxtNomCliente1(RichOutputText txtNomCliente1) {
        this.txtNomCliente1 = txtNomCliente1;
    }

    public RichOutputText getTxtNomCliente1() {
        return txtNomCliente1;
    }

    public void setTxtNomVendedor(RichOutputText txtNomVendedor) {
        this.txtNomVendedor = txtNomVendedor;
    }

    public RichOutputText getTxtNomVendedor() {
        return txtNomVendedor;
    }

    public void setTxtMsjBienv(RichOutputText txtMsjBienv) {
        this.txtMsjBienv = txtMsjBienv;
    }

    public RichOutputText getTxtMsjBienv() {
        return txtMsjBienv;
    }

    public void setTxtNomTarjeta(RichOutputLabel txtNomTarjeta) {
        this.txtNomTarjeta = txtNomTarjeta;
    }

    public RichOutputLabel getTxtNomTarjeta() {
        return txtNomTarjeta;
    }

    public void setTxtProm1(RichOutputLabel txtProm1) {
        this.txtProm1 = txtProm1;
    }

    public RichOutputLabel getTxtProm1() {
        return txtProm1;
    }

    public void setTxtProm2(RichOutputLabel txtProm2) {
        this.txtProm2 = txtProm2;
    }

    public RichOutputLabel getTxtProm2() {
        return txtProm2;
    }

    public void setTxtProm3(RichOutputLabel txtProm3) {
        this.txtProm3 = txtProm3;
    }

    public RichOutputLabel getTxtProm3() {
        return txtProm3;
    }

    public void setTxtNomCli2(RichOutputText txtNomCli2) {
        this.txtNomCli2 = txtNomCli2;
    }

    public RichOutputText getTxtNomCli2() {
        return txtNomCli2;
    }

    public void setTxtProducto(RichOutputText txtProducto) {
        this.txtProducto = txtProducto;
    }

    public RichOutputText getTxtProducto() {
        return txtProducto;
    }

    public void setTxtMontoProd(RichOutputText txtMontoProd) {
        this.txtMontoProd = txtMontoProd;
    }

    public RichOutputText getTxtMontoProd() {
        return txtMontoProd;
    }

    public void setTxtDni(RichOutputText txtDn) {
        this.txtDni = txtDn;
    }

    public RichOutputText getTxtDni() {
        return txtDni;
    }

    public void setTxtNomCli3(RichOutputText txtNomCli3) {
        this.txtNomCli3 = txtNomCli3;
    }

    public RichOutputText getTxtNomCli3() {
        return txtNomCli3;
    }

    public void setTxtNomCli6(RichOutputText txtNomCli6) {
        this.txtNomCli6 = txtNomCli6;
    }

    public RichOutputText getTxtNomCli6() {
        return txtNomCli6;
    }

    public void setTxtTea(RichOutputText txtTea) {
        this.txtTea = txtTea;
    }

    public RichOutputText getTxtTea() {
        return txtTea;
    }

    public void setTxtTcea(RichOutputText txtTcea) {
        this.txtTcea = txtTcea;
    }

    public RichOutputText getTxtTcea() {
        return txtTcea;
    }

    public void setTxtMembAnual(RichInputText txtMembAnual) {
        this.txtMembAnual = txtMembAnual;
    }

    public RichInputText getTxtMembAnual() {
        return txtMembAnual;
    }

    public void setTxtDesgravamen(RichInputText txtDesgravamen) {
        this.txtDesgravamen = txtDesgravamen;
    }

    public RichInputText getTxtDesgravamen() {
        return txtDesgravamen;
    }

    public void setTxtEstadoCuenta(RichOutputText txtEstadoCuenta) {
        this.txtEstadoCuenta = txtEstadoCuenta;
    }

    public RichOutputText getTxtEstadoCuenta() {
        return txtEstadoCuenta;
    }

    public void setTxtEstadoCta2(RichOutputText txtEstadoCta2) {
        this.txtEstadoCta2 = txtEstadoCta2;
    }

    public RichOutputText getTxtEstadoCta2() {
        return txtEstadoCta2;
    }

    public void setTxtNomCli7(RichOutputText txtNomCli7) {
        this.txtNomCli7 = txtNomCli7;
    }

    public RichOutputText getTxtNomCli7() {
        return txtNomCli7;
    }

    public void setTxtSup(RichOutputText txtSup) {
        this.txtSup = txtSup;
    }

    public RichOutputText getTxtSup() {
        return txtSup;
    }

    public void setTxtTelfCasa(RichInputText txtTelfCasa) {
        this.txtTelfCasa = txtTelfCasa;
    }

    public RichInputText getTxtTelfCasa() {
        return txtTelfCasa;
    }

    public void setTxtCelular(RichInputText txtCelular) {
        this.txtCelular = txtCelular;
    }

    public RichInputText getTxtCelular() {
        return txtCelular;
    }

    public void setTxtCorreo(RichInputText txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public RichInputText getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtTelfRef(RichInputText txtTelfRef) {
        this.txtTelfRef = txtTelfRef;
    }

    public RichInputText getTxtTelfRef() {
        return txtTelfRef;
    }

    public void setTxtParent(RichSelectOneChoice txtParent) {
        this.txtParent = txtParent;
    }

    public RichSelectOneChoice getTxtParent() {
        return txtParent;
    }

    public void setCboEstCiv(RichSelectOneChoice cboEstCiv) {
        this.cboEstCiv = cboEstCiv;
    }

    public RichSelectOneChoice getCboEstCiv() {
        return cboEstCiv;
    }

    public void setCboCliGen(RichSelectOneChoice cboCliGen) {
        this.cboCliGen = cboCliGen;
    }

    public RichSelectOneChoice getCboCliGen() {
        return cboCliGen;
    }

    public void setTxtNomCony1(RichInputText txtNomCony1) {
        this.txtNomCony1 = txtNomCony1;
    }

    public RichInputText getTxtNomCony1() {
        return txtNomCony1;
    }

    public void setTxtNomCony2(RichInputText txtNomCony2) {
        this.txtNomCony2 = txtNomCony2;
    }

    public RichInputText getTxtNomCony2() {
        return txtNomCony2;
    }

    public void setTxtApeMatCony(RichInputText txtApeMatCony) {
        this.txtApeMatCony = txtApeMatCony;
    }

    public RichInputText getTxtApeMatCony() {
        return txtApeMatCony;
    }

    public void setTxtFecNacCony(RichInputDate txtFecNacCony) {
        this.txtFecNacCony = txtFecNacCony;
    }

    public RichInputDate getTxtFecNacCony() {
        return txtFecNacCony;
    }

    public void setTxtDniCony(RichInputText txtDniCony) {
        this.txtDniCony = txtDniCony;
    }

    public RichInputText getTxtDniCony() {
        return txtDniCony;
    }

    public void setTxtApePatCony(RichInputText txtApePatCony) {
        this.txtApePatCony = txtApePatCony;
    }

    public RichInputText getTxtApePatCony() {
        return txtApePatCony;
    }

    public void setTxtGenCony(RichInputText txtGenCony) {
        this.txtGenCony = txtGenCony;
    }

    public RichInputText getTxtGenCony() {
        return txtGenCony;
    }

    public void setTxtDir(RichInputText txtDir) {
        this.txtDir = txtDir;
    }

    public RichInputText getTxtDir() {
        return txtDir;
    }

    public void setTxtRef(RichInputText txtRef) {
        this.txtRef = txtRef;
    }

    public RichInputText getTxtRef() {
        return txtRef;
    }

    public void setTxtNomEmp(RichInputText txtNomEmp) {
        this.txtNomEmp = txtNomEmp;
    }

    public RichInputText getTxtNomEmp() {
        return txtNomEmp;
    }

    public void setTxtRucEmp(RichInputText txtRucEmp) {
        this.txtRucEmp = txtRucEmp;
    }

    public RichInputText getTxtRucEmp() {
        return txtRucEmp;
    }

    public void setTxtFecIng(RichInputDate txtFecIng) {
        this.txtFecIng = txtFecIng;
    }

    public RichInputDate getTxtFecIng() {
        return txtFecIng;
    }

    public void setTxtDirEmp(RichInputText txtDirEmp) {
        this.txtDirEmp = txtDirEmp;
    }

    public RichInputText getTxtDirEmp() {
        return txtDirEmp;
    }

    public void setTxtRefEmp(RichInputText txtRefEmp) {
        this.txtRefEmp = txtRefEmp;
    }

    public RichInputText getTxtRefEmp() {
        return txtRefEmp;
    }

    public void setTxtTelfEmp(RichInputText txtTelfEmp) {
        this.txtTelfEmp = txtTelfEmp;
    }

    public RichInputText getTxtTelfEmp() {
        return txtTelfEmp;
    }

    public void setTxtAnexo(RichInputText txtAnexo) {
        this.txtAnexo = txtAnexo;
    }

    public RichInputText getTxtAnexo() {
        return txtAnexo;
    }

    public void setCboCeLaCa(RichSelectOneChoice cboCeLaCa) {
        this.cboCeLaCa = cboCeLaCa;
    }

    public RichSelectOneChoice getCboCeLaCa() {
        return cboCeLaCa;
    }

    public void setCboLuEnIn(RichSelectOneChoice cboLuEnIn) {
        this.cboLuEnIn = cboLuEnIn;
    }

    public RichSelectOneChoice getCboLuEnIn() {
        return cboLuEnIn;
    }

    public void setCboLuEnDe(RichSelectOneChoice cboLuEnDe) {
        this.cboLuEnDe = cboLuEnDe;
    }

    public RichSelectOneChoice getCboLuEnDe() {
        return cboLuEnDe;
    }

    public void setTxtFecNac(RichInputDate txtFecNac) {
        this.txtFecNac = txtFecNac;
    }

    public RichInputDate getTxtFecNac() {
        return txtFecNac;
    }

    public void setTxtTelfLlam(RichInputText txtTelfLlam) {
        this.txtTelfLlam = txtTelfLlam;
    }

    public RichInputText getTxtTelfLlam() {
        return txtTelfLlam;
    }

    public void setTxtCodDeptDom(RichInputText txtCodDeptDom) {
        this.txtCodDeptDom = txtCodDeptDom;
    }

    public RichInputText getTxtCodDeptDom() {
        return txtCodDeptDom;
    }

    public void setCboCliDep(RichSelectOneChoice cboCliDep) {
        this.cboCliDep = cboCliDep;
    }

    public RichSelectOneChoice getCboCliDep() {
        return cboCliDep;
    }

    public void setTxtProvincia(RichSelectOneChoice txtProvincia) {
        this.txtProvincia = txtProvincia;
    }

    public RichSelectOneChoice getTxtProvincia() {
        return txtProvincia;
    }

    public void setTxtCodProvincia(RichInputText txtCodProvincia) {
        this.txtCodProvincia = txtCodProvincia;
    }

    public RichInputText getTxtCodProvincia() {
        return txtCodProvincia;
    }

    public void setTxtDistrito(RichSelectOneChoice txtDistrito) {
        this.txtDistrito = txtDistrito;
    }

    public RichSelectOneChoice getTxtDistrito() {
        return txtDistrito;
    }

    public void setTxtCodDistrito(RichInputText txtCodDistrito) {
        this.txtCodDistrito = txtCodDistrito;
    }

    public RichInputText getTxtCodDistrito() {
        return txtCodDistrito;
    }

    public void setTxtNomCli4(RichInputText txtNomCli4) {
        this.txtNomCli4 = txtNomCli4;
    }

    public RichInputText getTxtNomCli4() {
        return txtNomCli4;
    }

    public void setTxtNomCli5(RichInputText txtNomCli5) {
        this.txtNomCli5 = txtNomCli5;
    }

    public RichInputText getTxtNomCli5() {
        return txtNomCli5;
    }

    public void setTxtCicloFact(RichInputText txtCicloFact) {
        this.txtCicloFact = txtCicloFact;
    }

    public RichInputText getTxtCicloFact() {
        return txtCicloFact;
    }

    public void setTxtLDPDD(RichSelectBooleanCheckbox txtLDPDD) {
        this.txtLDPDD = txtLDPDD;
    }

    public RichSelectBooleanCheckbox getTxtLDPDD() {
        return txtLDPDD;
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
      
        createInsertSpeechVenta3();
        createInsertSpeechVenta4();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = ctx.getSessionScope().get("LEADID") == null ? "" : ctx.getSessionScope().get("LEADID").toString();
        String nomBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        String ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String supervisor = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
        String monto = ctx.getSessionScope().get("monto") == null ? "" : ctx.getSessionScope().get("monto").toString();
        mostrarSpeechVenta(idLead, nomBin, ejVenta, supervisor, monto);
        
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String createInsertSpeechVenta3() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setTxtCodDeptEmp(RichInputText txtCodDeptEmp) {
        this.txtCodDeptEmp = txtCodDeptEmp;
    }

    public RichInputText getTxtCodDeptEmp() {
        return txtCodDeptEmp;
    }

    public void setTxtCodProvEmp(RichInputText txtCodProvEmp) {
        this.txtCodProvEmp = txtCodProvEmp;
    }

    public RichInputText getTxtCodProvEmp() {
        return txtCodProvEmp;
    }

    public void setTxtCodDistEmp(RichInputText txtCodDistEmp) {
        this.txtCodDistEmp = txtCodDistEmp;
    }

    public RichInputText getTxtCodDistEmp() {
        return txtCodDistEmp;
    }

    public String createInsertSpeechVenta4() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setTxtDeptEmp(RichSelectOneChoice txtDeptEmp) {
        this.txtDeptEmp = txtDeptEmp;
    }

    public RichSelectOneChoice getTxtDeptEmp() {
        return txtDeptEmp;
    }

    public void setTxtProvEmp(RichSelectOneChoice txtProvEmp) {
        this.txtProvEmp = txtProvEmp;
    }

    public RichSelectOneChoice getTxtProvEmp() {
        return txtProvEmp;
    }

    public void setTxtDistEmp(RichSelectOneChoice txtDistEmp) {
        this.txtDistEmp = txtDistEmp;
    }

    public RichSelectOneChoice getTxtDistEmp() {
        return txtDistEmp;
    }
    
    public void listXProd(String nombreProd) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String promocion = "";
        String msjBienv = "";
        String membresia = "";
        String seguro = "";
        


        try {
            String query =
                "select id_prod ID_PROD, nom_prod NOM_PROD, tcea TCEA, comision COMISION, membresia MEMBRESIA, seguro SEGURO, promoción PROMOCION, msj_bienv MSJ_BIENV from sbp_sls_maestro_prod where nom_prod = '" + nombreProd + "'";
            
            Util.Log(query + "query");
            
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    
                    promocion = rs.getString("PROMOCION");
                    
                    msjBienv = rs.getString("MSJ_BIENV");
                    
                    membresia = rs.getString("MEMBRESIA");
                    
                    seguro = rs.getString("SEGURO");
                    
                    txtMsjBienv.setValue(msjBienv);
                    
                    txtMembAnual.setValue(membresia);
                    txtDesgravamen.setValue(seguro);
                    
                    ArrayList<String> items = new ArrayList<String>(Arrays.asList((promocion).split("\\|", -1)));

                    int sizeList = items.size() - 1;
                    
                    Util.Log(sizeList + "sizeList");
                    
                    if(sizeList == 1){
                        
                        Util.Log("ENTRA if");
                        txtProm1.setValue(items.get(1));
                        txtProm2.setValue("");
                        txtProm3.setValue("");
                    } else  if(sizeList == 2){
                        
                       Util.Log("ENTRA ELSE if");
                        txtProm1.setValue(items.get(1));
                        txtProm2.setValue(items.get(2));
                        txtProm3.setValue("");
                       
                       
                    } else if(sizeList == 3){
                        
                       Util.Log("ENTRA ELSE if 2");
                        txtProm1.setValue(items.get(1));
                        txtProm2.setValue(items.get(2));
                        txtProm3.setValue(items.get(3));
                       
                    }
                    
                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        
    }
    
    public void mostrarSpeechVenta(String idLead, String nomProducto, String ejVenta, String supervisor, String monto){
        
        ADFContext ctx = ADFContext.getCurrent();
       
        ServiceBiPublisher bi = new ServiceBiPublisher();
        
        String mon = "";
        
        List<CpgLeadContAccProdFb> speechList = new ArrayList<CpgLeadContAccProdFb>();
        
        speechList = bi.obtenerLeadProdContAccFbCpg(idLead);
        
        ctx.getSessionScope().put("CpgLeadContAccProdFb", speechList);
        
        Util.Log("speechList tamaño --> " + speechList.size());
        
        for(CpgLeadContAccProdFb listSpeech : speechList){
            
            if (listSpeech.getMonLead().equalsIgnoreCase("PEN")) {
                mon = "S/ ";
            } else if (listSpeech.getMonLead().equalsIgnoreCase("USD")) {
                mon = "$ ";
            } else {
                mon = "";
            }
            
           txtNomCliente1.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtNomVendedor.setValue(ejVenta);
           txtNomTarjeta.setValue(nomProducto);
           txtNomCli2.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtProducto.setValue(nomProducto);
           txtMontoProd.setValue(mon + " " + monto);
           txtDni.setValue(listSpeech.getNumeroDni());
           txtNomCli3.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtFecNac.setValue(listSpeech.getFechaNacimiento());
           txtNomCli4.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtNomCli5.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtNomCli6.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtTea.setValue(listSpeech.getTeaMax());
           txtTcea.setValue(listSpeech.getTceaMax());
           txtSup.setValue(supervisor);
           txtNomCli7.setValue(listSpeech.getNombre() + " " + listSpeech.getApellidoPaterno() + " " + listSpeech.getApellidoMaterno());
           txtEstadoCuenta.setValue("S/. 8.90");
           txtEstadoCta2.setValue("S/. 8.90");
                
        }
        
        listXProd(nomProducto);
        
    }
    
    public void crearSpeech(){
        
        Conexion c = Util.DatosConexionSBP();
        
        Proceso p = new Proceso();
        
        MergeLeadClient mlc = new MergeLeadClient();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        
        String nomBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
        String supervisor = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
      
        String vendor = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        
        List<CpgLeadContAccProdFb> l = new ArrayList<CpgLeadContAccProdFb>();
        
        l = (List<CpgLeadContAccProdFb>)ctx.getSessionScope().get("CpgLeadContAccProdFb");
        
        SpeechVenta e = new SpeechVenta();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        DateFormat horaFormat = new SimpleDateFormat("HH");

        Date horaFormato = new Date();

        String horaFb = horaFormat.format(horaFormato);
       
        int hora = Integer.parseInt(horaFb) - 5;

        String horaFeedback = hora + "";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        String fecGestion = dateFormat.format(date);
        
        e.setLeadID(l.get(0).getIdLead() .toString());
        
        DateFormat dateFormat3 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date3 = new Date();
        e.setSpeechID(dateFormat3.format(date3));
        
        
        e.setClienteCodigo(l.get(0).getCliente());
        e.setClienteNombre(l.get(0).getApellidoPaterno() + " " +
                           l.get(0).getApellidoMaterno() + ", " + l.get(0).getNombre() +
                           " " + l.get(0).getSegundoNombre());
        e.setUsuarioNombre(ejVenta);
        e.setProductoCodigo(l.get(0).getCodigoProducto());
        e.setProductoNombre(nomBin);
        Util.Log(l.get(0).getMonto() + "l.getMonto()");
        e.setProductoMonto(Util.formatearMonto(l.get(0).getMonto()));
        e.setClienteNumeroDocumento(l.get(0).getNumeroDni());
        e.setValorTEA(Util.nvl(l.get(0).getTeaMin(), l.get(0).getTeaMax()));
        e.setValorTCEA(Util.nvl(l.get(0).getTceaMin(), l.get(0).getTceaMax()));
        e.setValorMembresia(""); // Falta definir campo
        e.setValorDesgravamen(""); // Falta definir campo
        e.setSoporteNumeroTelefono(""); // Falta definir campo
        e.setSupervisorNombre(supervisor); // Falta definir campo
        e.setValorEnvioEstadoCuenta("S/ 8.9");
        e.setCpgNom(l.get(0).getNomCpg());
        e.setHoraSpeech(horaFeedback);
        e.setVendorName(vendor);
        e.setFecGestion(fecGestion);
        e.setLeadID(l.get(0).getIdLead().toString());
        try {
            e.setClienteFechaNacimiento(sdf.format(this.txtFecNac.getValue()));
        } catch (Exception s) {
            e.setClienteFechaNacimiento(""); // e.getClienteFechaNacimiento());
        }
        
        e.setTelefonoLlamada(Util.nvl(this.txtTelfLlam, ""));
        e.setTelefonoCasa(Util.nvl(this.txtTelfCasa, ""));
        e.setTelefonoCelular(Util.nvl(this.txtCelular, ""));
        e.setCorreoElectronico(Util.nvl(this.txtCorreo,
                                        "")); // e.getCorreoElectronico());
        e.setTelefonoReferencia(Util.nvl(this.txtTelfRef,
                                         "")); // e.getTelefonoReferencia());
        e.setParentescoCodigo(Util.nvl(this.txtParent,
                                       "")); // e.getParentescoCodigo());
        e.setParentescoDescripcion(Util.getSelectedItem(this.txtParent)); // e.getParentescoDescripcion());
        e.setEstadoCivilCodigo(Util.nvl(this.cboEstCiv,
                                        "")); // e.getEstadoCivilCodigo());
        e.setEstadoCivilDescripcion(Util.getSelectedItem(this.cboEstCiv)); // e.getEstadoCivilDescripcion());
        e.setGeneroCodigo(Util.nvl(this.cboCliGen,
                                   "")); // e.getGeneroCodigo());
        e.setGeneroDescripcion(Util.getSelectedItem(this.cboCliGen)); // e.getGeneroDescripcion());
        e.setConyugeApellidoPaterno(Util.nvl(this.txtApePatCony,
                                             "")); // e.getConyugeApellidoPaterno());
        e.setConyugeApellidoMaterno(Util.nvl(this.txtApeMatCony,
                                             "")); // e.getConyugeApellidoMaterno());
        e.setConyugeNombre1(Util.nvl(this.txtNomCony1,
                                     "")); // e.getConyugeNombre1());
        e.setConyugeNombre2(Util.nvl(this.txtNomCony2,
                                     "")); // e.getConyugeNombre2());
        try {
            e.setConyugeFechaNacimiento(sdf.format(this.txtFecNacCony.getValue()));
        } catch (Exception s) {
            e.setConyugeFechaNacimiento(""); // e.getConyugeFechaNacimiento());
        }

        try {
            e.setCentroLaboresFechaIngreso(sdf.format(this.txtFecIng.getValue()));
        } catch (Exception s) {
            e.setCentroLaboresFechaIngreso(""); // e.getConyugeFechaNacimiento());
        }


        e.setConyugeNumeroDocumento(Util.nvl(this.txtDniCony,
                                             "")); // e.getConyugeNumeroDocumento());

        e.setConyugeGenerocodigo(Util.nvl(this.cboCoGene, ""));
        e.setConyugeGeneroDescripcion(Util.getSelectedItem(this.cboCoGene));
        e.setDireccion(Util.nvl(this.txtDir, ""));

        e.setDepartamento(Util.nvl(this.cboCliDep, ""));

        e.setProvincia(Util.nvl(this.txtProvincia, ""));

        e.setDistrito(Util.nvl(this.txtDistrito, ""));

        e.setReferencia(Util.nvl(this.cboConLab,
                                 "")); // e.getReferencia());

        e.setCondicionLaboralCodigo(Util.nvl(this.cboConLab,
                                             "")); // e.getCondicionLaboralCodigo());

        e.setCondicionLaboralDescripcion(Util.getSelectedItem(this.cboConLab)); // e.getCondicionLaboralDescripcion());

        e.setCentroLaboresNombre(Util.nvl(this.txtNomEmp,
                                          "")); // e.getCentroLaboresNombre());

        e.setCentroLaboresRuc(Util.nvl(this.txtRucEmp,
                                       "")); // e.getCentroLaboresRuc());

        e.setCentroLaboresDireccion(Util.nvl(this.txtDirEmp,
                                             "")); // e.getCentroLaboresDireccion());

        e.setCentroLaboresDepartamento(Util.nvl(this.txtDeptEmp, ""));

        e.setCentroLaboresProvincia(Util.nvl(this.txtProvEmp, ""));

        e.setCentroLaboresDistrito(Util.nvl(this.txtDistEmp, ""));

        e.setCentroLaboresReferencia(Util.nvl(this.txtRefEmp,
                                              "")); // e.getCentroLaboresReferencia());

        e.setCentroLaboresTelefono(Util.nvl(this.txtTelfEmp,
                                            "")); // e.getCentroLaboresTelefono());

        e.setCentroLaboresAnexo(Util.nvl(this.txtAnexo,
                                         "")); // e.getCentroLaboresAnexo());

        e.setCentroLaboresCargoCodigo(Util.nvl(this.cboCeLaCa,
                                               "")); // e.getCentroLaboresCargoCodigo());

        e.setCentroLaboresCargoDescripcion(Util.getSelectedItem(this.cboCeLaCa)); // e.getCentroLaboresCargoDescripcion());

        e.setLugarEntregaIndependienteCodigo(Util.nvl(this.cboLuEnIn,
                                                      "")); // e.getLugarEntregaIndependienteCodigo());

        e.setLugarEntregaIndependienteDescripcion(Util.getSelectedItem(this.cboLuEnIn)); // e.getLugarEntregaIndependienteDescripcion());

        e.setLugarEntregaDependienteCodigo(Util.nvl(this.cboLuEnDe,
                                                    "")); // e.getLugarEntregaDependienteCodigo());

        e.setLugarEntregaDependienteDescripcion(Util.getSelectedItem(this.cboLuEnDe)); // e.getLugarEntregaDependienteDescripcion());

        e.setValorMembresia(txtMembAnual.getValue().toString()); //e.setValorMembresia(Util.getSelectedItem(this.cboValMen));
        e.setValorDesgravamen(txtDesgravamen.getValue().toString()); //e.setValorDesgravamen(Util.getSelectedItem(this.cboValDes));
        e.setCicloFacturacion(Util.getSelectedItem(this.cboCicFac)); //e.setValorEnvioEstadoCuenta(e.getValorEnvioEstadoCuenta());

        String flaDis = "";

        if (txtCheckFlaDis.getValue().toString().equalsIgnoreCase("true")) {
            flaDis = "1";
        } else {
            flaDis = "0";
        }

        e.setFlagDisposicionEfectivo(flaDis);

        String flaAut = "";

        if (txtLDPDD.getValue().toString().equalsIgnoreCase("true")) {
            flaAut = "1";
        } else {
            flaAut = "0";
        }

        e.setFlagAutorizacionLPDP(flaAut);

        e.setPrimerNombre(l.get(0).getNombre());
        e.setSegundoNombre(l.get(0).getSegundoNombre());
        e.setApePaterno(l.get(0).getApellidoPaterno());
        e.setApeMaterno(l.get(0).getApellidoMaterno());
        e.setTipoProd(nomBin);
        e.setTea(l.get(0).getTeaMax());
        
        p = mlc.mergeLeadSpeechVenta(c, e);

    }

    public void setCboCoGene(RichSelectOneChoice cboCoGene) {
        this.cboCoGene = cboCoGene;
    }

    public RichSelectOneChoice getCboCoGene() {
        return cboCoGene;
    }

    public void setCboConLab(RichSelectOneChoice cboConLab) {
        this.cboConLab = cboConLab;
    }

    public RichSelectOneChoice getCboConLab() {
        return cboConLab;
    }

    public void setCboCicFac(RichSelectOneChoice cboCicFac) {
        this.cboCicFac = cboCicFac;
    }

    public RichSelectOneChoice getCboCicFac() {
        return cboCicFac;
    }

    public void registrarVenta(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idEjecVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
        
        crearSpeech();
        Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + idEjecVenta);
    }

    public void setTxtCheckFlaDis(RichSelectBooleanCheckbox txtCheckFlaDis) {
        this.txtCheckFlaDis = txtCheckFlaDis;
    }

    public RichSelectBooleanCheckbox getTxtCheckFlaDis() {
        return txtCheckFlaDis;
    }
}

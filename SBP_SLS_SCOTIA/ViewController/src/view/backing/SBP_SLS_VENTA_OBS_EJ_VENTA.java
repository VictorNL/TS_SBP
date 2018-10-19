package view.backing;

import Client.MergeLeadInvoke;

import WsEjRep.ServiceBiPublisher;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import dao.SpeechVentaBO;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.TimeZone;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;

import javax.xml.parsers.ParserConfigurationException;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.xml.sax.SAXException;

import view.Util;

public class SBP_SLS_VENTA_OBS_EJ_VENTA {
    private RichForm f1;
    private RichDocument d1;
    private RichSelectBooleanCheckbox txtLDPDD;
    private RichOutputText txtNomCli7;
    private RichOutputText txtEstadoCta2;
    private RichOutputText txtEstadoCuenta;
    private RichOutputText txtDesgravamen2;
    private RichInputText txtDesgravamen;
    private RichInputText txtMembAnual;
    private RichOutputText txtTcea;
    private RichOutputText txtTea;
    private RichOutputText txtNomCli6;
    private RichInputText txtNomCli5;
    private RichInputText txtNomCli4;
    private RichInputText txtCargoEmp;
    private RichInputText txtAnexo;
    private RichInputText txtTelfEmp;
    private RichInputText txtRefEmp;
    private RichInputText txtDirEmp;
    private RichInputDate txtFecIng;
    private RichInputText txtRucEmp;
    private RichInputText txtNomEmp;
    private RichInputText txtRef;
    private RichInputText txtDir;
    private RichInputText txtDniCony;
    private RichInputDate txtFecNacCony;
    private RichInputText txtApeMatCony;
    private RichInputText txtApePatCony;
    private RichInputText txtNomCony2;
    private RichInputText txtNomCony1;
    private RichInputText txtTelfRef;
    private RichInputText txtCorreo;
    private RichInputText txtCelular;
    private RichInputText txtTelfCasa;
    private RichInputText txtTelfLlam;
    private RichInputDate txtFecNac;
    private RichOutputText txtNomCli3;
    private RichOutputText txtDni;
    private RichOutputText txtMontoProd;
    private RichOutputText txtProducto;
    private RichOutputText txtNomCli2;
    private RichOutputLabel txtProm3;
    private RichOutputLabel txtProm2;
    private RichOutputLabel txtProm1;
    private RichOutputLabel txtNomTarjeta;
    private RichOutputText txtMsjBienv;
    private RichOutputText txtNomVendedor;
    private RichOutputText txtNomCliente1;
    private RichSelectOneChoice cboCliPar;
    private RichSelectOneChoice cboEstCiv;
    private RichSelectOneChoice cboCliGen;
    private RichSelectOneChoice cboCoGene;
    private RichSelectOneChoice cboConLab;
    private RichSelectOneChoice cboCeLaCa;
    private RichSelectOneChoice cboLuEnDe;
    private RichSelectOneChoice cboLuEnIn;
    private RichSelectOneChoice txtDeptEmp;
    private RichSelectOneChoice txtProvEmp;
    private RichSelectOneChoice txtDistEmp;
    private RichInputText txtCodDept;
    private RichSelectOneChoice txtDept;
    private RichSelectOneChoice txtProv;
    private RichSelectOneChoice txtDist;
    private RichInputText txtTextoSpeech;
    private RichSelectOneChoice txtLugEntrDep;
    private RichSelectOneChoice txtLugarEntrega;
    private RichInputText txtCargo;
    private RichSelectOneChoice txtGenCony;
    private RichSelectOneChoice txtEstCivil;
    private RichSelectOneChoice txtParent;
    private RichOutputText txtInfoLead;
    private RichInputText txtSup;
    private RichSelectOneChoice txtCicloFact;
    private RichSelectOneChoice txtCondLab;
    private RichSelectOneChoice cboTransInternac;
    private RichSelectOneChoice cboTransInternet;
    private RichSelectOneChoice cboEnvEstCta;
    private RichSelectOneChoice cboLugEnvEstCta;
    private RichPopup popUpDirecDom;
    private RichPopup popUpDirecLab;
    private RichInputText txtTipoViaDom;
    private RichInputText txtNombViaDom;
    private RichInputText txtNumDom;
    private RichInputText txtNroDptoDom;
    private RichInputText txtOficDom;
    private RichInputText txtPisoDom;
    private RichInputText txtManzDom;
    private RichInputText txtLoteDom;
    private RichInputText txtInteriorDom;
    private RichInputText txtSectorDom;
    private RichInputText txtKmDom;
    private RichInputText txtTipZonaDom;
    private RichInputText txtNombZonaDom;
    private RichInputText txtTipoViaLab;
    private RichInputText txtNombViaLab;
    private RichInputText txtNumLab;
    private RichInputText txtNroDptoLab;
    private RichInputText txtOficLab;
    private RichInputText txtPisoLab;
    private RichInputText txtManzLab;
    private RichInputText txtLoteLab;
    private RichInputText txtInteriorLab;
    private RichInputText txtSectorLab;
    private RichInputText txtKmLab;
    private RichInputText txtTipZonaLab;
    private RichInputText txtNombZonaLab;

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

    public void setTxtLDPDD(RichSelectBooleanCheckbox txtLDPDD) {
        this.txtLDPDD = txtLDPDD;
    }

    public RichSelectBooleanCheckbox getTxtLDPDD() {
        return txtLDPDD;
    }

    public void setTxtNomCli7(RichOutputText txtNomCli7) {
        this.txtNomCli7 = txtNomCli7;
    }

    public RichOutputText getTxtNomCli7() {
        return txtNomCli7;
    }

    public void setTxtEstadoCta2(RichOutputText txtEstadoCta2) {
        this.txtEstadoCta2 = txtEstadoCta2;
    }

    public RichOutputText getTxtEstadoCta2() {
        return txtEstadoCta2;
    }

    public void setTxtEstadoCuenta(RichOutputText txtEstadoCuenta) {
        this.txtEstadoCuenta = txtEstadoCuenta;
    }

    public RichOutputText getTxtEstadoCuenta() {
        return txtEstadoCuenta;
    }

    public void setTxtDesgravamen2(RichOutputText txtDesgravamen2) {
        this.txtDesgravamen2 = txtDesgravamen2;
    }

    public RichOutputText getTxtDesgravamen2() {
        return txtDesgravamen2;
    }

    public void setTxtDesgravamen(RichInputText txtDesgravamen) {
        this.txtDesgravamen = txtDesgravamen;
    }

    public RichInputText getTxtDesgravamen() {
        return txtDesgravamen;
    }

    public void setTxtMembAnual(RichInputText txtMembAnual) {
        this.txtMembAnual = txtMembAnual;
    }

    public RichInputText getTxtMembAnual() {
        return txtMembAnual;
    }

    public void setTxtTcea(RichOutputText txtTcea) {
        this.txtTcea = txtTcea;
    }

    public RichOutputText getTxtTcea() {
        return txtTcea;
    }

    public void setTxtTea(RichOutputText txtTea) {
        this.txtTea = txtTea;
    }

    public RichOutputText getTxtTea() {
        return txtTea;
    }

    public void setTxtNomCli6(RichOutputText txtNomCli6) {
        this.txtNomCli6 = txtNomCli6;
    }

    public RichOutputText getTxtNomCli6() {
        return txtNomCli6;
    }

    public void setTxtNomCli5(RichInputText txtNomCli5) {
        this.txtNomCli5 = txtNomCli5;
    }

    public RichInputText getTxtNomCli5() {
        return txtNomCli5;
    }

    public void setTxtNomCli4(RichInputText txtNomCli4) {
        this.txtNomCli4 = txtNomCli4;
    }

    public RichInputText getTxtNomCli4() {
        return txtNomCli4;
    }

    public void setTxtCargoEmp(RichInputText txtCargoEmp) {
        this.txtCargoEmp = txtCargoEmp;
    }

    public RichInputText getTxtCargoEmp() {
        return txtCargoEmp;
    }

    public void setTxtAnexo(RichInputText txtAnexo) {
        this.txtAnexo = txtAnexo;
    }

    public RichInputText getTxtAnexo() {
        return txtAnexo;
    }

    public void setTxtTelfEmp(RichInputText txtTelfEmp) {
        this.txtTelfEmp = txtTelfEmp;
    }

    public RichInputText getTxtTelfEmp() {
        return txtTelfEmp;
    }

    public void setTxtRefEmp(RichInputText txtRefEmp) {
        this.txtRefEmp = txtRefEmp;
    }

    public RichInputText getTxtRefEmp() {
        return txtRefEmp;
    }

    public void setTxtDirEmp(RichInputText txtDirEmp) {
        this.txtDirEmp = txtDirEmp;
    }

    public RichInputText getTxtDirEmp() {
        return txtDirEmp;
    }

    public void setTxtFecIng(RichInputDate txtFecIng) {
        this.txtFecIng = txtFecIng;
    }

    public RichInputDate getTxtFecIng() {
        return txtFecIng;
    }

    public void setTxtRucEmp(RichInputText txtRucEmp) {
        this.txtRucEmp = txtRucEmp;
    }

    public RichInputText getTxtRucEmp() {
        return txtRucEmp;
    }

    public void setTxtNomEmp(RichInputText txtNomEmp) {
        this.txtNomEmp = txtNomEmp;
    }

    public RichInputText getTxtNomEmp() {
        return txtNomEmp;
    }

    public void setTxtRef(RichInputText txtRef) {
        this.txtRef = txtRef;
    }

    public RichInputText getTxtRef() {
        return txtRef;
    }

    public void setTxtDir(RichInputText txtDir) {
        this.txtDir = txtDir;
    }

    public RichInputText getTxtDir() {
        return txtDir;
    }

    public void setTxtDniCony(RichInputText txtDniCony) {
        this.txtDniCony = txtDniCony;
    }

    public RichInputText getTxtDniCony() {
        return txtDniCony;
    }

    public void setTxtFecNacCony(RichInputDate txtFecNacCony) {
        this.txtFecNacCony = txtFecNacCony;
    }

    public RichInputDate getTxtFecNacCony() {
        return txtFecNacCony;
    }

    public void setTxtApeMatCony(RichInputText txtApeMatCony) {
        this.txtApeMatCony = txtApeMatCony;
    }

    public RichInputText getTxtApeMatCony() {
        return txtApeMatCony;
    }

    public void setTxtApePatCony(RichInputText txtApePatCony) {
        this.txtApePatCony = txtApePatCony;
    }

    public RichInputText getTxtApePatCony() {
        return txtApePatCony;
    }

    public void setTxtNomCony2(RichInputText txtNomCony2) {
        this.txtNomCony2 = txtNomCony2;
    }

    public RichInputText getTxtNomCony2() {
        return txtNomCony2;
    }

    public void setTxtNomCony1(RichInputText txtNomCony1) {
        this.txtNomCony1 = txtNomCony1;
    }

    public RichInputText getTxtNomCony1() {
        return txtNomCony1;
    }

    public void setTxtTelfRef(RichInputText txtTelfRef) {
        this.txtTelfRef = txtTelfRef;
    }

    public RichInputText getTxtTelfRef() {
        return txtTelfRef;
    }

    public void setTxtCorreo(RichInputText txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public RichInputText getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCelular(RichInputText txtCelular) {
        this.txtCelular = txtCelular;
    }

    public RichInputText getTxtCelular() {
        return txtCelular;
    }

    public void setTxtTelfCasa(RichInputText txtTelfCasa) {
        this.txtTelfCasa = txtTelfCasa;
    }

    public RichInputText getTxtTelfCasa() {
        return txtTelfCasa;
    }

    public void setTxtTelfLlam(RichInputText txtTelfLlam) {
        this.txtTelfLlam = txtTelfLlam;
    }

    public RichInputText getTxtTelfLlam() {
        return txtTelfLlam;
    }

    public void setTxtFecNac(RichInputDate txtFecNac) {
        this.txtFecNac = txtFecNac;
    }

    public RichInputDate getTxtFecNac() {
        return txtFecNac;
    }

    public void setTxtNomCli3(RichOutputText txtNomCli3) {
        this.txtNomCli3 = txtNomCli3;
    }

    public RichOutputText getTxtNomCli3() {
        return txtNomCli3;
    }

    public void setTxtDni(RichOutputText txtDni) {
        this.txtDni = txtDni;
    }

    public RichOutputText getTxtDni() {
        return txtDni;
    }

    public void setTxtMontoProd(RichOutputText txtMontoProd) {
        this.txtMontoProd = txtMontoProd;
    }

    public RichOutputText getTxtMontoProd() {
        return txtMontoProd;
    }

    public void setTxtProducto(RichOutputText txtProducto) {
        this.txtProducto = txtProducto;
    }

    public RichOutputText getTxtProducto() {
        return txtProducto;
    }

    public void setTxtNomCli2(RichOutputText txtNomCli2) {
        this.txtNomCli2 = txtNomCli2;
    }

    public RichOutputText getTxtNomCli2() {
        return txtNomCli2;
    }

    public void setTxtProm3(RichOutputLabel txtProm3) {
        this.txtProm3 = txtProm3;
    }

    public RichOutputLabel getTxtProm3() {
        return txtProm3;
    }

    public void setTxtProm2(RichOutputLabel txtProm2) {
        this.txtProm2 = txtProm2;
    }

    public RichOutputLabel getTxtProm2() {
        return txtProm2;
    }

    public void setTxtProm1(RichOutputLabel txtProm1) {
        this.txtProm1 = txtProm1;
    }

    public RichOutputLabel getTxtProm1() {
        return txtProm1;
    }

    public void setTxtNomTarjeta(RichOutputLabel txtNomTarjeta) {
        this.txtNomTarjeta = txtNomTarjeta;
    }

    public RichOutputLabel getTxtNomTarjeta() {
        return txtNomTarjeta;
    }

    public void setTxtMsjBienv(RichOutputText txtMsjBienv) {
        this.txtMsjBienv = txtMsjBienv;
    }

    public RichOutputText getTxtMsjBienv() {
        return txtMsjBienv;
    }

    public void setTxtNomVendedor(RichOutputText txtNomVendedor) {
        this.txtNomVendedor = txtNomVendedor;
    }

    public RichOutputText getTxtNomVendedor() {
        return txtNomVendedor;
    }

    public void setTxtNomCliente1(RichOutputText txtNomCliente1) {
        this.txtNomCliente1 = txtNomCliente1;
    }

    public RichOutputText getTxtNomCliente1() {
        return txtNomCliente1;
    }

    public void setCboCliPar(RichSelectOneChoice cboCliPar) {
        this.cboCliPar = cboCliPar;
    }

    public RichSelectOneChoice getCboCliPar() {
        return cboCliPar;
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

    public void setCboCeLaCa(RichSelectOneChoice cboCeLaCa) {
        this.cboCeLaCa = cboCeLaCa;
    }

    public RichSelectOneChoice getCboCeLaCa() {
        return cboCeLaCa;
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
        
        createInsertDom();
        
        createInsertEmp();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = ctx.getSessionScope().get("IDLEAD") == null ? "" : ctx.getSessionScope().get("IDLEAD").toString();
        
        Util.Log("idLead --> " + idLead);
        
        mostrarSpeechVenta(idLead);
        
    }

    public void cancelar(ActionEvent actionEvent) {
        
       ADFContext ctx = ADFContext.getCurrent();
        
        String userName = ctx.getSessionScope().get("userName") == null ? "" : ctx.getSessionScope().get("userName").toString();
        
        Util.redirec("SBP_SLS_VENTAS_X_EV_OBS?userName=" + userName);
        
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public void mostrarSpeechVenta(String idLead){    
       
        ADFContext ctx = ADFContext.getCurrent();
        
        ServiceBiPublisher bi = new ServiceBiPublisher();
        
        List<SpeechVentaBO> speechList = new ArrayList<SpeechVentaBO>();
        
        String nomProd = "";
        
        String idSpeech = "";

        speechList = bi.obtenerDatosSpeech(idLead);

        Util.Log("speechList tamaño --> " + speechList.size());
        
        ctx.getSessionScope().put("listaSpeech", speechList);
        
        for(SpeechVentaBO listSpeech : speechList){

            txtFecNac.setValue(listSpeech.getFecNacCli());
            txtTelfLlam.setValue(listSpeech.getTelfLlam());
            txtTelfCasa.setValue(listSpeech.getTelfCasa());
            txtCelular.setValue(listSpeech.getTelfCel());
            txtCorreo.setValue(listSpeech.getEmail());
            txtTelfRef.setValue(listSpeech.getTelfRef());
            txtParent.setValue(listSpeech.getParentDesc());
            txtEstCivil.setValue(listSpeech.getEstCivilDesc());
            txtNomCony1.setValue(listSpeech.getConyugeNombre1());
            txtNomCony2.setValue(listSpeech.getConyugeNombre2());
            txtApePatCony.setValue(listSpeech.getConyugeApePat());
            txtApeMatCony.setValue(listSpeech.getConyugeApeMat());
            txtFecNacCony.setValue(listSpeech.getConyugeFecNac());
            txtDniCony.setValue(listSpeech.getConyugeDni());
            txtGenCony.setValue(listSpeech.getConyugeGeneroDesc());
            cboCliGen.setValue(listSpeech.getGeneroDesc());
            txtDept.setValue(listSpeech.getDpto());
            txtProv.setValue(listSpeech.getProv());
            txtDist.setValue(listSpeech.getDistCli());
            txtDir.setValue(listSpeech.getDireccion());
            txtRef.setValue(listSpeech.getRefCli());
            txtCondLab.setValue(listSpeech.getCondLabCli());
            txtNomEmp.setValue(listSpeech.getCentLabNombre());
            txtRucEmp.setValue(listSpeech.getCentLabRuc());
            txtFecIng.setValue(listSpeech.getCentLabFecIngreso());
            txtDeptEmp.setValue(listSpeech.getCentLabDpto());
            txtProvEmp.setValue(listSpeech.getCentLabProv());
            txtDistEmp.setValue(listSpeech.getCentLabDist());
            txtDirEmp.setValue(listSpeech.getCentLabDireccion());
            txtRefEmp.setValue(listSpeech.getCentLabRef());
            txtTelfEmp.setValue(listSpeech.getCentLabTelf());
            txtAnexo.setValue(listSpeech.getCentLabAnexo());
            txtCargo.setValue(listSpeech.getCentLabCargoDesc());
            txtLugarEntrega.setValue(listSpeech.getLugEntrDesc());
            txtLugEntrDep.setValue(listSpeech.getLugEntDescDep());
            txtCicloFact.setValue(listSpeech.getCicloFactDesc());
            txtSup.setValue(listSpeech.getSupNombre());
            
            cboTransInternac.setValue(listSpeech.getTransInternac());
            cboTransInternet.setValue(listSpeech.getTransXInternet());
            cboEnvEstCta.setValue(listSpeech.getEnvEstCta());
            cboLugEnvEstCta.setValue(listSpeech.getLugEnvEstCta());
            
            if(listSpeech.getFlagAut().equalsIgnoreCase("Y")){
                txtLDPDD.setSelected(true);
            } else {
                txtLDPDD.setSelected(false);
            }   
            
            idSpeech = listSpeech.getIdSpeech();
            nomProd = listSpeech.getNomProd();
            
            ctx.getSessionScope().put("idSpeech", idSpeech);
            
            txtInfoLead.setValue(listSpeech.getNomCli() + " | " + listSpeech.getDniCliente() + " | " + listSpeech.getTelfLlam() + " | " +
                                 nomProd + " | " + listSpeech.getMtoProd());
                
        }


        try {
            
            mostrarSpeech(nomProd);
        
        } catch (JSchException e) {
            
            e.printStackTrace(System.out);
        
        } catch (SftpException e) {
        
            e.printStackTrace(System.out);
        
        }
        
    }
    
    public void mostrarSpeech(String nombreBin) throws JSchException, SftpException {
        
        Util.Log("nombreBin -- > " + nombreBin);

        String textoSpeech = speechProd(nombreBin);

        txtTextoSpeech.setValue(textoSpeech);
    }
    
    public String speechProd(String nomBin) throws JSchException,
                                                   SftpException {


        BufferedReader br = null;
        InputStream is = obtenerTxt(nomBin);
        StringBuilder sb = new StringBuilder();
        String content;
        try {
            br = new BufferedReader(new InputStreamReader(is,"latin1"));
            while ((content = br.readLine()) != null) {
                Util.Log("content --> " + content);
                sb.append(content + "\n");
            }
        } catch (IOException ioe) {
            System.out.println("IO Exception occurred");
            ioe.printStackTrace();
        }
        String texto = sb.toString();

        return texto;
    }
    
    public InputStream obtenerTxt(String nomBin) throws JSchException,
                                                        SftpException {

        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");

        JSch jsch = new JSch();
        Session session = null;

        session = jsch.getSession("ccmg-tVW", "sftp.us2.cloud.oracle.com", 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("Evol2018");

        session.setProxy(new ProxyHTTP(host, 80));
        System.out.println("1");
        session.connect();
        System.out.println("2");
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp)channel;

        InputStream stream =
            sftpChannel.get("/upload/ReadTxt/" + nomBin + ".txt");
        System.out.println("3");
        return stream;
    }
    
    public void setCboLuEnDe(RichSelectOneChoice cboLuEnDe) {
        this.cboLuEnDe = cboLuEnDe;
    }

    public RichSelectOneChoice getCboLuEnDe() {
        return cboLuEnDe;
    }

    public void setCboLuEnIn(RichSelectOneChoice cboLuEnIn) {
        this.cboLuEnIn = cboLuEnIn;
    }

    public RichSelectOneChoice getCboLuEnIn() {
        return cboLuEnIn;
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

    public void guardar(ActionEvent actionEvent) throws ParserConfigurationException,
                                                        SAXException,
                                                        IOException {
       
        String fechaNac = "";
        String telfLlamada = "";
        String telfCasa = "";
        String telfCel = "";
        String email = "";
        String telfRef = "";
        String parentesco = "";
        String estadoCivil = "";
        String genero = "";
        String apePatCony = "";
        String apeMatCony = "";
        String nombreCony1 = "";
        String nombreCony2 = "";
        String fecNacCony = "";
        String dniCony = "";
        String generoCony = "";
        String direccion = "";
        String dept = "";
        String prov = "";
        String dist = "";
        String ref = "";
        String condLab = "";
        String nomEmp = "";
        String  rucEmp = "";
        String fecIngCentLab = "";
        String  dirEmp = "";
        String deptEmp = "";
        String provEmp = "";
        String distEmp = "";
        String refEmp = "";
        String telfEmp = "";
        String anexoEmp = "";
        String cargoEmp = "";
        String lugarEntrega = "";
        String  lugarEntregaDep = "";
        String supNom = "";
        String cicloFact = "";
        String flagAut = "";
        String transInternac = "";
        String transInternet = "";
        String envEstCta = "";
        String lugEnvEstCta = "";
        //VNL
        String tipoDeViaDom = "-";
        String nombreDeViaDom = "-";
        String numeroDom = "-";
        String nroDptoDom = "-";
        String oficDom = "-";
        String pisoDom = "-";
        String manzanaDom = "-";
        String loteDom = "-";
        String interiorDom = "-";
        String sectorDom = "-";
        String kmDom = "-";
        String tipoZonaDom = "-";
        String nombreZonaDom = "-";
        String tipoDeViaLab = "-";
        String nombreDeViaLab = "-";
        String numeroLab = "-";
        String nroDptoLab = "-";
        String oficLab = "-";
        String pisoLab = "-";
        String manzanaLab = "-";
        String loteLab = "-";
        String interiorLab = "-";
        String sectorLab = "-";
        String kmLab = "-";
        String tipoZonaLab = "-";
        String nombreZonaLab = "-";
        
        if(txtFecNac.getValue() != null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            fechaNac = df.format(txtFecNac.getValue());
          
        } else {
            fechaNac = "-";
        }
        
        if(txtTelfLlam.getValue() != null){
          telfLlamada = txtTelfLlam.getValue().toString();
          
        } else {
            telfLlamada = "-";
        }
        
        if(txtTelfCasa.getValue() != null){
          telfCasa = txtTelfCasa.getValue().toString();
          
        } else {
            telfCasa = "-";
        }
        
        if(txtCelular.getValue() != null){
          telfCel = txtCelular.getValue().toString();
          
        } else {
            telfCel = "-";
        }
        
        if(txtCorreo.getValue() != null){
          email = txtCorreo.getValue().toString();
          
        } else {
            email = "-";
        }
        
        if(txtTelfRef.getValue() != null){
          telfRef = txtTelfRef.getValue().toString();
          
        } else {
            telfRef = "-";
        }
        
        if(txtParent.getValue() != null){
          parentesco = txtParent.getValue().toString();
          
        } else {
            parentesco = "-";
        }
        
        if(txtEstCivil.getValue() != null){
          estadoCivil = txtEstCivil.getValue().toString();
          
        } else {
            estadoCivil = "-";
        }
        
        if(cboCliGen.getValue() != null){
          genero = cboCliGen.getValue().toString();
          
        } else {
            genero = "-";
        }
        
        if(txtApePatCony.getValue() != null){
          apePatCony = txtApePatCony.getValue().toString();
          
        } else {
            apePatCony = "-";
        }
        
        if(txtApeMatCony.getValue() != null){
          apeMatCony = txtApeMatCony.getValue().toString();
          
        } else {
            apeMatCony = "-";
        }
        
        if(txtNomCony1.getValue() != null){
          nombreCony1 = txtNomCony1.getValue().toString();
          
        } else {
            nombreCony1 = "-";
        }
        
        if(txtNomCony2.getValue() != null){
          nombreCony2 = txtNomCony2.getValue().toString();
          
        } else {
            nombreCony2 = "-";
        }
        
        if(txtDniCony.getValue() != null){
          dniCony = txtDniCony.getValue().toString();
          
        } else {
            dniCony = "-";
        }
        
        if(txtFecNacCony.getValue() != null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            fecNacCony = df.format(txtFecNacCony.getValue());
          
        } else {
            fecNacCony = "-";
        }
        
        if(txtGenCony.getValue() != null){
          generoCony = txtGenCony.getValue().toString();
          
        } else {
            generoCony = "-";
        }
        
        if(txtDir.getValue() != null){
          direccion = txtDir.getValue().toString();
          
        } else {
            direccion = "-";
        }
        
        if(txtDept.getValue() != null){
          dept = txtDept.getValue().toString();
          
        } else {
            dept = "-";
        }
        
        if(txtProv.getValue() != null){
          prov = txtProv.getValue().toString();
          
        } else {
            prov = "-";
        }
        
        if(txtDist.getValue() != null){
          dist = txtDist.getValue().toString();
          
        } else {
            dist = "-";
        }
        
        if(txtRef.getValue() != null){
          ref = txtRef.getValue().toString();
          
        } else {
            ref = "-";
        }
        
        if(txtCondLab.getValue() != null){
          condLab = txtCondLab.getValue().toString();
          
        } else {
            condLab = "-";
        }
        
        if(txtNomEmp.getValue() != null){
          nomEmp = txtNomEmp.getValue().toString();
          
        } else {
            nomEmp = "-";
        }
        
        if(txtRucEmp.getValue() != null){
          rucEmp = txtRucEmp.getValue().toString();
          
        } else {
            rucEmp = "-";
        }
        
        if(txtFecIng.getValue() != null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            fecIngCentLab = df.format(txtFecIng.getValue());
          
        } else {
            fecIngCentLab = "-";
        }
        
        if(txtDirEmp.getValue() != null){
          dirEmp = txtDirEmp.getValue().toString();
          
        } else {
            dirEmp = "-";
        }
        
        if(txtDeptEmp.getValue() != null){
          deptEmp = txtDeptEmp.getValue().toString();
          
        } else {
            deptEmp = "-";
        }
        
        if(txtProvEmp.getValue() != null){
          provEmp = txtProvEmp.getValue().toString();
          Util.Log("prov Emp --> " + provEmp);
          
        } else {
            provEmp = "-";
        }
        
        if(txtDistEmp.getValue() != null){
          distEmp = txtDistEmp.getValue().toString();
          
        } else {
            distEmp = "-";
        }
        
        if(txtRefEmp.getValue() != null){
          refEmp = txtRefEmp.getValue().toString();
          
        } else {
            refEmp = "-";
        }
        
        if(txtTelfEmp.getValue() != null){
          telfEmp = txtTelfEmp.getValue().toString();
          
        } else {
            telfEmp = "-";
        }
        
        if(txtAnexo.getValue() != null){
          anexoEmp = txtAnexo.getValue().toString();
          
        } else {
            anexoEmp = "-";
        }
        
        if(txtCargo.getValue() != null){
          cargoEmp = txtCargo.getValue().toString();
          
        } else {
            cargoEmp = "-";
        }
        
        if(txtLugarEntrega.getValue() != null){
          lugarEntrega = txtLugarEntrega.getValue().toString();
          
        } else {
            lugarEntrega = "-";
        }
        
        if(txtLugEntrDep.getValue() != null){
          lugarEntregaDep = txtLugEntrDep.getValue().toString();
          
        } else {
            lugarEntregaDep = "-";
        }
        
        if(txtSup.getValue() != null){
          supNom = txtSup.getValue().toString();
          
        } else {
            supNom = "-";
        }
        
        if(txtCicloFact.getValue() != null){
          cicloFact = txtCicloFact.getValue().toString();
          
        } else {
            cicloFact = "-";
        }
        
        if(txtLDPDD.getValue() != null){
          flagAut = txtLDPDD.getValue().toString();
          
        } else {
            flagAut = "N";
        }
        
        if(cboTransInternac.getValue() != null){
            
            transInternac = cboTransInternac.getValue().toString();
            
        } else {
            
            transInternac = "-";
                
        }
        
        if(cboTransInternet.getValue() != null){
            
            transInternet = cboTransInternet.getValue().toString();
            
        } else {
            
            transInternet = "-";
                
        }
        
        if(cboEnvEstCta.getValue() != null){
            
            envEstCta = cboEnvEstCta.getValue().toString();
            
        } else {
            
            envEstCta = "-";
                
        }
        
        if(cboLugEnvEstCta.getValue() != null){
            
            lugEnvEstCta = cboLugEnvEstCta.getValue().toString();
            
        } else {
            
            lugEnvEstCta = "-";
                
        }
        //VNL
        tipoDeViaDom = validarNullCamposDirec(txtTipoViaDom);
        nombreDeViaDom = validarNullCamposDirec(txtNombViaDom);
        numeroDom = validarNullCamposDirec(txtNumDom);
        nroDptoDom = validarNullCamposDirec(txtNroDptoDom);
        oficDom = validarNullCamposDirec(txtOficDom);
        pisoDom = validarNullCamposDirec(txtPisoDom);
        manzanaDom = validarNullCamposDirec(txtManzDom);
        loteDom = validarNullCamposDirec(txtLoteDom);
        interiorDom = validarNullCamposDirec(txtInteriorDom);
        sectorDom = validarNullCamposDirec(txtSectorDom);
        kmDom = validarNullCamposDirec(txtKmDom);
        tipoZonaDom = validarNullCamposDirec(txtTipZonaDom);
        nombreZonaDom = validarNullCamposDirec(txtNombZonaDom);
        //VNL
        tipoDeViaLab = validarNullCamposDirec(txtTipoViaLab);
        nombreDeViaLab = validarNullCamposDirec(txtNombViaLab);
        numeroLab = validarNullCamposDirec(txtNumLab);
        nroDptoLab = validarNullCamposDirec(txtNroDptoLab);
        oficLab = validarNullCamposDirec(txtOficLab);
        pisoLab = validarNullCamposDirec(txtPisoLab);
        manzanaLab = validarNullCamposDirec(txtManzLab);
        loteLab = validarNullCamposDirec(txtLoteLab);
        interiorLab = validarNullCamposDirec(txtInteriorLab);
        sectorLab = validarNullCamposDirec(txtSectorLab);
        kmLab = validarNullCamposDirec(txtKmLab);
        tipoZonaLab = validarNullCamposDirec(txtTipZonaLab);
        nombreZonaLab = validarNullCamposDirec(txtNombZonaLab);
        
        ADFContext ctx = ADFContext.getCurrent();
        
        List<SpeechVentaBO> l = new ArrayList<SpeechVentaBO>();
        
        l = (List<SpeechVentaBO>)ctx.getSessionScope().get("listaSpeech");
        
        String idLead = ctx.getSessionScope().get("IDLEAD") == null ? "" : ctx.getSessionScope().get("IDLEAD").toString();
        
        String idSpeech = ctx.getSessionScope().get("idSpeech") == null ? "" : ctx.getSessionScope().get("idSpeech").toString();
        
        String userName = ctx.getSessionScope().get("userName") == null ? "" : ctx.getSessionScope().get("userName").toString();
        
        String nomContacto = l.get(0).getNomCli().replace(",", "");
        
        ctx.getSessionScope().put("nomContacto", nomContacto);
        
        Util.Log("userName SPEECH --> " + userName);
        
        MergeLeadInvoke flagSpeech = new MergeLeadInvoke();
        
        flagSpeech.mergeLeadSpeech(Long.parseLong(idLead), Long.parseLong(idSpeech), "1", "",
                                   fechaNac,  telfLlamada, telfCasa,  telfCel,  email,   telfRef, parentesco, estadoCivil,
                                   genero, apePatCony, apeMatCony, nombreCony1, nombreCony2,
                                   fecNacCony, dniCony,  generoCony, direccion,  dept, prov, dist, ref,
                                   condLab, nomEmp,   rucEmp, fecIngCentLab, dirEmp, deptEmp,
                                   provEmp, distEmp, refEmp, telfEmp, anexoEmp, cargoEmp,
                                   lugarEntrega, lugarEntregaDep, supNom, cicloFact, flagAut, transInternac, transInternet,
                                   envEstCta, lugEnvEstCta,
                                    tipoDeViaDom, nombreDeViaDom, numeroDom, nroDptoDom,
                                    oficDom, pisoDom, manzanaDom, loteDom,
                                    interiorDom, sectorDom, kmDom, tipoZonaDom,
                                    nombreZonaDom,
                                    tipoDeViaLab, nombreDeViaLab, numeroLab, nroDptoLab,
                                    oficLab, pisoLab, manzanaLab, loteLab,
                                    interiorLab, sectorLab, kmLab, tipoZonaLab,
                                    nombreZonaLab);
        
        generarTxt(l.get(0).getIdSpeech(), l.get(0).getFecNacCli(), l.get(0).getTelfLlam(), l.get(0).getTelfCel(), l.get(0).getTelfCasa(), l.get(0).getEmail(), l.get(0).getTelfRef(), l.get(0).getParentDesc(),
                   l.get(0).getParentDesc(), l.get(0).getEstCivilDesc(), l.get(0).getEstCivilDesc(), l.get(0).getGeneroDesc(), l.get(0).getGeneroDesc(), l.get(0).getConyugeApePat(), l.get(0).getConyugeApeMat(),
                   l.get(0).getConyugeNombre1(), l.get(0).getConyugeNombre2(), l.get(0).getConyugeFecNac(), l.get(0).getCentLabFecIngreso(), l.get(0).getConyugeDni(), l.get(0).getConyugeGeneroDesc(),
                   l.get(0).getConyugeGeneroDesc(), l.get(0).getDireccion(), l.get(0).getDpto(), l.get(0).getProv(), l.get(0).getDistCli(), l.get(0).getRefCli(), l.get(0).getCondLabCli(), l.get(0).getCondLabCli(),
                   l.get(0).getCentLabNombre(), l.get(0).getCentLabRuc(), l.get(0).getCentLabDireccion(), l.get(0).getCentLabDpto(), l.get(0).getCentLabProv(), l.get(0).getCentLabDist(), l.get(0).getCentLabRef(),
                   l.get(0).getCentLabTelf(), l.get(0).getCentLabAnexo(), l.get(0).getCentLabCargoDesc(), l.get(0).getCentLabCargoDesc(), l.get(0).getLugEntrDesc(), l.get(0).getLugEntrDesc(), l.get(0).getLugEntDescDep(),
                   l.get(0).getLugEntDescDep(), l.get(0).getValorMembresia(), l.get(0).getValorDesgravamen(), l.get(0).getCicloFactDesc(), l.get(0).getValorEnvEstCta(), l.get(0).getFlagAut(), "0");
        
        Util.redirec("SBP_SLS_VENTAS_X_EV_OBS?userName=" + userName);
       
    }
    
    public String validarNullCamposDirec(RichInputText campo) {
        if(campo.getValue() != null){
            return campo.getValue().toString();
        }else{
            return "-";
        }
    }
    
    public void generarTxt(String codigospeech, String fecnaccli,
                           String telfllam, String celular, String telfcasa,
                           String email, String telfref, String codparentesco,
                           String descparentesco, String codestcivil,
                           String descestcivil, String codgenero,
                           String descgenero, String appatcony,
                           String apmatcont, String nomcony, String nom2cony,
                           String fecnaccony, String fecingcentlab,
                           String doccony, String codgencony,
                           String descgencony, String direccion, String dept,
                           String prov, String distrito, String refdir,
                           String codcondlab, String condlabdesc,
                           String nomcentlab, String ruccentlab,
                           String dircentlab, String deptcentlab,
                           String provcentlab, String distcentlab,
                           String refcentlab, String telfcentlab,
                           String anexocentlab, String centlabcodcar,
                           String centlabdesccar, String lugenttarjdep,
                           String lugenttarjdepcod, String lugenttarjindepdesc,
                           String lugenttarjindepcod, String valormemb,
                           String valordesgravamen, String ciclofact,
                           String envioestcta, String flagaut,
                           String flagdispefect) {
        try {

            ADFContext ctx = ADFContext.getCurrent();

            String nomContacto =
                ctx.getSessionScope().get("nomContacto") == null ? "" :
                ctx.getSessionScope().get("nomContacto").toString();

            //FECHA
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Util.Log(dateFormat.format(date) + "DATE");

            String fecha = dateFormat.format(date);
            
            //HORA FEEDBACK
            
            final Date currentTimeHoraGest = new Date();

            final SimpleDateFormat sdfHoraGest = new SimpleDateFormat("HH");

            // Give it to me in GMT time.
            sdfHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String horaActual = sdfHoraGest.format(currentTimeHoraGest);
            Util.Log("Hora Feedback --> " + horaActual);
            
            //MINUTO
            
            final Date currentTimeMinGest = new Date();

            final SimpleDateFormat sdfMinGest = new SimpleDateFormat("mm");

            // Give it to me in GMT time.
            sdfHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String minuto = sdfMinGest.format(currentTimeMinGest);
            Util.Log("Hora Feedback --> " + minuto);
            
            //SEGUNDO
            final Date currentTimeSegGest = new Date();

            final SimpleDateFormat sdfSegGest = new SimpleDateFormat("ss");

            // Give it to me in GMT time.
            sdfHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String segundo = sdfSegGest.format(currentTimeSegGest);
            Util.Log("Hora Feedback --> " + segundo);

            String host = System.getProperty("http.proxyHost");

            JSch jsch = new JSch();

            Session session = null;
            
            Util.Log("inicio usuario");
            
            //SFTP SBP

       /*     session = jsch.getSession("usftp311", "190.116.5.24", 22);

            session.setConfig("StrictHostKeyChecking", "no");

            session.setPassword("uwy2FUmt");*/
            
            //SFTP CLOUD SBP
            
            session = jsch.getSession("ccmg-tVW", "sftp.us2.cloud.oracle.com", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("Evol2018");
            
            Util.Log("fin usuario");

            session.setProxy(new ProxyHTTP(host, 80));
            
            Util.Log("inicia conexión");

            session.connect();
            
            Util.Log("termina conexión");

            Channel channel = session.openChannel("sftp");
            
            channel.connect();
            
            ChannelSftp sftpChannel = (ChannelSftp)channel;

            //RUTA SBP
           //     sftpChannel.cd("/nfshome/sbpftp04/usftp311/");
            
            //RUTA CLOUD
           sftpChannel.cd("/upload/ReadTxt/");

            String nomFile = "Validacion-" + nomContacto + "-" + fecha + "-" + horaActual + "-" + minuto + "-" + segundo;
            Util.Log(nomFile + "nomFile");

            String speechVenta = "codigospeech , fecnaccli , telfllam ,celular , telfcasa , email, telfref, codparentesco, descparentesco, codestcivil, descestcivil, codgenero, descgenero, appatcony, apmatcont, nomcony , nom2cony, fecnaccony, fecingcentlab , doccony, codgencony, descgencony, direccion, dept, prov, distrito, refdir, codcondlab, condlabdesc, nomcentlab, ruccentlab, dircentlab ,deptcentlab, provcentlab, distcentlab, refcentlab, telfcentlab, anexocentlab ,centlabcodcar, centlabdesccar, lugenttarjdep, lugenttarjdepcod, lugenttarjindepdesc, lugenttarjindepcod, valormemb , valordesgravamen, ciclofact , envioestcta , flagaut, flagdispefect" +
                "\n" + codigospeech + "," + fecnaccli + "," + telfllam + "," +
                celular + "," + telfcasa + "," + email + "," + telfref + "," +
                codparentesco + "," + descparentesco + "," + codestcivil +
                "," + descestcivil + "," + codgenero + "," + descgenero + "," +
                appatcony + "," + apmatcont + "," + nomcony + "," + nom2cony +
                "," + fecnaccony + "," + fecingcentlab + "," + doccony + "," +
                codgencony + "," + descgencony + "," + direccion + "," + dept +
                "," + prov + "," + distrito + "," + refdir + "," + codcondlab +
                "," + condlabdesc + "," + nomcentlab + "," + ruccentlab + "," +
                dircentlab + "," + deptcentlab + "," + provcentlab + "," +
                distcentlab + "," + refcentlab + "," + telfcentlab + "," +
                anexocentlab + "," + centlabcodcar + "," + centlabdesccar +
                "," + lugenttarjdep + "," + lugenttarjdepcod + "," +
                lugenttarjindepdesc + "," + lugenttarjindepcod + "," +
                valormemb + "," + valordesgravamen + "," + ciclofact + "," +
                envioestcta + "," + flagaut + "," + flagdispefect;
            Util.Log(speechVenta);

            InputStream inputStream =
                new ByteArrayInputStream(speechVenta.getBytes());

            sftpChannel.put(inputStream, nomFile + ".csv");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTxtCodDept(RichInputText txtCodDept) {
        this.txtCodDept = txtCodDept;
    }

    public RichInputText getTxtCodDept() {
        return txtCodDept;
    }


    public void setTxtDept(RichSelectOneChoice txtDept) {
        this.txtDept = txtDept;
    }

    public RichSelectOneChoice getTxtDept() {
        return txtDept;
    }

    public void setTxtProv(RichSelectOneChoice txtProv) {
        this.txtProv = txtProv;
    }

    public RichSelectOneChoice getTxtProv() {
        return txtProv;
    }

    public void setTxtDist(RichSelectOneChoice txtDist) {
        this.txtDist = txtDist;
    }

    public RichSelectOneChoice getTxtDist() {
        return txtDist;
    }

      public void setTxtTextoSpeech(RichInputText txtTextoSpeech) {
        this.txtTextoSpeech = txtTextoSpeech;
    }

    public RichInputText getTxtTextoSpeech() {
        return txtTextoSpeech;
    }

    public void setTxtLugEntrDep(RichSelectOneChoice txtLugEntrDep) {
        this.txtLugEntrDep = txtLugEntrDep;
    }

    public RichSelectOneChoice getTxtLugEntrDep() {
        return txtLugEntrDep;
    }

    public void setTxtLugarEntrega(RichSelectOneChoice txtLugarEntrega) {
        this.txtLugarEntrega = txtLugarEntrega;
    }

    public RichSelectOneChoice getTxtLugarEntrega() {
        return txtLugarEntrega;
    }

    public void setTxtCargo(RichInputText txtCargo) {
        this.txtCargo = txtCargo;
    }

    public RichInputText getTxtCargo() {
        return txtCargo;
    }

    public void setTxtGenCony(RichSelectOneChoice txtGenCony) {
        this.txtGenCony = txtGenCony;
    }

    public RichSelectOneChoice getTxtGenCony() {
        return txtGenCony;
    }

    public void setTxtEstCivil(RichSelectOneChoice txtEstCivil) {
        this.txtEstCivil = txtEstCivil;
    }

    public RichSelectOneChoice getTxtEstCivil() {
        return txtEstCivil;
    }

    public void setTxtParent(RichSelectOneChoice txtParent) {
        this.txtParent = txtParent;
    }

    public RichSelectOneChoice getTxtParent() {
        return txtParent;
    }

    public void setTxtInfoLead(RichOutputText txtInfoLead) {
        this.txtInfoLead = txtInfoLead;
    }

    public RichOutputText getTxtInfoLead() {
        return txtInfoLead;
    }

    public void setTxtSup(RichInputText txtSup) {
        this.txtSup = txtSup;
    }

    public RichInputText getTxtSup() {
        return txtSup;
    }

    public void setTxtCicloFact(RichSelectOneChoice txtCicloFact) {
        this.txtCicloFact = txtCicloFact;
    }

    public RichSelectOneChoice getTxtCicloFact() {
        return txtCicloFact;
    }

    public String createInsertDom() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsertEmp() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setTxtCondLab(RichSelectOneChoice txtCondLab) {
        this.txtCondLab = txtCondLab;
    }

    public RichSelectOneChoice getTxtCondLab() {
        return txtCondLab;
    }

    public void setCboTransInternac(RichSelectOneChoice cboTransInternac) {
        this.cboTransInternac = cboTransInternac;
    }

    public RichSelectOneChoice getCboTransInternac() {
        return cboTransInternac;
    }

    public void setCboTransInternet(RichSelectOneChoice cboTransInternet) {
        this.cboTransInternet = cboTransInternet;
    }

    public RichSelectOneChoice getCboTransInternet() {
        return cboTransInternet;
    }

    public void setCboEnvEstCta(RichSelectOneChoice cboEnvEstCta) {
        this.cboEnvEstCta = cboEnvEstCta;
    }

    public RichSelectOneChoice getCboEnvEstCta() {
        return cboEnvEstCta;
    }

    public void setCboLugEnvEstCta(RichSelectOneChoice cboLugEnvEstCta) {
        this.cboLugEnvEstCta = cboLugEnvEstCta;
    }

    public RichSelectOneChoice getCboLugEnvEstCta() {
        return cboLugEnvEstCta;
    }

    public void mostrarDirecDom(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popUpDirecDom.show(hints);
    }

    public void mostrarDirecLab(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popUpDirecLab.show(hints);
    }

    public void setPopUpDirecDom(RichPopup popUpDirecDom) {
        this.popUpDirecDom = popUpDirecDom;
    }

    public RichPopup getPopUpDirecDom() {
        return popUpDirecDom;
    }

    public void setPopUpDirecLab(RichPopup popUpDirecLab) {
        this.popUpDirecLab = popUpDirecLab;
    }

    public RichPopup getPopUpDirecLab() {
        return popUpDirecLab;
    }

    public void cargarDirecLab(ActionEvent actionEvent) {
        String tipoDeViaLab="";
        String nombreDeViaLab="";
        String numeroLab="";
        String nroDptoLab="";
        String oficLab="";
        String pisoLab="";
        String manzanaLab="";
        String loteLab="";
        String interiorLab="";
        String sectorLab="";
        String kmLab="";
        String tipoZonaLab="";
        String nombreZonaLab="";

        //VNL
        if(txtTipoViaLab.getValue() == null){
            txtTipoViaLab.setValue("");
        }else{
            tipoDeViaLab = txtTipoViaLab.getValue().toString()+" ";
        }
        if(txtNombViaLab.getValue() == null){
            txtNombViaLab.setValue("");
        }else{
            nombreDeViaLab = txtNombViaLab.getValue().toString()+" ";
        }
        if(txtNumLab.getValue() == null){
            txtNumLab.setValue("");
        }else{
            numeroLab = txtNumLab.getValue().toString()+" ";
        }
        if(txtNroDptoLab.getValue() == null){
            txtNroDptoLab.setValue("");
        }else{
            nroDptoLab = txtNroDptoLab.getValue().toString()+" ";
        }
        if(txtOficLab.getValue() == null){
            txtOficLab.setValue("");
        }else{
            oficLab = txtOficLab.getValue().toString()+" ";
        }
        if(txtPisoLab.getValue() == null){
            txtPisoLab.setValue("");
        }else{
            pisoLab = txtPisoLab.getValue().toString()+" ";
        }
        if(txtManzLab.getValue() == null){
            txtManzLab.setValue("");
        }else{
            manzanaLab = txtManzLab.getValue().toString()+" ";
        }
        if(txtLoteLab.getValue() == null){
            txtLoteLab.setValue("");
        }else{
            loteLab = txtLoteLab.getValue().toString()+" ";
        }
        if(txtInteriorLab.getValue() == null){
            txtInteriorLab.setValue("");
        }else{
            interiorLab = txtInteriorLab.getValue().toString()+" ";
        }
        if(txtSectorLab.getValue() == null){
            txtSectorLab.setValue("");
        }else{
            sectorLab = txtSectorLab.getValue().toString()+" ";
        }
        if(txtKmLab.getValue() == null){
            txtKmLab.setValue("");
        }else{
            kmLab = txtKmLab.getValue().toString()+" ";
        }
        if(txtTipZonaLab.getValue() == null){
            txtTipZonaLab.setValue("");
        }else{
            tipoZonaLab = txtTipZonaLab.getValue().toString()+" ";
        }
        if(txtNombZonaLab.getValue() == null){
            txtNombZonaLab.setValue("");
        }else{
            nombreZonaLab = txtNombZonaLab.getValue().toString()+" ";
        }
        
        ADFContext ctx = ADFContext.getCurrent();
        ctx.getSessionScope().put("dirEmp", txtDirEmp.getValue());
        String dir = ctx.getSessionScope().get("dirEmp") == null ? "" : ctx.getSessionScope().get("dirEmp").toString();

        if(dir.equals("")){
            txtDirEmp.setValue(tipoDeViaLab+
                                nombreDeViaLab+
                                numeroLab+
                                nroDptoLab+
                                oficLab+
                                pisoLab+
                                manzanaLab+
                                loteLab+
                                interiorLab+
                                sectorLab+
                                kmLab+
                                tipoZonaLab+
                                nombreZonaLab);
        }
        
        popUpDirecLab.hide();
    }

    public void cargarDirecDom(ActionEvent actionEvent) {
        
        String tipoDeViaDom="";
        String nombreDeViaDom="";
        String numeroDom="";
        String nroDptoDom="";
        String oficDom="";
        String pisoDom="";
        String manzanaDom="";
        String loteDom="";
        String interiorDom="";
        String sectorDom="";
        String kmDom="";
        String tipoZonaDom="";
        String nombreZonaDom="";
        
        //VNL
        if(txtTipoViaDom.getValue() == null){
            txtTipoViaDom.setValue("");
        }else{
            tipoDeViaDom = txtTipoViaDom.getValue().toString()+" ";
        }
        if(txtNombViaDom.getValue() == null){
            txtNombViaDom.setValue("");
        }else{
            nombreDeViaDom = txtNombViaDom.getValue().toString()+" ";
        }
        if(txtNumDom.getValue() == null){
            txtNumDom.setValue("");
        }else{
            numeroDom = txtNumDom.getValue().toString()+" ";
        }
        if(txtNroDptoDom.getValue() == null){
            txtNroDptoDom.setValue("");
        }else{
            nroDptoDom = txtNroDptoDom.getValue().toString()+" ";
        }
        if(txtOficDom.getValue() == null){
            txtOficDom.setValue("");
        }else{
            oficDom = txtOficDom.getValue().toString()+" ";
        }
        if(txtPisoDom.getValue() == null){
            txtPisoDom.setValue("");
        }else{
            pisoDom = txtPisoDom.getValue().toString()+" ";
        }
        if(txtManzDom.getValue() == null){
            txtManzDom.setValue("");
        }else{
            manzanaDom = txtManzDom.getValue().toString()+" ";
        }
        if(txtLoteDom.getValue() == null){
            txtLoteDom.setValue("");
        }else{
            loteDom = txtLoteDom.getValue().toString()+" ";
        }
        if(txtInteriorDom.getValue() == null){
            txtInteriorDom.setValue("");
        }else{
            interiorDom = txtInteriorDom.getValue().toString()+" ";
        }
        if(txtSectorDom.getValue() == null){
            txtSectorDom.setValue("");
        }else{
            sectorDom = txtSectorDom.getValue().toString()+" ";
        }
        if(txtKmDom.getValue() == null){
            txtKmDom.setValue("");
        }else{
            kmDom = txtKmDom.getValue().toString()+" ";
        }
        if(txtTipZonaDom.getValue() == null){
            txtTipZonaDom.setValue("");
        }else{
            tipoZonaDom = txtTipZonaDom.getValue().toString()+" ";
        }
        if(txtNombZonaDom.getValue() == null){
            txtNombZonaDom.setValue("");
        }else{
            nombreZonaDom = txtNombZonaDom.getValue().toString();
        }
                            
        ADFContext ctx = ADFContext.getCurrent();
        ctx.getSessionScope().put("dirEmp", txtDir.getValue());
        String dir = ctx.getSessionScope().get("dirEmp") == null ? "" : ctx.getSessionScope().get("dirEmp").toString();

        if(dir.equals("")){
            txtDir.setValue(tipoDeViaDom+
                            nombreDeViaDom+
                            numeroDom+
                            nroDptoDom+
                            oficDom+
                            pisoDom+
                            manzanaDom+
                            loteDom+
                            interiorDom+
                            sectorDom+
                            kmDom+
                            tipoZonaDom+
                            nombreZonaDom);
        }
        popUpDirecDom.hide();
    }

    public void setTxtTipoViaDom(RichInputText txtTipoViaDom) {
        this.txtTipoViaDom = txtTipoViaDom;
    }

    public RichInputText getTxtTipoViaDom() {
        return txtTipoViaDom;
    }

    public void setTxtNombViaDom(RichInputText txtNombViaDom) {
        this.txtNombViaDom = txtNombViaDom;
    }

    public RichInputText getTxtNombViaDom() {
        return txtNombViaDom;
    }

    public void setTxtNumDom(RichInputText txtNumDom) {
        this.txtNumDom = txtNumDom;
    }

    public RichInputText getTxtNumDom() {
        return txtNumDom;
    }

    public void setTxtNroDptoDom(RichInputText txtNroDptoDom) {
        this.txtNroDptoDom = txtNroDptoDom;
    }

    public RichInputText getTxtNroDptoDom() {
        return txtNroDptoDom;
    }

    public void setTxtOficDom(RichInputText txtOficDom) {
        this.txtOficDom = txtOficDom;
    }

    public RichInputText getTxtOficDom() {
        return txtOficDom;
    }

    public void setTxtPisoDom(RichInputText txtPisoDom) {
        this.txtPisoDom = txtPisoDom;
    }

    public RichInputText getTxtPisoDom() {
        return txtPisoDom;
    }

    public void setTxtManzDom(RichInputText txtManzDom) {
        this.txtManzDom = txtManzDom;
    }

    public RichInputText getTxtManzDom() {
        return txtManzDom;
    }

    public void setTxtLoteDom(RichInputText txtLoteDom) {
        this.txtLoteDom = txtLoteDom;
    }

    public RichInputText getTxtLoteDom() {
        return txtLoteDom;
    }

    public void setTxtInteriorDom(RichInputText txtInteriorDom) {
        this.txtInteriorDom = txtInteriorDom;
    }

    public RichInputText getTxtInteriorDom() {
        return txtInteriorDom;
    }

    public void setTxtSectorDom(RichInputText txtSectorDom) {
        this.txtSectorDom = txtSectorDom;
    }

    public RichInputText getTxtSectorDom() {
        return txtSectorDom;
    }

    public void setTxtKmDom(RichInputText txtKmDom) {
        this.txtKmDom = txtKmDom;
    }

    public RichInputText getTxtKmDom() {
        return txtKmDom;
    }

    public void setTxtTipZonaDom(RichInputText txtTipZonaDom) {
        this.txtTipZonaDom = txtTipZonaDom;
    }

    public RichInputText getTxtTipZonaDom() {
        return txtTipZonaDom;
    }

    public void setTxtNombZonaDom(RichInputText txtNombZonaDom) {
        this.txtNombZonaDom = txtNombZonaDom;
    }

    public RichInputText getTxtNombZonaDom() {
        return txtNombZonaDom;
    }

    public void setTxtTipoViaLab(RichInputText txtTipoViaLab) {
        this.txtTipoViaLab = txtTipoViaLab;
    }

    public RichInputText getTxtTipoViaLab() {
        return txtTipoViaLab;
    }

    public void setTxtNombViaLab(RichInputText txtNombViaLab) {
        this.txtNombViaLab = txtNombViaLab;
    }

    public RichInputText getTxtNombViaLab() {
        return txtNombViaLab;
    }

    public void setTxtNumLab(RichInputText txtNumLab) {
        this.txtNumLab = txtNumLab;
    }

    public RichInputText getTxtNumLab() {
        return txtNumLab;
    }

    public void setTxtNroDptoLab(RichInputText txtNroDptoLab) {
        this.txtNroDptoLab = txtNroDptoLab;
    }

    public RichInputText getTxtNroDptoLab() {
        return txtNroDptoLab;
    }

    public void setTxtOficLab(RichInputText txtOficLab) {
        this.txtOficLab = txtOficLab;
    }

    public RichInputText getTxtOficLab() {
        return txtOficLab;
    }

    public void setTxtPisoLab(RichInputText txtPisoLab) {
        this.txtPisoLab = txtPisoLab;
    }

    public RichInputText getTxtPisoLab() {
        return txtPisoLab;
    }

    public void setTxtManzLab(RichInputText txtManzLab) {
        this.txtManzLab = txtManzLab;
    }

    public RichInputText getTxtManzLab() {
        return txtManzLab;
    }

    public void setTxtLoteLab(RichInputText txtLoteLab) {
        this.txtLoteLab = txtLoteLab;
    }

    public RichInputText getTxtLoteLab() {
        return txtLoteLab;
    }

    public void setTxtInteriorLab(RichInputText txtInteriorLab) {
        this.txtInteriorLab = txtInteriorLab;
    }

    public RichInputText getTxtInteriorLab() {
        return txtInteriorLab;
    }

    public void setTxtSectorLab(RichInputText txtSectorLab) {
        this.txtSectorLab = txtSectorLab;
    }

    public RichInputText getTxtSectorLab() {
        return txtSectorLab;
    }

    public void setTxtKmLab(RichInputText txtKmLab) {
        this.txtKmLab = txtKmLab;
    }

    public RichInputText getTxtKmLab() {
        return txtKmLab;
    }

    public void setTxtTipZonaLab(RichInputText txtTipZonaLab) {
        this.txtTipZonaLab = txtTipZonaLab;
    }

    public RichInputText getTxtTipZonaLab() {
        return txtTipZonaLab;
    }

    public void setTxtNombZonaLab(RichInputText txtNombZonaLab) {
        this.txtNombZonaLab = txtNombZonaLab;
    }

    public RichInputText getTxtNombZonaLab() {
        return txtNombZonaLab;
    }
}

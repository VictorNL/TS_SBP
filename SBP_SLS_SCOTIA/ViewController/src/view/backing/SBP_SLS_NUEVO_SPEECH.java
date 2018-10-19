package view.backing;

import Client.MergeLeadClient;

import Dao.Conexion;
import Dao.Proceso;
import Dao.SpeechVenta;

import WsEjRep.ServiceBiPublisher;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import dao.CpgLeadContAccProdFb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.fragment.RichPageTemplate;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.commons.dbutils.DbUtils;

import view.Util;

public class SBP_SLS_NUEVO_SPEECH {
    private RichPageTemplate pt1;
    private RichForm f1;
    private RichDocument d1;
    private RichOutputText txtInfoLead;
    private RichInputText txtTextoSpeech1;
    private RichInputText txtTelfLlam;
    private RichInputText txtTelfCasa;
    private RichInputText txtCelular;
    private RichInputText txtCorreo;
    private RichInputText txtTelfRef;
    private RichSelectOneChoice txtParent;
    private RichSelectOneChoice cboEstCiv;
    private RichSelectOneChoice cboCliGen;
    private RichInputDate txtFecNac;
    private RichInputText txtNomCony1;
    private RichInputText txtNomCony2;
    private RichInputText txtApePatCony;
    private RichInputText txtApeMatCony;
    private RichInputDate txtFecNacCony;
    private RichInputText txtDniCony;
    private RichSelectOneChoice cboCoGene;
    private RichInputText txtCodDist;
    private RichSelectOneChoice txtDist;
    private RichInputText txtCodProv;
    private RichSelectOneChoice txtProv;
    private RichInputText txtCodDept;
    private RichSelectOneChoice txtDept;
    private RichInputText txtDir;
    private RichInputText txtRef;
    private RichSelectOneChoice cboConLab;
    private RichSelectOneChoice txtDeptEmp;
    private RichSelectOneChoice txtProvEmp;
    private RichSelectOneChoice txtDistEmp;
    private RichInputText txtNomEmp;
    private RichInputText txtRucEmp;
    private RichInputDate txtFecIng;
    private RichInputText txtDirEmp;
    private RichInputText txtRefEmp;
    private RichInputText txtTelfEmp;
    private RichInputText txtAnexo;
    private RichSelectOneChoice cboCeLaCa;
    private RichSelectOneChoice cboLuEnIn;
    private RichInputText txtNomCli4;
    private RichInputText txtNomCli5;
    private RichSelectOneChoice cboLuEnDe;
    private RichOutputText txtNomCli6;
    private RichOutputText txtTea;
    private RichOutputText txtTcea;
    private RichInputText txtMembAnual;
    private RichInputText txtDesgravamen;
    private RichOutputText txtEstadoCta2;
    private RichOutputText txtEstadoCuenta;
    private RichSelectOneChoice cboCicFac;
    private RichOutputText txtNomCli7;
    private RichSelectBooleanCheckbox txtLDPDD;
    private RichSelectBooleanCheckbox txtCheckFlaDis;
    private RichInputText txtDNI;
    private RichInputText txtNomCli3;
    private RichInputText txtSupe;
    private RichPanelGroupLayout pnlTomaDatosSpeech;
    private RichInputText txtTextoSpeech2;

    public void setPt1(RichPageTemplate pt1) {
        this.pt1 = pt1;
    }

    public RichPageTemplate getPt1() {
        return pt1;
    }

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

    public void setTxtInfoLead(RichOutputText txtInfoLead) {
        this.txtInfoLead = txtInfoLead;
    }

    public RichOutputText getTxtInfoLead() {
        return txtInfoLead;
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
    
    public void onLoad(){
        
        createInsertDeptProvDistCli();
        
        createInsertDeptProvDistEmp();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String nomContacto = ctx.getSessionScope().get("nomContacto") == null ? "" : ctx.getSessionScope().get("nomContacto").toString();
        String monto = ctx.getSessionScope().get("monto") == null ? "" : ctx.getSessionScope().get("monto").toString();
        String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        String dni = ctx.getSessionScope().get("dni") == null ? "" : ctx.getSessionScope().get("dni").toString();
        String telf = "";
        if(ctx.getSessionScope().get("telf") != null) {
            
            telf = ctx.getSessionScope().get("telf") == null ? "" : ctx.getSessionScope().get("telf").toString();

        } else {
            
            telf = "-";
            
        }
       
       txtInfoLead.setValue(nomContacto + " | " + dni + " | " + telf + " | " + nombreBin + " | " + monto);

    }
    
    
    public void setTxtTextoSpeech1(RichInputText txtTextoSpeech1) {
        this.txtTextoSpeech1 = txtTextoSpeech1;
    }

    public RichInputText getTxtTextoSpeech1() {
        return txtTextoSpeech1;
    }

    public void setTxtTelfLlam(RichInputText txtTelfLlam) {
        this.txtTelfLlam = txtTelfLlam;
    }

    public RichInputText getTxtTelfLlam() {
        return txtTelfLlam;
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

    public void setTxtFecNac(RichInputDate txtFecNac) {
        this.txtFecNac = txtFecNac;
    }

    public RichInputDate getTxtFecNac() {
        return txtFecNac;
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

    public void setTxtApePatCony(RichInputText txtApePatCony) {
        this.txtApePatCony = txtApePatCony;
    }

    public RichInputText getTxtApePatCony() {
        return txtApePatCony;
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

    public void setCboCoGene(RichSelectOneChoice cboCoGene) {
        this.cboCoGene = cboCoGene;
    }

    public RichSelectOneChoice getCboCoGene() {
        return cboCoGene;
    }
    
    public InputStream obtenerTxt(String nomBin) throws JSchException, SftpException {

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

           InputStream stream = sftpChannel.get("/upload/ReadTxt/" + nomBin + ".txt");
           System.out.println("3");
           return stream;
       }
    
    public InputStream obtenerTxt2(String nomBin) throws JSchException, SftpException {

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

           InputStream stream = sftpChannel.get("/upload/ReadTxt/" + nomBin + "2.txt");
           System.out.println("3");
           return stream;
       }
    
    
    public InputStream descargarSpeechPdf(String nomBin) throws JSchException, SftpException {

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

           InputStream stream = sftpChannel.get("/upload/PDF/" + nomBin + ".pdf");
           System.out.println("3");
           return stream;
       }
    
    
    public String speechProd(String nomBin) throws JSchException,
                                                   SftpException {
        
        InputStreamReader isr = null;
        BufferedReader br = null;
        InputStream is =  obtenerTxt(nomBin);
        StringBuilder sb = new StringBuilder();
        String content;
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
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
    
    public String speechProd2(String nomBin) throws JSchException,
                                                   SftpException {
        
        InputStreamReader isr = null;
        BufferedReader br = null;
        InputStream is =  obtenerTxt2(nomBin);
        StringBuilder sb = new StringBuilder();
        String content;
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
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

    public void mostrarSpeech1(ActionEvent actionEvent) throws JSchException,
                                                               SftpException {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
        String textoSpeech1 = speechProd(nombreBin);
        
        txtTextoSpeech1.setValue(textoSpeech1);
        
        txtTextoSpeech1.setVisible(true);
        
        pnlTomaDatosSpeech.setVisible(false);
    }
    
    public void mostrarSpeech2() throws JSchException, SftpException {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
        String textoSpeech2 = speechProd2(nombreBin);
        
        txtTextoSpeech2.setValue(textoSpeech2);
        
        txtTextoSpeech2.setVisible(true);
    }

    
    public void tomaDatosSpeech(ActionEvent actionEvent) throws JSchException,
                                                                SftpException {
        
        txtTextoSpeech1.setVisible(false);
        pnlTomaDatosSpeech.setVisible(true);
        
        mostrarSpeech2();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = ctx.getSessionScope().get("LEADID") == null ? "" : ctx.getSessionScope().get("LEADID").toString();
        String supervisor = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
        String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
        mostrarSpeechVenta(idLead, supervisor, nombreBin);
        
    }
    
    public void mostrarSpeechVenta(String idLead, String supervisor, String nombreBin){
        
        ADFContext ctx = ADFContext.getCurrent();
       
        ServiceBiPublisher bi = new ServiceBiPublisher();
        
        String mon = "";
        
        List<CpgLeadContAccProdFb> speechList = new ArrayList<CpgLeadContAccProdFb>();
        
        speechList = bi.obtenerLeadProdContAccFbCpg(idLead);
        
        ctx.getSessionScope().put("speechList", speechList);
        
        Util.Log("speechList tamaño --> " + speechList.size());
        
        for(CpgLeadContAccProdFb listSpeech : speechList){
            
            if (listSpeech.getMonLead().equalsIgnoreCase("PEN")) {
                mon = "S/ ";
            } else if (listSpeech.getMonLead().equalsIgnoreCase("USD")) {
                mon = "$ ";
            } else {
                mon = "";
            }
            
        
           txtFecNac.setValue(listSpeech.getFechaNacimiento());
           txtSupe.setValue(supervisor);
           listXProd(nombreBin);
                           
        }
        
    }
    
    

    public void setTxtCodDist(RichInputText txtCodDist) {
        this.txtCodDist = txtCodDist;
    }

    public RichInputText getTxtCodDist() {
        return txtCodDist;
    }

    public void setTxtDist(RichSelectOneChoice txtDist) {
        this.txtDist = txtDist;
    }

    public RichSelectOneChoice getTxtDist() {
        return txtDist;
    }

    public void setTxtCodProv(RichInputText txtCodProv) {
        this.txtCodProv = txtCodProv;
    }

    public RichInputText getTxtCodProv() {
        return txtCodProv;
    }

    public void setTxtProv(RichSelectOneChoice txtProv) {
        this.txtProv = txtProv;
    }

    public RichSelectOneChoice getTxtProv() {
        return txtProv;
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

    public void setCboConLab(RichSelectOneChoice cboConLab) {
        this.cboConLab = cboConLab;
    }

    public RichSelectOneChoice getCboConLab() {
        return cboConLab;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String createInsertDeptProvDistCli() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsertDeptProvDistEmp() {
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

    public void setCboLuEnDe(RichSelectOneChoice cboLuEnDe) {
        this.cboLuEnDe = cboLuEnDe;
    }

    public RichSelectOneChoice getCboLuEnDe() {
        return cboLuEnDe;
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

    public void setCboCicFac(RichSelectOneChoice cboCicFac) {
        this.cboCicFac = cboCicFac;
    }

    public RichSelectOneChoice getCboCicFac() {
        return cboCicFac;
    }

    public void setTxtNomCli7(RichOutputText txtNomCli7) {
        this.txtNomCli7 = txtNomCli7;
    }

    public RichOutputText getTxtNomCli7() {
        return txtNomCli7;
    }

    public void setTxtLDPDD(RichSelectBooleanCheckbox txtLDPDD) {
        this.txtLDPDD = txtLDPDD;
    }

    public RichSelectBooleanCheckbox getTxtLDPDD() {
        return txtLDPDD;
    }

    public void setTxtCheckFlaDis(RichSelectBooleanCheckbox txtCheckFlaDis) {
        this.txtCheckFlaDis = txtCheckFlaDis;
    }

    public RichSelectBooleanCheckbox getTxtCheckFlaDis() {
        return txtCheckFlaDis;
    }

    public void setTxtDNI(RichInputText txtDNI) {
        this.txtDNI = txtDNI;
    }

    public RichInputText getTxtDNI() {
        return txtDNI;
    }

    public void setTxtNomCli3(RichInputText txtNomCli3) {
        this.txtNomCli3 = txtNomCli3;
    }

    public RichInputText getTxtNomCli3() {
        return txtNomCli3;
    }

    public void setTxtSupe(RichInputText txtSupe) {
        this.txtSupe = txtSupe;
    }

    public RichInputText getTxtSupe() {
        return txtSupe;
    }

    public void setPnlTomaDatosSpeech(RichPanelGroupLayout pnlTomaDatosSpeech) {
        this.pnlTomaDatosSpeech = pnlTomaDatosSpeech;
    }

    public RichPanelGroupLayout getPnlTomaDatosSpeech() {
        return pnlTomaDatosSpeech;
    }
    
    
    public void registraSpeech(){
        
        Conexion c = Util.DatosConexionSBP();
        
        Proceso p = new Proceso();
        
        MergeLeadClient mlc = new MergeLeadClient();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        
        String nomBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
        String supervisor = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
        
        String vendor = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        
        List<CpgLeadContAccProdFb> l = new ArrayList<CpgLeadContAccProdFb>();
        
        l = (List<CpgLeadContAccProdFb>)ctx.getSessionScope().get("speechList");
        
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
        
        e.setLeadID(l.get(0).getIdLead().toString());
        
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

        e.setDepartamento(Util.nvl(this.txtDept, ""));

        e.setProvincia(Util.nvl(this.txtProv, ""));

        e.setDistrito(Util.nvl(this.txtDist, ""));

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
        
        
         if(txtTelfLlam.getValue() == null || txtTelfCasa.getValue() == null ||
             txtCelular.getValue() == null || cboEstCiv.getValue() == null ||
             cboCliGen.getValue() == null || txtDept.getValue() == null ||
             txtProv.getValue() == null || txtDist.getValue() == null ||
             txtDir.getValue() == null || txtRef.getValue() == null ||
             txtDeptEmp.getValue() == null || txtProvEmp.getValue() == null ||
             txtDistEmp.getValue() == null || txtDirEmp.getValue() == null ||
             txtRefEmp.getValue() == null || txtTelfEmp.getValue() == null ||
             cboLuEnIn.getValue() == null || cboLuEnDe.getValue() == null || 
             cboCicFac.getValue() == null){
         
             FacesContext fctx = FacesContext.getCurrentInstance();
                          fctx.addMessage("",
                                          new FacesMessage(FacesMessage.SEVERITY_ERROR, "No deben estar en blanco los campos obligatorios",
                                                       ""));
                          
            
         } else {
             
             p = mlc.mergeLeadSpeechVenta(c, e);
             
          /*   String idEjecVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
             
             Util.Log("idEjecVenta --> " + idEjecVenta);*/
             
            // Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + idEjecVenta); 
             String ejecutivoVenta = ejVenta.replace(" ", ".");
             Util.Log(ejecutivoVenta + "EJECUTIVO SPEECH");
             Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + ejecutivoVenta);
             
         }
        
    }

    public void crearSpeech(ActionEvent actionEvent) {
        
        registraSpeech();      
      
    }
    
    public void listXProd(String nombreProd) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

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
                    
                    membresia = rs.getString("MEMBRESIA");
                    
                    seguro = rs.getString("SEGURO");
                    
                    txtMembAnual.setValue(membresia);
                    txtDesgravamen.setValue(seguro);
                    
                  
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

    public void volver(ActionEvent actionEvent) {
       
        ADFContext ctx = ADFContext.getCurrent();
        
        String idEjecVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
        
        Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + idEjecVenta);        
    }
    
    public void descargarCpgActual(FacesContext context, OutputStream out) throws IOException, JSchException,
                                                  SftpException {
        
            InputStream is = obtenerCpgActualPdf();
            
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while ((read = is.read(bytes)) != -1) {
                   out.write(bytes, 0, read);
                      }
            
        }
    
    public void descargarSpeech(FacesContext context, OutputStream out) throws IOException, JSchException,
                                                  SftpException {
        
            ADFContext ctx = ADFContext.getCurrent();
            
            String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
            InputStream is = descargarSpeechPdf(nombreBin);
            
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while ((read = is.read(bytes)) != -1) {
                   out.write(bytes, 0, read);
                      }
            
        }
    
    public InputStream obtenerCpgActualPdf() throws JSchException, SftpException {

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

           InputStream stream = sftpChannel.get("/upload/PDF/Campania.pdf");
           System.out.println("3");
           return stream;
       }    
    
    public String getFileName() {
        
            ADFContext ctx = ADFContext.getCurrent();
            
            String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
            
            String nombrePdf = nombreBin + ".pdf";

            return nombrePdf;
            
        }

    public void setTxtTextoSpeech2(RichInputText txtTextoSpeech2) {
        this.txtTextoSpeech2 = txtTextoSpeech2;
    }

    public RichInputText getTxtTextoSpeech2() {
        return txtTextoSpeech2;
    }
}

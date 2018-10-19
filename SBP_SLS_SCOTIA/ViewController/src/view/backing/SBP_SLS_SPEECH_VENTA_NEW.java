package view.backing;

import Client.MergeLeadClient;

import Client.MergeLeadInvoke;

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

import dao.Canal;
import dao.CpgLeadContAccProdFb;

import dao.Feedback;
import dao.Producto;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import java.util.TimeZone;

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
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.commons.dbutils.DbUtils;

import org.xml.sax.SAXException;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_SPEECH_VENTA_NEW {
    private RichForm f1;
    private RichDocument d1;
    private RichOutputText txtInfoLead;
    private RichPanelGroupLayout pnlTomaDatosSpeech;
    private RichInputDate txtFecNac;
    private RichInputText txtTelfLlam;
    private RichInputText txtTelfCasa;
    private RichInputText txtCelular;
    private RichInputText txtCorreo;
    private RichInputText txtTelfRef;
    private RichSelectOneChoice txtParent;
    private RichSelectOneChoice cboEstCiv;
    private RichSelectOneChoice cboCliGen;
    private RichInputText txtNomCony1;
    private RichInputText txtNomCony2;
    private RichInputText txtApePatCony;
    private RichInputText txtApeMatCony;
    private RichInputDate txtFecNacCony;
    private RichInputText txtDniCony;
    private RichSelectOneChoice cboCoGene;
    private RichInputText txtDir;
    private RichInputText txtRef;
    private RichSelectOneChoice cboConLab;
    private RichInputText txtNomEmp;
    private RichInputText txtRucEmp;
    private RichInputDate txtFecIng;
    private RichInputText txtDirEmp;
    private RichInputText txtRefEmp;
    private RichInputText txtTelfEmp;
    private RichInputText txtAnexo;
    private RichSelectOneChoice cboLuEnIn;
    private RichSelectOneChoice cboLuEnDe;
    private RichInputText txtSupe;
    private RichSelectOneChoice cboCicFac;
    private RichSelectBooleanCheckbox txtLDPDD;
    private RichSelectBooleanCheckbox txtCheckFlaDis;
    private RichInputText txtDesgravamen;
    private RichInputText txtMembAnual;
    private RichInputText txtTextoSpeech1;
    private RichInputText txtTextoSpeech2;
    private RichSelectOneChoice txtDept;
    private RichSelectOneChoice txtDeptEmp;
    private RichSelectOneChoice txtProvEmp;
    private RichSelectOneChoice txtDistEmp;
    private RichSelectOneChoice txtProv;
    private RichSelectOneChoice txtDist;
    private RichInputText txtTextoSpeech;
    private RichInputText cboCeLaCa;
    private RichSelectOneChoice cboTransInternet;
    private RichSelectOneChoice cboEnvEstCta;
    private RichSelectOneChoice cboTransInternac;
    private RichSelectOneChoice cboLugEnvEstCta;
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
    private RichPopup popUpDirecLab;
    private RichPopup popUpDirecDom;

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
        
     /*   session = jsch.getSession("root", "191.98.147.250", 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("evol2018");*/
        
        session.setProxy(new ProxyHTTP(host, 80));
        System.out.println("1");
        session.connect();
        System.out.println("2");
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp)channel;

        InputStream stream =
            sftpChannel.get("/tmp/SBPSpeech/" + nomBin + "_1.txt");
        System.out.println("3");
        return stream;
    }

    public void volver(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String nombreFb = ctx.getSessionScope().get("nombreFb") == null ? "" : ctx.getSessionScope().get("nombreFb").toString();
        
        String ejVenta = ctx.getSessionScope().get("ejVenta") == null ? "" : ctx.getSessionScope().get("ejVenta").toString();
        
        Util.Log("nombreFb --> " + nombreFb);
        
        if(nombreFb == null || nombreFb.equalsIgnoreCase("")){
        
            //Redireccionar a pantalla de búsqueda por dni

            String ejecutivoVenta = ejVenta.replace(" ", ".");
            
            Util.Log(ejecutivoVenta + "EJECUTIVO SPEECH");
            
            Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + ejecutivoVenta);
            
        } else {
            
            //Redireccionar a la pantalla de ventas
            
            String nomEjecutivo = ejVenta.replace(" ", ".");
            
            Util.Log("ejVenta --> " + nomEjecutivo);
            
            Util.redirec("SBP_SLS_OPP_PAGE?userId=" + nomEjecutivo);
            
        }
        
    }

    public void setPnlTomaDatosSpeech(RichPanelGroupLayout pnlTomaDatosSpeech) {
        this.pnlTomaDatosSpeech = pnlTomaDatosSpeech;
    }

    public RichPanelGroupLayout getPnlTomaDatosSpeech() {
        return pnlTomaDatosSpeech;
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

    public void setTxtSupe(RichInputText txtSupe) {
        this.txtSupe = txtSupe;
    }

    public RichInputText getTxtSupe() {
        return txtSupe;
    }

    public void setCboCicFac(RichSelectOneChoice cboCicFac) {
        this.cboCicFac = cboCicFac;
    }

    public RichSelectOneChoice getCboCicFac() {
        return cboCicFac;
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


    public void descargarCpgActual(FacesContext context, OutputStream out) throws JSchException,
                                                            SftpException,
                                                            IOException {
       
        InputStream is = obtenerCpgActualPdf();
        
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

    public void setTxtTextoSpeech1(RichInputText txtTextoSpeech1) {
        this.txtTextoSpeech1 = txtTextoSpeech1;
    }

    public RichInputText getTxtTextoSpeech1() {
        return txtTextoSpeech1;
    }

    public void setTxtTextoSpeech2(RichInputText txtTextoSpeech2) {
        this.txtTextoSpeech2 = txtTextoSpeech2;
    }

    public RichInputText getTxtTextoSpeech2() {
        return txtTextoSpeech2;
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
        
        Util.Log("inicia onload");
        
        createInsert1();
        createInsert2();

        ADFContext ctx = ADFContext.getCurrent();

        String telf = (String)resolveExpression("#{viewScope.telfActual2}");
        String nomContacto = (String)resolveExpression("#{viewScope.contacto2}");
        String monto = (String)resolveExpression("#{viewScope.montoDesem2}");
        String nombreBin = (String)resolveExpression("#{viewScope.prod2}");
        String dni = (String)resolveExpression("#{viewScope.numDoc2}");
        String idLead = (String)resolveExpression("#{viewScope.idLead2}");
        String nombreFb = (String)resolveExpression("#{viewScope.nombreFb2}");
        String idCanal = (String)resolveExpression("#{viewScope.idCanal2}");
        String canal = (String)resolveExpression("#{viewScope.canal2}");
        String idJefCanal = (String)resolveExpression("#{viewScope.idJefCanal2}");
        String jefCanal = (String)resolveExpression("#{viewScope.jefCanal2}");
        String idSup = (String)resolveExpression("#{viewScope.idSup2}");
        String sup = (String)resolveExpression("#{viewScope.sup2}");
        String idEjVenta = (String)resolveExpression("#{viewScope.idEjVenta2}");
        String ejVenta = (String)resolveExpression("#{viewScope.ejVenta2}");
        String tipoGestion = (String)resolveExpression("#{viewScope.tipoGestion2}");
        String grupoGestion = (String)resolveExpression("#{viewScope.grupoGestion2}");
        String detalleGestion = (String)resolveExpression("#{viewScope.detalleGestion2}");
        String auxFeedbackContactado = (String)resolveExpression("#{viewScope.auxFeedbackContactado2}");
        String auxFeedbackContactadoEfec = (String)resolveExpression("#{viewScope.auxFeedbackContactadoEfec2}");
        String auxFeedbackVenta = (String)resolveExpression("#{viewScope.auxFeedbackVenta2}");
        String auxFeedbackRevenueVenta = (String)resolveExpression("#{viewScope.auxFeedbackRevenueVenta2}");
        String nomDetGest = (String)resolveExpression("#{viewScope.nomDetGest2}");
        String nomGrupGest = (String)resolveExpression("#{viewScope.nomGrupGest2}");
        String nomTipGest = (String)resolveExpression("#{viewScope.nomTipGest2}");
        String fecGestion = (String)resolveExpression("#{viewScope.fecGestion2}");
        String obs = (String)resolveExpression("#{viewScope.obs2}");
        String dirAd = (String)resolveExpression("#{viewScope.dirAd2}");
        String emailAd = (String)resolveExpression("#{viewScope.emailAd2}");
        String idCpg = (String)resolveExpression("#{viewScope.idCpg2}");
        String cpgNom = (String)resolveExpression("#{viewScope.cpgNom2}");
        String montoDesem = (String)resolveExpression("#{viewScope.montoDesem2}");
        String fecha2daLlam = (String)resolveExpression("#{viewScope.fecha2daLlam2}");
        String contacto = (String)resolveExpression("#{viewScope.contacto2}");
        String numDoc = (String)resolveExpression("#{viewScope.numDoc2}");
        String tipDoc = (String)resolveExpression("#{viewScope.tipDoc2}");
        String flagInbound = (String)resolveExpression("#{viewScope.flagInbound2}");
        String flagOutbound = (String)resolveExpression("#{viewScope.flagOutbound2}");
        String horaFeedback = (String)resolveExpression("#{viewScope.horaFeedback2}");
        String auxFeedbackAgendado = (String)resolveExpression("#{viewScope.auxFeedbackAgendado2}");
        String prod = (String)resolveExpression("#{viewScope.prodFb2}");
        
        String codClie = (String)resolveExpression("#{viewScope.codClie2}");
        String nomClie = (String)resolveExpression("#{viewScope.nomClie2}");
        String codProd = (String)resolveExpression("#{viewScope.codProd2}");
        String nomProd = (String)resolveExpression("#{viewScope.nomProd2}");
        String montoProd = (String)resolveExpression("#{viewScope.montoProd2}");
        String genero = (String)resolveExpression("#{viewScope.genero2}");
        String tea = (String)resolveExpression("#{viewScope.tea2}");
        String tcea = (String)resolveExpression("#{viewScope.tcea2}");
        String primerNom = (String)resolveExpression("#{viewScope.primerNom2}");
        String segNom = (String)resolveExpression("#{viewScope.segNom2}");
        String apePat = (String)resolveExpression("#{viewScope.apePat2}");
        String apeMat = (String)resolveExpression("#{viewScope.apeMat2}");
        String fecNac = (String)resolveExpression("#{viewScope.fecNac2}");
        
        
        ctx.getSessionScope().put("nombreBin", nombreBin);
        ctx.getSessionScope().put("telf", telf);
        ctx.getSessionScope().put("idLead", idLead);
        ctx.getSessionScope().put("nombreFb", nombreFb);
        ctx.getSessionScope().put("idCanal", idCanal);
        ctx.getSessionScope().put("canal", canal);
        ctx.getSessionScope().put("idJefCanal", idJefCanal);
        ctx.getSessionScope().put("jefCanal", jefCanal);
        ctx.getSessionScope().put("idSup", idSup);
        ctx.getSessionScope().put("sup", sup);
        ctx.getSessionScope().put("idEjVenta", idEjVenta);
        ctx.getSessionScope().put("ejVenta", ejVenta);
        ctx.getSessionScope().put("tipoGestion", tipoGestion);
        ctx.getSessionScope().put("grupoGestion", grupoGestion);
        ctx.getSessionScope().put("detalleGestion", detalleGestion);
        ctx.getSessionScope().put("auxFeedbackContactado", auxFeedbackContactado);
        ctx.getSessionScope().put("auxFeedbackContactadoEfec", auxFeedbackContactadoEfec);
        ctx.getSessionScope().put("auxFeedbackVenta", auxFeedbackVenta);
        ctx.getSessionScope().put("auxFeedbackRevenueVenta", auxFeedbackRevenueVenta);
        ctx.getSessionScope().put("nomDetGest", nomDetGest);
        ctx.getSessionScope().put("nomGrupGest", nomGrupGest);
        ctx.getSessionScope().put("nomTipGest", nomTipGest);
        ctx.getSessionScope().put("fecGestion", fecGestion);
        ctx.getSessionScope().put("obs", obs);
        ctx.getSessionScope().put("dirAd", dirAd);
        ctx.getSessionScope().put("emailAd", emailAd);
        ctx.getSessionScope().put("idCpg", idCpg);
        ctx.getSessionScope().put("cpgNom", cpgNom);
        ctx.getSessionScope().put("montoDesem", montoDesem);
        ctx.getSessionScope().put("fecha2daLlam", fecha2daLlam);
        ctx.getSessionScope().put("contacto", contacto);
        ctx.getSessionScope().put("numDoc", numDoc);
        ctx.getSessionScope().put("tipDoc", tipDoc);
        ctx.getSessionScope().put("flagInbound", flagInbound);
        ctx.getSessionScope().put("flagOutbound", flagOutbound);
        ctx.getSessionScope().put("horaFeedback", horaFeedback);
        ctx.getSessionScope().put("auxFeedbackAgendado", auxFeedbackAgendado);
        ctx.getSessionScope().put("prod", prod);
        ctx.getSessionScope().put("dni", dni);
        
        ctx.getSessionScope().put("codClie", codClie);
        ctx.getSessionScope().put("nomClie", nomClie);
        ctx.getSessionScope().put("codProd", codProd);
        ctx.getSessionScope().put("nomProd", nomProd);
        ctx.getSessionScope().put("montoProd", montoProd);
        ctx.getSessionScope().put("genero", genero);
        ctx.getSessionScope().put("tea", tea);
        ctx.getSessionScope().put("tcea", tcea);
        ctx.getSessionScope().put("primerNom", primerNom);
        ctx.getSessionScope().put("segNom", segNom);
        ctx.getSessionScope().put("apePat", apePat);
        ctx.getSessionScope().put("apeMat", apeMat);
        ctx.getSessionScope().put("fecNac", fecNac);

        txtInfoLead.setValue(nomContacto + " | " + dni + " | " + telf + " | " +
                             nombreBin + " | " + monto);

        try {
             Util.Log("inicia sftp");
             mostrarSpeech(nombreBin);
             Util.Log("fin sftp");     
          } catch (JSchException e) {
                 e.printStackTrace(System.out);
             } catch (SftpException e) {
                 e.printStackTrace(System.out);
             }


    /*     try {*/
               Util.Log("inicio mostrar speech");
               MostrarDatosSpeechVenta();
               Util.Log("fin mostrar speech");
     /*      } catch (ParseException e) {
                 e.printStackTrace(System.out);
             }*/
        
       Util.Log("termina onload");
    }

    private void MostrarDatosSpeechVenta(){

        ADFContext ctx = ADFContext.getCurrent();

        String idLead = ctx.getSessionScope().get("idLead") == null ? "" : ctx.getSessionScope().get("idLead").toString();
       
        String sup = ctx.getSessionScope().get("sup") == null ? "" : ctx.getSessionScope().get("sup").toString();
       
      /*  ServiceBiPublisher lead = new ServiceBiPublisher();
       
        List<CpgLeadContAccProdFb> lista2 = new ArrayList<CpgLeadContAccProdFb>();
        
        lista2 = lead.obtenerLeadProdContAccFbCpg(idLead);
        
        Util.Log("LISTA LEAD --> " + lista2.size());*/


        try {

            String vendor = ctx.getSessionScope().get("canal") == null ? "" : ctx.getSessionScope().get("canal").toString();
            
            String cpgNom = ctx.getSessionScope().get("cpgNom") == null ? "" : ctx.getSessionScope().get("cpgNom").toString();

            String ejVenta = ctx.getSessionScope().get("ejVenta") == null ? "" : ctx.getSessionScope().get("ejVenta").toString();
            
            String codClie = ctx.getSessionScope().get("codClie") == null ? "" : ctx.getSessionScope().get("codClie").toString();
            
            String nomClie = ctx.getSessionScope().get("nomClie") == null ? "" : ctx.getSessionScope().get("nomClie").toString();
            
            String codProd = ctx.getSessionScope().get("codProd") == null ? "" : ctx.getSessionScope().get("codProd").toString();
            
            String prod = ctx.getSessionScope().get("prod") == null ? "" : ctx.getSessionScope().get("prod").toString();
            
            String montoProd = ctx.getSessionScope().get("montoProd") == null ? "" : ctx.getSessionScope().get("montoProd").toString();
            
            String genero = ctx.getSessionScope().get("genero") == null ? "" : ctx.getSessionScope().get("genero").toString();
            
            String dni = ctx.getSessionScope().get("dni") == null ? "" : ctx.getSessionScope().get("dni").toString();
            
            String tea = ctx.getSessionScope().get("tea") == null ? "" : ctx.getSessionScope().get("tea").toString();
            
            String tcea = ctx.getSessionScope().get("tcea") == null ? "" : ctx.getSessionScope().get("tcea").toString();
            
            String primerNom = ctx.getSessionScope().get("primerNom") == null ? "" : ctx.getSessionScope().get("primerNom").toString();

            String segNom = ctx.getSessionScope().get("segNom") == null ? "" : ctx.getSessionScope().get("segNom").toString();

            String apePat = ctx.getSessionScope().get("apePat") == null ? "" : ctx.getSessionScope().get("apePat").toString();

            String apeMat = ctx.getSessionScope().get("apeMat") == null ? "" : ctx.getSessionScope().get("apeMat").toString();
            
            String fecNac = ctx.getSessionScope().get("fecNac") == null ? "" : ctx.getSessionScope().get("fecNac").toString();
         
            //HORA FEEDBACK
            
            final Date currentTimeHoraGest = new Date();

            final SimpleDateFormat sdfHoraGest = new SimpleDateFormat("HH");

            // Give it to me in GMT time.
            sdfHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String horaFeedback = sdfHoraGest.format(currentTimeHoraGest);

          
            //FECHA GESTION
            final Date currentTimeFecGest = new Date();

            final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy-MM-dd");

            // Give it to me in GMT time.
            sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String fecGestion = sdfFecGest.format(currentTimeFecGest);
            Util.Log("Fecha Gestión --> " + fecGestion);

            SpeechVenta e = new SpeechVenta();
            e.setLeadID(idLead);
            
            e.setClienteCodigo(codClie);
            e.setClienteNombre(nomClie);
            e.setUsuarioNombre(ejVenta);
            e.setProductoCodigo(codProd);
            e.setProductoNombre(prod);
            e.setProductoMonto(Util.formatearMonto(montoProd));
            e.setGeneroDescripcion(genero);
            e.setClienteNumeroDocumento(dni);
            e.setValorTEA(tea);
            e.setValorTCEA(tcea);
            e.setValorMembresia(""); // Falta definir campo
            e.setValorDesgravamen(""); // Falta definir campo
            e.setSoporteNumeroTelefono(""); // Falta definir campo
            e.setSupervisorNombre(sup); // Falta definir campo
            e.setValorEnvioEstadoCuenta("S/ 8.9");
            e.setCpgNom(cpgNom);
            e.setHoraSpeech(horaFeedback);
            e.setVendorName(vendor);
            e.setFecGestion(fecGestion);
            
            e.setPrimerNombre(primerNom);
            e.setSegundoNombre(segNom);
            e.setApePaterno(apePat);
            e.setApeMaterno(apeMat);
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            String dateInString = fecNac;
            
            Util.Log("FECHA NAC --> " + dateInString);
            
            if(!dateInString.equalsIgnoreCase("-")){
                
                Date date1 = formatter.parse(dateInString);
                
                txtFecNac.setValue(date1);
                
                Util.Log("despues de " + date1);
                
            } else {
                
                txtFecNac.setValue("-");
                
            }
 

            this.txtSupe.setValue(e.getSupervisorNombre());
            
        
            // Guardar en session
            ctx.getSessionScope().put("SpeechVenta", e);

        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    public String cbRegistrarVenta_click() {
        
        Util.Log("1");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ADFContext ctx = ADFContext.getCurrent();

      
        String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();

        try {
            // Obtener los datos de session
            SpeechVenta s = (SpeechVenta)ctx.getSessionScope().get("SpeechVenta");

            MergeLeadClient mlc = new MergeLeadClient();

            Proceso p = new Proceso();

            Conexion c = Util.DatosConexionSBP();
            s.setLeadID(Util.nvl(s.getLeadID(), ""));
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            s.setSpeechID(dateFormat.format(date));
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
            try {
                s.setClienteFechaNacimiento(df.format(this.txtFecNac.getValue()));
            } catch (Exception e) {
                s.setClienteFechaNacimiento(""); // e.getClienteFechaNacimiento());
            }

            s.setTelefonoLlamada(Util.nvl(this.txtTelfLlam,
                                          "")); // e.getTelefonoLlamada());
            s.setTelefonoCasa(Util.nvl(this.txtTelfCasa,
                                       "")); // e.getTelefonoCasa());
            s.setTelefonoCelular(Util.nvl(this.txtCelular,
                                          "")); // e.getTelefonoCelular());
            s.setCorreoElectronico(Util.nvl(this.txtCorreo,
                                            "")); // e.getCorreoElectronico());
            s.setTelefonoReferencia(Util.nvl(this.txtTelfRef,
                                             "")); // e.getTelefonoReferencia());
            s.setParentescoCodigo(Util.nvl(this.txtParent,
                                           "")); // e.getParentescoCodigo());
            s.setParentescoDescripcion(Util.getSelectedItem(this.txtParent)); // e.getParentescoDescripcion());
            s.setEstadoCivilCodigo(Util.nvl(this.cboEstCiv,
                                            "")); // e.getEstadoCivilCodigo());
            s.setEstadoCivilDescripcion(Util.getSelectedItem(this.cboEstCiv)); // e.getEstadoCivilDescripcion());
            s.setGeneroCodigo(Util.nvl(this.cboCliGen,
                                       "")); // e.getGeneroCodigo());
                                       Util.Log("2.5");
            s.setGeneroDescripcion(Util.getSelectedItem(this.cboCliGen)); // e.getGeneroDescripcion());
            s.setConyugeApellidoPaterno(Util.nvl(this.txtApePatCony,
                                                 "")); // e.getConyugeApellidoPaterno());
            s.setConyugeApellidoMaterno(Util.nvl(this.txtApeMatCony,
                                                 "")); // e.getConyugeApellidoMaterno());
            s.setConyugeNombre1(Util.nvl(this.txtNomCony1,
                                         "")); // e.getConyugeNombre1());
            s.setConyugeNombre2(Util.nvl(this.txtNomCony2,
                                         "")); // e.getConyugeNombre2());
            try {
                s.setConyugeFechaNacimiento(sdf.format(this.txtFecNacCony.getValue()));
            } catch (Exception e) {
                s.setConyugeFechaNacimiento(""); // e.getConyugeFechaNacimiento());
            }

            try {
                s.setCentroLaboresFechaIngreso(sdf.format(this.txtFecIng.getValue()));
            } catch (Exception e) {
                s.setCentroLaboresFechaIngreso(""); // e.getConyugeFechaNacimiento());
            }


            s.setConyugeNumeroDocumento(Util.nvl(this.txtDniCony,
                                                 "")); // e.getConyugeNumeroDocumento());

            s.setConyugeGenerocodigo(Util.nvl(this.cboCoGene, ""));
            s.setConyugeGeneroDescripcion(Util.getSelectedItem(this.cboCoGene));
            s.setDireccion(Util.nvl(this.txtDir, ""));

            s.setDepartamento(Util.nvl(this.txtDept, ""));

            s.setProvincia(Util.nvl(this.txtProv, ""));

            s.setDistrito(Util.nvl(this.txtDist, ""));

            s.setReferencia(Util.nvl(this.txtRef, "")); // e.getReferencia());

            s.setCondicionLaboralCodigo(Util.nvl(this.cboConLab,
                                                 "")); // e.getCondicionLaboralCodigo());

            s.setCondicionLaboralDescripcion(Util.getSelectedItem(this.cboConLab)); // e.getCondicionLaboralDescripcion());

            s.setCentroLaboresNombre(Util.nvl(this.txtNomEmp,
                                              "")); // e.getCentroLaboresNombre());

            s.setCentroLaboresRuc(Util.nvl(this.txtRucEmp,
                                           "")); // e.getCentroLaboresRuc());

            s.setCentroLaboresDireccion(Util.nvl(this.txtDirEmp,
                                                 "")); // e.getCentroLaboresDireccion());

            s.setCentroLaboresDepartamento(Util.nvl(this.txtDeptEmp, ""));

            s.setCentroLaboresProvincia(Util.nvl(this.txtProvEmp, ""));

            s.setCentroLaboresDistrito(Util.nvl(this.txtDistEmp, ""));

            s.setCentroLaboresReferencia(Util.nvl(this.txtRefEmp,
                                                  "")); // e.getCentroLaboresReferencia());

            s.setCentroLaboresTelefono(Util.nvl(this.txtTelfEmp,
                                                "")); // e.getCentroLaboresTelefono());

            s.setCentroLaboresAnexo(Util.nvl(this.txtAnexo,
                                             "")); // e.getCentroLaboresAnexo());

            s.setCentroLaboresCargoCodigo(Util.nvl(this.cboCeLaCa,
                                                   "")); // e.getCentroLaboresCargoCodigo());

            s.setCentroLaboresCargoDescripcion(Util.nvl(this.cboCeLaCa, ""));

            s.setLugarEntregaIndependienteCodigo(Util.nvl(this.cboLuEnIn,
                                                          "")); // e.getLugarEntregaIndependienteCodigo());

            s.setLugarEntregaIndependienteDescripcion(Util.getSelectedItem(this.cboLuEnIn)); // e.getLugarEntregaIndependienteDescripcion());

            s.setLugarEntregaDependienteCodigo(Util.nvl(this.cboLuEnDe,
                                                        "")); 
            
            s.setTransInternac(Util.nvl(this.cboTransInternac,""));
            
            s.setTransXInternet(Util.nvl(this.cboTransInternet,""));
            
            s.setEnvEstCta(Util.nvl(this.cboEnvEstCta,""));
            
            s.setLugEnvEstCta(Util.nvl(this.cboLugEnvEstCta,""));

            s.setLugarEntregaDependienteDescripcion(Util.getSelectedItem(this.cboLuEnDe)); // e.getLugarEntregaDependienteDescripcion());


            s.setValorMembresia("-");
           // s.setValorMembresia(txtMembAnual.getValue().toString());
            s.setValorDesgravamen("-");
            //s.setValorDesgravamen(txtDesgravamen.getValue().toString());
            s.setCicloFacturacion(Util.getSelectedItem(this.cboCicFac));

            String flaDis = "";

            if (txtCheckFlaDis.getValue().toString().equalsIgnoreCase("true")) {
                flaDis = "1";
            } else {
                flaDis = "0";
            }

            s.setFlagDisposicionEfectivo(flaDis);

            String flaAut = "";

            if (txtLDPDD.getValue().toString().equalsIgnoreCase("true")) {
                flaAut = "1";
            } else {
                flaAut = "0";
            }

            s.setFlagAutorizacionLPDP(flaAut);
            s.setTipoProd(nombreBin);
            Util.Log("4");

            p = mlc.mergeLeadSpeechVenta(c, s);

            borrarFecProg(s.getLeadID());

            txtCheckFlaDis.setValue("");
            txtLDPDD.setValue("");
            
            String dni = ctx.getSessionScope().get("dni") == null ? "" : ctx.getSessionScope().get("dni").toString();

            //METODO PARA GENERAR TXT DE SPEECH VENTA
            generarTxt("",//SubProducto,
                       "",//Código Campaña,
                       "",//Empresa/Und. Vta.,
                       "",//Promotor,
                       "",//Tipo Empleo,
                       "DNI",//Tipo  Documento,
                       dni,//Nro. Documento,
                       s.getApePaterno(),//Ap. Paterno,
                       s.getApeMaterno(),//Ap. Materno,
                       s.getPrimerNombre(),//Nombre(1),
                       s.getSegundoNombre(),//Nombre(2),
                       s.getGeneroDescripcion(),//Sexo,
                       s.getClienteFechaNacimiento(),//Fecha de Nacimiento,
                       "",//Teléfono ( Códigos Telefonicos ),
                       s.getTelefonoLlamada(),//Teléfono,
                       s.getTelefonoCelular(),//Celular,
                       "",//Nacionalidad,
                       s.getEstadoCivilDescripcion(),//Estado civil,
                       "",//Correspondencia,
                       "",//Tipo de Referencia(Conyugue),
                       "DNI",//Tipo  Documento (Conyugue),
                       s.getConyugeNumeroDocumento(),//Nro. Documento(Conyugue),
                       s.getConyugeApellidoPaterno(),//Ap. Paterno(Conyugue),
                       s.getConyugeApellidoMaterno(),//Ap. Materno(Conyugue),
                       s.getConyugeNombre1(),//Nombre(1) (Conyugue),
                       s.getConyugeNombre2(),//Nombre(2) (Conyugue),
                       s.getConyugeGeneroDescripcion(),//Sexo Conyugue,
                       s.getConyugeFechaNacimiento(),//Fecha Nacimiento Conyugue,
                       "",//Tipo de Vía,
                       "",//Nombre de Vía,
                       "",//Número,
                       "",//Nro. Dpto,
                       "",//Oficina,
                       "",//Piso,
                       "",//Manzana,
                       "",//Lote,
                       "",//Interior,
                       "",//Sector,
                       "",//Kilometro,
                       "",//Tipo de Zona,
                       "",//Nombre Zona,
                       s.getDepartamento(),//Departamento,
                       s.getProvincia(),//Provincia,
                       s.getDistrito(),//Distrito,
                       s.getReferencia(),//Referencia,
                       s.getCorreoElectronico(),//E-Mail,
                       "",//Celular Aviso Emisión,
                       "",//Agencia Sectorización,
                       s.getCentroLaboresRuc(),//RUC,
                       "",//Teléfono ( Códigos Telefonicos ) Laboral,
                       s.getCentroLaboresTelefono(),//Teléfono Laboral,
                       "",//Fax Laboral,
                       "",//FAX / CEL,
                       s.getCentroLaboresNombre(),//Razón Social,
                       "",//E-Mail Laboral,
                       "",//Forma de Pago,
                       s.getCentroLaboresFechaIngreso(),//Fecha de Ingreso,
                       "",//Tipo de Vía Laboral,
                       "",//Nombre de Vía Laboral,
                       "",//Número Laboral,
                       "",//Nro. Dpto Laboral,
                       "",//Oficina Laboral,
                       "",//Piso Laboral,
                       "",//Manzana Laboral,
                       "",//Lote Laboral,
                       "",//Interior Laboral,
                       "",//Sector Laboral,
                       "",//Kilometro Laboral,
                       "",//Tipo de Zona Laboral,
                       "",//Nombre Zona Laboral,
                       s.getCentroLaboresDepartamento(),//Departamento Laboral,
                       s.getCentroLaboresProvincia(),//Provincia Laboral,
                       s.getCentroLaboresDistrito(),//Distrito Laboral,
                       s.getCentroLaboresReferencia(),//Referencia Laboral,
                       "",//Segmento,
                       "",//Campaña T/C (Codigo Ensobrado),
                       "",//Ingresos Mancomunados?,
                       "",//Disposición Efectivo,
                       "",//Compra de Deuda?,
                       "",//Venta con Extralinea?,
                       "",//Procesar Paperless,
                       "",//¿Permitir utilización de datos?,
                       s.getEnvEstCta(),//Envio de Estado de Cuenta,
                       s.getTransXInternet(),//Transacciones por Internet,
                       s.getTransInternac(),//Transacciones Internacionales,
                       s.getLugEnvEstCta(),//Lugar Entrega de Tarjeta,
                       "",//Agencia Entrega de Tarjeta,
                       "",//Nombre en Tarjeta (Embose),
                       "",//Tipo De Tarjeta,
                       s.getCicloFacturacion()//Ciclo de Facturación
                       );

        } catch (Exception ex) {
            Util.Log(ex.getMessage());

        }
        return null;
    }

    public void setTxtDept(RichSelectOneChoice txtDept) {
        this.txtDept = txtDept;
    }

    public RichSelectOneChoice getTxtDept() {
        return txtDept;
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

    public void RegistrarFeedback() {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        try {
            
            String telfAd = "-";
            String idLead = ctx.getSessionScope().get("idLead") == null ? "" : ctx.getSessionScope().get("idLead").toString();
            String nombreFb = ctx.getSessionScope().get("nombreFb") == null ? "" : ctx.getSessionScope().get("nombreFb").toString();
            String telf = ctx.getSessionScope().get("telf") == null ? "" : ctx.getSessionScope().get("telf").toString();
            String idCanal = ctx.getSessionScope().get("idCanal") == null ? "" : ctx.getSessionScope().get("idCanal").toString();
            String canal = ctx.getSessionScope().get("canal") == null ? "" : ctx.getSessionScope().get("canal").toString();
            String idJefCanal = ctx.getSessionScope().get("idJefCanal") == null ? "" : ctx.getSessionScope().get("idJefCanal").toString();
            String jefCanal = ctx.getSessionScope().get("jefCanal") == null ? "" : ctx.getSessionScope().get("jefCanal").toString();
            String idSup = ctx.getSessionScope().get("idSup") == null ? "" : ctx.getSessionScope().get("idSup").toString();
            String sup = ctx.getSessionScope().get("sup") == null ? "" : ctx.getSessionScope().get("sup").toString();
            String idEjVenta = ctx.getSessionScope().get("idEjVenta") == null ? "" : ctx.getSessionScope().get("idEjVenta").toString();
            String ejVenta = ctx.getSessionScope().get("ejVenta") == null ? "" : ctx.getSessionScope().get("ejVenta").toString();
            String tipoGestion = ctx.getSessionScope().get("tipoGestion") == null ? "" : ctx.getSessionScope().get("tipoGestion").toString();
            String grupoGestion = ctx.getSessionScope().get("grupoGestion") == null ? "" : ctx.getSessionScope().get("grupoGestion").toString();
            String detalleGestion = ctx.getSessionScope().get("detalleGestion") == null ? "" : ctx.getSessionScope().get("detalleGestion").toString();
            String auxFeedbackContactado = ctx.getSessionScope().get("auxFeedbackContactado") == null ? "" : ctx.getSessionScope().get("auxFeedbackContactado").toString();
            String auxFeedbackContactadoEfec = ctx.getSessionScope().get("auxFeedbackContactadoEfec") == null ? "" : ctx.getSessionScope().get("auxFeedbackContactadoEfec").toString();
            String auxFeedbackVenta = ctx.getSessionScope().get("auxFeedbackVenta") == null ? "" : ctx.getSessionScope().get("auxFeedbackVenta").toString();
            String auxFeedbackRevenueVenta = ctx.getSessionScope().get("auxFeedbackRevenueVenta") == null ? "" : ctx.getSessionScope().get("auxFeedbackRevenueVenta").toString();
            String nomDetGest = ctx.getSessionScope().get("nomDetGest") == null ? "" : ctx.getSessionScope().get("nomDetGest").toString();
            String nomGrupGest = ctx.getSessionScope().get("nomGrupGest") == null ? "" : ctx.getSessionScope().get("nomGrupGest").toString();
            String nomTipGest = ctx.getSessionScope().get("nomTipGest") == null ? "" : ctx.getSessionScope().get("nomTipGest").toString();
            String fecGestion = ctx.getSessionScope().get("fecGestion") == null ? "" : ctx.getSessionScope().get("fecGestion").toString();
            String obs = ctx.getSessionScope().get("obs") == null ? "" : ctx.getSessionScope().get("obs").toString();
            String dirAd = ctx.getSessionScope().get("dirAd") == null ? "" : ctx.getSessionScope().get("dirAd").toString();
            String emailAd = ctx.getSessionScope().get("emailAd") == null ? "" : ctx.getSessionScope().get("emailAd").toString();
            String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
            String cpgNom = ctx.getSessionScope().get("cpgNom") == null ? "" : ctx.getSessionScope().get("cpgNom").toString();
            String montoDesem = ctx.getSessionScope().get("montoDesem") == null ? "" : ctx.getSessionScope().get("montoDesem").toString();
            String fecha2daLlam = ctx.getSessionScope().get("fecha2daLlam") == null ? "" : ctx.getSessionScope().get("fecha2daLlam").toString();
            String contacto = ctx.getSessionScope().get("contacto") == null ? "" : ctx.getSessionScope().get("contacto").toString();
            String numDoc = ctx.getSessionScope().get("numDoc") == null ? "" : ctx.getSessionScope().get("numDoc").toString();
            String tipDoc = ctx.getSessionScope().get("tipDoc") == null ? "" : ctx.getSessionScope().get("tipDoc").toString();
            String flagInbound = ctx.getSessionScope().get("flagInbound") == null ? "" : ctx.getSessionScope().get("flagInbound").toString();
            String flagOutbound = ctx.getSessionScope().get("flagOutbound") == null ? "" : ctx.getSessionScope().get("flagOutbound").toString();
            String horaFeedback = ctx.getSessionScope().get("horaFeedback") == null ? "" : ctx.getSessionScope().get("horaFeedback").toString();
            String auxFeedbackAgendado = ctx.getSessionScope().get("auxFeedbackAgendado") == null ? "" : ctx.getSessionScope().get("auxFeedbackAgendado").toString();
            String prod = ctx.getSessionScope().get("prod") == null ? "" : ctx.getSessionScope().get("prod").toString();
            
            MergeLeadInvoke leadPayload = new MergeLeadInvoke();

            leadPayload.mergeLead(new Long(idLead),
                                nombreFb,
                                idCanal, 
                                canal,
                                idJefCanal, 
                                jefCanal,
                                idSup, 
                                sup, 
                                idEjVenta,
                                ejVenta,
                                telf, 
                                tipoGestion,
                                grupoGestion,
                                detalleGestion, 
                                new BigDecimal(auxFeedbackContactado),
                                new BigDecimal(auxFeedbackContactadoEfec),
                                new BigDecimal(auxFeedbackVenta),
                                new BigDecimal(auxFeedbackRevenueVenta),
                                nomTipGest,
                                nomGrupGest,
                                nomDetGest,
                                fecGestion, 
                                obs, 
                                telfAd,
                                dirAd,
                                emailAd, 
                                idCpg, 
                                cpgNom,
                                new BigDecimal(montoDesem.replaceAll(",", "")),
                                fecha2daLlam, 
                                contacto, 
                                numDoc,
                                tipDoc,
                                prod,
                                new Boolean(flagInbound),
                                new Boolean(flagOutbound), 
                                horaFeedback,
                                auxFeedbackAgendado);
            
            Util.Log("2");
            
            repGestionados(idLead, detalleGestion, nomDetGest);

        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    public void registraVenta(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
        
        SpeechVenta s = (SpeechVenta)ctx.getSessionScope().get("SpeechVenta");
        
        Util.Log("list speech --> " + s.getLeadID());
        
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
        
     //   String nombreFb = ctx.getSessionScope().get("nombreFb") == null ? "" : ctx.getSessionScope().get("nombreFb").toString();
        
        String telfAd = "-";
        String idLead = ctx.getSessionScope().get("idLead") == null ? "" : ctx.getSessionScope().get("idLead").toString();
        String nombreFb = ctx.getSessionScope().get("nombreFb") == null ? "" : ctx.getSessionScope().get("nombreFb").toString();
        String telf = ctx.getSessionScope().get("telf") == null ? "" : ctx.getSessionScope().get("telf").toString();
        String idCanal = ctx.getSessionScope().get("idCanal") == null ? "" : ctx.getSessionScope().get("idCanal").toString();
        String canal = ctx.getSessionScope().get("canal") == null ? "" : ctx.getSessionScope().get("canal").toString();
        String idJefCanal = ctx.getSessionScope().get("idJefCanal") == null ? "" : ctx.getSessionScope().get("idJefCanal").toString();
        String jefCanal = ctx.getSessionScope().get("jefCanal") == null ? "" : ctx.getSessionScope().get("jefCanal").toString();
        String idSup = ctx.getSessionScope().get("idSup") == null ? "" : ctx.getSessionScope().get("idSup").toString();
        String sup = ctx.getSessionScope().get("sup") == null ? "" : ctx.getSessionScope().get("sup").toString();
        String idEjVenta = ctx.getSessionScope().get("idEjVenta") == null ? "" : ctx.getSessionScope().get("idEjVenta").toString();
        String ejVenta = ctx.getSessionScope().get("ejVenta") == null ? "" : ctx.getSessionScope().get("ejVenta").toString();
        String tipoGestion = ctx.getSessionScope().get("tipoGestion") == null ? "" : ctx.getSessionScope().get("tipoGestion").toString();
        String grupoGestion = ctx.getSessionScope().get("grupoGestion") == null ? "" : ctx.getSessionScope().get("grupoGestion").toString();
        String detalleGestion = ctx.getSessionScope().get("detalleGestion") == null ? "" : ctx.getSessionScope().get("detalleGestion").toString();
        String auxFeedbackContactado = ctx.getSessionScope().get("auxFeedbackContactado") == null ? "" : ctx.getSessionScope().get("auxFeedbackContactado").toString();
        String auxFeedbackContactadoEfec = ctx.getSessionScope().get("auxFeedbackContactadoEfec") == null ? "" : ctx.getSessionScope().get("auxFeedbackContactadoEfec").toString();
        String auxFeedbackVenta = ctx.getSessionScope().get("auxFeedbackVenta") == null ? "" : ctx.getSessionScope().get("auxFeedbackVenta").toString();
        String auxFeedbackRevenueVenta = ctx.getSessionScope().get("auxFeedbackRevenueVenta") == null ? "" : ctx.getSessionScope().get("auxFeedbackRevenueVenta").toString();
        String nomDetGest = ctx.getSessionScope().get("nomDetGest") == null ? "" : ctx.getSessionScope().get("nomDetGest").toString();
        String nomGrupGest = ctx.getSessionScope().get("nomGrupGest") == null ? "" : ctx.getSessionScope().get("nomGrupGest").toString();
        String nomTipGest = ctx.getSessionScope().get("nomTipGest") == null ? "" : ctx.getSessionScope().get("nomTipGest").toString();
        String fecGestion = ctx.getSessionScope().get("fecGestion") == null ? "" : ctx.getSessionScope().get("fecGestion").toString();
        String obs = ctx.getSessionScope().get("obs") == null ? "" : ctx.getSessionScope().get("obs").toString();
        String dirAd = ctx.getSessionScope().get("dirAd") == null ? "" : ctx.getSessionScope().get("dirAd").toString();
        String emailAd = ctx.getSessionScope().get("emailAd") == null ? "" : ctx.getSessionScope().get("emailAd").toString();
        String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
        String cpgNom = ctx.getSessionScope().get("cpgNom") == null ? "" : ctx.getSessionScope().get("cpgNom").toString();
        String montoDesem = ctx.getSessionScope().get("montoDesem") == null ? "" : ctx.getSessionScope().get("montoDesem").toString();
        String fecha2daLlam = ctx.getSessionScope().get("fecha2daLlam") == null ? "" : ctx.getSessionScope().get("fecha2daLlam").toString();
        String contacto = ctx.getSessionScope().get("contacto") == null ? "" : ctx.getSessionScope().get("contacto").toString();
        String numDoc = ctx.getSessionScope().get("numDoc") == null ? "" : ctx.getSessionScope().get("numDoc").toString();
        String tipDoc = ctx.getSessionScope().get("tipDoc") == null ? "" : ctx.getSessionScope().get("tipDoc").toString();
        String flagInbound = ctx.getSessionScope().get("flagInbound") == null ? "" : ctx.getSessionScope().get("flagInbound").toString();
        String flagOutbound = ctx.getSessionScope().get("flagOutbound") == null ? "" : ctx.getSessionScope().get("flagOutbound").toString();
        String horaFeedback = ctx.getSessionScope().get("horaFeedback") == null ? "" : ctx.getSessionScope().get("horaFeedback").toString();
        String auxFeedbackAgendado = ctx.getSessionScope().get("auxFeedbackAgendado") == null ? "" : ctx.getSessionScope().get("auxFeedbackAgendado").toString();
        String prod = ctx.getSessionScope().get("prod") == null ? "" : ctx.getSessionScope().get("prod").toString();
        
        Util.Log("nombreFb --> " + nombreFb);
        
        try {
            
            if(txtTelfLlam.getValue() == null || txtTelfCasa.getValue() == null ||
                txtCelular.getValue() == null || cboEstCiv.getValue() == null ||
                cboCliGen.getValue() == null || txtDept.getValue() == null ||
                txtProv.getValue() == null || txtDist.getValue() == null ||
                txtDir.getValue() == null || txtRef.getValue() == null ||
                txtDeptEmp.getValue() == null || txtProvEmp.getValue() == null ||
                txtDistEmp.getValue() == null || txtDirEmp.getValue() == null ||
                txtRefEmp.getValue() == null || txtTelfEmp.getValue() == null ||
                cboCicFac.getValue() == null || cboConLab.getValue() == null ||
                cboLuEnIn.getValue() == null || cboTransInternac.getValue() == null ||
                cboTransInternet.getValue() == null || cboEnvEstCta.getValue() == null){
            
                FacesContext fctx = FacesContext.getCurrentInstance();
                             fctx.addMessage("",
                                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "No deben estar en blanco los campos obligatorios",
                                                          ""));
                             
               
            } else {
                
                
                
                if(nombreFb == null || nombreFb.equalsIgnoreCase("")){
                
                //Redireccionar a pantalla de búsqueda por dni
                
                    this.cbRegistrarVenta_click();
                
                    String ejecVenta = ctx.getSessionScope().get("ejVenta") == null ? "" : ctx.getSessionScope().get("ejVenta").toString();
                
                    String ejecutivoVenta = ejecVenta.replace(" ", ".");
                    Util.Log(ejecutivoVenta + "EJECUTIVO SPEECH");
                    Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + ejecutivoVenta);
                    
                } else {
                    
                    // Registrar Speech Venta
                //    this.cbRegistrarVenta_click();
                    
                   s.setLeadID(Util.nvl(s.getLeadID(), ""));
                   Util.Log("leadId --> " + s.getLeadID());
                   DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                   Date date = new Date();
                   s.setSpeechID(dateFormat.format(date));
                   
                   DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                   
                   try {
                       s.setClienteFechaNacimiento(df.format(this.txtFecNac.getValue()));
                   } catch (Exception e) {
                       s.setClienteFechaNacimiento(""); // e.getClienteFechaNacimiento());
                   }

                   s.setTelefonoLlamada(Util.nvl(this.txtTelfLlam,
                                                 "")); // e.getTelefonoLlamada());
                   s.setTelefonoCasa(Util.nvl(this.txtTelfCasa,
                                              "")); // e.getTelefonoCasa());
                   s.setTelefonoCelular(Util.nvl(this.txtCelular,
                                                 "")); // e.getTelefonoCelular());
                   s.setCorreoElectronico(Util.nvl(this.txtCorreo,
                                                   "")); // e.getCorreoElectronico());
                   s.setTelefonoReferencia(Util.nvl(this.txtTelfRef,
                                                    "")); // e.getTelefonoReferencia());
                   s.setParentescoCodigo(Util.nvl(this.txtParent,
                                                  "")); // e.getParentescoCodigo());
                   s.setParentescoDescripcion(Util.getSelectedItem(this.txtParent)); // e.getParentescoDescripcion());
                   s.setEstadoCivilCodigo(Util.nvl(this.cboEstCiv,
                                                   "")); // e.getEstadoCivilCodigo());
                   s.setEstadoCivilDescripcion(Util.getSelectedItem(this.cboEstCiv)); // e.getEstadoCivilDescripcion());
                   s.setGeneroCodigo(Util.nvl(this.cboCliGen,
                                              "")); // e.getGeneroCodigo());
                                              Util.Log("2.5");
                   s.setGeneroDescripcion(Util.getSelectedItem(this.cboCliGen)); // e.getGeneroDescripcion());
                   s.setConyugeApellidoPaterno(Util.nvl(this.txtApePatCony,
                                                        "")); // e.getConyugeApellidoPaterno());
                   s.setConyugeApellidoMaterno(Util.nvl(this.txtApeMatCony,
                                                        "")); // e.getConyugeApellidoMaterno());
                   s.setConyugeNombre1(Util.nvl(this.txtNomCony1,
                                                "")); // e.getConyugeNombre1());
                   s.setConyugeNombre2(Util.nvl(this.txtNomCony2,
                                                "")); // e.getConyugeNombre2());
                   try {
                       s.setConyugeFechaNacimiento(sdf.format(this.txtFecNacCony.getValue()));
                   } catch (Exception e) {
                       s.setConyugeFechaNacimiento(""); // e.getConyugeFechaNacimiento());
                   }

                   try {
                       s.setCentroLaboresFechaIngreso(sdf.format(this.txtFecIng.getValue()));
                   } catch (Exception e) {
                       s.setCentroLaboresFechaIngreso(""); // e.getConyugeFechaNacimiento());
                   }


                   s.setConyugeNumeroDocumento(Util.nvl(this.txtDniCony,
                                                        "")); // e.getConyugeNumeroDocumento());

                   s.setConyugeGenerocodigo(Util.nvl(this.cboCoGene, ""));
                   s.setConyugeGeneroDescripcion(Util.getSelectedItem(this.cboCoGene));
                   s.setDireccion(Util.nvl(this.txtDir, ""));

                   s.setDepartamento(Util.nvl(this.txtDept, ""));

                   s.setProvincia(Util.nvl(this.txtProv, ""));

                   s.setDistrito(Util.nvl(this.txtDist, ""));

                   s.setReferencia(Util.nvl(this.txtRef, "")); // e.getReferencia());

                   s.setCondicionLaboralCodigo(Util.nvl(this.cboConLab,
                                                        "")); // e.getCondicionLaboralCodigo());

                   s.setCondicionLaboralDescripcion(Util.getSelectedItem(this.cboConLab)); // e.getCondicionLaboralDescripcion());

                   s.setCentroLaboresNombre(Util.nvl(this.txtNomEmp,
                                                     "")); // e.getCentroLaboresNombre());

                   s.setCentroLaboresRuc(Util.nvl(this.txtRucEmp,
                                                  "")); // e.getCentroLaboresRuc());

                   s.setCentroLaboresDireccion(Util.nvl(this.txtDirEmp,
                                                        "")); // e.getCentroLaboresDireccion());

                   s.setCentroLaboresDepartamento(Util.nvl(this.txtDeptEmp, ""));

                   s.setCentroLaboresProvincia(Util.nvl(this.txtProvEmp, ""));

                   s.setCentroLaboresDistrito(Util.nvl(this.txtDistEmp, ""));

                   s.setCentroLaboresReferencia(Util.nvl(this.txtRefEmp,
                                                         "")); // e.getCentroLaboresReferencia());

                   s.setCentroLaboresTelefono(Util.nvl(this.txtTelfEmp,
                                                       "")); // e.getCentroLaboresTelefono());

                   s.setCentroLaboresAnexo(Util.nvl(this.txtAnexo,
                                                    "")); // e.getCentroLaboresAnexo());

                   s.setCentroLaboresCargoCodigo(Util.nvl(this.cboCeLaCa,
                                                          "")); // e.getCentroLaboresCargoCodigo());

                   s.setCentroLaboresCargoDescripcion(Util.nvl(this.cboCeLaCa, ""));

                   s.setLugarEntregaIndependienteCodigo(Util.nvl(this.cboLuEnIn,
                                                                 "")); // e.getLugarEntregaIndependienteCodigo());

                   s.setLugarEntregaIndependienteDescripcion(Util.getSelectedItem(this.cboLuEnIn)); // e.getLugarEntregaIndependienteDescripcion());

                   s.setLugarEntregaDependienteCodigo(Util.nvl(this.cboLuEnDe,
                                                               "")); 
                   
                   s.setTransInternac(Util.nvl(this.cboTransInternac,""));
                   
                   s.setTransXInternet(Util.nvl(this.cboTransInternet,""));
                   
                   s.setEnvEstCta(Util.nvl(this.cboEnvEstCta,""));
                   
                   s.setLugEnvEstCta(Util.nvl(this.cboLugEnvEstCta,""));

                   s.setLugarEntregaDependienteDescripcion(Util.getSelectedItem(this.cboLuEnDe)); // e.getLugarEntregaDependienteDescripcion());


                   s.setValorMembresia("-");
                   // s.setValorMembresia(txtMembAnual.getValue().toString());
                   s.setValorDesgravamen("-");
                   //s.setValorDesgravamen(txtDesgravamen.getValue().toString());
                   s.setCicloFacturacion(Util.getSelectedItem(this.cboCicFac));

                   String flaDis = "";

                   if (txtCheckFlaDis.getValue().toString().equalsIgnoreCase("true")) {
                       flaDis = "1";
                   } else {
                       flaDis = "0";
                   }

                   s.setFlagDisposicionEfectivo(flaDis);

                   String flaAut = "";

                   if (txtLDPDD.getValue().toString().equalsIgnoreCase("true")) {
                       flaAut = "1";
                   } else {
                       flaAut = "0";
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
                    
                    s.setTipoDeViaDom(tipoDeViaDom);
                    s.setNombreDeViaDom(nombreDeViaDom);
                    s.setNumeroDom(numeroDom);
                    s.setNroDptoDom(nroDptoDom);
                    s.setOficDom(oficDom);
                    s.setManzanaDom(manzanaDom);
                    s.setLoteDom(loteDom);
                    s.setInteriorDom(interiorDom);
                    s.setSectorDom(sectorDom);
                    s.setKmDom(kmDom);
                    s.setTipoZonaDom(tipoZonaDom);
                    s.setNombreZonaDom(nombreZonaDom);
                    s.setPisoDom(pisoDom);
                    //
                    s.setTipoDeViaLab(tipoDeViaLab);
                    s.setNombreDeViaLab(nombreDeViaLab);
                    s.setNumeroLab(numeroLab);
                    s.setNroDptoLab(nroDptoLab);
                    s.setOficLab(oficLab);
                    s.setManzanaLab(manzanaLab);
                    s.setLoteLab(loteLab);
                    s.setInteriorLab(interiorLab);
                    s.setSectorLab(sectorLab);
                    s.setKmLab(kmLab);
                    s.setTipoZonaLab(tipoZonaLab);
                    s.setNombreZonaLab(nombreZonaLab);
                    s.setPisoLab(pisoLab);
                    
                   s.setFlagAutorizacionLPDP(flaAut);
                   s.setTipoProd(nombreBin);
                    
                   Util.Log("4");
                    
                  //  ejecutaHiloSpeech(s);
                  Util.Log("inicio ejecuta insert tb FB");   
                  insertInfoFb(idLead, nombreFb, idCanal, canal, idJefCanal,
                               jefCanal, idSup, sup, idEjVenta, ejVenta, telf,
                               tipoGestion, grupoGestion, detalleGestion,
                               auxFeedbackContactado.toString(), auxFeedbackContactadoEfec.toString(),
                               auxFeedbackVenta.toString(), auxFeedbackRevenueVenta.toString(), nomTipGest,
                               nomGrupGest, nomDetGest, fecGestion, obs, telfAd, dirAd,
                               emailAd, idCpg, cpgNom, montoDesem.toString(), fecha2daLlam, contacto,
                               numDoc, tipDoc, prod, "true", "false", horaFeedback,
                               auxFeedbackAgendado);
                    
                    Util.Log("inicia hilo fb");
                    ejecutaHiloFeedbackSpeech(new Long(idLead),
                                nombreFb,
                                idCanal, 
                                canal,
                                idJefCanal, 
                                jefCanal,
                                idSup, 
                                sup, 
                                idEjVenta,
                                ejVenta,
                                telf, 
                                tipoGestion,
                                grupoGestion,
                                detalleGestion, 
                                new BigDecimal(auxFeedbackContactado),
                                new BigDecimal(auxFeedbackContactadoEfec),
                                new BigDecimal(auxFeedbackVenta),
                                new BigDecimal(auxFeedbackRevenueVenta),
                                nomTipGest,
                                nomGrupGest,
                                nomDetGest,
                                fecGestion, 
                                obs, 
                                telfAd,
                                dirAd,
                                emailAd, 
                                idCpg, 
                                cpgNom,
                                new BigDecimal(montoDesem.replaceAll(",", "")),
                                fecha2daLlam, 
                                contacto, 
                                numDoc,
                                tipDoc,
                                prod,
                                new Boolean(flagInbound),
                                new Boolean(flagOutbound), 
                                horaFeedback,
                                auxFeedbackAgendado,
                                s);
                 //   this.RegistrarFeedback();
                    limpiarSpeechVenta();

                    // Siguiente lead

              //      String idEjVenta = ctx.getSessionScope().get("idEjVenta") == null ? "" : ctx.getSessionScope().get("idEjVenta").toString();

              //      String ejVenta = ctx.getSessionScope().get("ejVenta") == null ? "" : ctx.getSessionScope().get("ejVenta").toString();
                    
                    String nomBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
                    
                    String nomEjecutivo = ejVenta.replace(" ", ".");
                        
             //     String idLead = ctx.getSessionScope().get("idLead") == null ? "" : ctx.getSessionScope().get("idLead").toString();

                    Util.Log("idEjVenta flag--> " + idEjVenta);
                    DosificacionEjecutivoBO.actualizarFlagUltimo2(idEjVenta);

                 //   DosificacionEjecutivoBO.eliminarLead(idLead);
                    Util.Log("nomEjecutivo --> " + nomEjecutivo);
                    Util.Log("inicia envío a otra pantalla");
                    Util.redirec("SBP_SLS_OPP_PAGE?userId=" + nomEjecutivo + "&nomBin=" + nomBin);
                    
                }
                
            }

 
        } catch (Exception e) {
            Util.Log(e.getMessage() + "ERROR");
        }
    }

    public String validarNullCamposDirec(RichInputText campo) {
        if(campo.getValue() != null){
            return campo.getValue().toString();
        }else{
            return "-";
        }
    }
    public void limpiarSpeechVenta() {

        txtTelfCasa.setValue("");
        txtCelular.setValue("");
        txtCorreo.setValue("");
        txtTelfRef.setValue("");
        txtParent.setValue("");
       // cboEstCiv.setValue("");
     //   cboCliGen.setValue("");
        txtNomCony1.setValue("");
        txtNomCony2.setValue("");
        txtApePatCony.setValue("");
        txtApeMatCony.setValue("");
        txtFecNac.setValue("");
        txtDniCony.setValue("");
      //  cboCoGene.setValue("");
        txtDir.setValue("");
        txtRef.setValue("");
      //  cboConLab.setValue("");
        txtNomEmp.setValue("");
        txtRucEmp.setValue("");
        txtFecIng.setValue("");
        txtDirEmp.setValue("");
        txtRefEmp.setValue("");
        txtTelfEmp.setValue("");
        txtAnexo.setValue("");
      //  cboLuEnIn.setValue("");
       // cboLuEnDe.setValue("");
       // cboCicFac.setValue("");
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String createInsert1() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsert2() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }
    
    public void descargarSpeech(FacesContext facesContext,
                                OutputStream out) throws IOException,
                                                         JSchException,
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
    
    public void generarTxt(String subProducto, String codCpg, String empUndVta, String promotor, String tipEmpleo, String tipDoc, String nroDoc, String apPaterno, String apMaterno,
                           String primerNombre, String segNombre, String sexo, String fecNac, String codTelf, String telf, String celular, String nacionalidad, String estadoCivil,
                           String correspondencia, String tipRefCony, String tipDocCony, String nroDocCony, String apPaternoCony, String apMaternoCony, String primerNombreCony, 
                           String segNombreCony, String sexoCony, String fecNacCony, String tipVia, String nomVia, String numero, String nroDept, String oficina, String piso, String mzna,
                           String lote, String interior, String sector, String kilometro, String tipZona, String nomZona, String dept, String prov, String dist, String ref, String email,
                           String celAvisoEmi, String agenciaSect, String ruc, String codTelfLab, String telfLab, String faxLab, String faxCel, String razonSocial, String emailLab, String formPago,
                           String fecIngreso, String tipViaLab, String nomViaLab, String numLab, String nroDeptLab, String ofLab, String pisoLab, String mznaLab, String lotLab, String intLab,
                           String sectorLab, String kmLab, String tipZonLab, String nomZonLab, String deptLab, String provLab, String distLab, String refLab, String segmento, String cpgTC, 
                           String ingresoMancomunado, String dispEfect, String compraDeuda, String ventaExtralinea, String paperless, String usoDatos, String envEstCta, String transaccInternet,
                           String transacInternac, String lugarEntregaTC, String agenciaEntregaTC, String embose, String tipTarjeta, String cicloFact) {
        try {

            ADFContext ctx = ADFContext.getCurrent();

            String nomContacto = ctx.getSessionScope().get("contactoFileSpeech") == null ? "" : ctx.getSessionScope().get("contactoFileSpeech").toString();

            //FECHA HORA FEEDBACK
                     
             final Date currentTimeFechaHoraGest = new Date();
    
             final SimpleDateFormat sdfFechaHoraGest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
             // Give it to me in GMT time.
             sdfFechaHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
             
             String fechaHoraFb = sdfFechaHoraGest.format(currentTimeFechaHoraGest);
             Util.Log("Fecha Hora Feedback --> " + fechaHoraFb);

            String host = System.getProperty("http.proxyHost");

            JSch jsch = new JSch();

            Session session = null;
            
            Util.Log("inicio usuario");
            
            //SFTP SBP

        /*    session = jsch.getSession("usftp311", "190.116.5.24", 22);

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

            String nomFile = "speechVenta " + nomContacto + "-" + fechaHoraFb;
            Util.Log(nomFile + "nomFile");

            String speechVenta = "SubProducto,Código Campaña,Empresa/Und. Vta.,Promotor,Tipo Empleo,Tipo  Documento,Nro. Documento,Ap. Paterno,Ap. Materno,Nombre(1),Nombre(2),Sexo,Fecha de Nacimiento,Teléfono ( Códigos Telefonicos ),Teléfono,Celular,Nacionalidad,Estado civil,Correspondencia,Tipo de Referencia(Conyugue),Tipo  Documento (Conyugue),Nro. Documento(Conyugue),Ap. Paterno(Conyugue),Ap.Materno(Conyugue),Nombre(1) (Conyugue),Nombre(2) (Conyugue),Sexo Conyugue,Fecha Nacimiento Conyugue,Tipo de Vía,Nombre de Vía,Número,Nro. Dpto,Oficina,Piso,Manzana,Lote,Interior,Sector,Kilometro,Tipo de Zona,Nombre Zona,Departamento,Provincia,Distrito,Referencia,E-Mail,Celular Aviso Emisión,Agencia Sectorización,RUC,Teléfono ( Códigos Telefonicos) Laboral,Teléfono Laboral,Fax Laboral,FAX / CEL,Razón Social,E-Mail Laboral,Forma de Pago,Fecha de Ingreso,Tipo de Vía Laboral,Nombre deVía Laboral,Número Laboral,Nro. Dpto Laboral,Oficina Laboral,Piso Laboral,Manzana Laboral,Lote Laboral,Interior Laboral,Sector Laboral,Kilometro Laboral,Tipo de Zona Laboral,Nombre Zona Laboral,Departamento Laboral,Provincia Laboral,Distrito Laboral,ReferenciaLaboral,Segmento,Campaña T/C (Codigo Ensobrado),Ingresos Mancomunados?,Disposición Efectivo,Compra de Deuda?,Venta conExtralinea?,Procesar Paperless,¿Permitir utilización de datos?,Envio de Estado de Cuenta,Transacciones por Internet,TransaccionesInternacionales,Lugar Entrega de Tarjeta,Agencia Entrega de Tarjeta,Nombre en Tarjeta (Embose),Tipo De Tarjeta,Ciclo de Facturación" +
                "\n" + subProducto + "," + codCpg + "," +   empUndVta + "," +   promotor + "," +   tipEmpleo + "," +   tipDoc + "," +   nroDoc + "," +   apPaterno + "," +   apMaterno + "," + 
                            primerNombre + "," +   segNombre + "," +   sexo + "," +   fecNac + "," +   codTelf + "," +   telf + "," +   celular + "," +   nacionalidad + "," +   estadoCivil + "," + 
                            correspondencia + "," +   tipRefCony + "," +   tipDocCony + "," +   nroDocCony + "," +   apPaternoCony + "," +   apMaternoCony + "," +   primerNombreCony + "," +  
                            segNombreCony + "," +   sexoCony + "," +   fecNacCony + "," +   tipVia + "," +   nomVia + "," +   numero + "," +   nroDept + "," +   oficina + "," +   piso + "," +   mzna + "," + 
                            lote + "," +   interior + "," +   sector + "," +   kilometro + "," +   tipZona + "," +   nomZona + "," +   dept + "," +   prov + "," +   dist + "," +   ref + "," +   email + "," + 
                            celAvisoEmi + "," +   agenciaSect + "," +   ruc + "," +   codTelfLab + "," +   telfLab + "," +   faxLab + "," +   faxCel + "," +   razonSocial + "," +   emailLab + "," +   formPago + "," + 
                            fecIngreso + "," +   tipViaLab + "," +   nomViaLab + "," +   numLab + "," +   nroDeptLab + "," +   ofLab + "," +   pisoLab + "," +   mznaLab + "," +   lotLab + "," +   intLab + "," + 
                            sectorLab + "," +   kmLab + "," +   tipZonLab + "," +   nomZonLab + "," +   deptLab + "," +   provLab + "," +   distLab + "," +   refLab + "," +   segmento + "," +   cpgTC + "," +  
                            ingresoMancomunado + "," +   dispEfect + "," +   compraDeuda + "," +   ventaExtralinea + "," +   paperless + "," +   usoDatos + "," +   envEstCta + "," +   transaccInternet + "," + 
                            transacInternac + "," +   lugarEntregaTC + "," +   agenciaEntregaTC + "," +   embose + "," +   tipTarjeta + "," +   cicloFact;
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
    
    public String getFileName() {
        
            ADFContext ctx = ADFContext.getCurrent();
            
            String nombreBin = ctx.getSessionScope().get("nombreBin") == null ? "" : ctx.getSessionScope().get("nombreBin").toString();
            
            String nombrePdf = nombreBin + ".pdf";

            return nombrePdf;
            
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
    
    public void borrarFecProg(String idLead) {

        Util.Log(idLead + "LEAD ID");

        PreparedStatement prepareStatement = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;

        String query = "BEGIN SBP_SLS_LEAD_PKG.borrarFecProg(?);END;";

        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, idLead);

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

    public void setTxtTextoSpeech(RichInputText txtTextoSpeech) {
        this.txtTextoSpeech = txtTextoSpeech;
    }

    public RichInputText getTxtTextoSpeech() {
        return txtTextoSpeech;
    }

    public void setCboCeLaCa(RichInputText cboCeLaCa) {
        this.cboCeLaCa = cboCeLaCa;
    }

    public RichInputText getCboCeLaCa() {
        return cboCeLaCa;
    }
    
    public void repGestionados(String idLead,
                               String detalleGestion,
                               String nomDetGest) throws ParserConfigurationException,
                                                         SAXException,
                                                         IOException {
        
        ServiceBiPublisher bi = new ServiceBiPublisher();
        
        List<Feedback> listLogRepGest = new ArrayList<Feedback>();
        
        MergeLeadInvoke leadPayload = new MergeLeadInvoke();
        
        //---------------------REPORTE AGENDADOS--------------------------------//
                        Util.Log("CREAR FB 2");

                        Util.Log("idLead --> " + idLead);

                        listLogRepGest = bi.obtenerInfoLogicaRepGest(idLead);

                        int valGestApex = Integer.parseInt(detalleGestion);

                        int valGestSales = Integer.parseInt(listLogRepGest.get(0).getValorGest());

                        Util.Log("valGestApex --> " + valGestApex);

                        Util.Log("valGestSales --> " + valGestSales);

                        Util.Log("TAMAÑO LIST listLogRepGest --> " +
                                 listLogRepGest.size());

                        if (valGestApex > valGestSales) {

                            leadPayload.mergeLeadGest(Long.parseLong(listLogRepGest.get(0).getIdLead()),
                                                      Long.parseLong(listLogRepGest.get(0).getIdFb()),
                                                      detalleGestion, nomDetGest);

                        } else {

                            leadPayload.mergeLeadGest(Long.parseLong(listLogRepGest.get(0).getIdLead()),
                                                      Long.parseLong(listLogRepGest.get(0).getIdFb()),
                                                      listLogRepGest.get(0).getValorGest(),
                                                      listLogRepGest.get(0).getMejorGest());
                        }

                    //---------------------REPORTE AGENDADOS--------------------------------//
    }
    
    public String generarIdFb() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idFb = "";


        try {
            String query =
                "select sbp_sls_info_lead_fb_s.nextval ID_FB from dual";

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                idFb = rs.getString("ID_FB");
            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        
        return idFb;
    }
    
    public void insertInfoFb(String idLead,
                             String nombreFb,
                             String idCanal,
                             String canal,
                             String idJefCanal,
                             String jefCanal,
                             String idSup,
                             String sup,
                             String idEjVenta,
                             String ejVenta,
                             String telefono,
                             String tipoGestion,
                             String grupoGestion,
                             String detalleGestion,
                             String auxFeedbackContactado,
                             String auxFeedbackContactadoEfec,
                             String auxFeedbackVenta,
                             String auxFeedbackRevenueVenta,
                             String nomTipGest,
                             String nomGrupGest,
                             String nomDetGest,
                             String fecGestion,
                             String observacion,
                             String telfAd,
                             String dirAd,
                             String emailAd,
                             String idCpg,
                             String cpgNom,
                             String montoDesem,
                             String fecha2daLlam,
                             String contacto,
                             String numDoc,
                             String tipDoc,
                             String prod,
                             String flagInbound,
                             String flagOutbound,
                             String horaFeedback,
                             String auxFeedbackAgendado){
            
             Connection conn = null;
             PreparedStatement st = null;
             ResultSet rs = null;    
             String idFb = generarIdFb();
                 
             Util.Log("idFb --> " + idFb);
             
               try {
                   
                     String query = "insert into sbp_sls_info_lead_fb values('" + idLead + "', " + 
                                    "'" + nombreFb +  "'," + 
                                    "'" + idCanal +  "'," + 
                                    "'" + canal +  "'," + 
                                    "'" + idJefCanal +  "'," + 
                                    "'" + jefCanal +  "'," + 
                                    "'" + idSup +  "'," + 
                                    "'" + sup +  "'," + 
                                    "'" + idEjVenta +  "'," + 
                                    "'" + ejVenta +  "'," + 
                                    "'" + telefono +  "'," + 
                                    "'" + tipoGestion +  "'," + 
                                    "'" + grupoGestion +  "'," + 
                                    "'" + detalleGestion +  "'," + 
                                    "'" + auxFeedbackContactado +  "'," + 
                                    "'" + auxFeedbackContactadoEfec +  "'," + 
                                    "'" + auxFeedbackVenta +  "'," + 
                                    "'" + auxFeedbackRevenueVenta +  "'," + 
                                    "'" + nomTipGest +  "'," + 
                                    "'" + nomGrupGest +  "'," + 
                                    "'" + nomDetGest +  "'," + 
                                    "'" + fecGestion +  "'," + 
                                    "'" + observacion +  "'," + 
                                    "'" + telfAd +  "'," + 
                                    "'" + dirAd +  "'," + 
                                    "'" + emailAd +  "'," + 
                                    "'" + idCpg +  "'," + 
                                    "'" + cpgNom +  "'," + 
                                    "'" + montoDesem + "'," +
                                    "'" + fecha2daLlam +  "'," + 
                                    "'" + contacto +  "'," + 
                                    "'" + numDoc +  "'," + 
                                    "'" + tipDoc +  "'," + 
                                    "'" + prod +  "'," + 
                                    "'" + flagInbound +  "'," + 
                                    "'" + flagOutbound +  "'," + 
                                    "'" + horaFeedback +  "'," + 
                                    "'" + auxFeedbackAgendado +  "'," + 
                                    "'" + idFb + "')";
                   
                   Util.Log(query);

                     sbp.utils.Connection connection = new sbp.utils.Connection();

                     conn = connection.getConnection();

                     st = conn.prepareStatement(query);

                     st.execute();
                   
                 } catch (SQLException e) {
                     Util.Log(e + "error");
                 } finally {
                     DbUtils.closeQuietly(rs);
                     DbUtils.closeQuietly(st);
                     DbUtils.closeQuietly(conn);
                 }
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

    public void setCboTransInternac(RichSelectOneChoice cboTransInternac) {
        this.cboTransInternac = cboTransInternac;
    }

    public RichSelectOneChoice getCboTransInternac() {
        return cboTransInternac;
    }

    public void setCboLugEnvEstCta(RichSelectOneChoice cboLugEnvEstCta) {
        this.cboLugEnvEstCta = cboLugEnvEstCta;
    }

    public RichSelectOneChoice getCboLugEnvEstCta() {
        return cboLugEnvEstCta;
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

    public void mostrarDirecDom(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popUpDirecDom.show(hints);
    }

    public void mostrarDirecLab(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popUpDirecLab.show(hints);
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

    public void setPopUpDirecLab(RichPopup popUpDirecLab) {
        this.popUpDirecLab = popUpDirecLab;
    }

    public RichPopup getPopUpDirecLab() {
        return popUpDirecLab;
    }

    public void setPopUpDirecDom(RichPopup popUpDirecDom) {
        this.popUpDirecDom = popUpDirecDom;
    }

    public RichPopup getPopUpDirecDom() {
        return popUpDirecDom;
    }

    public class TaskRunST implements Runnable {
        
        Long idLead= 0L;
        String nombreFb= "";
        String idCanal= "";
        String canal= "";
        String idJefCanal= "";
        String jefCanal= "";
        String idSup= "";
        String sup= "";
        String idEjVenta= "";
        String ejVenta= "";
        String telefono= "";
        String tipoGestion= "";
        String grupoGestion= "";
        String detalleGestion= "";
        BigDecimal auxFeedbackContactado= new BigDecimal(0);
        BigDecimal auxFeedbackContactadoEfec= new BigDecimal(0);
        BigDecimal auxFeedbackVenta= new BigDecimal(0);
        BigDecimal auxFeedbackRevenueVenta= new BigDecimal(0);
        String nomTipGest= "";
        String nomGrupGest= "";
        String nomDetGest= "";
        String fecGestion= "";
        String obs= "";
        String telfAd= "";
        String dirAd= "";
        String emailAd= "";
        String idCpg= ""; 
        String cpgNom= "";
        BigDecimal montoDesem =  new BigDecimal(0);
        String fecha2daLlam= "";
        String contacto= "";
        String numDoc= "";
        String tipDoc= "";
        String prod= "";
        Boolean flagOutbound = true;
        Boolean flagInbound = false;
        String horaFeedback= "";
        String auxFeedbackAgendado = "";   
        SpeechVenta listSpeech = new SpeechVenta();
        
        
       public TaskRunST(Long idLead,String nombreFb, String idCanal, String canal,
                        String idJefCanal, String jefCanal, String idSup,
                        String sup, String idEjVenta, String ejVenta,
                        String telefono, String tipoGestion,
                        String grupoGestion, String detalleGestion,
                        BigDecimal auxFeedbackContactado,
                        BigDecimal auxFeedbackContactadoEfec,
                        BigDecimal auxFeedbackVenta,
                        BigDecimal auxFeedbackRevenueVenta,
                        String nomTipGest, String nomGrupGest,
                        String nomDetGest, String fecGestion, String obs,
                        String telfAd, String dirAd, String emailAd,
                        String idCpg, String cpgNom, BigDecimal montoDesem,
                        String fecha2daLlam, String contacto,
                        String numDoc, String tipDoc, String prod,
                        Boolean flagInbound, Boolean flagOutbound,
                        String horaFeedback, String auxFeedbackAgendado,
                        SpeechVenta listSpeech) {
            
                this.idLead = idLead;
                this.nombreFb  = nombreFb;
                this.idCanal = idCanal;
                this.canal = canal;
                this.idJefCanal = idJefCanal;
                this.jefCanal = jefCanal;
                this.idSup = idSup;
                this.sup = sup;
                this.idEjVenta = idEjVenta;
                this.ejVenta = ejVenta;
                this.telefono = telefono;
                this.tipoGestion = tipoGestion;
                this.grupoGestion = grupoGestion;
                this.detalleGestion = detalleGestion;
                this.auxFeedbackContactado = auxFeedbackContactado;
                this.auxFeedbackContactadoEfec = auxFeedbackContactadoEfec;
                this.auxFeedbackVenta = auxFeedbackVenta;
                this.auxFeedbackRevenueVenta = auxFeedbackRevenueVenta;
                this.nomTipGest = nomTipGest;
                this.nomGrupGest = nomGrupGest;
                this.nomDetGest = nomDetGest;
                this.fecGestion = fecGestion;
                this.obs = obs;
                this.telfAd = telfAd;
                this.dirAd = dirAd;
                this.emailAd = emailAd;
                this.idCpg = idCpg; 
                this.cpgNom = cpgNom;
                this.montoDesem = montoDesem;
                this.fecha2daLlam = fecha2daLlam;
                this.contacto = contacto;
                this.numDoc = numDoc;
                this.tipDoc = tipDoc;
                this.prod = prod;
                this.flagInbound = flagInbound;
                this.flagOutbound = flagOutbound;
                this.horaFeedback = horaFeedback;
                this.auxFeedbackAgendado  = auxFeedbackAgendado;
                this.listSpeech = listSpeech;
                
            }
       


        public void run() throws RuntimeException{}

    }
    
    public class TaskRunPT implements Runnable {
          
          SpeechVenta listSpeech = new SpeechVenta();   
          
          public TaskRunPT(SpeechVenta listSpeech) {
              
                  this.listSpeech = listSpeech;
              
                  }

          public void run() throws RuntimeException  {}

      }
    
    public void ejecutaHiloFeedbackSpeech(Long idLead,String nombreFb, String idCanal, String canal,
                        String idJefCanal, String jefCanal, String idSup,
                        String sup, String idEjVenta, String ejVenta,
                        String telefono, String tipoGestion,
                        String grupoGestion, String detalleGestion,
                        BigDecimal auxFeedbackContactado,
                        BigDecimal auxFeedbackContactadoEfec,
                        BigDecimal auxFeedbackVenta,
                        BigDecimal auxFeedbackRevenueVenta,
                        String nomTipGest, String nomGrupGest,
                        String nomDetGest, String fecGestion, String obs,
                        String telfAd, String dirAd, String emailAd,
                        String idCpg, String cpgNom, BigDecimal montoDesem,
                        String fecha2daLlam, String contacto,
                        String numDoc, String tipDoc, String prod,
                        Boolean flagInbound, Boolean flagOutbound,
                        String horaFeedback, String auxFeedbackAgendado, SpeechVenta listSpeech){
        
        Thread th = null;
        
        try{
            th = new Thread(new TaskRunST(idLead, nombreFb,  idCanal,  canal,
                                            idJefCanal,  jefCanal,  idSup,
                                            sup,  idEjVenta,  ejVenta,
                                            telefono,  tipoGestion,
                                            grupoGestion,  detalleGestion,
                                            auxFeedbackContactado,
                                            auxFeedbackContactadoEfec,
                                            auxFeedbackVenta,
                                            auxFeedbackRevenueVenta,
                                            nomTipGest, nomGrupGest,
                                            nomDetGest, fecGestion, obs,
                                            telfAd, dirAd,  emailAd,
                                            idCpg,  cpgNom,  montoDesem,
                                            fecha2daLlam,  contacto,
                                            numDoc,  tipDoc,  prod,
                                            flagInbound, flagOutbound,
                                            horaFeedback, auxFeedbackAgendado, listSpeech) {
                
            @Override
            public void run() throws RuntimeException  {
                
                MergeLeadInvoke leadPayload = new MergeLeadInvoke();

                   try {
                       
                       Util.Log("idLead -- >" + idLead);
                                
                          leadPayload.mergeLead(idLead,
                                      nombreFb, idCanal, canal,
                                      idJefCanal, jefCanal, idSup,
                                      sup, idEjVenta, ejVenta,
                                      telefono, tipoGestion,
                                      grupoGestion, detalleGestion,
                                      auxFeedbackContactado,
                                      auxFeedbackContactadoEfec,
                                      auxFeedbackVenta,
                                      auxFeedbackRevenueVenta,
                                      nomTipGest, nomGrupGest,
                                      nomDetGest, fecGestion, obs,
                                      telfAd, dirAd, emailAd,
                                      idCpg, cpgNom, montoDesem,
                                      fecha2daLlam, contacto,
                                      numDoc, tipDoc, prod,
                                      flagInbound, flagOutbound,
                                      horaFeedback,
                                      auxFeedbackAgendado);
                       
                                MergeLeadClient mlc = new MergeLeadClient();
                                
                                Proceso p = new Proceso();

                                Conexion c = Util.DatosConexionSBP();
                                    
                                 p = mlc.mergeLeadSpeechVenta(c, listSpeech);//VNL
                                                
                                
                                //---------------------REPORTE AGENDADOS--------------------------------//
                                
                                List<Feedback> listLogRepGest = new ArrayList<Feedback>();
                                
                                ServiceBiPublisher bi = new ServiceBiPublisher();

                                listLogRepGest = bi.obtenerInfoLogicaRepGest(idLead.toString());

                                int valGestApex = Integer.parseInt(detalleGestion);

                                int valGestSales = Integer.parseInt(listLogRepGest.get(0).getValorGest());

                                Util.Log("valGestApex --> " + valGestApex);

                                Util.Log("valGestSales --> " + valGestSales);

                                Util.Log("TAMAÑO LIST listLogRepGest --> " + listLogRepGest.size());

                                if (valGestApex > valGestSales) {

                                leadPayload.mergeLeadGest(Long.parseLong(listLogRepGest.get(0).getIdLead()),
                                              Long.parseLong(listLogRepGest.get(0).getIdFb()),
                                              detalleGestion, nomDetGest);

                                } else {

                                leadPayload.mergeLeadGest(Long.parseLong(listLogRepGest.get(0).getIdLead()),
                                              Long.parseLong(listLogRepGest.get(0).getIdFb()),
                                              listLogRepGest.get(0).getValorGest(),
                                              listLogRepGest.get(0).getMejorGest());
                                }

                                //---------------------REPORTE AGENDADOS--------------------------------//
                            } catch (ParserConfigurationException e) {
                                e.printStackTrace();
                            } catch (SAXException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                
        });
            
        th.start();    

        }catch(Exception e){
        System.out.println(e);
        }
    }
    
    public void ejecutaHiloSpeech(SpeechVenta listSpeech){
        
        Thread th2 = null;
        
        try{
            th2 = new Thread(new TaskRunPT(listSpeech) {
                
                
            @Override
            public void run() throws RuntimeException  {
                
                Util.Log("listSpeech --> " + listSpeech.getLeadID());
                
                MergeLeadClient mlc = new MergeLeadClient();
                
                Proceso p = new Proceso();

                Conexion c = Util.DatosConexionSBP();
                    
                 p = mlc.mergeLeadSpeechVenta(c, listSpeech);
                                
                 }
                
        });
        Util.Log("inicia hilo speech");
        th2.start();    

        }catch(Exception e){
        System.out.println(e);
        }
    }

}

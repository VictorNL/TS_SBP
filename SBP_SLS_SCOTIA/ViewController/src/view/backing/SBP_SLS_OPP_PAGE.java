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

import dao.EjVenta;
import dao.Feedback;

import dao.LoginUser;
import dao.OptyRandom;

import dao.Producto;
import dao.Tarjetas;

import dao.lista.ListaLead;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.TimeZone;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceException;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichGoLink;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;


import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import org.xml.sax.SAXException;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_OPP_PAGE  {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelBox pb3;
    private RichPanelBox pb4;
    private RichMessages m1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText it4;
    private RichInputText it5;
    private RichInputText it6;
    private RichInputText it7;
    private RichInputText it8;
    private RichInputText it9;
    private RichInputText it10;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s1;
    private RichInputText txtIdOpty;
    private RichCommandButton btnTelf01;
    private RichInputText txtIdEjVenta;
    private RichInputText txtMonto;
    private RichPopup popupFeedback;
    private RichTable tblFeedback;
    private RichSelectOneChoice txtTipGest;
    private RichSelectOneChoice txtDetGest;
    private RichInputText txtFeedback;
    private RichSelectOneChoice txtGrupGest;
    private RichInputText txtEjVenta;
    private RichInputText txtCodTipGest;
    private RichInputText txtCodGrupGest;
    private RichInputText txtCodDetGest;
    private RichOutputText txtFecGestion;
    private RichPanelBox pnlFeedback;
    private RichInputText txtEmailAd;
    private RichInputText txtDirAd;
    private RichInputText txtTelfAd;
    private RichInputText txtObs;
    private RichCommandButton btnOpty1;
    private RichCommandButton btnOpty2;
    private RichCommandButton btnOpty3;
    private RichInputText txtJefCanal;
    private RichInputText txtIdSup;
    private RichInputText txtSup;
    private RichInputText txtIdJefCanl;
    private RichInputText txtIdJefCanal;
    private RichInputText txtCanal;
    private RichInputText txtIdCanal;
    private RichInputText txtCpgId;
    private RichInputText txtLeadOld;
    private RichInputText txtLeadNew;
    private RichInputText txtIdEj;
    private RichInputText txtCanalId;
    private RichInputDate txt2daLlam;
    private RichInputText txtEjecutivoVenta;
    private RichPopup popupConfirm;
    private RichPopup popupTelfExtra;
    private RichInputText txtFecGest;
    private RichInputText txtFeedbackTlfExt;
    private RichInputText txtFecGestTlfExt;
    private RichInputText txtTelfExtra;
    private RichSelectOneChoice txtTipGestTlfExt;
    private RichSelectOneChoice txtGrupGestTlfExt;
    private RichSelectOneChoice txtDetGestTlfExt;
    private RichInputText txtCodTipGestTlfExt;
    private RichInputText txtCodGrupGestTlfExt;
    private RichInputText txtCodDetGestTlfExt;
    private RichPopup popupMensajeError;
    private RichCommandButton btnTelfExtra;
    private RichOutputLabel txtNombre;
    private RichOutputLabel txtEdad;
    private RichOutputLabel txtSexo;
    private RichOutputLabel txtFecNac;
    private RichOutputLabel txtTipDoc;
    private RichOutputLabel txtNumDoc;
    private RichOutputLabel txtEmail;
    private RichOutputLabel txtProd;
    private RichOutputLabel txtCuota;
    private RichOutputLabel txtMontoMinimo;
    private RichOutputLabel txtPlazoMinimo;
    private RichOutputLabel txtPlazoMaximo;
    private RichOutputLabel txtMontoMaximo;
    private RichCommandLink txtTelf01;
    private RichCommandLink txtCel01;
    private RichCommandLink txtTelf02;
    private RichCommandLink txtCel02;
    private RichCommandLink txtTelf03;
    private RichCommandLink txtCel03;
    private RichOutputLabel txtAvance;
    private RichOutputLabel txtDeudaTc;
    private RichOutputLabel txtBancoTc;
    private RichOutputLabel txtDeudaPp;
    private RichOutputLabel txtBancoPp;
    private RichOutputLabel txtLinTc;
    private RichOutputLabel txtSbs;
    private RichOutputText txtContFb;
    private RichCommandButton btnSigVenta;
    private RichInputText txtMontMin;
    private RichInputText txtMonMax;
    private RichPopup popupMensajeMonto;
    private RichOutputLabel txtMensajeVal;
    private RichPopup popupValidarCampos;
    private RichOutputLabel txtProvincia;
    private RichOutputLabel txtDistrito;
    private RichOutputLabel txtDpto;
    private RichOutputLabel txtDireccion;
    private RichInputText txtMontoFb;
    private RichInputText txtMontoFbExtra;
    private RichInputText txtCodDept;
    private RichInputText txtCodProv;
    private RichInputText txtCodDist;
    private RichTable tblTarjeta;
    private RichPopup popupTarjetas;
    private RichSelectOneChoice soc7;
    private RichInputText itGestion;
    private RichPopup popupSpeech;
    private RichSelectBooleanCheckbox checkFlaDis;
    private RichSelectBooleanCheckbox checkFlaAut;
    private RichInputDate dtCoFeNa;
    private RichInputDate dtCeLaFI;
    private RichInputText txtTelLla;
    private RichInputDate dtFecNaS;
    private RichInputText itT01;
    private RichInputText itT02;
    private RichInputText itT03;
    private RichInputText itT04;
    private RichInputText itT05;
    private RichInputText itT06;
    private RichOutputLabel txtTarjeta1;
    private RichOutputLabel txtTarjeta2;
    private RichOutputLabel txtTarjeta3;
    private RichOutputLabel txtTarjeta4;
    private RichOutputLabel txtTcTea1;
    private RichOutputLabel txtTcTea2;
    private RichOutputLabel txtTcTea3;
    private RichOutputLabel txtMontoProd;
    private RichOutputText txtValEn2;
    private RichInputText txtValorTelf;
    private RichSelectOneChoice cboCliDep;
    private RichSelectOneChoice cboCliPro;
    private RichSelectOneChoice cboCliDis;
    private RichSelectOneChoice cboCeLaDe;
    private RichSelectOneChoice cboCeLaPr;
    private RichSelectOneChoice cboCeLaDs;
    private RichInputText txtCodDeptSpeech;
    private RichInputText txtCodProvSpeech;
    private RichInputText txtCodDistSpeech;
    private RichSelectOneChoice deptPrueba;
    private RichOutputLabel txtNomMon;
    private RichOutputLabel txtNomProd;
    private RichOutputLabel txtPromocion;
    private RichCommandLink txtBanco1Link;
    private RichCommandLink txtBanco2Link;
    private RichCommandLink txtBanco3Link;
    private RichInputText txtMemAn;
    private RichInputText txtSegDes;
    private RichTable tblTarjetaUbigeo;
    private RichInputText txtProdSel;
    private RichPopup popupCobertura;
    private RichInputText txtDescProm;
    private RichPopup popup2daLlamada;
    static int cont;
    private RichOutputText txtMsjBienv;
    private RichPanelSplitter pnlMsj;
    private RichPanelSplitter pnlMsjOro;
    private RichInputText txtProm2;
    private RichInputText txtProm3;
    private RichOutputLabel txtPromSpeech2;
    private RichOutputLabel txtPromSpeech3;
    private RichInputText txtApeMat;
    private RichInputText txtApePat;
    private RichInputText txtSegNom;
    private RichInputText txtPrimNom;
    private RichOutputLabel txtConsentimiento;
    private RichOutputLabel txtPromNueva;
    private RichCommandLink txtTelf1TipUser;
    private RichCommandLink txtCel3TipUser;
    private RichCommandLink txttelf3TipUser;
    private RichCommandLink txtCel2TipUser;
    private RichCommandLink txttelf2TipUser;
    private RichCommandLink txtCel1TipUser;
    private RichGoLink glTelf01;
    private RichGoLink glTelf02;
    private RichGoLink glTelf03;    
    private RichCommandLink txtTelfNew01;
    private RichCommandLink txtTelfNew02;
    private RichCommandLink txtTelfNew03;
    private RichOutputText txtTelfFb;
    private RichPanelLabelAndMessage txtLabelTelfFb;

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

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
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

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setIt5(RichInputText it5) {
        this.it5 = it5;
    }

    public RichInputText getIt5() {
        return it5;
    }

    public void setIt6(RichInputText it6) {
        this.it6 = it6;
    }

    public RichInputText getIt6() {
        return it6;
    }

    public void setIt7(RichInputText it7) {
        this.it7 = it7;
    }

    public RichInputText getIt7() {
        return it7;
    }

    public void setIt8(RichInputText it8) {
        this.it8 = it8;
    }

    public RichInputText getIt8() {
        return it8;
    }

    public void setIt9(RichInputText it9) {
        this.it9 = it9;
    }

    public RichInputText getIt9() {
        return it9;
    }

    public void setIt10(RichInputText it10) {
        this.it10 = it10;
    }

    public RichInputText getIt10() {
        return it10;
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

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setTxtIdOpty(RichInputText txtIdOpty) {
        this.txtIdOpty = txtIdOpty;
    }

    public RichInputText getTxtIdOpty() {
        return txtIdOpty;
    }

    public void setBtnTelf01(RichCommandButton btnTelf01) {
        this.btnTelf01 = btnTelf01;
    }

    public RichCommandButton getBtnTelf01() {
        return btnTelf01;
    }

    public void setTxtIdEjVenta(RichInputText txtIdEjVenta) {
        this.txtIdEjVenta = txtIdEjVenta;
    }

    public RichInputText getTxtIdEjVenta() {
        return txtIdEjVenta;
    }

    public void setTxtMonto(RichInputText txtMonto) {
        this.txtMonto = txtMonto;
    }

    public RichInputText getTxtMonto() {
        return txtMonto;
    }

    public void setPopupFeedback(RichPopup popupFeedback) {
        this.popupFeedback = popupFeedback;
    }

    public RichPopup getPopupFeedback() {
        return popupFeedback;
    }

    public void setTblFeedback(RichTable tblFeedback) {
        this.tblFeedback = tblFeedback;
    }

    public RichTable getTblFeedback() {
        return tblFeedback;
    }

    public void setTxtTipGest(RichSelectOneChoice txtTipGest) {
        this.txtTipGest = txtTipGest;
    }

    public RichSelectOneChoice getTxtTipGest() {
        return txtTipGest;
    }

    public void setTxtDetGest(RichSelectOneChoice txtDetGest) {
        this.txtDetGest = txtDetGest;
    }

    public RichSelectOneChoice getTxtDetGest() {
        return txtDetGest;
    }

    public void setTxtFeedback(RichInputText txtFeedback) {
        this.txtFeedback = txtFeedback;
    }

    public RichInputText getTxtFeedback() {
        return txtFeedback;
    }

    public void setTxtGrupGest(RichSelectOneChoice txtGrupGest) {
        this.txtGrupGest = txtGrupGest;
    }

    public RichSelectOneChoice getTxtGrupGest() {
        return txtGrupGest;
    }

    public void setTxtEjVenta(RichInputText txtEjVenta) {
        this.txtEjVenta = txtEjVenta;
    }

    public RichInputText getTxtEjVenta() {
        return txtEjVenta;
    }

    public void setTxtCodTipGest(RichInputText txtCodTipGest) {
        this.txtCodTipGest = txtCodTipGest;
    }

    public RichInputText getTxtCodTipGest() {
        return txtCodTipGest;
    }

    public void setTxtCodGrupGest(RichInputText txtCodGrupGest) {
        this.txtCodGrupGest = txtCodGrupGest;
    }

    public RichInputText getTxtCodGrupGest() {
        return txtCodGrupGest;
    }

    public void setTxtCodDetGest(RichInputText txtCodDetGest) {
        this.txtCodDetGest = txtCodDetGest;
    }

    public RichInputText getTxtCodDetGest() {
        return txtCodDetGest;
    }

    public void setTxtFecGestion(RichOutputText txtFecGestion) {
        this.txtFecGestion = txtFecGestion;
    }

    public RichOutputText getTxtFecGestion() {
        return txtFecGestion;
    }

    public void setPnlFeedback(RichPanelBox pnlFeedback) {
        this.pnlFeedback = pnlFeedback;
    }

    public RichPanelBox getPnlFeedback() {
        return pnlFeedback;
    }

    public void setTxtEmailAd(RichInputText txtEmailAd) {
        this.txtEmailAd = txtEmailAd;
    }

    public RichInputText getTxtEmailAd() {
        return txtEmailAd;
    }

    public void setTxtDirAd(RichInputText txtDirAd) {
        this.txtDirAd = txtDirAd;
    }

    public RichInputText getTxtDirAd() {
        return txtDirAd;
    }

    public void setTxtTelfAd(RichInputText txtTelfAd) {
        this.txtTelfAd = txtTelfAd;
    }

    public RichInputText getTxtTelfAd() {
        return txtTelfAd;
    }

    public void setTxtObs(RichInputText txtObs) {
        this.txtObs = txtObs;
    }

    public RichInputText getTxtObs() {
        return txtObs;
    }

    public void setBtnOpty1(RichCommandButton btnOpty1) {
        this.btnOpty1 = btnOpty1;
    }

    public RichCommandButton getBtnOpty1() {
        return btnOpty1;
    }

    public void setBtnOpty2(RichCommandButton btnOpty2) {
        this.btnOpty2 = btnOpty2;
    }

    public RichCommandButton getBtnOpty2() {
        return btnOpty2;
    }

    public void setBtnOpty3(RichCommandButton btnOpty3) {
        this.btnOpty3 = btnOpty3;
    }

    public RichCommandButton getBtnOpty3() {
        return btnOpty3;
    }


    public void setTxtJefCanal(RichInputText txtJefCanal) {
        this.txtJefCanal = txtJefCanal;
    }

    public RichInputText getTxtJefCanal() {
        return txtJefCanal;
    }

    public void setTxtIdSup(RichInputText txtIdSup) {
        this.txtIdSup = txtIdSup;
    }

    public RichInputText getTxtIdSup() {
        return txtIdSup;
    }

    public void setTxtSup(RichInputText txtSup) {
        this.txtSup = txtSup;
    }

    public RichInputText getTxtSup() {
        return txtSup;
    }

    public void setTxtIdJefCanl(RichInputText txtIdJefCanl) {
        this.txtIdJefCanl = txtIdJefCanl;
    }

    public RichInputText getTxtIdJefCanl() {
        return txtIdJefCanl;
    }

    public void setTxtIdJefCanal(RichInputText txtIdJefCanal) {
        this.txtIdJefCanal = txtIdJefCanal;
    }

    public RichInputText getTxtIdJefCanal() {
        return txtIdJefCanal;
    }

    public void setTxtCanal(RichInputText txtCanal) {
        this.txtCanal = txtCanal;
    }

    public RichInputText getTxtCanal() {
        return txtCanal;
    }

    public void setTxtIdCanal(RichInputText txtIdCanal) {
        this.txtIdCanal = txtIdCanal;
    }

    public RichInputText getTxtIdCanal() {
        return txtIdCanal;
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void setTxtLeadOld(RichInputText txtLeadOld) {
        this.txtLeadOld = txtLeadOld;
    }

    public RichInputText getTxtLeadOld() {
        return txtLeadOld;
    }

    public void setTxtLeadNew(RichInputText txtLeadNew) {
        this.txtLeadNew = txtLeadNew;
    }

    public RichInputText getTxtLeadNew() {
        return txtLeadNew;
    }

    public void setTxtIdEj(RichInputText txtIdEj) {
        this.txtIdEj = txtIdEj;
    }

    public RichInputText getTxtIdEj() {
        return txtIdEj;
    }

    public void setTxtCanalId(RichInputText txtCanalId) {
        this.txtCanalId = txtCanalId;
    }

    public RichInputText getTxtCanalId() {
        return txtCanalId;
    }

    public void setTxt2daLlam(RichInputDate txt2daLlam) {
        this.txt2daLlam = txt2daLlam;
    }

    public RichInputDate getTxt2daLlam() {
        return txt2daLlam;
    }

    public void setTxtEjecutivoVenta(RichInputText txtEjecutivoVenta) {
        this.txtEjecutivoVenta = txtEjecutivoVenta;
    }

    public RichInputText getTxtEjecutivoVenta() {
        return txtEjecutivoVenta;
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }

    public void setPopupTelfExtra(RichPopup popupTelfExtra) {
        this.popupTelfExtra = popupTelfExtra;
    }

    public RichPopup getPopupTelfExtra() {
        return popupTelfExtra;
    }

    public void setTxtFecGest(RichInputText txtFecGest) {
        this.txtFecGest = txtFecGest;
    }

    public RichInputText getTxtFecGest() {
        return txtFecGest;
    }

    public void setTxtFeedbackTlfExt(RichInputText txtFeedbackTlfExt) {
        this.txtFeedbackTlfExt = txtFeedbackTlfExt;
    }

    public RichInputText getTxtFeedbackTlfExt() {
        return txtFeedbackTlfExt;
    }

    public void setTxtFecGestTlfExt(RichInputText txtFecGestTlfExt) {
        this.txtFecGestTlfExt = txtFecGestTlfExt;
    }

    public RichInputText getTxtFecGestTlfExt() {
        return txtFecGestTlfExt;
    }

    public void setTxtTelfExtra(RichInputText txtTelfExtra) {
        this.txtTelfExtra = txtTelfExtra;
    }

    public RichInputText getTxtTelfExtra() {
        return txtTelfExtra;
    }

    public void setTxtCodTipGestTlfExt(RichInputText txtCodTipGestTlfExt) {
        this.txtCodTipGestTlfExt = txtCodTipGestTlfExt;
    }

    public RichInputText getTxtCodTipGestTlfExt() {
        return txtCodTipGestTlfExt;
    }

    public void setTxtCodGrupGestTlfExt(RichInputText txtCodGrupGestTlfExt) {
        this.txtCodGrupGestTlfExt = txtCodGrupGestTlfExt;
    }

    public RichInputText getTxtCodGrupGestTlfExt() {
        return txtCodGrupGestTlfExt;
    }

    public void setTxtCodDetGestTlfExt(RichInputText txtCodDetGestTlfExt) {
        this.txtCodDetGestTlfExt = txtCodDetGestTlfExt;
    }

    public RichInputText getTxtCodDetGestTlfExt() {
        return txtCodDetGestTlfExt;
    }

    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
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
        ctx.getSessionScope().put("EjecutivoVentaId", partyId);

        return partyId;
    }


    public void buscarDatos(ActionEvent actionEvent) {

        ADFContext ctx = ADFContext.getCurrent();
        String idEjecutivoVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        DosificacionEjecutivoBO.actualizarFlagUltimo2(idEjecutivoVenta);
        this.mostrarLead(idEjecutivoVenta);
        Util.Log("buscarFeedback size feedback lista");
        this.bloquearTelefonos();
        Util.Log("buscarFeedback size feedback lista");
    }

    public void buscarFeedback(List<Feedback> listFeedback) {

        Util.LogTime("Inicio");
        try {
            
            Util.Log("buscarFeedback size feedback lista " + listFeedback.size());

            CollectionModel _tablaFeedback =
                (CollectionModel)tblFeedback.getValue();
            JUCtrlHierBinding _adfTableFeedbackBinding =
                (JUCtrlHierBinding)_tablaFeedback.getWrappedData();
            DCIteratorBinding itFeedback =
                _adfTableFeedbackBinding.getDCIteratorBinding();

            int h = 0;
            h = itFeedback.getRowSetIterator().getRowCount();

            for (int i = 0; i < h; i++) {
                itFeedback.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }

            Row rw8 = null;

            String idFb = null;


            for (Feedback opp8 : listFeedback) {

                rw8 = itFeedback.getNavigatableRowIterator().createRow();
                itFeedback.getNavigatableRowIterator().insertRow(rw8);
                rw8.setNewRowState(Row.STATUS_INITIALIZED);

                rw8.setAttribute("NomFb", opp8.getNomFb());
                rw8.setAttribute("IdFb", opp8.getIdFb());
                rw8.setAttribute("FecGestion", opp8.getFecGestion());
                rw8.setAttribute("tipoGestion", opp8.getNomTipGest());
                rw8.setAttribute("grupoGestion", opp8.getNomGrupGest());
                rw8.setAttribute("detGestion", opp8.getNomDetGest());
                rw8.setAttribute("DescAdGestion", opp8.getDescAdGestion());
                rw8.setAttribute("NomCpg", opp8.getNomCpg());
                rw8.setAttribute("CodCpg", opp8.getCodCpg());
                rw8.setAttribute("MontoDesem",
                                 Util.formatearMonto(opp8.getMontoDesem()));
                rw8.setAttribute("MontoDesem2of",
                                 Util.formatearMonto(opp8.getMontoDesem2Of()));
                rw8.setAttribute("telefono", opp8.getTelefono());
                Util.Log(opp8.getIdLead() + "IIIID LLLLLEAD");
                rw8.setAttribute("IdLead", opp8.getIdLead());
                rw8.setAttribute("CanalFb", opp8.getCanal());
                rw8.setAttribute("CodCanalFb", opp8.getIdCanal());
                rw8.setAttribute("Sup", opp8.getSup());
                rw8.setAttribute("JefCanal", opp8.getJefCanal());
                rw8.setAttribute("EjVenta", opp8.getEjVenta());
                rw8.setAttribute("idEjVenta", opp8.getIdEjVenta());
                rw8.setAttribute("idJefCanal", opp8.getIdjefCanal());
                rw8.setAttribute("idSup", opp8.getIdSup());
                rw8.setAttribute("monto",
                                 Util.formatearMonto(opp8.getMonto()));
                rw8.setAttribute("telfAd", opp8.getTelfAd());
                rw8.setAttribute("obs", opp8.getObs());
                rw8.setAttribute("dirAd", opp8.getDirAd());
                rw8.setAttribute("emailAd", opp8.getEmailAd());
                rw8.setAttribute("fec2daLlam", opp8.getFec2daLlam());

                idFb = opp8.getIdFb().toString();
                Util.Log(idFb + "idFb");

            }
        } catch (Exception ex) {
            Util.Log("buscarFeedback " + ex.getMessage());
        }
    }

    public void buscarFeedback(String leadId) {

        Util.Log(leadId + "ID LEAD");
        try {
         //   ServiceBiPublisher pantOpty = new ServiceBiPublisher();
            List<Feedback> listFeedback = new ArrayList<Feedback>();
          //  listFeedback = pantOpty.obtenerFeedback(leadId);
            listFeedback = listFb(leadId);
            Util.Log("buscarFeedback size feedback " + listFeedback.size());

            CollectionModel _tablaFeedback =
                (CollectionModel)tblFeedback.getValue();
            JUCtrlHierBinding _adfTableFeedbackBinding =
                (JUCtrlHierBinding)_tablaFeedback.getWrappedData();
            DCIteratorBinding itFeedback =
                _adfTableFeedbackBinding.getDCIteratorBinding();

            int h = 0;
            h = itFeedback.getRowSetIterator().getRowCount();

            for (int i = 0; i < h; i++) {
                itFeedback.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }

            Row rw8 = null;

            for (Feedback opp8 : listFeedback) {

                rw8 = itFeedback.getNavigatableRowIterator().createRow();
                itFeedback.getNavigatableRowIterator().insertRow(rw8);
                rw8.setNewRowState(Row.STATUS_INITIALIZED);
                rw8.setAttribute("NomFb", opp8.getNomFb());
                rw8.setAttribute("IdFb", opp8.getIdFb());
                rw8.setAttribute("FecGestion", opp8.getFecGestion());
                rw8.setAttribute("tipoGestion", opp8.getNomTipGest());
                rw8.setAttribute("grupoGestion", opp8.getNomGrupGest());
                rw8.setAttribute("detGestion", opp8.getNomDetGest());
                rw8.setAttribute("DescAdGestion", opp8.getDescAdGestion());
                rw8.setAttribute("NomCpg", opp8.getNomCpg());
                rw8.setAttribute("CodCpg", opp8.getCodCpg());
                rw8.setAttribute("MontoDesem",
                                 Util.formatearMonto(opp8.getMontoDesem()));
                rw8.setAttribute("MontoDesem2of",
                                 Util.formatearMonto(opp8.getMontoDesem2Of()));
                rw8.setAttribute("telefono", opp8.getTelefono());
                rw8.setAttribute("IdLead", opp8.getIdLead());
                rw8.setAttribute("CanalFb", opp8.getCanal());
                rw8.setAttribute("CodCanalFb", opp8.getIdCanal());
                rw8.setAttribute("Sup", opp8.getSup());
                rw8.setAttribute("JefCanal", opp8.getJefCanal());
                rw8.setAttribute("EjVenta", opp8.getEjVenta());
                rw8.setAttribute("idEjVenta", opp8.getIdEjVenta());
                rw8.setAttribute("idJefCanal", opp8.getIdjefCanal());
                rw8.setAttribute("idSup", opp8.getIdSup());
                rw8.setAttribute("monto", Util.formatearMonto(opp8.getMonto()));
                rw8.setAttribute("telfAd", opp8.getTelfAd());
                rw8.setAttribute("obs", opp8.getObs());
                rw8.setAttribute("dirAd", opp8.getDirAd());
                rw8.setAttribute("emailAd", opp8.getEmailAd());
                rw8.setAttribute("fec2daLlam", opp8.getFec2daLlam());

            }
        } catch (Exception ex) {
            Util.Log("buscarFeedback L " + ex.getMessage());
        }
    }
    
    public List<Feedback> listFb(String leadId){
        
        List<Feedback> listFb = new ArrayList<Feedback>();
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idLead = "";       
        String nombreFb = "";       
        String idCanal = "";       
        String canal = "";       
        String idJefCanal = "";       
        String jefCanal = "";       
        String idSup = "";       
        String sup = "";       
        String idEjVenta = "";       
        String ejVenta = "";       
        String telefono = "";       
        String tipoGestion = "";       
        String grupoGestion = "";       
        String detalleGestion = "";       
        String auxFeedbackContactado = "";       
        String auxFeedbackContactadoEfec = "";       
        String auxFeedbackVenta = "";       
        String auxFeedbackRevenueVenta = "";       
        String nomTipGest = "";       
        String nomGrupGest = "";       
        String nomDetGest = "";       
        String fecGestion = "";       
        String observacion = "";       
        String telfAd = "";       
        String dirAd = "";       
        String emailAd = "";       
        String idCpg = "";       
        String cpgNom = "";       
        String montoDesem = "";       
        String fecha2daLlam = "";       
        String contacto = "";       
        String numDoc = "";       
        String tipDoc = "";       
        String prod = "";       
        String flagInbound = "";       
        String flagOutbound = "";       
        String horaFeedback = "";       
        String auxFeedbackAgendado = "";
        
        try {
            
            String query = "select idLead IDLEAD," +                    
                            "nombreFb NOMBREFB ," + 
                            "idCanal IDCANAL ," + 
                            "canal CANAL ," + 
                            "idJefCanal IDJEFCANAL ," + 
                            "jefCanal JEFCANAL ," + 
                            "idSup IDSUP ," + 
                            "sup SUP ," + 
                            "idEjVenta IDEJVENTA ," + 
                            "ejVenta EJVENTA ," + 
                            "telefono TELEFONO ," + 
                            "tipoGestion TIPOGESTION ," + 
                            "grupoGestion GRUPOGESTION ," + 
                            "detalleGestion DETALLEGESTION ," + 
                            "auxFeedbackContactado AUXFEEDBACKCONTACTADO ," + 
                            "auxFeedbackContactadoEfec AUXFEEDBACKCONTACTADOEFEC ," + 
                            "auxFeedbackVenta AUXFEEDBACKVENTA ," + 
                            "auxFeedbackRevenueVenta AUXFEEDBACKREVENUEVENTA ," + 
                            "nomTipGest NOMTIPGEST ," + 
                            "nomGrupGest NOMGRUPGEST ," + 
                            "nomDetGest NOMDETGEST ," + 
                            "fecGestion FECGESTION ," + 
                            "observacion OBSERVACION ," + 
                            "telfAd TELFAD ," + 
                            "dirAd DIRAD ," + 
                            "emailAd EMAILAD ," + 
                            "idCpg IDCPG ," + 
                            "cpgNom CPGNOM ," + 
                            "montoDesem MONTODESEM ," + 
                            "fecha2daLlam FECHA2DALLAM ," + 
                            "contacto CONTACTO ," + 
                            "numDoc NUMDOC ," + 
                            "tipDoc TIPDOC ," + 
                            "prod PROD ," + 
                            "flagInbound FLAGINBOUND ," + 
                            "flagOutbound FLAGOUTBOUND ," + 
                            "horaFeedback HORAFEEDBACK ," + 
                            "auxFeedbackAgendado AUXFEEDBACKAGENDADO " + 
                            "from sbp_sls_info_lead_fb where idLead = '" + leadId + "'";
            
            Util.Log(query);
            
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();
            
            while (rs.next()) {
            
                Feedback fb = new Feedback();
                
                idLead = rs.getString("IDLEAD");     
                nombreFb = rs.getString("NOMBREFB");     
                idCanal = rs.getString("IDCANAL");     
                canal = rs.getString("CANAL");     
                idJefCanal = rs.getString("IDJEFCANAL");     
                jefCanal = rs.getString("JEFCANAL");     
                idSup = rs.getString("IDSUP");     
                sup = rs.getString("SUP");     
                idEjVenta = rs.getString("IDEJVENTA");     
                ejVenta = rs.getString("EJVENTA");     
                telefono = rs.getString("TELEFONO");     
                tipoGestion = rs.getString("TIPOGESTION");     
                grupoGestion = rs.getString("GRUPOGESTION");     
                detalleGestion = rs.getString("DETALLEGESTION");     
                auxFeedbackContactado = rs.getString("AUXFEEDBACKCONTACTADO");     
                auxFeedbackContactadoEfec = rs.getString("AUXFEEDBACKCONTACTADOEFEC");     
                auxFeedbackVenta = rs.getString("AUXFEEDBACKVENTA");     
                auxFeedbackRevenueVenta = rs.getString("AUXFEEDBACKREVENUEVENTA");     
                nomTipGest = rs.getString("NOMTIPGEST");     
                nomGrupGest = rs.getString("NOMGRUPGEST");     
                nomDetGest = rs.getString("NOMDETGEST");     
                fecGestion = rs.getString("FECGESTION");     
                observacion = rs.getString("OBSERVACION");     
                telfAd = rs.getString("TELFAD");     
                dirAd = rs.getString("DIRAD");     
                emailAd = rs.getString("EMAILAD");     
                idCpg = rs.getString("IDCPG");     
                cpgNom = rs.getString("CPGNOM");     
                montoDesem = rs.getString("MONTODESEM");     
                fecha2daLlam = rs.getString("FECHA2DALLAM");     
                contacto = rs.getString("CONTACTO");     
                numDoc = rs.getString("NUMDOC");     
                tipDoc = rs.getString("TIPDOC");     
                prod = rs.getString("PROD");     
                flagInbound = rs.getString("FLAGINBOUND");     
                flagOutbound = rs.getString("FLAGOUTBOUND");     
                horaFeedback = rs.getString("HORAFEEDBACK");     
                auxFeedbackAgendado = rs.getString("AUXFEEDBACKAGENDADO");
                
                fb.setIdLead(idLead);
                fb.setNomFb(nombreFb);
                fb.setIdCanal(idCanal);
                fb.setCanal(canal);
                fb.setIdjefCanal(idJefCanal);
                fb.setJefCanal(jefCanal);
                fb.setIdSup(idSup);
                fb.setSup(sup);
                fb.setIdEjVenta(idEjVenta);
                fb.setEjVenta(ejVenta);
                fb.setTelefono(telefono);
                fb.setTipoGestion(tipoGestion);
                fb.setGrupoGestion(grupoGestion);
                fb.setDetGestion(detalleGestion);
                fb.setAuxContactado(auxFeedbackContactado);
                fb.setAuxContactadoEfec(auxFeedbackContactadoEfec);
                fb.setAuxVenta(auxFeedbackVenta);
                fb.setAuxRevenueVenta(auxFeedbackRevenueVenta);
                fb.setNomTipGest(nomTipGest);
                fb.setNomGrupGest(nomGrupGest);
                fb.setNomDetGest(nomDetGest);
                fb.setFecGestion(fecGestion);
                fb.setObs(observacion);
                fb.setTelfAd(telfAd);
                fb.setDirAd(dirAd);
                fb.setEmailAd(emailAd);
                fb.setCodCpg(idCpg);
                fb.setNomCpg(cpgNom);
                fb.setMontoDesem(montoDesem);
                fb.setFec2daLlam(fecha2daLlam);
                fb.setContacto(contacto);
                fb.setNumDoc(numDoc);
                fb.setTipDoc(tipDoc);
                fb.setProd(prod);
                fb.setFlagInbound(flagInbound);
                fb.setFlagOutbound(flagOutbound);
                fb.setHoraFeedback(horaFeedback);
                fb.setAuxFeedbackAgendado(auxFeedbackAgendado);
                
                listFb.add(fb);
                
            }
            
            Util.Log("tamaño fb list --> " + listFb.size());
            
        } catch (SQLException e) {
                  Util.Log(e + "error");
              } finally {
                  DbUtils.closeQuietly(rs);
                  DbUtils.closeQuietly(st);
                  DbUtils.closeQuietly(conn);
              }

        return listFb;
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

    public String obtenerNumTelf(String numTelf) {

        ServiceBiPublisher pantOpty = new ServiceBiPublisher();

        ADFContext ctx = ADFContext.getCurrent();

        String optyId =
            ctx.getSessionScope().get("IdOpty") == null ? "" : ctx.getSessionScope().get("IdOpty").toString();


        //FEEDBACK
        List<Feedback> listFeedback = new ArrayList<Feedback>();
        listFeedback = pantOpty.obtenerFeedback(optyId);


        String telf = "";
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding("CpgOptyProdContAccFb1Iterator");

        // Get an object representing the table and what may be selected within it
        ViewObject voTableData = dcItteratorBindings.getViewObject();

        // Get selected row
        Row rowSelected = voTableData.getCurrentRow();

        if (rowSelected.getAttribute(numTelf) != null ||
            rowSelected.getAttribute(numTelf) == "") {

            // Display attribute of row in console output - would generally be bound to a UI component like a Label and or used to call another proces
            telf = rowSelected.getAttribute(numTelf).toString();
            Util.Log(telf + "gelf");

            //Iterador de la cabecera de busqueda de documentos
            DCBindingContainer bindings2 =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dcItteratorBindings2 =
                bindings2.findIteratorBinding("FeedBackVo1Iterator");

            Row rw8 = null;

            Util.Log(listFeedback.size() + "size feedback");

            for (Feedback listGnrl8 : listFeedback) {

                rw8 =
dcItteratorBindings2.getNavigatableRowIterator().createRow();
                dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
                rw8.setNewRowState(Row.STATUS_INITIALIZED);

                rw8.setAttribute("IdOpp", listGnrl8.getIdLead());
                rw8.setAttribute("telefono", telf);
                rw8.setAttribute("CanalFb", listGnrl8.getCanal());
                rw8.setAttribute("CodCanalFb", listGnrl8.getIdCanal());
                rw8.setAttribute("Sup", listGnrl8.getSup());
                rw8.setAttribute("JefCanal", listGnrl8.getJefCanal());
                rw8.setAttribute("idJefCanal", listGnrl8.getIdjefCanal());
                rw8.setAttribute("idSup", listGnrl8.getIdSup());
                rw8.setAttribute("NomCpg", listGnrl8.getNomCpg());
                rw8.setAttribute("CodCpg", listGnrl8.getCodCpg());
                break;
            }
        }

        return telf;
    }

    public void popupCrearFeedback(ActionEvent actionEvent, String numTelf) {

        ADFContext ctx = ADFContext.getCurrent();

        this.txtTelLla.setValue(numTelf);
        
        ctx.getSessionScope().put("numLlamada", numTelf);

        String canalId =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String supId =
            ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        String nomCanal =
            ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        String jefeCanal =
            ctx.getSessionScope().get("JefeCanal") == null ? "" :
            ctx.getSessionScope().get("JefeCanal").toString();
        String idJefeCanal =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String sup =
            ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        String ejVenta =
            ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String idEjecVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        String montoFb =
            ctx.getSessionScope().get("montoLead") == null ? "" : ctx.getSessionScope().get("montoLead").toString();

        txtMontoFb.setValue(Util.formatearMonto(montoFb));

        String nomFeedback = "";
        String fecGestion = "";

        RichPopup.PopupHints hints = new RichPopup.PopupHints();


        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");


        Row rw8 = null;

        DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss aa");
        java.util.Date fecha = new java.util.Date();
        fecGestion = df.format(fecha);

        txtFecGestion.setValue(fecGestion);

        nomFeedback = "Feedback" + "-" + fecGestion + "-" + ejVenta;
        txtFeedback.setValue(nomFeedback);

        rw8 = dcItteratorBindings2.getNavigatableRowIterator().createRow();
        dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
        rw8.setNewRowState(Row.STATUS_INITIALIZED);


        rw8.setAttribute("telf", numTelf);
        rw8.setAttribute("nomFb", txtFeedback.getValue().toString());
        rw8.setAttribute("idEjVenta", idEjecVenta);
        rw8.setAttribute("canal", nomCanal);
        rw8.setAttribute("idCanal", canalId);
        rw8.setAttribute("idJefCanal", idJefeCanal);
        rw8.setAttribute("jefCanal", jefeCanal);
        rw8.setAttribute("idSup", supId);
        rw8.setAttribute("sup", sup);
        rw8.setAttribute("ejVenta", ejVenta);
        rw8.setAttribute("fecGestion", txtFecGestion.getValue().toString());


        getPopupFeedback().show(hints);
    }

    public void popupCrearFeedbackTelfExt(ActionEvent actionEvent) {

        ADFContext ctx = ADFContext.getCurrent();

        String canalId =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String supId =
            ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        String nomCanal =
            ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        String jefeCanal =
            ctx.getSessionScope().get("JefeCanal") == null ? "" :
            ctx.getSessionScope().get("JefeCanal").toString();
        String idJefeCanal =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String sup =
            ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        String ejVenta =
            ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String idEjecVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        String montoFb =
            ctx.getSessionScope().get("montoLead") == null ? "" : ctx.getSessionScope().get("montoLead").toString();

        txtMontoFb.setValue(Util.formatearMonto(montoFb));

        String nomFeedback = "";
        String fecGestion = "";

        RichPopup.PopupHints hints = new RichPopup.PopupHints();

        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");


        Row rw8 = null;


        DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm aa");
        java.util.Date fecha = new java.util.Date();
        // Date fecActual = Calendar.getInstance().getTime();
        fecGestion = df.format(fecha);
        txtFecGestTlfExt.setValue(fecGestion);

        nomFeedback = "Feedback" + "-" + fecGestion;
        txtFeedbackTlfExt.setValue(nomFeedback);

        rw8 = dcItteratorBindings2.getNavigatableRowIterator().createRow();
        dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
        rw8.setNewRowState(Row.STATUS_INITIALIZED);


        rw8.setAttribute("nomFb", txtFeedbackTlfExt.getValue().toString());
        rw8.setAttribute("idEjVenta", idEjecVenta);
        rw8.setAttribute("canal", nomCanal);
        rw8.setAttribute("idCanal", canalId);
        rw8.setAttribute("idJefCanal", idJefeCanal);
        rw8.setAttribute("jefCanal", jefeCanal);
        rw8.setAttribute("idSup", supId);
        rw8.setAttribute("sup", sup);
        rw8.setAttribute("ejVenta", ejVenta);
        rw8.setAttribute("fecGestion", txtFecGestTlfExt.getValue().toString());

        Util.Log(txtFeedback.getValue().toString() + "fb");

        getPopupTelfExtra().show(hints);
    }
    
    public void redirect(String telf) throws IOException {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Util.Log("URL -- >" + "http://190.117.52.130:88/vapi/l005.php?anx=2404&dest=" + telf);
        externalContext.redirect("http://190.117.52.130:88/vapi/l005.php?anx=2404&dest=" + telf);
      //  externalContext.redirect("http://190.117.52.130:88/vapi/l005.php?anx=2404&dest=943519883");
    }

    public void agregarTelf1(ActionEvent actionEvent) {

        String numTelf = txtTelf01.getText();

        txtValorTelf.setValue("1");

        popupCrearFeedback(actionEvent, numTelf);


    }

    public void agregarTelf02(ActionEvent actionEvent) {

        String numTelf = txtTelf02.getText();

        txtValorTelf.setValue("2");

        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarTelf03(ActionEvent actionEvent) {

        String numTelf = txtTelf03.getText();

        txtValorTelf.setValue("3");

        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarCel01(ActionEvent actionEvent) {

        String numTelf = txtCel01.getText();

        txtValorTelf.setValue("4");
        
        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarCel02(ActionEvent actionEvent) {

        String numTelf = txtCel02.getText();

        txtValorTelf.setValue("5");

        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarCel03(ActionEvent actionEvent) {

        String numTelf = txtCel03.getText();

        txtValorTelf.setValue("6");

        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarTelfExtra(ActionEvent actionEvent) {

        popupCrearFeedbackTelfExt(actionEvent);

    }

    public void limpiarPopup(Row rowSelected) {
        rowSelected.remove();
    }


    public void limpiarComboGestion() {

        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("FeedbackTipo1Iterator");
        Row rowSelected = dcItteratorBindings2.getCurrentRow();


        rowSelected.remove();
        Util.Log("FUNCIONÓ");
    }

    public void actualizarCheckCont(String tipoGestion) {

        ADFContext ctx = ADFContext.getCurrent();

        MergeLeadInvoke leadPayload = new MergeLeadInvoke();

        String idLead =
            ctx.getSessionScope().get("iDlEaD") == null ? "" : ctx.getSessionScope().get("iDlEaD").toString();

        Util.Log(idLead + "ID LEAD");
        Util.Log(tipoGestion + "TIPO GESTION");

        if (tipoGestion.equalsIgnoreCase("1")) {


            try {
                leadPayload.mergeLeadCont(Long.parseLong(idLead), true);
            } catch (ParserConfigurationException e) {
                e.printStackTrace(System.out);
            } catch (SAXException e) {
                e.printStackTrace(System.out);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void actualizarCheckContEfec(String grupoGestion) {

        ADFContext ctx = ADFContext.getCurrent();

        MergeLeadInvoke leadPayload = new MergeLeadInvoke();

        String idLead =
            ctx.getSessionScope().get("iDlEaD") == null ? "" : ctx.getSessionScope().get("iDlEaD").toString();

        Util.Log(idLead + "ID LEAD");
        Util.Log(grupoGestion + "GRUPO GESTION");

        if (grupoGestion.equalsIgnoreCase("1") ||
            grupoGestion.equalsIgnoreCase("2") ||
            grupoGestion.equalsIgnoreCase("3") ||
            grupoGestion.equalsIgnoreCase("4") ||
            grupoGestion.equalsIgnoreCase("5") ||
            grupoGestion.equalsIgnoreCase("6") ||
            grupoGestion.equalsIgnoreCase("8") ||
            grupoGestion.equalsIgnoreCase("9")) {


            try {
                leadPayload.mergeLeadContEfec(Long.parseLong(idLead), true);
            } catch (ParserConfigurationException e) {
                e.printStackTrace(System.out);
            } catch (SAXException e) {
                e.printStackTrace(System.out);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void crearFeedBack() throws ParseException,
                                       DatatypeConfigurationException,
                                       ParserConfigurationException,
                                       SAXException, IOException {
        try {

            ADFContext ctx = ADFContext.getCurrent();

            String nombreFb = "";
            String idCanal = "";
            String canal = "";
            String idJefCanal = "";
            String jefCanal = "";
            String idSup = "";
            String sup = "";
            String idEjVenta = "";
            String ejVenta = "";
            String telefono = "";
            String tipoGestion = "";
            String grupoGestion = "";
            String detalleGestion = "";
            BigDecimal auxFeedbackContactado = new BigDecimal(0);
            BigDecimal auxFeedbackContactadoEfec = new BigDecimal(0);
            BigDecimal auxFeedbackVenta = new BigDecimal(0);
            BigDecimal auxFeedbackRevenueVenta = new BigDecimal(0);
            String auxFeedbackAgendado = "";
            String nomDetGest = "";
            String nomGrupGest = "";
            String nomTipGest = "";
            String obs = "";
            String telfAd = "";
            String dirAd = "";
            String emailAd = "";
            BigDecimal montoDesem = new BigDecimal(0);
            String fecha2daLlam = "";
            String idLead = "";
            String contacto = this.txtContFb.getValue().toString();
            String numDoc = this.txtNumDoc.getValue().toString();
            String tipDoc = this.txtTipDoc.getValue().toString();
            String prod = this.txtProd.getValue().toString();
            Boolean flagOutbound = true;
            Boolean flagInbound = false;
            String fechaHoraFb = "";

            //FECHA GESTION
            final Date currentTimeFecGest = new Date();

            final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy-MM-dd");

            // Give it to me in GMT time.
            sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String fecGestion = sdfFecGest.format(currentTimeFecGest);
            Util.Log("Fecha Gestión --> " + fecGestion);
            
             //HORA FEEDBACK
            
            final Date currentTimeHoraGest = new Date();

            final SimpleDateFormat sdfHoraGest = new SimpleDateFormat("HH");

            // Give it to me in GMT time.
            sdfHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            String horaFeedback = sdfHoraGest.format(currentTimeHoraGest);
            
            //FECHA HORA FEEDBACK
            
            final Date currentTimeFechaHoraGest = new Date();

            final SimpleDateFormat sdfFechaHoraGest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Give it to me in GMT time.
            sdfFechaHoraGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            
            fechaHoraFb = sdfFechaHoraGest.format(currentTimeFechaHoraGest);

            String montoDes = this.txtMonto.getValue().toString();

            DCBindingContainer bindings2 = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dcItteratorBindings2 = bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");
            Row rowSelected = dcItteratorBindings2.getCurrentRow();

            nombreFb = nvl(rowSelected, "nomFb", "");
            idCanal = nvl(rowSelected, "idCanal", "");
            canal = nvl(rowSelected, "canal", "");
            idJefCanal = nvl(rowSelected, "idJefCanal", "");
            jefCanal = nvl(rowSelected, "jefCanal", "");
            idSup = nvl(rowSelected, "idSup", "");
            sup = nvl(rowSelected, "sup", "");
            idEjVenta = nvl(rowSelected, "idEjVenta", "");
            ejVenta = nvl(rowSelected, "ejVenta", "");
            telefono = nvl(rowSelected, "telf", "");

            ArrayList<String> fbGestion1 = new ArrayList(Arrays.asList(this.itGestion.getValue().toString().split("¬")));
            ArrayList<String> fbGestion2 = new ArrayList(Arrays.asList(fbGestion1.get(0).split("°")));
            ArrayList<String> fbGestion3 = new ArrayList(Arrays.asList(fbGestion1.get(1).split("°")));


            tipoGestion = fbGestion2.get(0);
            ctx.getSessionScope().put("tipGest", tipoGestion);
            grupoGestion = fbGestion2.get(1);
            ctx.getSessionScope().put("grupGest", grupoGestion);
            detalleGestion = fbGestion2.get(2);
            ctx.getSessionScope().put("detGest", detalleGestion);

            nomTipGest = fbGestion3.get(0);
            ctx.getSessionScope().put("nomTipGest", nomTipGest);
            nomGrupGest = fbGestion3.get(1);
            ctx.getSessionScope().put("nomGrupGest", nomGrupGest);
            nomDetGest = fbGestion3.get(2);
            ctx.getSessionScope().put("nomDetGest", nomDetGest);

            obs = nvl(rowSelected, "observaciones", "");
            telfAd = nvl(rowSelected, "telfAd", "");
            dirAd = nvl(rowSelected, "direccionAd", "");
            emailAd = nvl(rowSelected, "emailAd", "");
            montoDes = nvl(rowSelected, "montoDesem", "0");
            fecha2daLlam = nvl(rowSelected, "fec2daLlam", "");

            Util.Log(montoDes + "MONTO DES");

            if (tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("1")) {

                montoDes = nvl(rowSelected, "montoDesem", this.txtMonto.getValue().toString());
                String montoDesembolsado = montoDes.replace(",", "");
                Util.Log(montoDes + "MONTO DES");
                montoDesem = new BigDecimal(montoDesembolsado);

            } else {
                montoDesem = new BigDecimal(0);
            }

            if (tipoGestion.equalsIgnoreCase("1")) {
                auxFeedbackContactado = new BigDecimal(1);
                Util.Log(auxFeedbackContactado + "auxFeedbackContactado");
                actualizarCheckCont(tipoGestion);
            }

            if (tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("1") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("2") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("3") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("4") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("5") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("6") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("8") ||
                tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("9")) {
                auxFeedbackContactadoEfec = new BigDecimal(1);
                actualizarCheckContEfec(grupoGestion);
            }

            if (grupoGestion.equalsIgnoreCase("1")) {
                auxFeedbackVenta = new BigDecimal(1);
            }

            if (grupoGestion.equalsIgnoreCase("1")) {
                String monto = txtMonto.getValue().toString();
                auxFeedbackRevenueVenta = new BigDecimal(monto);
            }

            if (tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("9")) {
                auxFeedbackAgendado = "1";
                Util.Log(auxFeedbackAgendado + "auxFeedbackAgendado");
            }

            boolean validar = true;

  if (validar) {

    try {

    String idCpg = ctx.getSessionScope().get("CodCpg") == null ? "" : ctx.getSessionScope().get("CodCpg").toString();
    String cpgNom = ctx.getSessionScope().get("NomCpg") == null ? "" : ctx.getSessionScope().get("NomCpg").toString();
    idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
    canal = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
    idJefCanal = ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
    jefCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
    idSup = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
    sup = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
    idEjVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
    ejVenta = ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
    idLead = ctx.getSessionScope().get("iDlEaD") == null ? "" : ctx.getSessionScope().get("iDlEaD").toString();

        if (tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("1")) {

                Feedback f = new Feedback();
                f.setIdLead(idLead);
                f.setNomFb(nombreFb);
                f.setIdCanal(idCanal);
                f.setCanal(canal);
                f.setIdjefCanal(idJefCanal);
                f.setJefCanal(jefCanal);
                f.setIdSup(idSup);
                f.setSup(sup);
                f.setIdEjVenta(idEjVenta);
                f.setEjVenta(ejVenta);
                f.setTelefono(telefono);
                f.setTipoGestion(tipoGestion);
                f.setGrupoGestion(grupoGestion);
                f.setDetGestion(detalleGestion);
                f.setAuxContactado(auxFeedbackContactado.toString());
                f.setAuxContactadoEfec(auxFeedbackContactadoEfec.toString());
                f.setAuxVenta(auxFeedbackVenta.toString());
                f.setAuxRevenueVenta(auxFeedbackRevenueVenta.toString());
                f.setNomDetGest(nomDetGest);
                f.setNomGrupGest(nomGrupGest);
                f.setNomTipGest(nomTipGest);
                f.setFecGestion(fecGestion);
                f.setObs(obs);
                f.setTelfAd(telfAd);
                f.setDirAd(dirAd);
                f.setEmailAd(emailAd);
                f.setCodCpg(idCpg);
                f.setNomCpg(cpgNom);
                f.setMontoDesem(montoDesem + "");
                f.setFec2daLlam(fecha2daLlam);
                f.setContacto(contacto);
                f.setNumDoc(numDoc);
                f.setTipDoc(tipDoc);
                f.setProd(prod);
                f.setFlagInbound("false");
                f.setFlagOutbound("true");
                f.setHoraFeedback(horaFeedback);
                f.setAuxFeedbackAgendado(auxFeedbackAgendado);
                
                ctx.getSessionScope().put("Feedback", f);


            if (txtValorTelf.getValue().toString().equalsIgnoreCase("1")) {
                cont++;
                txtTelf01.setDisabled(false);
            } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("2")) {
                cont++;
                txtTelf02.setDisabled(false);
            } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("3")) {
                cont++;
                txtTelf03.setDisabled(false);
            } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("4")) {
                cont++;
                txtCel01.setDisabled(false);
            } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("5")) {
                cont++;
                txtCel02.setDisabled(false);
            } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("6")) {
                cont++;
                txtCel03.setDisabled(false);
            } else {
                System.out.println("NADA");
            }

            btnSigVenta.setDisabled(true);

        } else {
    
           if (tipoGestion.equalsIgnoreCase("1") &&
                grupoGestion.equalsIgnoreCase("9") &&
                fecha2daLlam.equalsIgnoreCase("")) {

                RichPopup.PopupHints hints =
                    new RichPopup.PopupHints();

                getPopup2daLlamada().show(hints);


                return;

            } else {

               // leadPayload.mergeLead(
               Util.Log("inicio ejecuta hilo");
              ejecutaHilo(Long.parseLong(idLead),
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

                        }
            
                        Util.Log("inicio ejecuta insert tb FB");   
                        insertInfoFb(idLead, nombreFb, idCanal, canal, idJefCanal,
                                     jefCanal, idSup, sup, idEjVenta, ejVenta, telefono,
                                     tipoGestion, grupoGestion, detalleGestion,
                                     auxFeedbackContactado.toString(), auxFeedbackContactadoEfec.toString(),
                                     auxFeedbackVenta.toString(), auxFeedbackRevenueVenta.toString(), nomTipGest,
                                     nomGrupGest, nomDetGest, fecGestion, obs, telfAd, dirAd,
                                     emailAd, idCpg, cpgNom, montoDesem.toString(), fecha2daLlam, contacto,
                                     numDoc, tipDoc, prod, "false", "true", horaFeedback, auxFeedbackAgendado);

                        if (txtValorTelf.getValue().toString().equalsIgnoreCase("1")) {
                            txtTelf01.setDisabled(true);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("2")) {
                            txtTelf02.setDisabled(true);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("3")) {
                            txtTelf03.setDisabled(true);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("4")) {
                            txtCel01.setDisabled(true);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("5")) {
                            txtCel02.setDisabled(true);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("6")) {
                            txtCel03.setDisabled(true);
                        } else {
                            System.out.println("NADA");
                        }

                        btnSigVenta.setDisabled(false);

                    }

                    // Si el feedbak es "Venta"
                    if (tipoGestion.equalsIgnoreCase("1") &&
                        grupoGestion.equalsIgnoreCase("1")) {

                        if (txtValorTelf.getValue().toString().equalsIgnoreCase("1")) {
                            txtTelf01.setDisabled(false);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("2")) {
                            txtTelf02.setDisabled(false);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("3")) {
                            txtTelf03.setDisabled(false);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("4")) {
                            txtCel01.setDisabled(false);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("5")) {
                            txtCel02.setDisabled(false);
                        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("6")) {
                            txtCel03.setDisabled(false);
                        } else {
                            System.out.println("NADA");
                        }

             
                    String montoDesemFb = montoDesem + "";
                     
                    String nomBinSesion = ctx.getSessionScope().get("nomBinSesion") == null ? "" : ctx.getSessionScope().get("nomBinSesion").toString();
                    
                    String nombreTarjeta = ctx.getSessionScope().get("nomProd") == null ? "" : ctx.getSessionScope().get("nomProd").toString();
                    
                    String tarjetaFija = "";
                    
                    Util.Log("nomBinSesion --> "+ nomBinSesion);
                     
                    if(nomBinSesion.equalsIgnoreCase("") || nomBinSesion == null){
                        
                        tarjetaFija = nombreTarjeta;
                        
                    } else {
                        
                        tarjetaFija = nomBinSesion;
                        
                    }
                    
                        Util.Log("nombreTarjeta --> "+ nombreTarjeta);
                    
                        Util.Log("tarjetaFija --> "+ tarjetaFija);
                        
                        String nomProd = txtProdSel.getValue().toString();
                        
                        String montoProd = txtMontoProd.getValue().toString();
                        
                        String genero = ctx.getSessionScope().get("genero") == null ? "" : ctx.getSessionScope().get("genero").toString();
                            
                        String tea = ctx.getSessionScope().get("tea") == null ? "" : ctx.getSessionScope().get("tea").toString();
                        
                        String tcea = ctx.getSessionScope().get("tcea") == null ? "" : ctx.getSessionScope().get("tcea").toString();
                        
                        String primerNom = txtPrimNom.getValue().toString();
                        
                        String segNom = txtSegNom.getValue().toString();
                        
                        String apePat = txtApePat.getValue().toString();
                        
                        String apeMat = txtApeMat.getValue().toString();
                        
                        String fecNac = txtFecNac.getValue().toString();
                        
                        Util.redirec("SBP_SLS_SPEECH_VENTA_NEW?telfActual=" + telefono + "&contacto=" + contacto +
                                  "&montoDesem=" + montoDes + "&prod=" + tarjetaFija + "&idLead=" + idLead +
				  "&nombreFb=" + nombreFb + "&idCanal=" + idCanal + "&canal=" + canal + "&idJefCanal=" + idJefCanal +
				  "&jefCanal=" + jefCanal + "&idSup=" + idSup + "&sup=" + sup + "&idEjVenta=" + idEjVenta + 
                                  "&ejVenta=" + ejVenta + "&tipoGestion=" + tipoGestion + "&grupoGestion=" + grupoGestion + "&detalleGestion=" + 
                                  detalleGestion + "&auxFeedbackContactado=" + auxFeedbackContactado.toString() + "&auxFeedbackContactadoEfec=" + auxFeedbackContactadoEfec.toString() +
                                  "&auxFeedbackVenta=" + auxFeedbackVenta.toString() + "&auxFeedbackRevenueVenta=" + auxFeedbackRevenueVenta.toString() + "&nomDetGest=" + nomDetGest +
                                  "&nomGrupGest=" + nomGrupGest + "&nomTipGest=" + nomTipGest + "&fecGestion=" + fecGestion + "&obs=" + obs + "&telfAd=" + telfAd +  "&dirAd=" + dirAd +
                                  "&emailAd=" + emailAd + "&idCpg=" + idCpg + "&cpgNom=" + cpgNom + "&montoDesem=" + montoDesemFb + "&fecha2daLlam=" + fecha2daLlam + "&contacto=" +
                                  contacto + "&numDoc=" + numDoc + "&tipDoc=" + tipDoc + "&flagInbound=" + "false" + "&flagOutbound=" + "true" + "&horaFeedback=" + horaFeedback + 
                                  "&auxFeedbackAgendado=" + auxFeedbackAgendado + "&prodFb=" + prod + "&codClie=" + numDoc + "&nomClie=" + contacto + "&codProd=" + "-" + "&nomProd=" + nomProd +
                                  "&montoProd=" + montoProd + "&genero=" + genero + "&tea=" + tea + "&tcea=" + tcea + "&primerNom=" + primerNom + "&segNom=" + segNom + "&apePat=" + apePat +
                                  "&apeMat=" + apeMat + "&fecNac=" + fecNac);

                    }

                    String estadoFb = validaFb(tipoGestion, grupoGestion, detalleGestion);

                    if (estadoFb.equalsIgnoreCase("true")) {
                        
                        String tipUsuario = ctx.getSessionScope().get("tipUsuario") == null ? "" : ctx.getSessionScope().get("tipUsuario").toString();
                        
                        if(tipUsuario.equalsIgnoreCase("Predictivo")){
                            
                            Util.Log("Entra validación de fb predictivo");
                            
                            txtTelfNew01.setDisabled(true);
                            
                            txtTelfNew02.setDisabled(true);
                            
                            txtTelfNew03.setDisabled(true);
                            
                            txtCel1TipUser.setDisabled(true);
                            
                            txtCel2TipUser.setDisabled(true);
                            
                            txtCel3TipUser.setDisabled(true);
                            
                        } else {
                            
                            String idEjecutivoVenta = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();

                            borrarFecProg(idLead);

                            // Mostrar siguiente lead
                            DosificacionEjecutivoBO.actualizarFlagUltimo2(idEjecutivoVenta);
                            Util.Log("siguiente lead");
                            this.mostrarLead(idEjecutivoVenta);
                            
                        }

                    } 
        
                    String numLlamar = ctx.getSessionScope().get("numLlamar") == null ? "" : ctx.getSessionScope().get("numLlamar").toString();
        
                    Util.Log("numLlamar --> " + numLlamar);
            
                    Util.Log("telf prueba 1 --> " + txtCel01.getText().replace("-", ""));
        
                    if(numLlamar.equalsIgnoreCase(txtTelf01.getText().replace("-", ""))){
                        
                        txtTelfNew01.setDisabled(true);
                        
                    } else if(numLlamar.equalsIgnoreCase(txtTelf02.getText().replace("-", ""))){
                        
                        txtTelfNew02.setDisabled(true);
                        
                    } else if(numLlamar.equalsIgnoreCase(txtTelf03.getText().replace("-", ""))){
                        
                        txtTelfNew03.setDisabled(true);
                        
                    } else if(numLlamar.equalsIgnoreCase(txtCel01.getText().replace("-", ""))){
                        
                        txtCel1TipUser.setDisabled(true);
                        
                    } else if(numLlamar.equalsIgnoreCase(txtCel02.getText().replace("-", ""))){
                        
                        txtCel2TipUser.setDisabled(true);
                        
                    } else if(numLlamar.equalsIgnoreCase(txtCel03.getText().replace("-", ""))){
                        
                        txtCel3TipUser.setDisabled(true);
                        
                    } 

                    try {

                        if (!fecha2daLlam.equalsIgnoreCase("")) {
                            DosificacionEjecutivoBO.actualizarFechaProgramada(idLead,
                                                                              fecha2daLlam);
                        }
                    } catch (Exception e) {
                        Util.Log("Error hora programada" + e);
                    }


                } catch (WebServiceException e) {
                    e.printStackTrace();
                }

                idLead = ctx.getSessionScope().get("iDlEaD") == null ? "" : ctx.getSessionScope().get("iDlEaD").toString();


                limpiarPopup(rowSelected);
                soc7.setValue("");
                itGestion.setValue("");
               
                Util.Log("busca feedback inicio");
                buscarFeedback(idLead);
                Util.Log("busca feedback fin");
                getPopupFeedback().hide();

                // 31/05/2018@FP-EVOL Eliminar Lead si se catalogó como tal al feedbanck
                if (Boolean.parseBoolean(fbGestion1.get(2))) {
                    DosificacionEjecutivoBO.eliminarLead(idLead);
                }

                // 31/05/2018@FP-EVOL Redireccionar
                if (ctx.getSessionScope().get("LEADID") != null) {
                    ctx.getSessionScope().put("LEADID", null);
                    Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + idEjVenta);
                }

            } else {

                // Limpiar combos
                this.txtGrupGest.setValue("");
                this.txtDetGest.setValue("");

                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getPopupValidarCampos().show(hints);

            }
            
            validaTipUsuario(idEjVenta);
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
    }

    public int listTelefonos() {

        int contador = 0;

        if (txtTelf01.getText().equalsIgnoreCase("-") ||
            txtTelf01.getText().equalsIgnoreCase("")) {

            Util.Log("ENTRA IF list 1");

        } else {

            contador++;

        }

        if (txtTelf02.getText().equalsIgnoreCase("-") ||
            txtTelf02.getText().equalsIgnoreCase("")) {

            Util.Log("ENTRA IF list 2");

        } else {

            contador++;

        }
        if (txtTelf03.getText().equalsIgnoreCase("-") ||
            txtTelf03.getText().equalsIgnoreCase("")) {

            Util.Log("ENTRA IF list 3");

        } else {

            contador++;

        }
        if (txtCel01.getText().equalsIgnoreCase("-") ||
            txtCel01.getText().equalsIgnoreCase("")) {

            Util.Log("ENTRA IF list 4");

        } else {

            contador++;

        }
        if (txtCel02.getText().equalsIgnoreCase("-") ||
            txtCel02.getText().equalsIgnoreCase("")) {
            Util.Log("ENTRA IF list 5");

        } else {

            contador++;

        }
        if (txtCel03.getText().equalsIgnoreCase("-") ||
            txtCel03.getText().equalsIgnoreCase("")) {
            Util.Log("ENTRA IF list 6");

        } else {

            contador++;

        }

        Util.Log(contador + "CONTADOR LISTA");

        return contador;
    }

    public void limpiarSpeechVenta() {

        txtTelCas.setValue("");
        txtTelCel.setValue("");
        txtCorEle.setValue("");
        txtTelRef.setValue("");
        cboCliPar.setValue("");
        cboEstCiv.setValue("");
        cboCliGen.setValue("");
        txtConNo1.setValue("");
        txtConNo2.setValue("");
        txtCoApPa.setValue("");
        txtCoApMa.setValue("");
        dtCoFeNa.setValue("");
        txtCoNuDo.setValue("");
        cboCoGene.setValue("");

        txtCliDir.setValue("");
        txtDirRef.setValue("");
        cboConLab.setValue("");
        txtCeLaNo.setValue("");
        txtCeLaRU.setValue("");
        dtCeLaFI.setValue("");
        txtCeLaDi.setValue("");
        txtCeLaRe.setValue("");
        txtCeLaTe.setValue("");

        txtCeLaAn.setValue("");
        cboLuEnIn.setValue("");
        cboLuEnDe.setValue("");
        cboCicFac.setValue("");


    }


    public void crearFeedBackTlfExtra() throws ParseException,
                                               DatatypeConfigurationException,
                                               ParserConfigurationException,
                                               SAXException, IOException {

        ADFContext ctx = ADFContext.getCurrent();

        String nombreFb = "";
        String idCanal = "";
        String canal = "";
        String idJefCanal = "";
        String jefCanal = "";
        String idSup = "";
        String sup = "";
        String idEjVenta = "";
        String ejVenta = "";
        String tipoGestion = "";
        String grupoGestion = "";
        String detalleGestion = "";
        BigDecimal auxFeedbackContactado = new BigDecimal(0);
        BigDecimal auxFeedbackContactadoEfec = new BigDecimal(0);
        BigDecimal auxFeedbackVenta = new BigDecimal(0);
        BigDecimal auxFeedbackRevenueVenta = new BigDecimal(0);
        String nomDetGest = "";
        String nomGrupGest = "";
        String nomTipGest = "";
        String obs = "";
        String telfAd = "";
        String dirAd = "";
        String emailAd = "";
        BigDecimal montoDesem = new BigDecimal(0);
        String fecha2daLlam = "";
        String idLead = "";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String montoDes = txtMonto.getValue().toString();


        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");


        // Get selected row
        Row rowSelected = dcItteratorBindings2.getCurrentRow();

        nombreFb = nvl(rowSelected, "nomFb", "");
        idCanal = nvl(rowSelected, "idCanal", "");
        canal = nvl(rowSelected, "canal", "");
        idJefCanal = nvl(rowSelected, "idJefCanal", "");
        jefCanal = nvl(rowSelected, "jefCanal", "");
        idSup = nvl(rowSelected, "idSup", "");
        sup = nvl(rowSelected, "sup", "");
        idEjVenta = nvl(rowSelected, "idEjVenta", "");
        ejVenta = nvl(rowSelected, "ejVenta", "");
        tipoGestion = nvlNoRow(txtCodTipGestTlfExt, "");
        grupoGestion = nvlNoRow(txtCodGrupGestTlfExt, "");
        detalleGestion = nvlNoRow(txtCodDetGestTlfExt, "");
        nomDetGest = nvl(rowSelected, "detGest", "");
        nomGrupGest = nvl(rowSelected, "grupGest", "");
        nomTipGest = nvl(rowSelected, "tipoGest", "");
        obs = nvl(rowSelected, "observaciones", "");
        telfAd = nvlNoRow(txtTelfExtra, "");
        dirAd = nvl(rowSelected, "direccionAd", "");
        emailAd = nvl(rowSelected, "emailAd", "");
        montoDes = nvl(rowSelected, "montoDesem", "0");
        fecha2daLlam = nvl(rowSelected, "fec2daLlam", "");

        Util.Log(telfAd + "TELF");

        if (tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("1")) {


            montoDes =
                    nvl(rowSelected, "montoDesem", txtMonto.getValue().toString());

            String montoDesembolsado = montoDes.replace(",", "");

            Util.Log(montoDes + "MONTO DES");

            montoDesem = new BigDecimal(montoDesembolsado);

        } else {

            montoDesem = new BigDecimal(0);

        }

        if (tipoGestion.equalsIgnoreCase("1")) {
            auxFeedbackContactado = new BigDecimal(1);
            actualizarCheckCont("1");

        }

        if (tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("1") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("2") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("3") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("4") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("5") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("6") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("8") ||
            tipoGestion.equalsIgnoreCase("1") &&
            grupoGestion.equalsIgnoreCase("9")) {
            auxFeedbackContactadoEfec = new BigDecimal(1);
            actualizarCheckContEfec(grupoGestion);

        }

        if (grupoGestion.equalsIgnoreCase("1")) {
            auxFeedbackVenta = new BigDecimal(1);
        }

        if (grupoGestion.equalsIgnoreCase("1")) {

            String monto = txtMonto.getValue().toString();

            auxFeedbackRevenueVenta = new BigDecimal(monto);
        }

        boolean validar = validarCreacionFbTelfExtra(rowSelected);

        Util.Log(validar + "GHB 5");


        if (validar) {
            try {

                idCanal =
                        ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
                canal =
                        ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
                idJefCanal =
                        ctx.getSessionScope().get("IdJefeCanal") == null ? "" :
                        ctx.getSessionScope().get("IdJefeCanal").toString();
                jefCanal =
                        ctx.getSessionScope().get("JefeCanal") == null ? "" :
                        ctx.getSessionScope().get("JefeCanal").toString();
                idSup =
                        ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();

                sup =
ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
                idEjVenta =
                        ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
                        ctx.getSessionScope().get("IdEjecVenta").toString();
                ejVenta =
                        ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
                idLead =
                        ctx.getSessionScope().get("iDlEaD") == null ? "" : ctx.getSessionScope().get("iDlEaD").toString();

                Util.Log(idLead + "ID LEAD");

                Util.Log(fecha2daLlam + "FECHA 2DA 1");

                if (tipoGestion.equalsIgnoreCase("1") &&
                    grupoGestion.equalsIgnoreCase("1")) {

                    double montMaxi =
                        Double.parseDouble(txtMonMax.getValue().toString());
                    double montMin =
                        Double.parseDouble(txtMontMin.getValue().toString());
                    int montoMaxim = (int)montMaxi;
                    int montoMinim = (int)montMin;

                    if (montoDesem.intValue() >= montoMinim &&
                        montoDesem.intValue() <= montoMaxim) {

                        /*    leadPayload.mergeLead(Long.parseLong(idLead), nombreFb,
                                              idCanal, canal, idJefCanal,
                                              jefCanal, idSup, sup, idEjVenta,
                                              ejVenta, telefono, tipoGestion,
                                              grupoGestion, detalleGestion,
                                              auxFeedbackContactado,
                                              auxFeedbackContactadoEfec,
                                              auxFeedbackVenta,
                                              auxFeedbackRevenueVenta,
                                              nomDetGest, nomGrupGest,
                                              nomTipGest, fecGestion, obs,
                                              telfAd, dirAd, emailAd, idCpg,
                                              cpgNom, montoDesem, fecha2daLlam,
                                              contacto, numDoc, tipDoc, prod,
                                              flagInbound, flagOutbound, horaFeedback);*/
                    } else {
                        RichPopup.PopupHints hints =
                            new RichPopup.PopupHints();
                        getPopupMensajeMonto().show(hints);
                        txtMensajeVal.setValue("Debe ingresar un monto entre " +
                                               montMin + " hasta " + montMaxi);
                        return;
                    }
                } else {
                    /*   leadPayload.mergeLead(Long.parseLong(idLead), nombreFb,
                                          idCanal, canal, idJefCanal, jefCanal,
                                          idSup, sup, idEjVenta, ejVenta,
                                          telefono, tipoGestion, grupoGestion,
                                          detalleGestion,
                                          auxFeedbackContactado,
                                          auxFeedbackContactadoEfec,
                                          auxFeedbackVenta,
                                          auxFeedbackRevenueVenta, nomDetGest,
                                          nomGrupGest, nomTipGest, fecGestion,
                                          obs, telfAd, dirAd, emailAd, idCpg,
                                          cpgNom, montoDesem, fecha2daLlam,
                                          contacto, numDoc, tipDoc, prod,
                                          flagInbound, flagOutbound);*/
                }

                btnSigVenta.setDisabled(false);

                Util.Log(fecha2daLlam + "FECHA 2DA");

                if (tipoGestion.equalsIgnoreCase("1") &&
                    grupoGestion.equalsIgnoreCase("1")) {
                    String idEjecutivoVenta =
                        ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
                        ctx.getSessionScope().get("IdEjecVenta").toString();

                    DosificacionEjecutivoBO.actualizarFlagUltimo2(idEjecutivoVenta);
                    this.mostrarLead(idEjecutivoVenta);

                    DosificacionEjecutivoBO.eliminarLead(idLead);

                }

                if (tipoGestion.equalsIgnoreCase("1") &&
                    grupoGestion.equalsIgnoreCase("2")) {
                    String idEjecutivoVenta =
                        ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
                        ctx.getSessionScope().get("IdEjecVenta").toString();

                    DosificacionEjecutivoBO.actualizarFlagUltimo2(idEjecutivoVenta);
                    this.mostrarLead(idEjecutivoVenta);

                    DosificacionEjecutivoBO.eliminarLead(idLead);

                }


                try {


                    if (!fecha2daLlam.equalsIgnoreCase("")) {
                        DosificacionEjecutivoBO.actualizarFechaProgramada(idLead,
                                                                          fecha2daLlam);
                    }
                } catch (Exception e) {
                    Util.Log("Error hora programada" + e);
                }


            } catch (WebServiceException e) {
                e.printStackTrace();
            }

            idLead =
                    ctx.getSessionScope().get("iDlEaD") == null ? "" : ctx.getSessionScope().get("iDlEaD").toString();


            Util.Log(idLead + "IIIIIIIIIIIIIIIIIIII");
            //    limpiarPopup(rowSelected);
            buscarFeedback(idLead);
            getPopupTelfExtra().hide();

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getPopupMensajeError().show(hints);

        } else {
            String limpGrupGest = "";
            String limpDetGest = "";
            txtGrupGestTlfExt.setValue(limpGrupGest);
            txtDetGestTlfExt.setValue(limpDetGest);
            Util.Log(txtGrupGestTlfExt.getValue().toString() + "cod");
           

        }

    }

    public void crearFb(ActionEvent actionEvent) throws ParseException,
                                                        DatatypeConfigurationException,
                                                        ParserConfigurationException,
                                                        SAXException,
                                                        IOException {
            
        crearFeedBack();    
        if (txtValorTelf.getValue().toString().equalsIgnoreCase("1")) {
            txtTelf01.setDisabled(false);
        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("2")) {
            txtTelf02.setDisabled(false);
        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("3")) {
            txtTelf03.setDisabled(false);
        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("4")) {
            txtCel01.setDisabled(false);
        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("5")) {
            txtCel02.setDisabled(false);
        } else if (txtValorTelf.getValue().toString().equalsIgnoreCase("6")) {
            txtCel03.setDisabled(false);
        }
    }

    public String nvl(Row currRow, String a, String b) {
        try {
            if (currRow.getAttribute(a) != null)
                return currRow.getAttribute(a).toString();
            else
                return b;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + a + " NullPointerException");
            return b;
        }
    }

    private String nvlNoRow(RichInputText txtCodDetGest, String string) {
        try {
            if (txtCodDetGest.getValue() != null)
                return txtCodDetGest.getValue().toString();
            else
                return string;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + txtCodDetGest + " NullPointerException");
            return string;
        }
    }

    private String nvlNoRowString(String txtCodDetGest, String string) {
        try {
            if (txtCodDetGest != null)
                return txtCodDetGest;
            else
                return string;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + txtCodDetGest + " NullPointerException");
            return string;
        }
    }


    public boolean validarCreacionFb(Row currRow) {

        Boolean crearFb = false;
        String nombreFb = "";
        String idCanal = "";
        String canal = "";
        String idJefCanal = "";
        String jefCanal = "";
        String idSup = "";
        String sup = "";
        String idEjVenta = "";
        String ejVenta = "";
        String telefono = "";
        String tipoGestion = "";
        String grupoGestion = "";
        String detalleGestion = "";
        String nomDetGest = "";
        String nomGrupGest = "";
        String nomTipGest = "";
        String obs = "";
        String telfAd = "";
        String dirAd = "";
        String emailAd = "";


        nombreFb = nvl(currRow, "nomFb", "");
        idCanal = nvl(currRow, "idCanal", "");
        canal = nvl(currRow, "canal", "");
        idJefCanal = nvl(currRow, "idJefCanal", "");
        jefCanal = nvl(currRow, "jefCanal", "");
        idSup = nvl(currRow, "idSup", "");
        sup = nvl(currRow, "sup", "");
        idEjVenta = nvl(currRow, "idEjVenta", "");
        ejVenta = nvl(currRow, "ejVenta", "");
        telefono = nvl(currRow, "telf", "");
        tipoGestion = nvl(currRow, "codTipGest", "");
        grupoGestion = nvl(currRow, "codGrupGest", "");
        detalleGestion = nvl(currRow, "codDetGest", "");
        nomDetGest = nvl(currRow, "detGest", "");
        nomGrupGest = nvl(currRow, "grupGest", "");
        nomTipGest = nvl(currRow, "tipoGest", "");
        obs = nvl(currRow, "observaciones", " ");
        telfAd = nvl(currRow, "telfAd", " ");
        dirAd = nvl(currRow, "direccionAd", " ");
        emailAd = nvl(currRow, "emailAd", " ");

        if (!nombreFb.equalsIgnoreCase("") &&
            !idEjVenta.equalsIgnoreCase("") && !idCanal.equalsIgnoreCase("") &&
            !canal.equalsIgnoreCase("") && !idJefCanal.equalsIgnoreCase("") &&
            !jefCanal.equalsIgnoreCase("") && !idSup.equalsIgnoreCase("") &&
            !sup.equalsIgnoreCase("") && !ejVenta.equalsIgnoreCase("") &&
            !telefono.equalsIgnoreCase("") &&
            !tipoGestion.equalsIgnoreCase("") &&
            !grupoGestion.equalsIgnoreCase("") &&
            !detalleGestion.equalsIgnoreCase("") &&
            !nomDetGest.equalsIgnoreCase("") &&
            !nomGrupGest.equalsIgnoreCase("") &&
            !nomTipGest.equalsIgnoreCase("") && !obs.equalsIgnoreCase("") &&
            !telfAd.equalsIgnoreCase("") && !dirAd.equalsIgnoreCase("") &&
            !emailAd.equalsIgnoreCase("")) {

            crearFb = true;
            Util.Log(crearFb + "GHB 2");

        } else {

            crearFb = false;
            Util.Log(crearFb + "GHB 3");

        }
        return crearFb;
    }

    public boolean validarCreacionFbTelfExtra(Row currRow) {

        Boolean crearFb = false;
        String nombreFb = "";
        String idCanal = "";
        String canal = "";
        String idJefCanal = "";
        String jefCanal = "";
        String idSup = "";
        String sup = "";
        String idEjVenta = "";
        String ejVenta = "";
        String tipoGestion = "";
        String grupoGestion = "";
        String detalleGestion = "";
        String nomDetGest = "";
        String nomGrupGest = "";
        String nomTipGest = "";
        String obs = "";
        String telfAd = "";
        String dirAd = "";
        String emailAd = "";


        nombreFb = nvl(currRow, "nomFb", "");
        idCanal = nvl(currRow, "idCanal", "");
        canal = nvl(currRow, "canal", "");
        idJefCanal = nvl(currRow, "idJefCanal", "");
        jefCanal = nvl(currRow, "jefCanal", "");
        idSup = nvl(currRow, "idSup", "");
        sup = nvl(currRow, "sup", "");
        idEjVenta = nvl(currRow, "idEjVenta", "");
        ejVenta = nvl(currRow, "ejVenta", "");
        tipoGestion = nvl(currRow, "codTipGest", "");
        grupoGestion = nvl(currRow, "codGrupGest", "");
        detalleGestion = nvl(currRow, "codDetGest", "");
        nomDetGest = nvl(currRow, "detGest", "");
        nomGrupGest = nvl(currRow, "grupGest", "");
        nomTipGest = nvl(currRow, "tipoGest", "");
        obs = nvl(currRow, "observaciones", " ");
        telfAd = nvl(currRow, "telfAd", " ");
        dirAd = nvl(currRow, "direccionAd", " ");
        emailAd = nvl(currRow, "emailAd", " ");

        if (!nombreFb.equalsIgnoreCase("") &&
            !idEjVenta.equalsIgnoreCase("") && !idCanal.equalsIgnoreCase("") &&
            !canal.equalsIgnoreCase("") && !idJefCanal.equalsIgnoreCase("") &&
            !jefCanal.equalsIgnoreCase("") && !idSup.equalsIgnoreCase("") &&
            !sup.equalsIgnoreCase("") && !ejVenta.equalsIgnoreCase("") &&
            !tipoGestion.equalsIgnoreCase("") &&
            !grupoGestion.equalsIgnoreCase("") &&
            !detalleGestion.equalsIgnoreCase("") &&
            !nomDetGest.equalsIgnoreCase("") &&
            !nomGrupGest.equalsIgnoreCase("") &&
            !nomTipGest.equalsIgnoreCase("") && !obs.equalsIgnoreCase("") &&
            !telfAd.equalsIgnoreCase("") && !dirAd.equalsIgnoreCase("") &&
            !emailAd.equalsIgnoreCase("")) {

            crearFb = true;
            Util.Log(crearFb + "GHB 2");

        } else {

            crearFb = false;
            Util.Log(crearFb + "GHB 3");

        }
        return crearFb;
    }

    public String nomFeedback() {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String nomFb = "";


        try {
            String query =
                "select SBP_SLS_NOM_FB_PKG.nom_feedback NOM_FB from dual";

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                nomFb = rs.getString("NOM_FB");

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(nomFb + "nomFb");
        return nomFb;
    }

    public List<Canal> listXcpg() {
        List<Canal> listCanal = new ArrayList<Canal>();


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idSup = "";
        String idCpg = "";
        String idCanal = "";
        String cant = "";
        String porcRep = "";
        String pausa = "";


        try {
            String query =
                "select id_sup ID_SUP, id_cpg ID_CPG, id_canal ID_CANAL, cantidad CANT, porc_rep PORC_REP, pausado PAUSA from SBP_SLS_DOSIF_X_CPG";

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                Canal cnl = new Canal();

                while (rs.next()) {

                    idSup = rs.getString("ID_SUP");
                    idCpg = rs.getString("ID_CPG");
                    idCanal = rs.getString("ID_CANAL");
                    cant = rs.getString("CANT");
                    porcRep = rs.getString("PORC_REP");
                    pausa = rs.getString("PAUSA");

                    cnl.setIdSup(idSup);
                    cnl.setIdCpg(idCpg);
                    cnl.setCanalId(idCanal);
                    cnl.setCant(cant);
                    cnl.setPorcAsig(porcRep);
                    cnl.setPausa(pausa);
                    listCanal.add(cnl);
                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listCanal;
    }

    public void poblarTabla(ActionEvent actionEvent) {

        ServiceBiPublisher pantOpty = new ServiceBiPublisher();

        //------<INI>Obtención de data de tabla de configuración-----
        List<Canal> listIdSup = new ArrayList<Canal>();
        listIdSup = listIdSup();
        Util.Log("-------------------<1>-----------------");

        //------<INI>Obtención de ejecutivos de venta-----
        List<EjVenta> listEjVenta = new ArrayList<EjVenta>(); //operador
        List<EjVenta> listEjVentaAcum = new ArrayList<EjVenta>(); //acumulador
        for (Canal listIdSupe : listIdSup) {
            listEjVenta =
                    pantOpty.obtenerEjVenta(listIdSupe.getIdSup()); //obtenemos lista del SALES CLOUD
            listEjVentaAcum.addAll(listEjVenta);
        } //acumulamos las listas
        Util.Log("-------------------<2>-----------------");

        //------<INI>Obtención de oportunidades-----
        List<OptyRandom> listRdmOp = new ArrayList<OptyRandom>(); //operador
        List<OptyRandom> listRdmAcum =
            new ArrayList<OptyRandom>(); //acumulador

        for (Canal tblConfig : listIdSup) {
            for (EjVenta ejeVenta : listEjVentaAcum) {
                if (tblConfig.getIdSup().equalsIgnoreCase(ejeVenta.getIdSup()) &&
                    tblConfig.getCanalId().equalsIgnoreCase(ejeVenta.getIdCanal())) {

                    Double leadViejo1;
                    Double leadNuevo1;
                    leadViejo1 =
                            Double.parseDouble(tblConfig.getCant()) * (Double.parseDouble(tblConfig.getPorcAsig())) /
                            100;
                    leadNuevo1 =
                            Double.parseDouble(tblConfig.getCant()) - leadViejo1;
                    //--TRACE--
                    Util.Log(ejeVenta.getIdEjVenta() + "|" +
                             leadViejo1.toString() + "|" +
                             leadNuevo1.toString() + "|" +
                             tblConfig.getIdCpg() + "|" +
                             tblConfig.getCanalId());

                    /* listRdmOp = pantOpty.obtenerOptyRandom(ejeVenta.getIdEjVenta(),
                                                             leadViejo1.toString(),
                                                             leadNuevo1.toString(),
                                                             tblConfig.getIdCpg(),
                                                             tblConfig.getCanalId());

                      Util.Log(listRdmOp.size() + "");
                      listRdmAcum.addAll(listRdmOp); */
                }
            }
        } //acumulamos las listas
        Util.Log("-------------------<3>-----------------");

        //------<INI>Inserción de data----------
        for (OptyRandom ListRdmAcumF : listRdmAcum) {
            insertDataDosificador(ListRdmAcumF.getOptyId(),
                                  ListRdmAcumF.getOptyNumber(), "0");
        }
        ;
        Util.Log("-------------------<4>-----------------");


        for (Canal listIdSupe : listIdSup) {

            listIdSupe.getCanalId();
            listIdSupe.getIdSup();
            listIdSupe.getIdCpg();
            listIdSupe.getPorcAsig();
            listIdSupe.getCant();
            //-------------------------------TRACE-------------------------------
            Util.Log("-------------------<INI TABLA CONFIG>-----------------");
            Util.Log("1:" + listIdSupe.getCanalId());
            Util.Log("2:" + listIdSupe.getIdSup());
            Util.Log("3:" + listIdSupe.getIdCpg());
            Util.Log("4:" + listIdSupe.getPorcAsig());
            Util.Log("5:" + listIdSupe.getCant());
            Util.Log("-------------------<FIN TABLA CONFIG>-----------------");

        }

        Util.Log("-------------------<INI EJECUTIVOS>-----------------" +
                 listEjVentaAcum.size());
        for (EjVenta ListEjVentaAcum : listEjVentaAcum) {
            //-------------------------------TRACE-------------------------------
            Util.Log("1:" + ListEjVentaAcum.getEjVenta());
            Util.Log("2:" + ListEjVentaAcum.getIdEjVenta());
        }
        Util.Log("-------------------<FIN EJECUTIVOS>-----------------");

        Util.Log("-------------------<INI OPPT EJE>-----------------" +
                 listRdmAcum.size());
        for (OptyRandom ListRdmAcum : listRdmAcum) {
            //-------------------------------TRACE-------------------------------
            Util.Log("1:" + ListRdmAcum.getOptyId());
            Util.Log("2:" + ListRdmAcum.getOptyNumber());
        }
        Util.Log("-------------------<FIN OPPT EJE>-----------------");

    }

    public String optyRandom1() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String optyId = "";

        String idEjVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        try {
            String query =
                "SELECT id_opty OPTY FROM SBP_SLS_OPP_DOSIF_X_USER where estado = '0' and id_ej_venta = " +
                idEjVenta + " and rownum = 1";

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                optyId = rs.getString("OPTY");
                Util.Log("opt" + optyId);

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(optyId + "OPTY");
        return optyId;
    }

    public List<Canal> listIdSup() {

        List<Canal> listIdSup = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idSup = "";
        String idCpg = "";
        String idCanal = "";
        String cant = "";
        String porcRep = "";


        try {
            String query =
                "select id_sup ID_SUP, id_cpg ID_CPG, id_canal ID_CANAL, cantidad CANT, porc_rep PORC_REP, pausado PAUSA from SBP_SLS_DOSIF_X_CPG ";

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                Canal sup = new Canal();
                idSup = rs.getString("ID_SUP");
                idCpg = rs.getString("ID_CPG");
                idCanal = rs.getString("ID_CANAL");
                cant = rs.getString("CANT");
                porcRep = rs.getString("PORC_REP");


                sup.setIdSup(idSup);
                sup.setIdCpg(idCpg);
                sup.setCanalId(idCanal);
                sup.setCant(cant);
                sup.setPorcAsig(porcRep);
                listIdSup.add(sup);

            }

            //}
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listIdSup;
    }


    public String obtenerCantidad() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String cant = "";


        try {
            String query =
                "select cantidad CANT from SBP_SLS_DOSIF_X_CPG"; // where id_sup = " + idSup + " and id_cpg = " +  idCpg;

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                cant = rs.getString("CANT");
                Util.Log("cant" + cant);

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(cant + "OPTY");
        return cant;
    }


    public String obtenerPorcRept() {

        ADFContext ctx = ADFContext.getCurrent();

        String idSup =
            ctx.getSessionScope().get("IdSup") == null ? "" : ctx.getSessionScope().get("IdSup").toString();
        String idCpg =
            ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String porcRep = "";


        try {
            String query = "select porc_rep PORC_REP from SBP_SLS_DOSIF_X_CPG";

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                porcRep = rs.getString("PORC_REP");
                Util.Log("cant" + porcRep);

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(porcRep + "OPTY");
        return porcRep;
    }

    public void actualizaEstadoOpty(String optyId) {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;


        try {

            Util.Log("entra");

            String query =
                "update SBP_SLS_OPP_DOSIF_X_USER set estado = '1' where id_opty = " +
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

    public void insertDataDosificador(String IdOpty, String IdEjVenta,
                                      String Estatus) {
        PreparedStatement prepareStatement = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;

        String query =
            "BEGIN SBP_SLS_OPP_DOSIF_X_USER_PKG.pr_insert_opty(?,?,?);END;";


        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, IdOpty);
            prepareStatement.setString(2, IdEjVenta);
            prepareStatement.setString(3, Estatus);

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


  /*  public void obtenerCanal(String userName) {

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
        Util.Log("inicio reporte obt canal");
        lista2 = canal.obtenerCanal(userName);
        Util.Log("fin reporte obt canal");
        
        Util.Log("lista2 --> " + lista2.size());
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

        ctx.getSessionScope().put("IdCan", canalId);
        ctx.getSessionScope().put("SupId", supId);
        ctx.getSessionScope().put("Canal", nomCanal);
        ctx.getSessionScope().put("JefeCanal", jefeCanal);
        ctx.getSessionScope().put("IdJefeCanal", idJefeCanal);
        ctx.getSessionScope().put("Super", sup);
        ctx.getSessionScope().put("EjecVenta", ejVenta);
        ctx.getSessionScope().put("IdEjecVenta", idEjecVenta);
    }
    
    */
    
  public List<Canal> listInfoVenta(String nomEjec) {
      
      String ejecVenta = nomEjec.replace(".", " ");

      List<Canal> listInfoVenta = new ArrayList<Canal>();

      Connection conn = null;
      PreparedStatement st = null;
      ResultSet rs = null;
      String jefeCanal = "";
      String idJefeCanal = "";
      String canalId = "";
      String supId = "";
      String nomCanal = "";
      String sup = "";
      String idEjecVenta = "";
      String ejVenta = "";


      try {
          
          String query = "select id_canal ID_CANAL, nom_can NOM_CAN, id_jefe_canal ID_JEFE_CANAL, jef_can JEF_CAN, id_supervisor ID_SUPERVISOR, sup SUP, ejecutivo EJECUTIVO , id_ejecutivo ID_EJECUTIVO from sbp_sls_lead_dosif_x_user where ejecutivo =  '" + ejecVenta + "' and rownum = 1";
            
          Util.Log(query);
          
          sbp.utils.Connection connection = new sbp.utils.Connection();

          conn = connection.getConnection();

          st = conn.prepareStatement(query);

          rs = st.executeQuery();

          while (rs.next()) {
              Canal info = new Canal();
              idJefeCanal = rs.getString("ID_JEFE_CANAL");
              jefeCanal = rs.getString("JEF_CAN");
              canalId = rs.getString("ID_CANAL");
              supId = rs.getString("ID_SUPERVISOR");
              sup = rs.getString("SUP");
              nomCanal = rs.getString("NOM_CAN");
              idEjecVenta = rs.getString("ID_EJECUTIVO");
              ejVenta = rs.getString("EJECUTIVO");

              info.setIdJefCanal(idJefeCanal);
              info.setJefCanal(jefeCanal);
              info.setCanalId(canalId);
              info.setIdSup(supId);
              info.setSup(sup);
              info.setCanal(nomCanal);
              info.setIdEjVenta(idEjecVenta);
              info.setEjVenta(ejVenta);
              listInfoVenta.add(info);

          }

          //}
      } catch (SQLException e) {
          Util.Log(e + "error");
      } finally {
          DbUtils.closeQuietly(rs);
          DbUtils.closeQuietly(st);
          DbUtils.closeQuietly(conn);
      }


      return listInfoVenta;
  }
    public void obtenerCanal(String userName) {

        ADFContext ctx = ADFContext.getCurrent();

     //   ServiceBiPublisher canal = new ServiceBiPublisher();

        String jefeCanal = "";
        String idJefeCanal = "";
        String canalId = "";
        String supId = "";
        String nomCanal = "";
        String sup = "";
        String idEjecVenta = "";
        String ejVenta = "";


        List<Canal> lista2 = new ArrayList<Canal>();
        Util.Log("inicio reporte obt canal");
        lista2 = listInfoVenta(userName);
      //  lista2 = canal.obtenerCanal(userName);
        Util.Log("fin reporte obt canal");
        
        Util.Log("lista2 --> " + lista2.size());
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

        ctx.getSessionScope().put("IdCan", canalId);
        ctx.getSessionScope().put("SupId", supId);
        ctx.getSessionScope().put("Canal", nomCanal);
        ctx.getSessionScope().put("JefeCanal", jefeCanal);
        ctx.getSessionScope().put("IdJefeCanal", idJefeCanal);
        ctx.getSessionScope().put("Super", sup);
        ctx.getSessionScope().put("EjecVenta", ejVenta);
        ctx.getSessionScope().put("IdEjecVenta", idEjecVenta);
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

        this.createInsert();
        this.createInsertPopup();
        this.createInsertSpeech();
        this.createInsertSpeech2();
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLeadNew = (String)resolveExpression("#{viewScope.idLead2}");
        
        String ejecutivoVenta = (String)resolveExpression("#{viewScope.userId2}");
        
        String nomBinSesion = (String)resolveExpression("#{viewScope.nomBin2}"); 
        
        String numLlamar = (String)resolveExpression("#{viewScope.telf2}");  
        
        ctx.getSessionScope().put("numLlamar", numLlamar);
        
        ctx.getSessionScope().put("nomBinSesion", nomBinSesion);
        
        ctx.getSessionScope().put("idLeadNew", idLeadNew);

       if (ctx.getSessionScope().get("LEADID") != null) {

            // Lead quetxtCliNom viene de la pantalla SBP_SLS_BUSQUEDA_DNI_OPP
            String sLeadId = ctx.getSessionScope().get("LEADID").toString();

            String ejVenta = ctx.getSessionScope().get("ejVentaDni") == null ? "" : ctx.getSessionScope().get("ejVentaDni").toString();

            txtEjecutivoVenta.setValue(ejVenta);

            Util.Log("Lead en session [SBP_SLS_BUSQUEDA_DNI_OPP] :: " + sLeadId);

            // Ocultar objetos
            this.btnSigVenta.setVisible(false);
            
        } else if(idLeadNew == null){

            Util.Log("idLeadNew :: " + idLeadNew);

            Util.Log("Ejecutivo :: " + ejecutivoVenta);
            
            Util.Log("inicio obtener canal");
            
            this.obtenerCanal(ejecutivoVenta);
            
            Util.Log("fin obtener canal");
           
            String partyId = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();

            Util.Log("inicio mostrar lead");
           
            validaTipUsuario(partyId);
                      
            String tipoCon = tipConexion(partyId);
                      
            ctx.getSessionScope().put("tipoCon", tipoCon);
                      
            String encriptar = encriptar(partyId);
                      
            ctx.getSessionScope().put("encriptar", encriptar);
                      
            this.mostrarLead(partyId);
           
            Util.Log("fin mostrar lead");
            
       } else {
           
           btnSigVenta.setVisible(false);
           
           ctx.getSessionScope().put("nomBinSesion", nomBinSesion);
            
           ctx.getSessionScope().put("idLeadNew", idLeadNew);
            
           Util.Log("idLeadNew :: " + idLeadNew);

           Util.Log("Ejecutivo :: " + ejecutivoVenta);
           
           Util.Log("inicio obtener canal");
            
           this.obtenerCanal(ejecutivoVenta);
            
           Util.Log("fin obtener canal");
            
           String partyId = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();

           Util.Log("inicio mostrar lead");
                      
           String tipoCon = tipConexion(partyId);
                    
           ctx.getSessionScope().put("tipoCon", tipoCon);
                      
           String encriptar = encriptar(partyId);
                      
           ctx.getSessionScope().put("encriptar", encriptar);
           
           Util.Log("entra predictivo");
           
           List<CpgLeadContAccProdFb> opp;
           
           validaTipUsuario(partyId);
           
           String tipUsuario = ctx.getSessionScope().get("tipUsuario") == null ? "" : ctx.getSessionScope().get("tipUsuario").toString();
            
           if(tipUsuario.equalsIgnoreCase("Predictivo")){
               
               try {
                   
                   opp = new DosificacionEjecutivoBO().obtenerInfoLead(idLeadNew);
                   
                   if(opp.get(0).getTelefono01().equalsIgnoreCase(numLlamar) ||
                      opp.get(0).getTelefono02().equalsIgnoreCase(numLlamar) ||
                      opp.get(0).getTelefono03().equalsIgnoreCase(numLlamar) ||
                      opp.get(0).getCelular01().equalsIgnoreCase(numLlamar) ||
                      opp.get(0).getCelular02().equalsIgnoreCase(numLlamar) ||
                      opp.get(0).getCelular03().equalsIgnoreCase(numLlamar)){
                                                                                
                        this.mostrarLead(opp);
                          
                   } else {
                       
                       FacesContext fctx = FacesContext.getCurrentInstance();
                       fctx.addMessage("",
                                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de teléfono utilizado no coincide con la información brindad por el Banco",
                                                        ""));
                   }
                   
                   
                   
               } catch (SQLException e) {
                   
                   Util.Log("Exception : " + e);
               }
               
           }
         
        }

        // Limpiar campos
        this.txtNomProd.setValue("");
        this.txtPromocion.setValue("");
        this.txtMemAn.setValue("");
        this.txtSegDes.setValue("");

        Util.Log("fin onload");
        cont = 0;
        
        bloquearTelefonos();
    }

    public void setTxtTelf1TipUser(RichCommandLink txtTelf1TipUser) {
        this.txtTelf1TipUser = txtTelf1TipUser;
    }

    public RichCommandLink getTxtTelf1TipUser() {
        return txtTelf1TipUser;
    }

    public void setTxtCel3TipUser(RichCommandLink txtCel3TipUser) {
        this.txtCel3TipUser = txtCel3TipUser;
    }

    public RichCommandLink getTxtCel3TipUser() {
        return txtCel3TipUser;
    }

    public void setTxttelf3TipUser(RichCommandLink txttelf3TipUser) {
        this.txttelf3TipUser = txttelf3TipUser;
    }

    public RichCommandLink getTxttelf3TipUser() {
        return txttelf3TipUser;
    }

    public void setTxtCel2TipUser(RichCommandLink txtCel2TipUser) {
        this.txtCel2TipUser = txtCel2TipUser;
    }

    public RichCommandLink getTxtCel2TipUser() {
        return txtCel2TipUser;
    }

    public void setTxttelf2TipUser(RichCommandLink txttelf2TipUser) {
        this.txttelf2TipUser = txttelf2TipUser;
    }

    public RichCommandLink getTxttelf2TipUser() {
        return txttelf2TipUser;
    }

    public void setTxtCel1TipUser(RichCommandLink txtCel1TipUser) {
        this.txtCel1TipUser = txtCel1TipUser;
    }

    public RichCommandLink getTxtCel1TipUser() {
        return txtCel1TipUser;
    }


    public void validaTipUsuario(String idEjecutivo){
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String tipUsuario = tipUser(idEjecutivo);
        
        ctx.getSessionScope().put("tipUsuario", tipUsuario);
        
        Util.Log("tipUsuario --> " + tipUsuario);
        
        if(tipUsuario.equalsIgnoreCase("Progresivo") || tipUsuario.equalsIgnoreCase("Predictivo")){
           
            glTelf01.setDisabled(false);
            
            glTelf01.setVisible(true);
            
            txtTelfNew01.setDisabled(false);
             
            txtTelfNew02.setDisabled(false);
            
            txtTelfNew03.setDisabled(false);
            
            txtCel1TipUser.setDisabled(false);
            
            txtCel2TipUser.setDisabled(false);
            
            txtCel3TipUser.setDisabled(false);
            
            txtCel01.setVisible(false);
            
            txtCel02.setVisible(false);
            
            txtCel03.setVisible(false);
            
            txtTelf01.setVisible(false);
            
            txtTelf02.setVisible(false);
            
            txtTelf03.setVisible(false);
            
            txtLabelTelfFb.setVisible(false);
            
            txtTelfFb.setVisible(false);
           
        } else if(tipUsuario.equalsIgnoreCase("Manual")){
           
           glTelf01.setDisabled(true);
           
           glTelf01.setVisible(false);
           
          /* txtTelfNew01.setDisabled(false);
         
           txtTelfNew02.setDisabled(false);
        
           txtTelfNew03.setDisabled(false);*/
           
           txtCel01.setVisible(true);
           
           txtCel02.setVisible(true);
           
           txtCel03.setVisible(true);
           
           txtTelf01.setVisible(true);
           
           txtTelf02.setVisible(true);
           
           txtTelf03.setVisible(true);
           
           txtLabelTelfFb.setVisible(true);
           
           txtTelfFb.setVisible(true);
        }
        
        Util.Log("terminó valida ");
    }
  

    public void llamarCel01(ActionEvent actionEvent) {
        
        String numeroTelf = txtTelLla.getValue().toString();
        
        String numTelf = numeroTelf.replace("-", "");
       
        Util.Log("numTelf --> " + numTelf);
        
        try {
            redirect(numTelf);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
  /*  public String getLinkWithParamsTelf02() {
        
            // get the binding container
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
     
     
            // get an ADF attributevalue from the ADF page definitions
            AttributeBinding attr = (AttributeBinding) bindings.getControlBinding("Telefono01");
            String telf01 = txtTelLla.getValue().toString();
            String numTelf = telf01.replace("-", "");          
                
            
            //Encriptar{
            int Multi_Largo = 1;
            int Multi = 2;
            int Prefijo = 0;
            String Producto = new String();
            String NInvertido = new String();;
            
            if (numTelf.substring(0, 1) == "0") {
            Prefijo = 79;
            } else {
                Prefijo = 78;
            }
            
            if (numTelf.length() > 0) {
                Producto = Integer.toString(Integer.parseInt(numTelf)*Multi);
                NInvertido = new StringBuilder(Producto).reverse().toString();
            }
            String base = "sip:" + Prefijo + Multi_Largo + Multi + NInvertido;

            
            Util.Log("base --> " + base);
            return base;
        }  
    
    public String getLinkWithParamsTelf03() {
        
            // get the binding container
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            
            String telf01 = txtTelLla.getValue().toString();
            String numTelf = telf01.replace("-", "");
            
                
            String base = "tel:" + numTelf;
            
            Util.Log("base --> " + base);
            return base;
        }  */
    
    public String getLinkWithParamsTelf01() {
        
            // get the binding container
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            
            ADFContext ctx = ADFContext.getCurrent();
            
            String partyId = ctx.getSessionScope().get("IdEjecVenta") == null ? "" : ctx.getSessionScope().get("IdEjecVenta").toString();
            
            String tipoCon = ctx.getSessionScope().get("tipoCon") == null ? "" : ctx.getSessionScope().get("tipoCon").toString();
            
            String encriptar = ctx.getSessionScope().get("encriptar") == null ? "" : ctx.getSessionScope().get("encriptar").toString();
            
            String base = "";
            
            Util.Log("partyId --> " + partyId);
            
            Util.Log("tipoCon --> " + tipoCon);
            
            Util.Log("encriptar --> " + encriptar);
     
            String telfLlamar = txtTelLla.getValue().toString();
            
            String numTelf = telfLlamar.replace("-", "");
            
            Util.Log("numTelf --> " + numTelf);
            
            if(numTelf.equalsIgnoreCase("") || numTelf == null ){
                
                numTelf = "0";
                
            }
            
            Util.Log("numTelf 2 --> " + numTelf);
            
            if(encriptar.equalsIgnoreCase("Si")){
            
            //Encriptar{
            int Multi_Largo = 1;
            int Multi = 2;
            int Prefijo = 0;
            String Producto = new String();
            String NInvertido = new String();;
            
            if (numTelf.substring(0, 1) == "0") {
            Prefijo = 79;
            } else {
                Prefijo = 78;
            }
            
            if (numTelf.length() > 0) {
                Producto = Long.toString(Long.parseLong(numTelf)*Multi);
                NInvertido = new StringBuilder(Producto).reverse().toString();
            }
             base = tipoCon + ":" + Prefijo + Multi_Largo + Multi + NInvertido;
            
            } else if(encriptar.equalsIgnoreCase("No")) {
                
             base = tipoCon + ":" + numTelf; 
                
            }
            
            Util.Log("base --> " + base);
            
            return base;
        }  

    
    public void setGlTelf01(RichGoLink glTelf01) {
        this.glTelf01 = glTelf01;
    }

    public RichGoLink getGlTelf01() {
        return glTelf01;
    }

    public void setGlTelf02(RichGoLink glTelf02) {
        this.glTelf02 = glTelf02;
    }

    public RichGoLink getGlTelf02() {
        return glTelf02;
    }

    public void setGlTelf03(RichGoLink glTelf03) {
        this.glTelf03 = glTelf03;
    }

    public RichGoLink getGlTelf03() {
        return glTelf03;
    }

    public void setTxtTelfNew01(RichCommandLink txtTelfNew01) {
        this.txtTelfNew01 = txtTelfNew01;
    }

    public RichCommandLink getTxtTelfNew01() {
        return txtTelfNew01;
    }

    public void setTxtTelfNew02(RichCommandLink txtTelfNew02) {
        this.txtTelfNew02 = txtTelfNew02;
    }

    public RichCommandLink getTxtTelfNew02() {
        return txtTelfNew02;
    }

    public void setTxtTelfNew03(RichCommandLink txtTelfNew03) {
        this.txtTelfNew03 = txtTelfNew03;
    }

    public RichCommandLink getTxtTelfNew03() {
        return txtTelfNew03;
    }

    public void setTxtTelfFb(RichOutputText txtTelfFb) {
        this.txtTelfFb = txtTelfFb;
    }

    public RichOutputText getTxtTelfFb() {
        return txtTelfFb;
    }

    public void setTxtLabelTelfFb(RichPanelLabelAndMessage txtLabelTelfFb) {
        this.txtLabelTelfFb = txtLabelTelfFb;
    }

    public RichPanelLabelAndMessage getTxtLabelTelfFb() {
        return txtLabelTelfFb;
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
                        String horaFeedback, String auxFeedbackAgendado) {
            
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
            }

        public void run() throws RuntimeException{}

    }


    public void ejecutaHilo(Long idLead,String nombreFb, String idCanal, String canal,
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
                        String horaFeedback,
                        String auxFeedbackAgendado){
        
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
                                            horaFeedback, auxFeedbackAgendado) {
                
            @Override
            public void run() throws RuntimeException  {
                
                MergeLeadInvoke leadPayload = new MergeLeadInvoke();


                            try {
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
       
            ADFContext ctx = ADFContext.getCurrent();
                
            String codProdFb = ctx.getSessionScope().get("codProdFb") == null ? "" : ctx.getSessionScope().get("codProdFb").toString();
           
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
                                   "'" + idFb + "'," + 
                                   "'" + codProdFb + "')";
                  
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

    private void mostrarLead(String idEjecutivo) {

        this.txtTelf01.setDisabled(false);

        this.txtTelf02.setDisabled(false);

        this.txtTelf03.setDisabled(false);

        this.txtCel01.setDisabled(false);

        this.txtCel02.setDisabled(false);

        this.txtCel03.setDisabled(false);

        this.btnSigVenta.setDisabled(true);


        try {
            Util.Log("inicio obtener lead");
            List<CpgLeadContAccProdFb> opp = new DosificacionEjecutivoBO().obtenerLead(idEjecutivo);
            Util.Log("fin obtener lead");

            if (!opp.get(0).getIdLead().toString().equalsIgnoreCase("0")) {

                // Mostrar datos
                Util.Log("inicio mostrar lead");
                this.mostrarLead(opp);
                Util.Log("fin mostrar lead");

            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getPopupConfirm().show(hints);

            }

        } catch (Exception ex) {
            Util.Log("[mostrarLead] : " + ex.getMessage());
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getPopupConfirm().show(hints);

        }
        
        validaTipUsuario(idEjecutivo);


        btnSigVenta.setDisabled(true);

    }
    

    private void mostrarLead(List<CpgLeadContAccProdFb> opp) {

        cont = 0;

        txtTelf01.setDisabled(false);

        txtTelf02.setDisabled(false);

        txtTelf03.setDisabled(false);

        txtCel01.setDisabled(false);

        txtCel02.setDisabled(false);

        txtCel03.setDisabled(false);

        btnSigVenta.setDisabled(true);

        ADFContext ctx = ADFContext.getCurrent();

        try {
            
            Util.Log("ID LEAD INICIO --> " + opp.get(0).getIdLead().toString());
            
            ctx.getSessionScope().put("iDlEaD", opp.get(0).getIdLead().toString());
            
            ctx.getSessionScope().put("codProdFb", opp.get(0).getCodProd().toString());
            
            Util.Log("cod prod --> " + opp.get(0).getCodProd().toString());
            

            DCBindingContainer binding = this.getDCBindingsContainer();
            DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("CpgOptyProdContAccFb1Iterator");
            Row rw = null;

            rw = dcItteratorBindings.getNavigatableRowIterator().createRow();
            dcItteratorBindings.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            String idCpg = "";
            String nomCpg = "";
            String genero = opp.get(0).getGenero();
            String sr = "";
            String monedaOpty = opp.get(0).getMonLead();
            String mon = "";
            String nomMon = "";
            String consentimiento = "";
            
            ctx.getSessionScope().put("genero", opp.get(0).getGenero());
            
            if(opp.get(0).getFlagautorizacion().equalsIgnoreCase("Y")){
                consentimiento = " | Consentimiento de Información: Si";
            } else {
                consentimiento = " | Consentimiento de Información: No";
            }
            
            Util.Log("consentimiento --> " + consentimiento);
            
            txtConsentimiento.setValue(consentimiento);

            if (genero.equalsIgnoreCase("MASCULINO")) {
                sr = "Sr.";
            } else if (genero.equalsIgnoreCase("FEMENINO")) {
                sr = "Sra./Srta.";
            } else {
                sr = "";
            }

            if (monedaOpty.equalsIgnoreCase("PEN")) {
                mon = "S/ ";
            } else if (monedaOpty.equalsIgnoreCase("USD")) {
                mon = "$ ";
            } else {
                mon = "";
            }

            if (monedaOpty.equalsIgnoreCase("PEN")) {
                nomMon = "Soles ";
            } else if (monedaOpty.equalsIgnoreCase("USD")) {
                nomMon = "Dólares";
            } else {
                nomMon = "";
            }
            
            txtPromNueva.setValue(opp.get(0).getPromNueva());

            txtNomMon.setValue(nomMon);

            Util.Log("[mostrarLead] Registros Lead obtenidos : " + opp.size());

            txtEmail.setValue(opp.get(0).getEmail());
            txtTipDoc.setValue(opp.get(0).getTipoDocumento());
            txtNumDoc.setValue(opp.get(0).getNumeroDni());
            String dni = opp.get(0).getNumeroDni();
            ctx.getSessionScope().put("dni", dni);
            Util.Log("6");
            String prodSel = "";
            String nombre =
                sr + " " + opp.get(0).getNombre() + " " + opp.get(0).getSegundoNombre() +
                " " + opp.get(0).getApellidoPaterno() + " " +
                opp.get(0).getApellidoMaterno();
            
            String nomContacto = opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno();
            Util.Log("nomContacto --> " + nomContacto);
            ctx.getSessionScope().put("nomContactoVenta", nomContacto);
            txtPrimNom.setValue(opp.get(0).getNombre());
            txtSegNom.setValue(opp.get(0).getSegundoNombre());
            txtApePat.setValue(opp.get(0).getApellidoPaterno());
            txtApeMat.setValue(opp.get(0).getApellidoMaterno());
            txtNombre.setValue(nombre);
            txtContFb.setValue(opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno());
            txtFecNac.setValue(opp.get(0).getFechaNacimiento());
            rw.setAttribute("SegundoNombre", opp.get(0).getSegundoNombre());
            rw.setAttribute("ApellidoPaterno", opp.get(0).getApellidoPaterno());
            rw.setAttribute("ApellidoMaterno", opp.get(0).getApellidoMaterno());
            txtEdad.setValue(opp.get(0).getEdad() + " años");
            txtDistrito.setValue(opp.get(0).getDistContacto());
            txtProvincia.setValue(opp.get(0).getProvContacto());
            txtDpto.setValue(opp.get(0).getDeptCont());
            txtTelf01.setText(Util.NumeroFormateado(opp.get(0).getTelefono01()));
            itT01.setValue(Util.nvl(opp.get(0).getTelefono01(), "90000000"));
            txtTelf02.setText(Util.NumeroFormateado(opp.get(0).getTelefono02()));
            txtTelf03.setText(Util.NumeroFormateado(opp.get(0).getTelefono03()));
            txtCel01.setText(Util.NumeroFormateado(opp.get(0).getCelular01()));
            txtCel02.setText(Util.NumeroFormateado(opp.get(0).getCelular02()));
            txtCel03.setText(Util.NumeroFormateado(opp.get(0).getCelular03()));
            itT02.setValue(Util.nvl(opp.get(0).getTelefono02(), "90000000"));
            itT03.setValue(Util.nvl(opp.get(0).getTelefono03(), "90000000"));
            itT04.setValue(Util.nvl(opp.get(0).getCelular01(), "90000000"));
            itT05.setValue(Util.nvl(opp.get(0).getCelular02(), "90000000"));
            itT06.setValue(Util.nvl(opp.get(0).getCelular03(), "90000000"));
            rw.setAttribute("PaisContacto", opp.get(0).getPaisContacto());
            rw.setAttribute("DeptCont", opp.get(0).getDeptCont());
            rw.setAttribute("DistContacto", opp.get(0).getDistContacto());
            rw.setAttribute("ProvContacto", opp.get(0).getProvContacto());
            rw.setAttribute("Direccion", opp.get(0).getDireccion());
            rw.setAttribute("IdLead", opp.get(0).getIdLead());
            rw.setAttribute("NomLead", opp.get(0).getNomLead());
            txtProd.setValue(opp.get(0).getNomProd());
            rw.setAttribute("Tceamax", opp.get(0).getTceaMax());
            rw.setAttribute("Tceamin", opp.get(0).getTceaMin());
            rw.setAttribute("Teamax", opp.get(0).getTeaMax());
            rw.setAttribute("Teamin", opp.get(0).getTeaMin());
            txtMonMax.setValue(opp.get(0).getMontoMaximo());
            txtMontMin.setValue(opp.get(0).getMontoMinimo());
            txtPlazoMaximo.setValue(opp.get(0).getPlazoMaximo());
            txtPlazoMinimo.setValue(opp.get(0).getPlazoMinimo());
            txtBancoPp.setValue(opp.get(0).getBanco1NombreRccPP());
            txtBancoTc.setValue(opp.get(0).getBanco1NombreRccTC());
            txtDeudaPp.setValue(opp.get(0).getBanco1DeudaRccPP());
            txtDeudaTc.setValue(opp.get(0).getBanco1DEURccTC());
            txtLinTc.setValue(opp.get(0).getBanco1LINRccTC());
            txtSbs.setValue(opp.get(0).getSbsBaFiCaEDPEDPRccPP());
            rw.setAttribute("OrganizationName", opp.get(0).getOrganizationName());
            rw.setAttribute("Ruc", opp.get(0).getRuc());
            rw.setAttribute("PaisEmp", opp.get(0).getPaisEmp());
            rw.setAttribute("DeptEmp", opp.get(0).getDeptEmp());
            rw.setAttribute("ProvEmp", opp.get(0).getProvEmp());
            rw.setAttribute("DirEmp", opp.get(0).getDirEmp());
            rw.setAttribute("DistEmp", opp.get(0).getDistEmp());
            txtAvance.setValue(opp.get(0).getAvance());
            idCpg = opp.get(0).getIdCpg().toString();
            nomCpg = opp.get(0).getNomCpg();
            txtMonto.setValue(Util.formatearMonto(opp.get(0).getMonto()));
            txtMontoFbExtra.setValue(opp.get(0).getMonto());

            txtBanco1Link.setText(opp.get(0).getTcNombreBIN1());

            if (!opp.get(0).getTcNombreBIN1().equalsIgnoreCase("-")) {
                    Util.Log(opp.get(0).getTcNombreBIN1() +
                             " opp.get(0).getTcNombreBIN1()");
                    Util.Log("entra 1");
                    prodSel = opp.get(0).getTcNombreBIN1();
                }

                if (opp.get(0).getTcNombreBIN1().equalsIgnoreCase("-")) {
                    Util.Log("entra 2");
                    prodSel = opp.get(0).getTcNombreBIN2();
                }

                if (opp.get(0).getTcNombreBIN2().equalsIgnoreCase("-") &&
                    opp.get(0).getTcNombreBIN1().equalsIgnoreCase("-")) {
                    Util.Log("entra 3");
                    prodSel = opp.get(0).getTcNombreBIN3();
                }
            
            String nomContFile = opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno();

            ctx.getSessionScope().put("nomProd", prodSel);
            ctx.getSessionScope().put("contactoFileSpeech", nomContFile);
            txtProdSel.setValue(prodSel);
            
            Util.Log("Antes de entrar al sftp");
            
            try {
                mostrarSpeech2(prodSel);
            } catch (JSchException e) {
                e.printStackTrace(System.out);
                txtProdSel.setValue("");
            } catch (SftpException e) {
                e.printStackTrace(System.out);
                txtProdSel.setValue("");
            }
            
            txtBanco2Link.setText(opp.get(0).getTcNombreBIN2());
            txtBanco3Link.setText(opp.get(0).getTcNombreBIN3());

            txtTcTea1.setValue(opp.get(0).getTcTea1() + "%");
            txtTcTea2.setValue(opp.get(0).getTcTea2() + "%");
            txtTcTea3.setValue(opp.get(0).getTcTea3() + "%");
            String montoProd = mon + Util.formatearMonto(opp.get(0).getMonto());
            txtMontoProd.setValue(montoProd);
            ctx.getSessionScope().put("CodCpg", idCpg);
            ctx.getSessionScope().put("NomCpg", nomCpg);
            
            ctx.getSessionScope().put("tea", Util.nvl(opp.get(0).getTeaMin(), opp.get(0).getTeaMax()));
            ctx.getSessionScope().put("tcea", Util.nvl(opp.get(0).getTceaMin(), opp.get(0).getTceaMax()));
            
            String nom1 = opp.get(0).getNombre();
            String nom2 = opp.get(0).getSegundoNombre();
            String apePat = opp.get(0).getApellidoPaterno();
            String apeMat = opp.get(0).getApellidoMaterno();
            Util.Log("16");
            ctx.getSessionScope().put("nom1", nom1);
            ctx.getSessionScope().put("nom2", nom2);
            ctx.getSessionScope().put("apePat", apePat);
            ctx.getSessionScope().put("apeMat", apeMat);

            ctx.getSessionScope().put("montoLead", opp.get(0).getMonto());

            ctx.getSessionScope().put("CpgLeadContAccProdFb", opp.get(0));
            
            
            Util.Log("telf 01 --> " + opp.get(0).getTelefono01());
            
            Util.Log("inicio busca fb");
            buscarFeedback(opp.get(0).getIdLead().toString());
            Util.Log("fin busca fb");

        } catch (Exception ex) {
            Util.Log("[mostrarLead] : " + ex.getMessage());
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getPopupConfirm().show(hints);

        }

        btnSigVenta.setDisabled(true);
        
        bloquearTelefonos();
    }


    public void aceptarLead(ActionEvent actionEvent) {

        ADFContext ctx = ADFContext.getCurrent();

        String IdEjecutivo =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        DosificacionEjecutivoBO.ordenarAntiguos(IdEjecutivo);
        this.mostrarLead(IdEjecutivo);
        getPopupConfirm().hide();
    }

    public void rechazarLead(ActionEvent actionEvent) {
        getPopupConfirm().hide();
    }

    public void setTxtTipGestTlfExt(RichSelectOneChoice txtTipGestTlfExt) {
        this.txtTipGestTlfExt = txtTipGestTlfExt;
    }

    public RichSelectOneChoice getTxtTipGestTlfExt() {
        return txtTipGestTlfExt;
    }

    public void setTxtGrupGestTlfExt(RichSelectOneChoice txtGrupGestTlfExt) {
        this.txtGrupGestTlfExt = txtGrupGestTlfExt;
    }

    public RichSelectOneChoice getTxtGrupGestTlfExt() {
        return txtGrupGestTlfExt;
    }

    public void setTxtDetGestTlfExt(RichSelectOneChoice txtDetGestTlfExt) {
        this.txtDetGestTlfExt = txtDetGestTlfExt;
    }

    public RichSelectOneChoice getTxtDetGestTlfExt() {
        return txtDetGestTlfExt;
    }

    public void setPopupMensajeError(RichPopup popupMensajeError) {
        this.popupMensajeError = popupMensajeError;
    }

    public RichPopup getPopupMensajeError() {
        return popupMensajeError;
    }

    public void setBtnTelfExtra(RichCommandButton btnTelfExtra) {
        this.btnTelfExtra = btnTelfExtra;
    }

    public RichCommandButton getBtnTelfExtra() {
        return btnTelfExtra;
    }

    public void setTxtNombre(RichOutputLabel txtNombre) {
        this.txtNombre = txtNombre;
    }

    public RichOutputLabel getTxtNombre() {
        return txtNombre;
    }

    public void setTxtEdad(RichOutputLabel txtEdad) {
        this.txtEdad = txtEdad;
    }

    public RichOutputLabel getTxtEdad() {
        return txtEdad;
    }

    public void setTxtSexo(RichOutputLabel txtSexo) {
        this.txtSexo = txtSexo;
    }

    public RichOutputLabel getTxtSexo() {
        return txtSexo;
    }

    public void setTxtFecNac(RichOutputLabel txtFecNac) {
        this.txtFecNac = txtFecNac;
    }

    public RichOutputLabel getTxtFecNac() {
        return txtFecNac;
    }

    public void setTxtTipDoc(RichOutputLabel txtTipDoc) {
        this.txtTipDoc = txtTipDoc;
    }

    public RichOutputLabel getTxtTipDoc() {
        return txtTipDoc;
    }

    public void setTxtNumDoc(RichOutputLabel txtNumDoc) {
        this.txtNumDoc = txtNumDoc;
    }

    public RichOutputLabel getTxtNumDoc() {
        return txtNumDoc;
    }

    public void setTxtEmail(RichOutputLabel txtEmail) {
        this.txtEmail = txtEmail;
    }

    public RichOutputLabel getTxtEmail() {
        return txtEmail;
    }

    public void setTxtProd(RichOutputLabel txtProd) {
        this.txtProd = txtProd;
    }

    public RichOutputLabel getTxtProd() {
        return txtProd;
    }

    public void setTxtCuota(RichOutputLabel txtCuota) {
        this.txtCuota = txtCuota;
    }

    public RichOutputLabel getTxtCuota() {
        return txtCuota;
    }

    public void setTxtMontoMinimo(RichOutputLabel txtMontoMinimo) {
        this.txtMontoMinimo = txtMontoMinimo;
    }

    public RichOutputLabel getTxtMontoMinimo() {
        return txtMontoMinimo;
    }

    public void setTxtPlazoMinimo(RichOutputLabel txtPlazoMinimo) {
        this.txtPlazoMinimo = txtPlazoMinimo;
    }

    public RichOutputLabel getTxtPlazoMinimo() {
        return txtPlazoMinimo;
    }

    public void setTxtPlazoMaximo(RichOutputLabel txtPlazoMaximo) {
        this.txtPlazoMaximo = txtPlazoMaximo;
    }

    public RichOutputLabel getTxtPlazoMaximo() {
        return txtPlazoMaximo;
    }

    public void setTxtMontoMaximo(RichOutputLabel txtMontoMaximo) {
        this.txtMontoMaximo = txtMontoMaximo;
    }

    public RichOutputLabel getTxtMontoMaximo() {
        return txtMontoMaximo;
    }

    public void setTxtTelf01(RichCommandLink txtTelf01) {
        this.txtTelf01 = txtTelf01;
    }

    public RichCommandLink getTxtTelf01() {
        return txtTelf01;
    }

    public void setTxtCel01(RichCommandLink txtCel01) {
        this.txtCel01 = txtCel01;
    }

    public RichCommandLink getTxtCel01() {
        return txtCel01;
    }

    public void setTxtTelf02(RichCommandLink txtTelf02) {
        this.txtTelf02 = txtTelf02;
    }

    public RichCommandLink getTxtTelf02() {
        return txtTelf02;
    }

    public void setTxtCel02(RichCommandLink txtCel02) {
        this.txtCel02 = txtCel02;
    }

    public RichCommandLink getTxtCel02() {
        return txtCel02;
    }

    public void setTxtTelf03(RichCommandLink txtTelf03) {
        this.txtTelf03 = txtTelf03;
    }

    public RichCommandLink getTxtTelf03() {
        return txtTelf03;
    }

    public void setTxtCel03(RichCommandLink txtCel03) {
        this.txtCel03 = txtCel03;
    }

    public RichCommandLink getTxtCel03() {
        return txtCel03;
    }

    public void setTxtAvance(RichOutputLabel txtAvance) {
        this.txtAvance = txtAvance;
    }

    public RichOutputLabel getTxtAvance() {
        return txtAvance;
    }

    public void setTxtDeudaTc(RichOutputLabel txtDeudaTc) {
        this.txtDeudaTc = txtDeudaTc;
    }

    public RichOutputLabel getTxtDeudaTc() {
        return txtDeudaTc;
    }

    public void setTxtBancoTc(RichOutputLabel txtBancoTc) {
        this.txtBancoTc = txtBancoTc;
    }

    public RichOutputLabel getTxtBancoTc() {
        return txtBancoTc;
    }

    public void setTxtDeudaPp(RichOutputLabel txtDeudaPp) {
        this.txtDeudaPp = txtDeudaPp;
    }

    public RichOutputLabel getTxtDeudaPp() {
        return txtDeudaPp;
    }

    public void setTxtBancoPp(RichOutputLabel txtBancoPp) {
        this.txtBancoPp = txtBancoPp;
    }

    public RichOutputLabel getTxtBancoPp() {
        return txtBancoPp;
    }

    public void setTxtLinTc(RichOutputLabel txtLinTc) {
        this.txtLinTc = txtLinTc;
    }

    public RichOutputLabel getTxtLinTc() {
        return txtLinTc;
    }

    public void setTxtSbs(RichOutputLabel txtSbs) {
        this.txtSbs = txtSbs;
    }

    public RichOutputLabel getTxtSbs() {
        return txtSbs;
    }

    public void setTxtContFb(RichOutputText txtContFb) {
        this.txtContFb = txtContFb;
    }

    public RichOutputText getTxtContFb() {
        return txtContFb;
    }

    public void setBtnSigVenta(RichCommandButton btnSigVenta) {
        this.btnSigVenta = btnSigVenta;
    }

    public RichCommandButton getBtnSigVenta() {
        return btnSigVenta;
    }

    public void setTxtMontMin(RichInputText txtMontMin) {
        this.txtMontMin = txtMontMin;
    }

    public RichInputText getTxtMontMin() {
        return txtMontMin;
    }

    public void setTxtMonMax(RichInputText txtMonMax) {
        this.txtMonMax = txtMonMax;
    }

    public RichInputText getTxtMonMax() {
        return txtMonMax;
    }

    public void setPopupMensajeMonto(RichPopup popupMensajeMonto) {
        this.popupMensajeMonto = popupMensajeMonto;
    }

    public RichPopup getPopupMensajeMonto() {
        return popupMensajeMonto;
    }

    public void setTxtMensajeVal(RichOutputLabel txtMensajeVal) {
        this.txtMensajeVal = txtMensajeVal;
    }

    public RichOutputLabel getTxtMensajeVal() {
        return txtMensajeVal;
    }

    public void setPopupValidarCampos(RichPopup popupValidarCampos) {
        this.popupValidarCampos = popupValidarCampos;
    }

    public RichPopup getPopupValidarCampos() {
        return popupValidarCampos;
    }

    public void setTxtProvincia(RichOutputLabel txtProvincia) {
        this.txtProvincia = txtProvincia;
    }

    public RichOutputLabel getTxtProvincia() {
        return txtProvincia;
    }

    public void setTxtDistrito(RichOutputLabel txtDistrito) {
        this.txtDistrito = txtDistrito;
    }

    public RichOutputLabel getTxtDistrito() {
        return txtDistrito;
    }

    public void setTxtDpto(RichOutputLabel txtDpto) {
        this.txtDpto = txtDpto;
    }

    public RichOutputLabel getTxtDpto() {
        return txtDpto;
    }

    public void setTxtDireccion(RichOutputLabel txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public RichOutputLabel getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtMontoFb(RichInputText txtMontoFb) {
        this.txtMontoFb = txtMontoFb;
    }

    public RichInputText getTxtMontoFb() {
        return txtMontoFb;
    }

    public void setTxtMontoFbExtra(RichInputText txtMontoFbExtra) {
        this.txtMontoFbExtra = txtMontoFbExtra;
    }

    public RichInputText getTxtMontoFbExtra() {
        return txtMontoFbExtra;
    }

    public String createInsert() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setTxtCodDept(RichInputText txtCodDept) {
        this.txtCodDept = txtCodDept;
    }

    public RichInputText getTxtCodDept() {
        return txtCodDept;
    }

    public void setTxtCodProv(RichInputText txtCodProv) {
        this.txtCodProv = txtCodProv;
    }

    public RichInputText getTxtCodProv() {
        return txtCodProv;
    }

    public void setTxtCodDist(RichInputText txtCodDist) {
        this.txtCodDist = txtCodDist;
    }

    public RichInputText getTxtCodDist() {
        return txtCodDist;
    }

    public void buscarProductos(ActionEvent actionEvent) {

        buscarCobertura();

    }

    public void buscarCobertura() {

        List<Tarjetas> listaTarjeta = new ArrayList<Tarjetas>();

        listaTarjeta = listTarjetas();

        Util.Log(listaTarjeta.size() + "tamaño");

        if (listaTarjeta.size() > 0) {

            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Si existe cobertura",
                                             ""));


        } else {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sin cobertura",
                                             ""));
        }

    }

    public List<Tarjetas> listTarjetas() {

        ADFContext ctx = ADFContext.getCurrent();

        String nomProd =
            ctx.getSessionScope().get("nomProd") == null ? "" : ctx.getSessionScope().get("nomProd").toString();

        List<Tarjetas> listaTarjeta = new ArrayList<Tarjetas>();

        if (txtCodDist.getValue() != null && txtCodProv.getValue() != null &&
            txtCodDept.getValue() != null) {


            Util.Log("ENTRA COBERTURA");
            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs = null;
            String codigoDistrito = txtCodDist.getValue().toString();
            String codigoProvincia = txtCodProv.getValue().toString();
            String codigoDepto = txtCodDept.getValue().toString();

            String codDist = "";
            String dist = "";
            String codProv = "";
            String prov = "";
            String codDept = "";
            String dept = "";
            String codTarjeta = "";
            String nomTarjeta = "";

            try {
                String query = "select cod_dist cod_dist,\n" +
                    "       nom_dist nom_dist,\n" +
                    "       cod_prov cod_prov,\n" +
                    "       nom_prov nom_prov,\n" +
                    "       cod_dept cod_dept,\n" +
                    "       nom_dept nom_dept,\n" +
                    "       cod_tarjeta cod_tarjeta,\n" +
                    "       desc_tarj   desc_tarj\n" +
                    "  from SBP_SLS_TARJETAS where cod_dept = " + codigoDepto +
                    " and cod_prov = " + codigoProvincia + " and cod_dist = " +
                    codigoDistrito + " and desc_tarj = '" + nomProd + "'";


                /*    txtBanco1Link.getText() + "' or desc_tarj = '" +
                    txtBanco2Link.getText() + "' or desc_tarj = '" +
                    txtBanco3Link.getText() + "')";*/

                Util.Log(query + "QUERY");

                sbp.utils.Connection connection = new sbp.utils.Connection();

                conn = connection.getConnection();

                st = conn.prepareStatement(query);

                rs = st.executeQuery();

                while (rs.next()) {
                    Tarjetas tarjeta = new Tarjetas();

                    codDist = rs.getString("cod_dist");
                    dist = rs.getString("nom_dist");
                    codProv = rs.getString("cod_prov");
                    prov = rs.getString("nom_prov");
                    codDept = rs.getString("cod_dept");
                    dept = rs.getString("nom_dept");
                    codTarjeta = rs.getString("cod_tarjeta");
                    nomTarjeta = rs.getString("desc_tarj");


                    tarjeta.setCodDist(codDist);
                    tarjeta.setDis(dist);
                    tarjeta.setCodProv(codProv);
                    tarjeta.setProv(prov);
                    tarjeta.setCodDept(codDept);
                    tarjeta.setDep(dept);
                    tarjeta.setCodTarjeta(codTarjeta);
                    tarjeta.setTarjeta(nomTarjeta);
                    listaTarjeta.add(tarjeta);

                }

            } catch (SQLException e) {
                Util.Log(e + "error");
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(st);
                DbUtils.closeQuietly(conn);
            }

        } else {
            /*  FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un departamento, provincia y distrito",
                                             ""));*/
        }

        Util.Log(listaTarjeta.size() + "LISTA TARJETA");
        return listaTarjeta;

    }

    public void setTblTarjeta(RichTable tblTarjeta) {
        this.tblTarjeta = tblTarjeta;
    }

    public RichTable getTblTarjeta() {
        return tblTarjeta;
    }

    public void setPopupTarjetas(RichPopup popupTarjetas) {
        this.popupTarjetas = popupTarjetas;
    }

    public RichPopup getPopupTarjetas() {
        return popupTarjetas;
    }

    public String createInsertPopup() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert3");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setSoc7(RichSelectOneChoice soc7) {
        this.soc7 = soc7;
    }

    public RichSelectOneChoice getSoc7() {
        return soc7;
    }

    public void setItGestion(RichInputText itGestion) {
        this.itGestion = itGestion;
    }

    public RichInputText getItGestion() {
        return itGestion;
    }

    public void setPopupSpeech(RichPopup popupSpeech) {
        this.popupSpeech = popupSpeech;
    }

    public RichPopup getPopupSpeech() {
        return popupSpeech;
    }

    private RichInputText txtLeadId;
    private RichOutputText txtSpeeID;
    private RichOutputText txtUsuNom;
    private RichOutputText txtCliCod;
    private RichOutputText txtCliNom;
    private RichOutputText txtProCod;
    private RichOutputText txtProNom;
    private RichOutputText txtProTip;
    private RichOutputText txtProDes;
    private RichOutputText txtProMon;
    private RichInputText txtTelCas;
    private RichInputText txtTelCel;
    private RichInputText txtCorEle;
    private RichInputText txtTelRef;
    private RichInputText txtCliPar;
    private RichSelectOneChoice cboCliPar;
    private RichInputText txtCeLaAn;
    private RichInputText txtCeLaCa;
    private RichSelectOneChoice cboCeLaCa;
    private RichInputText txtCeLaDe;
    private RichInputText txtCeLaDi;
    private RichInputText txtCeLaDs;
    private RichInputText txtCeLaNo;
    private RichInputText txtCeLaPr;
    private RichInputText txtCeLaRe;
    private RichInputText txtCeLaRU;
    private RichInputText txtCeLaTe;
    private RichInputText txtCicFac;
    private RichSelectOneChoice cboCicFac;
    private RichInputText txtConlab;
    private RichSelectOneChoice cboConLab;
    private RichInputText txtCoApMa;
    private RichInputText txtCoApPa;
    private RichSelectOneChoice cboCoGene;
    private RichInputText txtCoGene;
    private RichInputText txtConNo1;
    private RichInputText txtConNo2;
    private RichInputText txtCoNuDo;
    private RichInputText txtCliDep;
    private RichInputText txtCliDir;
    private RichInputText txtCliDis;
    private RichInputText txtEstCiv;
    private RichSelectOneChoice cboEstCiv;
    private RichInputText txtFlaAut;
    private RichInputText txtFlaDis;
    private RichInputText txtCliGen;
    private RichSelectOneChoice cboCliGen;
    private RichInputText txtLuEnDe;
    private RichSelectOneChoice cboLuEnDe;
    private RichInputText txtLuEnIn;
    private RichSelectOneChoice cboLuEnIn;
    private RichInputText txtCliPro;
    private RichInputText txtDirRef;
    private RichOutputText txtSopTel;
    private RichOutputText txtSopNom;
    private RichOutputText txtValTEC;
    private RichOutputText txtValTEA;
    private RichOutputText txtCliNo2;
    private RichOutputText txtProNo2;
    private RichOutputText txtCliDoc;
    private RichOutputText txtCliNo3;
    private RichInputText txtCliNo4;
    private RichInputText txtCliNo5;
    private RichOutputText txtCliNo6;
    private RichOutputText txtCliNo7;
    private RichOutputText txtValMen;
    private RichSelectOneChoice cboValMen;
    private RichOutputText txtValDes;
    private RichSelectOneChoice cboValDes;
    private RichOutputText txtValEnv;

    public void setTxtLeadId(RichInputText it1) {
        this.txtLeadId = it1;
    }

    public RichInputText getTxtLeadId() {
        return txtLeadId;
    }

    public void setTxtSpeeID(RichOutputText rot) {
        this.txtSpeeID = rot;
    }

    public RichOutputText getTxtSpeeID() {
        return txtSpeeID;
    }
    // Bienvenida

    public void setTxtCliCod(RichOutputText rot) {
        this.txtCliCod = rot;
    }

    public RichOutputText getTxtCliCod() {
        return txtCliCod;
    }

    public void setTxtCliNom(RichOutputText rot) {
        this.txtCliNom = rot;
    }

    public RichOutputText getTxtCliNom() {
        return txtCliNom;
    }

    public void setTxtUsuNom(RichOutputText rot) {
        this.txtUsuNom = rot;
    }

    public RichOutputText getTxtUsuNom() {
        return txtUsuNom;
    }

    public void setTxtProCod(RichOutputText rot) {
        this.txtProCod = rot;
    }

    public RichOutputText getTxtProCod() {
        return txtProCod;
    }

    public void setTxtProNom(RichOutputText rot) {
        this.txtProNom = rot;
    }

    public RichOutputText getTxtProNom() {
        return txtProNom;
    }
    // Promociones

    public void setTxtProTip(RichOutputText rot) {
        this.txtProTip = rot;
    }

    public RichOutputText getTxtProTip() {
        return txtProTip;
    }

    public void setTxtProDes(RichOutputText rot) {
        this.txtProDes = rot;
    }

    public RichOutputText getTxtProDes() {
        return txtProDes;
    }

    public void setTxtCliNo2(RichOutputText rot) {
        this.txtCliNo2 = rot;
    }

    public RichOutputText getTxtCliNo2() {
        return txtCliNo2;
    }

    public void setTxtProNo2(RichOutputText rot) {
        this.txtProNo2 = rot;
    }

    public RichOutputText getTxtProNo2() {
        return txtProNo2;
    }

    public void setTxtProMon(RichOutputText rot) {
        this.txtProMon = rot;
    }

    public RichOutputText getTxtProMon() {
        return txtProMon;
    }
    // Datos del cliente

    public void setTxtCliDoc(RichOutputText rit) {
        this.txtCliDoc = rit;
    }

    public RichOutputText getTxtCliDoc() {
        return txtCliDoc;
    }

    public void setTxtCliNo3(RichOutputText rit) {
        this.txtCliNo3 = rit;
    }

    public RichOutputText getTxtCliNo3() {
        return txtCliNo3;
    }

    public void setTxtTelCas(RichInputText rit) {
        this.txtTelCas = rit;
    }

    public RichInputText getTxtTelCas() {
        return txtTelCas;
    }

    public void setTxtTelCel(RichInputText rit) {
        this.txtTelCel = rit;
    }

    public RichInputText getTxtTelCel() {
        return txtTelCel;
    }

    public void setTxtCorEle(RichInputText rit) {
        this.txtCorEle = rit;
    }

    public RichInputText getTxtCorEle() {
        return txtCorEle;
    }

    public void setTxtTelRef(RichInputText rit) {
        this.txtTelRef = rit;
    }

    public RichInputText getTxtTelRef() {
        return txtTelRef;
    }

    public void setCboCliPar(RichSelectOneChoice rit) {
        this.cboCliPar = rit;
    }

    public RichSelectOneChoice getCboCliPar() {
        return cboCliPar;
    }

    public void setTxtCliPar(RichInputText rit) {
        this.txtCliPar = rit;
    }

    public RichInputText getTxtCliPar() {
        return txtCliPar;
    }

    public void setCboEstCiv(RichSelectOneChoice rit) {
        this.cboEstCiv = rit;
    }

    public RichSelectOneChoice getCboEstCiv() {
        return cboEstCiv;
    }

    public void setTxtEstCiv(RichInputText rit) {
        this.txtEstCiv = rit;
    }

    public RichInputText getTxtEstCiv() {
        return txtEstCiv;
    }

    public void setCboCliGen(RichSelectOneChoice rit) {
        this.cboCliGen = rit;
    }

    public RichSelectOneChoice getCboCliGen() {
        return cboCliGen;
    }

    public void setTxtCliGen(RichInputText rit) {
        this.txtCliGen = rit;
    }

    public RichInputText getTxtCliGen() {
        return txtCliGen;
    }
    // Datos del conyuge

    public void setTxtCoApPa(RichInputText rot) {
        this.txtCoApPa = rot;
    }

    public RichInputText getTxtCoApPa() {
        return txtCoApPa;
    }

    public void setTxtCoApMa(RichInputText rot) {
        this.txtCoApMa = rot;
    }

    public RichInputText getTxtCoApMa() {
        return txtCoApMa;
    }

    public void setTxtConNo1(RichInputText rot) {
        this.txtConNo1 = rot;
    }

    public RichInputText getTxtConNo1() {
        return txtConNo1;
    }

    public void setTxtConNo2(RichInputText rot) {
        this.txtConNo2 = rot;
    }

    public RichInputText getTxtConNo2() {
        return txtConNo2;
    }

    public void setTxtCoNuDo(RichInputText rot) {
        this.txtCoNuDo = rot;
    }

    public RichInputText getTxtCoNuDo() {
        return txtCoNuDo;
    }

    public void setTxtConlab(RichInputText rot) {
        this.txtConlab = rot;
    }

    public RichInputText getTxtConlab() {
        return txtConlab;
    }

    public void setCboCoGene(RichSelectOneChoice rot) {
        this.cboCoGene = rot;
    }

    public RichSelectOneChoice getCboCoGene() {
        return cboCoGene;
    }

    public void setTxtCoGene(RichInputText rot) {
        this.txtCoGene = rot;
    }

    public RichInputText getTxtCoGene() {
        return txtCoGene;
    }
    // Direccion domicilio

    public void setTxtCliDir(RichInputText rot) {
        this.txtCliDir = rot;
    }

    public RichInputText getTxtCliDir() {
        return txtCliDir;
    }

    public void setTxtCliDep(RichInputText rot) {
        this.txtCliDep = rot;
    }

    public RichInputText getTxtCliDep() {
        return txtCliDep;
    }

    public void setTxtCliPro(RichInputText rot) {
        this.txtCliPro = rot;
    }

    public RichInputText getTxtCliPro() {
        return txtCliPro;
    }

    public void setTxtCliDis(RichInputText rot) {
        this.txtCliDis = rot;
    }

    public RichInputText getTxtCliDis() {
        return txtCliDis;
    }

    public void setTxtDirRef(RichInputText rot) {
        this.txtDirRef = rot;
    }

    public RichInputText getTxtDirRef() {
        return txtDirRef;
    }

    public void setCboConLab(RichSelectOneChoice rot) {
        this.cboConLab = rot;
    }

    public RichSelectOneChoice getCboConLab() {
        return cboConLab;
    }
    // Datos Laborales

    public void setTxtCeLaNo(RichInputText rit) {
        this.txtCeLaNo = rit;
    }

    public RichInputText getTxtCeLaNo() {
        return txtCeLaNo;
    }

    public void setTxtCeLaRU(RichInputText rit) {
        this.txtCeLaRU = rit;
    }

    public RichInputText getTxtCeLaRU() {
        return txtCeLaRU;
    }

    public void setTxtCeLaDi(RichInputText rit) {
        this.txtCeLaDi = rit;
    }

    public RichInputText getTxtCeLaDi() {
        return txtCeLaDi;
    }

    public void setTxtCeLaDe(RichInputText rit) {
        this.txtCeLaDe = rit;
    }

    public RichInputText getTxtCeLaDe() {
        return txtCeLaDe;
    }

    public void setTxtCeLaPr(RichInputText rit) {
        this.txtCeLaPr = rit;
    }

    public RichInputText getTxtCeLaPr() {
        return txtCeLaPr;
    }

    public void setTxtCeLaDs(RichInputText rit) {
        this.txtCeLaDs = rit;
    }

    public RichInputText getTxtCeLaDs() {
        return txtCeLaDs;
    }

    public void setTxtCeLaRe(RichInputText rit) {
        this.txtCeLaRe = rit;
    }

    public RichInputText getTxtCeLaRe() {
        return txtCeLaRe;
    }

    public void setTxtCeLaTe(RichInputText rit) {
        this.txtCeLaTe = rit;
    }

    public RichInputText getTxtCeLaTe() {
        return txtCeLaTe;
    }

    public void setTxtCeLaAn(RichInputText rit) {
        this.txtCeLaAn = rit;
    }

    public RichInputText getTxtCeLaAn() {
        return txtCeLaAn;
    }

    public void setCboCeLaCa(RichSelectOneChoice rit) {
        this.cboCeLaCa = rit;
    }

    public RichSelectOneChoice getCboCeLaCa() {
        return cboCeLaCa;
    }

    public void setTxtCeLaCa(RichInputText rit) {
        this.txtCeLaCa = rit;
    }

    public RichInputText getTxtCeLaCa() {
        return txtCeLaCa;
    }
    // Consignacion Independiente

    public void setTxtCliNo4(RichInputText rit) {
        this.txtCliNo4 = rit;
    }

    public RichInputText getTxtCliNo4() {
        return txtCliNo4;
    }

    public void setTxtLuEnIn(RichInputText rit) {
        this.txtLuEnIn = rit;
    }

    public RichInputText getTxtLuEnIn() {
        return txtLuEnIn;
    }

    public void setCboLuEnIn(RichSelectOneChoice rit) {
        this.cboLuEnIn = rit;
    }

    public RichSelectOneChoice getCboLuEnIn() {
        return cboLuEnIn;
    }
    // Consignacion Dependiente

    public void setTxtCliNo5(RichInputText rit) {
        this.txtCliNo5 = rit;
    }

    public RichInputText getTxtCliNo5() {
        return txtCliNo5;
    }

    public void setTxtLuEnDe(RichInputText rit) {
        this.txtLuEnDe = rit;
    }

    public RichInputText getTxtLuEnDe() {
        return txtLuEnDe;
    }

    public void setCboLuEnDe(RichSelectOneChoice rit) {
        this.cboLuEnDe = rit;
    }

    public RichSelectOneChoice getCboLuEnDe() {
        return cboLuEnDe;
    }
    // Cierre de Venta

    public void setTxtValTEA(RichOutputText rot) {
        this.txtValTEA = rot;
    }

    public RichOutputText getTxtValTEA() {
        return txtValTEA;
    }

    public void setTxtValTEC(RichOutputText rot) {
        this.txtValTEC = rot;
    }

    public RichOutputText getTxtValTEC() {
        return txtValTEC;
    }

    public void setTxtValMen(RichOutputText rit) {
        this.txtValMen = rit;
    }

    public RichOutputText getTxtValMen() {
        return txtValMen;
    }

    public void setCboValMen(RichSelectOneChoice rit) {
        this.cboValMen = rit;
    }

    public RichSelectOneChoice getcboValMen() {
        return cboValMen;
    }

    public void setTxtValDes(RichOutputText rit) {
        this.txtValDes = rit;
    }

    public RichOutputText getTxtValDes() {
        return txtValDes;
    }

    public void setCboValDes(RichSelectOneChoice rit) {
        this.cboValDes = rit;
    }

    public RichSelectOneChoice getcboValDes() {
        return cboValDes;
    }

    public void setTxtValEnv(RichOutputText rit) {
        this.txtValEnv = rit;
    }

    public RichOutputText getTxtValEnv() {
        return txtValEnv;
    }

    public void setTxtCicFac(RichInputText rit) {
        this.txtCicFac = rit;
    }

    public RichInputText getTxtCicFac() {
        return txtCicFac;
    }

    public void setCboCicFac(RichSelectOneChoice rit) {
        this.cboCicFac = rit;
    }

    public RichSelectOneChoice getcboCicFac() {
        return cboCicFac;
    }

    public void setTxtFlaDis(RichInputText rit) {
        this.txtFlaDis = rit;
    }

    public RichInputText getTxtFlaDis() {
        return txtFlaDis;
    }

    public void setTxtSopTel(RichOutputText rot) {
        this.txtSopTel = rot;
    }

    public RichOutputText getTxtSopTel() {
        return txtSopTel;
    }

    public void setTxtSopNom(RichOutputText rot) {
        this.txtSopNom = rot;
    }

    public RichOutputText getTxtSopNom() {
        return txtSopNom;
    }

    public void setTxtFlaAut(RichInputText rit) {
        this.txtFlaAut = rit;
    }

    public RichInputText getTxtFlaAut() {
        return txtFlaAut;
    }

    public void setTxtCliNo6(RichOutputText rit) {
        this.txtCliNo6 = rit;
    }

    public RichOutputText getTxtCliNo6() {
        return txtCliNo6;
    }

    public void setTxtCliNo7(RichOutputText rit) {
        this.txtCliNo7 = rit;
    }

    public RichOutputText getTxtCliNo7() {
        return txtCliNo7;
    }

    // Métodos

    private void MostrarDatosSpeechVenta() throws ParseException {

        txtPromocion.setValue("");
        txtPromSpeech2.setValue("");
        txtPromSpeech3.setValue("");

        ADFContext ctx = ADFContext.getCurrent();

        String nomProd = ctx.getSessionScope().get("nomProd") == null ? "" : ctx.getSessionScope().get("nomProd").toString();

        listXProd(nomProd);

        String idProd =
            ctx.getSessionScope().get("idProd") == null ? "" : ctx.getSessionScope().get("idProd").toString();

        Util.Log(idProd + "idProd");

        if (idProd.equalsIgnoreCase("4")) {
            Util.Log("ENTRA IF MJS");
            pnlMsj.setVisible(true);
            pnlMsjOro.setVisible(false);
        } else if (idProd.equalsIgnoreCase("6")) {
            Util.Log("ENTRA ELSE IF MJS");
            pnlMsjOro.setVisible(true);
            pnlMsj.setVisible(false);
        } else {
            Util.Log("ENTRA ELSE MJS");
            pnlMsj.setVisible(false);
            pnlMsjOro.setVisible(false);
        }

        String tcea =
            ctx.getSessionScope().get("tcea") == null ? "" : ctx.getSessionScope().get("tcea").toString();
        String comision =
            ctx.getSessionScope().get("comision") == null ? "" : ctx.getSessionScope().get("comision").toString();
        String membresia =
            ctx.getSessionScope().get("membresia") == null ? "" :
            ctx.getSessionScope().get("membresia").toString();
        String seguro =
            ctx.getSessionScope().get("seguro") == null ? "" : ctx.getSessionScope().get("seguro").toString();
        String promocion =
            ctx.getSessionScope().get("promocion") == null ? "" :
            ctx.getSessionScope().get("promocion").toString();
        String msjBienv =
            ctx.getSessionScope().get("msjBienv") == null ? "" : ctx.getSessionScope().get("msjBienv").toString();

        txtTelLla.getValue().toString();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = txtFecNac.getValue().toString();
        Date date1 = formatter.parse(dateInString);

        ArrayList<String> items =
            new ArrayList<String>(Arrays.asList((promocion).split("\\|", -1)));

        int sizeList = items.size() - 1;

        Util.Log(sizeList + "sizeList");

        if (sizeList == 1) {

            Util.Log("ENTRA if");
            txtPromocion.setValue(items.get(1));
            txtPromSpeech2.setValue("");
            txtPromSpeech3.setValue("");
        } else if (sizeList == 2) {

            Util.Log("ENTRA ELSE if");
            txtPromocion.setValue(items.get(1));
            txtPromSpeech2.setValue(items.get(2));
            txtPromSpeech3.setValue("");


        } else if (sizeList == 3) {

            Util.Log("ENTRA ELSE if 2");
            txtPromocion.setValue(items.get(1));
            txtPromSpeech2.setValue(items.get(2));
            txtPromSpeech3.setValue(items.get(3));

        }

        Util.Log("1");
        dtFecNaS.setValue(date1);
        this.txtMemAn.setValue(membresia);
        this.txtSegDes.setValue(seguro);
        this.txtMsjBienv.setValue(msjBienv);

        Util.Log("2");

        try {

            this.txtNomProd.setValue(nomProd);

            if (!this.txtPromocion.getValue().toString().equalsIgnoreCase("")) {
                this.txtPromocion.setValue(items.get(1));
            } else {
                this.txtPromocion.setValue("");
            }

            if (!this.txtMemAn.getValue().toString().equalsIgnoreCase("")) {
                this.txtMemAn.setValue(membresia);
            } else {
                this.txtMemAn.setValue("");
            }

            if (!this.txtSegDes.getValue().toString().equalsIgnoreCase("")) {
                this.txtSegDes.setValue(seguro);
            } else {
                this.txtSegDes.setValue("");
            }

            Util.Log("3");

            // Obtener los datos de session
            CpgLeadContAccProdFb l = (CpgLeadContAccProdFb)ctx.getSessionScope().get("CpgLeadContAccProdFb");

            String vendor =
                ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();

            String sup =
                ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();

            String nomCpg =
                ctx.getSessionScope().get("NomCpg") == null ? "" : ctx.getSessionScope().get("NomCpg").toString();

            DateFormat horaFormat = new SimpleDateFormat("HH");

            Date horaFormato = new Date();

            String horaFb = horaFormat.format(horaFormato);

            int hora = Integer.parseInt(horaFb) - 5;

            String horaFeedback = hora + "";
            Util.Log("3");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = new Date();

            String fecGestion = dateFormat.format(date);

            SpeechVenta e = new SpeechVenta();
            e.setLeadID(l.getIdLead().toString());
            e.setClienteCodigo(l.getCliente());
            e.setClienteNombre(l.getApellidoPaterno() + " " +
                               l.getApellidoMaterno() + ", " + l.getNombre() +
                               " " + l.getSegundoNombre());
            e.setUsuarioNombre(txtEjecutivoVenta.getValue().toString().replace(".",
                                                                               " "));
            e.setProductoCodigo(l.getCodigoProducto());
            e.setProductoNombre(Util.nvl(Util.nvl(l.getTcNombreBIN1(),
                                                  l.getTcNombreBIN2()),
                                         l.getTcNombreBIN3()));
            Util.Log(l.getMonto() + "l.getMonto()");
            e.setProductoMonto(Util.formatearMonto(l.getMonto()));
            e.setClienteNumeroDocumento(l.getNumeroDni());
            e.setValorTEA(Util.nvl(l.getTeaMin(), l.getTeaMax()));
            e.setValorTCEA(Util.nvl(l.getTceaMin(), l.getTceaMax()));
            e.setValorMembresia(""); // Falta definir campo
            e.setValorDesgravamen(""); // Falta definir campo
            e.setSoporteNumeroTelefono(""); // Falta definir campo
            e.setSupervisorNombre(sup); // Falta definir campo
            e.setValorEnvioEstadoCuenta("S/ 8.9");
            e.setCpgNom(nomCpg);
            e.setHoraSpeech(horaFeedback);
            e.setVendorName(vendor);
            e.setFecGestion(fecGestion);

            // =======================================================================
            // Bienvenida
            this.txtCliCod.setValue(e.getClienteCodigo());
            this.txtCliNom.setValue(e.getClienteNombre());
            this.txtUsuNom.setValue(e.getUsuarioNombre());
            this.txtProCod.setValue(e.getProductoCodigo());
            this.txtProNom.setValue(nomProd);
            //this.txtProNom.setValue(e.getProductoNombre());
            txtNomMon.getValue().toString();
            // Promociones
            this.txtProTip.setValue(e.getPromocionTipo());
            this.txtProDes.setValue(e.getPromocionDescripcion());
            this.txtCliNo2.setValue(e.getClienteNombre());
            this.txtProNo2.setValue(nomProd);
            // this.txtProNo2.setValue(e.getProductoNombre());
            this.txtProMon.setValue(e.getProductoMonto());
            // Datos del Cliente
            this.txtCliDoc.setValue(e.getClienteNumeroDocumento());
            this.txtCliNo3.setValue(e.getClienteNombre());
            // Consignacion Independiente
            this.txtCliNo4.setValue(e.getClienteNombre());
            // Consignacion Dependiente
            this.txtCliNo5.setValue(e.getClienteNombre());
            // Cierre de Venta
            this.txtCliNo6.setValue(e.getClienteNombre());
            this.txtValTEA.setValue(e.getValorTEA());
            this.txtValTEC.setValue(e.getValorTCEA());
            this.txtValMen.setValue(e.getValorMembresia());
            this.txtValDes.setValue(e.getValorDesgravamen());
            this.txtValEnv.setValue(e.getValorEnvioEstadoCuenta());
            this.txtValEn2.setValue(e.getValorEnvioEstadoCuenta());
            this.txtCliNo7.setValue(e.getClienteNombre());
            this.txtSopTel.setValue(e.getSoporteNumeroTelefono());
            this.txtSopNom.setValue(e.getSupervisorNombre());

            // Guardar en session
            ctx.getSessionScope().put("SpeechVenta", e);

        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }

    public String cbRegistrarVenta_click() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ADFContext ctx = ADFContext.getCurrent();

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
            try {
                s.setClienteFechaNacimiento(sdf.format(this.dtFecNaS.getValue())); // e.getClienteFechaNacimiento());
            } catch (Exception e) {
                s.setClienteFechaNacimiento(""); // e.getClienteFechaNacimiento());
            }

            s.setTelefonoLlamada(Util.nvl(this.txtTelLla,
                                          "")); // e.getTelefonoLlamada());
            s.setTelefonoCasa(Util.nvl(this.txtTelCas,
                                       "")); // e.getTelefonoCasa());
            s.setTelefonoCelular(Util.nvl(this.txtTelCel,
                                          "")); // e.getTelefonoCelular());
            s.setCorreoElectronico(Util.nvl(this.txtCorEle,
                                            "")); // e.getCorreoElectronico());
            s.setTelefonoReferencia(Util.nvl(this.txtTelRef,
                                             "")); // e.getTelefonoReferencia());
            s.setParentescoCodigo(Util.nvl(this.cboCliPar,
                                           "")); // e.getParentescoCodigo());
            s.setParentescoDescripcion(Util.getSelectedItem(this.cboCliPar)); // e.getParentescoDescripcion());
            s.setEstadoCivilCodigo(Util.nvl(this.cboEstCiv,
                                            "")); // e.getEstadoCivilCodigo());
            s.setEstadoCivilDescripcion(Util.getSelectedItem(this.cboEstCiv)); // e.getEstadoCivilDescripcion());
            s.setGeneroCodigo(Util.nvl(this.cboCliGen,
                                       "")); // e.getGeneroCodigo());
            s.setGeneroDescripcion(Util.getSelectedItem(this.cboCliGen)); // e.getGeneroDescripcion());
            s.setConyugeApellidoPaterno(Util.nvl(this.txtCoApPa,
                                                 "")); // e.getConyugeApellidoPaterno());
            s.setConyugeApellidoMaterno(Util.nvl(this.txtCoApMa,
                                                 "")); // e.getConyugeApellidoMaterno());
            s.setConyugeNombre1(Util.nvl(this.txtConNo1,
                                         "")); // e.getConyugeNombre1());
            s.setConyugeNombre2(Util.nvl(this.txtConNo2,
                                         "")); // e.getConyugeNombre2());
            try {
                s.setConyugeFechaNacimiento(sdf.format(this.dtCoFeNa.getValue()));
            } catch (Exception e) {
                s.setConyugeFechaNacimiento(""); // e.getConyugeFechaNacimiento());
            }

            try {
                s.setCentroLaboresFechaIngreso(sdf.format(this.dtCeLaFI.getValue()));
            } catch (Exception e) {
                s.setCentroLaboresFechaIngreso(""); // e.getConyugeFechaNacimiento());
            }


            s.setConyugeNumeroDocumento(Util.nvl(this.txtCoNuDo,
                                                 "")); // e.getConyugeNumeroDocumento());

            s.setConyugeGenerocodigo(Util.nvl(this.cboCoGene, ""));
            s.setConyugeGeneroDescripcion(Util.getSelectedItem(this.cboCoGene));
            s.setDireccion(Util.nvl(this.txtCliDir, ""));

            s.setDepartamento(Util.nvl(this.cboCliDep, ""));

            s.setProvincia(Util.nvl(this.cboCliPro, ""));

            s.setDistrito(Util.nvl(this.cboCliDis, ""));

            s.setReferencia(Util.nvl(this.txtDirRef,
                                     "")); // e.getReferencia());

            s.setCondicionLaboralCodigo(Util.nvl(this.cboConLab,
                                                 "")); // e.getCondicionLaboralCodigo());

            s.setCondicionLaboralDescripcion(Util.getSelectedItem(this.cboConLab)); // e.getCondicionLaboralDescripcion());

            s.setCentroLaboresNombre(Util.nvl(this.txtCeLaNo,
                                              "")); // e.getCentroLaboresNombre());

            s.setCentroLaboresRuc(Util.nvl(this.txtCeLaRU,
                                           "")); // e.getCentroLaboresRuc());

            s.setCentroLaboresDireccion(Util.nvl(this.txtCeLaDi,
                                                 "")); // e.getCentroLaboresDireccion());

            s.setCentroLaboresDepartamento(Util.nvl(this.cboCeLaDe, ""));

            s.setCentroLaboresProvincia(Util.nvl(this.cboCeLaPr, ""));

            s.setCentroLaboresDistrito(Util.nvl(this.cboCeLaDs, ""));

            s.setCentroLaboresReferencia(Util.nvl(this.txtCeLaRe,
                                                  "")); // e.getCentroLaboresReferencia());

            s.setCentroLaboresTelefono(Util.nvl(this.txtCeLaTe,
                                                "")); // e.getCentroLaboresTelefono());

            s.setCentroLaboresAnexo(Util.nvl(this.txtCeLaAn,
                                             "")); // e.getCentroLaboresAnexo());

            s.setCentroLaboresCargoCodigo(Util.nvl(this.cboCeLaCa,
                                                   "")); // e.getCentroLaboresCargoCodigo());

            s.setCentroLaboresCargoDescripcion(Util.getSelectedItem(this.cboCeLaCa)); // e.getCentroLaboresCargoDescripcion());

            s.setLugarEntregaIndependienteCodigo(Util.nvl(this.cboLuEnIn,
                                                          "")); // e.getLugarEntregaIndependienteCodigo());

            s.setLugarEntregaIndependienteDescripcion(Util.getSelectedItem(this.cboLuEnIn)); // e.getLugarEntregaIndependienteDescripcion());

            s.setLugarEntregaDependienteCodigo(Util.nvl(this.cboLuEnDe,
                                                        "")); // e.getLugarEntregaDependienteCodigo());

            s.setLugarEntregaDependienteDescripcion(Util.getSelectedItem(this.cboLuEnDe)); // e.getLugarEntregaDependienteDescripcion());

            s.setValorMembresia(txtMemAn.getValue().toString()); //s.setValorMembresia(Util.getSelectedItem(this.cboValMen));
            s.setValorDesgravamen(txtSegDes.getValue().toString()); //s.setValorDesgravamen(Util.getSelectedItem(this.cboValDes));
            s.setCicloFacturacion(Util.getSelectedItem(this.cboCicFac)); //s.setValorEnvioEstadoCuenta(e.getValorEnvioEstadoCuenta());

            String flaDis = "";

            if (checkFlaDis.getValue().toString().equalsIgnoreCase("true")) {
                flaDis = "1";
            } else {
                flaDis = "0";
            }

            s.setFlagDisposicionEfectivo(flaDis);

            String flaAut = "";

            if (checkFlaAut.getValue().toString().equalsIgnoreCase("true")) {
                flaAut = "1";
            } else {
                flaAut = "0";
            }

            s.setFlagAutorizacionLPDP(flaAut);

            s.setPrimerNombre(txtPrimNom.getValue().toString());
            s.setSegundoNombre(txtSegNom.getValue().toString());
            s.setApePaterno(txtApePat.getValue().toString());
            s.setApeMaterno(txtApeMat.getValue().toString());
            s.setTipoProd(txtNomProd.getValue().toString());
            s.setTea(txtValTEA.getValue().toString());

            p = mlc.mergeLeadSpeechVenta(c, s);

            borrarFecProg(s.getLeadID());

            checkFlaDis.setValue("");
            checkFlaAut.setValue("");

            // prueba();

            //METODO PARA GENERAR TXT DE SPEECH VENTA
               generarTxt(s.getSpeechID(), s.getClienteFechaNacimiento(),
                                            s.getTelefonoLlamada(), s.getTelefonoCasa(),
                                            s.getTelefonoCelular(), s.getCorreoElectronico(),
                                            s.getTelefonoReferencia(), s.getParentescoCodigo(),
                                            s.getParentescoDescripcion(), s.getEstadoCivilCodigo(),
                                            s.getEstadoCivilDescripcion(), s.getGeneroCodigo(),
                                            s.getGeneroDescripcion(), s.getConyugeApellidoPaterno(),
                                            s.getConyugeApellidoMaterno(), s.getConyugeNombre1(),
                                            s.getConyugeNombre2(), s.getConyugeFechaNacimiento(),
                                            "-", s.getConyugeNumeroDocumento(),
                                            s.getConyugeGenerocodigo(),
                                            s.getConyugeGeneroDescripcion(), s.getDireccion(),
                                            s.getDepartamento(), s.getProvincia(), s.getDistrito(),
                                            s.getReferencia(), s.getCondicionLaboralCodigo(),
                                            s.getCondicionLaboralDescripcion(),
                                            s.getCentroLaboresNombre(), s.getCentroLaboresRuc(),
                                            s.getCentroLaboresFechaIngreso(),
                                            s.getCentroLaboresDireccion(),
                                            s.getCentroLaboresDepartamento(),
                                            s.getCentroLaboresProvincia(),
                                            s.getCentroLaboresDistrito(),
                                            s.getCentroLaboresReferencia(),
                                            s.getCentroLaboresTelefono(), s.getCentroLaboresAnexo(),
                                            s.getCentroLaboresCargoCodigo(),
                                            s.getCentroLaboresCargoDescripcion(),
                                            s.getLugarEntregaIndependienteCodigo(),
                                            s.getLugarEntregaIndependienteDescripcion(),
                                            s.getLugarEntregaDependienteCodigo(),
                                            s.getLugarEntregaDependienteDescripcion(),
                                            s.getValorMembresia(), s.getValorDesgravamen(),
                                            s.getCicloFacturacion(), s.getFlagDisposicionEfectivo(),
                                            s.getFlagAutorizacionLPDP());

        } catch (Exception ex) {
            Util.Log(ex.getMessage());

        }
        return null;
    }

    public void prueba() {

        String SFTPHOST = "190.116.5.24";
        int SFTPPORT = 22;
        String SFTPUSER = "usftp311";
        String SFTPPASS = "uwy2FUmt";


        /*
        String SFTPHOST = "190.116.5.24";
        int SFTPPORT = 22;
        String SFTPUSER = "usftp311";
        String SFTPPASS = "uwy2FUmt";
              */

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println("preparing the host information for sftp.");


        try {


            JSch jsch = new JSch();
            /*

            try {
                    // jsch.addIdentity(".ssh/id_rsa");
                     jsch.setKnownHosts(".ssh/known_hosts");
                 } catch (JSchException e) {
                     e.printStackTrace();
                 }
            */


            session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setPassword(SFTPPASS);
            config.put("HashKnownHosts", "no");
            // config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");


            // config.put("IdentityFile", "~/.ssh/id_rsa");
            //  config.put("HostName", "190.116.5.24");


            // jsch.addIdentity(".ssh/id_rsa");
            /* try {
                           jsch.addIdentity(".ssh/id_rsa");
                           jsch.setKnownHosts(".ssh/known_hosts");
                       } catch (JSchException e) {
                           e.printStackTrace();
                       }
            */

            session.setTimeout(90000);
            Util.Log("1 Connecting to server...");


            session.setConfig(config);
            Util.Log("2 Connecting to server...");

            session.connect();
            Util.Log("3 Connecting to server...");


            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp)channel;


        } catch (Exception ex) {

            Util.Log("Exception found while transfer the response:" + ex);
        } finally {

            channelSftp.exit();
            Util.Log("sftp Channel exited.");
            channel.disconnect();
            Util.Log("Channel disconnected.");
            session.disconnect();
            Util.Log("Host Session disconnected.");
        }

    }

    public void registraVenta(ActionEvent actionEvent) {
        try {

            // Registrar Speech Venta
            this.cbRegistrarVenta_click();
            this.RegistrarFeedback();
            limpiarSpeechVenta();

            // Ocultar popup
            getPopupSpeech().hide();

            // Siguiente lead
            ADFContext ctx = ADFContext.getCurrent();

            if (ctx.getSessionScope().get("LEADID") != null) {

                String ejVentaDni =
                    ctx.getSessionScope().get("ejVentaDni") == null ? "" :
                    ctx.getSessionScope().get("ejVentaDni").toString();

                Util.redirec("SBP_SLS_BUSQUEDA_DNI_OPP?userId=" + ejVentaDni);

            } else {

                String idEjecutivoVenta =
                    ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
                    ctx.getSessionScope().get("IdEjecVenta").toString();
                String idLead =
                    ctx.getSessionScope().get("iDlEaD") == null ? "" :
                    ctx.getSessionScope().get("iDlEaD").toString();

                DosificacionEjecutivoBO.actualizarFlagUltimo2(idEjecutivoVenta);
                this.mostrarLead(idEjecutivoVenta);
                DosificacionEjecutivoBO.eliminarLead(idLead);

            }

        } catch (Exception e) {
            Util.Log(e.getMessage() + "ERROR");
        }
    }

    public void setCheckFlaDis(RichSelectBooleanCheckbox checkFlaDis) {
        this.checkFlaDis = checkFlaDis;
    }

    public RichSelectBooleanCheckbox getCheckFlaDis() {
        return checkFlaDis;
    }

    public void setCheckFlaAut(RichSelectBooleanCheckbox checkFlaAut) {
        this.checkFlaAut = checkFlaAut;
    }

    public RichSelectBooleanCheckbox getCheckFlaAut() {
        return checkFlaAut;
    }

    public void RegistrarFeedback() {
        ADFContext ctx = ADFContext.getCurrent();
        try {
            Feedback f = (Feedback)ctx.getSessionScope().get("Feedback");

            MergeLeadInvoke leadPayload = new MergeLeadInvoke();

            leadPayload.mergeLead(new Long(f.getIdLead()),
                    // Long.parseLong(idLead),
                    f.getNomFb(), // nombreFb,
                    f.getIdCanal(), // idCanal,
                    f.getCanal(), // canal,
                    f.getIdjefCanal(), // idJefCanal,
                    f.getJefCanal(), // jefCanal,
                    f.getIdSup(), // idSup,
                    f.getSup(), // sup,
                    f.getIdEjVenta(), // idEjVenta,
                    f.getEjVenta(), // ejVenta,
                    f.getTelefono(), // telefono,
                    f.getTipoGestion(), //tipoGestion,
                    f.getGrupoGestion(), //grupoGestion,
                    f.getDetGestion(), //detalleGestion,
                    //f.getAuxContactado(), //auxFeedbackContactado,
                    new BigDecimal(f.getAuxContactado().replaceAll(",", "")),
                    new BigDecimal(f.getAuxContactadoEfec().replaceAll(",",
                                                                       "")),
                    //auxFeedbackContactadoEfec,
                    new BigDecimal(f.getAuxVenta().replaceAll(",", "")),
                    //auxFeedbackVenta,
                    new BigDecimal(f.getAuxRevenueVenta().replaceAll(",", "")),
                    f.getNomTipGest(), f.getNomGrupGest(), //nomGrupGest,
                    f.getNomDetGest(), //nomDetGest,
                    f.getFecGestion(), //fecGestion,
                    f.getObs(), //obs,
                    f.getTelfAd(), //telfAd,
                    f.getDirAd(), //dirAd,
                    f.getEmailAd(), //emailAd,
                    f.getCodCpg(), //idCpg,
                    f.getNomCpg(), //cpgNom,
                    new BigDecimal(f.getMontoDesem().replaceAll(",", "")),
                    //montoDesem,
                    f.getFec2daLlam(), //fecha2daLlam,
                    f.getContacto(), //contacto,
                    f.getNumDoc(), //numDoc,
                    f.getTipDoc(), //tipDoc,
                    f.getProd(), //prod,
                    new Boolean(f.getFlagInbound()), //flagInbound,
                    new Boolean(f.getFlagOutbound()), //flagOutbound);
                    f.getHoraFeedback(), f.getAuxFeedbackAgendado());


        } catch (Exception ex) {
            Util.Log(ex.getMessage());
        }
    }


    public void setDtCoFeNa(RichInputDate dtCoFeNa) {
        this.dtCoFeNa = dtCoFeNa;
    }

    public RichInputDate getDtCoFeNa() {
        return dtCoFeNa;
    }

    public void setDtCeLaFI(RichInputDate dtCeLaFI) {
        this.dtCeLaFI = dtCeLaFI;
    }

    public RichInputDate getDtCeLaFI() {
        return dtCeLaFI;
    }

    public void setTxtTelLla(RichInputText txtTelLla) {
        this.txtTelLla = txtTelLla;
    }

    public RichInputText getTxtTelLla() {
        return txtTelLla;
    }

    public void setDtFecNaS(RichInputDate dtFecNaS) {
        this.dtFecNaS = dtFecNaS;
    }

    public RichInputDate getDtFecNaS() {
        return dtFecNaS;
    }


    public void setItT01(RichInputText itT01) {
        this.itT01 = itT01;
    }

    public RichInputText getItT01() {
        return itT01;
    }

    public void setItT02(RichInputText itT02) {
        this.itT02 = itT02;
    }

    public RichInputText getItT02() {
        return itT02;
    }

    public void setItT03(RichInputText itT03) {
        this.itT03 = itT03;
    }

    public RichInputText getItT03() {
        return itT03;
    }

    public void setItT04(RichInputText itT04) {
        this.itT04 = itT04;
    }

    public RichInputText getItT04() {
        return itT04;
    }

    public void setItT05(RichInputText itT05) {
        this.itT05 = itT05;
    }

    public RichInputText getItT05() {
        return itT05;
    }

    public void setItT06(RichInputText itT06) {
        this.itT06 = itT06;
    }

    public RichInputText getItT06() {
        return itT06;
    }

    public void setTxtTarjeta1(RichOutputLabel txtTarjeta1) {
        this.txtTarjeta1 = txtTarjeta1;
    }

    public RichOutputLabel getTxtTarjeta1() {
        return txtTarjeta1;
    }

    public void setTxtTarjeta2(RichOutputLabel txtTarjeta2) {
        this.txtTarjeta2 = txtTarjeta2;
    }

    public RichOutputLabel getTxtTarjeta2() {
        return txtTarjeta2;
    }

    public void setTxtTarjeta3(RichOutputLabel txtTarjeta3) {
        this.txtTarjeta3 = txtTarjeta3;
    }

    public RichOutputLabel getTxtTarjeta3() {
        return txtTarjeta3;
    }

    public void setTxtTarjeta4(RichOutputLabel txtTarjeta4) {
        this.txtTarjeta4 = txtTarjeta4;
    }

    public RichOutputLabel getTxtTarjeta4() {
        return txtTarjeta4;
    }

    public void setTxtTcTea1(RichOutputLabel txtTcTea1) {
        this.txtTcTea1 = txtTcTea1;
    }

    public RichOutputLabel getTxtTcTea1() {
        return txtTcTea1;
    }

    public void setTxtTcTea2(RichOutputLabel txtTcTea2) {
        this.txtTcTea2 = txtTcTea2;
    }

    public RichOutputLabel getTxtTcTea2() {
        return txtTcTea2;
    }

    public void setTxtTcTea3(RichOutputLabel txtTcTea3) {
        this.txtTcTea3 = txtTcTea3;
    }

    public RichOutputLabel getTxtTcTea3() {
        return txtTcTea3;
    }

    public void setTxtMontoProd(RichOutputLabel txtMontoProd) {
        this.txtMontoProd = txtMontoProd;
    }

    public RichOutputLabel getTxtMontoProd() {
        return txtMontoProd;
    }

    public void setTxtValEn2(RichOutputText txtValEn2) {
        this.txtValEn2 = txtValEn2;
    }

    public RichOutputText getTxtValEn2() {
        return txtValEn2;
    }

    public void setTxtValorTelf(RichInputText txtValorTelf) {
        this.txtValorTelf = txtValorTelf;
    }

    public RichInputText getTxtValorTelf() {
        return txtValorTelf;
    }

    public void setCboCliDep(RichSelectOneChoice cboCliDep) {
        this.cboCliDep = cboCliDep;
    }

    public RichSelectOneChoice getCboCliDep() {
        return cboCliDep;
    }

    public void setCboCliPro(RichSelectOneChoice cboCliPro) {
        this.cboCliPro = cboCliPro;
    }

    public RichSelectOneChoice getCboCliPro() {
        return cboCliPro;
    }

    public void setCboCliDis(RichSelectOneChoice cboCliDis) {
        this.cboCliDis = cboCliDis;
    }

    public RichSelectOneChoice getCboCliDis() {
        return cboCliDis;
    }

    public void setCboCeLaDe(RichSelectOneChoice cboCeLaDe) {
        this.cboCeLaDe = cboCeLaDe;
    }

    public RichSelectOneChoice getCboCeLaDe() {
        return cboCeLaDe;
    }

    public void setCboCeLaPr(RichSelectOneChoice cboCeLaPr) {
        this.cboCeLaPr = cboCeLaPr;
    }

    public RichSelectOneChoice getCboCeLaPr() {
        return cboCeLaPr;
    }

    public void setCboCeLaDs(RichSelectOneChoice cboCeLaDs) {
        this.cboCeLaDs = cboCeLaDs;
    }

    public RichSelectOneChoice getCboCeLaDs() {
        return cboCeLaDs;
    }

    public void setTxtCodDeptSpeech(RichInputText txtCodDeptSpeech) {
        this.txtCodDeptSpeech = txtCodDeptSpeech;
    }

    public RichInputText getTxtCodDeptSpeech() {
        return txtCodDeptSpeech;
    }

    public void setTxtCodProvSpeech(RichInputText txtCodProvSpeech) {
        this.txtCodProvSpeech = txtCodProvSpeech;
    }

    public RichInputText getTxtCodProvSpeech() {
        return txtCodProvSpeech;
    }

    public void setTxtCodDistSpeech(RichInputText txtCodDistSpeech) {
        this.txtCodDistSpeech = txtCodDistSpeech;
    }

    public RichInputText getTxtCodDistSpeech() {
        return txtCodDistSpeech;
    }

    public void setDeptPrueba(RichSelectOneChoice deptPrueba) {
        this.deptPrueba = deptPrueba;
    }

    public RichSelectOneChoice getDeptPrueba() {
        return deptPrueba;
    }

    public String createInsertSpeech() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert4");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsertSpeech2() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CreateInsert5");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setTxtNomMon(RichOutputLabel txtNomMon) {
        this.txtNomMon = txtNomMon;
    }

    public RichOutputLabel getTxtNomMon() {
        return txtNomMon;
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
                ctx.getSessionScope().get("contactoFileSpeech") == null ? "" :
                ctx.getSessionScope().get("contactoFileSpeech").toString();

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

    public void obtenerNomBanco1(ActionEvent actionEvent) {

            txtDescProm.setValue("");

             ADFContext ctx = ADFContext.getCurrent();

             String nomProd1 = txtBanco1Link.getText();

             txtProdSel.setValue(nomProd1);

             try {
                 Util.Log("inicio sftp");
                 mostrarSpeech2(nomProd1);
                 Util.Log("fin sftp");
             } catch (JSchException e) {
                 e.printStackTrace(System.out);
             } catch (SftpException e) {
                 e.printStackTrace(System.out);
             }
             ctx.getSessionScope().put("nomProd", nomProd1);
    }

    public Producto listXProd(String nombreProd) {

        ADFContext ctx = ADFContext.getCurrent();

        Producto listProd = new Producto();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String idProd = "";
        String nomProd = "";
        String tcea = "";
        String comision = "";
        String membresia = "";
        String seguro = "";
        String promocion = "";
        String msjBienv = "";


        try {
            String query =
                "select id_prod ID_PROD, nom_prod NOM_PROD, tcea TCEA, comision COMISION, membresia MEMBRESIA, seguro SEGURO, promoción PROMOCION, msj_bienv MSJ_BIENV from sbp_sls_maestro_prod where nom_prod = '" +
                nombreProd + "'";

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                Producto prod = new Producto();

                while (rs.next()) {

                    idProd = rs.getString("ID_PROD");
                    nomProd = rs.getString("NOM_PROD");
                    tcea = rs.getString("TCEA");
                    comision = rs.getString("COMISION");
                    membresia = rs.getString("MEMBRESIA");
                    seguro = rs.getString("SEGURO");
                    promocion = rs.getString("PROMOCION");
                    msjBienv = rs.getString("MSJ_BIENV");


                    ctx.getSessionScope().put("idProd", idProd);
                    ctx.getSessionScope().put("nomProd", nomProd);
                    ctx.getSessionScope().put("tcea", tcea);
                    ctx.getSessionScope().put("comision", comision);
                    ctx.getSessionScope().put("membresia", membresia);
                    ctx.getSessionScope().put("seguro", seguro);
                    ctx.getSessionScope().put("promocion", promocion);
                    ctx.getSessionScope().put("msjBienv", msjBienv);

                    prod.setIdProd(idProd);
                    prod.setNomProd(nomProd);
                    prod.setTcea(tcea);
                    prod.setComision(comision);
                    prod.setMembresia(membresia);
                    prod.setSeguro(seguro);
                    prod.setPromocion(promocion);
                    prod.setMensajeBienv(msjBienv);
                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return listProd;
    }

    public void setTxtNomProd(RichOutputLabel txtNomProd) {
        this.txtNomProd = txtNomProd;
    }

    public RichOutputLabel getTxtNomProd() {
        return txtNomProd;
    }

    public void setTxtPromocion(RichOutputLabel txtPromocion) {
        this.txtPromocion = txtPromocion;
    }

    public RichOutputLabel getTxtPromocion() {
        return txtPromocion;
    }

    public void setTxtBanco1Link(RichCommandLink txtBanco1Link) {
        this.txtBanco1Link = txtBanco1Link;
    }

    public RichCommandLink getTxtBanco1Link() {
        return txtBanco1Link;
    }

    public void setTxtBanco2Link(RichCommandLink txtBanco2Link) {
        this.txtBanco2Link = txtBanco2Link;
    }

    public RichCommandLink getTxtBanco2Link() {
        return txtBanco2Link;
    }

    public void setTxtBanco3Link(RichCommandLink txtBanco3Link) {
        this.txtBanco3Link = txtBanco3Link;
    }

    public RichCommandLink getTxtBanco3Link() {
        return txtBanco3Link;
    }

    public void obtenerNomBanco2(ActionEvent actionEvent) {

        txtDescProm.setValue("");

            ADFContext ctx = ADFContext.getCurrent();

            String nomProd2 = txtBanco2Link.getText();

            txtProdSel.setValue(nomProd2);


            try {
                mostrarSpeech2(nomProd2);
            } catch (JSchException e) {
                e.printStackTrace(System.out);
            } catch (SftpException e) {
                e.printStackTrace(System.out);
            }
            ctx.getSessionScope().put("nomProd", nomProd2);
    }

    public void obtenerNomBanco3(ActionEvent actionEvent) {

            txtDescProm.setValue("");

             ADFContext ctx = ADFContext.getCurrent();

             String nomProd3 = txtBanco3Link.getText();

             txtProdSel.setValue(nomProd3);


             try {
                 mostrarSpeech2(nomProd3);
             } catch (JSchException e) {
                 e.printStackTrace(System.out);
             } catch (SftpException e) {
                 e.printStackTrace(System.out);
             }
             ctx.getSessionScope().put("nomProd", nomProd3);
    }

    public String promPrincipal(String nombreProd) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String promocion = "";


        try {
            String query =
                "select promoción PROMOCION from sbp_sls_maestro_prod where nom_prod = '" +
                nombreProd + "'";

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    promocion = rs.getString("PROMOCION");

                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return promocion;
    }

    public void setTxtMemAn(RichInputText txtMemAn) {
        this.txtMemAn = txtMemAn;
    }

    public RichInputText getTxtMemAn() {
        return txtMemAn;
    }

    public void setTxtSegDes(RichInputText txtSegDes) {
        this.txtSegDes = txtSegDes;
    }

    public RichInputText getTxtSegDes() {
        return txtSegDes;
    }

    public void setTblTarjetaUbigeo(RichTable tblTarjetaUbigeo) {
        this.tblTarjetaUbigeo = tblTarjetaUbigeo;
    }

    public RichTable getTblTarjetaUbigeo() {
        return tblTarjetaUbigeo;
    }

    public void setTxtProdSel(RichInputText txtProdSel) {
        this.txtProdSel = txtProdSel;
    }

    public RichInputText getTxtProdSel() {
        return txtProdSel;
    }

    public void setPopupCobertura(RichPopup popupCobertura) {
        this.popupCobertura = popupCobertura;
    }

    public RichPopup getPopupCobertura() {
        return popupCobertura;
    }

    public void setTxtDescProm(RichInputText txtDescProm) {
        this.txtDescProm = txtDescProm;
    }

    public RichInputText getTxtDescProm() {
        return txtDescProm;
    }

    public void setPopup2daLlamada(RichPopup popup2daLlamada) {
        this.popup2daLlamada = popup2daLlamada;
    }

    public RichPopup getPopup2daLlamada() {
        return popup2daLlamada;
    }

    public void setTxtMsjBienv(RichOutputText txtMsjBienv) {
        this.txtMsjBienv = txtMsjBienv;
    }

    public RichOutputText getTxtMsjBienv() {
        return txtMsjBienv;
    }

    public void setPnlMsj(RichPanelSplitter pnlMsj) {
        this.pnlMsj = pnlMsj;
    }

    public RichPanelSplitter getPnlMsj() {
        return pnlMsj;
    }

    public void setPnlMsjOro(RichPanelSplitter pnlMsjOro) {
        this.pnlMsjOro = pnlMsjOro;
    }

    public RichPanelSplitter getPnlMsjOro() {
        return pnlMsjOro;
    }

    public void setTxtProm2(RichInputText txtProm2) {
        this.txtProm2 = txtProm2;
    }

    public RichInputText getTxtProm2() {
        return txtProm2;
    }

    public void setTxtProm3(RichInputText txtProm3) {
        this.txtProm3 = txtProm3;
    }

    public RichInputText getTxtProm3() {
        return txtProm3;
    }

    public void setTxtPromSpeech2(RichOutputLabel txtPromSpeech2) {
        this.txtPromSpeech2 = txtPromSpeech2;
    }

    public RichOutputLabel getTxtPromSpeech2() {
        return txtPromSpeech2;
    }

    public void setTxtPromSpeech3(RichOutputLabel txtPromSpeech3) {
        this.txtPromSpeech3 = txtPromSpeech3;
    }

    public RichOutputLabel getTxtPromSpeech3() {
        return txtPromSpeech3;
    }

    public void setTxtApeMat(RichInputText txtApeMat) {
        this.txtApeMat = txtApeMat;
    }

    public RichInputText getTxtApeMat() {
        return txtApeMat;
    }

    public void setTxtApePat(RichInputText txtApePat) {
        this.txtApePat = txtApePat;
    }

    public RichInputText getTxtApePat() {
        return txtApePat;
    }

    public void setTxtSegNom(RichInputText txtSegNom) {
        this.txtSegNom = txtSegNom;
    }

    public RichInputText getTxtSegNom() {
        return txtSegNom;
    }

    public void setTxtPrimNom(RichInputText txtPrimNom) {
        this.txtPrimNom = txtPrimNom;
    }

    public RichInputText getTxtPrimNom() {
        return txtPrimNom;
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


    public String validaFb(String codTipGest, String codGrupGest,
                           String codDetGest) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String estado = "";


        try {
            String query =
                "select FLAG_ELIM_LEAD FLAG_ELIM_LEAD from SBP_SLS_GEST_FEEDBACK where COD_TIP_GEST = " +
                codTipGest + " and COD_GRUP_GEST = " + codGrupGest +
                " and COD_DET_GEST = " + codDetGest;

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    estado = rs.getString("FLAG_ELIM_LEAD");

                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return estado;
    }
    
    public InputStream obtenerTxt2(String nomBin) throws JSchException,
                                                         SftpException {

        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");

        JSch jsch = new JSch();
        Session session = null;

       session = jsch.getSession("ccmg-tVW", "sftp.us2.cloud.oracle.com", 22);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("Evol2018");
        
       /*  session = jsch.getSession("root", "191.98.147.250", 22);
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
            sftpChannel.get("/upload/ReadTxt/" + nomBin + "_2.txt");
        System.out.println("3");
        return stream;
    }

    public String speechProd2(String nomBin) throws JSchException,
                                                    SftpException {

        BufferedReader br = null;
        InputStream is = obtenerTxt2(nomBin);
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
    
    public void mostrarSpeech2(String nombreBin) throws JSchException, SftpException {
        
        Util.Log("nombreBin -- > " + nombreBin);

        String textoSpeech1 = speechProd2(nombreBin);

        txtDescProm.setValue(textoSpeech1);
    }

    public void setTxtConsentimiento(RichOutputLabel txtConsentimiento) {
        this.txtConsentimiento = txtConsentimiento;
    }

    public RichOutputLabel getTxtConsentimiento() {
        return txtConsentimiento;
    }

    public void setTxtPromNueva(RichOutputLabel txtPromNueva) {
        this.txtPromNueva = txtPromNueva;
    }

    public RichOutputLabel getTxtPromNueva() {
        return txtPromNueva;
    }
    
    public String tipUser(String idEjVenta) {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String tipUsuario = "";


        try {
            String query =
                "select tipo_usuario TIPO_USUARIO from sbp_sls_list_ev_mant where id_ej_venta = '" +
                idEjVenta + "'";

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    tipUsuario = rs.getString("TIPO_USUARIO");

                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return tipUsuario;
    }
    
    
    public String tipConexion(String idEjVenta) {

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String tipCon = "";


        try {
            String query =
                "select tipo_con TIPO_CON from sbp_sls_list_ev_mant where id_ej_venta = '" +
                idEjVenta + "'";

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    tipCon = rs.getString("TIPO_CON");

                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return tipCon;
    }
    
    
    public String encriptar(String idEjVenta) {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String encriptar = "";


        try {
            String query =
                "select encriptar ENCRIPTAR from sbp_sls_list_ev_mant where id_ej_venta = '" +
                idEjVenta + "'";

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {

                while (rs.next()) {

                    encriptar = rs.getString("ENCRIPTAR");

                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return encriptar;
    }
    
    public void bloquearTelefonos(){
            if(txtTelf01.getText().equals(null) || txtTelf01.getText().equals("")){
                txtTelf01.setDisabled(true);
                txtTelfNew01.setDisabled(true);
            }
            if(txtTelf02.getText().equals(null) || txtTelf02.getText().equals("")){
                txtTelf02.setDisabled(true);
                txtTelfNew02.setDisabled(true);
            }
            if(txtTelf03.getText().equals(null) || txtTelf03.getText().equals("")){
                txtTelf03.setDisabled(true);
                txtTelfNew03.setDisabled(true);
            }
            if(txtCel01.getText().equals(null) || txtCel01.getText().equals("")){
                txtCel01.setDisabled(true);
                txtCel1TipUser.setDisabled(true);
            }
            if(txtCel02.getText().equals(null) || txtCel02.getText().equals("")){
                txtCel02.setDisabled(true);
                txtCel2TipUser.setDisabled(true);
            }
            if(txtCel03.getText().equals(null) || txtCel03.getText().equals("")){
                txtCel03.setDisabled(true);
                txtCel3TipUser.setDisabled(true);
            }
        }

}


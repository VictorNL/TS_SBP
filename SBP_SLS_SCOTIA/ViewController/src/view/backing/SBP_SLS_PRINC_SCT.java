package view.backing;

import WsEjRep.ServiceBiPublisher;

import client.MergeEntityInvoke;

import dao.Campania;

import dao.Canal;

import java.io.IOException;

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
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import org.xml.sax.SAXException;

import view.Util;

public class SBP_SLS_PRINC_SCT {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichMessages m1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText it4;
    private RichInputText it5;
    private RichInputComboboxListOfValues productoBaseId;
    private RichInputText it6;
    private RichInputText it7;
    private RichInputText it8;
    private RichSelectBooleanCheckbox sbc1;
    private RichInputText txtCodCpg;
    private RichInputText txtCpgNom;
    private RichInputText txtFecIni;
    private RichInputText txtFecFin;
    private RichInputText txtAsig;
    private RichInputText txtProd;
    private RichInputText txtCantOpp;
    private RichInputText txtCantTotal;
    private RichInputText txtPeriod;
    private RichSelectBooleanCheckbox txtActiva;
    private RichInputText txtCpgId;
    private RichInputText txtObjectName;
	 private RichInputText txtActivo;
    private RichTable tblCanal;
    private RichInputText txtCantLead;
    private RichOutputText txtCantidad;

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

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
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

    public void setProductoBaseId(RichInputComboboxListOfValues productoBaseId) {
        this.productoBaseId = productoBaseId;
    }

    public RichInputComboboxListOfValues getProductoBaseId() {
        return productoBaseId;
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

    public void setSbc1(RichSelectBooleanCheckbox sbc1) {
        this.sbc1 = sbc1;
    }

    public RichSelectBooleanCheckbox getSbc1() {
        return sbc1;
    }

    public void setTxtCodCpg(RichInputText txtCodCpg) {
        this.txtCodCpg = txtCodCpg;
    }

    public RichInputText getTxtCodCpg() {
        return txtCodCpg;
    }

    public void setTxtCpgNom(RichInputText txtCpgNom) {
        this.txtCpgNom = txtCpgNom;
    }

    public RichInputText getTxtCpgNom() {
        return txtCpgNom;
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

    public void setTxtAsig(RichInputText txtAsig) {
        this.txtAsig = txtAsig;
    }

    public RichInputText getTxtAsig() {
        return txtAsig;
    }

    public void setTxtProd(RichInputText txtProd) {
        this.txtProd = txtProd;
    }

    public RichInputText getTxtProd() {
        return txtProd;
    }

    public void setTxtCantOpp(RichInputText txtCantOpp) {
        this.txtCantOpp = txtCantOpp;
    }

    public RichInputText getTxtCantOpp() {
        return txtCantOpp;
    }

    public void setTxtCantTotal(RichInputText txtCantTotal) {
        this.txtCantTotal = txtCantTotal;
    }

    public RichInputText getTxtCantTotal() {
        return txtCantTotal;
    }

    public void setTxtPeriod(RichInputText txtPeriod) {
        this.txtPeriod = txtPeriod;
    }

    public RichInputText getTxtPeriod() {
        return txtPeriod;
    }

    public void setTxtActiva(RichSelectBooleanCheckbox txtActiva) {
        this.txtActiva = txtActiva;
    }

    public RichSelectBooleanCheckbox getTxtActiva() {
        return txtActiva;
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
        
        Util.Log("inicio");
        //obtener parámetro
        txtCpgId.setValue((String) resolveExpression("#{viewScope.cpgId2}"));      
        
        String cpgId = txtCpgId.getValue().toString();
        
        infCpg(cpgId);
        //mostrarDatosCpg(cpgId);
        Util.Log("fin");
    }
    
    public void buscarCanales(String cpgId){
        
        Util.Log("inicio");
        
      /*  ADFContext ctx = ADFContext.getCurrent();      
        
        String cpgId =
            ctx.getSessionScope().get("CampaignId") == null ? "" : ctx.getSessionScope().get("CampaignId").toString();
       */
        Row rw = null;

        ServiceBiPublisher can = new ServiceBiPublisher();

        List<Canal> listCanal = new ArrayList<Canal>();

        listCanal = can.obtenerCanalesLead(cpgId);

        Util.Log(listCanal.size() + "tamaño");


        CollectionModel _tablaCanal= (CollectionModel)tblCanal.getValue();
        JUCtrlHierBinding _adfTableCanalBinding =
            (JUCtrlHierBinding)_tablaCanal.getWrappedData(); 
        DCIteratorBinding itCanal =
            _adfTableCanalBinding.getDCIteratorBinding();

        int b = 0;
        b = itCanal.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (Canal listCan : listCanal) {

            rw = itCanal.getNavigatableRowIterator().createRow();
            itCanal.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdCanal", listCan.getCanalId());
            rw.setAttribute("NomCanal", listCan.getCanal());
            rw.setAttribute("Cantidad", Integer.parseInt(listCan.getCant()));
           //txtCantidad.setValue(Util.formatearMonto(listCan.getCant()));
            rw.setAttribute("PartyNumber", listCan.getPartyNumber());

        }
        
        
        Util.Log("fin");
    }
    
    public void mostrarDatosCpg(String cpgId) {
        
        Util.Log("inicio");

        ADFContext ctx = ADFContext.getCurrent();
     
        String campaignId = "";

        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> listCpg = new ArrayList<Campania>();

        listCpg = cpg.obtenerCpgSct2(cpgId);

        for (Campania listCamp : listCpg) {
           
            txtCodCpg.setValue(listCamp.getCpgId());
            campaignId = listCamp.getCpgId();
            txtCpgNom.setValue(listCamp.getCpgNom());
            txtProd.setValue(listCamp.getProd());
            txtFecIni.setValue(listCamp.getFecIni());
            txtFecFin.setValue(listCamp.getFecFin());
            txtPeriod.setValue(listCamp.getPeriodoCpg());
            txtActiva.setValue(listCamp.getActivo());
            txtActivo.setValue(listCamp.getActivo());								 
        }
        
        ctx.getSessionScope().put("CampaignId", campaignId);
        
        Util.Log("fin");
        
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void infCpg(String cpgId){
        
        Util.Log("inicio");
        
        ADFContext ctx = ADFContext.getCurrent();        

        double porc = 0.0;
        
        String idCpg = "";
        String nomCpg = "";
        String porcAsig = "";
        String prod = "";
        String fecIni = "";
        String fecFin = "";
        String cantOf = "";
        String perCpg = "";
        String checkBox = "";
        String cantLead = "";

        
        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> listCpg = new ArrayList<Campania>();

        listCpg = cpg.obtenerCpgSct(cpgId);

        if (listCpg.size() != 0) {
            
        for (Campania listCamp : listCpg) {

            porc = Math.floor((Double.parseDouble(listCamp.getCantLead())/Double.parseDouble(listCamp.getCantOf())) * 100.0) / 100.0;
            Util.Log(porc + "PORCENTAJE");
            String porcAsignado = String.valueOf(porc);
            
            txtCodCpg.setValue(listCamp.getCpgId());
            txtCpgNom.setValue(listCamp.getCpgNom());
            txtAsig.setValue(porcAsignado);
            txtProd.setValue(listCamp.getProd());
            txtFecIni.setValue(listCamp.getFecIni());
            txtFecFin.setValue(listCamp.getFecFin());
            txtCantTotal.setValue(/*Util.formatearMonto(*/listCamp.getCantOf());
            txtPeriod.setValue(listCamp.getPeriodoCpg());
            txtCantLead.setValue(/*Util.formatearMonto(*/listCamp.getCantLead());
            txtActiva.setValue(listCamp.getActivo());
            txtActivo.setValue(listCamp.getActivo());								 
            
                     idCpg = listCamp.getCpgId();
                     nomCpg = listCamp.getCpgNom();
                     porcAsig = porcAsignado;
                     prod = listCamp.getProd();
                     fecIni = listCamp.getFecIni();
                     fecFin = listCamp.getFecFin();
                     cantOf = listCamp.getCantOf();
                     perCpg = listCamp.getPeriodoCpg();
                     checkBox = listCamp.getActivo();
                     cantLead = listCamp.getCantLead();
        }

        ctx.getSessionScope().put("campId", idCpg);
        ctx.getSessionScope().put("nombCpg", nomCpg);
        ctx.getSessionScope().put("%Asig", porcAsig);
        ctx.getSessionScope().put("prod", prod);
        ctx.getSessionScope().put("fecIni", fecIni);
        ctx.getSessionScope().put("fecFin", fecFin);
        ctx.getSessionScope().put("cantOf", cantOf);
        ctx.getSessionScope().put("perCpg", perCpg);
        ctx.getSessionScope().put("checkBox", checkBox);
        ctx.getSessionScope().put("cantLead", cantLead);
        
        buscarCanales(cpgId);
        
        actualizarCampania();
        
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
        
        Util.Log("fin");
    
    }
    
    public void actualizarCampania(){
        
        Util.Log("inicio");
        
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
      String cantLead =
          ctx.getSessionScope().get("cantLead") == null ? "" : ctx.getSessionScope().get("cantLead").toString();
      
      

      MergeEntityInvoke mergeEntity = new MergeEntityInvoke();        

      if(nomCpg == null || porcAsig == null || fecIni == null ||
       prod == null || fecFin == null || cantOf == null ||
      txtObjectName.getValue() == null){
      FacesContext fctx = FacesContext.getCurrentInstance();
      fctx.addMessage("",
              new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen campos incompletos",""));

      }

      String resultado;

      double porcentaje = 0.0;

      Util.Log(porcAsig + "porc asig 123");
      porcentaje = Math.floor((Double.parseDouble(porcAsig)/100))*10.0/10.0;

      Util.Log(txtFecIni.getValue() + "");

      String valor = "";

      Util.Log(txtActiva.getValue().toString());


      if (txtActiva.getValue().toString().equalsIgnoreCase("true")) {
          valor = "true";
      } else {
          valor = "false";
      }


      try {

          resultado =
          mergeEntity.mergeEntity(idCpg, nomCpg, prod, porcAsig, fecIni,
                                  fecFin, cantOf, perCpg, cantLead, txtObjectName.getValue().toString());
          
          double porcent = Double.parseDouble(porcAsig) * 100;
          
       String porcentajeFinal =  String.valueOf(porcent);
          
          txtCodCpg.setValue(idCpg);
          txtCpgNom.setValue(nomCpg);
          txtAsig.setValue(porcentajeFinal + "%");
          txtProd.setValue(prod);
          txtFecIni.setValue(fecIni);
          txtFecFin.setValue(fecFin);
          txtCantTotal.setValue(cantOf);
          txtPeriod.setValue(perCpg);
          txtCantLead.setValue(cantLead);
          txtActiva.setValue(valor);
          
                 
          
          
      } catch (ParserConfigurationException e) {
      } catch (SAXException e) {
      } catch (IOException e) {
      }

      Util.Log(valor);
      
        Util.Log("fin");
    }
    
    public void procesar(ActionEvent actionEvent) {
        
        infCpg(txtCpgId.getValue().toString());
    }

    public void setTxtObjectName(RichInputText txtObjectName) {
        this.txtObjectName = txtObjectName;
    }

    public RichInputText getTxtObjectName() {
        return txtObjectName;
    }
	 public void setTxtActivo(RichInputText txtActivo) {
        this.txtActivo = txtActivo;
    }

    public RichInputText getTxtActivo() {
        return txtActivo;
    }

    public void setTblCanal(RichTable tblCanal) {
        this.tblCanal = tblCanal;
    }

    public RichTable getTblCanal() {
        return tblCanal;
    }

    public void setTxtCantLead(RichInputText txtCantLead) {
        this.txtCantLead = txtCantLead;
    }

    public RichInputText getTxtCantLead() {
        return txtCantLead;
    }

    public void setTxtCantidad(RichOutputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public RichOutputText getTxtCantidad() {
        return txtCantidad;
    }
}

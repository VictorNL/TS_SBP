package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Campania;
import dao.Canal;
import dao.EjVenta;
import dao.LoginUser;

import java.util.ArrayList;
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
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_LIST_EJV_X_SUP {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichOutputLabel ol1;
    private RichInputText it1;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl6;
    private RichTable t1;
    private RichMessages m1;
    private RichTable tblEjVenta;
    private RichInputText txtSup;
    private RichInputText txtCpgId;
    private RichSelectOneChoice cboSexo;
    private RichSelectOneChoice cboEstadoCivil;
    private RichSelectOneChoice cboFlagClubSueldo;
    private RichInputText txtEdadMin;
    private RichInputText txtEdadMax;

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

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
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

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
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

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
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

    public void setTblEjVenta(RichTable tblEjVenta) {
        this.tblEjVenta = tblEjVenta;
    }

    public RichTable getTblEjVenta() {
        return tblEjVenta;
    }

    public void setTxtSup(RichInputText txtSup) {
        this.txtSup = txtSup;
    }

    public RichInputText getTxtSup() {
        return txtSup;
    }


    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public String obtenerUser(String userName){
        
        ADFContext ctx = ADFContext.getCurrent();
        
        ServiceBiPublisher user = new ServiceBiPublisher();
        
        String partyId = "";
        List<LoginUser> listNomUsu = new ArrayList<LoginUser>();
           listNomUsu = user.obtenerLoginUser(userName);
           
           for(LoginUser listaNomUsu : listNomUsu){
              partyId = listaNomUsu.getPartyId();
           }
           
           Util.Log(partyId + "PARTY ID");
        ctx.getSessionScope().put("supId", partyId);
        
        return partyId;
    }
    
    


    public void buscarEjVenta(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String supId =
            ctx.getSessionScope().get("uSeRiD") == null ? "" : ctx.getSessionScope().get("uSeRiD").toString();

        String cpgId =
            ctx.getSessionScope().get("cpanaId") == null ? "" : ctx.getSessionScope().get("cpanaId").toString();

        String nomCpg = ctx.getSessionScope().get("cpanaNom") == null ? "" : ctx.getSessionScope().get("cpanaNom").toString();
        
        Row rw = null;

        ServiceBiPublisher sup = new ServiceBiPublisher();

        List<EjVenta> listEjVenta = new ArrayList<EjVenta>();
        
        List<Canal> listEjKardex = new ArrayList<Canal>();

        listEjVenta = sup.obtenerEjVentaXSup(cpgId, supId);

        Util.Log(listEjVenta.size() + "tamaño");


        CollectionModel _tablaEjVenta = (CollectionModel)tblEjVenta.getValue();
        JUCtrlHierBinding _adfTableEjVentaBinding =
            (JUCtrlHierBinding)_tablaEjVenta.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itEjVenta =
            _adfTableEjVentaBinding.getDCIteratorBinding();

        int b = 0;
        b = itEjVenta.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itEjVenta.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (EjVenta listEjec : listEjVenta) {

            rw = itEjVenta.getNavigatableRowIterator().createRow();
            itEjVenta.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("IdSup", listEjec.getIdSup());
            rw.setAttribute("NomSup", listEjec.getSup());
            rw.setAttribute("IdEjVenta", listEjec.getIdEjVenta());
            rw.setAttribute("NomEjVenta", listEjec.getEjVenta());
            rw.setAttribute("Cantidad", Integer.parseInt(listEjec.getCantidadLead()));

        }
    }
    
    
    public void obtenerDatos(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idSup = "";
        String idEjVenta = "";
        String cantidad = "";
        String sexo = Util.getSelectedItem(this.cboSexo);
        String estCivil = Util.getSelectedItem(this.cboEstadoCivil);
        String flagClubSueldo = Util.getSelectedItem(this.cboFlagClubSueldo);
        String edadMin = txtEdadMin.getValue().toString();
        String edadMax = txtEdadMax.getValue().toString();
        
                
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsListEvXSupView1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        
        idSup = rowSelected.getAttribute("IdSup").toString();
        idEjVenta = rowSelected.getAttribute("IdEjVenta").toString();
        cantidad = rowSelected.getAttribute("Cantidad").toString();
         
       
        ctx.getSessionScope().put("SperId", idSup);
        ctx.getSessionScope().put("EjVentaId", idEjVenta);
        ctx.getSessionScope().put("Cantidad", cantidad);
        ctx.getSessionScope().put("Sexo", sexo);
        ctx.getSessionScope().put("estCivil", estCivil);
        ctx.getSessionScope().put("flagClubSueldo", flagClubSueldo);
        
        ctx.getSessionScope().put("edadMin", edadMin);
        ctx.getSessionScope().put("edadMax", edadMax);
        
        Util.Log(cantidad + "CANT CAPTURADA");
        Util.Log(idSup + "IDSUPER CAPTURADO");
        Util.Log(idEjVenta + "IDEJVENTA CAPTURADO");
        Util.Log(sexo + "Sexo");
        Util.Log(estCivil + "estCivil");
        
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
        ctx.getSessionScope().put("JefECaNAl", jefeCanal);
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
        
        //obtener parámetro
        txtSup.setValue((String) resolveExpression("#{viewScope.userId2}"));     
        
        txtCpgId.setValue((String) resolveExpression("#{viewScope.cpgId2}"));
        
        String nomUser = txtSup.getValue().toString();
        
        String cpgId = txtCpgId.getValue().toString();
        
        Util.Log(txtSup.getValue().toString() + "NOM USER");
        
        ctx.getSessionScope().put("cpanaId", cpgId);
        
        String nomCpg = nomCpg(cpgId);
        
        ctx.getSessionScope().put("cpanaNom", nomCpg);
      
        mostrarDatos(nomUser); 
    
    }
    
    
    public void mostrarDatos(String IDSupervisor) {
          
          Util.LogTime("Inicio");
          
          String canalId = "";
          
          String jefeCanal = "";
          
          ADFContext ctx = ADFContext.getCurrent();
          
          String cpgId = ctx.getSessionScope().get("cpanaId") == null ? "" : ctx.getSessionScope().get("cpanaId").toString();
          
          String nomCpg = ctx.getSessionScope().get("cpanaNom") == null ? "" : ctx.getSessionScope().get("cpanaNom").toString();

          Row rw = null;

          ServiceBiPublisher sup = new ServiceBiPublisher();

          List<EjVenta> listEjVenta = new ArrayList<EjVenta>();
          
          List<Canal> listEjVentaCanal = new ArrayList<Canal>();

          listEjVenta = sup.obtenerEjVentaXSup(cpgId, IDSupervisor);

          Util.Log(listEjVenta.size() + "tamaño");

          CollectionModel _tablaEjVenta = (CollectionModel)tblEjVenta.getValue();
          JUCtrlHierBinding _adfTableEjVentaBinding = (JUCtrlHierBinding)_tablaEjVenta.getWrappedData(); //devuelve el table binding
          DCIteratorBinding itEjVenta = _adfTableEjVentaBinding.getDCIteratorBinding();

          int b = 0;
          b = itEjVenta.getRowSetIterator().getRowCount();

          for (int i = 0; i < b; i++) {
              itEjVenta.getRowSetIterator().getRowAtRangeIndex(0).remove();
          }

          for (EjVenta listEjec : listEjVenta) {

              rw = itEjVenta.getNavigatableRowIterator().createRow();
              itEjVenta.getNavigatableRowIterator().insertRow(rw);
              rw.setNewRowState(Row.STATUS_INITIALIZED);

              rw.setAttribute("IdSup", listEjec.getIdSup());
              rw.setAttribute("NomSup", listEjec.getSup());
              rw.setAttribute("IdEjVenta", listEjec.getIdEjVenta());
              rw.setAttribute("NomEjVenta", listEjec.getEjVenta());
              rw.setAttribute("NomEjVenta", listEjec.getEjVenta());
              rw.setAttribute("Cantidad", Util.formatearMonto(listEjec.getCantidadLead()));
              jefeCanal = listEjec.getJefCan();
              canalId = listEjec.getIdCanal();
          }
          
          for (EjVenta listEj : listEjVenta) {
              
              Canal canKardex = new Canal();
              
              canKardex.setIdCpg(txtCpgId.getValue().toString());
              canKardex.setCanalId(listEj.getIdCanal());
              canKardex.setCanal(listEj.getCanal());
                  
              canKardex.setJefCanal(listEj.getJefCan());
              canKardex.setIdJefCanal(listEj.getIdJefCan());
              canKardex.setSup(listEj.getSup());
              Util.Log(listEj.getEjVenta() + "listEj.getSup()");
              canKardex.setIdSup(listEj.getIdSup());
              canKardex.setNomCpg(nomCpg);
                  
              canKardex.setIdEjVenta(listEj.getIdEjVenta());
              canKardex.setEjVenta(listEj.getEjVenta());
              canKardex.setCant(listEj.getCantidadLead());
              listEjVentaCanal.add(canKardex);
              
          }
          
          
          ctx.getSessionScope().put("CaNaLiD", canalId);
          ctx.getSessionScope().put("JefECaNAl", jefeCanal);
          ctx.getSessionScope().put("userLog", IDSupervisor);
          
          ctx.getSessionScope().put("listEjVenta", listEjVentaCanal);
          
          Util.LogTime("Fin");
      }
    
    public String nomCpg(String idCpg) {

        String nomCpg = "";
        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> Nomcpg = new ArrayList<Campania>();

        Nomcpg = cpg.obtenerNomCpg(idCpg);

        for (Campania cpgList : Nomcpg) {
            nomCpg = cpgList.getCpgNom();
        }

        Util.Log(nomCpg + "NOM CPG");
        return nomCpg;
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void setCboSexo(RichSelectOneChoice cboSexo) {
        this.cboSexo = cboSexo;
    }

    public RichSelectOneChoice getCboSexo() {
        return cboSexo;
    }

    public void setCboEstadoCivil(RichSelectOneChoice cboEstadoCivil) {
        this.cboEstadoCivil = cboEstadoCivil;
    }

    public RichSelectOneChoice getCboEstadoCivil() {
        return cboEstadoCivil;
    }

    public void setCboFlagClubSueldo(RichSelectOneChoice cboFlagClubSueldo) {
        this.cboFlagClubSueldo = cboFlagClubSueldo;
    }

    public RichSelectOneChoice getCboFlagClubSueldo() {
        return cboFlagClubSueldo;
    }

    public void setTxtEdadMin(RichInputText txtEdadMin) {
        this.txtEdadMin = txtEdadMin;
    }

    public RichInputText getTxtEdadMin() {
        return txtEdadMin;
    }

    public void setTxtEdadMax(RichInputText txtEdadMax) {
        this.txtEdadMax = txtEdadMax;
    }

    public RichInputText getTxtEdadMax() {
        return txtEdadMax;
    }
}

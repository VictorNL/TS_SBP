package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Campania;
import dao.Canal;

import dao.EjVenta;

import oracle.adf.controller.v2.lifecycle.Lifecycle;

import dao.EjVentaFiltro;
import dao.Lead;


import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;


import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;


import oracle.adf.view.rich.component.rich.nav.RichCommandLink;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;
public class SBP_SLS_REASIG_X_FILT {
    private RichForm f1;
    private RichDocument d1;
    private RichTable tblEjVenta;
    private RichSelectOneChoice cboEstCivil;
    private RichSelectOneChoice cboGenero;
    private RichSelectOneChoice cboCondLab;
    private RichSelectOneChoice cboRes;
    private RichSelectOneChoice cboCont;
    private RichSelectOneChoice ckCluSueldo;
    private RichInputText txtEdadDesde;
    private RichInputText txtEdadHasta;
    private RichInputText txtPropDesde;
    private RichInputText txtPropHasta;
    private RichInputText txtDecilDesde;
    private RichInputText txtDecilHasta;
    private RichInputDate dtFecGest;
    private RichInputText txtHoraDesde;
    private RichInputText txtHoraHasta;
    private RichCommandLink txtNomEjVenta;
    private RichInputText txtCodTipGest;
    private RichInputText txtCodGrupGest;
    private RichInputText txtCodDetGest;
    private RichSelectOneChoice cboBin01;
    private RichSelectOneChoice cboBin02;
    private RichSelectOneChoice cboBin03;
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
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

    public void setTblEjVenta(RichTable tblEjVenta) {
        this.tblEjVenta = tblEjVenta;
    }

    public RichTable getTblEjVenta() {
        return tblEjVenta;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String createInsertGest() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert3");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
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

    public String createInsert3() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert2");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
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
           
           createInsert();
           
           String user = (String) resolveExpression("#{viewScope.userId2}");
           
           String idCpg = (String) resolveExpression("#{viewScope.cpgId2}");
           
           String nomCpg = nomCpg(idCpg);
           
           ctx.getSessionScope().put("idCpg", idCpg);
           
           ctx.getSessionScope().put("nomCpg", nomCpg);
           
           Util.Log(user + "USUARIO");
           
           Util.Log(idCpg + "CAMPAÑA");
           
           obtenerCanal(user);
           
           mostrarDatos(user);
           
           
       }
       
    public void mostrarDatos(String nomSup) {
          
          ServiceBiPublisher serv = new ServiceBiPublisher();

          List<EjVenta> listEjVenta = new ArrayList<EjVenta>();
          
          CollectionModel _tablaSup = (CollectionModel)tblEjVenta.getValue();
          JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
          DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();
          
          listEjVenta = serv.obtenerEvXSupFiltro(nomSup);
          
          Util.Log("listEjVenta tamaño --> " + listEjVenta.size());
          
          int b = 0;
          b = itSup.getRowSetIterator().getRowCount();
          
              
          for (int i = 0; i < b; i++) {
              itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
          }
          
          Row rw = null;  

          for (EjVenta listadoEjVenta : listEjVenta) {

              rw = itSup.getNavigatableRowIterator().createRow();
              itSup.getNavigatableRowIterator().insertRow(rw);
              rw.setNewRowState(Row.STATUS_INITIALIZED);


              rw.setAttribute("IdSup", listadoEjVenta.getIdSup());
              rw.setAttribute("NomSup", listadoEjVenta.getSup());
              rw.setAttribute("IdEjVenta", listadoEjVenta.getIdEjVenta());
              rw.setAttribute("NomEjVenta", listadoEjVenta.getEjVenta());
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

           ctx.getSessionScope().put("IdCan", canalId);
           ctx.getSessionScope().put("SupId", supId);
           ctx.getSessionScope().put("Canal", nomCanal);
           ctx.getSessionScope().put("JefeCanal", jefeCanal);
           ctx.getSessionScope().put("IdJefeCanal", idJefeCanal);
           ctx.getSessionScope().put("Super", sup);
           ctx.getSessionScope().put("EjecVenta", ejVenta);
           ctx.getSessionScope().put("IdEjecVenta", idEjecVenta);
           ctx.getSessionScope().put("userLog", userName);
       }
    
    public void obtenerDatos(ActionEvent actionEvent) {
         
         ADFContext ctx = ADFContext.getCurrent();
         
         String idEjVenta = "";
         String nomEjVenta = "";
         String cantidad = "";
         String idSup = "";
         
         List<Lead> leadList = new ArrayList<Lead>();
                 
         DCBindingContainer bindings =
             (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
         DCIteratorBinding dcItteratorBindings =
             bindings.findIteratorBinding("SbpSlsReasigEvXSupFilView1Iterator");
         
         ViewObject voTableData = dcItteratorBindings.getViewObject();
         
         Row rowSelected = voTableData.getCurrentRow();
         
         nomEjVenta = rowSelected.getAttribute("NomEjVenta").toString();
         idEjVenta = rowSelected.getAttribute("IdEjVenta").toString();
         cantidad = rowSelected.getAttribute("Cantidad").toString();
         idSup = rowSelected.getAttribute("IdSup").toString();
         
         leadList = LeadXEv(idEjVenta);
         
         Util.Log("leadList --> " + leadList.size());
        
         ctx.getSessionScope().put("nomEjVenta", nomEjVenta);
         ctx.getSessionScope().put("idEjventaQuitar", idEjVenta);
         ctx.getSessionScope().put("Cantidad", cantidad);
         ctx.getSessionScope().put("idSup", idSup);
         ctx.getSessionScope().put("leadListReasig", leadList);
         
         for(Lead listPrueba : leadList){
             Util.Log("ID LEAD A ACTUALIZAR -- > " + listPrueba.getIdLead());
         }
         
         
         Util.Log("CANT CAPTURADA --> " + cantidad);
         Util.Log("nom ejventa --> " + nomEjVenta);
         Util.Log("IDEJVENTA CAPTURADO --> " + idEjVenta);
         Util.Log("idSup CAPTURADO --> " + idSup);
         
     }
    
    public List<Lead> LeadXEv(String idEjVenta){
          
          ADFContext ctx = ADFContext.getCurrent();
          
          ServiceBiPublisher lead = new ServiceBiPublisher();
          
          String condLab = "";
          String bin01 = "";
          String decilMin = "";
          String decilMax = "";
          String canalId = "";
          String bin02 = "";
          String bin03 = "";
          String residencia = "";
          String edadMin = "";
          String propMin = "";
          String propMax = "";
          String edadMax = "";
          String contactabilidad = "";
          String estCivil = "";
          String flagClub = "";
          String genero = "";
          String tipGest = "";
          String grupGest = "";
          String detGest = "";
          String fecGest = "";
          String horaDesde = "";
          String horaHasta = "";
          
          List<Lead> leadList = new ArrayList<Lead>();
          
          canalId = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
          
          String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
          
          Util.Log("canalId --> " + canalId);
          
          if(ckCluSueldo.getValue() != null){
          
              flagClub = ckCluSueldo.getValue().toString();
          
          } else {
              
              flagClub = "";
          }
          
          if(cboCondLab.getValue() != null){
          
              condLab = cboCondLab.getValue().toString();
          
          } else {
              
              condLab = "";
          }
          
          if(cboBin01.getValue() != null){
          
              bin01 = cboBin01.getValue().toString();
          
          } else {
              
              bin01 = "";
          }
          
          if(txtDecilDesde.getValue() != null){
          
              decilMin = txtDecilDesde.getValue().toString();
          
          } else {
              
              decilMin = "";
          }
          
          if(txtDecilHasta.getValue() != null){
          
              decilMax = txtDecilHasta.getValue().toString();
          
          } else {
              
              decilMax = "";
          }
          
          
          if(cboBin02.getValue() != null){
          
              bin02 = cboBin02.getValue().toString();
          
          } else {
              
              bin01 = "";
          }
          
          if(cboBin03.getValue() != null){
          
              bin03 = cboBin03.getValue().toString();
          
          } else {
              
              bin01 = "";
          }
          
          if(cboRes.getValue() != null){
          
              residencia = cboRes.getValue().toString();
          
          } else {
              
              residencia = "";
          }
          
          if(txtEdadDesde.getValue() != null){
          
              edadMin = txtEdadDesde.getValue().toString();
          
          } else {
              
              edadMin = "";
          }
          
          if(txtEdadHasta.getValue() != null){
          
              edadMax = txtEdadHasta.getValue().toString();
          
          } else {
              
              edadMax = "";
          }
          
          if(txtPropDesde.getValue() != null){
          
              propMin = txtPropDesde.getValue().toString();
          
          } else {
              
              propMin = "";
          }
          
          if(txtPropHasta.getValue() != null){
          
              propMax = txtPropHasta.getValue().toString();
          
          } else {
              
              propMax = "";
          }
          
          if(txtEdadHasta.getValue() != null){
          
              propMax = txtEdadHasta.getValue().toString();
          
          } else {
              
              propMax = "";
          }
          
          if(cboCont.getValue() != null){
          
              contactabilidad = cboCont.getValue().toString();
          
          } else {
              
              contactabilidad = "";
          }
          
          if(cboEstCivil.getValue() != null){
          
              estCivil = cboEstCivil.getValue().toString();
          
          } else {
              
              estCivil = "";
          }
          
          if(cboGenero.getValue() != null){
          
              genero = cboGenero.getValue().toString();
          
          } else {
              
              genero = "";
          }
          
          if(txtCodTipGest.getValue() != null){
          
              tipGest = txtCodTipGest.getValue().toString();
          
          } else {
              
              tipGest = "";
          }
          
          if(txtCodGrupGest.getValue() != null){
          
              grupGest = txtCodGrupGest.getValue().toString();
          
          } else {
              
              grupGest = "";
          }
          
          if(txtCodDetGest.getValue() != null){
          
              detGest = txtCodDetGest.getValue().toString();
          
          } else {
              
              detGest = "";
          }
          
          if(dtFecGest.getValue() != null){
          
              fecGest = dtFecGest.getValue().toString();
          
          } else {
              
              fecGest = "";
          }
          
          if(txtHoraDesde.getValue() != null){
          
              horaDesde = txtHoraDesde.getValue().toString();
          
          } else {
              
              horaDesde = "";
          }
          
          if(txtHoraHasta.getValue() != null){
          
              horaHasta = txtHoraHasta.getValue().toString();
          
          } else {
              
              horaHasta = "";
          }
          
                                                                                   
          leadList = lead.obtenerReasigLeadXFiltro(condLab, idEjVenta, bin01, decilMin, decilMax, canalId,
                                         bin02, bin03, idCpg, residencia, edadMin, propMin, propMax,
                                         edadMax, contactabilidad, estCivil, flagClub, genero, tipGest, grupGest, detGest,
                                         fecGest, horaDesde, horaHasta);
          
          return leadList;
          
      }
       
    public String nomCpg(String idCpg) {
          String nomCpg = "";
          ServiceBiPublisher cpg = new ServiceBiPublisher();

          List<Campania> Nomcpg = new ArrayList<Campania>();

          Nomcpg = cpg.obtenerNomCpg(idCpg);

          for (Campania cpgList : Nomcpg) {
              nomCpg = cpgList.getCpgNom();
          }

          Util.Log("fin");
          return nomCpg;
      }
      
       
    public void createInsert(){
           
           createInsert1();
           
           createInsert2();
           
           createInsert3();
           
           createInsertGest();
           
       }

    public void setCboEstCivil(RichSelectOneChoice cboEstCivil) {
        this.cboEstCivil = cboEstCivil;
    }

    public RichSelectOneChoice getCboEstCivil() {
        return cboEstCivil;
    }

    public void setCboGenero(RichSelectOneChoice cboGenero) {
        this.cboGenero = cboGenero;
    }

    public RichSelectOneChoice getCboGenero() {
        return cboGenero;
    }

    public void setCboCondLab(RichSelectOneChoice cboCondLab) {
        this.cboCondLab = cboCondLab;
    }

    public RichSelectOneChoice getCboCondLab() {
        return cboCondLab;
    }

    public void setCboRes(RichSelectOneChoice cboRes) {
        this.cboRes = cboRes;
    }

    public RichSelectOneChoice getCboRes() {
        return cboRes;
    }

    public void setCboCont(RichSelectOneChoice cboCont) {
        this.cboCont = cboCont;
    }

    public RichSelectOneChoice getCboCont() {
        return cboCont;
    }

    public void setCkCluSueldo(RichSelectOneChoice ckCluSueldo) {
        this.ckCluSueldo = ckCluSueldo;
    }

    public RichSelectOneChoice getCkCluSueldo() {
        return ckCluSueldo;
    }

    public void setTxtEdadDesde(RichInputText txtEdadDesde) {
        this.txtEdadDesde = txtEdadDesde;
    }

    public RichInputText getTxtEdadDesde() {
        return txtEdadDesde;
    }

    public void setTxtEdadHasta(RichInputText txtEdadHasta) {
        this.txtEdadHasta = txtEdadHasta;
    }

    public RichInputText getTxtEdadHasta() {
        return txtEdadHasta;
    }

    public void setTxtPropDesde(RichInputText txtPropDesde) {
        this.txtPropDesde = txtPropDesde;
    }

    public RichInputText getTxtPropDesde() {
        return txtPropDesde;
    }

    public void setTxtPropHasta(RichInputText txtPropHasta) {
        this.txtPropHasta = txtPropHasta;
    }

    public RichInputText getTxtPropHasta() {
        return txtPropHasta;
    }

    public void setTxtDecilDesde(RichInputText txtDecilDesde) {
        this.txtDecilDesde = txtDecilDesde;
    }

    public RichInputText getTxtDecilDesde() {
        return txtDecilDesde;
    }

    public void setTxtDecilHasta(RichInputText txtDecilHasta) {
        this.txtDecilHasta = txtDecilHasta;
    }

    public RichInputText getTxtDecilHasta() {
        return txtDecilHasta;
    }

    public void setDtFecGest(RichInputDate dtFecGest) {
        this.dtFecGest = dtFecGest;
    }

    public RichInputDate getDtFecGest() {
        return dtFecGest;
    }

    public void setTxtHoraDesde(RichInputText txtHoraDesde) {
        this.txtHoraDesde = txtHoraDesde;
    }

    public RichInputText getTxtHoraDesde() {
        return txtHoraDesde;
    }

    public void setTxtHoraHasta(RichInputText txtHoraHasta) {
        this.txtHoraHasta = txtHoraHasta;
    }

    public RichInputText getTxtHoraHasta() {
        return txtHoraHasta;
    }
    
    public List<EjVentaFiltro> listEj(){
          
          String supId = "";
          String sup = "";
          String ejVenta = "";
          String idEjVenta = "";
          String cant = "";
          
          List<EjVentaFiltro> listFilt = new ArrayList<EjVentaFiltro>();
          
          DCBindingContainer binding = this.getDCBindingsContainer();
          DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsReasigEvXSupFilView1Iterator");

          ViewObject vo = dcItteratorBindings.getViewObject();
          RowSetIterator rsi = vo.createRowSetIterator(null);

          if (rsi.getRowCount() > 0) {
            
              Row currRow;

              while (rsi.hasNext()) {
                  
                  currRow = rsi.next();
                  
                  EjVentaFiltro filt = new EjVentaFiltro();
                  
                  supId = currRow.getAttribute("IdSup").toString();
                  sup = currRow.getAttribute("NomSup").toString();
                  ejVenta = currRow.getAttribute("NomEjVenta").toString();
                  idEjVenta = currRow.getAttribute("IdEjVenta").toString();
                  if(currRow.getAttribute("Cantidad") != null){
                  
                      cant = currRow.getAttribute("Cantidad").toString();
                      
                  } else {
                      cant = "0";
                  }
                  
                  Util.Log("cant --> " + cant);
                  filt.setSupId(supId);
                  filt.setNomSup(sup);
                  filt.setEjVenta(ejVenta);
                  filt.setIdEjVenta(idEjVenta);
                  filt.setCantidad(cant);
                  listFilt.add(filt);
              }

          }
          
      return listFilt;
      
      }

    public void consultarLeads(ActionEvent actionEvent) {
         
         
         ADFContext ctx = ADFContext.getCurrent();
         
         List<EjVentaFiltro> listFiltro = new ArrayList<EjVentaFiltro>();
                 
         listFiltro = listEj();
         
         Util.Log("listFiltro tamaño --> " + listFiltro.size());
        
         String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
         
         Util.Log("cpg Id --> " + idCpg);
         
         Row rw = null;

         CollectionModel _tablaCanal= (CollectionModel)tblEjVenta.getValue();
         
         JUCtrlHierBinding _adfTableCanalBinding = (JUCtrlHierBinding)_tablaCanal.getWrappedData(); //devuelve el table binding
         DCIteratorBinding itCanal = _adfTableCanalBinding.getDCIteratorBinding();

         int b = 0;
         b = itCanal.getRowSetIterator().getRowCount();

         for (int i = 0; i < b; i++) {
             itCanal.getRowSetIterator().getRowAtRangeIndex(0).remove();
         }


             for (EjVentaFiltro listEV : listFiltro) {

                 rw = itCanal.getNavigatableRowIterator().createRow();
                 itCanal.getNavigatableRowIterator().insertRow(rw);
                 rw.setNewRowState(Row.STATUS_INITIALIZED);
                     
                 String cantLead = calcularCantLead(listEV.getIdEjVenta(), idCpg);
                 Util.Log("cant lead x ejVenta --> " + cantLead);
                 rw.setAttribute("IdSup", listEV.getSupId()); 
                 rw.setAttribute("IdEjVenta", listEV.getIdEjVenta()); 
                 rw.setAttribute("NomSup", listEV.getNomSup());     
                 rw.setAttribute("NomEjVenta", listEV.getEjVenta());   
                 
                 rw.setAttribute("Cantidad", cantLead);
             
             }     
             
         txtNomEjVenta.setDisabled(false);
      
     }
    
    public String calcularCantLead(String idEjVenta, String idCpg){
           
           ADFContext ctx = ADFContext.getCurrent();
           
           ServiceBiPublisher lead = new ServiceBiPublisher();
           
           String condLab = "";
           String bin01 = "";
           String decilMin = "";
           String decilMax = "";
           String canalId = "";
           String bin02 = "";
           String bin03 = "";
           String residencia = "";
           String edadMin = "";
           String propMin = "";
           String propMax = "";
           String edadMax = "";
           String contactabilidad = "";
           String estCivil = "";
           String flagClub = "";
           String genero = "";
           String tipGest = "";
           String grupGest = "";
           String detGest = "";
           String fecGest = "";
           String horaDesde = "";
           String horaHasta = "";
           
           List<Lead> leadList = new ArrayList<Lead>();
           
           canalId = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
           
           Util.Log("canalId --> " + canalId);
           
           if(ckCluSueldo.getValue() != null){
           
               condLab = ckCluSueldo.getValue().toString();
           
           } else {
               
               condLab = "";
           }
           
           if(cboCondLab.getValue() != null){
           
               condLab = cboCondLab.getValue().toString();
           
           } else {
               
               condLab = "";
           }
           
           if(cboBin01.getValue() != null){
           
               bin01 = cboBin01.getValue().toString();
           
           } else {
               
               bin01 = "";
           }
           
           if(txtDecilDesde.getValue() != null){
           
               decilMin = txtDecilDesde.getValue().toString();
           
           } else {
               
               decilMin = "";
           }
           
           if(txtDecilHasta.getValue() != null){
           
               decilMax = txtDecilHasta.getValue().toString();
           
           } else {
               
               decilMax = "";
           }
           
           
           if(cboBin02.getValue() != null){
           
               bin02 = cboBin02.getValue().toString();
           
           } else {
               
               bin01 = "";
           }
           
           if(cboBin03.getValue() != null){
           
               bin03 = cboBin03.getValue().toString();
           
           } else {
               
               bin01 = "";
           }
           
           if(cboRes.getValue() != null){
           
               residencia = cboRes.getValue().toString();
           
           } else {
               
               residencia = "";
           }
           
           if(txtEdadDesde.getValue() != null){
           
               edadMin = txtEdadDesde.getValue().toString();
           
           } else {
               
               edadMin = "";
           }
           
           if(txtEdadHasta.getValue() != null){
           
               edadMax = txtEdadHasta.getValue().toString();
           
           } else {
               
               edadMax = "";
           }
           
           if(txtPropDesde.getValue() != null){
           
               propMin = txtPropDesde.getValue().toString();
           
           } else {
               
               propMin = "";
           }
           
           if(txtPropHasta.getValue() != null){
           
               propMax = txtPropHasta.getValue().toString();
           
           } else {
               
               propMax = "";
           }
           
           if(txtEdadHasta.getValue() != null){
           
               propMax = txtEdadHasta.getValue().toString();
           
           } else {
               
               propMax = "";
           }
           
           if(cboCont.getValue() != null){
           
               contactabilidad = cboCont.getValue().toString();
           
           } else {
               
               contactabilidad = "";
           }
           
           if(cboEstCivil.getValue() != null){
           
               estCivil = cboEstCivil.getValue().toString();
           
           } else {
               
               estCivil = "";
           }
           
           if(cboGenero.getValue() != null){
           
               genero = cboGenero.getValue().toString();
           
           } else {
               
               genero = "";
           }
           
           if(txtCodTipGest.getValue() != null){
           
               tipGest = txtCodTipGest.getValue().toString();
           
           } else {
               
               tipGest = "";
           }
           
           if(txtCodGrupGest.getValue() != null){
           
               grupGest = txtCodGrupGest.getValue().toString();
           
           } else {
               
               grupGest = "";
           }
           
           if(txtCodDetGest.getValue() != null){
           
               detGest = txtCodDetGest.getValue().toString();
           
           } else {
               
               detGest = "";
           }
           
           if(dtFecGest.getValue() != null){
           
               fecGest = dtFecGest.getValue().toString();
           
           } else {
               
               fecGest = "";
           }
           
           if(txtHoraDesde.getValue() != null){
           
               horaDesde = txtHoraDesde.getValue().toString();
           
           } else {
               
               horaDesde = "";
           }
           
           if(txtHoraHasta.getValue() != null){
           
               horaHasta = txtHoraHasta.getValue().toString();
           
           } else {
               
               horaHasta = "";
           }
           
           
           Util.Log("fecGest --> " + fecGest); 
                                                                                    
           leadList = lead.obtenerReasigLeadXFiltro(condLab, idEjVenta, bin01, decilMin, decilMax, canalId,
                                          bin02, bin03, idCpg, residencia, edadMin, propMin, propMax,
                                          edadMax, contactabilidad, estCivil, flagClub, genero, tipGest, grupGest, detGest,
                                          fecGest, horaDesde, horaHasta);
           
           ctx.getSessionScope().put("leadList", leadList);
           
           String cantLeadsXEjV = leadList.size() + "";
           
           ctx.getSessionScope().put("cantLeadXFiltroReas", cantLeadsXEjV);
           
           return cantLeadsXEjV;
           
       }

    public void setTxtNomEjVenta(RichCommandLink txtNomEjVenta) {
        this.txtNomEjVenta = txtNomEjVenta;
    }

    public RichCommandLink getTxtNomEjVenta() {
        return txtNomEjVenta;
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

    public void setCboBin01(RichSelectOneChoice cboBin01) {
        this.cboBin01 = cboBin01;
    }

    public RichSelectOneChoice getCboBin01() {
        return cboBin01;
    }

    public void setCboBin02(RichSelectOneChoice cboBin02) {
        this.cboBin02 = cboBin02;
    }

    public RichSelectOneChoice getCboBin02() {
        return cboBin02;
    }

    public void setCboBin03(RichSelectOneChoice cboBin03) {
        this.cboBin03 = cboBin03;
    }

    public RichSelectOneChoice getCboBin03() {
        return cboBin03;
    }
}

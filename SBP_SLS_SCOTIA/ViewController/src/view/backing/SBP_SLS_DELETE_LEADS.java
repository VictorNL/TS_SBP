package view.backing;

import WsEjRep.ServiceBiPublisher;

import client.ImportLeadResource;

import dao.Canal;
import dao.CargaMasiva;
import dao.Lead;

import dao.LeadDelete;
import dao.LeadLote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
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
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_DELETE_LEADS {
    private RichForm f1;
    private RichDocument d1;
    private RichTable tblLote;
    private RichInputText txtLeadBorrar;

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

    public void setTblLote(RichTable tblLote) {
        this.tblLote = tblLote;
    }

    public RichTable getTblLote() {
        return tblLote;
    }
    
    public void onBeforePhase(PhaseEvent phaseEvent) {
           if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
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
           ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
           return valueExp.getValue(elContext);
       }
       
       public void onLoad(){
           
        mostrarDatos();
           
       }
       
       public void mostrarDatos(){
           
           List<LeadLote> listLot = new ArrayList<LeadLote>();

           listLot = listLeadLote();

           Util.Log(listLot.size() + "TAMAÑO");
           CollectionModel _tablaSup = (CollectionModel)tblLote.getValue();
           JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
           DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();


           int b = 0;
           b = itSup.getRowSetIterator().getRowCount();

           for (int i = 0; i < b; i++) {
               itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
           }
           Row rw = null;
           
           
           
           for (LeadLote listadoLote : listLot) {

               rw = itSup.getNavigatableRowIterator().createRow();
               itSup.getNavigatableRowIterator().insertRow(rw);
               rw.setNewRowState(Row.STATUS_INITIALIZED);


               rw.setAttribute("IdCarga", listadoLote.getIdCarga());
               rw.setAttribute("FecCarga", listadoLote.getFecCarga());
               rw.setAttribute("Usuario", listadoLote.getUsuario());
               rw.setAttribute("CantLead", listadoLote.getCantLead());
               rw.setAttribute("Estado", listadoLote.getEstado());       
               
           }
       }
       
       
    public List<LeadLote> listLeadLote() {

          List<LeadLote> listaLeadLote = new ArrayList<LeadLote>();

          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          String idCarga = "";
          String fecCarga = "";
          String usuario = "";
          String cantLead = "";
          String estado = "";


          try {
              String query =
                  "select distinct nvl(id_carga, 0) ID_CARGA, nvl(fecha_carga, '-') FECHA_CARGA, nvl(usuario, '-') USUARIO, nvl(cantidad_lead, '0') CANTIDAD_LEAD, nvl(estado, '-') ESTADO from SBP_SLS_CARGA_MASIVA_HIST order by id_carga";

              Util.Log(query + "QUERY");

              sbp.utils.Connection connection = new sbp.utils.Connection();

              conn = connection.getConnection();

              st = conn.prepareStatement(query);

              rs = st.executeQuery();

              while (rs.next()) {
                  LeadLote lote = new LeadLote();
                  idCarga = rs.getString("ID_CARGA");
                  fecCarga = rs.getString("FECHA_CARGA");
                  usuario = rs.getString("USUARIO");
                  cantLead = rs.getString("CANTIDAD_LEAD");
                  estado = rs.getString("ESTADO");

                  lote.setIdCarga(idCarga);
                  lote.setFecCarga(fecCarga);
                  lote.setUsuario(usuario);
                  lote.setCantLead(cantLead);
                  lote.setEstado(estado);
                  listaLeadLote.add(lote);

              }

          } catch (SQLException e) {
              Util.Log(e + "error");
          } finally {
              DbUtils.closeQuietly(rs);
              DbUtils.closeQuietly(st);
              DbUtils.closeQuietly(conn);
          }
      
      Util.Log(listaLeadLote.size() + "CANTIDAD LEADS DELETE");
          return listaLeadLote;
      }
    
    public void actualizaEstadoBorrado(String idCarga) {

        Util.Log(idCarga + "ID CARGA");
        PreparedStatement prepareStatement = null;

        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;
        
        try {

            String query =
                "BEGIN SBP_SLS_CARGA_MASIVA_PKG.act_estado_borrado(?); END;";

            Util.Log(query);

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);

            prepareStatement.setString(1, idCarga);
            prepareStatement.execute();
            Util.Log("1");
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "MENSAJE");
        } finally {
            DbUtils.closeQuietly(prepareStatement);
            DbUtils.closeQuietly(conn);
        }
        

    }

    public void setTxtLeadBorrar(RichInputText txtLeadBorrar) {
        this.txtLeadBorrar = txtLeadBorrar;
    }

    public RichInputText getTxtLeadBorrar() {
        return txtLeadBorrar;
    }
    
    public void borrarLeads(){
        
        ServiceBiPublisher lot = new ServiceBiPublisher();
        
        List<LeadLote> listaLeadLote = new ArrayList<LeadLote>();
        
        if(txtLeadBorrar.getValue() != null){
        
        String loteId = txtLeadBorrar.getValue().toString();
        
        Util.Log(loteId + "LOTE");
        
        listaLeadLote = lot.obtenerLeadDeleteLote(loteId);
        
        Util.Log(listaLeadLote.size() + "TAMAÑO LIST");
        
        borrarLeadMasivo(listaLeadLote);
        
        actualizaEstadoBorrado(loteId);
        
        mostrarDatos();
        
        FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "Se ha enviado el proceso de borrado de Leads correspondiente al lote " + loteId,
                                                                          ""));
            
        } else {
                
                FacesContext fctx = FacesContext.getCurrentInstance();
                             fctx.addMessage("",
                                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar un N° de Lote",
                                                          ""));
            
            }
        
    }
    
    
    public void borrarLeadMasivo(List<LeadLote> listLotLead) {


        Boolean exito = false;
        String strTrama1;
        String strTrama2;
        Integer intIdxLead = 0;

        int pivot = 50000;
        int ini = 0;
        int fin = pivot;
        int cant = listLotLead.size();

        StringBuffer sBuffer = new StringBuffer();

        try {

            while (cant > 0) {

                // Cabecera del txt
                sBuffer.append("Lead.LeadNumber,Lead.ActionCode,Lead.Status,Lead.RetireReason");
                sBuffer.append(System.getProperty("line.separator"));

                List<LeadLote> listaLeads = new ArrayList<LeadLote>();
                
                if (cant > pivot) {

                listaLeads = listLotLead.subList(ini, fin);

                Util.Log(listaLeads + "LISTALEADS IF");

                for (int i = 0; i < listaLeads.size(); i++) {

                    LeadLote lote = listaLeads.get(intIdxLead);
                    intIdxLead++;

                    strTrama1 = lote.getLeadNumber() + "," + "" + "," + "RETIRED" + "," + "DUPLICATE_LEAD";

                    sBuffer.append(strTrama1);

                    sBuffer.append(System.getProperty("line.separator"));

                    Util.Log("[strTrama1] : " + strTrama1);


                    strTrama2 = lote.getLeadNumber() + "," + "DELETE" + "," + "" + "," + "";

                    sBuffer.append(strTrama2);
                    sBuffer.append(System.getProperty("line.separator"));

                    Util.Log("[strTrama2] : " + strTrama2);

                }

                Util.Log(new Date() +
                                   " Inicio de ejecutar web service if");

                ImportLeadResource importLeadResource =
                    new ImportLeadResource();

                exito =
                        importLeadResource.importarLeadRecurso(sBuffer, "SBP - Importar LeadResource, Carga Masiva CSV",
                                                               "300000111678267");
                Util.Log(new Date() +
                                   " Termino de ejecutar web service if");

                sBuffer = new StringBuffer();
                intIdxLead = 0;

               // actualizaFlagLead(idCarga, ini, fin);


                Util.Log(listaLeads.size() + "TAMAÑO A QUITAR");

            }  else {
                intIdxLead = 0;

                listaLeads = listLotLead.subList(ini, listLotLead.size());

                for (int i = 0; i < listaLeads.size(); i++) {

                    LeadLote lote = listaLeads.get(intIdxLead);
                    intIdxLead++;

                    strTrama1 = lote.getLeadNumber() + "," + "" + "," + "RETIRED" + "," + "DUPLICATE_LEAD";

                    sBuffer.append(strTrama1);

                    sBuffer.append(System.getProperty("line.separator"));

                    Util.Log("[strTrama1] : " + strTrama1);


                    strTrama2 = lote.getLeadNumber() + "," + "DELETE" + "," + "" + "," + "";

                    sBuffer.append(strTrama2);
                    sBuffer.append(System.getProperty("line.separator"));

                    Util.Log("[strTrama2] : " + strTrama2);

                    }

                    Util.Log(new Date() +
                                   " Inicio de ejecutar web service if");

                    ImportLeadResource importLeadResource =
                    new ImportLeadResource();

                    exito =
                        importLeadResource.importarLeadRecurso(sBuffer, "SBP - Importar LeadResource, Carga Masiva CSV",
                                                               "300000111678267");
                    Util.Log(new Date() +
                                   " Termino de ejecutar web service if");
                          
                 //   actualizaFlagLead(idCarga, ini, fin);
            
                }
                
                ini = fin;

                Util.Log(ini + "INI");
                Util.Log(pivot + "fin = ini + pivot");

                fin = ini + pivot;

                Util.Log(fin + "FIN");
                Util.Log(cant + "fin = ini + pivot");

                cant = cant - pivot;

                Util.Log(cant + "CANTdddd");

              
                Util.Log(listaLeads + "LISTALEADS");
                
               // eliminarDataProc();
            }

            } catch (Exception ex) {
            Util.Log("[Error] : " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                          ex.getMessage(),
                                                                          ""));
            }

            }

    public void borrarLeads(ActionEvent actionEvent) {
        borrarLeads();
    }
}

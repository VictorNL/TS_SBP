package view.backing;

import Client.MergeLeadInvoke;

import Dao.SimplifiedImportActivity;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;
import dao.EjVenta;

import dao.EjVentaFiltro;
import dao.Lead;

import dao.LeadDelete;

import dao.NuevoDestino;

import java.io.ByteArrayOutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_REASIG_EVENTA_NEW {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtCantLeadsTodos;
    private RichOutputLabel txtEjventaActual;
    private RichTable tblEjec;
    
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
    
    public List<EjVentaFiltro> listEj(){

         String ejVenta = "";
         String idEjVenta = "";
         String cant = "";
         
         List<EjVentaFiltro> listFilt = new ArrayList<EjVentaFiltro>();
         
         DCBindingContainer binding = this.getDCBindingsContainer();
         DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsReasigEvXSupView1Iterator");

         ViewObject vo = dcItteratorBindings.getViewObject();
         RowSetIterator rsi = vo.createRowSetIterator(null);

         if (rsi.getRowCount() > 0) {
           
             Row currRow;

             while (rsi.hasNext()) {
                 
                 currRow = rsi.next();
                 
                 EjVentaFiltro filt = new EjVentaFiltro();

                 ejVenta = currRow.getAttribute("NomEjVenta").toString();
                 idEjVenta = currRow.getAttribute("IdEjVenta").toString();
                 if(currRow.getAttribute("Cantidad") != null){
                 
                     cant = currRow.getAttribute("Cantidad").toString();
                     
                 } else {
                     cant = "0";
                 }
                 
                 Util.Log("cant --> " + cant);
                 filt.setEjVenta(ejVenta);
                 filt.setIdEjVenta(idEjVenta);
                 filt.setCantidad(cant);
                 listFilt.add(filt);
             }

         }
         
     return listFilt;
     
     }

    public void reasignar_OLD(ActionEvent actionEvent) {
       
        ADFContext ctx = ADFContext.getCurrent();
              
              List<EjVentaFiltro> listFiltroReasig = new ArrayList<EjVentaFiltro>();
              
              List<Lead> leadListadoTabla = new ArrayList<Lead>();
              
              List<Lead> listadoLeads = new ArrayList<Lead>();
              
              listadoLeads = (List<Lead>)ctx.getSessionScope().get("leadListReasig");
              
              Util.Log("listadoLeads tamaño --> " + listadoLeads.size());
              
              String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
              
              String idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
              
              String canal = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
                         
              String jefeCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
                         
              String supervisor = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
              
              String idSup = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
              
              String cantidad = ctx.getSessionScope().get("Cantidad") == null ? "" : ctx.getSessionScope().get("Cantidad").toString();
              
              String idEjecVentaQuitar = ctx.getSessionScope().get("idEjventaQuitar") == null ? "" : ctx.getSessionScope().get("idEjventaQuitar").toString();
              
              Util.Log("idEjecVentaQuitar --> " + idEjecVentaQuitar);
              
              Integer cant = Integer.parseInt(cantidad);
              
              listFiltroReasig = listEj();
              
              Util.Log("listFiltro --> " + listFiltroReasig.size());
              
              ctx.getSessionScope().put("listFiltroReasig", listFiltroReasig);
              
              insertarDataReasigList(listadoLeads, listFiltroReasig);
              
              String sLoteEjecucion = DosificacionEjecutivoBO.reasignarXFiltro_OLD(idCpg, idCanal, canal, jefeCanal, supervisor, idSup, cant, 0.0);
              FacesContext fctx = FacesContext.getCurrentInstance();
              fctx.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, /*DosificacionEjecutivoBO.obtenerLog(sLoteEjecucion)*/ "Asignación Completada",
                                               ""));
              String idCarga = ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();
              
              Util.Log("listadoLeads tamaño antes de insertar --> " + listadoLeads.size());
              
              leadListadoTabla = listLeadEj(idCarga);
              
              reasignarSupEjVenta(leadListadoTabla , idEjecVentaQuitar);
    }
    
    public void reasignar(ActionEvent actionEvent) {
            try {

                ADFContext ctx = ADFContext.getCurrent();

                List<Lead> leadListadoTabla = new ArrayList<Lead>();
                List<Lead> listadoLeads = new ArrayList<Lead>();

                listadoLeads = (List<Lead>)ctx.getSessionScope().get("leadListReasig");

                Util.Log("listadoLeads tamaño --> " + listadoLeads.size());

                String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
                String idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
                String canal = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
                String jefeCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
                String supervisor = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
                String idSup = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
                String cantidad = ctx.getSessionScope().get("Cantidad") == null ? "" : ctx.getSessionScope().get("Cantidad").toString();
                String idEjecVentaQuitar = ctx.getSessionScope().get("idEjventaQuitar") == null ? "" : ctx.getSessionScope().get("idEjventaQuitar").toString();

                Util.Log("idEjecVentaQuitar :: " + idEjecVentaQuitar);

                Integer cant = Integer.parseInt(cantidad);

                List<EjVentaFiltro> listFiltroReasig = this.listEj();
                ctx.getSessionScope().put("listFiltroReasig", listFiltroReasig);
                Util.Log("listFiltroReasig :: " + listFiltroReasig.size());

                this.insertarDataReasigList(listadoLeads, listFiltroReasig);

                DosificacionEjecutivoBO.reasignarXFiltro(idCpg, 
                                                         idCanal, 
                                                         canal, 
                                                         jefeCanal, 
                                                         supervisor, 
                                                         idSup, 
                                                         idEjecVentaQuitar);           
                

                Util.MostrarMensajeOK("Asignación Completada");

                String idCarga = ctx.getSessionScope().get("idCarga") == null ? "" : ctx.getSessionScope().get("idCarga").toString();

                Util.Log("listadoLeads tamaño antes de insertar :: " + listadoLeads.size());

                leadListadoTabla = this.listLeadEj(idCarga);
                this.reasignarSupEjVenta(leadListadoTabla, idEjecVentaQuitar);

            } catch (Exception ex) {
                Util.MostrarMensajeKO(ex.getMessage());
            }
        }
    
    public void reasignarSupEjVenta(List<Lead> plstLeads,
                                      String pstrResourceID) {
          
               SimplifiedImportActivity p = new SimplifiedImportActivity();


               String strTrama1;
               String strTrama2;
               String strTrama3;
               String strTramaCabecera1 = "LeadNumber" + "\n";
               String strTramaCabecera2 = "LeadNumber,ActionCode,ResourceId" + "\n";
               String strTramaFeedback = "LEAD_NUMBER,RecordName,EjecutivoVenta_c,Supervisor_c,JefeCanal_c,Canal_c,NombreCampana_c,AuxFeedbackInicial_c,TipoGestion_c,GrupoGestion_c,DetalleGestion_c" +
                   "\n";
               String strTrama4;
               String strTrama5;
               Integer intIdxLead = 0;

               int pivot = 50000;
               int ini = 0;
               int fin = pivot;
               int cant = plstLeads.size();


               try {
                   while (cant > 0) {
                       if (cant > pivot) {
                       
                           ByteArrayOutputStream bos = new ByteArrayOutputStream();

                           ZipOutputStream zos = new ZipOutputStream(bos);
                           ZipEntry ze = new ZipEntry("Lead.csv");
                           zos.putNextEntry(ze);

                           byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                           zos.write(cabeceraLead1, 0, cabeceraLead1.length);

                           List<Lead> listaLeads = new ArrayList<Lead>();
                           
                           listaLeads = plstLeads.subList(ini, fin);
                           
                           Util.Log(listaLeads.size() + "LISTALEAD");
                           intIdxLead = 0;

                           for (int i = 0; i < listaLeads.size(); i++) {

                               Lead lead = listaLeads.get(intIdxLead);
                               intIdxLead++;

                               strTrama1 = lead.getLeadNumber() + "\n";

                               Util.Log(strTrama1 + "strTrama1 if");

                               byte[] bt = strTrama1.getBytes();
                               zos.write(bt, 0, bt.length);

                           }
                           // Cerrar Entry
                           zos.closeEntry();

                           ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                           zos.putNextEntry(zef);
                           byte[] cabeceraLead = strTramaCabecera2.getBytes();
                           zos.write(cabeceraLead, 0, cabeceraLead.length);

                           intIdxLead = 0;
                           for (int i = 0; i < listaLeads.size(); i++) {

                               Lead lead = listaLeads.get(intIdxLead);
                               intIdxLead++;

                               strTrama2 =
                                       lead.getLeadNumber() + "," + "DELETE" + "," +
                                       pstrResourceID + "\n";
                               strTrama3 =
                                       lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() +
                                       "\n";


                               Util.Log(strTrama2 + "strTrama2 if");

                               Util.Log(strTrama3 + "strTrama3  if");


                               byte[] bt = strTrama2.getBytes();
                               byte[] bt2 = strTrama3.getBytes();


                               zos.write(bt, 0, bt.length);
                               zos.write(bt2, 0, bt2.length);


                           }
                           // Cerrar Entry
                           zos.closeEntry();


                           Util.Log("2");


                           ZipEntry zefe = new ZipEntry("Feedback_Lead_c.csv");
                           zos.putNextEntry(zefe);

                           byte[] cabeceraFb = strTramaFeedback.getBytes();

                           zos.write(cabeceraFb, 0, cabeceraFb.length);

                           intIdxLead = 0;
                           for (int i = 0; i < listaLeads.size(); i++) {

                               Lead lead = listaLeads.get(intIdxLead);
                               intIdxLead++;

                               strTrama4 =
                                       lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                       "," + lead.getResourceId() + "," +
                                       lead.getSup() + "," +
                                       lead.getJefeCanal() + "," + lead.getCanal() +
                                       "," + lead.getNomCpg() + "," + "1" + "," +
                                       "" + "," + "" + "," + "" + "\n";

                               strTrama5 =
                                       lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                       "," + pstrResourceID + "," +
                                       lead.getSup() + "," +
                                       lead.getJefeCanal() + "," + lead.getCanal() +
                                       "," + lead.getNomCpg() + "," + "0" + "," +
                                       "3" + "," + "12" + "," + "38" + "\n";

                               Util.Log(strTrama4 + "strTrama4 if");

                               Util.Log(strTrama5 + "strTrama5 if");

                               byte[] bt = strTrama4.getBytes();
                               byte[] bt2 = strTrama5.getBytes();


                               zos.write(bt, 0, bt.length);
                               zos.write(bt2, 0, bt2.length);


                           }
                           // Cerrar Entry
                           zos.closeEntry();

                           String str =
                               client.Base64.byteArrayToBase64(bos.toByteArray());

                           Util.Log(str + "str");

                           MergeLeadInvoke mlc = new MergeLeadInvoke();
                           p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                       } else {
                           
          

                           ByteArrayOutputStream bos1 = new ByteArrayOutputStream();

                           ZipOutputStream zos1 = new ZipOutputStream(bos1);
                           ZipEntry ze = new ZipEntry("Lead.csv");
                           zos1.putNextEntry(ze);

                           byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                           zos1.write(cabeceraLead1, 0, cabeceraLead1.length);

                           List<Lead> listaLeads = new ArrayList<Lead>();
                           
                           Util.Log(ini + "ini");
                           
                           Util.Log(fin + "fin");
                           
                           Util.Log(plstLeads.size() + "plstLeads.size()");

                       //    listaLeads = plstLeads.subList(ini, fin);
                           
                           Util.Log(listaLeads.size() + "listaLeads 1");
                           
                           intIdxLead = 0;

                           listaLeads = plstLeads.subList(ini, plstLeads.size());
                           
                           Util.Log(listaLeads.size() + "listaLeads 2");

                           for (int i = 0; i < listaLeads.size(); i++) {

                               Lead lead = listaLeads.get(intIdxLead);
                               intIdxLead++;


                               strTrama1 = lead.getLeadNumber() + "\n";

                               Util.Log(strTrama1 + "strTrama1  else");

                               byte[] bt = strTrama1.getBytes();

                               zos1.write(bt, 0, bt.length);


                           }
                           // Cerrar Entry
                           zos1.closeEntry();

                           ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                           zos1.putNextEntry(zef);
                           byte[] cabeceraLead = strTramaCabecera2.getBytes();
                           zos1.write(cabeceraLead, 0, cabeceraLead.length);

                           intIdxLead = 0;
                           for (int i = 0; i < listaLeads.size(); i++) {

                               Lead lead = listaLeads.get(intIdxLead);
                               intIdxLead++;

                               strTrama2 =
                                       lead.getLeadNumber() + "," + "DELETE" + "," +
                                       pstrResourceID + "\n";
                               strTrama3 =
                                       lead.getLeadNumber() + "," + "" + "," + lead.getResourceId() +
                                       "\n";


                               Util.Log(strTrama2 + "strTrama2 else");

                               Util.Log(strTrama3 + "strTrama3  else");


                               byte[] bt = strTrama2.getBytes();
                               byte[] bt2 = strTrama3.getBytes();


                               zos1.write(bt, 0, bt.length);
                               zos1.write(bt2, 0, bt2.length);


                           }
                           // Cerrar Entry
                           zos1.closeEntry();


                           ZipEntry zefe = new ZipEntry("Feedback_Lead_c.csv");
                           zos1.putNextEntry(zefe);

                           byte[] cabeceraFb = strTramaFeedback.getBytes();

                           zos1.write(cabeceraFb, 0, cabeceraFb.length);

                           intIdxLead = 0;
                           for (int i = 0; i < listaLeads.size(); i++) {

                               Lead lead = listaLeads.get(intIdxLead);
                               intIdxLead++;

                               strTrama4 =
                                       lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                       "," + lead.getResourceId() + "," +
                                       lead.getSup() + "," +
                                       lead.getJefeCanal() + "," + lead.getCanal() +
                                       "," + lead.getNomCpg() + "," + "1" + "," +
                                       "" + "," + "" + "," + "" + "\n";

                               strTrama5 =
                                       lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                       "," + pstrResourceID + "," +
                                       lead.getSup() + "," +
                                       lead.getJefeCanal() + "," + lead.getCanal() +
                                       "," + lead.getNomCpg() + "," + "0" + "," +
                                       "3" + "," + "12" + "," + "38" + "\n";

                               Util.Log(strTrama4 + "strTrama4 else");

                               Util.Log(strTrama5 + "strTrama5 else");

                               byte[] bt = strTrama4.getBytes();
                               byte[] bt2 = strTrama5.getBytes();


                               zos1.write(bt, 0, bt.length);
                               zos1.write(bt2, 0, bt2.length);


                           }
                           // Cerrar Entry
                           zos1.closeEntry();


                           String str =
                               client.Base64.byteArrayToBase64(bos1.toByteArray());
                           Util.Log(str + "str");
                           MergeLeadInvoke mlc = new MergeLeadInvoke();
                           p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                       }
                       ini = fin;

                       fin = ini + pivot;

                       cant = cant - pivot;

                   }
                   //  eliminarDataProcesada();


               } catch (Exception ex) {
                   Util.Log("[Error] : " + ex.getMessage());
                   FacesContext.getCurrentInstance().addMessage("",
                                                                new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                                 ex.getMessage(),
                                                                                 ""));
               }

      }
    
    public List<Lead> listLeadEj(String idCarga) {

          List<Lead> listaLeadsEj = new ArrayList<Lead>();

          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          String leadNumber = "";
          String ejecId = "";
          String fbNom = "";
          String ejecutivo = "";
          String supervisor = "";
          String jefCanal = "";
          String canal = "";
          String cpgNom = "";
          String fbIni = "";
          String tipGest = "";
          String grupGest = "";
          String detGest = "";


          try {
              String query =
                  "select lead_number LEAD_NUMBER, ejec_vent_id EJEC_VENT_ID, fb_nom FB_NOM, ejecutivo EJECUTIVO, supervisor SUPERVISOR, jef_canal JEF_CANAL, canal CANAL, cpg_nom CPG_NOM, fb_inicial FB_INICIAL, tip_gest TIP_GEST, grup_dest GRUP_GEST, det_gest DET_EST from SBP_SLS_TBL_REASIG_EV where id_sec = " +
                  idCarga;

              Util.Log(query + "QUERY");

              sbp.utils.Connection connection = new sbp.utils.Connection();

              conn = connection.getConnection();

              st = conn.prepareStatement(query);

              rs = st.executeQuery();

              while (rs.next()) {
                  Lead lead = new Lead();
                  leadNumber = rs.getString("LEAD_NUMBER");
                  ejecId = rs.getString("EJEC_VENT_ID");
                  fbNom = rs.getString("FB_NOM");
                  ejecutivo = rs.getString("EJECUTIVO");
                  supervisor = rs.getString("SUPERVISOR");
                  jefCanal = rs.getString("JEF_CANAL");
                  canal = rs.getString("CANAL");
                  cpgNom = rs.getString("CPG_NOM");
                  fbIni = rs.getString("FB_INICIAL");
                  tipGest = rs.getString("TIP_GEST");
                  grupGest = rs.getString("GRUP_GEST");
                  detGest = rs.getString("DET_EST");
                  

                  lead.setLeadNumber(leadNumber);
                  lead.setResourceId(ejecId);
                  lead.setLeadNumber(fbNom);
                  lead.setEjecutivo(ejecutivo);
                  lead.setSup(supervisor);
                  lead.setJefeCanal(jefCanal);
                  lead.setCanal(canal);
                  lead.setNomCpg(cpgNom);
              
                  listaLeadsEj.add(lead);

              }

          } catch (SQLException e) {
              Util.Log(e + "error");
          } finally {
              DbUtils.closeQuietly(rs);
              DbUtils.closeQuietly(st);
              DbUtils.closeQuietly(conn);
          }

          Util.Log(listaLeadsEj.size() + "LISTA LIST LEAD");
          return listaLeadsEj;
      }
    
    public void insertarDataReasigList(List<Lead> plstLeads,
                                         List<EjVentaFiltro> plstNuevoDestino) {
          
          ADFContext ctx = ADFContext.getCurrent();

          String jefCanal = ctx.getSessionScope().get("JefeCanal") == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();

          Util.Log(jefCanal + "JEFE CANAL");
          
          String nomCpg = ctx.getSessionScope().get("nomCpg") == null ? "" : ctx.getSessionScope().get("nomCpg").toString();

          String nomCan = ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
          
          String nomSup = ctx.getSessionScope().get("Super") == null ? "" : ctx.getSessionScope().get("Super").toString();
          
          Util.Log(plstLeads.size() + "TAMAÑO LISTA LEAD REA");

          Integer intIdxLead = 0;
          
          String idCarga = obtenerIdCarga();

          PreparedStatement prepareStatement = null;

          sbp.utils.Connection connection = new sbp.utils.Connection();
          Connection conn = null;

          for (EjVentaFiltro desti : plstNuevoDestino) {

              for (int i = 0; i < Integer.parseInt(desti.getCantidad()); i++) {

                  // Obtener lead
                  Lead lead = plstLeads.get(intIdxLead);
                  intIdxLead++;

                  try {


                      String query =
                          "BEGIN SBP_SLS_REASIG_EV_PKG.insert_datos(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

                      conn = connection.getConnection();
                      prepareStatement = conn.prepareStatement(query);
                      prepareStatement.setString(1, lead.getLeadNumber());
                      prepareStatement.setString(2, desti.getIdEjVenta());
                      prepareStatement.setString(3, idCarga);
                      prepareStatement.setString(4, lead.getLeadNumber());
                      prepareStatement.setString(5, desti.getEjVenta());
                      prepareStatement.setString(6, nomSup);
                      prepareStatement.setString(7, jefCanal);
                      prepareStatement.setString(8, nomCan);
                      prepareStatement.setString(9, nomCpg);
                      prepareStatement.setString(10, "1");
                      prepareStatement.setString(11, "3");
                      prepareStatement.setString(12, "12");
                      prepareStatement.setString(13, "38");

                      prepareStatement.execute();
                      prepareStatement.close();
                      conn.close();


                  } catch (SQLException e) {
                    e.printStackTrace();

                  } finally {
                      DbUtils.closeQuietly(prepareStatement);
                      DbUtils.closeQuietly(conn);
                  }

              }
          }
          
      }
    
    public String obtenerIdCarga() {

          ADFContext ctx = ADFContext.getCurrent();

          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          String idCarga = "";


          try {
              String query =
                  "select SBP_SLS_ID_REASIG_EV_S.nextval ID_CARGA from dual";

              Util.Log(query + "QUERY");

              sbp.utils.Connection connection = new sbp.utils.Connection();

              conn = connection.getConnection();

              st = conn.prepareStatement(query);

              rs = st.executeQuery();

              while (rs.next()) {
                  idCarga = rs.getString("ID_CARGA");
              }

          } catch (SQLException e) {
              Util.Log(e + "error");
          } finally {
              DbUtils.closeQuietly(rs);
              DbUtils.closeQuietly(st);
              DbUtils.closeQuietly(conn);
          }

          Util.Log(idCarga + "ID CARGA");
          ctx.getSessionScope().put("idCarga", idCarga);
          return idCarga;
      }

    public void setTxtCantLeadsTodos(RichInputText txtCantLeadsTodos) {
        this.txtCantLeadsTodos = txtCantLeadsTodos;
    }

    public RichInputText getTxtCantLeadsTodos() {
        return txtCantLeadsTodos;
    }

    public void aplicar(ActionEvent actionEvent) {
      
        if(txtCantLeadsTodos.getValue() == null){
                 
             FacesContext.getCurrentInstance().addMessage("",
                                                                      new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                                       "Debe ingresar una cantidad",
                                                                                       ""));
             } else {
            
             List<EjVentaFiltro> listFiltro = new ArrayList<EjVentaFiltro>();
                     
             listFiltro = listEj();
             
             Util.Log("listFiltro tamaño --> " + listFiltro.size());
             
             Row rw = null;

             CollectionModel _tablaCanal= (CollectionModel)tblEjec.getValue();
             
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
                         
                     rw.setAttribute("IdEjVenta", listEV.getIdEjVenta());  
                     rw.setAttribute("NomEjVenta", listEV.getEjVenta());      
                     rw.setAttribute("Cantidad", txtCantLeadsTodos.getValue().toString());
                 
                 }    
             }
         }

    public void setTxtEjventaActual(RichOutputLabel txtEjventaActual) {
        this.txtEjventaActual = txtEjventaActual;
    }

    public RichOutputLabel getTxtEjventaActual() {
        return txtEjventaActual;
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
          
          String idSup = ctx.getSessionScope().get("idSup") == null ? "" : ctx.getSessionScope().get("idSup").toString();
          
          String idEjVenta = ctx.getSessionScope().get("idEjventaQuitar") == null ? "" : ctx.getSessionScope().get("idEjventaQuitar").toString();
          
          String nomEjVenta = ctx.getSessionScope().get("nomEjVenta") == null ? "" : ctx.getSessionScope().get("nomEjVenta").toString();
          
          String cantLeadEVAct = ctx.getSessionScope().get("Cantidad") == null ? "" : ctx.getSessionScope().get("Cantidad").toString();
          
          String info = "Los leads a reasignar pertenecen a " + nomEjVenta + " y son la cantidad de " + cantLeadEVAct;
          
          poblarEjVenta(idSup, idEjVenta);
          
          mostrarDatos(cantLeadEVAct);
          
          txtEjventaActual.setValue(info);
          
      }
      
    public void limpiarTablaReasigSupEv() {

          PreparedStatement prepareStatement = null;


          sbp.utils.Connection connection = new sbp.utils.Connection();
          PreparedStatement st = null;
          Connection conn = null;


          String query = "truncate table SBP_SLS_LIST_EV_X_SUP_TEMP";


          try {

              conn = connection.getConnection();
              prepareStatement = conn.prepareStatement(query);
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
              Util.Log(e + "excepcion" + e.getMessage() + "Exception");
          } finally {
              DbUtils.closeQuietly(st);
              DbUtils.closeQuietly(conn);

          }

      }
      
    public void poblarEjVenta(String supId, String idEjVenta) {

          limpiarTablaReasigSupEv();

          ServiceBiPublisher canal = new ServiceBiPublisher();


          List<EjVenta> lista2 = new ArrayList<EjVenta>();
          lista2 = canal.reasigLeadSuEv(supId, idEjVenta);

          PreparedStatement prepareStatement = null;


          for (EjVenta ejVentaList : lista2) {

              sbp.utils.Connection connection = new sbp.utils.Connection();
              PreparedStatement st = null;
              Connection conn = null;


              String query =
                  "BEGIN SBP_SLS_SUP_EV_PKG.pr_insert_ej_venta(?,?,?,?,?,?); END;";


              try {

                  conn = connection.getConnection();
                  prepareStatement = conn.prepareStatement(query);
                  prepareStatement.setString(1, ejVentaList.getIdCanal());
                  prepareStatement.setString(2, ejVentaList.getCanal());
                  prepareStatement.setString(3, ejVentaList.getIdSup());
                  prepareStatement.setString(4, ejVentaList.getSup());
                  prepareStatement.setString(5, ejVentaList.getIdEjVenta());
                  prepareStatement.setString(6, ejVentaList.getEjVenta());

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
          }
      }
    
    public void mostrarDatos(String cantidad) {
                   
           ADFContext ctx = ADFContext.getCurrent();
           
           Integer cantLead = Integer.parseInt(cantidad);
           
           ctx.getSessionScope().put("Cantidad", cantLead);
       
           Integer cantSup = listEjecutivos().size();

           List<Canal> listEjVenta = new ArrayList<Canal>();

           listEjVenta = listEjecutivos();

           CollectionModel _tablaEjec = (CollectionModel)tblEjec.getValue();
           JUCtrlHierBinding _adfTableEjecBinding = (JUCtrlHierBinding)_tablaEjec.getWrappedData(); //devuelve el table binding
           DCIteratorBinding itEjec = _adfTableEjecBinding.getDCIteratorBinding();


           int b = 0;
           b = itEjec.getRowSetIterator().getRowCount();

           for (int i = 0; i < b; i++) {
               itEjec.getRowSetIterator().getRowAtRangeIndex(0).remove();
           }
           Row rw = null;

           for (Canal listadoEjec : listEjVenta) {

               rw = itEjec.getNavigatableRowIterator().createRow();
               itEjec.getNavigatableRowIterator().insertRow(rw);
               rw.setNewRowState(Row.STATUS_INITIALIZED);

               rw.setAttribute("IdCanal", listadoEjec.getCanalId());
               rw.setAttribute("NomCanal", listadoEjec.getCanal());
               rw.setAttribute("IdSup", listadoEjec.getIdSup());
               rw.setAttribute("NomSup", listadoEjec.getSup());
               rw.setAttribute("IdEjVenta", listadoEjec.getIdEjVenta());
               rw.setAttribute("Cantidad", cantLead/cantSup);
               rw.setAttribute("NomEjVenta", listadoEjec.getEjVenta());

           }

       }
    
    public List<Canal> listEjecutivos() {

          ADFContext ctx = ADFContext.getCurrent();

          String idCanal = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();


          List<Canal> listEjec = new ArrayList<Canal>();

          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          String canalId = "";
          String canal = "";
          String idSup = "";
          String sup = "";
          String idEjVenta = "";
          String ejVenta = "";

          try {
              String query =
                  "select id_canal ID_CANAL, nom_canal NOM_CANAL, id_sup ID_SUP, nom_sup NOM_SUP, id_ej_venta ID_EJ_VENTA, nom_ej_venta NOM_EJ_VENTA from SBP_SLS_LIST_EV_X_SUP_TEMP where id_canal = " +
                  idCanal;

              Util.Log(query + "QUERY");

              sbp.utils.Connection connection = new sbp.utils.Connection();

              conn = connection.getConnection();

              st = conn.prepareStatement(query);

              rs = st.executeQuery();

              while (rs.next()) {
                  Canal can = new Canal();
                  canalId = rs.getString("ID_CANAL");
                  canal = rs.getString("NOM_CANAL");
                  idSup = rs.getString("ID_SUP");
                  sup = rs.getString("NOM_SUP");
                  idEjVenta = rs.getString("ID_EJ_VENTA");
                  ejVenta = rs.getString("NOM_EJ_VENTA");


                  can.setCanalId(canalId);
                  can.setCanal(canal);
                  can.setIdSup(idSup);
                  can.setSup(sup);
                  can.setIdEjVenta(idEjVenta);
                  can.setEjVenta(ejVenta);
                  listEjec.add(can);

              }

          } catch (SQLException e) {
              Util.Log(e + "error");
          } finally {
              DbUtils.closeQuietly(rs);
              DbUtils.closeQuietly(st);
              DbUtils.closeQuietly(conn);
          }


          return listEjec;
      }

    public void setTblEjec(RichTable tblEjec) {
        this.tblEjec = tblEjec;
    }

    public RichTable getTblEjec() {
        return tblEjec;
    }
}

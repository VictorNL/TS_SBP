package view.backing;

import Client.MergeLeadInvoke;

import Dao.ImportActivityStatus;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jdbc.OracleTypes;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.BO.CargaSftp;

import view.Util;

public class SBP_SLS_CARGA_FEEDBACK {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText itUsrCar;
    private RichInputText itIntEje;
    private RichInputDate itFecIni;
    private RichInputDate itFecFin;
    private RichInputText itJobSbp;
    // Variables globales
       private String SBP_SLS_CARGA_FEEDBACK_EVENT = "SBP_SLS_CARGA_FEEDBACK_EVENT";
    private RichPopup pconfir;
    private RichOutputLabel olPopMsj;
    private RichTable tconsul;

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

    public void setItUsrCar(RichInputText itUsrCar) {
        this.itUsrCar = itUsrCar;
    }

    public RichInputText getItUsrCar() {
        return itUsrCar;
    }

    public void setItIntEje(RichInputText itIntEje) {
        this.itIntEje = itIntEje;
    }

    public RichInputText getItIntEje() {
        return itIntEje;
    }

    public void setItFecIni(RichInputDate itFecIni) {
        this.itFecIni = itFecIni;
    }

    public RichInputDate getItFecIni() {
        return itFecIni;
    }

    public void setItFecFin(RichInputDate itFecFin) {
        this.itFecFin = itFecFin;
    }

    public RichInputDate getItFecFin() {
        return itFecFin;
    }

    public void setItJobSbp(RichInputText itJobSbp) {
        this.itJobSbp = itJobSbp;
    }

    public RichInputText getItJobSbp() {
        return itJobSbp;
    }
    
    
    /* *************************************************************************
       * Eventos al iniciar pagina
       ************************************************************************* */

      public void onBeforePhase(PhaseEvent phaseEvent) {
          if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !this.isPostback()) {
              this.onLoad();
          }
      }
      
      private boolean isPostback() {
          return Boolean.TRUE.equals(this.resolveExpression("#{adfFacesContext.postback}"));
      }

      private Object resolveExpression(String expression) {
          FacesContext facesContext = FacesContext.getCurrentInstance();
          Application app = facesContext.getApplication();
          ExpressionFactory elFactory = app.getExpressionFactory();
          ELContext elContext = facesContext.getELContext();
          ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
          return valueExp.getValue(elContext);
      }

      private void onLoad() {
          try {
              this.itUsrCar.setValue((String)resolveExpression("#{viewScope.UserID2}"));
          } catch (Exception ex) {
              this.itUsrCar.setValue("anonimo");
              Util.Log(ex.getMessage());
          }
      }
      
    /* *************************************************************************
        * Eventos botones
        ************************************************************************* */


       public void cbDetenerFeedback_click(ActionEvent actionEvent) {
           try {
               ADFContext.getCurrent().getSessionScope().put(this.SBP_SLS_CARGA_FEEDBACK_EVENT, "cbDetenerFeedback_click");
               this.olPopMsj.setValue("Está seguro que desea detener la carga de feedback's");
               this.getPconfir().show(new RichPopup.PopupHints());
           } catch (Exception ex) {
               Util.Log("Exception :: " + ex.getMessage());
           }
       }

       public void cbBuscarCargas_click(ActionEvent actionEvent) {
           this.buscarCargas();
       }

    public void cbCargaFeedback_click(ActionEvent actionEvent) {
        try {

                if (Util.nvl(this.itIntEje, "").isEmpty()) {
                    Util.MostrarMensajeKO("Ingrese un intervalo de tiempo de ejecución en minutos");
                    return;
                }

                ADFContext.getCurrent().getSessionScope().put(this.SBP_SLS_CARGA_FEEDBACK_EVENT, "cbCargaFeedback_click");
                this.olPopMsj.setValue("Está seguro que desea iniciar la carga de feedback's");
                this.getPconfir().show(new RichPopup.PopupHints());
            } catch (Exception ex) {
                Util.Log("Exception :: " + ex.getMessage());
            }
    }

    public void setPconfir(RichPopup pconfir) {
        this.pconfir = pconfir;
    }

    public RichPopup getPconfir() {
        return pconfir;
    }

    public void setOlPopMsj(RichOutputLabel olPopMsj) {
        this.olPopMsj = olPopMsj;
    }

    public RichOutputLabel getOlPopMsj() {
        return olPopMsj;
    }
    
    private void buscarCargas(){

           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

           // Validaciones
           if (!Util.nvl(this.itFecIni, "").isEmpty() || 
               !Util.nvl(this.itFecFin, "").isEmpty()) {

               try {

                   Date di = sdf.parse(sdf.format(this.itFecIni.getValue()));
                   Date df = sdf.parse(sdf.format(this.itFecFin.getValue()));

                   if (df.before(di)) {
                       Util.MostrarMensajeKO("Ingrese un rango correcto de fechas");
                       return;
                   }
               } catch (ParseException ex) {
                   Util.MostrarMensajeKO("Ingrese un rango correcto de fechas");
                   return;
               } catch (Exception ex) {
                   Util.MostrarMensajeKO("Ingrese un rango correcto de fechas");
                   return;
               }
           }

           Connection cnx = null;
           CallableStatement st = null;
           ResultSet rs = null;

           try {

               String sQuery = "begin SBP_SLS_CARGA_MASIVA_PKG.pr_getCargas(?,?,?,?); end;";
               cnx = new sbp.utils.Connection().getConnection();
               st = cnx.prepareCall(sQuery);
               st.setString(1, !Util.nvl(this.itFecIni, "").isEmpty() ? sdf.format(this.itFecIni.getValue()) : "");
               st.setString(2, !Util.nvl(this.itFecFin, "").isEmpty() ? sdf.format(this.itFecFin.getValue()) : "");
               st.setString(3, Util.nvl(this.itJobSbp, ""));
               st.registerOutParameter(4, OracleTypes.CURSOR);
               st.execute();
               rs = (ResultSet)st.getObject(4);            
               
               // Obtener la tabla (pantalla)
               CollectionModel cm = (CollectionModel)this.tconsul.getValue();
               JUCtrlHierBinding hb = (JUCtrlHierBinding)cm.getWrappedData(); //devuelve el table binding
               DCIteratorBinding ib = hb.getDCIteratorBinding();

               // Eliminar data existente
               int t = ib.getRowSetIterator().getRowCount();
               for (int i = 0; i < t; i++) {
                   ib.getRowSetIterator().getRowAtRangeIndex(0).remove();
               }
               
               // Mostrar dato en la tabla (pantalla)
               Row r = null;
               if (rs != null) {
                   while (rs.next()) {
                       r = ib.getNavigatableRowIterator().createRow();
                       ib.getNavigatableRowIterator().insertRow(r);
                       r.setNewRowState(Row.STATUS_INITIALIZED);

                       r.setAttribute("FechaCargaS", rs.getString("FECHA_CARGA_S"));
                       r.setAttribute("DescripcionCarga", rs.getString("DESCRIPCION_CARGA"));
                       r.setAttribute("IdCargaSbp", rs.getString("ID_CARGA_SBP"));
                       r.setAttribute("UsuarioCarga", rs.getString("USUARIO_CARGA"));
                       r.setAttribute("Observaciones", rs.getString("OBSERVACIONES"));
                       
                       String sEstado = rs.getString("ESTADO");
                       //Util.Log("1::" + sEstado);
                       if (!sEstado.equalsIgnoreCase("Completed") && 
                           !sEstado.equalsIgnoreCase("Completed with errors")) {

                           if (rs.getString("ID_CARGA_SBP") != null && 
                              !rs.getString("ID_CARGA_SBP").equalsIgnoreCase("0")) {

                               ImportActivityStatus s = new MergeLeadInvoke().getImportActivityStatus(Util.DatosConexionSBP(), rs.getString("ID_CARGA_SBP"));

                               // Actualizar estado de carga
                               new CargaSftp().mergeCabecera(rs.getString("ID_CARGA_SBP"), 
                                                             s.getStatus(), 
                                                             Util.nvl(this.itUsrCar, ""), 
                                                             s.getLoadErrors().equalsIgnoreCase("0") ? "" : s.getLoadErrors());

                               sEstado = s.getStatus();
                           }
                       }

                       r.setAttribute("Estado", sEstado);

                   }
                   rs.close();
               }
               st.close();
               cnx.close();

           } catch (Exception ex) {
               Util.MostrarMensajeKO("Validar :: " + ex.getMessage());
           } finally {
               DbUtils.closeQuietly(rs);
               DbUtils.closeQuietly(st);
               DbUtils.closeQuietly(cnx);
           }
       }

    public void setTconsul(RichTable tconsul) {
        this.tconsul = tconsul;
    }

    public RichTable getTconsul() {
        return tconsul;
    }
    
    /* *************************************************************************
       * Eventos popup confirmacion
       ************************************************************************* */
      
      public void cbAceptar_click(ActionEvent actionEvent) {
          this.getPconfir().hide();

          try {
              // Session de objetos
              String strEvento = ADFContext.getCurrent().getSessionScope().get(this.SBP_SLS_CARGA_FEEDBACK_EVENT).toString();
              String strMensaje = "";

              if (strEvento.equalsIgnoreCase("cbCargaFeedback_click")) {
                  new CargaSftp().IniciarEnvioFeedback(Integer.parseInt(this.itIntEje.getValue().toString()));
                  strMensaje = "Carga iniciada";
              }

              if (strEvento.equalsIgnoreCase("cbDetenerFeedback_click")) {
                  new CargaSftp().DetenerEnvioFeedback();
                  strMensaje = "Carga detenida";
              }

              // Mostrar estado de carga
              this.buscarCargas();

              Util.MostrarMensajeOK(strMensaje);
          } catch (Exception ex) {
              Util.MostrarMensajeKO("Exception :: " + ex.getMessage());
          }
      }

      public void cbCancelar_click(ActionEvent actionEvent) {
          this.getPconfir().hide();
      }
}

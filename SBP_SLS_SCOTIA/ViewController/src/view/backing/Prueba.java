package view.backing;


import WsEjRep.ServiceBiPublisher;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import dao.Campania;
import dao.Canal;
import dao.CpgLeadContAccProdFb;
import dao.EjVenta;
import dao.EjVentaFiltro;
import dao.Lead;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;

import java.util.List;


import java.util.Vector;

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
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;


import view.DosificacionEjecutivoBO;
import view.Util;

public class Prueba {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText it1;
    private RichInputText it2;
    private RichCommandButton cb1;
    private RichInputText txtResource;
    private RichInputText txtCantidad;
    private RichInputText txtAsignado;
    private RichTable tblfb;
    private RichInputText txtTexto;
    private RichOutputLabel txtTexto1;
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
    private RichInputText txtCantLeads;
    private RichInputText txtCantAsignar;
    private RichCommandButton btnAsignar;
    private RichSelectOneChoice cboBin01;
    private RichSelectOneChoice cboBin02;
    private RichSelectOneChoice cboBin03;
    private RichTable tblEjVenta;

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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setTxtResource(RichInputText txtResource) {
        this.txtResource = txtResource;
    }

    public RichInputText getTxtResource() {
        return txtResource;
    }

    public void setTxtCantidad(RichInputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public RichInputText getTxtCantidad() {
        return txtCantidad;
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

 
    public void setTxtAsignado(RichInputText txtAsignado) {
        this.txtAsignado = txtAsignado;
    }

    public RichInputText getTxtAsignado() {
        return txtAsignado;
    }

    public void setTblfb(RichTable tblfb) {
        this.tblfb = tblfb;
    }

    public RichTable getTblfb() {
        return tblfb;
    }


    public void prueba(ActionEvent actionEvent) throws JSchException,
                                                       SftpException {        

            BufferedReader br = null;
            InputStream is =  obtenerTxt();
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
             String mystring = sb.toString();
             
             Util.Log("myString --> " + mystring);

       txtTexto.setValue(mystring);
       txtTexto1.setValue(mystring);
       
       
      //  listTxt();
       
    }
    
    
    public void obtenerIpCloud(){
        //OBTENER IP PUBLICO
            BufferedReader in = null;
            try {
               URL  whatismyip = new URL("http://checkip.amazonaws.com");
                in = new BufferedReader(new InputStreamReader(
                               whatismyip.openStream()));
                       String ip = in.readLine();
                       
                       Utils.Util.Log(ip + "ip");
            } catch (MalformedURLException e) {
                 e.printStackTrace(System.out);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            } finally {
                       if (in != null) {
                           try {
                               in.close();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                   }
    }
    
    public void sayHello(FacesContext context, OutputStream out) throws IOException, JSchException,
                                                  SftpException {
        
            InputStream is = obtenerTxt();
            
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while ((read = is.read(bytes)) != -1) {
                   out.write(bytes, 0, read);
                      }
            
        }
    
    public static void main(String[] args){
        
        String zack = null;
        
        String name = ((zack == null) ? "N/A" : zack);
        
        System.out.println(name);
        
           
      /*         InputStreamReader isr = null;
                   BufferedReader br = null;
                   InputStream is = 
                        new ByteArrayInputStream("Campaña de lanzamiento te devolvemos hasta un máximo S/100 realizando 2 compras con tu Tarjeta de Crédito".getBytes());
                   StringBuilder sb = new StringBuilder();
                   String content;
                   try {
                       isr = new InputStreamReader(is);
                       br = new BufferedReader(isr);
                       while ((content = br.readLine()) != null) {
                            sb.append(content);
                       }
                    } catch (IOException ioe) {
                            System.out.println("IO Exception occurred");
                            ioe.printStackTrace();  
                       }
                    String mystring = sb.toString();
                    System.out.println(mystring);
               
       */
           }
    
    public void imprimirTxt(){
        
        InputStreamReader isr = null;
            BufferedReader br = null;
            InputStream is = 
                 new ByteArrayInputStream("Campaña de lanzamiento te devolvemos hasta un máximo S/100 realizando 2 compras con tu Tarjeta de Crédito".getBytes());
            StringBuilder sb = new StringBuilder();
            String content;
            try {
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                while ((content = br.readLine()) != null) {
                     sb.append(content);
                }
             } catch (IOException ioe) {
                     System.out.println("IO Exception occurred");
                     ioe.printStackTrace();  
                }
             String mystring = sb.toString();
             System.out.println(mystring);
             
    }
    
    public InputStream obtenerTxt() throws JSchException, SftpException {

           String host = System.getProperty("http.proxyHost");
           String port = System.getProperty("http.proxyPort");

           JSch jsch = new JSch();
           Session session = null;
           
          /* session = jsch.getSession("ccmg-tVW", "sftp.us2.cloud.oracle.com", 22);
           session.setConfig("StrictHostKeyChecking", "no");
           session.setPassword("Evol2018");¨*/
           
           session = jsch.getSession("root", "191.98.147.250", 22);
           session.setConfig("StrictHostKeyChecking", "no");
           session.setPassword("evol2018");


           session.setProxy(new ProxyHTTP(host, 80));
           System.out.println("1");
           session.connect();
           System.out.println("2");
           Channel channel = session.openChannel("sftp");
           channel.connect();
           ChannelSftp sftpChannel = (ChannelSftp)channel;

           InputStream stream = sftpChannel.get("/tmp/SBPSpeech/VISA ORO_2.txt");
           System.out.println("3");
           return stream;
       }

    public void setTxtTexto(RichInputText txtTexto) {
        this.txtTexto = txtTexto;
    }

    public RichInputText getTxtTexto() {
        return txtTexto;
    }
    
    public void listTxt() throws JSchException, SftpException {
        
        String nomFile = "";

        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");

        JSch jsch = new JSch();
        Session session = null;
        
        session = jsch.getSession("usftp311", "190.116.5.24", 22);

        session.setConfig("StrictHostKeyChecking", "no");

        session.setPassword("uwy2FUmt");

        session.setProxy(new ProxyHTTP(host, 80));
        Utils.Util.Log("1");
        session.connect();
        Utils.Util.Log("2");
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        
        sftpChannel.cd("/");
        
        Vector filelist = sftpChannel.ls("/");
        
        for (int i = 0; i < filelist.size(); i++) {
                        
            nomFile = filelist.get(i).toString();
            Util.Log("nomFile ---> " + nomFile);
    
        }
       
    }


    public String getFileName() {
            
            String nomFile = "Campaña.pdf";

            return nomFile;
            
        }

    public void setTxtTexto1(RichOutputLabel txtTexto1) {
        this.txtTexto1 = txtTexto1;
    }

    public RichOutputLabel getTxtTexto1() {
        return txtTexto1;
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

    public void consultarLeads(ActionEvent actionEvent) {
        
           Util.Log("inicio consulta");
        
           ADFContext ctx = ADFContext.getCurrent();
          
           ServiceBiPublisher lead = new ServiceBiPublisher();
          
           String condLab = "";
           String supId = "";
           String bin01 = "";
           String decilMin = "";
           String decilMax = "";
           String canalId = "";
           String bin02 = "";
           String bin03 = "";
           String idCpg = "";
           String residencia = "";
           String edadMin = "";
           String propMin = "";
           String propMax = "";
           String edadMax = "";
           String contactabilidad = "";
           String estCivil = "";
           String flagClub = "";
           String genero = "";
           
           List<Lead> leadList = new ArrayList<Lead>();
           
           canalId = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
           
           supId = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
          
        
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
           
           idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
           
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
           
               edadMax = txtPropHasta.getValue().toString();
           
           } else {
               
               edadMax = "";
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
        Util.Log("inicio ejecuta ws");      
        
           String cantLead = lead.obtenerCantLeadXFiltroXSup(condLab, supId, bin01, decilMin, decilMax, 
                                                  canalId, bin02, bin03, idCpg, residencia, edadMin,
                                                  propMin, propMax, edadMax, contactabilidad, estCivil,
                                                  flagClub, genero);
           
        /*   leadList = lead.obtenerCantLeadXFiltro(condLab, supId, bin01, decilMin, decilMax, 
                                                  canalId, bin02, bin03, idCpg, residencia, edadMin,
                                                  propMin, propMax, edadMax, contactabilidad, estCivil,
                                                  flagClub, genero);*/
           Util.Log("fin ejecuta ws");                                                                  
    
        //   Util.Log("cantLeads list--> " + leadList.size());
           
           Util.Log("fin consulta");
           
         //  String cantLeads = leadList.size() + "";
           
           ctx.getSessionScope().put("cantLeadXFiltro", cantLead);
           
           txtCantLeads.setValue(cantLead);
           
           insertarDataDosifXCpg(idCpg, supId, canalId, cantLead);
           
           btnAsignar.setDisabled(false);
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
    
    public void insertarDataDosifXCpg(String cpgId, String supId, String idCan, String cant){
          
          int ofertas = Integer.parseInt(cant);
          int porcentaje = Integer.parseInt("0");
          
          Util.Log("inicio");

          String pausa = "0";

          String nameCpg = nomCpg(cpgId);


          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          
          try {

              String query = "merge into SBP_SLS_DOSIF_X_CPG_LEAD rdm\n" +
                  "    using (select " + supId + " sup, " + cpgId + " cpg, " +
                  idCan + " canal, " + ofertas + " cant, " +
                  porcentaje + " rep," + pausa +
                  " pausa_supervisor," + "'" + nameCpg + "'" + " nom_cpg from dual) mer\n" +
                  "    \n" +
                  "    on (rdm.id_sup = mer.sup and rdm.id_cpg = mer.cpg)\n" +
                  "    when matched then\n" +
                  "      update set rdm.id_canal = mer.canal,\n" +
                  "                 rdm.cantidad = mer.cant,\n" +
                  "                 rdm.porc_rep = mer.rep,\n" +
                  "                 rdm.pausa_supervisor = mer.pausa_supervisor,\n" +
                  "                 rdm.nom_cpg = mer.nom_cpg\n" +
                  "    when not matched then\n" +
                  "      insert\n" +
                  "        (id_sup, id_cpg, id_canal, porc_rep, cantidad, pausa_supervisor, nom_cpg)\n" +
                  "      values\n" +
                  "        (" + supId + "," + cpgId + "," + idCan + "," +
                  porcentaje + "," + ofertas + "," +
                  pausa + "," + "'" + nameCpg + "'" + ")";

              Util.Log(query + "query");

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

    public void setTxtCantLeads(RichInputText txtCantLeads) {
        this.txtCantLeads = txtCantLeads;
    }

    public RichInputText getTxtCantLeads() {
        return txtCantLeads;
    }

    public void setTxtCantAsignar(RichInputText txtCantAsignar) {
        this.txtCantAsignar = txtCantAsignar;
    }

    public RichInputText getTxtCantAsignar() {
        return txtCantAsignar;
    }
    
    public List<EjVentaFiltro> listEj(){
            
            String supId = "";
            String sup = "";
            String ejVenta = "";
            String idEjVenta = "";
            String cant = "";
            
            List<EjVentaFiltro> listFilt = new ArrayList<EjVentaFiltro>();
            
            DCBindingContainer binding = this.getDCBindingsContainer();
            DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsAsigEvXSupFilView1Iterator");

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
    
    
    public Integer cantValidarAsig(){
            
            int cant = 0;
            
            DCBindingContainer binding = this.getDCBindingsContainer();
            DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsAsigEvXSupFilView1Iterator");

            ViewObject vo = dcItteratorBindings.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);

            if (rsi.getRowCount() > 0) {
              
                Row currRow;

                while (rsi.hasNext()) {
                    
                    currRow = rsi.next();
                    
                    if(currRow.getAttribute("Cantidad") == null){
                        
                        cant += 0;
                    } else {
                        
                        cant += Integer.parseInt(currRow.getAttribute("Cantidad").toString());
                        
                    }

                    

 
                }

            }
            
        return cant;
        
        }


    public void aplicarCant(ActionEvent actionEvent) {
       
        if(txtCantAsignar.getValue() == null){
                 
             FacesContext.getCurrentInstance().addMessage("",
                                                          new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                                       "Debe ingresar una cantidad",
                                                                                       ""));
             } else {
        
             List<EjVentaFiltro> listFiltro = new ArrayList<EjVentaFiltro>();
                     
             listFiltro = listEj();
             
             Util.Log("listFiltro tamaño --> " + listFiltro.size());
             
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
                         
                     rw.setAttribute("IdSup", listEV.getSupId()); 
                     rw.setAttribute("IdEjVenta", listEV.getIdEjVenta()); 
                     rw.setAttribute("NomSup", listEV.getNomSup());     
                     rw.setAttribute("NomEjVenta", listEV.getEjVenta());      
                     rw.setAttribute("Cantidad", txtCantAsignar.getValue().toString());
                 
                 }      
               }
       
    }
    
    
    public void cantTotalAsignar() {
        
             List<EjVentaFiltro> listFiltro = new ArrayList<EjVentaFiltro>();
                     
             listFiltro = listEj();
             
             Util.Log("listFiltro tamaño --> " + listFiltro.size());
             
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

                     rw.setAttribute("Cantidad", txtCantAsignar.getValue().toString());
                 
                 }      
       
    }

    /** Autor: Fernan Palomino
         ** Fecha: 10/10/2018
         ** Notas: Asignacion de LEAD
         **      : - Valida que si ingresa 0 no debe hacer nada
         **      : - La asignacion no debe implicar a los ejecutivos que tiene 0 en cantidad
         * */
        public void asignarLeads(ActionEvent actionEvent) {

            try {

                ADFContext ctx = ADFContext.getCurrent();

                // Obtener datos de session
                String supervisor  = ctx.getSessionScope().get("Super")       == null ? "" : ctx.getSessionScope().get("Super").toString();
                String idJefeCanal = ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
                String jefeCanal   = ctx.getSessionScope().get("JefeCanal")   == null ? "" : ctx.getSessionScope().get("JefeCanal").toString();
                String idCpg       = ctx.getSessionScope().get("idCpg")       == null ? "" : ctx.getSessionScope().get("idCpg").toString();
                String canal       = ctx.getSessionScope().get("Canal")       == null ? "" : ctx.getSessionScope().get("Canal").toString();
                String idCanal     = ctx.getSessionScope().get("IdCan")       == null ? "" : ctx.getSessionScope().get("IdCan").toString();
                String idSup       = ctx.getSessionScope().get("SupId")       == null ? "" : ctx.getSessionScope().get("SupId").toString();

                Integer cantidad = Integer.parseInt(this.txtCantLeads.getValue().toString());

                int cantLeadVal = this.cantValidarAsig();

                Util.Log("cantLeadVal :: " + cantLeadVal);

                if (cantLeadVal == 0) {
                    Util.MostrarMensajeKO("Ingrese valores a asignar");
                }
                else if (cantLeadVal > cantidad) {
                    Util.MostrarMensajeKO("La Cantidad total no puede exceder la de leads disponibles");
                } 
                else {

                    ServiceBiPublisher lead = new ServiceBiPublisher();

                    String condLab         = Util.nvl(this.cboCondLab, "");
                    String supId           = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
                    String bin01           = Util.nvl(this.cboBin01, "");
                    String decilMin        = Util.nvl(this.txtDecilDesde, "");
                    String decilMax        = Util.nvl(this.txtDecilHasta, "");
                    String canalId         = ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
                    String bin02           = Util.nvl(this.cboBin02, "");
                    String bin03           = Util.nvl(this.cboBin03, "");
                    String residencia      = Util.nvl(this.cboRes, "");
                    String edadMin         = Util.nvl(this.txtEdadDesde, "");
                    String edadMax         = Util.nvl(this.txtEdadHasta, "");
                    String propMin         = Util.nvl(this.txtPropDesde, "");
                    String propMax         = Util.nvl(this.txtPropHasta, "");
                    String contactabilidad = Util.nvl(this.cboCont, "");
                    String estCivil        = Util.nvl(this.cboEstCivil, "");
                    String flagClub        = Util.nvl(this.ckCluSueldo, "");
                    String genero          = Util.nvl(this.cboGenero, "");

                    // Obtener los leads que se asignaran
                    List<Lead> leadList = new ArrayList<Lead>();
                    leadList = lead.obtenerCantLeadXFiltro(condLab, 
                                                           supId, 
                                                           bin01, 
                                                           decilMin, 
                                                           decilMax, 
                                                           canalId, 
                                                           bin02, 
                                                           bin03, 
                                                           idCpg, 
                                                           residencia, 
                                                           edadMin, 
                                                           propMin, 
                                                           propMax, 
                                                           edadMax, 
                                                           contactabilidad, 
                                                           estCivil, 
                                                           flagClub, 
                                                           genero, 
                                                           cantLeadVal + "");

                    Util.Log("Cantidad de leadList obtenidos :: " + leadList.size());

                    // Listar los ejecutivos en la lista
                    List<EjVentaFiltro> listFiltro = this.listEj();

                    // Dosificar
                    DosificacionEjecutivoBO.dosificarXFiltro(idCpg, 
                                                             idCanal, 
                                                             canal, 
                                                             jefeCanal, 
                                                             supervisor, 
                                                             idSup, 
                                                             idJefeCanal,
                                                             leadList,
                                                             listFiltro);

                    
                    Util.MostrarMensajeOK("Asignación Completada");
                    
                    // Mandar por hilo (Mejora)
                    List<Lead> leadListadoTabla = DosificacionEjecutivoBO.listLead(idCpg, idSup);
                    DosificacionEjecutivoBO.asignarLeadTxt(leadListadoTabla);

                }

            } catch (Exception ex) {
                Util.MostrarMensajeKO(ex.getMessage());
            }
                    
         borrarCant();

        }
    public void setBtnAsignar(RichCommandButton btnAsignar) {
        this.btnAsignar = btnAsignar;
    }

    public RichCommandButton getBtnAsignar() {
        return btnAsignar;
    }

    public void limpiar(ActionEvent actionEvent) {
        System.gc();
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

    public String createInsert1() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert1");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsert2() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert2");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public String createInsert3() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert3");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void setTblEjVenta(RichTable tblEjVenta) {
        this.tblEjVenta = tblEjVenta;
    }

    public RichTable getTblEjVenta() {
        return tblEjVenta;
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
          
          createInsert1();
          
          createInsert2();
          
          createInsert3();
      
          String user = (String) resolveExpression("#{viewScope.userId2}");
          
          String idCpg = (String) resolveExpression("#{viewScope.cpgId2}");
          
          ctx.getSessionScope().put("idCpg", idCpg);
          
          Util.Log(user + "USUARIO");
          
          Util.Log(idCpg + "CAMPAÑA");
          
          obtenerCanal(user);
          
          mostrarDatos(user);
          
          btnAsignar.setDisabled(true);
          
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
    
    public void mostrarDatos(String nomSup) {
          
          ServiceBiPublisher serv = new ServiceBiPublisher();

          List<EjVenta> listEjVenta = new ArrayList<EjVenta>();
          
          CollectionModel _tablaSup = (CollectionModel)tblEjVenta.getValue();
          JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
          DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();
          listEjVenta = serv.obtenerEvXSupFiltro(nomSup);
          
          Util.Log("listEjVenta --> " + listEjVenta.size());
          
          int b = 0;
          
          try{
              b = itSup.getRowSetIterator().getRowCount();
          } catch (Exception e) {
              Util.Log(e + "error");
          } 
          
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
    
    
    public List<CpgLeadContAccProdFb> listLead(){
        
        ADFContext ctx = ADFContext.getCurrent();
        
        List<CpgLeadContAccProdFb> listLead = new ArrayList<CpgLeadContAccProdFb>();
        
        String idCpg = ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();
        
        String supId = ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idLead = ""; 
        
        try {
            
            String query = "select id_lead ID_LEAD from sbp_sls_lead_dosif_x_user where ETAPA = 'N' and ID_CAMPANIA = " + 
                           idCpg + " and ID_SUPERVISOR = " + supId;
            
            Util.Log(query);
            
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();
            
            while (rs.next()) {
            
                CpgLeadContAccProdFb lead = new CpgLeadContAccProdFb();
                
                idLead = rs.getString("ID_LEAD"); 
                
                lead.setIdLead(Long.parseLong(idLead));
                
                listLead.add(lead);
                
            }
            
            Util.Log("tamaño listLead --> " + listLead.size());
            
        } catch (SQLException e) {
                  Util.Log(e + "error");
              } finally {
                  DbUtils.closeQuietly(rs);
                  DbUtils.closeQuietly(st);
                  DbUtils.closeQuietly(conn);
              }

        return listLead;
    }
    
    public void borrarCant() {
    
        List<EjVentaFiltro> listFiltro = new ArrayList<EjVentaFiltro>();
                
        listFiltro = listEjBorra();
        
        Util.Log("listFiltro tamaño --> " + listFiltro.size());
        
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

                rw.setAttribute("IdSup", listEV.getSupId());
                rw.setAttribute("IdEjVenta", listEV.getIdEjVenta());
                rw.setAttribute("NomSup", listEV.getNomSup());
                rw.setAttribute("NomEjVenta", listEV.getEjVenta());
                rw.setAttribute("Cantidad", "");
            
            }      
          
    }
    
    
    public List<EjVentaFiltro> listEjBorra(){
        
        String supId = "";
        String sup = "";
        String ejVenta = "";
        String idEjVenta = "";
        String cant = "";
        
        List<EjVentaFiltro> listFilt = new ArrayList<EjVentaFiltro>();
        
        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsAsigEvXSupFilView1Iterator");

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
                cant = "";
                
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


   
      
}

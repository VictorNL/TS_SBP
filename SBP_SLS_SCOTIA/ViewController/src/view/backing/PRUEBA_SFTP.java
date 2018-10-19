package view.backing;

import WsEjRep.ServiceBiPublisher;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import dao.Canal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.component.rich.nav.RichGoLink;

import view.Util;

import oracle.adf.model.BindingContext;
import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import view.BO.CargaSftp;

public class PRUEBA_SFTP {
    private RichForm f1;
    private RichDocument d1;
    private RichCommandButton cb1;
    private RichInputText txtPrueba;
    private RichGoLink glTelf;
    private RichInputText txtTelf01;
    static AtomicBoolean status = new AtomicBoolean(false);
    static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private RichInputText txtHora;
    private String PRUEBA_SFTP = "PRUEBA_SFTP_EVENT";


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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void mostrarInfoSftp(ActionEvent actionEvent) throws JSchException,
                                                                SftpException {
        
        String nomBin = "osc";
        
        String prueba = speechProd2(nomBin);
        
        txtPrueba.setValue(prueba);
      
    }
    
    public InputStream obtenerTxt2(String nomBin) throws JSchException,
                                                         SftpException {
        
        Util.Log("nomBin -- > " + nomBin);

        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");

        JSch jsch = new JSch();
        Session session = null;
        
        session = jsch.getSession("usftp572", "sftp.scotiabank.com.pe", 22);

        session.setConfig("StrictHostKeyChecking", "no");

        session.setPassword("JwsgC5ka");

        session.setProxy(new ProxyHTTP(host, 80));
        System.out.println("1");
        session.connect();
        System.out.println("2");
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp)channel;

       
        //InputStream stream = sftpChannel.get("/nfshome/sbpftp04/usftp572/IN/" + nomBin + ".csv");
        InputStream stream = sftpChannel.get("/IN/" + nomBin + ".csv");
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
    
    public void setTxtPrueba(RichInputText txtPrueba) {
        this.txtPrueba = txtPrueba;
    }

    public RichInputText getTxtPrueba() {
        return txtPrueba;
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
        
      //  obtenerCanal("Edgar.Ramos");
        
      final Date currentTimeFecGest = new Date();

      final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

      // Give it to me in GMT time.
      sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
      
      String fecEjecucion = sdfFecGest.format(currentTimeFecGest);
      
      Util.Log("fecEjecucion -- > " + fecEjecucion);
      
        
        txtTelf01.setValue("943519883");
        
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
        Util.Log("inicio reporte obt canal");
        lista2 = canal.obtenerCanal(userName);
        Util.Log("fin reporte obt canal");
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

    public void timer(ActionEvent actionEvent) {
        
        final Date currentTimeFecGest = new Date();

        //final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        final SimpleDateFormat sdfFecGest = new SimpleDateFormat("ss");

        // Give it to me in GMT time.
        sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
       
        String fecEjecucion = sdfFecGest.format(currentTimeFecGest);
        
        Util.Log("fecEjecucion --> " + fecEjecucion);
        
        String hora = txtHora.getValue().toString();
        
        Util.Log("hora -- > " + hora);
        
        ejecutaHilo(hora);
    }

    public void setTxtHora(RichInputText txtHora) {
        this.txtHora = txtHora;
    }

    public RichInputText getTxtHora() {
        return txtHora;
    }

    public void detenerProceso(ActionEvent actionEvent) {
        
        ADFContext.getCurrent().getSessionScope().put(this.PRUEBA_SFTP, "cbDetenerFeedback_click");
       
        String strEvento = ADFContext.getCurrent().getSessionScope().get(this.PRUEBA_SFTP).toString();
        
        Util.Log("strEvento -- > " + strEvento);
        
        String strMensaje = "";
        
        if (strEvento.equalsIgnoreCase("cbDetenerFeedback_click")) {
            new CargaSftp().DetenerEnvioFeedback();
            strMensaje = "Carga detenida";
        }
    }


    public class TaskRunST implements Runnable {
        
        String horaActual = "";
        
       public TaskRunST(String horaActual) {
            
                this.horaActual = horaActual;
                
            }

        public void run() throws RuntimeException{}

    }


    public void ejecutaHilo(String horaActual){
        
        Thread th = null;
        
        final long timeInterval = 1000;
        
        try{
            th = new Thread(new TaskRunST(horaActual) {
                
            @Override
            public void run() throws RuntimeException  {
                
            while (true) {
                
                final Date currentTimeFecGest = new Date();

                final SimpleDateFormat sdfFecGest = new SimpleDateFormat("ss");
                
                //final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

                // Give it to me in GMT time.
                sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
                
                String fecEjecucion = sdfFecGest.format(currentTimeFecGest);
                
                Util.Log("horaActual --> " + horaActual);
                
                Util.Log("fecEjecucion --> " + fecEjecucion);
                
            if(horaActual.equalsIgnoreCase(fecEjecucion)){
                
                Util.Log("se ejecutó a las  " + horaActual + " coincidió a las " + fecEjecucion);
                
            }
        
            try {
                
            Thread.sleep(timeInterval);
                
            } catch (InterruptedException e) {
                
            e.printStackTrace();
            }
            }
           
        }
                
        });
        
        th.start();  
            th.stop();

        }catch(Exception e){
        System.out.println(e);
        }
    }

  
    public static void main(String [ ] args) { 
        
        final Date currentTimeFecGest = new Date();

        final SimpleDateFormat sdfFecGest = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // Give it to me in GMT time.
        sdfFecGest.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        
        String fecEjecucion = sdfFecGest.format(currentTimeFecGest);
        
        Util.Log("fecEjecucion -- > " + fecEjecucion);
     
     
     //ejecutar url localmente
    /*    try {
          Desktop desktop = java.awt.Desktop.getDesktop();
          URI oURL = new URI("sip:943519883");
          desktop.browse(oURL);
        } catch (Exception e) {
          e.printStackTrace();
        }*/
        
    
    }
      

    public void setGlTelf(RichGoLink glTelf) {
        this.glTelf = glTelf;
    }

    public RichGoLink getGlTelf() {
        return glTelf;
    }
    
   
    public String getLinkWithParams() {
        
            // get the binding container
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
     
     
            // get an ADF attributevalue from the ADF page definitions
            AttributeBinding attr = (AttributeBinding) bindings.getControlBinding("Telefono01");
            String valLastName = txtTelf01.getValue().toString();// (String) attr.getInputValue();
            
                
            String base = "sip:"+valLastName;
            Util.Log("base --> " + base);
            return base;
        }

    public void setTxtTelf01(RichInputText txtTelf01) {
        this.txtTelf01 = txtTelf01;
    }

    public RichInputText getTxtTelf01() {
        return txtTelf01;
    }
    
    public void timerStopped() {
        if (status.getAndSet(false)) {
            scheduler.shutdown();
            scheduler = Executors.newScheduledThreadPool(1);
    
        } else {
            addFacesInformationMessage("ERROR","Timer no esta iniciado");
        }
    }
    
    public static void addFacesInformationMessage(String type, String msg) {
        FacesContext ctx = getFacesContext();
        if(type.equals("INFO")){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
            ctx.addMessage(null, fm);
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
            ctx.addMessage(null, fm);
        }
    }  
    
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}

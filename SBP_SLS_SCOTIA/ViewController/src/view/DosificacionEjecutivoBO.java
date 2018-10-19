package view;

import Client.MergeLeadInvoke;

import Dao.SimplifiedImportActivity;

import WsEjRep.ServiceBiPublisher;
import client.ImportLeadResource;

import cliente.ClientBiPublisher;

import dao.Campania;
import dao.CpgLeadContAccProdFb;
import dao.Dosificador;
import dao.DosificadorLista;
import dao.EjVenta;
import dao.EjVentaFiltro;
import dao.Lead;
import dao.LeadRandom;
import dao.lista.ListaLead;

import java.io.ByteArrayOutputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.share.ADFContext;

import oracle.jdbc.OracleTypes;
import org.apache.commons.dbutils.DbUtils;

public class DosificacionEjecutivoBO {
    
    public DosificacionEjecutivoBO() {
        super();
    }
    
    public static List<CpgLeadContAccProdFb> getLead(String idLead) {
        
        List<CpgLeadContAccProdFb> list = new ArrayList<CpgLeadContAccProdFb>();
    
        list = infoLead(idLead);
        
        Util.Log("tamaño lista --> " + list.size());
        
        return list;
    }
    
    public static List<CpgLeadContAccProdFb> infoLead(String idLead){
        
        List<CpgLeadContAccProdFb> opp = new ArrayList<CpgLeadContAccProdFb>();
        
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;         
        
        String nomEmpresa = "";
        String nombre = "";
        String apellidoPaterno = "";
        String paisEmp = "";
        String deptEmp = "";
        String provinciaEmp = "";
        String distritoEmp = "";
        String codCpg = "";
        String cantOf = "";
        String periodCpg = "";
        String segCpg = "";
        String activo = "";
        String codProd = "";
        String porcAsig = "";
        String fecIni = "";
        String fecFin = "";
        String leadName = "";
        String monedaLead = "";
        String producto = "";
        String montoMin = "";
        String montoMax = "";
        String plazoMin = "";
        String plazoMax = "";
        String teaMin = "";
        String teaMax = "";
        String tceaMin = "";
        String tceaMax = "";
        String tcNombrebin1 = "";
        String tcPct1 = "";
        String tcTea1 = "";
        String tcNombrebin2 = "";
        String tcPct2 = "";
        String tcTea2 = "";
        String tcNombrebin3 = "";
        String tcPct3 = "";
        String tcTea3 = "";
        String tcNombrebin4 = "";
        String tcPct4 = "";
        String tcTea4 = "";
        String email = "";
        String sexo = "";
        String dni = "";
        String tipoDoc = "";
        String apellidoMaterno = "";
        String segundoNombre = "";
        String edad = "";
        String telf01 = "";
        String telf02 = "";
        String telf03 = "";
        String cel01 = "";
        String cel02 = "";
        String cel03 = "";
        String fecNac = "";
        String banco1DeuRcctc = "";
        String banco1DeudaRccpp = "";
        String banco1LinRcctc = "";
        String banco1NombreRccpp = "";
        String banco1NombreRcctc = "";
        String banco2DeuRcctc = "";
        String banco2DeudaRccpp = "";
        String banco2LinRcctc = "";
        String banco2NombreRccpp = "";
        String banco2NombreRcctc = "";
        String baficaedpdeuRcctc = "";
        String baficaedpedpLinRcctc = "";
        String sl01nom = "";
        String tipZonaCourier = "";
        String flagAut = "";
        String flagClubSueldo = "";
        String pais = "";
        String provincia = "";
        String departamento = "";
        String distrito = "";
        String nomCpg = "";
        String direccionAd = "";
        String dirAd = "";
        String leadId = "";
        String cuota = "";
        String propension = "";
        String decil = "";
        String montoProd = "";
        String estadoCivil = "";
        String ruc = "";
        String flujo = "";
        String promNueva = "";
        
        try {
            
          String query =
              "select nvl(nom_empresa , '-')     NOM_EMPRESA,"    +
            "nvl(nombre , '-')                 NOMBRE,"                +
            "nvl(apellido_paterno , '-')       APELLIDO_PATERNO,"      +
            "nvl(pais_emp , '-')               PAIS_EMP,"              +
            "nvl(dept_emp , '-')               DEPT_EMP,"              +
            "nvl(provincia_emp , '-')          PROVINCIA_EMP,"         +
            "nvl(distrito_emp , '-')           DISTRITO_EMP,"          +
            "nvl(cod_cpg , '-')                COD_CPG,"               +
            "nvl(cant_of , '-')                CANT_OF,"               +
            "nvl(period_cpg , '-')             PERIOD_CPG,"            +
            "nvl(seg_cpg , '-')                        SEG_CPG,"               +
            "nvl(activo , '-')                         ACTIVO,"                +
            "nvl(cod_prod , '-')        COD_PROD,"              +
            "nvl(porc_asig , '-')        PORC_ASIG,"             +
            "nvl(fec_ini , '-')        FEC_INI,"               +
            "nvl(fec_fin , '-')        FEC_FIN,"               +
            "nvl(lead_name , '-')        LEAD_NAME,"             +
            "nvl(moneda_lead , '-')        MONEDA_LEAD,"           +
            "nvl(producto , '-')        PRODUCTO,"              +
            "nvl(monto_min , '-')        MONTO_MIN,"             +
            "nvl(monto_max , '-')        MONTO_MAX,"             +
            "nvl(plazo_min , '-')        PLAZO_MIN,"             +
            "nvl(plazo_max , '-')        PLAZO_MAX,"             +
            "nvl(tea_min , '-')        TEA_MIN,"               +
            "nvl(tea_max , '-')        TEA_MAX,"               +
            "nvl(tcea_min , '-')        TCEA_MIN,"              +
            "nvl(tcea_max , '-')        TCEA_MAX,"              +
            "nvl(tc_nombrebin1 , '-')        TC_NOMBREBIN1,"         +
            "nvl(tc_pct1 , '-')        TC_PCT1,"               +
            "nvl(tc_tea1 , '-')        TC_TEA1,"               +
            "nvl(tc_nombrebin2 , '-')        TC_NOMBREBIN2,"         +
            "nvl(tc_pct2 , '-')        TC_PCT2,"               +
            "nvl(tc_tea2 , '-')        TC_TEA2,"               +
            "nvl(tc_nombrebin3 , '-')        TC_NOMBREBIN3,"         +
            "nvl(tc_pct3 , '-')        TC_PCT3,"               +
            "nvl(tc_tea3 , '-')        TC_TEA3,"               +
            "nvl(tc_nombrebin4 , '-')        TC_NOMBREBIN4,"         +
            "nvl(tc_pct4 , '-')        TC_PCT4,"               +
            "nvl(tc_tea4 , '-')        TC_TEA4,"               +
            "nvl(email , '-')        EMAIL,"                 +
            "nvl(sexo , '-')        SEXO,"                  +
            "nvl(dni , '-')        DNI,"                   +
            "nvl(tipo_doc , '-')               TIPO_DOC,"              +
            "nvl(apellido_materno , '-')       APELLIDO_MATERNO,"      +
            "nvl(segundo_nombre , '-')         SEGUNDO_NOMBRE,"        +
            "nvl(edad , '-')                   EDAD,"                  +
            "nvl(telf01 , '-')                 TELF01,"                +
            "nvl(telf02 , '-')                 TELF02,"                +
            "nvl(telf03 , '-')                 TELF03,"                +
            "nvl(cel01 , '-')                  CEL01,"                 +
            "nvl(cel02 , '-')                  CEL02,"                 +
            "nvl(cel03 , '-')                  CEL03,"                 +
            "nvl(fec_nac , '-')                FEC_NAC,"               +
            "nvl(banco1_deu_rcctc , '-')       BANCO1_DEU_RCCTC,"      +
            "nvl(banco1_deuda_rccpp , '-')     BANCO1_DEUDA_RCCPP,"    +
            "nvl(banco1_lin_rcctc , '-')       BANCO1_LIN_RCCTC,"      +
            "nvl(banco1_nombre_rccpp , '-')    BANCO1_NOMBRE_RCCPP,"   +
            "nvl(banco1_nombre_rcctc , '-')    BANCO1_NOMBRE_RCCTC,"   +
            "nvl(banco2_deu_rcctc , '-')       BANCO2_DEU_RCCTC,"      +
            "nvl(banco2_deuda_rccpp , '-')     BANCO2_DEUDA_RCCPP,"    +
            "nvl(banco2_lin_rcctc , '-')       BANCO2_LIN_RCCTC,"      +
            "nvl(banco2_nombre_rccpp , '-')    BANCO2_NOMBRE_RCCPP,"   +
            "nvl(banco2_nombre_rcctc , '-')    BANCO2_NOMBRE_RCCTC,"   +
            "nvl(baficaedpdeu_rcctc , '-')     BAFICAEDPDEU_RCCTC,"    +
            "nvl(baficaedpedp_lin_rcctc , '-') BAFICAEDPEDP_LIN_RCCTC,"+
            "nvl(sl01nom , '-')                SL01NOM,"               +
            "nvl(tip_zona_courier , '-')       TIP_ZONA_COURIER,"      +
            "nvl(flag_aut , '-')               FLAG_AUT,"              +
            "nvl(flag_club_sueldo , '-')       FLAG_CLUB_SUELDO,"      +
            "nvl(pais , '-')                   PAIS,"                  +
            "nvl(provincia , '-')              PROVINCIA,"             +
            "nvl(departamento , '-')           DEPARTAMENTO,"          +
            "nvl(distrito , '-')               DISTRITO,"              +
            "nvl(nom_cpg , '-')                NOM_CPG,"               +
            "nvl(direccion_ad , '-')           DIRECCION_AD,"          +
            "nvl(dir_ad , '-')                 DIR_AD,"                +
            "nvl(id_lead , '-')                ID_LEAD,"               +
            "nvl(cuota , '-')                  CUOTA,"                 +
            "nvl(propension , '-')             PROPENSION,"            +
            "nvl(decil , '-')                  DECIL,"                 +
            "nvl(monto_prod , '-')             MONTO_PROD,"            +
            "nvl(estado_civil , '-')           ESTADO_CIVIL,"          +
            "nvl(ruc , '-')                    RUC,"                   +
            "nvl(flujo , '-')                  FLUJO,"                 +
            "nvl(prom_nueva , '-')             PROM_NUEVA"             +
          " from sbp_sls_info_lead where id_lead = " + idLead; 
            
            Util.Log(query);
            
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();
            
            while (rs.next()) {
            
                Util.Log("1");
                
            CpgLeadContAccProdFb listLead = new CpgLeadContAccProdFb();  
             
                nomEmpresa = rs.getString("NOM_EMPRESA");
                nombre = rs.getString("NOMBRE");
                apellidoPaterno = rs.getString("APELLIDO_PATERNO");
                paisEmp = rs.getString("PAIS_EMP");
                deptEmp = rs.getString("DEPT_EMP");
                provinciaEmp = rs.getString("PROVINCIA_EMP");
                distritoEmp = rs.getString("DISTRITO_EMP");
                codCpg = rs.getString("COD_CPG");
                cantOf = rs.getString("CANT_OF");
                periodCpg = rs.getString("PERIOD_CPG");
                segCpg = rs.getString("SEG_CPG");
                activo = rs.getString("ACTIVO");
                codProd = rs.getString("COD_PROD");
                porcAsig = rs.getString("PORC_ASIG");
                fecIni = rs.getString("FEC_INI");
                fecFin = rs.getString("FEC_FIN");
                leadName = rs.getString("LEAD_NAME");
                monedaLead = rs.getString("MONEDA_LEAD");
                producto = rs.getString("PRODUCTO");
                montoMin = rs.getString("MONTO_MIN");
                montoMax = rs.getString("MONTO_MAX");
                plazoMin = rs.getString("PLAZO_MIN");
                plazoMax = rs.getString("PLAZO_MAX");
                teaMin = rs.getString("TEA_MIN");
                teaMax = rs.getString("TEA_MAX");
                tceaMin = rs.getString("TCEA_MIN");
                tceaMax = rs.getString("TCEA_MAX");
                tcNombrebin1 = rs.getString("TC_NOMBREBIN1");
                tcPct1 = rs.getString("TC_PCT1");
                tcTea1 = rs.getString("TC_TEA1");
                tcNombrebin2 = rs.getString("TC_NOMBREBIN2");
                tcPct2 = rs.getString("TC_PCT2");
                tcTea2 = rs.getString("TC_TEA2");
                tcNombrebin3 = rs.getString("TC_NOMBREBIN3");
                tcPct3 = rs.getString("TC_PCT3");
                tcTea3 = rs.getString("TC_TEA3");
                tcNombrebin4 = rs.getString("TC_NOMBREBIN4");
                tcPct4 = rs.getString("TC_PCT4");
                tcTea4 = rs.getString("TC_TEA4");
                email = rs.getString("EMAIL");
                sexo = rs.getString("SEXO");
                dni = rs.getString("DNI");
                tipoDoc = rs.getString("TIPO_DOC");
                apellidoMaterno = rs.getString("APELLIDO_MATERNO");
                segundoNombre = rs.getString("SEGUNDO_NOMBRE");
                edad = rs.getString("EDAD");
                telf01 = rs.getString("TELF01");
                telf02 = rs.getString("TELF02");
                telf03 = rs.getString("TELF03");
                cel01 = rs.getString("CEL01");
                cel02 = rs.getString("CEL02");
                cel03 = rs.getString("CEL03");
                fecNac = rs.getString("FEC_NAC");
                banco1DeuRcctc = rs.getString("BANCO1_DEU_RCCTC");
                banco1DeudaRccpp = rs.getString("BANCO1_DEUDA_RCCPP");
                banco1LinRcctc = rs.getString("BANCO1_LIN_RCCTC");
                banco1NombreRccpp = rs.getString("BANCO1_NOMBRE_RCCPP");
                banco1NombreRcctc = rs.getString("BANCO1_NOMBRE_RCCTC");
                banco2DeuRcctc = rs.getString("BANCO2_DEU_RCCTC");
                banco2DeudaRccpp = rs.getString("BANCO2_DEUDA_RCCPP");
                banco2LinRcctc = rs.getString("BANCO2_LIN_RCCTC");
                banco2NombreRccpp = rs.getString("BANCO2_NOMBRE_RCCPP");
                banco2NombreRcctc = rs.getString("BANCO2_NOMBRE_RCCTC");
                baficaedpdeuRcctc = rs.getString("BAFICAEDPDEU_RCCTC");
                baficaedpedpLinRcctc = rs.getString("BAFICAEDPEDP_LIN_RCCTC");
                sl01nom = rs.getString("SL01NOM");
                tipZonaCourier = rs.getString("TIP_ZONA_COURIER");
                flagAut = rs.getString("FLAG_AUT");
                flagClubSueldo = rs.getString("FLAG_CLUB_SUELDO");
                pais = rs.getString("PAIS");
                provincia = rs.getString("PROVINCIA");
                departamento = rs.getString("DEPARTAMENTO");
                distrito = rs.getString("DISTRITO");
                nomCpg = rs.getString("NOM_CPG");
                direccionAd = rs.getString("DIRECCION_AD");
                dirAd = rs.getString("DIR_AD");
                leadId = rs.getString("ID_LEAD");
                cuota = rs.getString("CUOTA");
                propension = rs.getString("PROPENSION");
                decil = rs.getString("DECIL");
                montoProd = rs.getString("MONTO_PROD");
                estadoCivil = rs.getString("ESTADO_CIVIL");
                ruc = rs.getString("RUC");
                flujo = rs.getString("FLUJO");
                promNueva = rs.getString("PROM_NUEVA");
                
                listLead.setOrganizationName(nomEmpresa);
                listLead.setNombre(nombre);
                listLead.setApellidoPaterno(apellidoPaterno);
                listLead.setPaisEmp(paisEmp);
                listLead.setDeptEmp(deptEmp);
                listLead.setProvEmp(provinciaEmp);
                listLead.setDistEmp(distritoEmp);
                Util.Log("codCpg --> " + codCpg);
                listLead.setIdCpg(Long.parseLong(codCpg));
                listLead.setCantOfertas(cantOf);
                listLead.setPeriodoCpg(periodCpg);
                listLead.setSegmCpg(segCpg);
                listLead.setActivoCpg(activo);
                listLead.setCodProd(codProd);
                listLead.setPorcAsig(porcAsig);
                listLead.setFecIni(fecIni);
                listLead.setFecFin(fecFin);
                listLead.setNomLead(leadName);
                listLead.setMonLead(monedaLead);
                listLead.setNomProd(producto);
                listLead.setMontoMinimo(montoMin);
                listLead.setMontoMaximo(montoMax);
                listLead.setPlazoMinimo(plazoMin);
                listLead.setPlazoMaximo(plazoMax);
                listLead.setTeaMin(teaMin);
                listLead.setTeaMax(teaMax);
                listLead.setTceaMin(tceaMin);
                listLead.setTceaMax(tceaMax);
                listLead.setTcNombreBIN1(tcNombrebin1);
                listLead.setTcPct(tcPct1);
                listLead.setTcTea1(tcTea1);
                listLead.setTcNombreBIN2(tcNombrebin2);
                listLead.setTcPct2(tcPct2);
                listLead.setTcTea2(tcTea2);
                listLead.setTcNombreBIN3(tcNombrebin3);
                listLead.setTcPct3(tcPct3);
                listLead.setTcTea3(tcTea3);
                listLead.setTcNombreBIN4(tcNombrebin4);
                listLead.setTcPct4(tcPct4);
                listLead.setTcTea4(tcTea4);
                listLead.setEmail(email);
                listLead.setGenero(sexo);
                listLead.setNumeroDni(dni);
                listLead.setTipoDocumento(tipoDoc);
                listLead.setApellidoMaterno(apellidoMaterno);
                listLead.setSegundoNombre(segundoNombre);
                listLead.setEdad(edad);
                listLead.setTelefono01(telf01);
                listLead.setTelefono02(telf02);
                listLead.setTelefono03(telf03);
                listLead.setCelular01(cel01);
                listLead.setCelular02(cel02);
                listLead.setCelular03(cel03);
                listLead.setFechaNacimiento(fecNac);
                listLead.setBanco1DEURccTC(banco1DeuRcctc);
                listLead.setBanco1DeudaRccPP(banco1DeudaRccpp);
                listLead.setBanco1LINRccTC(banco1LinRcctc);
                listLead.setBanco1NombreRccPP(banco1NombreRccpp);
                listLead.setBanco1NombreRccTC(banco1NombreRcctc);
                listLead.setBanco2DEURccTC(banco2DeuRcctc);
                listLead.setBanco2DeudaRccPP(banco2DeudaRccpp);
                listLead.setBanco2LINRccTc(banco2LinRcctc);
                listLead.setBanco2NombreRccPP(banco2NombreRccpp);
                listLead.setBanco2NombreRccTC(banco2NombreRcctc);
                listLead.setBaFiCaEDPDEURccTC(baficaedpdeuRcctc);
                listLead.setBaFiCaEDPEDPLINRccTC(baficaedpedpLinRcctc);
                Util.Log("5");
                listLead.setBaFiCaEDPDEURccTC("-");
                Util.Log("6");
                listLead.setSl01Nom(sl01nom);
                Util.Log("7");
                listLead.setTipoZonaCourier(tipZonaCourier);
                Util.Log("8");
                listLead.setFlagautorizacion(flagAut);
                Util.Log("9");
                listLead.setFlagClubSueldo(flagClubSueldo);
                Util.Log("10");
                listLead.setPaisContacto(pais);
                Util.Log("11");
                listLead.setProvContacto(provincia);
                listLead.setDeptCont(departamento);
                Util.Log("12");
                listLead.setDistContacto(distrito);
                Util.Log("13");
                listLead.setNomCpg(nomCpg);
                listLead.setDistEmp(direccionAd);
                listLead.setDireccion(dirAd);
                Util.Log("leadId -- >" + leadId);
                listLead.setIdLead(Long.parseLong(leadId));
                listLead.setCuota(cuota);
                listLead.setPropension(propension);
                listLead.setDecil(decil);
                listLead.setMonto(montoProd);
                listLead.setEstadoCivil(estadoCivil);
                listLead.setRuc(ruc);
                listLead.setFlujo(flujo);
                listLead.setPromNueva(promNueva);
                opp.add(listLead);
                
                
            }
            
            Util.Log("tamaño lista --> " + opp.size());
            
        } catch (SQLException e) {
                  Util.Log(e + "error");
              } finally {
                  DbUtils.closeQuietly(rs);
                  DbUtils.closeQuietly(st);
                  DbUtils.closeQuietly(conn);
              }
        
        return opp;

    }
    
    public static List<CpgLeadContAccProdFb> obtenerLead(String IdEjecutivo) throws SQLException {
        
           List<CpgLeadContAccProdFb> opp = new ArrayList<CpgLeadContAccProdFb>();
        
           DosificacionEjecutivoBE be = new DosificacionEjecutivoBE();
        
           String sPausado = "N";
           
           String sQuery = "begin SBP_SLS_LEAD_PKG.pr_obtenerLead(?,?); end;";
           
           ResultSet rs = null;
        
           Connection cnx = null;
        
           CallableStatement st = null;
           
           try {
               
               sbp.utils.Connection connection;
               connection = new sbp.utils.Connection();
               cnx = connection.getConnection();
               st = cnx.prepareCall(sQuery);
               st.setString(1, IdEjecutivo);
               st.registerOutParameter(2, OracleTypes.CURSOR);
               st.execute();
               
               rs = (ResultSet)st.getObject(2);
               
               if(rs != null){
                   
                   while (rs.next()){
                       be.setCampania(rs.getString("ID_CAMPANIA"));
                       be.setCanal(rs.getString("ID_CANAL"));
                       be.setSupervisor(rs.getString("ID_SUPERVISOR"));
                       be.setEjecutivo(rs.getString("ID_EJECUTIVO"));
                       be.setLead(rs.getString("ID_LEAD"));
                       be.setAvance(rs.getString("AVANCE"));
                       break;
                   }
                   
                   rs.close();
               }
               
               if (be.getLead() == null){
                   Util.Log("Entro el else asigna valores por defecto");
                   be.setLead("0");
               }
                   
               Util.Log("Lead : " + be.getLead());
               
               if (!be.getLead().equalsIgnoreCase("0")) {           
                   
                   // Si esta habilitado traer la Lead
                   opp = getLead(be.getLead());
                   
                   Util.Log("TAMAÑO LEADS --> " + opp.size());
                   
                   opp.get(0).setAvance(be.getAvance());
                   
                   Util.Log("Registros de la Lead : " + opp.size());
                   
                   sPausado = opp.get(0).getActivoCpg() == null ? "N" : opp.get(0).getActivoCpg();
                   
                   if (sPausado.equalsIgnoreCase("N")){
                       
                       // Actualizar campaña visualizar
                       actualizarCpgActual(IdEjecutivo, opp.get(0).getIdCpg().toString());
                       
                       // Traer nuevo Lead
                       //opp = obtenerLead(IdEjecutivo);
                   }
               }
               else{
                   be.setCampania("");
                   be.setCanal("");
                   be.setSupervisor("");
                   be.setEjecutivo("");
                   be.setLead("0");
                   Util.Log("2 Entro el else asigna valores por defecto");
               }
                   
           }catch (Exception ex){
               Util.Log("Exception : " + ex);
           
           }finally {
                   DbUtils.closeQuietly(rs);
                   DbUtils.closeQuietly(st);
                   DbUtils.closeQuietly(cnx);
               } 
           
           return opp;
           
       }
    
    public static List<CpgLeadContAccProdFb> obtenerInfoLead(String idLead) throws SQLException {
        
           List<CpgLeadContAccProdFb> opp = new ArrayList<CpgLeadContAccProdFb>();        
           
           opp = getLead(idLead);
           
           return opp;
           
       }
    
    public static String reasignarXFiltro_OLD(String IdCampania, String IdCanal, String canal, String jefCanal, String sup, String IdSupervisor, Integer Cantidad, Double Porcentaje){

               ADFContext ctx = ADFContext.getCurrent();
               
               List<Lead> listadoLeads = new ArrayList<Lead>();
                       
               listadoLeads = (List<Lead>)ctx.getSessionScope().get("leadListReasig");
               
               List<EjVentaFiltro> listFiltroReasig = new ArrayList<EjVentaFiltro>();
                       
               listFiltroReasig = (List<EjVentaFiltro>)ctx.getSessionScope().get("listFiltroReasig");
               
               String nomCpg = nomCpg(IdCampania);

               String sLoteEjecucion = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
               List<LeadRandom> listRdmLeadSinc = new ArrayList<LeadRandom>(); // Lista nuevos leads
               
               try{
                   // Inicializar Leads
                //   inicializar(IdCampania, IdSupervisor);
                   
                   DosificadorLista dlEjecutivos = new DosificadorLista(); 
                   Integer iCantLeadNuevasPorSupe = 0; // Cantidad lead nueva necesaria por supervisor
                   
                   Integer iCantLeadLibresPorCamp = listadoLeads.size();
                   Util.Log(" : ---------------------------------------------------------------------------------------------------------");
                   Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor);

                   // Ejecutivos por supervisor
                   
                   
                   int icantEjec = listFiltroReasig.size();
                   Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec);
                    
                   for (EjVentaFiltro ej : listFiltroReasig){

                       Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec + " : " + ej.getIdEjVenta());

                       // Calcular la cantidad de Lead nuevos que necesita el ejecutivo
                       Integer iCantLeadNuevasPorEjec = obtenerCantLeadEjecutivo(IdCampania, ej.getIdEjVenta(), Cantidad, Porcentaje, iCantLeadLibresPorCamp, icantEjec);
                       Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec + " : " + ej.getIdEjVenta() + " : " + iCantLeadNuevasPorEjec);

                       // Acumulado por canal
                       iCantLeadNuevasPorSupe = iCantLeadNuevasPorSupe + iCantLeadNuevasPorEjec;
                       Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec + " : " + ej.getIdEjVenta() + " : " + iCantLeadNuevasPorEjec + " : " + iCantLeadNuevasPorSupe);

                       // Armar arreglo de ejecutivos
                       Dosificador de = new Dosificador();
                       de.setCampania(IdCampania);
                       de.setCanal(IdCanal);
                       de.setSupervisor(IdSupervisor);
                       de.setEjecutivo(ej.getIdEjVenta());
                       de.setCantidad(Integer.parseInt(ej.getCantidad()));
                       de.setNomCampania(nomCpg);
                       de.setNomCanal(canal);
                       de.setNomSup(sup);
                       de.setNomEjec(ej.getEjVenta());
                       de.setNomJefCan(jefCanal);
                       dlEjecutivos.Datos.add(de);
                       
                   }
                          
                   // Obtener las nuevas leads por campaña y canal
                   Util.Log("tamaño list ejec -->" + dlEjecutivos.Datos.size());
                   if (dlEjecutivos.Datos.size() > 0) {
                  /*     listRdmLead = sbp.obtenerLeadRandom(IdCampania,
                                                           IdCanal,
                                                           IdSupervisor,
                                                           iCantLeadNuevasPorSupe.toString());*/
                      
                      
                      // Recorrer ArrayList armado
                      for (Dosificador dos : dlEjecutivos.Datos){
                          
                          Util.Log("cant dos --> " + dos.getCantidad());
                          // Insertar la dosificacion
                          for (int i = 0; i < dos.getCantidad(); i++) {      
                              
                              actualizarEjVentaXLead(dos.getNomEjec(),
                                                     dos.getEjecutivo(),
                                                    listadoLeads.get(0).getIdLead().toString());

                              Util.Log("listadoLeads tam --> " + listadoLeads.size());
                              listRdmLeadSinc.add(new LeadRandom(listadoLeads.get(0).getIdLead().toString(), listadoLeads.get(0).getLeadNumber()));

                              listadoLeads.remove(0); 

                          }
                       // Insertar log
                       registrarLog(sLoteEjecucion, "Campaña " + dos.getCampania().toString() + " Canal " + dos.getCanal().toString() + " Supervisor " + dos.getSupervisor().toString() + " Ejecutivos " + dos.getEjecutivo().toString() + " :: " + dos.getCantidad().toString() + " Leads registradas");
                       
                      }

                      Util.Log(" Leads sobrantes listRdmLead : " + listadoLeads.size());
                      
                      // Limpiar los ejecutivos
                      dlEjecutivos = new DosificadorLista();
                   }   
                          
                   // 
                   Util.Log("IdCampania --> " + IdCampania + " - IdSupervisor --> " + IdSupervisor);
                   ordenarReasig(IdCampania, IdSupervisor);
                    
                   // Armar el txt de sincronizacion (actualiza el check Nuevo_c)   
              //     actualizarChecknuevo(listRdmLeadSinc);
                   
                   
               }catch(Exception ex){
                   Util.Log(ex.getMessage());
               }
               return sLoteEjecucion;
           }
    
    public static String reasignarXFiltro(String IdCampania, 
                                              String IdCanal, 
                                              String canal, 
                                              String jefCanal, 
                                              String sup, 
                                              String IdSupervisor,
                                              String IdEjecutivoReasignado) {

            ADFContext ctx = ADFContext.getCurrent();

            List<Lead> listadoLeads = (List<Lead>)ctx.getSessionScope().get("leadListReasig");
            List<EjVentaFiltro> listFiltroReasig = (List<EjVentaFiltro>)ctx.getSessionScope().get("listFiltroReasig");

            String nomCpg = nomCpg(IdCampania);

            String sLoteEjecucion = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
            List<LeadRandom> listRdmLeadSinc = new ArrayList<LeadRandom>();

            try {
                DosificadorLista dlEjecutivos = new DosificadorLista();

                for (EjVentaFiltro ej : listFiltroReasig) {

                    // Armar arreglo de ejecutivos
                    Dosificador de = new Dosificador();
                    de.setCampania(IdCampania);
                    de.setCanal(IdCanal);
                    de.setSupervisor(IdSupervisor);
                    de.setEjecutivo(ej.getIdEjVenta());
                    de.setCantidad(Integer.parseInt(ej.getCantidad()));
                    de.setNomCampania(nomCpg);
                    de.setNomCanal(canal);
                    de.setNomSup(sup);
                    de.setNomEjec(ej.getEjVenta());
                    de.setNomJefCan(jefCanal);
                    dlEjecutivos.Datos.add(de);

                }

                // Obtener las nuevas leads por campaña y canal
                Util.Log("tamaño list ejec -->" + dlEjecutivos.Datos.size());
                if (dlEjecutivos.Datos.size() > 0) {

                    // Recorrer ArrayList armado
                    for (Dosificador dos : dlEjecutivos.Datos) {

                        Util.Log("Ejecutivo/Lead :: " + dos.getEjecutivo() + "/" + dos.getCantidad());

                        // Obtener el ultimo nro orden del ejecutivo
                        int iNroOrden = obtenerNroOrden(IdCampania, dos.getEjecutivo());

                        Util.Log("iNroOrden :: " + iNroOrden);
                        
                        // Insertar la dosificacion
                        for (int i = 0; i < dos.getCantidad(); i++) {

                            iNroOrden += 1;

                            actualizarDataDosificador(listadoLeads.get(0).getIdLead().toString(),
                                                      dos.getEjecutivo(), 
                                                      dos.getNomEjec(), 
                                                      iNroOrden);

                            Util.Log("listadoLeads tam --> " + listadoLeads.size());
                            listRdmLeadSinc.add(new LeadRandom(listadoLeads.get(0).getIdLead().toString(), 
                                                               listadoLeads.get(0).getLeadNumber()));

                            listadoLeads.remove(0);

                        }
                        // Insertar log
                        registrarLog(sLoteEjecucion, 
                                     "Campaña " + dos.getCampania().toString() + " " +
                                     "Canal " + dos.getCanal().toString() + " " + 
                                     "Supervisor " + dos.getSupervisor().toString() + " " + 
                                     "Ejecutivos " + dos.getEjecutivo().toString() + " :: " + dos.getCantidad().toString() + " Leads registradas");

                    }

                    Util.Log(" Leads sobrantes listRdmLead : " + listadoLeads.size());

                    // Limpiar los ejecutivos
                    dlEjecutivos = new DosificadorLista();
                    
                    // Reordenar los lead del ejecutivo que se quitaron lead's
                    ordenar(IdCampania, IdSupervisor, IdEjecutivoReasignado);
                    
                }

            } catch (Exception ex) {
                Util.Log(ex.getMessage());
            }
            return sLoteEjecucion;
        }
    
    public static void actualizarEjVentaXLead(String nomEjecutivo, String idEjecutivo, String leadId) {

         Connection conn = null;
         PreparedStatement st = null;
         ResultSet rs = null;


         try {

             String query =
                 "update sbp_sls_lead_dosif_x_user set EJECUTIVO = '" + nomEjecutivo + "', ID_EJECUTIVO = " + idEjecutivo
                  + "where ID_LEAD = " + leadId;

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
    
    public static List<CpgLeadContAccProdFb> obtenerLeadCont(String idLead) {
        ServiceBiPublisher sbp = new ServiceBiPublisher();
        List<CpgLeadContAccProdFb> Lead = new ArrayList<CpgLeadContAccProdFb>();
      
        try {
          
        // Si esta habilitado traer la oportunidad
        Lead = sbp.obtenerLeadProdContAccFbCpg(idLead);
        
        Util.Log("Registros de la lead : " + Lead.size());
                
        }catch (Exception ex){
            Util.Log("Exception : " + ex);
        }
        
        return Lead;
    }
    
    public static void actualizarCpgActual(String IdEjecutivo, String IdCampania) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_actualizarCpgActual(?,?);END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, IdEjecutivo);
            ps.setString(2, IdCampania);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    private static void inicializar() {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_inicializar; END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void inicializar(String IdCampania, String IdSupervisor) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_inicializar(?,?); END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, IdCampania);
            ps.setString(2, IdSupervisor);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void ordenar() {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_ordenar; END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void ordenarReasig(String IdCampania, String IdSupervisor) {
          PreparedStatement ps = null;
          sbp.utils.Connection connection = new sbp.utils.Connection();
          Connection cnx = null;

          String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_ordenar_reasig(?,?); END;";

          try {

              cnx = connection.getConnection();
              ps = cnx.prepareStatement(query);
              ps.setString(1, IdCampania);
              ps.setString(2, IdSupervisor);

              ps.execute();
              ps.close();
              cnx.close();

          } catch (SQLException e) {
              Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
          } catch (NullPointerException e) {
              Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
          } catch (Exception e) {
              Util.Log("Exception : " + e.getMessage() + " Exception");
          } finally {
              DbUtils.closeQuietly(ps);
              DbUtils.closeQuietly(cnx);
          }
      }
    
    public static void ordenar(String IdCampania, String IdSupervisor) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_ordenar(?,?); END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, IdCampania);
            ps.setString(2, IdSupervisor);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void ordenar(String IdCampania, String IdSupervisor, String IdEjecutivo) {
            PreparedStatement ps = null;
            sbp.utils.Connection connection = new sbp.utils.Connection();
            Connection cnx = null;

            String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_ordenar(?,?,?); END;";

            try {

                cnx = connection.getConnection();
                ps = cnx.prepareStatement(query);
                ps.setString(1, IdCampania);
                ps.setString(2, IdSupervisor);
                ps.setString(3, IdEjecutivo);

                ps.execute();
                ps.close();
                cnx.close();

            } catch (SQLException e) {
                Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
            } catch (NullPointerException e) {
                Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
            } catch (Exception e) {
                Util.Log("Exception : " + e.getMessage() + " Exception");
            } finally {
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(cnx);
            }
        }
    
    public static void actualizarFlagUltimo2(String idEjecutivo) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_actualizarFlagUltimo(?);END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, idEjecutivo);

            ps.execute();
            Util.Log("EJECUTA");
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
   
    public static void actualizarFlagUltimo(String idLead) {
        actualizarFlagUltimo(idLead, "Y");
    }
    
    public static void actualizarFlagUltimo(String idLead, String Habilitado) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_actualizarFlagUltimo(?,?);END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, idLead);
            ps.setString(2, Habilitado);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }

    public static void eliminarLead(String idLead){
        
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;
        
        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_eliminarLead(?); END;";
        
        try {
        
            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, idLead);
            
            ps.execute();
            ps.close();
            cnx.close();
        
        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void ordenarAntiguos(String IdEjecutivo){
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_ordenarAntiguos(?); END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, IdEjecutivo);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static String dosificar(){
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idJefCan = ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        String sLoteEjecucion = d.format(new java.util.Date());
        
        ServiceBiPublisher sbp = new ServiceBiPublisher();
        List<LeadRandom> listRdmLead = new ArrayList<LeadRandom>(); // Lista nuevas Leads
        List<LeadRandom> listRdmLeadSinc = new ArrayList<LeadRandom>(); // Lista nuevas Leads
        
        try {
            // Inicializar Leads
            inicializar();
            
            // Obtener lista de supervisores (Tabla temporal)
            for (Dosificador sp : CampaniaCanalSupervisor().Datos){
                
                try {
                
                    DosificadorLista dlEjecutivos = new DosificadorLista(); 
                    Integer iCantLeadNuevasPorSupe = 0; // Cantida Lead nueva necesaria por supervisor
                
                    // Obtener la cantidad de leed nuevos libres
                    Integer iCantLeadLibresPorCamp = Integer.parseInt(sbp.obtenerCantLeadLibres(sp.getCampania(), sp.getCanal(), sp.getSupervisor()));               
                    Util.Log(" : ---------------------------------------------------------------------------------------------------------");                  
                    Util.Log(" : " + iCantLeadLibresPorCamp + " : "  + sp.getCampania() + " : " + sp.getCanal() + " : " + sp.getSupervisor()); 
                    
                    // Ejecutivos por supervisor                    
                    List<EjVenta> lsEjeVenCan = sbp.obtenerEjVenta(sp.getSupervisor());
                    int icantEjec = lsEjeVenCan.size();
                    Util.Log(" : " + iCantLeadLibresPorCamp + " : "  + sp.getCampania() + " : " + sp.getCanal() + " : " + sp.getSupervisor() + " : " + icantEjec);
            
                    // Obtener lista de Ejecutivos (Cloud)
                    for (EjVenta ej : lsEjeVenCan){
                        
                        Util.Log(sp.getNomEjec() + "NOM EJEC");
                        
                        Util.Log(" : " + iCantLeadLibresPorCamp + " : "  + sp.getCampania() + " : " + sp.getCanal() + " : " + sp.getSupervisor() + " : " + icantEjec + " : " + ej.getIdEjVenta());
                        
                        // Calcular la cantidad de Lead nuevos que necesita el ejecutivo
                        Integer iCantLeadNuevasPorEjec = obtenerCantLeadEjecutivo(sp.getCampania(), ej.getIdEjVenta(), sp.getCantidad(), sp.getPorcentaje(), iCantLeadLibresPorCamp, icantEjec);
                        //Util.Log(" E iCanOpoEje : " + ej.getIdEjVenta() + " :: " + iCanOpoEje);
                        Util.Log(" : " + iCantLeadLibresPorCamp + " : "  + sp.getCampania() + " : " + sp.getCanal() + " : " + sp.getSupervisor() + " : " + icantEjec + " : " + ej.getIdEjVenta() + " : " + iCantLeadNuevasPorEjec);
    
                        // Acumulado por canal
                        iCantLeadNuevasPorSupe = iCantLeadNuevasPorSupe + iCantLeadNuevasPorEjec;
                        //Util.Log(" E iCanOpoNew : " + iCanOpoNew);
                        Util.Log(" : " + iCantLeadLibresPorCamp + " : "  + sp.getCampania() + " : " + sp.getCanal() + " : " + sp.getSupervisor() + " : " + icantEjec + " : " + ej.getIdEjVenta() + " : " + iCantLeadNuevasPorEjec + " : " + iCantLeadNuevasPorSupe);
    
                        // Armar arreglo de ejecutivos                            
                        Dosificador de = new Dosificador();
                        de.setCampania(sp.getCampania());
                        de.setCanal(sp.getCanal());
                        de.setSupervisor(sp.getSupervisor());
                        de.setEjecutivo(ej.getIdEjVenta());
                        de.setCantidad(iCantLeadNuevasPorEjec);
                        de.setNomCampania(sp.getNomCampania());
                        de.setNomCanal(ej.getCanal());
                        de.setNomSup(ej.getSup());
                        de.setNomEjec(ej.getEjVenta());
                        de.setNomJefCan(ej.getJefCan());
                        dlEjecutivos.Datos.add(de);
                        
                    }
                    
                    // Obtener las nuevas Leads por campaña y canal
                    if (dlEjecutivos.Datos.size() > 0) {
                        listRdmLead = sbp.obtenerLeadRandom(sp.getCampania(),
                                                            sp.getCanal(),
                                                            sp.getSupervisor(),
                                                            iCantLeadNuevasPorSupe.toString());
                        
                        Util.Log(" Leads obtenidas listRdmLead : " + listRdmLead.size());
                        
                        // Recorrer ArrayList armado
                        for (Dosificador dos : dlEjecutivos.Datos){
                            // Insertar la dosificacion
                            for (int i = 0; i < dos.getCantidad(); i++) {
                                insertDataDosificador(dos.getCampania(),
                                                      dos.getCanal(),
                                                      dos.getSupervisor(),
                                                      dos.getEjecutivo(),
                                                      listRdmLead.get(0).getLeadId(),
                                                      listRdmLead.get(0).getLeadNumber(),
                                                      listRdmLead.get(0).getLeadNumber(),
                                                      dos.getNomEjec(),
                                                      dos.getNomSup(),
                                                      dos.getNomJefCan(),
                                                      dos.getNomCanal(),
                                                      dos.getNomCampania(),
                                                      idJefCan);
                                listRdmLeadSinc.add(new LeadRandom(listRdmLead.get(0).getLeadId(), listRdmLead.get(0).getLeadNumber()));
                                listRdmLead.remove(0); 
                            }
                            
                            // Insertar log
                            registrarLog(sLoteEjecucion, "Campaña " + dos.getCampania().toString() + 
                                                         " Canal " + dos.getCanal().toString() + 
                                                         " Supervisor " + dos.getSupervisor().toString() + 
                                                         " Ejecutivos " + dos.getEjecutivo().toString() + 
                                                         " :: " + dos.getCantidad().toString() + 
                                                         " Leads registradas");
                            
                        }
    
                        Util.Log(" Leads sobrantes listRdmLead : " + listRdmLead.size());
                        
                        // Limpiar los ejecutivos
                        dlEjecutivos = new DosificadorLista();
                    }    
                    
                } catch (Exception ex) {
                    Util.Log("Exception : " + ex.getMessage());
                }
            }
                
            // 
            ordenar();
            // Armar el txt de sincronizacion (actualiza el check Nuevo_c)
            actualizarChecknuevo(listRdmLeadSinc);

        }
        catch (SQLException ex){
            Util.Log(" SQLException : " + ex.getMessage());
            DosificacionEjecutivoBO.ordenar();
        }
        catch (Exception ex){
            Util.Log(" Exception : " + ex.getMessage());
            DosificacionEjecutivoBO.ordenar();
        }
            
        return sLoteEjecucion;
    }
    
    public static String dosificar(String IdCampania, String IdCanal, String IdSupervisor, Integer Cantidad, Double Porcentaje, String idJefCan){

        String sLoteEjecucion = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        
        ServiceBiPublisher sbp = new ServiceBiPublisher();
        List<LeadRandom> listRdmLead = new ArrayList<LeadRandom>(); // Lista nuevos leads
        List<LeadRandom> listRdmLeadSinc = new ArrayList<LeadRandom>(); // Lista nuevos leads
        
        try{
            // Inicializar Leads
            inicializar(IdCampania, IdSupervisor);
            
            DosificadorLista dlEjecutivos = new DosificadorLista(); 
            Integer iCantLeadNuevasPorSupe = 0; // Cantidad lead nueva necesaria por supervisor
            
            Integer iCantLeadLibresPorCamp = Integer.parseInt(sbp.obtenerCantLeadLibres(IdCampania, IdCanal, IdSupervisor));
            Util.Log(" : ---------------------------------------------------------------------------------------------------------");
            Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor);

            // Ejecutivos por supervisor
            List<EjVenta> lsEjeVenCan = sbp.obtenerEjVenta(IdSupervisor);
            int icantEjec = lsEjeVenCan.size();
            Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec);
             
            for (EjVenta ej : lsEjeVenCan){

                Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec + " : " + ej.getIdEjVenta());

                // Calcular la cantidad de Lead nuevos que necesita el ejecutivo
                Integer iCantLeadNuevasPorEjec = obtenerCantLeadEjecutivo(IdCampania, ej.getIdEjVenta(), Cantidad, Porcentaje, iCantLeadLibresPorCamp, icantEjec);
                Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec + " : " + ej.getIdEjVenta() + " : " + iCantLeadNuevasPorEjec);

                // Acumulado por canal
                iCantLeadNuevasPorSupe = iCantLeadNuevasPorSupe + iCantLeadNuevasPorEjec;
                Util.Log(" : " + iCantLeadLibresPorCamp + " : " + IdCampania + " : " + IdCanal + " : " + IdSupervisor + " : " + icantEjec + " : " + ej.getIdEjVenta() + " : " + iCantLeadNuevasPorEjec + " : " + iCantLeadNuevasPorSupe);

                // Armar arreglo de ejecutivos
                Dosificador de = new Dosificador();
                de.setCampania(IdCampania);
                de.setCanal(IdCanal);
                de.setSupervisor(IdSupervisor);
                de.setEjecutivo(ej.getIdEjVenta());
                de.setCantidad(iCantLeadNuevasPorEjec);
                //de.setNomCampania(sp.getNomCampania());
                de.setNomCanal(ej.getCanal());
                de.setNomSup(ej.getSup());
                de.setNomEjec(ej.getEjVenta());
                de.setNomJefCan(ej.getJefCan());
                dlEjecutivos.Datos.add(de);
                
            }
                   
            // Obtener las nuevas leads por campaña y canal
            if (dlEjecutivos.Datos.size() > 0) {
                listRdmLead = sbp.obtenerLeadRandom(IdCampania,
                                                    IdCanal,
                                                    IdSupervisor,
                                                    iCantLeadNuevasPorSupe.toString());
               
               
               // Recorrer ArrayList armado
               for (Dosificador dos : dlEjecutivos.Datos){
                   // Insertar la dosificacion
                   for (int i = 0; i < dos.getCantidad(); i++) {                        
                       insertDataDosificador(dos.getCampania(),
                                             dos.getCanal(),
                                             dos.getSupervisor(),
                                             dos.getEjecutivo(),
                                             listRdmLead.get(0).getLeadId(),
                                             listRdmLead.get(0).getLeadNumber(),
                                             listRdmLead.get(0).getLeadNumber(),
                                             dos.getNomEjec(),
                                             dos.getNomSup(),
                                             dos.getNomJefCan(),
                                             dos.getNomCanal(),
                                             dos.getNomCampania(),
                                             idJefCan);
                       listRdmLeadSinc.add(new LeadRandom(listRdmLead.get(0).getLeadId(), listRdmLead.get(0).getLeadNumber()));
                       listRdmLead.remove(0); 
                   }
                // Insertar log
                registrarLog(sLoteEjecucion, "Campaña " + dos.getCampania().toString() + " Canal " + dos.getCanal().toString() + " Supervisor " + dos.getSupervisor().toString() + " Ejecutivos " + dos.getEjecutivo().toString() + " :: " + dos.getCantidad().toString() + " Leads registradas");
                
               }

               Util.Log(" Leads sobrantes listRdmLead : " + listRdmLead.size());
               
               // Limpiar los ejecutivos
               dlEjecutivos = new DosificadorLista();
            }   
                   
            // 
            ordenar(IdCampania, IdSupervisor);
             
            // Armar el txt de sincronizacion (actualiza el check Nuevo_c)   
            actualizarChecknuevo(listRdmLeadSinc);
            
            
        }catch(Exception ex){
            Util.Log(ex.getMessage());
        }
        return sLoteEjecucion;
    }

    
    private static Integer obtenerCantLeadEjecutivo(String IdCampania, String IdEjecutivo, Integer Cantidad, double Porcentaje, Integer Libres, Integer Ejecutivos){
           String sQuery = "select count(1) as RESULTADO from SBP_SLS_LEAD_DOSIF_X_USER where ID_CAMPANIA = " + IdCampania + " and ID_EJECUTIVO = " + IdEjecutivo + "";
           Integer iCanAntExi = 0; // Cantidad leads antiguas exixtentes
           Integer iCanAntCal = 0; // Cantidad leads calculado
           Integer iCanNewCal = 0; // Cantidad leads calculado
           Integer iCanNewRet = 0; // Cantidad leads nuevos necesarias
           
           try {
               // Cantidad de antiguos del ejecutivo
               iCanAntExi = Integer.parseInt(Util.ExecuteQuery(sQuery)); // Bolsa de antiguos
               iCanAntCal = (int)(Cantidad * Porcentaje / 100); // Cantidad de antiguos
               
               iCanNewCal = (Cantidad < (Libres/Ejecutivos) ? Cantidad : (Libres/Ejecutivos)) - 
                            (iCanAntCal < iCanAntExi ? iCanAntCal : iCanAntExi);
               
               iCanNewRet = iCanNewCal;
           } catch (Exception e){
               iCanNewRet = 0;
           }
           return iCanNewRet;
       }
    
    public static void insertDataDosificador(String IdCampania, String IdCanal,
                                      String IdSupervisor, String IdEjecutivo,
                                      String IdLead, String leadNumber,
                                      String fbNom, String ejecutivo,
                                      String sup, String jefCan, String canal,
                                      String cpgNom, String idJefCan) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query =
            "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_insertarlead(?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, IdCampania);
            ps.setString(2, IdCanal);
            ps.setString(3, IdSupervisor);
            ps.setString(4, IdEjecutivo);
            ps.setString(5, IdLead);
            ps.setString(6, leadNumber);
            ps.setString(7, fbNom);
            ps.setString(8, ejecutivo);
            ps.setString(9, sup);
            ps.setString(10, jefCan);
            ps.setString(11, canal);
            ps.setString(12, cpgNom);
            ps.setString(13, idJefCan);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() +
                     " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void insertDataDosificador(String IdCampania, 
                                                 String IdCanal, 
                                                 String IdSupervisor, 
                                                 String IdEjecutivo, 
                                                 String IdLead, 
                                                 String leadNumber, 
                                                 String fbNom, 
                                                 String ejecutivo, 
                                                 String sup, 
                                                 String jefCan, 
                                                 String canal, 
                                                 String cpgNom, 
                                                 String idJefCan,
                                                 int iOrden,
                                                 String FlagUtltimo) {
            PreparedStatement ps = null;
            sbp.utils.Connection connection = new sbp.utils.Connection();
            Connection cnx = null;

            String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_insertarlead(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";

            try {

                cnx = connection.getConnection();
                ps = cnx.prepareStatement(query);
                ps.setString(1, IdCampania);
                ps.setString(2, IdCanal);
                ps.setString(3, IdSupervisor);
                ps.setString(4, IdEjecutivo);
                ps.setString(5, IdLead);
                ps.setString(6, leadNumber);
                ps.setString(7, fbNom);
                ps.setString(8, ejecutivo);
                ps.setString(9, sup);
                ps.setString(10, jefCan);
                ps.setString(11, canal);
                ps.setString(12, cpgNom);
                ps.setString(13, idJefCan);
                ps.setInt(14, iOrden);
                ps.setString(15, FlagUtltimo);

                ps.execute();
                ps.close();
                cnx.close();

            } catch (SQLException e) {
                Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
            } catch (NullPointerException e) {
                Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
            } catch (Exception e) {
                Util.Log("Exception : " + e.getMessage() + " Exception");
            } finally {
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(cnx);
            }
        }
    
    private static DosificadorLista CampaniaCanalSupervisor() throws SQLException {
           
           String sQuery = "begin SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_obtenerCpgCanalSup(?); end;";
           DosificadorLista Lista = new DosificadorLista();
           ResultSet rs = null;
           Connection conn = null;
           CallableStatement st = null;
           try {

               sbp.utils.Connection connection;
               connection = new sbp.utils.Connection();
               conn = connection.getConnection();
               st = conn.prepareCall(sQuery);
               st.registerOutParameter(1, OracleTypes.CURSOR);
               st.execute();
               
               rs = (ResultSet)st.getObject(1);
               //rs = ((OracleCallableStatement)st).getCursor(2);
               
               while (rs.next()){
                   Dosificador sp = new Dosificador();
                   sp.setCampania(rs.getString("ID_CPG"));
                   sp.setCanal(rs.getString("ID_CANAL"));
                   sp.setSupervisor(rs.getString("ID_SUP"));
                   sp.setCantidad(Integer.parseInt(rs.getString("CANTIDAD")));
                   sp.setPorcentaje(Double.parseDouble(rs.getString("PORC_REP")));
                   sp.setNomCampania(rs.getString("NOM_CPG"));
                   Lista.Datos.add(sp);
               }
               rs.close();
           
           } catch (SQLException ex){
               Util.Log("SQLException : " + ex.getMessage());
           } catch (Exception ex){
               Util.Log("Exception : " + ex.getMessage());
           } finally {
                   DbUtils.closeQuietly(rs);
                   DbUtils.closeQuietly(st);
                   DbUtils.closeQuietly(conn);
               }
           
           return Lista;
       }

    public static void actualizarChecknuevo(List<LeadRandom> listRdmLeadSinc) {
        Util.Log("[actualizarChecknuevo] Inicio ");
        Boolean exito = false;
        String strTrama;

        StringBuffer sBuffer = new StringBuffer();

        try {
            // Cabecera del txt
            sBuffer.append("Lead.LeadNumber,Lead.Nuevo_c");
            sBuffer.append(System.getProperty("line.separator"));

            // Recorremos los nuevos destino
            for (LeadRandom lead : listRdmLeadSinc) {

                strTrama = lead.getLeadNumber() + ",N";

                sBuffer.append(strTrama);
                sBuffer.append(System.getProperty("line.separator"));

            }

            //
            Util.Log(" Inicio de ejecutar web service");
            ImportLeadResource importLeadResource = new ImportLeadResource();


            exito =
                    importLeadResource.importarLeadRecurso(sBuffer, "SBP - Actualizar leads asignadas",
                                                           "300000040865670");
            Util.Log(" Termino de ejecutar web service");

        } catch (Exception ex) {
            Util.Log("[Exception] : " + ex.getMessage());
            /* FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                          ex.getMessage(),
                                                                          "")); */
        }

    } 
    
    public static void registrarLog(String LoteEjecucion, String Mensaje) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;

        String query =
            "begin SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_registrarLog(?,?); end;";

        try {

            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, LoteEjecucion);
            ps.setString(2, Mensaje);

            ps.execute();
            ps.close();
            cnx.close();

        } catch (SQLException e) {
            Util.Log("[registrarLog] SQLException : " + e.getMessage() +
                     " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("[registrarLog] NullPointerException : " +
                     e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("[registrarLog] Exception : " + e.getMessage() +
                     " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static String obtenerLog(String sLoteEjecucion){
        String sQuery = "begin SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_obtenerLog(?,?); end;";
        String sLog = "";
        ResultSet rs = null;
        Connection conn = null;
        CallableStatement st = null;
        try {

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareCall(sQuery);
            st.setString(1, sLoteEjecucion);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();
            
            rs = (ResultSet)st.getObject(2);
            //rs = ((OracleCallableStatement)st).getCursor(2);

            sLog = "Resumen Proceso " +sLoteEjecucion;
            
            while (rs.next()){
                sLog = sLog + "\n" + rs.getString("MENSAJE").toString();
            }
            rs.close();
        
        } catch (SQLException ex){
            Util.Log("[obtenerLog] SQLException : " + ex.getMessage());
        } catch (Exception ex){
            Util.Log("[obtenerLog] Exception : " + ex.getMessage());
        } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(st);
                DbUtils.closeQuietly(conn);
            }
        
        return sLog;
    }

    public static void actualizarFechaProgramada(String IdLead, String FechaProgramada){
    PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;
        
        String query = "BEGIN SBP_SLS_Lead_PKG.pr_registrarHoraProgramada(?,?); END;";
        
        try {
        
            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, IdLead);
            ps.setString(2, FechaProgramada);
            
            ps.execute();
            ps.close();
            cnx.close();
        
        } catch (SQLException e) {
            Util.Log("[actualizarFechaProgramada] SQLException : " + e.getMessage() + " IT DOES NOT WORK");
        } catch (NullPointerException e) {
            Util.Log("[actualizarFechaProgramada] NullPointerException : " + e.getMessage() + " NullPointerException caught");
        } catch (Exception e) {
            Util.Log("[actualizarFechaProgramada] Exception : " + e.getMessage() + " Exception");
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static List<Lead> listLead(String idCpg, String idSup) {

        List<Lead> listaLeads = new ArrayList<Lead>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String leadNumber = "";
        String idEjecutivo = "";
        String fbNom = "";
        String ejecutivo = "";
        String sup = "";
        String canal = "";
        String cpgNom = "";
        String jefCan = "";


        try {
            String query = "";
            if(idCpg == null && idSup == null){
               query = "select lead_number LEAD_NUMBER, id_ejecutivo ID_EJECUTIVO, jef_can JEF_CAN, fb_nom FB_NAME, ejecutivo EJEC_NOM, sup SUP, nom_can CANAL_NOM, cpg_nom CPG_NOM from sbp_sls_lead_dosif_x_user where etapa = 'N' and flag_visualizar = 'Y'";

                
            }else{

                 query =
                    "select lead_number LEAD_NUMBER, id_ejecutivo ID_EJECUTIVO, jef_can JEF_CAN, fb_nom FB_NAME, ejecutivo EJEC_NOM, sup SUP, nom_can CANAL_NOM, cpg_nom CPG_NOM from sbp_sls_lead_dosif_x_user where etapa = 'N' and flag_visualizar = 'Y' and id_campania = " + idCpg + " and id_supervisor = " + idSup;

            }
            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                Lead lead = new Lead();
                idEjecutivo = rs.getString("ID_EJECUTIVO");
                jefCan = rs.getString("JEF_CAN");
                leadNumber = rs.getString("LEAD_NUMBER");
                fbNom = rs.getString("FB_NAME");
                ejecutivo = rs.getString("EJEC_NOM");
                sup = rs.getString("SUP");
                canal = rs.getString("CANAL_NOM");
                cpgNom = rs.getString("CPG_NOM");

                lead.setResourceId(idEjecutivo);
                lead.setLeadNumber(leadNumber);
                lead.setJefeCanal(jefCan);
                lead.setLeadNumber(fbNom);
                lead.setEjecutivo(ejecutivo);
                lead.setSup(sup);
                lead.setCanal(canal);
                lead.setNomCpg(cpgNom);

                listaLeads.add(lead);

            }

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log(listaLeads.size() + "LISTA LIST LEAD");
        return listaLeads;
    }
    
    public static void actualizarFlagNuevo(List<LeadRandom> plstLeads) {

           SimplifiedImportActivity p = new SimplifiedImportActivity();


           String strTrama1;
           String strTramaCabecera1 = "LeadNumber,Nuevo_c" + "\n";
           Integer intIdxLead = 0;

          // int pivot = 1000;
           int ini = 0;
           int cant = plstLeads.size();
           int fin = cant;

       Util.Log(cant + "cant");

           try {
           //    while (cant > 0) {
                   
             //      if (cant > pivot) {
                   
                       ByteArrayOutputStream bos = new ByteArrayOutputStream();

                       ZipOutputStream zos = new ZipOutputStream(bos);
                       ZipEntry ze = new ZipEntry("Lead.csv");
                       zos.putNextEntry(ze);

                       byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                       zos.write(cabeceraLead1, 0, cabeceraLead1.length);

                       List<LeadRandom> listaLeads = new ArrayList<LeadRandom>();

                       listaLeads = plstLeads.subList(ini, fin);
                       
                       intIdxLead = 0;

                       for (int i = 0; i < listaLeads.size(); i++) {

                           LeadRandom lead = listaLeads.get(intIdxLead);
                           intIdxLead++;

                           strTrama1 = lead.getLeadNumber() + ",N" + "\n";

                           Util.Log(strTrama1 + "strTrama1 if");

                           byte[] bt = strTrama1.getBytes();
                           zos.write(bt, 0, bt.length);

                       }
                       // Cerrar Entry
                       zos.closeEntry();

                       String str =
                           client.Base64.byteArrayToBase64(bos.toByteArray());

                       Util.Log(str + "str");

                       MergeLeadInvoke mlc = new MergeLeadInvoke();
                       p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

               /*    } else {
                       Util.Log("1");
                       ByteArrayOutputStream bos1 = new ByteArrayOutputStream();

                       ZipOutputStream zos1 = new ZipOutputStream(bos1);
                       ZipEntry ze = new ZipEntry("Lead.csv");
                       zos1.putNextEntry(ze);

                       byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                       zos1.write(cabeceraLead1, 0, cabeceraLead1.length);

                       List<LeadRandom> listaLeads = new ArrayList<LeadRandom>();
                       
                       Util.Log(ini + "ini");
                       Util.Log(fin + "fin");
                       Util.Log(cant + "cant");

                       listaLeads = plstLeads.subList(ini, plstLeads.size());
                       
                       Util.Log("2");
                       
                       intIdxLead = 0;

                       listaLeads = plstLeads.subList(ini, plstLeads.size());

                       for (int i = 0; i < listaLeads.size(); i++) {

                           LeadRandom lead = listaLeads.get(intIdxLead);
                           intIdxLead++;

                           strTrama1 = lead.getLeadNumber() + ",N" + "\n";

                           Util.Log(strTrama1 + "strTrama1  else");

                           byte[] bt = strTrama1.getBytes();


                           zos1.write(bt, 0, bt.length);


                       }
                       
                       Util.Log("3");
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

               }*/
               //  eliminarDataProcesada();


           } catch (Exception ex) {
               Util.Log("[Error] : " + ex.getMessage());
               FacesContext.getCurrentInstance().addMessage("",
                                                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                                                             ex.getMessage(),
                                                                             ""));
           }

       }

    public static void asignarLeadTxt(List<Lead> plstLeads) {

        SimplifiedImportActivity p = new SimplifiedImportActivity();


        String strTrama1;
        String strTrama2;
        String strTramaCabecera1 = "LeadNumber" + "\n";
        String strTramaCabecera2 = "LeadNumber,ResourceId" + "\n";
        String strTramaFeedback = "LEAD_NUMBER,RecordName,EjecutivoVenta_c,Supervisor_c,JefeCanal_c,Canal_c,NombreCampana_c" + "\n";
        String strTrama4;
        Integer intIdxLead = 0;

        int pivot = 1000;
        int ini = 0;
        int fin = pivot;
        int cant = plstLeads.size();

    Util.Log(cant + "cant");

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
                                lead.getLeadNumber() + "," + lead.getResourceId() + "\n";


                        Util.Log(strTrama2 + "strTrama2 if");


                        byte[] bt = strTrama2.getBytes();


                        zos.write(bt, 0, bt.length);


                    }
                    // Cerrar Entry
                    zos.closeEntry();

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
                                "," + lead.getResourceId() + "," + lead.getSup() + "," +
                                lead.getJefeCanal() + "," + lead.getCanal() +
                                "," + lead.getNomCpg() + "\n";


                        Util.Log(strTrama4 + "strTrama4 if");


                        byte[] bt = strTrama4.getBytes();


                        zos.write(bt, 0, bt.length);


                    }
                    // Cerrar Entry
                    zos.closeEntry();

                    String str =
                        client.Base64.byteArrayToBase64(bos.toByteArray());

                    Util.Log(str + "str");

                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p = mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

                } else {
                    Util.Log("1");
                    ByteArrayOutputStream bos1 = new ByteArrayOutputStream();

                    ZipOutputStream zos1 = new ZipOutputStream(bos1);
                    ZipEntry ze = new ZipEntry("Lead.csv");
                    zos1.putNextEntry(ze);

                    byte[] cabeceraLead1 = strTramaCabecera1.getBytes();

                    zos1.write(cabeceraLead1, 0, cabeceraLead1.length);

                    List<Lead> listaLeads = new ArrayList<Lead>();
                    
                    Util.Log(ini + "ini");
                    Util.Log(fin + "fin");
                    Util.Log(cant + "cant");

                    listaLeads = plstLeads.subList(ini, plstLeads.size());
                    
                    Util.Log("2");
                    
                    intIdxLead = 0;

                    listaLeads = plstLeads.subList(ini, plstLeads.size());

                    for (int i = 0; i < listaLeads.size(); i++) {

                        Lead lead = listaLeads.get(intIdxLead);
                        intIdxLead++;


                        strTrama1 = lead.getLeadNumber() + "\n";

                        Util.Log(strTrama1 + "strTrama1  else");

                        byte[] bt = strTrama1.getBytes();


                        zos1.write(bt, 0, bt.length);


                    }
                    
                    Util.Log("3");
                    // Cerrar Entry
                    zos1.closeEntry();

                    ZipEntry zef = new ZipEntry("Lead_Resource.csv");
                    zos1.putNextEntry(zef);
                    byte[] cabeceraLead = strTramaCabecera2.getBytes();
                    zos1.write(cabeceraLead, 0, cabeceraLead.length);

                    intIdxLead = 0;
                    
                    Util.Log("4");
                    for (int i = 0; i < listaLeads.size(); i++) {

                        Lead lead = listaLeads.get(intIdxLead);
                        intIdxLead++;

                        strTrama2 =
                                lead.getLeadNumber() + "," + lead.getResourceId() + "\n";
                       

                        Util.Log(strTrama2 + "strTrama2 else");



                        byte[] bt = strTrama2.getBytes();


                        zos1.write(bt, 0, bt.length);


                    }
                    Util.Log("5");
                    // Cerrar Entry
                    zos1.closeEntry();


                    ZipEntry zefe = new ZipEntry("Feedback_Lead_c.csv");
                    zos1.putNextEntry(zefe);

                    byte[] cabeceraFb = strTramaFeedback.getBytes();

                    zos1.write(cabeceraFb, 0, cabeceraFb.length);

                    intIdxLead = 0;
                    Util.Log("6");
                    for (int i = 0; i < listaLeads.size(); i++) {

                        Lead lead = listaLeads.get(intIdxLead);
                        intIdxLead++;

                        strTrama4 =
                                lead.getLeadNumber() + "," + lead.getLeadNumber() +
                                "," + lead.getResourceId() + "," + lead.getSup() + "," +
                                lead.getJefeCanal() + "," + lead.getCanal() +
                                "," + lead.getNomCpg() + "\n";

                  
                        Util.Log(strTrama4 + "strTrama4 else");

                        byte[] bt = strTrama4.getBytes();


                        zos1.write(bt, 0, bt.length);


                    }
                    // Cerrar Entry
                    zos1.closeEntry();
                    Util.Log("7");

                    String str =
                        client.Base64.byteArrayToBase64(bos1.toByteArray());
                    Util.Log(str + "str");
                    MergeLeadInvoke mlc = new MergeLeadInvoke();
                    p =
        mlc.submitSimplifiedImportActivity(Util.DatosConexionSBP(), "LEAD", str);

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
    
    public static String nomCpg(String idCpg) {
          Util.Log("inicio");
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
    
    public static String dosificarXFiltro(String IdCampania, 
                                              String IdCanal, 
                                              String canal, 
                                              String jefCanal, 
                                              String sup, 
                                              String IdSupervisor, 
                                              String idJefCan,
                                              List<Lead> listadoLeads,
                                              List<EjVentaFiltro> listFiltro
                                              ) {
            Util.Log("============================================");
            Util.Log("IdCampania   :: " + IdCampania   );
            Util.Log("IdCanal      :: " + IdCanal      );
            Util.Log("canal        :: " + canal        );
            Util.Log("jefCanal     :: " + jefCanal     );
            Util.Log("sup          :: " + sup          );
            Util.Log("IdSupervisor :: " + IdSupervisor );
            Util.Log("idJefCan     :: " + idJefCan     );
            Util.Log("listadoLeads :: " + listadoLeads.size() );
            Util.Log("listFiltro   :: " + listFiltro.size()   );
            Util.Log("============================================");

            String nomCpg = nomCpg(IdCampania);

            String sLoteEjecucion = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
            List<LeadRandom> listRdmLeadSinc = new ArrayList<LeadRandom>(); // Lista nuevos leads

            try {
                DosificadorLista dlEjecutivos = new DosificadorLista();

                for (EjVentaFiltro ej : listFiltro) {

                    // Armar arreglo de ejecutivos
                    Dosificador de = new Dosificador();
                    de.setCampania(IdCampania);
                    de.setCanal(IdCanal);
                    de.setSupervisor(IdSupervisor);
                    de.setEjecutivo(ej.getIdEjVenta());
                    de.setCantidad(Integer.parseInt(ej.getCantidad()));
                    de.setNomCampania(nomCpg);
                    de.setNomCanal(canal);
                    de.setNomSup(sup);
                    de.setNomEjec(ej.getEjVenta());
                    de.setNomJefCan(jefCanal);
                    dlEjecutivos.Datos.add(de);

                }

                // Obtener las nuevas leads por campaña y canal
                if (dlEjecutivos.Datos.size() > 0) {
              
                    // Recorrer ArrayList armado
                    for (Dosificador dos : dlEjecutivos.Datos) {

                        Util.Log("Ejecutivo/Lead :: " + dos.getEjecutivo() + "/" + dos.getCantidad());

                        // Obtener el ultimo nro orden del ejecutivo
                        int iNroOrden = obtenerNroOrden(IdCampania, dos.getEjecutivo());
                        
                        Util.Log("iNroOrden :: " + iNroOrden);

                        // Insertar la dosificacion
                        for (int i = 0; i < dos.getCantidad(); i++) {
                            
                            iNroOrden += 1;
                            
                            // Definir flag ultimo
                            String sFlagUltimo = iNroOrden == 1 ? "Y" : "N";
                            
                            // Insertar
                            insertDataDosificador(dos.getCampania(), 
                                                  dos.getCanal(), 
                                                  dos.getSupervisor(), 
                                                  dos.getEjecutivo(), 
                                                  listadoLeads.get(0).getIdLeadNuevo(), 
                                                  listadoLeads.get(0).getLeadNumber(), 
                                                  listadoLeads.get(0).getLeadNumber(), 
                                                  dos.getNomEjec(), 
                                                  dos.getNomSup(), 
                                                  dos.getNomJefCan(), 
                                                  dos.getNomCanal(), 
                                                  dos.getNomCampania(), 
                                                  idJefCan,
                                                  iNroOrden,
                                                  sFlagUltimo);
                            
                            insertInfoLead(listadoLeads.get(0).getInsert());

                            listRdmLeadSinc.add(new LeadRandom(listadoLeads.get(0).getIdLeadNuevo(), 
                                                               listadoLeads.get(0).getLeadNumber()));
                            listadoLeads.remove(0);
                        }
                        // Insertar log
                        registrarLog(sLoteEjecucion, 
                                     "Campaña " + dos.getCampania().toString() + " " +
                                     "Canal " + dos.getCanal().toString() + " " + 
                                     "Supervisor " + dos.getSupervisor().toString() + " " + 
                                     "Ejecutivos " + dos.getEjecutivo().toString() + 
                                     " :: " + dos.getCantidad().toString() + " Leads registradas");

                    }

                    // Limpiar los ejecutivos
                    dlEjecutivos = new DosificadorLista();
                }

                // Armar el txt de sincronizacion (actualiza el check Nuevo_c)
                // Mandar por hilo (Mejora****)
                actualizarFlagNuevo(listRdmLeadSinc);

            } catch (Exception ex) {
                Util.Log("Error --> " + ex.getMessage());
            }
            return sLoteEjecucion;
        }
    
    public static void insertInfoLead(String insert){
         
          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          
            try {
                
                  String query = insert;
                
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
    
    public static Integer obtenerNroOrden(String sIdCampania, String sIdEjecutivo){
            Util.Log("Inicio");
            Connection cnx = null;
            CallableStatement st = null;
            Integer iReturn = 0;

            try {
                String sQuery = "begin SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_obtenerNroOrden(?,?,?); end;";

                sbp.utils.Connection connection;
                connection = new sbp.utils.Connection();
                cnx = connection.getConnection();
                st = cnx.prepareCall(sQuery);
                st.setString(1, sIdCampania);
                st.setString(2, sIdEjecutivo);
                st.registerOutParameter(3, OracleTypes.INTEGER);
                st.execute();

                // Valores de retorno
                iReturn = st.getInt(3);

                cnx.close();
                st.close();

            } catch (Exception ex) {
                Util.Log(ex.getMessage());
            } finally {
                DbUtils.closeQuietly(st);
                DbUtils.closeQuietly(cnx);
            }

            return iReturn;
        }
    
    public static void actualizarDataDosificador(String IdLead,
                                                     String IdEjecutivo, 
                                                     String NomEjecutivo, 
                                                     Integer iOrden) {
            PreparedStatement ps = null;
            sbp.utils.Connection connection = new sbp.utils.Connection();
            Connection cnx = null;

            String query = "BEGIN SBP_SLS_LEAD_DOSIF_X_USER_PKG.pr_actualizarLead(?,?,?,?); END;";

            try {

                cnx = connection.getConnection();
                ps = cnx.prepareStatement(query);
                ps.setString(1, IdEjecutivo);
                ps.setString(2, NomEjecutivo);
                ps.setString(3, IdLead);
                ps.setInt(4, iOrden);

                ps.execute();
                ps.close();
                cnx.close();

            } catch (SQLException e) {
                Util.Log("SQLException : " + e.getMessage() + " IT DOES NOT WORK");
            } catch (NullPointerException e) {
                Util.Log("NullPointerException : " + e.getMessage() + " NullPointerException caught");
            } catch (Exception e) {
                Util.Log("Exception : " + e.getMessage() + " Exception");
            } finally {
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(cnx);
            }
        }
}
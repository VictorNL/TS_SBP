package view.BO;

import dao.Canal;
import dao.NuevoDestino;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import oracle.adf.share.ADFContext;
import oracle.jdbc.OracleTypes;
import org.apache.commons.dbutils.DbUtils;
import view.BE.AsigReasigReporteBE;
import view.BE.AsigReasigReporteLista;
import view.Util;

public class AsigReasigReporteBO {
    
    public AsigReasigReporteBO() { super(); }
    
    public static AsigReasigReporteLista obtenerReporte(AsigReasigReporteBE param) {
        
        Util.Log(param.getFechaInicio().toString() + "1");
        Util.Log(param.getFechaFin().toString() + "2");
        Util.Log(param.getCampaniaID() + "3");
        Util.Log(param.getCanalID() + "4");
        Util.Log(param.getSupervisorID() + "5");
        Util.Log(param.getQuerySQL() + "6");
        
        AsigReasigReporteLista lista = new AsigReasigReporteLista();
        String sQuery = "begin SBP_SLS_REPORTES_PKG.pr_obtenerAsigReasig(?,?,?,?,?,?,?); end;";
        
        ResultSet rs = null;
        Connection cnx = null;
        CallableStatement st = null;
        try {
            
            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setString(1, param.getFechaInicio().toString());
            st.setString(2, param.getFechaFin().toString());
            st.setString(3, param.getCampaniaID());
            st.setString(4, param.getCanalID());
            st.setString(5, param.getSupervisorID());
            st.setString(6, param.getQuerySQL());
            st.registerOutParameter(7, OracleTypes.CURSOR);
            st.execute();
            
            // Obtener el cursor
            rs = (ResultSet)st.getObject(7);
            
            if(rs != null) {
                while (rs.next()){
                    AsigReasigReporteBE e = new AsigReasigReporteBE();
                    e.setUsuarioEjecucion(rs.getString("USUARIO_EJECUCION"));
                    e.setUsuarioEjecucionPerfil(rs.getString("USUARIO_EJECUCION_PERFIL"));
                    e.setCampaniaID(rs.getString("CAMPANIA_ID"));
                    e.setCampaniaNombre(rs.getString("CAMPANIA_NOMBRE"));
                    e.setCanalID(rs.getString("CANAL_ID"));
                    e.setCanalNombre(rs.getString("CANAL_NOMBRE"));
                    e.setJefeCanalID(rs.getString("JEFE_CANAL_ID"));
                    e.setJefeCanalNombre(rs.getString("JEFE_CANAL_NOMBRE"));
                    e.setSupervisorID(rs.getString("SUPERVISOR_ID"));
                    e.setSupervisorNombre(rs.getString("SUPERVISOR_NOMBRE"));
                    e.setEjecutivoID(rs.getString("EJECUTIVO_ID"));
                    e.setEjecutivoNombre(rs.getString("EJECUTIVO_NOMBRE"));
                    e.setFecha(rs.getString("FECHA"));
                    e.setCantidadAsignada(Integer.parseInt(rs.getString("CANTIDAD_ASIGNADA")));
                    e.setCantidadReasignada(Integer.parseInt(rs.getString("CANTIDAD_REASIGNADA")));
                    
                    lista.Lista.add(e);
                }
            }
            
        } catch (SQLException e) {
            Util.Log(e.getMessage());
        } catch (Exception e) {
            Util.Log(e.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        
        return lista;
    }
    
    
    public static AsigReasigReporteLista obtenerReporteSup(AsigReasigReporteBE param) {
        
        Util.Log(param.getFechaInicio().toString() + "1");
        Util.Log(param.getFechaFin().toString() + "2");
        Util.Log(param.getCampaniaID() + "3");
        Util.Log(param.getCanalID() + "4");
        Util.Log(param.getSupervisorID() + "5");
        Util.Log(param.getQuerySQL() + "6");
        
        AsigReasigReporteLista lista = new AsigReasigReporteLista();
        String sQuery = "begin SBP_SLS_REPORTES_PKG.pr_obtenerAsigReasig(?,?,?,?,?,?,?); end;";
        
        ResultSet rs = null;
        Connection cnx = null;
        CallableStatement st = null;
        try {
            
            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setString(1, param.getFechaInicio().toString());
            st.setString(2, param.getFechaFin().toString());
            st.setString(3, param.getCampaniaID());
            st.setString(4, param.getCanalID());
            st.setString(5, param.getSupervisorID());
            st.setString(6, param.getQuerySQL());
            st.registerOutParameter(7, OracleTypes.CURSOR);
            st.execute();
            
            // Obtener el cursor
            rs = (ResultSet)st.getObject(7);
            
            if(rs != null) {
                while (rs.next()){
                    AsigReasigReporteBE e = new AsigReasigReporteBE();
                    e.setUsuarioEjecucion(rs.getString("USUARIO_EJECUCION"));
                    e.setUsuarioEjecucionPerfil(rs.getString("USUARIO_EJECUCION_PERFIL"));
                    e.setCampaniaID(rs.getString("CAMPANIA_ID"));
                    e.setCampaniaNombre(rs.getString("CAMPANIA_NOMBRE"));
                    e.setCanalID(rs.getString("CANAL_ID"));
                    e.setCanalNombre(rs.getString("CANAL_NOMBRE"));
                    e.setJefeCanalID(rs.getString("JEFE_CANAL_ID"));
                    e.setJefeCanalNombre(rs.getString("JEFE_CANAL_NOMBRE"));
                    e.setSupervisorID(rs.getString("SUPERVISOR_ID"));
                    e.setSupervisorNombre(rs.getString("SUPERVISOR_NOMBRE"));
                    e.setEjecutivoID(rs.getString("EJECUTIVO_ID"));
                    e.setEjecutivoNombre(rs.getString("EJECUTIVO_NOMBRE"));
                    e.setFecha(rs.getString("FECHA"));
                    e.setCantidadAsignada(Integer.parseInt(rs.getString("CANTIDAD_ASIGNADA")));
                    e.setCantidadReasignada(Integer.parseInt(rs.getString("CANTIDAD_REASIGNADA")));
                    
                    lista.Lista.add(e);
                }
            }
            
        } catch (SQLException e) {
            Util.Log(e.getMessage());
        } catch (Exception e) {
            Util.Log(e.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        
        return lista;
    }
    
    public static void Registrar(AsigReasigReporteBE param) {
        PreparedStatement ps = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection cnx = null;
        
        String query = "begin SBP_SLS_REPORTES_PKG.pr_insertarAsigReasig(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;";
        
        try {
        
            cnx = connection.getConnection();
            ps = cnx.prepareStatement(query);
            ps.setString(1, param.getUsuarioEjecucion());
            ps.setString(2, param.getUsuarioEjecucionPerfil());
            ps.setString(3, param.getCampaniaID());
            ps.setString(4, param.getCampaniaNombre());
            ps.setString(5, param.getCanalID());
            ps.setString(6, param.getCanalNombre());
            ps.setString(7, param.getJefeCanalID());
            ps.setString(8, param.getJefeCanalNombre());
            ps.setString(9, param.getSupervisorID());
            ps.setString(10, param.getSupervisorNombre());
            ps.setString(11, param.getEjecutivoID());
            ps.setString(12, param.getEjecutivoNombre());
            ps.setString(13, param.getFecha());
            ps.setInt(14, param.getCantidadAsignada());
            ps.setInt(15, param.getCantidadReasignada());
            
            ps.execute();
            ps.close();
            cnx.close();
        
        } catch (SQLException e) {
            Util.Log("SQLException : " + e.getMessage());
        } catch (NullPointerException e) {
            Util.Log("NullPointerException : " + e.getMessage() );
        } catch (Exception e) {
            Util.Log("Exception : " + e.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(cnx);
        }
    }
    
    public static void GenerarSBP_CAN(List<NuevoDestino> lDestino) {
        ADFContext ctx = ADFContext.getCurrent();
        List<Canal> lOrigen = (List<Canal>)ctx.getSessionScope().get("listCanKardex");

        AsigReasigReporteLista lr = new AsigReasigReporteLista();
        String IdCaNaLDel = ctx.getSessionScope().get("IdCaNaL") == null ? "0" : ctx.getSessionScope().get("IdCaNaL").toString();

        try {
            
            // 
            for (Canal o : lOrigen) {            
                AsigReasigReporteBE be = new AsigReasigReporteBE();
                be.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
            //    be.setUsuarioEjecucion(ctx.getSessionScope().get("userLog").toString());
                be.setUsuarioEjecucionPerfil("SBP");
                be.setCampaniaID(o.getIdCpg());
                be.setCampaniaNombre(o.getNomCpg());
                be.setCanalID(o.getCanalId());
                be.setCanalNombre(o.getCanal());
                be.setCantidadAsignada(Integer.parseInt(o.getCant()));
                
                int iReas = 0;
                if (IdCaNaLDel.equalsIgnoreCase(o.getCanalId())) {
                    for (NuevoDestino d : lDestino) {
                        if (!d.getResourceId().equalsIgnoreCase(o.getCanalId())) {
                            iReas += Integer.parseInt(d.getCantidad());
                        }
                    }
                    iReas = iReas * -1;
                } else {
                    for (NuevoDestino d : lDestino) {
                        if (d.getResourceId().equalsIgnoreCase(o.getCanalId())) {
                            iReas = Integer.parseInt(d.getCantidad());
                        }
                    }
                }
                
                be.setCantidadReasignada(iReas);
                lr.Lista.add(be);
            }
            
            // Registrar
            for (AsigReasigReporteBE e : lr.Lista) {
              Util.Log(e.getCanalID() + "e.CANAL");
                Registrar(e);
            }
            
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }    
    }
    
    public static void GenerarJC_SUP(List<NuevoDestino> lDestino) {
        
        ADFContext ctx = ADFContext.getCurrent();
        List<Canal> lOrigen = (List<Canal>)ctx.getSessionScope().get("ListSupCanKardex");
        AsigReasigReporteLista lr = new AsigReasigReporteLista();
        String idSupDel = ctx.getSessionScope().get("SUPerId") == null ? "0" : ctx.getSessionScope().get("SUPerId").toString();
        try {            
            
            // 
            for (Canal o : lOrigen) {
                
                AsigReasigReporteBE be = new AsigReasigReporteBE();
                
                be.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
                be.setUsuarioEjecucion(ctx.getSessionScope().get("userLog").toString());
                be.setUsuarioEjecucionPerfil("JEFE_CANAL");
                
                be.setCampaniaID(o.getIdCpg());
                be.setCampaniaNombre(o.getNomCpg());
                be.setCanalID(o.getCanalId());
                be.setCanalNombre(o.getCanal());
                
                be.setJefeCanalID(o.getIdJefCanal());
                be.setJefeCanalNombre(o.getJefCanal());
                             
                be.setSupervisorID(o.getIdSup());
                Util.Log(o.getSup() + "o.getSup()");
                be.setSupervisorNombre(o.getSup());
                be.setEjecutivoID(o.getIdEjVenta());
                be.setEjecutivoNombre(o.getEjVenta());
                
                
                be.setCantidadAsignada(Integer.parseInt(o.getCant()));
                
                int iReas = 0;
                if (idSupDel.equalsIgnoreCase(o.getIdSup())) {
                    for (NuevoDestino d : lDestino) {
                        if (!d.getResourceId().equalsIgnoreCase(o.getIdSup())) {
                            iReas += Integer.parseInt(d.getCantidad());
                        }
                    }
                    iReas = iReas * -1;
                } else {
                    for (NuevoDestino d : lDestino) {
                        if (d.getResourceId().equalsIgnoreCase(o.getIdSup())) {
                            iReas = Integer.parseInt(d.getCantidad());
                        }
                    }
                }
                
                be.setCantidadReasignada(iReas);
                
                lr.Lista.add(be);
            }
            
            // Registrar
            for (AsigReasigReporteBE e : lr.Lista) {
                Registrar(e);
            }
            
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }        
    }
    
    public static void GenerarSUP_EJ(List<NuevoDestino> lDestino) {
        ADFContext ctx = ADFContext.getCurrent();
        List<Canal> lOrigen = (List<Canal>)ctx.getSessionScope().get("listEjVenta");
        AsigReasigReporteLista lr = new AsigReasigReporteLista();
        String EjVentaIdDel = ctx.getSessionScope().get("EjVentaId") == null ? "0" : ctx.getSessionScope().get("EjVentaId").toString();
        try {            
           
            
            // 
            for (Canal o : lOrigen) {
                AsigReasigReporteBE be = new AsigReasigReporteBE();
                be.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
                be.setUsuarioEjecucion(ctx.getSessionScope().get("userLog").toString());
                be.setUsuarioEjecucionPerfil("SUPERVISOR");
                
                be.setCampaniaID(o.getIdCpg());
                be.setCanalID(o.getCanalId());
                be.setCanalNombre(o.getCanal());
                
                be.setSupervisorID(o.getIdSup());
                be.setSupervisorNombre(o.getSup());
                
                be.setJefeCanalID(o.getIdJefCanal());
                be.setJefeCanalNombre(o.getJefCanal());
                be.setCampaniaNombre(o.getNomCpg());
                
                be.setEjecutivoID(o.getIdEjVenta());
                be.setEjecutivoNombre(o.getEjVenta());
                be.setCantidadAsignada(Integer.parseInt(o.getCant()));
                
                int iReas = 0;
                if (EjVentaIdDel.equalsIgnoreCase(o.getIdEjVenta())) {
                    for (NuevoDestino d : lDestino) {
                        if (!d.getResourceId().equalsIgnoreCase(o.getIdEjVenta())) {
                            iReas += Integer.parseInt(d.getCantidad());
                        }
                    }
                    iReas = iReas * -1;
                } else {
                    for (NuevoDestino d : lDestino) {
                        if (d.getResourceId().equalsIgnoreCase(o.getIdEjVenta())) {
                            iReas = Integer.parseInt(d.getCantidad());
                        }
                    }
                }
                
                be.setCantidadReasignada(iReas);
                
                lr.Lista.add(be);
            }
            
            // Registrar
            for (AsigReasigReporteBE e : lr.Lista) {
                Registrar(e);
            }
            
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }    
    }
}

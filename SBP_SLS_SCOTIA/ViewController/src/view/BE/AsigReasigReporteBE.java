package view.BE;

public class AsigReasigReporteBE {
    public AsigReasigReporteBE() {
        super();
    }
    
    private String UsuarioEjecucion;
    private String UsuarioEjecucionPerfil;
    private String CampaniaID;
    private String CampaniaNombre;
    private String CanalID;
    private String CanalNombre;
    private String JefeCanalID;
    private String JefeCanalNombre;
    private String SupervisorID;
    private String SupervisorNombre;
    private String EjecutivoID;
    private String EjecutivoNombre;
    private String Fecha;
    private Integer CantidadAsignada;
    private Integer CantidadReasignada;
    
    // Filtros de Busqueda
    private String FechaInicio;
    private String FechaFin;
    private String QuerySQL;

    public void setUsuarioEjecucion(String UsuarioEjecucion) {
        this.UsuarioEjecucion = UsuarioEjecucion;
    }

    public String getUsuarioEjecucion() {
        return UsuarioEjecucion;
    }

    public void setUsuarioEjecucionPerfil(String UsuarioEjecucionPerfil) {
        this.UsuarioEjecucionPerfil = UsuarioEjecucionPerfil;
    }

    public String getUsuarioEjecucionPerfil() {
        return UsuarioEjecucionPerfil;
    }

    public void setCampaniaID(String CampaniaID) {
        this.CampaniaID = CampaniaID;
    }

    public String getCampaniaID() {
        return CampaniaID;
    }

    public void setCampaniaNombre(String CampaniaNombre) {
        this.CampaniaNombre = CampaniaNombre;
    }

    public String getCampaniaNombre() {
        return CampaniaNombre;
    }

    public void setCanalID(String CanalID) {
        this.CanalID = CanalID;
    }

    public String getCanalID() {
        return CanalID;
    }

    public void setCanalNombre(String CanalNombre) {
        this.CanalNombre = CanalNombre;
    }

    public String getCanalNombre() {
        return CanalNombre;
    }

    public void setJefeCanalID(String JefeCanalID) {
        this.JefeCanalID = JefeCanalID;
    }

    public String getJefeCanalID() {
        return JefeCanalID;
    }

    public void setJefeCanalNombre(String JefeCanalNombre) {
        this.JefeCanalNombre = JefeCanalNombre;
    }

    public String getJefeCanalNombre() {
        return JefeCanalNombre;
    }

    public void setSupervisorID(String SupervisorID) {
        this.SupervisorID = SupervisorID;
    }

    public String getSupervisorID() {
        return SupervisorID;
    }

    public void setSupervisorNombre(String SupervisorNombre) {
        this.SupervisorNombre = SupervisorNombre;
    }

    public String getSupervisorNombre() {
        return SupervisorNombre;
    }

    public void setEjecutivoID(String EjecutivoID) {
        this.EjecutivoID = EjecutivoID;
    }

    public String getEjecutivoID() {
        return EjecutivoID;
    }

    public void setEjecutivoNombre(String EjecutivoNombre) {
        this.EjecutivoNombre = EjecutivoNombre;
    }

    public String getEjecutivoNombre() {
        return EjecutivoNombre;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setCantidadAsignada(Integer CantidadAsignada) {
        this.CantidadAsignada = CantidadAsignada;
    }

    public Integer getCantidadAsignada() {
        return CantidadAsignada;
    }

    public void setCantidadReasignada(Integer CantidadReasignada) {
        this.CantidadReasignada = CantidadReasignada;
    }

    public Integer getCantidadReasignada() {
        return CantidadReasignada;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setQuerySQL(String QuerySQL) {
        this.QuerySQL = QuerySQL;
    }

    public String getQuerySQL() {
        return QuerySQL;
    }
}

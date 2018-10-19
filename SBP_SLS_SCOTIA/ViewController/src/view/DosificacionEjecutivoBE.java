package view;

public class DosificacionEjecutivoBE {
    public DosificacionEjecutivoBE() {
        super();
    }

    private String Campania;
    private String Canal;
    private String Supervisor;
    private String Ejecutivo;
    private String Lead;
    private Integer Habilitado;
	private String Avance;

    public void setCampania(String Campania) {
        this.Campania = Campania;
    }

    public String getCampania() {
        return Campania;
    }

    public void setCanal(String Canal) {
        this.Canal = Canal;
    }

    public String getCanal() {
        return Canal;
    }

    public void setSupervisor(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public String getSupervisor() {
        return Supervisor;
    }

    public void setEjecutivo(String Ejecutivo) {
        this.Ejecutivo = Ejecutivo;
    }

    public String getEjecutivo() {
        return Ejecutivo;
    }



    public void setHabilitado(Integer Habilitado) {
        this.Habilitado = Habilitado;
    }

    public Integer getHabilitado() {
        return Habilitado;
    }

    public void setAvance(String Avance) {
        this.Avance = Avance;
    }

    public String getAvance() {
        return Avance;
    }

    public void setLead(String Lead) {
        this.Lead = Lead;
    }

    public String getLead() {
        return Lead;
    }
}

public class Voo {
    private String numero;
    private String companhia;
    private String horarioEmbarque;
    private String horarioSaida;
    private String destino;
    private String status;

    public Voo(String numero, String companhia, String horarioEmbarque, String horarioSaida, String destino) {
        this.numero = numero.toUpperCase();
        this.companhia = companhia;
        this.horarioEmbarque = horarioEmbarque;
        this.horarioSaida = horarioSaida;
        this.destino = destino;
        this.status = "PREVISTO"; // Status inicial
    }
    //getters 
    public String getNumero() {
        return numero;
    }
    public String getCompanhia() {
        return companhia;
    }
    public String getHorarioEmbarque() {
        return horarioEmbarque;
    }
    public String getHorarioSaida() {
        return horarioSaida;
    }
    public String getDestino() {
        return destino;
    }
    public String getStatus() {
        return status;
    }

    //setters
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }
    public void setHorarioEmbarque(String horarioEmbarque) {
        this.horarioEmbarque = horarioEmbarque;
    }
    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
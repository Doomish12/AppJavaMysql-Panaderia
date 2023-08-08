package entidades;

public class Cliente {
    private int codCli;
    private String nomCli;
    private String apCli;
    private String celCli;
    private int codDis;

 
    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getApCli() {
        return apCli;
    }

    public void setApCli(String apCli) {
        this.apCli = apCli;
    }

    public String getCelCli() {
        return celCli;
    }

    public void setCelCli(String celCli) {
        this.celCli = celCli;
    }

    public int getCodDis() {
        return codDis;
    }

    public void setCodDis(int codDis) {
        this.codDis = codDis;
    }
}


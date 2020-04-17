package br.mack.ps2.entidades;

public class Jogo {
    private long idjogo;
    private String nm_timeA;
    private String nm_timeB;
    private int pont_timeA;
    private int pont_timeB;

    public Jogo(){
        this.setIdJogo(-1);
        this.setNm_timeA(null);
        this.setNm_timeB(null);
        this.setPont_timeA(0);
        this.setPont_timeB(0);
    }

    public Jogo(long id, String nm_timeA, String nm_timeB, int pont_timaA, int pont_timeB){
        this.setIdJogo(id);
        this.setNm_timeA(nm_timeA);
        this.setNm_timeB(nm_timeB);
        this.setPont_timeA(0);
        this.setPont_timeB(0);
    }

    public long getIdJogo() {
        return idjogo;
    }

    public void setIdJogo(long id) {
        this.idjogo = id;
    }

    public String getNm_timeA() {
        return nm_timeA;
    }

    public void setNm_timeA(String nm_timeA) {
        this.nm_timeA = nm_timeA;
    }

    public String getNm_timeB() {
        return nm_timeB;
    }

    public void setNm_timeB(String nm_timeB) {
        this.nm_timeB = nm_timeB;
    }

    public int getPont_timeA() {
        return pont_timeA;
    }

    public void setPont_timeA(int pont_timeA) {
        this.pont_timeA = pont_timeA;
    }

    public int getPont_timeB() {
        return pont_timeB;
    }

    public void setPont_timeB(int pont_timeB) {
        this.pont_timeB = pont_timeB;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "id = " + idjogo +
                ", nm_timeA = '" + nm_timeA + '\'' +
                ", nm_timeB = '" + nm_timeB + '\'' +
                ", pont_timeA = " + pont_timeA +
                ", pont_timeB = " + pont_timeB +
                '}';
    }
}
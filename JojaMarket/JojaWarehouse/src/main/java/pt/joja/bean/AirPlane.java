package pt.joja.bean;

public class AirPlane {

    private String fdj;

    private String yc;

    private Integer zkl;

    private String jz;

    private String fjs;

    public String getFdj() {
        return fdj;
    }

    public void setFdj(String fdj) {
        this.fdj = fdj;
    }

    public String getYc() {
        return yc;
    }

    public void setYc(String yc) {
        this.yc = yc;
    }

    public Integer getZkl() {
        return zkl;
    }

    public void setZkl(Integer zkl) {
        this.zkl = zkl;
    }

    public String getJz() {
        return jz;
    }

    public void setJz(String jz) {
        this.jz = jz;
    }

    public String getFjs() {
        return fjs;
    }

    public void setFjs(String fjs) {
        this.fjs = fjs;
    }

    @Override
    public String toString() {
        return "AirPlane{" +
                "fdj='" + fdj + '\'' +
                ", yc='" + yc + '\'' +
                ", zkl=" + zkl +
                ", jz='" + jz + '\'' +
                ", fjs='" + fjs + '\'' +
                '}';
    }
}

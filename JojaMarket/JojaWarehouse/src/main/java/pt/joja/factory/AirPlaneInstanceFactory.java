package pt.joja.factory;

import pt.joja.bean.AirPlane;

/**
 * AirPlane的实例工厂
 */
public class AirPlaneInstanceFactory {

    public AirPlane getAirPlane(String jz) {

        System.out.println("实例工厂正在工作..");

        AirPlane ap = new AirPlane();
        ap.setFdj("太行发动机");
        ap.setJz(jz);
        ap.setFjs("副驾驶Bot");
        ap.setYc("25.6m");
        ap.setZkl(300);

        return ap;
    }
}

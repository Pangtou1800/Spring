package pt.joja.factory;

import org.springframework.beans.factory.FactoryBean;
import pt.joja.bean.AirPlane;

public class MyFactoryBean implements FactoryBean<AirPlane> {

    /**
     * 工厂方法，Spring会自动调用
     *
     * @return
     * @throws Exception
     */
    @Override
    public AirPlane getObject() throws Exception {
        AirPlane ap = new AirPlane();
        ap.setFdj("强力发动机");
        return ap;
    }

    /**
     * 返回创建对象的类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return AirPlane.class;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}

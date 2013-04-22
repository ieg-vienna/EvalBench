package evaluation.evalBench.task;

import java.util.Map;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.namespace.QName;

public class Configuration {
    
    private Map<QName, String> extension;

    @XmlAnyAttribute
    public Map<QName, String> getAny() {
        return extension;
    }

    public void setAny(Map<QName, String> extension) {
        this.extension = extension;
    }

}

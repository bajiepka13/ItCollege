package org.bajiepka.courseApp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("CommonConfig")
@Scope("singleton")
public class CommonConfig {

    @Value("D:\\")
    public String defaultPath;
    @Value("json")
    public String defultPrefix;

    public String getDefaultPath() {
        return defaultPath;
    }

    public String getDefultPrefix() {
        return defultPrefix;
    }
}

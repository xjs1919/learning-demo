package com.github.xjs.springboot4.config;

import org.springframework.beans.factory.BeanRegistrar;
import org.springframework.beans.factory.BeanRegistry;
import org.springframework.core.env.Environment;

public class BeanRegistrarConfig implements BeanRegistrar {
    @Override
    public void register(BeanRegistry registry, Environment env) {
        if(env.matchesProfiles("prod")){
            registry.registerBean("envService", ProductionService.class);
        } else {
            registry.registerBean("envService", NonProductionService.class);
        }
    }
    public interface EnvService{
        String getEnv();
    }
    public static class ProductionService implements EnvService{
        @Override
        public String getEnv() {
            return "Production";
        }
    }
    public static class NonProductionService implements EnvService {
        @Override
        public String getEnv() {
            return "NonProductio";
        }
    }
}

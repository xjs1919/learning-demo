package com.github.xjs.iocdemo.methodinjection.lookupmethod;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Lookup2App {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Command command(){
        return new Command();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CommandManager2 commandManager2(){
        return new CommandManager2(){
            @Override
            protected Command createCommand() {
                return command();
            }
        };
    }

}

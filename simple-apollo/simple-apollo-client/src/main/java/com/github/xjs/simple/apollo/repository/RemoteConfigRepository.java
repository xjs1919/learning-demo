package com.github.xjs.simple.apollo.repository;

import com.github.xjs.simple.apollo.util.DefaultApplicationProvider;
import com.github.xjs.simple.apollo.util.HttpRequest;
import com.github.xjs.simple.apollo.util.HttpResponse;
import com.github.xjs.simple.apollo.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 10:44
 */
public class RemoteConfigRepository {

    private static Logger logger = LoggerFactory.getLogger(RemoteConfigRepository.class);

    private ApolloConfig apolloConfig;

    private HttpUtil httpUtil;

    private ScheduledExecutorService m_executorService;

    private List<RepositoryChangeListener> listeners = new ArrayList<>();

    public void addChangeListener(RepositoryChangeListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public RemoteConfigRepository(){
        this.httpUtil = HttpUtil.getInstance();
        this.m_executorService = Executors.newScheduledThreadPool(1);
        this.sync();
        this.schedulePeriodicRefresh();
    }

    protected synchronized void sync() {
        try {
            ApolloConfig previous = this.apolloConfig;
            ApolloConfig current = loadApolloConfig();
            if (previous != current) {
                this.apolloConfig = current;
                this.fireRepositoryChange(this.getConfig());
            }
        } catch (Throwable ex) {
            throw ex;
        }
    }

    private void schedulePeriodicRefresh() {
        m_executorService.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        sync();
                    }
                }, 3, 3,
                TimeUnit.SECONDS);
    }

    protected void fireRepositoryChange(Properties newProperties) {
        for (RepositoryChangeListener listener : listeners) {
            try {
                listener.onRepositoryChange(newProperties);
            } catch (Throwable ex) {
                logger.error("Failed to invoke repository change listener {}", listener.getClass(), ex);
            }
        }
    }

    public Properties getConfig() {
        if (this.apolloConfig == null) {
            this.sync();
        }
        return transformApolloConfigToProperties(this.apolloConfig);
    }

    private Properties transformApolloConfigToProperties(ApolloConfig apolloConfig) {
        Properties result = new Properties();
        result.putAll(apolloConfig.getConfigurations());
        return result;
    }

    private ApolloConfig loadApolloConfig() {
        DefaultApplicationProvider applicationProvider = DefaultApplicationProvider.getInstance();
        HttpRequest request = new HttpRequest(applicationProvider.getMeta()+"/get_config?appid="+applicationProvider.getAppId());
        HttpResponse<ApolloConfig> response = httpUtil.doGet(request, ApolloConfig.class);
        if(response.getStatusCode() == 304){
            return this.apolloConfig;
        }
        return response.getBody();
    }
}

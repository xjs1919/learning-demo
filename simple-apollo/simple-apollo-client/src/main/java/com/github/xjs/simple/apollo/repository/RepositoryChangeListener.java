package com.github.xjs.simple.apollo.repository;

import java.util.Properties;



/**
 * @Author: xjs@choicesoft.com.cn
 * @Date: 2020/9/30 11:22
 */
public interface RepositoryChangeListener {

    void onRepositoryChange(Properties newProperties);

}

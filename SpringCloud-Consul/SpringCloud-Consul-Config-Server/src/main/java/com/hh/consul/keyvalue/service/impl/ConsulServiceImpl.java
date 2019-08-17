package com.hh.consul.keyvalue.service.impl;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.hh.consul.keyvalue.service.ConsulService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Consul service
 * <p/>
 * Created in 2018.11.28
 * <p/>
 *
 * @author Liaozihong
 */
@Service
public class ConsulServiceImpl implements ConsulService {

    private static final Logger log = LoggerFactory.getLogger(ConsulServiceImpl.class);

    @Autowired
    private ConsulClient consulClient;

    /**
     * key所在的目录前缀，格式为：config/应用名称/
     */
    @Value("#{'${spring.cloud.consul.config.prefix}/'.concat('${spring.cloud.consul.config.name}/')}")
    private String keyPrefix;

    /**
     * 将应用的配置信息保存到consul中
     *
     * @param kvValue 封装的配置信息的map对象
     */
    @Override
    public void setKVValue(Map<String, String> kvValue) {
        for (Map.Entry<String, String> kv : kvValue.entrySet()) {
            try {
                this.consulClient.setKVValue(keyPrefix + kv.getKey(), kv.getValue());
            } catch (Exception e) {
                log.error("SetKVValue exception: {},kvValue: {}", e.getMessage(), kvValue);
            }
        }
    }

    @Override
    public void setKVValue(String key, String value) {
        try {
            this.consulClient.setKVValue(keyPrefix + key, value);
        } catch (Exception e) {
            log.error("SetKVValue exception: {},key: {},value: {}", e.getMessage(), key, value);
        }
    }

    /**
     * 获取应用配置的所有key-value信息
     *
     * @param keyPrefix key所在的目录前缀，格式为：config/应用名称/
     * @return 应用配置的所有key-value信息
     */
    @Override
    public Map<String, String> getKVValues(String keyPrefix) {
        Map<String, String> map = new HashMap<>();

        try {
            Response<List<GetValue>> response = this.consulClient.getKVValues(keyPrefix);
            if (response != null) {
                for (GetValue getValue : response.getValue()) {
                    int index = getValue.getKey().lastIndexOf("/") + 1;
                    String key = getValue.getKey().substring(index);
                    String value = getValue.getDecodedValue();
                    map.put(key, value);
                }
            }
            return map;
        } catch (Exception e) {
            log.error("GetKVValues exception: {},keyPrefix: {}", e.getMessage(), keyPrefix);
        }
        return null;
    }

    @Override
    public Map<String, String> getKVValues() {
        return this.getKVValues(keyPrefix);
    }

    /**
     * 获取应用配置的所有key信息
     *
     * @param keyPrefix key所在的目录前缀，格式为：config/应用名称/
     * @return 应用配置的所有key信息
     */
    @Override
    public List<String> getKVKeysOnly(String keyPrefix) {
        List<String> list = new ArrayList<>();
        try {
            Response<List<String>> response = this.consulClient.getKVKeysOnly(keyPrefix);

            if (response.getValue() != null) {
                for (String key : response.getValue()) {
                    int index = key.lastIndexOf("/") + 1;
                    String temp = key.substring(index);
                    list.add(temp);
                }
            }
            return list;
        } catch (Exception e) {
            log.error("GetKVKeysOnly exception: {},keyPrefix: {}", e.getMessage(), keyPrefix);
        }
        return null;
    }

    @Override
    public List<String> getKVKeysOnly() {
        return this.getKVKeysOnly(keyPrefix);
    }
}

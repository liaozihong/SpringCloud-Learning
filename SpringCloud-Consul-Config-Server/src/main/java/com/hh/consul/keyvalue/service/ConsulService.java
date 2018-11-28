package com.hh.consul.keyvalue.service;

import java.util.List;
import java.util.Map;

public interface ConsulService {
    public void setKVValue(Map<String, String> kvValue);

    public void setKVValue(String key, String value);

    public Map<String, String> getKVValues(String keyPrefix);

    public List<String> getKVKeysOnly(String keyPrefix);

    public Map<String, String> getKVValues();

    public List<String> getKVKeysOnly();
}

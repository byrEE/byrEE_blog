package com.byrEE.services;

import java.io.Serializable;

/**
 * @author byrEE
 */
public interface SettingService {
    Serializable get(String key);
    Serializable get(String key, Serializable defaultValue);
    void put(String key, Serializable value);
}

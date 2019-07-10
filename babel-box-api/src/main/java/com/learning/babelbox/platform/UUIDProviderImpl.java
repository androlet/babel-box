package com.learning.babelbox.platform;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDProviderImpl implements UUIDProvider {
    @Override
    public String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
}

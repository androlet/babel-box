package com.learning.babelbox.platform;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UUIDProviderMock implements UUIDProvider {

    private String uuid;

    public void setUuid(String value) {
        uuid = value;
    }

    @Override
    public String getRandomUUID() {
        return uuid;
    }
}

package com.learning.babelbox.platform;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class RandomProviderMock implements RandomProvider {

    private int nextRandomNumber;

    public void setNextRandomNumber(int randomNumber) {
        nextRandomNumber = randomNumber;
    }

    @Override
    public int getRandomNumber(int min, int max) {
        return nextRandomNumber;
    }

    @Override
    public Object removeRandomElement(List list) {
        return list.remove(0);
    }
}

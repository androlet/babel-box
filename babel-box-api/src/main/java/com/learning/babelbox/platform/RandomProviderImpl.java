package com.learning.babelbox.platform;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomProviderImpl implements RandomProvider {

    public int getRandomNumber(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public Object removeRandomElement(List list) {
        int randomElementIndex = getRandomNumber(0, list.size() - 1);
        return list.remove(randomElementIndex);
    }
}

package com.learning.babelbox.platform;

import java.util.List;

public interface RandomProvider {
    int getRandomNumber(int min, int max);

    Object removeRandomElement(List list);
}

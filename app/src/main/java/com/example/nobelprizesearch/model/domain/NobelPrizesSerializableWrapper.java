package com.example.nobelprizesearch.model.domain;

import java.io.Serializable;
import java.util.List;

public class NobelPrizesSerializableWrapper implements Serializable {
    public final List<NobelPrize> prizeList;

    public NobelPrizesSerializableWrapper(List<NobelPrize> prizeList) {
        this.prizeList = prizeList;
    }
}

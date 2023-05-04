package com.cosmoplat.jtx.common.result;

import java.io.Serializable;

public interface IResultType extends Serializable {
    int getCode();

    String getMessage();
}

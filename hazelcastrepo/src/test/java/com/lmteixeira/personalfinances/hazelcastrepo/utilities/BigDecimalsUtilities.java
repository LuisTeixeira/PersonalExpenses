package com.lmteixeira.personalfinances.hazelcastrepo.utilities;

import java.math.BigDecimal;

public class BigDecimalsUtilities {

    public static boolean compareBigDecimals(BigDecimal expected, BigDecimal actual) {
        return (expected.compareTo( actual ) == 0);
    }

}

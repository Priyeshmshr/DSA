package com.cpprac;

import java.util.UUID;

/**
 * @author priyesh.mishra
 */
public class IDFCTxnGeneration {

    public static void main(String[] args) {
        Long rowId = 1001l;
        String prefix = "IDFLPSILENT";
        String constant = "000000000000000000000000";
        //35 characters: - 11 = 24;
        StringBuilder sb = new StringBuilder(prefix);
        sb.append(String.format("%024d",rowId));
        System.out.println(sb.toString());
        int len=24-String.valueOf(rowId).length();
        sb.append(constant.subSequence(0,24-len));
        sb.append(rowId);

        System.out.println(UUID.randomUUID().toString());
    }
}

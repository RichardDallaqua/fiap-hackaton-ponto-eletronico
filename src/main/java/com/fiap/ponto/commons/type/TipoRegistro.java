package com.fiap.ponto.commons.type;

public enum TipoRegistro {

    ENTRADA, SAIDA, SAIDA_ALMOCO, VOLTA_ALMOCO, HORA_EXTRA;

    public static TipoRegistro getNextValue(TipoRegistro tipoRegistro){
        switch (tipoRegistro){
            case ENTRADA:
                return SAIDA_ALMOCO;
            case SAIDA_ALMOCO:
                return VOLTA_ALMOCO;
            case VOLTA_ALMOCO:
                return SAIDA;
            default:
                return HORA_EXTRA;
        }
    }
}

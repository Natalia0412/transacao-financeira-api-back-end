package com.tgid.teste.junior.utils.anottations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        if (cpf.length() != 11 || temNumerosIguais(cpf)) {
            return false;
        }
        return cpfValido(cpf);

    }

    private static boolean temNumerosIguais(String cpf) {
        char primeiroDigito = cpf.charAt(0);
        for (char digito : cpf.toCharArray()) {
            if (digito != primeiroDigito) {
                return false;
            }
        }
        return true;
    }


    public static boolean cpfValido(String cpf){
        int primeiroVerificadorMomentanio = Character.getNumericValue(cpf.charAt(9));
        int segundoVerificadorMomentanio = Character.getNumericValue(cpf.charAt(10));

        // first verification number calculate
        String primeirosNoveDigitosCPF = cpf.substring(0, 9);
        int calculo1 = getCalcularNumerosCpf(8, primeirosNoveDigitosCPF);
        int primeiroVerificador = getDigitoVerificadorReal(calculo1);

        if (primeiroVerificador != primeiroVerificadorMomentanio) {
            return false;
        }

        // second verification number calculate
        String primeirosDezNumerosDoCpf = cpf.substring(0, 10);
        int calculo2 = getCalcularNumerosCpf(9, primeirosDezNumerosDoCpf);
        int segundoVerificador = getDigitoVerificadorReal(calculo2);
        return (segundoVerificador == segundoVerificadorMomentanio);
    }

    private static int getDigitoVerificadorReal(int calculo) {
        int res = calculo % 11;
        return (res < 2) ? 0 : 11 - res;
    }

    private static int  getCalcularNumerosCpf(int rangeEnd, String numeros) {
        int res = 0;
        for (int posicao = rangeEnd; posicao >= 0; posicao--) {
            int calculo = (rangeEnd - posicao) + 2;
            res += calculo * Character.getNumericValue(numeros.charAt(posicao));
        }
        return res;
    }

//    public static boolean cnpjValido(String cnpj){
//        int[] numerosMultiplicadoresBaseCnpj1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
//        int[] numerosMultiplicadoresBaseCnpj2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
//
//        String primeiroDozeNumerosCnpj = cnpj.substring(0, 12);
//        int primeiroVerificadorMomentanio = Character.getNumericValue(cnpj.charAt(12));
//        int segundoVerificadorMomentanio = Character.getNumericValue(cnpj.charAt(13));
//
//        int calculo1 = calcularNumeroBaseCnpj(numerosMultiplicadoresBaseCnpj1, primeiroDozeNumerosCnpj);
//        int primeiroVerificador = getDigitoVerificadorReal(calculo1);
//        if (primeiroVerificador != primeiroVerificadorMomentanio ) {
//            return false;
//        }
//
//        String primeiroTrezeNumerosCnpj = cnpj.substring(0, 13);
//        int calculo2 = calcularNumeroBaseCnpj(numerosMultiplicadoresBaseCnpj2, primeiroTrezeNumerosCnpj);
//
//        int segundoVerificador = getDigitoVerificadorReal(calculo2);
//        return (segundoVerificadorMomentanio == segundoVerificador);
//
//    }
//
//    private static int calcularNumeroBaseCnpj(int[] mnultiplicadoresBase, String numeros) {
//        int res = 0;
//        for (int i = 0; i < mnultiplicadoresBase.length; i++) {
//            res += Character.getNumericValue(numeros.charAt(i)) * mnultiplicadoresBase[i];
//        }
//        return res;
//    }
}



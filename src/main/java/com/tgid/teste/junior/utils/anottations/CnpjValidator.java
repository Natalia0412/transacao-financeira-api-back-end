package com.tgid.teste.junior.utils.anottations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<Cnpj, String> {
    @Override
    public void initialize(Cnpj constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext constraintValidatorContext) {
        if (cnpj.length() != 14 || temNumerosIguais(cnpj)) {
            return false;
        }
        return cnpjValido(cnpj);

    }

    private static boolean temNumerosIguais(String cnpj) {
        char primeiroDigito = cnpj.charAt(0);
        for (char digito : cnpj.toCharArray()) {
            if (digito != primeiroDigito) {
                return false;
            }
        }
        return true;
    }

    private static int getDigitoVerificadorReal(int calculo) {
        int res = calculo % 11;
        return (res < 2) ? 0 : 11 - res;
    }

    public static boolean cnpjValido(String cnpj){
        int[] numerosMultiplicadoresBaseCnpj1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] numerosMultiplicadoresBaseCnpj2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        String primeiroDozeNumerosCnpj = cnpj.substring(0, 12);
        int primeiroVerificadorMomentanio = Character.getNumericValue(cnpj.charAt(12));
        int segundoVerificadorMomentanio = Character.getNumericValue(cnpj.charAt(13));

        int calculo1 = calcularNumeroBaseCnpj(numerosMultiplicadoresBaseCnpj1, primeiroDozeNumerosCnpj);
        int primeiroVerificador = getDigitoVerificadorReal(calculo1);
        if (primeiroVerificador != primeiroVerificadorMomentanio ) {
            return false;
        }

        String primeiroTrezeNumerosCnpj = cnpj.substring(0, 13);
        int calculo2 = calcularNumeroBaseCnpj(numerosMultiplicadoresBaseCnpj2, primeiroTrezeNumerosCnpj);

        int segundoVerificador = getDigitoVerificadorReal(calculo2);
        return (segundoVerificadorMomentanio == segundoVerificador);

    }

    private static int calcularNumeroBaseCnpj(int[] mnultiplicadoresBase, String numeros) {
        int res = 0;
        for (int i = 0; i < mnultiplicadoresBase.length; i++) {
            res += Character.getNumericValue(numeros.charAt(i)) * mnultiplicadoresBase[i];
        }
        return res;
    }
}

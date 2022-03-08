/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import java.util.ArrayList;
import pilas.PilaA;
import pilas.PilaADT;

/**
 *
 * @author Pamor
 */
public class Calculadora {

    /**
     * Regresa el número de elementos de una pila dada.
     *
     * @param pila Pila de la cual se quiere conocer su número de elementos.
     * @return - int: El número de elementos en la pila.
     */
    public static <T> int numEle(PilaADT<T> pila) {
        int contador = 0;
        ArrayList<T> apoyo = new ArrayList();
        while (!pila.isEmpty()) {
            apoyo.add(pila.pop());
            contador++;
        }
        int i = apoyo.size() - 1;
        while (i >= 0) { //lo regresa a su pos original pa q la pila no se vea afectada
            pila.push(apoyo.get(i));
            i--;
        }
        return contador;
    }

    /**
     * Determina si un carácter dado es un número o un punto decimal.
     *
     * @param car Caracter a evaluar.
     * @return <ul>
     * <li>true: si el carácter es un número o un punto decimal.
     * <li>false: si el carácter no es un número o punto decimal.
     * </ul>
     */
    public static boolean esNum(Character car) {
        boolean res = false;
        if (car == '0' || car == '1' || car == '2' || car == '3' || car == '4' || car == '5' || car == '6' || car == '7' || car == '8' || car == '9' || car == '.' || car == 'n' || car == ' ') {
            res = true;
        }
        return res;
    }

    /**
     * Determina si un carácter dado es un operador (excepto -).
     *
     * @param car Caracter a evaluar.
     * @return <ul>
     * <li>true: si el carácter es un operador.
     * <li>false: si el carácter no es un operador.
     * </ul>
     */
    public static boolean esSigno(Character car) { //no incluye el -
        boolean res = false;
        if (car == '+' || car == '*' || car == '/' || car == '^') {
            res = true;
        }
        return res;
    }

    /**
     * Determina si una expresión matemática tiene una correcta sintaxis.
     *
     * @param cad Expresión a evaluar.
     * @return <ul>
     * <li>true: si la expresión tiene sintaxis correcta.
     * <li>false: si la expresión tiene sintaxis incorrecta.
     * </ul>
     */
    public static boolean malSignos(String cad) {
        boolean res = false;
        PilaA<Character> p = new PilaA();
        int i = 0;
        if (!esSigno(cad.charAt(cad.length() - 1)) && cad.charAt(cad.length() - 1) != '-') { //si el ultimo elemento no es signo
            while (i < cad.length()) {
                if (esSigno(cad.charAt(i))) { //es signo (excepto el menos
                    try {
                        p.pop();
                    } catch (Exception e) {
                        break;
                    }
                } else if (cad.charAt(i) == '-') {
                    if (i != 0 && cad.charAt(i - 1) != '(') {
                        if (cad.charAt(i + 1) == ')') { //(3-)
                            break;
                        }
                        if (esSigno(cad.charAt(i - 1)) || cad.charAt(i - 1) == '-') {// 3*-2 o 3--2
                            break;
                        } else if (esSigno(cad.charAt(+1)) || cad.charAt(i + 1) == '-') {// 3-*2   o 3--2
                            break;
                        } else {
                            if (esNum(cad.charAt(i - 1))) {//funge como operador
                                try {
                                    p.pop();
                                } catch (Exception e) {
                                    break;
                                }
                            }
                        }

                    }

                } else if (cad.charAt(i) == '(') {
                    if (!p.isEmpty()) {//la expersion esta bien antes de '(' si la pila esta vacía
                        break;

                    }
                } else if (cad.charAt(i) == ')') {
                    if (numEle(p) != 1) {//la expersion esta bien al salir del parentesis si la pila tiene 1 elemento
                        break;
                    }
                } else { //si es num, punto decimal
                    if (i == 0) { //push el numero o decimal en la pos 0
                        p.push(cad.charAt(i));
                    } else if (esSigno(cad.charAt(i - 1)) || cad.charAt(i - 1) == ')' || cad.charAt(i - 1) == '(' || cad.charAt(i - 1) == '-') {  //pa q sirva con nums de varios digitos
                        p.push(cad.charAt(i));
                    }
                }
                i++;
            }

        }
        if (i == cad.length() && numEle(p) == 1) {
            res = true;
        }

        return res;
    }

    /**
     * Determina si una cadena cuenta con una cantidad y acomodo adecuado de
     * paréntesis.
     *
     * @param cad Cadena a evaluar
     * @return <ul>
     * <li>true: si la expresión tiene paréntesis adecuados.
     * <li>false: si la expresión tiene no paréntesis adecuados.
     * </ul>
     */
    public static boolean parentesisBal(String cad) {
        PilaA<Character> p = new PilaA();
        int i = 0;
        boolean res = false;
        while (i < cad.length()) {
            if (cad.charAt(i) == '(') {
                p.push(cad.charAt(i)); //me lo puedo ahorrar y decirle push('A') o cualquier caracter
            }
            if (cad.charAt(i) == ')') {
                try {
                    p.pop(); //lanza la exception hecha en clase 
                } catch (Exception e) { //si lanza exception, pa q no me pare el programa y c vea feo
                    break;
                }
            }
            i++;
        }
        if (i == cad.length() && p.isEmpty()) {
            res = true;
        }

        return res;
    }

    /**
     * Determina si una si una expresión matemática cuenta con una correcta
     * sintaxis y un uso adecuado de paréntesis.
     *
     * @param cad Cadena a evaluar
     * @return <ul>
     * <li>true: si la expresión cumple con ambas condiciones.
     * <li>false: si la expresión no cumple al menos una de las dos condiciones.
     * </ul>
     */
    public static boolean estaBien(String cad) {
        boolean res = false;
        Integer n = 0;

        if (malSignos(cad) && parentesisBal(cad)) {
            res = true;
        }
        return res;
    }

    public static boolean espacio(char carac) {
        boolean resp = false;
        if (carac == ' ') {
            resp = true;
        }

        return resp;
    }

    public static boolean esN(char caract) {
        boolean resp = false;
        if (caract == 'n') {
            resp = true;
        }

        return resp;
    }

    public static String resuelveExpresion(String exp) {// recibe cadena de postfijo
        String resultado;
        int i;
        int size;

        PilaADT<Double> numeros = new PilaA();
        double x = 0, y, aux2, z = 0; //variables donde guardas valores de la operacion cuando encuentras un operador
        boolean errordiv = false; //solo funciona para el caso en el que divides entre cero
        StringBuilder aux = new StringBuilder();

        i = 0;

        while (i < exp.length()) {
            //System.out.println("entre a while");

            if (esNum(exp.charAt(i))) {
                //System.out.println("vi que no es operador");

                if (exp.charAt(i) != ' ') {
                    //System.out.println("vi que no es espacio");
                    if (exp.charAt(i) == 'n') {
                        //System.out.println("vi que es n");
                        aux.append("-");
                    } else {
                        aux.append(exp.charAt(i));
                        //System.out.println("revise aux: " + aux);
                    }

                } else {

                    //System.out.println("entre a push");
                    String aux3 = aux.toString();

                    aux2 = Double.valueOf(aux3);

                    numeros.push(aux2);
                    //System.out.println("agregue a pila");
                    //System.out.println("miro pila "+ numeros.peek());
                    //System.out.println("intento vaciar aux");
                    aux.setLength(0);
                    //System.out.println("vacio aux");
                }

            } else {
                //System.out.println("intento calcular");

                switch (exp.charAt(i)) {
                    case '-':
                        x = numeros.pop();

                        y = numeros.pop();
                        z = y - x;

                        aux.append(z);

                        break;
                    case '+':
                        y = numeros.pop();

                        x = numeros.pop();

                        z = x + y;

                        aux.append(z);
                        break;

                    case '*':
                        y = numeros.pop();
                        x = numeros.pop();
                        z = x * y;

                        aux.append(z);
                        //System.out.println("calculo y dejo en aux "+ aux);
                        break;

                    case '^':
                        y = numeros.pop();
                        x = numeros.pop();
                        z = Math.pow(x, y);

                        aux.append(z);
                        break;

                    case '/':
                        y = numeros.pop();
                        x = numeros.pop();
                        if (y == 0) {
                            errordiv = true;
                        } else {
                            z = x / y;
                        }

                        aux.append(z);
                        break;
                }
            }

            i++;
        }

        if (errordiv == false) {// por si hay problema  con la division entre 0
            resultado = String.valueOf(numeros.pop());
        } else {
            resultado = "error";
        }

        return resultado;
    }

    public String infixToPostfix(String cad) {
        StringBuilder postfix = new StringBuilder();
        PilaA pilaAux = new PilaA();
        int i = 0;
        while (i < cad.length()) {
            char caracterActual = cad.charAt(i);
            if (Character.isDigit(caracterActual) || caracterActual == '.') {
                postfix.append(caracterActual);
                if (i < (cad.length() - 1) && (Character.isDigit(cad.charAt(i + 1)) || cad.charAt(i + 1) == '.')) {

                } else {
                    postfix.append(' ');
                }
            } else if (caracterActual == '(') {
                pilaAux.push(caracterActual);
            } else if (caracterActual == '+' || caracterActual == '*' || caracterActual == '/' || caracterActual == '^') {
                try {
                    char operadorTope = (char) pilaAux.peek();
                    if (caracterActual == operadorTope || operadorTope == '^') {
                        while (!pilaAux.isEmpty()) {
                            try {
                                char elementoPila = (char) pilaAux.peek();
                                if (elementoPila != '(') {
                                    postfix.append(pilaAux.pop());
                                    postfix.append(' ');
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                                postfix.append(pilaAux.pop());
                                postfix.append(' ');
                            }
                        }
                        pilaAux.push(caracterActual);
                    } else if ((operadorTope == '*' || operadorTope == '/') && (caracterActual == '+' || caracterActual == '-')) {
                        while (!pilaAux.isEmpty()) {//trata el menos como signo
                            try {
                                char elementoPila = (char) pilaAux.peek();
                                if (elementoPila != '(') {
                                    postfix.append(pilaAux.pop());
                                    postfix.append(' ');
                                } else {
                                    break;
                                }
                            } catch (Exception e) {
                                postfix.append(pilaAux.pop());
                                postfix.append(' ');
                            }
                        }

                        pilaAux.push(caracterActual);
                    } else {
                        pilaAux.push(caracterActual);
                    }
                } catch (Exception e) {
                    pilaAux.push(caracterActual);
                }
            } else if (caracterActual == '-') {
                if (i == 0) {
                    postfix.append('n');
                } else if (cad.charAt(i - 1) == '(') {//es signo
                    postfix.append('n');
                    if (i < (cad.length() - 1) && (Character.isDigit(cad.charAt(i + 1)) || cad.charAt(i + 1) == '.')) {

                    } else {
                        postfix.append(' ');
                    }
                } else { //es un operador
                    while (!pilaAux.isEmpty()) {//trata el menos como signo
                        try {
                            char elementoPila = (char) pilaAux.peek();
                            if (elementoPila != '(') {
                                postfix.append(pilaAux.pop());
                                postfix.append(' ');
                            } else {
                                break;
                            }
                        } catch (Exception e) {
                            postfix.append(pilaAux.pop());
                            postfix.append(' ');
                        }
                    }

                    pilaAux.push(caracterActual);
                }
            } else if (caracterActual == ')') {
                while (!pilaAux.isEmpty()) {
                    try {
                        char elementoPila = (char) pilaAux.peek();
                        if (elementoPila != '(') {
                            postfix.append(pilaAux.pop());
                            postfix.append(' ');
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        postfix.append(pilaAux.pop());
                        postfix.append(' ');
                    }

                }
                pilaAux.pop();
            }
            i++;
        }
        while (!pilaAux.isEmpty()) {
            postfix.append(pilaAux.pop());
            postfix.append(" ");
        }
        return postfix.toString();
    }

}

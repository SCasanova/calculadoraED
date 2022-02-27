
import Pilas.PilaA;
import Pilas.PilaADT;
import java.util.ArrayList;

/**
 *
 * @author Pamor
 */
public class Sintax {



//regresa el numero de elementos de una pila dada
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
//regresa true si es un numero del 0 al 9 o un punto decimal; false si no co¿umple con lo anterior

    public static boolean esNum(Character car) {
        boolean res = false;
        if (car == '0' || car == '1' || car == '2' || car == '3' || car == '4' || car == '5' || car == '6' || car == '7' || car == '8' || car == '9' || car == '.') {
            res = true;
        }
        return res;
    }
//regresa true si es un +,*,/

    public static boolean esSigno(Character car) { //no incluye el -
        boolean res = false;
        if (car == '+' || car == '*' || car == '/') {
            res = true;
        }
        return res;
    }
//regresa true si los signos de la expresión estan colocados correctamente; false si hay algun error de sintaxis asociado a los signos

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

    //recibe una cadena. regresa true si los parentesis estan balanceados, false si no
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

//utiliza el q checa signos y el q checa parentesis; regresa true si ambos se cumplen; false si al menos uno no se cumple
    public static boolean estaBien(String cad) {
        boolean res = false;
        Integer n = 0;

        if (malSignos(cad) && parentesisBal(cad)) {
            res = true;
        }
        return res;
    }

}

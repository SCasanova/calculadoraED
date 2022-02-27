/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 *
 * @author personal
 */
public class Postfix {
    String expresion;

    public Postfix() {
    }

    public Postfix(String expresion) {
        this.expresion = expresion;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    
    public String  infixToPostfix(){
        StringBuilder postfix = new StringBuilder();
        PilaA pilaAux = new PilaA();
        int i = 0;
        while(i < this.expresion.length()){
            char caracterActual = this.expresion.charAt(i);
            if(Character.isDigit(caracterActual)){
                postfix.append(caracterActual);
            } else if (caracterActual == '('){
                pilaAux.push(caracterActual);
            } else if(caracterActual == '+' || caracterActual == '-' || caracterActual == '*' || caracterActual == '/' || caracterActual == '^'){
                try{
                    char operadorTope = (char) pilaAux.peek();
                if(caracterActual == operadorTope || operadorTope == '^'){
                    while(!pilaAux.isEmpty()){
                        try{
                            char elementoPila = (char) pilaAux.peek();
                            if(elementoPila != '(')
                                postfix.append(pilaAux.pop());
                            else
                                break;
                        } catch(Exception e){
                            postfix.append(pilaAux.pop());
                        }
                    }
                    pilaAux.push(caracterActual);
                } else if((operadorTope == '*' || operadorTope == '/') && (caracterActual == '+' || caracterActual == '-')){
                    while(!pilaAux.isEmpty()){
                        try{
                            char elementoPila = (char) pilaAux.peek();
                            if(elementoPila != '(')
                                postfix.append(pilaAux.pop());
                            else
                                break;
                        } catch(Exception e){
                            postfix.append(pilaAux.pop());
                        }  
                    }
                     pilaAux.push(caracterActual);
                } else 
                    pilaAux.push(caracterActual);
                } catch(Exception e){
                    pilaAux.push(caracterActual);
                }
                
            } else if(caracterActual == ')'){
                while(!pilaAux.isEmpty()){
                        try{
                            char elementoPila = (char) pilaAux.peek();
                            if(elementoPila != '(')
                                postfix.append(pilaAux.pop());
                            else
                                break;
                        } catch(Exception e){
                            postfix.append(pilaAux.pop());
                        }
                            
                    }
                 pilaAux.pop();
            }
            i++;
        }
        while(!pilaAux.isEmpty()){
             postfix.append(pilaAux.pop());
        }
        return postfix.toString();
    }
    
}

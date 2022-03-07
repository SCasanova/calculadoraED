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

    
    public String  infixToPostfix(String cad){
        StringBuilder postfix = new StringBuilder();
        PilaA pilaAux = new PilaA();
        int i = 0;
        while(i < cad.length()){
            char caracterActual = cad.charAt(i);
            if(Character.isDigit(caracterActual) || caracterActual == '.'){
                postfix.append(caracterActual);
                if(i < (cad.length() -1) && (Character.isDigit(cad.charAt(i+1)) || cad.charAt(i+1) == '.')){
                    
                }else{
                    postfix.append(' ');
                }
            } else if (caracterActual == '('){
                pilaAux.push(caracterActual);
            } else if(caracterActual == '+' || caracterActual == '-' || caracterActual == '*' || caracterActual == '/' || caracterActual == '^'){
                try{
                    char operadorTope = (char) pilaAux.peek();
                if(caracterActual == operadorTope || operadorTope == '^'){
                    while(!pilaAux.isEmpty()){
                        try{
                            char elementoPila = (char) pilaAux.peek();
                            if(elementoPila != '('){
                                postfix.append(pilaAux.pop());
                                postfix.append(' '); 
                            }
                            else
                                break;
                        } catch(Exception e){
                            postfix.append(pilaAux.pop());
                            postfix.append(' '); 
                        }
                    }
                    pilaAux.push(caracterActual);
                } else if((operadorTope == '*' || operadorTope == '/') && (caracterActual == '+' || caracterActual == '-')){
                    while(!pilaAux.isEmpty()){
                        try{
                            char elementoPila = (char) pilaAux.peek();
                            if(elementoPila != '('){
                               postfix.append(pilaAux.pop());
                               postfix.append(' ');  
                            }
                            else
                                break;
                        } catch(Exception e){
                            postfix.append(pilaAux.pop());
                            postfix.append(' ');  
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
                            if(elementoPila != '('){
                               postfix.append(pilaAux.pop());
                               postfix.append(' ');  
                            }
                            else
                                break;
                        } catch(Exception e){
                            postfix.append(pilaAux.pop());
                            postfix.append(' ');
                        }
                            
                    }
                 pilaAux.pop();
            }
            i++;
        }
        while(!pilaAux.isEmpty()){
             postfix.append(pilaAux.pop());
             postfix.append(" ");
        }
        return postfix.toString();
    }
    
}

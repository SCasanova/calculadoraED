/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 * 
 * @author san-b
 */
public class resuelveExpresion {
    
    
    /**
    * Reseuelve la expresión que nos dan ya convertida a postfijo  
 */
    
    public static boolean esOperador(char caracter){  //metodo aux para ver si es numero o operador
        boolean resp=true;
        if(caracter=='1')
            resp=false;
        if(caracter=='2')
            resp=false;
        if(caracter=='3')
            resp=false;
        if(caracter=='4')
            resp=false;
        if(caracter=='5')
            resp=false;
        if(caracter=='6')
            resp=false;                 //cambiar a swich
        if(caracter=='7')
            resp=false;
        if(caracter=='8')
            resp=false;
        if(caracter=='9')
            resp=false;
        if(caracter=='0')
            resp=false;
    
    return resp;
    }
    
    
    public static String resuelveExpresion(String exp){// recibe cadena de postfijo
        String expresion, resultado;
        int i;
        int size;
        PilaADT<Double> numeros = new PilaA();
        double x=0,y; //variables donde guardas valores de la operacion cuando encuentras un operador
        boolean errordiv=false; //solo funciona para el caso en el que divides entre cero
        String aux=""; // ayuda a castear de char a double
        
        expresion =exp ;// aqui va el  resultado de  metodo que regresa la expresionismos postfija bien y revisada
        size = expresion.length();    //aqui guardo el numero de caracteres de la expresión
        
        if (!expresion.equals("error")){  //supongo que en otro metodo avisa que es error
            i = 0;
            
            while(i < size ){
                
                
                if(!esOperador(expresion.charAt(i))){
                    
                    
                    aux=String.valueOf(expresion.charAt(i));//castear a double para poder metera a pila
                    
                    numeros.push(Double.parseDouble(aux));
                    
                }
                
                 
                
                else{
                    
                  
                        switch(expresion.charAt(i)){
                            case'-':
                                x=numeros.pop();
                                
                                    y=numeros.pop();
                                    x=y-x;
                                
                                numeros.push(x);
                                
                                break;
                            case'+':
                                y = numeros.pop();
                                
                                x = numeros.pop();
                                
                                x = x + y;
                                
                                numeros.push(x);
                            break;
                            
                            case'*':
                                y=numeros.pop();
                                x=numeros.pop();
                                x=x*y;
                                numeros.push(x);
                            break;
                            
                            case'^':
                                y=numeros.pop();
                                x=numeros.pop();
                                x=Math.pow(x,y);
                                numeros.push(x);
                            break;
                                
                            
                            case'/':
                                y=numeros.pop();
                                x=numeros.pop();
                                if(y==0)
                                    errordiv=true;
                                else
                                    x=x/y;
                                numeros.push(x);
                                break;    
                        }
                }
                        
                       
                        i++;
                    }
        }
        else 
            resultado="error"; //si mandan postfijo mal desde el principio
                
                
            
            if(errordiv==false){// por si hay pedo con la division
                resultado=String.valueOf(numeros.pop());
            }
            else
                resultado="error";
         
       
        
        
         
        return resultado;
    }
    
    
    public static void main(String[] args) {
        
          System.out.println("el resultado es: " + resuelveExpresion("987*+654+*-32*+"));
          System.out.println("el resultado es: " + resuelveExpresion("97-"));
          System.out.println("el resultado es: " + resuelveExpresion("245+")); //no toma doble digito
          System.out.println("el resultado es: " + resuelveExpresion("53^"));
            
 
      
      
}
}
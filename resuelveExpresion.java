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
            resp=false;                 
        if(caracter=='7')
            resp=false;
        if(caracter=='8')
            resp=false;
        if(caracter=='9')
            resp=false;
        if(caracter=='0')
            resp=false;
        if(caracter==' ')
            resp=false;
        if(caracter=='.')
            resp=false;
    
    return resp;
    }
    public static boolean espacio(char carac){
        boolean resp=false;
        if(carac==' ')
            resp=true;
        
        
        return resp;
    }
    
    
    public static String resuelveExpresion(String exp){// recibe cadena de postfijo
        String expresion, resultado;
        int i;
        int size;
        
        PilaADT<Double> numeros = new PilaA();
        double x=0,y,aux2, z = 0; //variables donde guardas valores de la operacion cuando encuentras un operador
        boolean errordiv=false; //solo funciona para el caso en el que divides entre cero
        StringBuilder aux=new StringBuilder(); // ayuda a castear de char a double
        
        expresion =exp ;// aqui va el  resultado de  metodo que regresa la expresionismos postfija bien y revisada
        size = expresion.length();    //aqui guardo el numero de caracteres de la expresión
        
       
            i = 0;
            
            while(i < size ){
                //System.out.println("entre a while");
                
                
                if(!esOperador(expresion.charAt(i))){
                   // System.out.println("vi que no es operador");
                    
                    if(!espacio(expresion.charAt(i))){
                        //System.out.println("vi que no es espacio");
                        aux.append(expresion.charAt(i));
                        //System.out.println("revise aux: " + aux);
                    }
                    
                        
                    else{   
                    
                        //System.out.println("entre a push");
                    String aux3=aux.toString();
                    
                    aux2=Double.valueOf(aux3);
                    
                    numeros.push(aux2);
                        //System.out.println("agregue a pila");
                        //System.out.println("miro pila "+ numeros.peek());
                        //System.out.println("intento vaciar aux");
                    aux.setLength(0);
                        //System.out.println("vacio aux");
                    }
                    
                }
                
                 
                
                else{
                    //System.out.println("intento calcular");
                    
                  
                        switch(expresion.charAt(i)){
                            case'-':
                                x=numeros.pop();
                                
                                    y=numeros.pop();
                                    z=y-x;
                                
                                
                                    aux.append(z);
                                
                                break;
                            case'+':
                                y = numeros.pop();
                                
                                x = numeros.pop();
                                
                                z = x + y;
                                
                                
                                aux.append(z);
                            break;
                            
                            case'*':
                                y=numeros.pop();
                                x=numeros.pop();
                                z=x*y;
                                
                                aux.append(z);
                                //System.out.println("calculo y dejo en aux "+ aux);
                            break;
                            
                            case'^':
                                y=numeros.pop();
                                x=numeros.pop();
                                z=Math.pow(x,y);
                                
                                aux.append(z);
                            break;
                                
                            
                            case'/':
                                y=numeros.pop();
                                x=numeros.pop();
                                if(y==0)
                                    errordiv=true;
                                else
                                    z=x/y;
                                
                                aux.append(z);
                                break;    
                        }
                }
                        
                       
                        i++;
                    }
        
                
                
            
            if(errordiv==false){// por si hay problema  con la division entre 0
                resultado=String.valueOf(numeros.pop());
            }
            else
                resultado="error";
         
       
        
        
         
        return resultado;
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("el resultado es: " + resuelveExpresion("9.0 8 7 * + 6 5 4 + * - 3 2 * + 2 * "));
        System.out.println("el resultado es: " + resuelveExpresion("92 45.5 - "));
        System.out.println("el resultado es: " + resuelveExpresion("24 5.5 + ")); 
        System.out.println("el resultado es: " + resuelveExpresion("5.7 3 ^ "));
        System.out.println("el resultado es: " + resuelveExpresion("5.2 3 ^ 1000.0 * "));
        System.out.println("el resultado es: " + resuelveExpresion("10 30.56 / 8 + "));
        System.out.println("el resultado es: " + resuelveExpresion("10 0.0 / "));
        System.out.println("el resultado es: " + resuelveExpresion("5.2 3 ^ 1000.0 * 0 / "));
            
          //System.out.println("probar el metodo de espacio" + espacio(' '));
      
      
}
}
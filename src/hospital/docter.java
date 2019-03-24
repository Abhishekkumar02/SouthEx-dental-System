package hospital;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Abhishek
 */
public class docter {


    private String docname;
    private String docspec;
    private String docadd; 
    private String docphone;
   
    
    
    public docter(String pdocname, String pdocspec,String pdocphone,String pdocadd)
    {
       
        this.docname = pdocname;
         this.docspec = pdocspec; 
         this.docphone = pdocphone; 
         this.docadd = pdocadd;
       
         
         
       
    }

    
   
     public String getDocname(){
        return docname;
    }
 
    
    public String getDocspec()
    {
        return docspec;
    }
    
    public String getDocphone()
    {
        return docphone;
    }
    
    public String getDocadd()
    {
        return  docadd;
    }
   
    
  
    
    
}
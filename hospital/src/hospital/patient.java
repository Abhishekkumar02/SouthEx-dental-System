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
public class patient {
 private String id;
    private String name;
    private String gender;
    private String add; 
    
    private String dob;
    private String docter;
    private String phone;
    private float amt;
    private String pmode;
    private String date;
        private String remark;
    private String treatment;
    private String dop;
    private String print;
  
    
    public patient(String pid, String pname, String pgender,String padd,String pphone,String pdob,String pdocter,float pamt,String ppmode,String pdate,String premark,String ptreatment,String pdop,String pprint)
    {
        this.id = pid;
        this.name = pname;
         this.gender = pgender; 
         this.add = padd; 
         
         this.dob = pdob;
         this.docter = pdocter;
         this.phone = pphone;
          this.amt = pamt;
         this.pmode = ppmode;
        this.date = pdate;
        this.remark=premark;
         this.treatment=ptreatment;
          this.dop=pdop;
          this.print=pprint;
         
       
    }
      public String getPrint(){
        return print;
    }
     public String getDop(){
        return dop;
    }
     public String getTreatment(){
        return treatment;
    }
 public String getRemark(){
        return remark;
    }
   
 public String getDate(){
        return date;
    }
    public float getAmt(){
        return amt;
    }
     public String getMode(){
        return pmode;
    }
    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public String getAdd()
    {
        return  add;
    }
   
    
    public String getDob()
    {
        return  dob;
    }
     public String getDocter()
    {
        return docter;
    }
    
    public String getPhone()
    {
        return  phone;
    }

   
    
    
}
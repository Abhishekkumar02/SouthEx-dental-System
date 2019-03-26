package hospital;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Abhishek
 */
public class Exporter {
  

    /**
     *
     * @param file
     */
    Exporter(){}
    public void exortTable(JTable jTable1,File file) throws IOException{
      TableModel model=jTable1.getModel();
      FileWriter out=new FileWriter(file);
      BufferedWriter bw=new BufferedWriter(out);
      for (int i=0;i<model.getColumnCount();i++){
        bw.write(model.getColumnName(i)+"\t");
      } 
      bw.write("\n");
      for (int i=0;i<model.getRowCount();i++){
        for (int j=0;j<model.getColumnCount();j++){
          bw.write(model.getValueAt(i,j).toString()+"\t");
        }
        bw.write("\n");
      }
      bw.close();
   System.out.print("Write out to"+file);


}

   
    
}



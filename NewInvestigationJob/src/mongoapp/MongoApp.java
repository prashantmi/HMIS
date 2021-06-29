/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoapp;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class MongoApp {

    private static String connectionURI = "mongodb://localhost:27017";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MongoClient mongoClient = new MongoClient(new MongoClientURI(connectionURI));
        MongoDatabase database = mongoClient.getDatabase("cdac");
        System.out.println("s");
        XMLGeneratorService xmlG = new XMLGeneratorService();
        xmlG.beepForAnHour();
        PDFGeneratorService pdfG = new PDFGeneratorService();
        pdfG.beepForAnHour();

    }

}

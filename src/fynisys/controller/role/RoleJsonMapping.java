/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fynisys.controller.role;

/**
 *
 * @author daffodil-11
 */

    import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RoleJsonMapping {
	public static void main(String[] args) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                
                
                
                // Convert JSON string from file to Object
                //RoleBean[] user = mapper.readValue(new File("D:\\user.json"), RoleBean[].class);
                List<RoleBean> user;
                user = Arrays.asList(mapper.readValue(new File("D:\\user.json"), RoleBean[].class));
                System.out.println("THis is the use"+user.size());
            } catch (IOException ex) {
                Logger.getLogger(RoleJsonMapping.class.getName()).log(Level.SEVERE, null, ex);
            }

	}


    
}

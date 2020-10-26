package com.example.demo.controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.*;
import com.example.demo.repo.EmployeeRepo;





@RestController
@RequestMapping("/api/v1")
public class controller {

	@Autowired
	private EmployeeRepo erepo;
	
	@PostMapping("/Employee")
	public model createEmployee(@RequestBody model emp){
		return erepo.save(emp);
		
	}
	
	@GetMapping("/Employee/name/{firstname}")
	public ResponseEntity<model> getEmployeeById(@PathVariable(value="firstname") String firstname) {
		//model m= erepo.findById(empID).get() ;
		model m = erepo.findByfirstname(firstname);
		System.out.println(m.toString());
		return ResponseEntity.ok().body(m) ;
		
	}
	
	@GetMapping("/Employee/{id}")
	public ResponseEntity<model> getEmployeeById(@PathVariable(value="id") Long empID) {
		model m= erepo.findById(empID).get() ;
		//model m = erepo.findByfirstname(firstname);
		System.out.println(m.getBranch());
		return ResponseEntity.ok().body(m) ;
		
	}
	
	
	/*
	@GetMapping("/Employee/{id}")
	public ResponseEntity<model> getEmployeeById(@PathVariable(value="id") Long empID) {
		model m= erepo.findById(empID).get() ;
		String cityName = m.getCity();
		String countryCodeAPI = "http://battuta.medunes.net/api/country/search/?city=" + cityName + "&key=f6aa01d280721e65f81f6a9f1bf2a1fa";
		ArrayList<HashMap<String, String>> genreList = new ArrayList<HashMap<String, String>>();
		JSONParser json = new JSONParser();
		/*
		try {
        JSONArray arr = (JSONArray)json.parse(countryCodeAPI);         
        for (int i = 0; i < arr.size(); i++) {
            JSONObject obj=(JSONObject)arr.get(0); 
            System.out.println("Country Code : "+obj.get(code));
            System.out.println("Country Name : "+obj.get(name));
        } 
        } catch (ParseException e) {                
            e.printStackTrace();
        }*/
       /* 
        JSONArray jsonArray = new JSONArray("your str"); 
    	for (Object object : jsonArray) { 
        JSONObject jsonObject = (JSONObject)object;
        System.out.println(jsonObject.toString()); 
    }
		
		return ResponseEntity.ok().body(m) ;
		
	}

	*/
	
	
    @GetMapping("/Employee")
    public ResponseEntity<Iterable<model>> getAllEmployee() {
        Iterable<model> iterable = erepo.findAll();
          for (model employee : iterable) {
              System.out.println(employee);
          }
        return ResponseEntity.ok().body(iterable) ;
        
        
    }
  

	
	@PutMapping("/Employee/{id}")
	public ResponseEntity<model> getEmployeeById(@PathVariable(value="id") Long empID,@RequestBody model empdetails) {
		model m= erepo.findById(empID).get() ;
		m.setFirstname(empdetails.getFirstname());
		m.setLastname(empdetails.getLastname());
		m.setBranch(empdetails.getBranch());
		final model updateModel = erepo.save(m);
		System.out.println(updateModel.toString());
		return ResponseEntity.ok(updateModel);
		
	}
	
	@DeleteMapping("/Employee/{id}")
	public Map<String,Boolean> deleteEmployee(@PathVariable(value="id") Long empID) {
		model m= erepo.findById(empID).get() ;
		erepo.delete(m);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted",true) ;
		return response;
	}
}

package com.example.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.CustomerService;
@Controller
@CrossOrigin(origins = "*")

public class AppController {
	
	  @Autowired
	  private CustomerService customerService;
	  
	  @Autowired
	  private ReportService rpClass;
	  
	  /*This method get json param by request like
	   * {
		    "id":"98",
		    "name":"Jaman Dewan",
		    "age":"33",
		    "email":"jaman@yahoo.com"
		}
	   * and return string response*/
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/customer/string/data", method = RequestMethod.POST, consumes = "application/json")
	  @ResponseBody
	  public String showCustomerJSON(@RequestBody Customer c) {
		  
		  return "Customer Id is: "+c.getId()+"\nName is: "+c.getName()+"\nAge is: "+c.getAge()+"\nEmail is: "+c.getEmail();
	  }
	  
	  /*This method get no param by request but return json response*/
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/customer/list/data", method = RequestMethod.POST, consumes = "application/json")
	  @ResponseBody
	  public List<Customer> showCustomerFromDB(@RequestBody Customer c) {
		  List<Customer> listCustomer = customerService.selectAllCustomer();
		  return listCustomer;
	  }
	  
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/")
	  public String showCustomerInHomePage(Model model) {
		  List<Customer> listCustomer = customerService.selectAllCustomer();
		  model.addAttribute("cutomerList", listCustomer);
		  
		  return "index"; 
	  }
	  
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping("/showInsertionPage")
	  public String showInsertionPage(Model model) {
		  Customer customer = new Customer();
		  model.addAttribute("customerClass", customer);
		  
		  return "insertionPage";
	  }
	  
	  @CrossOrigin(origins = "http://localhost:4200")
	  @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	  public String saveCustomer(@ModelAttribute("Customer") Customer customer) {
		  customerService.save(customer);
		  
		  return "redirect:/";
	  }
	  
	  @RequestMapping("/showEditPage/{id}") 
	  public ModelAndView showEditPage(@PathVariable(name = "id") Integer id) { 
	  
		  List<Customer> customer = customerService.findById(id);
		  
		  ModelAndView mav = new ModelAndView("editPage");
		  mav.addObject("cusList", customer);
		  
		  return mav; 
	  }
	 
	  @RequestMapping(value = "/updateCustomerById", method = RequestMethod.POST)
	  public String updateCutomerById(@ModelAttribute("Customer") Customer customer) {
		  customerService.updateCutomerById(customer);
		  return "redirect:/";
	  }
	  
	  @RequestMapping(value = "/deleteCustomerById/{id}")
	  public String deleteCutomerById(@PathVariable(name = "id") Integer id) {
		  customerService.deleteSingleCutomerById(id);
		  return "redirect:/";
	  }
	  
	  @RequestMapping(value = "/cr/{extension}")
	  public String generateReport(@PathVariable(name = "extension") String extension) throws Exception {
		  return rpClass.selectAllCustomerForReport(extension);
		}
	  
}

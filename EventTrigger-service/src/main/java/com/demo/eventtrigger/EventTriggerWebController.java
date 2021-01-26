package com.demo.eventtrigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventTriggerWebController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventTriggerWebController.class);
	@Autowired
	EventService eventService;
	
	
   @PostMapping("/submitform")
   public String foobarPost(
       @ModelAttribute("command") FormCommand command,
       Model model ) {
	   return "redirect:/submit?city="+command.dropdownSelectedValue+"&status="+command.radioButtonSelectedValue 	;
   }

   @GetMapping("/submit")
   public String getDetails(@ModelAttribute("command") FormCommand command,
		   Model model, @RequestParam(value="city",required=false) String city,@RequestParam(value="status", required=false) String status) {
	   System.out.println(city);
	   command.dropdownSelectedValue=city;
	   command.radioButtonSelectedValue= status;  
	    
	   if (null!=city && null!=status) {
	   try {
			eventService.post(status.equalsIgnoreCase("open")?true:false,city.toUpperCase());
		} catch (Exception e) {
			// 
			LOGGER.error("Error in calling service >>> {}",e);
		}
	   }
	   return "submit";
   }
   
   @ModelAttribute("lidStatus")
   public String[] getLidStatus() {
       return new String[] {
           "open", "close"
       };
   }
   
   @ModelAttribute("singleSelectAllValues")
   public String[] getDropdownAllValues() {
       return new String[] {
           "BANGALORE", "DELHI", "KOLKATA", "MUMBAI", 
           "HYDERABAD", "CHENNAI", "KOCHI"
       };
   }
}


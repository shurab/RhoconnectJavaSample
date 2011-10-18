package com.rhomobile.contact.controller;

import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;

import com.rhomobile.contact.form.Contact;
import com.rhomobile.contact.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    //get log4j handler
	private static final Logger logger = Logger.getLogger(ContactController.class);	
	
	@Autowired
	private ContactService contactService;

	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {
//		map.put("contact", new Contact());
		map.put("contactList", contactService.listContact());

		return "index";
	}

	@RequestMapping("/new")
	public String newContact(Map<String, Object> map) {
		map.put("contact", new Contact());
//		if(logger.isDebugEnabled()) logger.debug("Logger: ContactController::new");
//		logger.info("Logger:info: ContactController::new");
		
		return "new";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
	Contact contact, BindingResult result) {
		logger.info(contact.toString());
		if(contact.getId() == null) {
			logger.info("Creating new contact ...");			
			contactService.addContact(contact);			
		} else {
			logger.info("Updating existing contact ...");
			contactService.updateContact(contact);
		}
		
		return "redirect:/index";
	}

	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId")
	Integer contactId) {
		contactService.removeContact(contactId);

		return "redirect:/index";
	}
	
	// TODO:
	@RequestMapping(value = "/show/{contactId}", method = RequestMethod.GET)
	public ModelAndView showContact(@PathVariable("contactId")
	Integer contactId) {
		Contact contact = contactService.getContact(contactId);
		logger.info(contact.toString());

		ModelAndView mav = new ModelAndView();		
        // Use the view named "show" to display the data
        mav.setViewName("show");
        // Add a model object to be displayed by the view
        mav.addObject("contact", contact);

        return mav;
	}

	@RequestMapping("/edit/{contactId}")
	public ModelAndView editContact(@PathVariable("contactId")
	Integer contactId, Contact contact) {
		contact = contactService.getContact(contactId);
		logger.info(contact.toString());

		ModelAndView mav = new ModelAndView();		
        // Use the view named "edit" to display the data
        mav.setViewName("edit");
        // Add a model object to be displayed by the view
        mav.addObject("contact", contact);

        return mav;
	}
		
}

package com.mastersheel007.springrestdemo.controller;

import com.mastersheel007.springrestdemo.domain.Person;
import com.mastersheel007.springrestdemo.dto.PersonDTO;
import com.mastersheel007.springrestdemo.service.intf.IPersonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mastersheel007
 */
@Controller
public class PersonRestController {

	@Autowired
	private IPersonService personService;

	@RequestMapping(value = "/RESTService/", method = RequestMethod.GET)
	public @ResponseBody String personHome() {
		
		return "<h1>Welcome To The RESTFul Person</h1>";
	}

	@RequestMapping(value = "/RESTService/persons/", method = RequestMethod.POST, 
			produces = { "application/json", "application/xml" }, 
			consumes = { "application/json", "application/xml" })
	public @ResponseBody PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
		
		return new PersonDTO().toDTO(personService.createPerson(personDTO
				.toDomain()));
	}

	@RequestMapping(value = "/RESTService/persons/{personId}/", method = RequestMethod.DELETE)
	public @ResponseBody Boolean deletePerson(@PathVariable("personId") Long personId) {
		
		return personService.deletePersonById(personId);
	}

	@RequestMapping(value = "/RESTService/persons/{personId}/", method = RequestMethod.GET, 
			produces = { "application/json", "application/xml" })
	public @ResponseBody PersonDTO getPerson(@PathVariable("personId") Long personId) {
		
		return new PersonDTO().toDTO(personService.findById(personId));
	}

	@RequestMapping(value = "/RESTService/persons/", method = RequestMethod.PUT, 
			produces = {"application/json", "application/xml" }, 
			consumes = {"application/json", "application/xml" })
	public @ResponseBody PersonDTO updatePerson(@RequestBody PersonDTO updatedPersonDTO) {
		
		return new PersonDTO().toDTO(personService
				.updatePerson(updatedPersonDTO.toDomain()));
	}

	@RequestMapping(value = "/RESTService/persons/", method = RequestMethod.GET, 
			produces = {"application/json", "application/xml" })
	public @ResponseBody List<PersonDTO> getAllPersons() {
		
		List<PersonDTO> personDTOs = new ArrayList<>();
		for (Person person : personService.findAll()) {
			PersonDTO personDTO = new PersonDTO();
			personDTOs.add(personDTO.toDTO(person));
		}
		return personDTOs;
	}
}
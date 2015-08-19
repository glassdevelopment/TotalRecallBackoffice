package com.fing.proygrad.totalrecallbackoffice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/channel")
public class ChannelAPI {

	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
	  return "Hello Jersey";
	}
	
}

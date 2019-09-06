package spring.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.CouponClientFacade;
import spring.CouponSystem;
import spring.Session;
import spring.exceptions.couponSystemException;
import spring.models.ClientType;

@RestController
@RequestMapping("Login")
public class LoginController {

	@Autowired
	private Map<String, Session> tokens;
	@Autowired
	private CouponSystem couponSystem;
	
	
	@PostMapping("login")
	public ResponseEntity<String>login(@RequestParam String name, @RequestParam String password, @RequestParam String clientType){
		if (!clientType.equals("ADMIN") && !clientType.equals("COMPANY") && !clientType.equals("CUSTOMER")) {
			return new ResponseEntity<>("Check clientType again", HttpStatus.UNAUTHORIZED);
		}
		
		Session session = null;
		CouponClientFacade facade = null;
		String token = UUID.randomUUID().toString();
		long lastAccessed=System.currentTimeMillis();
		
		try {
			facade=couponSystem.login(name, password, ClientType.valueOf(clientType));
			session = new Session();
			session.setFacade(facade);
			session.setLastAccesed(lastAccessed);
			tokens.put(token, session);
			return new ResponseEntity<>(token,HttpStatus.OK);
		} catch (couponSystemException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}
}

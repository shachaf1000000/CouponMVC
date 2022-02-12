package com.project.CouponMVC.tokens;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.project.CouponMVC.exceptions.TokenDoesNotExist;
import com.project.CouponMVC.services.ClientService;
import com.project.CouponMVC.utils.BaseCount;

@Component
public class TokenManager {

	private static final long EXPIRATION_TIME_PERIOD_IN_MILLIS = 1000*60*10; //10 min
	private static final long TOKENS_ALIVE_PRINTER_PERIOD = 10000;
	
	Map<Token, ClientService> tokenVSservices = new HashMap<>();
	
	public TokenManager() {
		super();
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(TOKENS_ALIVE_PRINTER_PERIOD);
						System.out.println("Tokens alive:");
						for (Entry<Token, ClientService> entry : tokenVSservices.entrySet()) {
							System.out.println(entry.getKey() + " --> " + entry.getValue());
						}
					}
				} catch (Exception e) {
					System.out.println("thread stopped");
				}
			}
		});
		
		t.setDaemon(true);
		t.start();
		
	}

	public synchronized String getToken(ClientService service) {
			
		Token token;
		do {
			token = new Token();
		}while(tokenVSservices.containsKey(token));
		
		
		tokenVSservices.put(token, service);
		
		return token.getToken();
	}
	
	public synchronized ClientService getService(String token) throws TokenDoesNotExist {
		Token fetchedToken = new Token(token);
		ClientService service = tokenVSservices.get(fetchedToken);
		
		if(service==null)
			throw new TokenDoesNotExist();
		
		return service;
	}
	
/**
 * 
 * @return a set of deleted tokens.
 */
	public synchronized Set<Token> deleteExpiredCoupons(){
		Set<Token> tokens = tokenVSservices.keySet();
		Set<Token> deletedTokens = new HashSet<>();
		
		for (Token token : tokens) {
			if(token.isExpired()) {
				deletedTokens.add(token);
				tokenVSservices.remove(token);
			}
		}
		
		return deletedTokens;
	}
	
	public class Token {
		
		private static final int TOKEN_STRING_LENGTH = 8;
		
		private String token;
		private long createTime;
		
		public Token() {
			super();
			this.token = BaseCount.getRandomString(TOKEN_STRING_LENGTH);
			this.createTime = (new Date()).getTime();
		}

		public Token(String tokenString) {
			super();
			this.token = tokenString;
		}
		
		public void printRemainTimeInSeconds() {
			long nowTime = (new Date()).getTime();
			long remainTime = (EXPIRATION_TIME_PERIOD_IN_MILLIS-(nowTime-createTime));
			String t="";
			if(remainTime<1000){
				t="Milliseconds";
			}
			else if(remainTime<60000) {
				t="Seconds";
				remainTime/=1000;
			}
			else {
				t="Minutes";
				remainTime/=(60*1000);
			}
			System.out.println(token+", Remaining time, a litle bit more than " +remainTime+" "+t);
		}

		public boolean isExpired()
		{
			long nowTime = (new Date()).getTime();
			return (nowTime-createTime)>EXPIRATION_TIME_PERIOD_IN_MILLIS;
		}
		
		public String getToken() {
			return token;
		}

		public long getCreateTime() {
			return createTime;
		}

		
		
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((token == null) ? 0 : token.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Token other = (Token) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (token == null) {
				if (other.token != null)
					return false;
			} else if (!token.equals(other.token))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Token [token=" + token + ", createTime=" + createTime + "]";
		}

		private TokenManager getEnclosingInstance() {
			return TokenManager.this;
		}
	}
	
}

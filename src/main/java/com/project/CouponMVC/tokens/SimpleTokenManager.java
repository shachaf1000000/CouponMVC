package com.project.CouponMVC.tokens;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenManager {

	private static final long EXPIRATION_TIME_PERIOD_IN_MILLIS = 1000*60*10; //10 min
	private static final long EXPIRATION_THREAD_PERIOD_IN_MILLIS = 1000*5; //5 seconds
	private static long lastToken = 5678;
	private Map<String, Token> tokens = new HashMap<>();

	
	public SimpleTokenManager() {
		super();
	}

	synchronized public boolean isTokenExist(String token) {
		return tokens.containsKey(token);
	}

	synchronized public String getNewToken() {
		Token newToken = new Token();
		tokens.put(newToken.getToken(), newToken);
		System.out.println("New token created by server: "+newToken.getToken());
		return newToken.getToken();
	}
	
	/**
	 * initializing a timer thread that will operate each EXPIRATION_THREAD_PERIOD_IN_MILLIS millis
	 * and remove the expired tokens.
	 */
	public void initThread() {
	    int delay = 0; // delay for 0 sec.
	    Timer timer = new Timer();

		System.out.println("Remove Expired Sessions Thread Initialized!");
	    timer.scheduleAtFixedRate(new TimerTask() {
	      public void run() {
	        removeExpiredSessions();
	      }
	    }, delay, EXPIRATION_THREAD_PERIOD_IN_MILLIS);
	}
	
	synchronized private void removeExpiredSessions() {
		//System.out.println("Remove Expired Sessions Thread Started!");
		System.out.println("---------------------------------------------------------");
		Map<String, Token> remainedTokens = new HashMap<String, SimpleTokenManager.Token>();
		for (Token token : tokens.values()) {
			if (token.isExpired()) {
				System.out.println(token+" has expired!");
			}
			else {
				token.printRemainTimeInSeconds();
				remainedTokens.put(token.getToken(), token);
			}
		}
		tokens = remainedTokens;
		System.out.println("---------------------------------------------------------");
		System.out.println("Remove Expired Sessions Thread Done, active sessions:"+tokens.size());
	}
	
	public class Token {
		private String token;
		private long createTime;
		
		public Token() {
			super();
			this.token = "Coupn_project_token_"+lastToken++;
			this.createTime = (new Date()).getTime();
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
		public String toString() {
			return "Token [token=" + token + ", createTime=" + createTime + "]";
		}
	}

}

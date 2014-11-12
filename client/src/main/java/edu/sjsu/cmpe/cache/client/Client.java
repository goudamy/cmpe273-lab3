package edu.sjsu.cmpe.cache.client;
import java.util.ArrayList;
import java.util.List;
import com.google.common.hash.Hashing;

public class Client {
		
    public static void main(String[] args) throws Exception {
           	
    	  	 
    	List<CacheServiceInterface> cache = new ArrayList<CacheServiceInterface>();
    	 	cache.add(new DistributedCacheService("http://localhost:3000"));
    	 	cache.add(new DistributedCacheService("http://localhost:3001"));
    	 	cache.add(new DistributedCacheService("http://localhost:3002"));
    	
    	       
    	
    	char[] value = {'z','a','b','c','d','e','f','g','h','i','j'};
    	
    	System.out.println("Put Values ");
       	
    	for(int i=1;i<=10;i++)
    	{
    		char pair = value[i];
    		int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i)), cache.size());
    	    cache.get(bucket).put(i, Character.toString(pair));
     		System.out.println( + i +"=>"+ pair );
     					
     	}
    	System.out.println("Get Values ");
    	
    	for(int j=1;j<=10;j++)
    	{   
    		//char pair = value[j];
    		int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(j)), cache.size());
    		System.out.println( j + "=>" + cache.get(bucket).get(j));
    	}
    	
    	       
        
		
    }

}


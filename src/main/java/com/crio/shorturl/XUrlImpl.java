package com.crio.shorturl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class  XUrlImpl implements XUrl{
    HashMap <String,String> mp;
    HashMap <String,Integer> countTrack;
   public XUrlImpl()
   {
     mp=new HashMap<String,String>();
     countTrack=new HashMap<String,Integer>();
   }



    static String getAlphaNumericString(int n)
 {
 
  // chose a Character random from this String
  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
         + "0123456789"
         + "abcdefghijklmnopqrstuvxyz";
 
  // create StringBuffer size of AlphaNumericString
  StringBuilder sb = new StringBuilder(n);
 
  for (int i = 0; i < n; i++) {
 
   // generate a random number between
   // 0 to AlphaNumericString variable length
   int index
    = (int)(AlphaNumericString.length()
      * Math.random());
 
   // add Character one by one in end of sb
   sb.append(AlphaNumericString
      .charAt(index));
  }
 
  return sb.toString();
 }
//////////////////////////////////////////////////////////////
    public String registerNewUrl(String longUrl)
    {
      
       
      if( mp.containsKey(longUrl)) 
       {
        return mp.get(longUrl);
       }
       else{

        String shortUrl="http://short.url/"+ getAlphaNumericString(9);
          mp.put(longUrl, shortUrl);
          return shortUrl;
       }

    }

    public String registerNewUrl(String longUrl, String shortUrl)
    {
          if(mp.containsValue(shortUrl))
          return null;
          else{
            mp.put(longUrl, shortUrl);
            return shortUrl;
          }
    }

    public String getUrl(String shortUrl)
    {
     
        Set set=mp.entrySet();
        Iterator i=set.iterator();
        
        while(i.hasNext())
        {
            Map.Entry me=(Map.Entry)i.next();

            if(me.getValue().equals(shortUrl))
             {

                if(countTrack.containsKey(me.getKey()))
                {
                    int num=countTrack.get(me.getKey());
                    countTrack.put((String)me.getKey(),(Integer)(num+1) );
                }
                else{
                    countTrack.put((String)me.getKey(),(Integer)1);
                }
             
                return (String) me.getKey();
                
             }
            
            
        }

        return null;
    }
  
    public Integer getHitCount(String longUrl)
    {
        if(countTrack.containsKey(longUrl))
           return countTrack.get(longUrl);
           else
           return (Integer)0;
    }
   
   public  String delete(String longUrl)
    {
      if(mp.size()!=0)
      mp.remove(longUrl);
      return longUrl;
    }





  }
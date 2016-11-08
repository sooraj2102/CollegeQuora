package soorajshingari.kietmessenger;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sooraj on 10-10-2016.
 */
@IgnoreExtraProperties
public class CreatePostConstructor {
public String PostSubject,PostBody,Uid,key;
    public CreatePostConstructor(String postSubject, String postBody,String uid,String key) {
        PostSubject = postSubject;
        PostBody = postBody;
        Uid=uid;
        this.key=key;
    }
    @Exclude
   public Map<String,Object> toMap() {
        HashMap<String,Object> result=new HashMap<>();
        result.put("uid",Uid);
        result.put("Subject",PostSubject);
        result.put("Body",PostBody);
        result.put("Key",key);
        return result;
    }
}
